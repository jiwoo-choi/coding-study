interface ParticipationDataType { 
    update: string,
    data : Participant[],
}
  
export type Participant = {
    id : string,
    participation : Participation[]
}

export interface Participation {
    yyyymm:string,
    start:string,
    last:string,
    attendance:boolean[]
}
  

export default ParticipationDataType;