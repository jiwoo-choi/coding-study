//readme를 업데이트할것.
import fs from 'fs'
import { Toolkit } from "actions-toolkit";
import github from '@actions/github'
interface ParticipateData {

    //연도월로 키값을 정함.
    // [] 단순 배열구조에서, 배열에 좀 독립적인 key구조로 변경.
    [yyyymm:string]:{
        year: string,
        month:string,
        data : {
            date : string,
            day : string,
            checked: string[]
        },
    }
}

class ReadmeManager {

//$ npm install @actions/github

    participants:String[]|null;
    participantsData:ParticipateData|null;

    constructor(){

        this.participants = this.readParticipatns();
        this.participantsData = this.readData();

        let d : Date = new Date();
        let a = d.getFullYear() + d.getMonth();

        console.log(a);
        // // 읽긴 했는데, 
        // if (this.participantsData && this.participantsData) {
        //     // 가장 최근의 이슈를 가져옵니다.
        //     Toolkit.run( async (tools) => {

        //         let allIssues = await tools.github.issues.listForRepo({
        //             owner:"jiwoo-choi",
        //             repo:"coding-test-study",
        //             sort:"created",
        //             direction:"desc",
        //         })

        //         if (allIssues.data.length > 0) {
        //             let comments = await tools.github.issues.listComments({
        //                 issue_number:allIssues.data[0].number,
        //                 owner:"jiwoo-choi",
        //                 repo:"coding-test-study",
        //             })
        //             // 오늘이 만약 1일이라면, JSON대대적 수정.
        //             // 오늘이 만약 1일이 아니라면, 그냥 montly데이터에 추가.
        //             // 오늘 날자.
        //             // 오늘 참여한 사람. comments.data.map( data => data.user.login) 
        //             // 마지막 json에 추가하기.]

        //             // 덧글 남긴사람들 중에, 참여자만 걸러내기.
        //             let todayParticipatns = comments.data.map( data => data.user.login)
        //             .filter( (value,index,array) => array.indexOf(value) === index)
        //             .filter( (value) => this.participants?.includes(value));

        //             // 빈배열체크. => 나중에 해도됨.
        //             if (this.participantsData?.monthlyData) {
        //             }

        //             // 적어도 한개는 있을경우.
        //             // 오늘이 2일인경우. => 새롭게 만들어줘야합니다.

        //             // if (this.participantsData!.monthlyData.length > 0) {
        //             //     this.participantsData!.monthlyData[this.participantsData!.monthlyData.length].dailyData.push({});
        //             // } else {
        //                 //오늘이 2일이 아닌경우 -> 가장 마지막의 monthly에다가 추가해줘야한다.
        //                 //혹은 태그를 달까? [String: value]
        //                 //202010 => 이런식으로? 정리하는게 낫겠지?
        //                 //202010 : { 
                            
                        
        //             // }
        //             //오늘이 2일이면 => montly에 새롭게 만들어줘야함
        //             // 그게 아니라면, 

                    

        //             // Json업데이트하기.

                    
        //         // 테이블을 기준으로 이번주 데이터를 수집합니다.
        //         // 1. 오늘날짜.
        //         // 2. 오늘 참여한사람.
        //         // 3. 오늘 
        //         // 오늘 데이터를 수집했습니다.
        //         }
        //     }, {
        //         secrets: ["GITHUB_TOKEN"],
        //     });
        // } else {
        //     Toolkit.run( tools => {
        //         tools.exit.failure("json파일을 받는데 오류가 있었습니다.");
        //     })
        // }

        // const read = fs.readFileSync("./participants.json"); // 기존 json을 읽어온다.
        // const json : Data = JSON.parse(read.toString());
        // console.log(json);
        // Toolkit.run( async (tools) => {

            
        //     // let octokit = github.getOctokit(tools.token);
        //     // let list = await octokit.issues.listForRepo( {
        //     //     owner:"jiwoo-choi",
        //     //     repo:"coding-test-study"
        //     // })
        //     //읽어온다.
        //     //가장 최근꺼만 읽는다. 
        //     //json업데이트 인정 안한다.

        //     let list = await tools.github.issues.listForRepo({
        //         owner:"jiwoo-choi",
        //         repo:"coding-test-study",
        //         sort:"created",
        //         direction:"desc",
        //     })

        //     if (list.data.length > 0) {
            
        //         // tools.github.issues.listComments()
        //         let comments = await tools.github.issues.listComments({
        //             issue_number:2,
        //             owner:"jiwoo-choi",
        //             repo:"coding-test-study",
        //         })
        //         console.log(comments.data[0].body);
        //         console.log(comments.data[0].id);
        //         console.log(comments.data[0].user.login);


        //         // tools.log.success(comments.data[0].user.id);

                
        //     } else {
        //         tools.log.error("log");
        //     }
            
            // // github id..
            // let comments = await tools.github.issues.listComments({
            //     issue_number: list.data[0].id,
            //     owner:"jiwoo-choi",
            //     repo:"coding-test-study",
            // })

            // 이슈는 딱 한개만 봅니다.
            // since yesterday?
            // 가장 위에있는 이슈를 찾습니다.
            // 간단한 validate를 합니다.
            
            // 가장 처음에 있는 레포를 가져온다.
            // 
            // list.data[0].
            // tools.log.success(list);


        //     try {
        //         // const issue = await tools.github.issues.create({
        //         //   ...tools.context.repo,
        //         //   title,
        //         //   body,
        //         // });
        // octokt


        //         let response = await tools.github.issues.
        //         tools.log.success(response);
        //         // tools.log.success(
        //         //   `Created issue ${issue.data.title}#${issue.data.number}: ${issue.data.html_url}`,
        //         // );
        //       } catch (err) {
        //         // Log the error message
        //         tools.log.error(err);
        
        //         // The error might have more details
        //         if (err.errors) tools.log.error(err.errors);
        
        //         // Exit with a failing status
        //         tools.exit.failure();
        //       }
        // },
        // {
        //     secrets: ["GITHUB_TOKEN"],
        // },
        // )
    }

    /**
     * 참여자 목록부터 수집합니다. 
     * 만약, 참여자가 없다면 null을 반환하고, 참여자가 있다면 그 리스트를 반환합니다.
     */
    readParticipatns():String[] | null {
        try {
            const participants = fs.readFileSync("./participants.json"); // 기존 json을 읽어온다.
            const parsed : String[] = JSON.parse(participants.toString());   
            return parsed.length > 0 ? parsed : null; 
        } catch (err) {
            console.log(err);
            return null;
        }
    }

    readData():ParticipateData|null {
        try {
            const read = fs.readFileSync("./data.json"); // 기존 json을 읽어온다.
            const json : ParticipateData = JSON.parse(read.toString());
            return json;
        } catch(err) {
            console.log(err);
            return null;
        }
    }
    

    populateTable():String{
        // 오늘 내용을 업데이트한다.
        // issue를 읽는다...
        // const write = fs.writeFileSync("./data.json", JSON.stringify(json));

        return "";
    }

}
//여기서파일읽는법.
// 월간 체크하기.
// 달별로 체크하기.
// Object로 관리하자.
// JSON

new ReadmeManager();
