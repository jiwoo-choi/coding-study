import transform from './transform';
import { MonthlyDataType, ParticipationDataType, DataType, PresetParticipantDataType } from 'entity';
import fs from 'fs'
import { MONTHLY_DB, PARTICIPANTS_PROPERTIES } from '../properties/path';
import DataSourceTx from './DataSourceTx';


export default class DataSource implements DataSourceTx { 
    
    private static instance?: DataSourceTx;
    private pariticipationData? : ParticipationDataType;
    private monthlyData? : MonthlyDataType;
    private presetParticipants?:PresetParticipantDataType[];
    private _data : DataType;

    public static getInstance(): DataSourceTx {
        if (DataSource.instance === undefined) {
            DataSource.instance = new DataSource();
        }
        return DataSource.instance;
    }

    private constructor() {
        this.loadData();
        this._data = {
            participationData: this.pariticipationData,
            monthlyData : this.monthlyData,
            presetParticipants : this.presetParticipants
        }
    }

    get data(): DataType {
        return this._data;
    }

    saveData(): void {

        if (!this.monthlyData) return;

        const participants = JSON.parse(fs.readFileSync(PARTICIPANTS_PROPERTIES).toString()) as string[]
        
        try {
            this.monthlyData.update = new Date().toISOString();
            this.pariticipationData = transform(this.monthlyData, participants)
            fs.writeFileSync(MONTHLY_DB, JSON.stringify(this.monthlyData));
        } catch {
            throw new Error('write failre: cannot write MONTHLY_DB')
        }
        // 현재 메모리에 저장된게 DB버전. 현재 이 싱글톤을 공유하면 같은 참조를 가져야한다.
        // this.loadData();
     }

    loadData() : void {

        let monthly_db : MonthlyDataType;
        let participants : PresetParticipantDataType[];

        try {
            monthly_db = JSON.parse(fs.readFileSync(MONTHLY_DB).toString()) as MonthlyDataType;
        } catch (e) {
            throw new Error('load failure : cannot read MONTHLY_DB'  + 'message : ' + e);
        }

        try {
            participants = JSON.parse(fs.readFileSync(PARTICIPANTS_PROPERTIES).toString()) as PresetParticipantDataType[]
        } catch (e) {
            throw new Error('load failure : cannot read PARTICIPANTS_PROPERTIES' + 'message : ' + e);
        }
        
        try {     
            this.monthlyData = monthly_db;
            this.presetParticipants = participants;
            this.pariticipationData = transform(this.monthlyData, participants.map( v => v.github));
        } catch (e) {
            throw new Error('load failure : cannot load to DatabaseSource' + 'message : ' + e);
        }
    }

    getData() : DataType {
        return this._data;
    }
}