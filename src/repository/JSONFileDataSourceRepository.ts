import { Attendance, DataType, Monthly, Participant } from "entity";
import { Calendar } from "../util";
import { DataSource, DataSourceTx } from "../datasource";
import MontlyRepository from "./MonthlyRepository";
import ParticipantRepository from "./ParticipantRepository";

export default class JSONFileDataSourceRepository implements ParticipantRepository, MontlyRepository {

    private dataSource : DataSourceTx;
    private data : DataType;

    constructor() {
        this.dataSource = DataSource.getInstance();
        this.data = this.dataSource.getData();
    }

    queryByYYYYMMInMontly(yyyymm: string): Monthly | null {
        if (!this.data.monthlyData) return null;
        const monthlyData = this.data.monthlyData.data.filter( monthly => monthly.yyyymm === yyyymm)
        if (monthlyData.length < 1) return null;
        return monthlyData[0];
    }

    queryLatestAttedanceByYYYYMM(yyyymm: string): Attendance | null {
        const monthlyData = this.queryByYYYYMMInMontly(yyyymm);
        if (monthlyData == null) return null;
        if (monthlyData.attendance.length < 1) return null;
        return monthlyData.attendance[monthlyData.attendance.length - 1];
    }

    updateAttendants(yyyymm: string, day: string, attendants: string[]): void {
        console.log("start");
        if (!this.data.monthlyData) return;
        console.log("end");
        let dirty = false;

        for (const monthly of this.data.monthlyData.data) {
            if (monthly.yyyymm !== yyyymm) continue;
            for (const attendance of monthly.attendance) {
                if (attendance.day === day) {
                    dirty = true;
                    attendance.checked = attendants;
                    break;
                }
            }
            if (dirty) break;
        }

        if (dirty) {
            this.dataSource.saveData();
        }
    }

    addNewMeta(date: Calendar, issue_number: number): void {
        
        if (!this.data.monthlyData) return;

        let yyyymm = date.builder().yyyymm.build();
        let day = date.builder().date.build();
        let year = date.builder().year.build();
        let month = date.builder().month.build();

        const data = this.data.monthlyData.data;

        let dirty = false;
        for (let monthly of data) {
            if (monthly.yyyymm === yyyymm) { 
                console.log("YYY-dirty-1");
                monthly.attendance.push({
                    day : day,
                    checked:[],
                    issue_number: issue_number
                })
                dirty = true;
                break;
            }
        }

        if (!dirty) {
            console.log("YYY-dirty-2");
            data.push({
                yyyymm : yyyymm,
                year : year,
                month : month,
                attendance: [
                    {
                        day : day,
                        checked:[],
                        issue_number: issue_number
                    }
                ]
            })
            dirty = true;
        }

        console.log(this.data.monthlyData.data[2].attendance);
        console.log(this.data.monthlyData.data[2].attendance.length);
        this.dataSource.saveData();
        console.log("YYY-dirty-3");
        console.log(this.data.monthlyData.data[2].attendance);
        console.log(this.data.monthlyData.data[2].attendance.length);

    }


    queryAllMonthly(): Monthly[] {
        if (!this.data.monthlyData) return [];
        return this.data.monthlyData.data;
    }
    
    queryAvailableYYYYMMM(): string[] {
        if (!this.data.participationData) return [];
        return Array.from(
            this.data.participationData.data
                .reduce( (prev, curr) => {
                curr.participation.forEach( (e) => {
                    prev.add(e.yyyymm);
                }
            )
            return prev;
        }, new Set<string>()))
        .sort((a, b) => parseInt(b)-parseInt(a));
    }
    
    queryByYYYYMM(yyyymm: string): Participant[] {
        if (!this.data.participationData) return [];
        return this.data.participationData.data.reduce( (prev, curr) => {
            const participation = curr.participation.filter( v => v.yyyymm === yyyymm)
            if (participation.length > 0) {
                prev.push({
                    id : curr.id,
                    participation: participation
                });    
            }
            return prev 
        }, [] as Participant[])
    }

    queryById(id: string): Participant | null{
        if (!this.data.participationData) return null;
        const result =  this.data.participationData.data.filter( (participant) => participant.id === id);
        return result.length > 0 ? result[0] : null;
    }

    queryAllParticipants(): Participant[] {
        if (!this.data.participationData) return [];
        return this.data.participationData.data;
    }
}