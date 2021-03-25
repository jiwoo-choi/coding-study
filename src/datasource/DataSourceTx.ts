import { DataType } from "entity";

export default interface DataSourceTx {
    loadData(): void;
    saveData(): void;
    getData(): DataType;
}