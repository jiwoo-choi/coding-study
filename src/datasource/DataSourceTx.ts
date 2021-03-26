import { DataType, MonthlyDataType } from "../entity";

export default interface DataSourceTx {
    loadData(): void;
    saveData(): void;
    getData(): DataType;
    readonly data: DataType;
}