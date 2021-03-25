import fs from 'fs'
import fetch from 'node-fetch'
interface Properties {
    participants : string[],
    tierRange: string,
    minSolvedLimit: number,
    numberOfQuestions:number,
}

interface BOJResult {
    success: boolean,
    result: {
      total_problems:number,
      total_page:number,
      problems: Problem[]
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

export default class BojQuery {
    private data : Properties;

    constructor() {
        const read = fs.readFileSync("./bojproperties.json");
        this.data = JSON.parse(read.toString()) as Properties;
    }

    getQuery() {
        return this.data.participants.map( value => `~solved_by:${value}`).join('+') 
        + "+tier:" + this.data.tierRange + "+"
        + "+solved:" + this.data.minSolvedLimit + ".." 
        + "&page=1";
        
    }

    
    async request(): Promise<string[]> {

        const mapper = (value : Problem) => `[${this.convert(value.level)} : ${value.id}번 ${value.title}](https://www.acmicpc.net/problem/${value.id})`;
        
        const response = await fetch(`https://api.solved.ac/v2/search/problems.json?query=${this.getQuery()}`)
        const json = await response.json() as BOJResult;
        if (json.result.total_problems == 0) {
            return Promise.reject("NO QUESTIONS FOUND");
        } else if (json.result.total_problems <= this.data.numberOfQuestions) {
            return json.result.problems.map(mapper)
        } else {
            const duplicateChecker = new Set<number>();
            const returnArray = [];
            const prevSize = 0;
            while(duplicateChecker.size < this.data.numberOfQuestions) {
                const index = Math.floor(Math.random() * json.result.problems.length-1);
                duplicateChecker.add(index);
                if (duplicateChecker.size != prevSize) {
                    returnArray.push(json.result.problems[index]);
                }
            }
            return returnArray.map(mapper);
        }
    } 

    convert(level : number) : string {
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
}