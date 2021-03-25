import { ChartQueryGenerator } from '../../util';
import { MonthlyRepository, ParticipationRepository } from '../../repository';
import ChartGeneratorService from './ChartGeneratorService'
export default class MarkdownTableGeneratorService implements ChartGeneratorService {
    
    private pRepository: ParticipationRepository;
    private mRepository : MonthlyRepository;

    constructor(pRepository:ParticipationRepository, mRepository: MonthlyRepository){
        this.pRepository = pRepository;
        this.mRepository = mRepository;
    }

    populateChart(): string {

        let chart: string = "";

        const chartQueryMaker = new ChartQueryGenerator();
        const allParticipant = this.pRepository.queryAllParticipants();
        const sumRateByMonthSinceBegining = chartQueryMaker.getSumRateByMonthSinceBegining(this.mRepository.queryAllMonthly());
        const sumRateByPplSinceBeginning = chartQueryMaker.getSumRateByPplSinceBeginning(allParticipant);

        const sumRateByMonthSinceBeginingStr = JSON.stringify(sumRateByMonthSinceBegining);
        const sumRateByPplSinceBeginningStr = JSON.stringify(sumRateByPplSinceBeginning);

        chart += "## 참여율 차트 (단위 : 건)"
        + "\n"
        + "|월간 참여횟수|인당 총 참여횟수|"
        + "\n"
        + "|:-:|:-:"
        + "\n"
        + `|![Image of sumRateByMonthSinceBegining](https://quickchart.io/chart?c=${sumRateByMonthSinceBeginingStr})`
        + `|![Image of sumRateByPplSinceBeginningStr](https://quickchart.io/chart?c=${sumRateByPplSinceBeginningStr})`
        + '\n'

        chart += "## 개인별 참여 차트 (단위 : %)"
        + '\n'

        const result = allParticipant.reduce( (prev, curr, index) => {
            if (index % 2 == 0) { 
                prev["header"].push([]) 
                prev["divider"].push([]) 
                prev["content"].push([]) 
            }
            const idx = Math.trunc(index/2);
            
            prev["header"][idx].push("|"+curr.id);
            prev["divider"][idx].push("|:-:")
            const monthly = JSON.stringify(chartQueryMaker.getMonthlyPariticipantRate(curr));
            prev["content"][idx].push(`|![Image of ${curr.id}](https://quickchart.io/chart?c=${monthly})`);
            return prev;
        }, {header:[], divider:[], content:[] } as any)
        
        const len = result["header"].length;

        for (let i = 0 ; i < len; i++) {
            chart += result["header"][i].join("");
            chart += '\n';
            chart += result["divider"][i].join("");
            chart += '\n';
            chart += result["content"][i].join("");
            chart += '\n\n'
        }


        return chart;
    }

}