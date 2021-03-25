import { MonthlyDataType, Participation, ParticipationDataType } from 'entity';
// monthly 데이터에서 participatns 데이터로 변경하는 함수
export default function transform( monthly_obj : MonthlyDataType , participants: string[]) : ParticipationDataType {

  // 외부 의존성을 없애야 한다.
  // const monthly_db = fs.readFileSync(MONTHLY_DB);
  // const monthly_obj = JSON.parse(monthly_db.toString()) as MonthlyDataType

  // const participants_pp = fs.readFileSync(PARTICIPANTS_PROPERTIES)
  // const participants = JSON.parse(participants_pp.toString()) as string[]
  
  const result : ParticipationDataType = {
    data : [] as {
      id : string,
      participation : []
    }[] ,
    update: new Date().toString()
  }
  
  for (const name of participants) {

    const participant = { id : name , participation: [] as Participation[]}

    for(const yyyymm_data of monthly_obj.data) {

      const participation : Participation = {} as Participation;
      participation.yyyymm = yyyymm_data.yyyymm;
      participation.attendance = [];
      for (const attendance of yyyymm_data.attendance) {
        participation.attendance.push(attendance.checked.includes(name))  
      }
      // 처음과 마지막 날짜를 기준으로 한다.
      // participants가 참여한?? 만약 중간에 빠지면? => Test할필요 없음 지원범위아님.
      participation.start = yyyymm_data.attendance[0].day;
      participation.last = yyyymm_data.attendance[yyyymm_data.attendance.length-1].day;
      participant.participation.push(participation);
    }
    result.data.push(participant);
  }
  return result;
}
  
  
  
