import { Toolkit } from "actions-toolkit";
import { Calendar } from "../../util";

export interface GithubAPISerivce {
    createIssue(callback: (ok : boolean, issue_number: number, dataManager: Calendar) => void) : void
    readIssue(issue_number : number, callback: (ok : boolean, attendants: string[]) => void) : void;
}

export default class GitHubToolkitCRUDService implements GithubAPISerivce {

    // TO-DO : Either이나 Result같은 모델을 대입해볼 여지가 잇음.
    private dateManager : Calendar;
    
    constructor(dateManager : Calendar) {
        this.dateManager = dateManager;
    }
    
    createIssue(callback: (ok : boolean, issue_number:number, dataManager: Calendar) => void) {

     

        Toolkit.run( async tools => {
            const cheeringComments = ["오늘도 화이팅!", "오늘도 끝까지 해봐요!", "오늘도 풀어봅시다."]
            //dateManager
            
            const title = `${this.dateManager.builder('/').year.month.date.build()} 매일매일 알고리즘 참석확인`
            const body = cheeringComments[Math.floor(Math.random() * cheeringComments.length)]
            + '\n'
            + `제출기한 : ${this.dateManager.getTomorrow().builder('/').year.month.date.build()} (다음날) 12:30PM 까지`

            tools.log.info(`Creating new issue ${title}`);

            try {
                const issue = await tools.github.issues.create({
                    ...tools.context.repo,
                    title,
                    body,
                });
                tools.log.success(`Created issue ${issue.data.title}#${issue.data.number}: ${issue.data.html_url}`,);
                callback(true, issue.data.number, this.dateManager);
            } catch (e) {
                tools.log.error(e);
                callback(false, 0, this.dateManager);
                tools.exit.failure();
            }
        },  
            {
                secrets: ["GITHUB_TOKEN"],
            },
        )
    }


    readIssue(issue_number: number, callback: (ok : boolean, attendants:string[]) => void) {

   


        Toolkit.run ( async tools => {
            tools.log.info(`Accessing ${issue_number}`);
            try {
                const issues = await tools.github.issues.listComments({
                    ...tools.context.repo,
                    issue_number: issue_number,
                })
                const attendants = issues.data.map(value => value.user.login);
                callback(false, attendants);
            } catch (e) {
                tools.log.error(e);
                tools.exit.failure();
                callback(false, []);
            }
        },  
            {
                secrets: ["GITHUB_TOKEN"],
            },
        )
    }
}