export default interface TableGeneratorService {

    populateTable(checked?:string, unchecked?:string): string,
    // private getTable(yyyymm:string) : string,
    // private getHeader(yyyymm:string): string[],
    // getRows(yyyymm:string): {id : string, attendance:boolean[]}[],
}