import {ActionType} from "./actionType";

export default interface QueryType {
    actionType: ActionType ;
    name: String
    query: CreateType | ReadType
}

export interface CreateType {
    checked : boolean;
}

export interface ReadType {
    year? : string;
    month? : string;
    date? : string;
    day?: string;
    yyyymm?: string;
}