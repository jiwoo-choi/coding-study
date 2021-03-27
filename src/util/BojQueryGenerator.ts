export interface ProblemProperties {
    /** 검색할 문제 range */
    tierRange: string,
    /** 최소 검색 제한 */
    minSolvedLimit: number,
    /** 뽑을 문제 개수들 */
    numberOfQuestions:number,
}

export interface BojQueryGenerator {

    /** id에 해당하는 정보를 가져오는 쿼리 생산 */
    getParticipantInfoQuery(id:string):string;
    /** ids를 제외한 나머지를 요청하는 쿼리 생산. */
    getRandomProblemQuery(ids:string[], properties: ProblemProperties):string;
}


const BASE_URL: string = "https://api.solved.ac/v2/search/";
const SOURCE_URL : string = "recommendations.json";
// const PROBLEM_SOURCE : string = "problems.json";

export default class SolvedACQueryGenerator implements BojQueryGenerator {

    getParticipantInfoQuery(id: string): string {
        return BASE_URL + SOURCE_URL + "?query=" + id;
    }

    getRandomProblemQuery(ids: string[], properties: ProblemProperties): string {
        return BASE_URL + SOURCE_URL + "?query=" + 
        ids.map( value => `~solved_by:${value}`).join('+') 
        + "+tier:" + properties.tierRange + "+"
        + "+solved:" + properties.minSolvedLimit + ".." 
        + "&page=1";
    }
}