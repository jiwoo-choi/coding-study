import { ActionType } from './ActionType'
import MonthlyDataType , { Attendance, Monthly} from './MonthlyDataType'
import QueryType , {CreateType, ReadType} from './QueryType'
import ParticipationDataType, { Participation, Participant} from './ParticipationDataType'

interface DataType {
    participationData? : ParticipationDataType,
    monthlyData?: MonthlyDataType
}

export {
    ActionType,
    MonthlyDataType,
    QueryType,
    CreateType,
    ReadType,
    Attendance,
    Monthly,
    ParticipationDataType,
    Participation,
    Participant,
    DataType,

}