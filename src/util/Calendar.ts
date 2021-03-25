import moment, { Moment, MomentInput } from "moment-timezone";

export default class Calendar {

    private now : Moment;
    private stringBuilder : string = "";
    private dateState! : {
        year: string,
        month : string,
        date: string,
        day : string,
        hour : string,
        minutes : string,
        seconds : string
    };

    private _delimiter?: string;

    constructor(date? : MomentInput) {
        this.now = date ? moment(date) : moment(moment.now());
        moment.locale('ko');
        this.updateDateState();
    }

    builder(delimiter: string = "") {
        this.stringBuilder = "";
        this._delimiter = delimiter;
        return this;
    }

    get date() {
        this.appendString(this.dateState.date);
        return this;
    }

    get month() {
        this.appendString(this.dateState.month);
        return this;
    }

    get day() {
        this.appendString(this.dateState.day);
        return this;
    }

    get year() {
        this.appendString(this.dateState.year);
        return this;
    }

    get hour() {
        this.appendString(this.dateState.hour);
        return this;
    }

    get yyyymm() {
        this.appendString(this.dateState.year + this.dateState.month);
        return this;
    }

    get minutes() {
        this.appendString(this.dateState.minutes)
        return this;
    }

    get seconds() {
        this.appendString(this.dateState.seconds);
        return this;
    }

    private appendString(str : string){
        this.stringBuilder += str + this._delimiter;
    }

    delimiter(newDelimiter: string) {
        this._delimiter = newDelimiter;
        return this;
    }

    isFirstDayInMonth() : boolean {
        return this.now.date() == 1;
    }   

    isLastDayInMonth() : boolean {
        return this.now.daysInMonth() == this.now.date(); 
    }

    updateDate() {
        this.now = moment(moment.now());
        this.updateDateState();
    }

    updateDateState() {
        let formated = this.now.format("YYYY-MM-DD-dddd-hh-mm-ss").split("-");
        this.dateState = {
            year : formated[0],
            month : formated[1],
            date : formated[2],
            day : formated[3],
            hour : formated[4],
            minutes : formated[5],
            seconds: formated[6],
        }
    }

    getYesterDay() : Calendar {
        return new Calendar(this.now.subtract(1, "day"));
    }

    getTomorrow() : Calendar {
        return new Calendar(this.now.add(1, "day"));
    }

    getTotalDaysInMonths (mapper: ({year, month,day} : {year: string, month: string, day: string}) => any ) : any[] {
        return new Array(this.now.daysInMonth()).fill(0).map( (value, index) => {
            return { year : this.dateState.year, month : this.dateState.month, day : ("0"+(index+1)).slice(-2)};
        }).map(mapper);
    }

    build() {
        if (this._delimiter && this._delimiter.length > 0) {
            return this.stringBuilder.substr(0, this.stringBuilder.length-this._delimiter.length);
        } else {
            return this.stringBuilder;
        }
    }

}

