import transform from './transform';
import { MonthlyDataType, ParticipationDataType, DataType } from 'entity';
import fs from 'fs'
import { MONTHLY_DB, PARTICIPANTS_PROPERTIES } from '../properties/path';
import DataSourceTx from './DataSourceTx';


export default class DataSource implements DataSourceTx { 
    
    private static instance?: DataSourceTx;
    private pariticipationData? : ParticipationDataType;
    private monthlyData? : MonthlyDataType;

    public static getInstance(): DataSourceTx {
        if (DataSource.instance === undefined) {
            DataSource.instance = new DataSource();
        }
        return DataSource.instance;
    }

    private constructor() {
        this.loadData();
    }

    saveData(newMonthlyData: MonthlyDataType): void {

        this.monthlyData = newMonthlyData;

        try {
            if (this.monthlyData) {
                this.monthlyData.update = new Date().toISOString();
                fs.writeFileSync(MONTHLY_DB, JSON.stringify(this.monthlyData));
            }    
        } catch {
            throw new Error('write failre: cannot write MONTHLY_DB')
        }   
        this.loadData();       
     }

    loadData() : void {

        let monthly_db : MonthlyDataType;
        let participants : string[];

        try {
            console.log(MONTHLY_DB);
            monthly_db = JSON.parse(fs.readFileSync(MONTHLY_DB).toString()) as MonthlyDataType;
        } catch (e) {
            throw new Error('load failure : cannot read MONTHLY_DB'  + 'message : ' + e);
        }

        try {
            participants = JSON.parse(fs.readFileSync(PARTICIPANTS_PROPERTIES).toString()) as string[]
        } catch (e) {
            throw new Error('load failure : cannot read PARTICIPANTS_PROPERTIES' + 'message : ' + e);
        }

        try {     
            this.monthlyData = monthly_db;
            this.pariticipationData = transform(this.monthlyData, participants);
            console.log(this.pariticipationData);
        } catch (e) {
            throw new Error('load failure : cannot load to DatabaseSource' + 'message : ' + e);
        }
        // if (this.monthlyData && participants_pp) {
        //     const participants = JSON.parse(participants_pp.toString()) as string[]
        // } else {
        //     throw new Error('읽기 ')
        // }
        // this.pariticipationData = JSON.parse(fs.readFileSync(PARTICIPANTS_DB).toString()) as ParticipationDataType
    }

    getData() : DataType {
        return {
            participationData: this.pariticipationData,
            monthlyData : this.monthlyData
        }
    }
}