import { DataType, MonthlyDataType } from "../entity";

export default interface DataSourceTx {
    loadData(): void;
    saveData(newMonthlyData: MonthlyDataType): void;
    getData(): DataType;
}