export default interface MonthlyDataType {
    update: String,
    data : Monthly[]
  }


export interface Monthly {
    year:string, 
    month:string, 
    yyyymm: string,
    attendance : Attendance[]
}

export interface Attendance {
    day: string,
    checked:string[],
    issue_number:number
}