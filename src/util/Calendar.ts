import { DateType } from "./DateManager";
import $ from "./utils";
import moment, { Moment } from "moment-timezone";

/** 
 * 각 월에 맞는 데이터를 반환하는 클래스 입니다.
 */
export default class Calendar {
    // 해당하는 날짜를 입력하고, 시작일을 결정하면..
    // 해당 날짜에 맞는 것을 다 알려줌. 
    // 1. 총 몇개의 날짜가 있는건지 
    // 2. 각 날짜에 해당하는 타이틀링 12/21 (포맷정하면 알아서 해줌.)
    static getDateList(YYYYMM:string, format:string="MM/DD", start:number=1, end?: number): string[] {
        moment.locale('ko')
        const momentInstance = moment(YYYYMM + start.toString(), "YYYYMMDD").locale('kr').tz("Asia/Seoul");
        end = (end) ? end : momentInstance.daysInMonth();
        let data = [];
        for (let i = start ; i <= end; i++) {
            data.push(momentInstance.format(format));
            momentInstance.add(1, "days");
        }
        return data;
    }

    // constructor(YYYYMM:string, start:number = 1, end?: number) {
    //     moment.locale('ko')
    //     this.momentInstance = moment(YYYYMM + start.toString(), "YYYYMMDD").locale('kr').tz("Asia/Seoul");
    //     this.start = start;
    //     this.end = (end) ? end : this.momentInstance.daysInMonth();
    // }

    // public getDateList(format:string= "MM/DD") : string[] {
    //     let data = [];
    //     for (let i = this.start ; i <= this.end; i++) {
    //         data.push(this.momentInstance.format(format));
    //         this.momentInstance.add(1, "days");
    //     }
    //     return data;
    // }


}