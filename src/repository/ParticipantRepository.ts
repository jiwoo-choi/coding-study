import { ParticipationDataType } from "../entity";
import { Participant } from "../entity";

/**
 * 데이터를 조회만 할 수 있는 역할을 가지고 있음.
 */
export default interface ParticipationRepository {
    queryById(id : string): Participant | null;
    queryAllParticipants(): Participant[];
    queryByYYYYMM(yyyymm:string):Participant[];
    queryAvailableYYYYMMM():string[];
}