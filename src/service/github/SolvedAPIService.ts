import fetch from 'node-fetch'
import { SolvedACQueryGenerator } from '../../util';


 
//https://api.solved.ac/v2/search/recommendations.json?query=hoonti06
// constructor() {
    // const read = fs.readFileSync("./bojproperties.json");
    // this.data = JSON.parse(read.toString()) as ProblemProperties;
// }

//{"success":true,"result":{"autocomplete":[],"problems":[],"problem_count":0,"users":[{"user_id":"hoonti06","bio":"","profile_image_url":null,"solved":403,"exp":8501769,"level":16,"class":4,"class_decoration":0,"vote_count":0,"rank":1}],"user_count":1,"algorithms":[],"algorithm_count":0,"wiki_articles":[{"title":"hoonti06","caption":"hoonti06","description":"\uae00 \uc0c8\ub85c \uc791\uc131\ud558\uae30","href":"\/wiki\/edit\/hoonti06"}]}}

export interface SolvedACAPIResult {
    success: boolean,
    result: {
      total_problems:number,
      total_page:number,
      problems: Problem[]
      users: {
        bio: string,
        class: number,
        class_decoration: number,
        exp: number,
        level: number,
        profile_image_url: string | null,
        rank: number,
        solved: number,
        user_id: string,
        vote_count: number,
      }
    }   
}

interface Problem {
    id : string,
    level: number,
    level_locked: number,
    solvable: number,
    title: string,
    solved_count :number,
    average_try:number,

}


export default class SolvedAPIService {

    private bojQueryGenerator : SolvedACQueryGenerator;

    constructor() {
        this.bojQueryGenerator = new SolvedACQueryGenerator();
        //repository를 받아와서 올리기..
    }
    /**
     * int형 레벨을 string 레벨로 변경해주는 함수.
     * @param level number 타입 레벨
     * @returns 백준 표기 레벨
     */
    private convert(level : number) : string {
        if (level < 1 || level > 30) return "Undefined";
        const table = [
            "Bronze",
            "Silver",
            "Gold",
            "Platinum",
            "Diamond",
            "Ruby",
        ]
        return table[Math.floor((level-1)/5)] + " " + (5 - ((level-1) % 5));
    }


    async getProfiles() {
        this.bojQueryGenerator.getParticipantInfoQuery('');
    }



    async request(): Promise<string[]> {

        return new Promise( (resolve, reject) => {
            resolve([]);
        })
    }
}

    //     const mapper = (value : Problem) => `[${this.convert(value.level)} : ${value.id}번 ${value.title}](https://www.acmicpc.net/problem/${value.id})`;
        
    //     const response = await fetch(`https://api.solved.ac/v2/search/problems.json?query=${this.getQuery()}`)
    //     const json = await response.json() as BOJResult;
    //     if (json.result.total_problems == 0) {
    //         return Promise.reject("NO QUESTIONS FOUND");
    //     } else if (json.result.total_problems <= this.data.numberOfQuestions) {
    //         return json.result.problems.map(mapper)
    //     } else {
    //         const duplicateChecker = new Set<number>();
    //         const returnArray = [];
    //         const prevSize = 0;
    //         while(duplicateChecker.size < this.data.numberOfQuestions) {
    //             const index = Math.floor(Math.random() * json.result.problems.length-1);
    //             duplicateChecker.add(index);
    //             if (duplicateChecker.size != prevSize) {
    //                 returnArray.push(json.result.problems[index]);
    //             }
    //         }
    //         return returnArray.map(mapper);
    //     }
    // } 

