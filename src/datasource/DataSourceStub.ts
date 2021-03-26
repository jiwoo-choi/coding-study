import { DataType, MonthlyDataType } from "../entity";
import DataSource from "./DataSource";
import DataSourceTx from "./DataSourceTx";

/**
 * 테스팅을 위한 간단한 stub. 간단하기 떄문에 interface도 없다.
 */
export default class DataSourceStub implements DataSourceTx {

    get data() : DataType{
        return this.getData();
    }

    private mockData;
    constructor(mockData: DataType) {
        this.mockData = mockData;
    }  

    loadData(): void {
    }

    saveData(): void {
    }
    
    getData(): DataType {
        return this.mockData;
    }
}