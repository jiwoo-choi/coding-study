import MonthlyDataType , { Attendance, Monthly} from './MonthlyDataType'
import ParticipationDataType, { Participation, Participant} from './ParticipationDataType'
import PresetParticipantDataType from './PresetParticipantDataType'

interface DataType {
    participationData? : ParticipationDataType,
    monthlyData?: MonthlyDataType
    presetParticipants?: PresetParticipantDataType[]
}

export {
    MonthlyDataType,
    Attendance,
    Monthly,
    ParticipationDataType,
    Participation,
    Participant,
    DataType,
    PresetParticipantDataType
}