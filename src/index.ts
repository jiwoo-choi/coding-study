// import fs from 'fs'
// import $ from './util/utils'
// import { Toolkit } from 'actions-toolkit';
// import Calendar from './util/Calendar';
// import { DateType } from './util/DateManager';
// import BojQuery from './util/BojQueryGenerator'


// class Main {

//     constructor(){
//       Toolkit.run( async tools => {
//         await this.updateData(tools);
//         this.populateTable();
//         await this.createIssue(tools); 
//       },
//         {
//           secrets: ["GITHUB_TOKEN"],
//         },
//     )
//   } 


  
//     async updateData(tools: Toolkit){

//       const read = fs.readFileSync("./data.json");
//       const parsedData = JSON.parse(read.toString()) as ParticipateData;
      
//       const yyyymm = $.datebuilder.yyear().ymonth().build();

//       for (let i = 0 ; i < parsedData.data.length; i++) {
//         if (parsedData.data[i].yyyymm == yyyymm) {
//             const dailyData = parsedData.data[i].attendance[parsedData.data[i].attendance.length-1]
//             const issue_number = dailyData.issue_number;
//             const issues = await tools.github.issues.listComments({
//               ...tools.context.repo,
//               issue_number: issue_number,
//             })
//             dailyData.checked = issues.data.map(value => value.user.login)
//             parsedData.update = $.date.getMoment(DateType.TODAY).format("YY-MM-DD-hh:mm");
//             fs.writeFileSync("./data.json", JSON.stringify(parsedData));
//         }
//       }

//     }

//     populateTable() {

//       const read = fs.readFileSync("./data.json");
//       const participatns = fs.readFileSync("./participants.json");

//       const parsedData = JSON.parse(read.toString()) as ParticipateData;
//       const parsedList = JSON.parse(participatns.toString()) as string[];

//       let readme = "" ;
//       /**
//        * 한가지 중요한 가정.
//        * 1. 정렬되어있다.
//        * 2. 모든 날짜에 빠찜없이 기록하고 있다. (특정케이스를 제외하고)
//        */
      
//        // 1. yearmonth를 본다
//        // 2. attendance를 보고 시작점을 찾는다.
//        // 3. caandear를 불러온다.
//        // 4. participants마다 객체를 만들어준다.

//        // 순회 1
//        // 그럼 캘린더를 기준으로 순회를 할것입니다.(X)
//        // 시작점 + attendance기준까지 순회를 할것입니다.
//        // participatns의 키값을 기준으로 checked에 동일 키값이 존재하는지 체크합니다.
//        // 만약 있다면, 그 날자의 인덱스에 해당하는것에다가 체크표시를 합니다.
//        // 

//       const reversed = parsedData.data.reverse();
//       for (let i = 0 ; i < reversed.length ; i++) {
//       // 1. YYMM 단위로 데이터를 전부 돌것.
//       // 2. 데이터를 통해서 무엇을 가져올것?

//         const data = reversed[i];
//         let start = data.attendance[0].day; // 현재 start 날짜 정보가 들어있습니다.
//         let dates = Calendar.getDateList(data.yyyymm, "MM/DD", parseInt(start));
//         let tableHeader = ["|참여자"].concat(dates).concat(["참석률|"])
//         const divider = ["|--"].concat(Array(dates.length).fill("--")).concat(["--|"]);
//         const participants = parsedList.reduce( (prev, curr) => {
//           prev[curr] = {
//             data : [`|${curr}`].concat(Array(dates.length).fill("")).concat(["|"]),
//             attendPoint : 0,
//           }
//           return prev;
//         }, {} as {
//           [key:string] : {
//           data : string[],
//           attendPoint:number;
//           }
//         } );

//         // 날짜별로 순회한다
//         // 오늘까지 해야한다.
//         for (let j = 0 ; j < data.attendance.length ; j++) {
//           // attendance배열이 - 실제 캘린더의 날자와 짝이 맞는다고 가정해야한다.
//           data.attendance[j].checked.forEach( value => {
//             if (value in participants) {
//               participants[value].attendPoint++;
//               participants[value].data[j+1] = "💰";
//             } 
//           })
//         }
//         const list = Object.keys(participants).reduce( (prev, curr) => {
//           let obj = participants[curr];
//           let rate = Math.round(obj.attendPoint / dates.length * 100);
//           console.log(rate);
//           obj.data[obj.data.length-1] = `${rate}%`
//           return prev + obj.data.join('|') + '\n' 
//         }, "");
        
//         readme += `## ${data.year}년 ${data.month}월 참석률 대시보드` + '\n'
//         readme += tableHeader.join('|') + '\n' + divider.join('|') + '\n' + list;
//     }
//     readme += '\n'+ `마지막 업데이트 : ${$.date.getMoment(DateType.TODAY).format("YY-MM-DD-hh:mm")}`;
//     fs.writeFileSync("./README.md", readme);

//   }

//   async createIssue(tools:Toolkit){
        
//     const title = `${$.datebuilder.year().month().day().delimiter('/').build()} 매일매일 알고리즘 참석확인`
//     const cheering = ["오늘도 화이팅!", "오늘도 끝까지 해봐요!", "오늘도 풀어봅시다."]

//     // const boj = new BojQuery();
//     // const response = await boj.request();
//     // const plus = response.join('\n');

//     const body = 
//     // plus
//     // + '\n'
//      
//     + '\n' 
//     + `제출기한 : ${$.datebuilder.tyear().tmonth().tday().delimiter('-').build()} (다음날) 12:30PM 까지`

//     tools.log.info(`Creating new issue ${title}`);
//     try {
//       const issue = await tools.github.issues.create({
//         ...tools.context.repo,
//         title,
//         body,
//       });

//       // const issue = {
//       //     data : {
//       //       number : 100,
//       //       title : "test",
//       //       html_url: "test_2",
//       //     }
//       // }
//     const read = fs.readFileSync("./data.json");
//     const parsedData = JSON.parse(read.toString()) as ParticipateData;
//     const yyyymm = $.datebuilder.year().month().build();
//     parsedData.update = $.date.getMoment(DateType.TODAY).format("YY-MM-DD-hh:mm"); // 깃허브 오류 방지용.
    

//     if ($.date.isTodayFirstDay()) {
//         // 새로운 이슈를 만든다.
//         parsedData.data.push({
//           year: $.datebuilder.year().build(),
//           month : $.datebuilder.month().build(),
//           yyyymm: $.datebuilder.year().month().build(),
//           attendance: [{
//               day: $.datebuilder.day().build(),
//               date : $.datebuilder.year().month().day().build(),
//               checked:[],
//               issue_number : issue.data.number
//           }]
//         })
//     } else {
//       // 오늘 날짜 찾아서 append만 한다.
//         for (let i = 0 ; i < parsedData.data.length ; i++) {
//           if (parsedData.data[i].yyyymm === yyyymm) {
//             parsedData.data[i].attendance.push({
//                 day: $.datebuilder.day().build(),
//                 date : $.datebuilder.year().month().day().build(),
//                 checked:[],
//                 issue_number : issue.data.number
//             });
//           }  else {
//             // TODO : 없을경우 새로 추가.
//           }
//         }
//       }
//     fs.writeFileSync("./data.json", JSON.stringify(parsedData));
//     tools.log.success(`Created issue ${issue.data.title}#${issue.data.number}: ${issue.data.html_url}`,);
//   } catch (err) {
//     tools.log.error(err);
//     if (err.errors) tools.log.error(err.errors);
//     tools.exit.failure();
//   }
//   }

// }

// new Main();

import fs from 'fs'
import moment from 'moment-timezone'
import { Calendar } from './util';
import transform from './datasource/transform'

import { JSONFileDataSourceRepository, MonthlyRepository, ParticipationRepository } from './repository';
import { MarkdownTableGeneratorService, TableGeneratorService, GithubIssueCheckService , GithubToolkitCRUDService, AttendantCheckService, GithubAPISerivce} from './service';
import { DashboardManageService, MarkDownChartGeneratorService, ChartGeneratorService } from './service';

/** container */
const jsonRepository = new JSONFileDataSourceRepository();
const dateManager = new Calendar();
const participantRepository : ParticipationRepository = jsonRepository as ParticipationRepository; 
const monthlyRepository : MonthlyRepository = jsonRepository as MonthlyRepository;
const githubIssueAPIService : GithubAPISerivce = new GithubToolkitCRUDService(dateManager);
const githubIssueCheckService : AttendantCheckService = new GithubIssueCheckService(githubIssueAPIService, dateManager, monthlyRepository);
const markDownTableGeneratorService : TableGeneratorService = new MarkdownTableGeneratorService(participantRepository);
const markDownChartGeneratorService : ChartGeneratorService = new MarkDownChartGeneratorService(participantRepository, monthlyRepository);
const dashboardManager : DashboardManageService = new DashboardManageService(markDownTableGeneratorService, markDownChartGeneratorService, dateManager);



try {
    githubIssueCheckService.checkAttendants();
    // dashboardManager.updateDashboard();
    // githubIssueCheckService.updateNewMetaInfo();    
} catch (e) {
    console.log(e);
}
