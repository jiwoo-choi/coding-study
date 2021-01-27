import moment, { Moment } from "moment-timezone";


/**
 * 유일한 날짜를 관리하는 싱글톤 객체입니다.
 */
export default class DateManager {

    private static instance? : DateManager = undefined;

    static getInstance() : DateManager {
        if (DateManager.instance == undefined) {
            DateManager.instance = new DateManager();
        }
        return DateManager.instance as DateManager;
    }
    

    private now!: number;

    private construtor() {
        this.now = moment.now();
    }

    getMoment(dateType : DateType) : Moment {
        switch(dateType) {
            case DateType.TODAY:
                return moment(this.now).tz("Asia/Seoul")
            case DateType.TOMORROW:
                return moment(this.now).tz("Asia/Seoul").add(1, "day");
            case DateType.YESTERDAY:
                return moment(this.now).tz("Asia/Seoul").subtract(1, "day");
        }
    }
    

    isTodayFirstDay():boolean{
        return this.getMoment(DateType.TODAY).day() == 1
    }


}

export enum DateType {
    YESTERDAY, TODAY, TOMORROW
}