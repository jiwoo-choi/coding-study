import { DataType, MonthlyDataType } from "../entity";
import DataSource from "./DataSource";
import DataSourceTx from "./DataSourceTx";

/**
 * 테스팅을 위한 간단한 stub. 간단하기 떄문에 interface도 없다.
 */
export default class DataSourceStub implements DataSourceTx {

    private data : DataType
    constructor(mockData: DataType) {
        this.data = mockData;
    }  
    loadData(): void {
    }
    saveData(newMonthlyData: MonthlyDataType): void {
    }
    getData(): DataType {
        return this.data;
    }
}