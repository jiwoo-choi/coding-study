import moment from "moment-timezone";
/**
 * 날짜 관리와 스트링 빌더의 역할을 하는 클래스입니다.
 */

export default class DateBuilder {
    
    static instance? : DateBuilder = undefined;

    static getInstance() : DateBuilder {
        if (DateBuilder.instance !== undefined) {
            return this.instance as DateBuilder;
        } else {
            return new DateBuilder();
        }
    }

    private _year : string;
    private _month: string;
    private _day: string;
    private _yyear : string;
    private _ymonth: string;
    private _yday: string;
    private _tday : string;
    private _tmonth : string;
    private _tyear: string;

    private result:string = "";
    private _delimiter: string = "";
    private internalStack : string[] = [];
    private _debug:boolean = false;

    private constructor() {
        const today = moment(moment.now()).tz("Asia/Seoul")
        const tomorrow = moment(moment.now()).tz("Asia/Seoul").add(1,"days")
        const yesterday = moment(moment.now()).tz("Asia/Seoul").subtract(1,"day");

        this._year = today.format("YYYY");
        this._month = today.format("MM");
        this._day = today.format("DD");
        this._yyear = yesterday.format("YYYY");
        this._ymonth = yesterday.format("MM");
        this._yday = yesterday.format("DD");
        this._tyear = tomorrow.format("YYYY");
        this._tmonth = tomorrow.format("MM");
        this._tday = tomorrow.format("DD");
    }

    public delimiter(delimiter: string):DateBuilder {
        this._delimiter = delimiter;
        return this;
    }

    public year():DateBuilder{
        this.internalStack.push(this._year);
        // if (this.result !== "") this.result = this.result + this._delimiter; 
        // this.result = this.result + this._year;
        return this;
    }

    public month():DateBuilder{
        this.internalStack.push(this._month);
        // if (this.result !== "") this.result = this.result + this._delimiter; 
        // this.result = this.result + this._month;
        return this;
    }
    
    public day():DateBuilder{
        this.internalStack.push(this._day);
        // if (this.result !== "") this.result = this.result + this._delimiter; 
        // this.result = this.result + this._day;
        return this;
    }
    
    public yyear():DateBuilder{
        this.internalStack.push(this._yyear);
        // if (this.result !== "") this.result = this.result + this._delimiter; 
        // this.result = this.result + this._yyear;
        return this;
    }
    
    public ymonth():DateBuilder{
        this.internalStack.push(this._ymonth);
        // if (this.result !== "") this.result = this.result + this._delimiter; 
        // this.result = this.result + this._ymonth;
        return this;
    }

    public yday():DateBuilder{
        this.internalStack.push(this._yday);
        // if (this.result !== "") this.result = this.result + this._delimiter; 
        // this.result = this.result + this._yday;
        return this;
    }

    public tday(): DateBuilder {
        this.internalStack.push(this._tday);
        return this;
    }


    public tmonth(): DateBuilder {
        this.internalStack.push(this._tmonth);
        return this;
    }
    

    public tyear(): DateBuilder {
        this.internalStack.push(this._tyear);
        return this;
    }
    

    clear(){
        this.internalStack = [];
        this.result = "";
        this._debug = false;
        this._delimiter = "";
    }

    build(){
        let result = this.result;

        while(this.internalStack.length > 0) {
            result = this.internalStack.pop() + this._delimiter + result;
        }

        if(this._delimiter !== "") {
            result = result.slice(0,-1);
        }

        if (this._debug) {
            console.log("DEBUG: " + result);
        }

        this.clear();

        return result;
    }

    debug(){
        this._debug = true;
        return this;
    }

    isYestderDayFirstDay():boolean{
        return this._yday == "01";
    }
}
