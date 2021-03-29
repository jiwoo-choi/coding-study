import { DataType, MonthlyDataType } from "../entity";
import DataSource from "./DataSource";
import DataSourceTx from "./DataSourceTx";
import fs from 'fs'

/**
 * 테스팅을 위한 간단한 stub. 간단하기 떄문에 interface도 없다.
 */
export default class DataSourceStub implements DataSourceTx {

    get data() : DataType {
        return this.getData();
    }

    private mockData;

    constructor(mockData: DataType) {
        this.mockData = mockData;
    }  

    loadData(): void {
        // 테스트 데이터를 읽습니다.
        this.mockData = fs.readFileSync('./test.json') as DataType;
    }

    saveData(): void {
        // 테스트 데이터를 저장합니다.
        // console.log(this.mockData.monthlyData)
        fs.writeFileSync('./test.json', JSON.stringify(this.mockData.monthlyData));
    }
    
    getData(): DataType {
        return this.mockData;
    }
}