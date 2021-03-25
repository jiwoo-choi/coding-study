import Calendar from "./Calendar";
import { DataType, Monthly, MonthlyDataType, Participant, ParticipationDataType } from "../entity";


export interface BarTypeChart {
    type : 'bar' | 'pie',
    data : {
        labels: string[],
        datasets: {
            backgroundColor?: string,
            label?:string,
            data:number[]
        }[]
    }
    options: {
        legend: {
            display:boolean;
        },
        scales?: {
            yAxes:
                {
                  ticks: {
                    stepSize: number,
                    min: number
                    max: number,  
                  },
                }[]
         }
    }
  }


//https://quickchart.io/documentation/
//https://quickchart.io/documentation/#parameters
export default class ChartQueryGenerator {



    getMonthlyPariticipantRate(participantInfo:Participant) : BarTypeChart {
        
        const calendarDP = {
        } as {
            [yyyymm:string] : number;
        }

        const labels : string[] = [];
        const data : number[]= [];

        participantInfo.participation.forEach ( v => {
            const calendar = new Calendar(v.yyyymm);
            if (calendarDP[v.yyyymm] === undefined) calendarDP[v.yyyymm] = calendar.getTotalDaysInMonths(()=> ".").length;
            const totalLength = calendarDP[v.yyyymm];
            const attended = v.attendance.filter( point => point === true ).length;
            labels.push(calendar.builder("-").year.month.build());
            data.push(Math.round((attended / totalLength) * 100));  
        })

        return {
            type : 'bar',
            data : {
                labels : labels,
                datasets: [{
                    backgroundColor : 'rgb(54,162,235)',
                    data:data
                }]    
            },
            options : {
                legend : {
                    display:false,
                },
                scales : {
                    yAxes:[
                        {
                            ticks:{
                                stepSize:5,
                                min:0,
                                max:100,
                            }
                        }
                    ]
                }
            },
            
        }
    }

    getSumRateByPplSinceBeginning(allParticipantData:Participant[]): BarTypeChart {
      
        const participantDP = {} as {
            [id:string] : number;
        }

        allParticipantData.forEach( participant => {
            if (participantDP[participant.id] === undefined) participantDP[participant.id] = 0;
            participantDP[participant.id] += participant.participation.reduce( (prev, curr) => {
                prev += curr.attendance.filter( attnded => attnded === true).length;
                return prev;
            }, 0);
        })

        const labels = Object.keys(participantDP);
        const data = labels.map( v => participantDP[v]);

        return {
            type : 'bar',
            data : {
                labels : labels,
                datasets: [{
                    backgroundColor: 'rgb(255,99,132)',
                    data: data
                }]    
            },
            options : {
                legend : {
                    display:false,
                }
            }
        };
    }

    getSumRateByMonthSinceBegining(allMonthlyData:Monthly[]) : BarTypeChart {

        const monthlyDP = {} as {
            [yyyymm:string] : number;
        }

        allMonthlyData.forEach( monthly => {
            if (monthlyDP[monthly.yyyymm] === undefined) { monthlyDP[monthly.yyyymm] = 0 }
            monthlyDP[monthly.yyyymm] += monthly.attendance.reduce( (prev, curr) => {
                prev += curr.checked.length;
                return prev;
            }, 0);
        })


        const labels = Object.keys(monthlyDP).sort();
        const data = labels.map( v => monthlyDP[v]);

        return {
            type : 'bar',
            data : {
                labels : labels,
                datasets: [{
                    backgroundColor: 'rgb(75,192,192)',
                    data: data
                }]    
            },
            options: {
                legend: {
                    display: false,
                },
            }
        };;
    }
}