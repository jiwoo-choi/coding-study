import fs from 'fs'
import DateManager from './util/DateManager';
import $ from './util/utils'
import moment from 'moment-timezone';
import { Toolkit } from 'actions-toolkit';

class Main {

    constructor(){
        this.createIssue();
    }
    
    createIssue(){
        const title = `${$.datebuilder.year().month().day().delimiter('/').build()} 매일매일 알고리즘 참석확인`
        const cheering = ["오늘도 화이팅!", "오늘도 끝까지 해봐요!"]
        const body = 
            cheering[Math.floor(Math.random() * cheering.length)] +
            '\n'+ 
            `제출기한 : ${$.datebuilder.tyear().tmonth().tday().delimiter('-').build()} (다음날) 7AM 까지`

        Toolkit.run(
            async tools => {
              tools.log.info(`Creating new issue ${title}`);
    
              try {
                const issue = await tools.github.issues.create({
                  ...tools.context.repo,
                  title,
                  body,
                });
                tools.log.success(`Created issue ${issue.data.title}#${issue.data.number}: ${issue.data.html_url}`, );
            
            } catch (err) {
                tools.log.error(err);
                if (err.errors) tools.log.error(err.errors);
                tools.exit.failure();
            }
            },
            {
              secrets: ["GITHUB_TOKEN"],
            },
          );
    }
}
