import { Calendar } from "../util";
import { Attendance, Monthly } from "../entity";

/**
 * 실질적인 데이터를 조회, 데이터 수정 까지 하는 역할을 지니고 있음.
 */
export default interface MontlyRepository {


    /** 모든 월간 데이터 리스트 가져오기 */
    queryAllMonthly():Monthly[];

    /** 월간 데이터중 yyyymm에 해당하는 데이터를 가져온다 */
    queryByYYYYMMInMontly(yyyymm:string):Monthly | null

    /** 기록된 마지막 참여정보를 가져옵니다 (CreateIssue할때 만들었던 issue number를 의미) */
    queryLatestAttedanceByYYYYMM(yyyymm:string): Attendance | null;

    /** 해당 날짜에 참석한 인원들 리스트를 DB에 업데이트 */
    updateAttendants(yyyymm: string, day : number | string , attendants : string[]): void;

    /** 받은 date를 기준으로 메타 데이터를 추가합니다. */
    addNewMeta(date:Calendar, issue_number: number ):void;
}

