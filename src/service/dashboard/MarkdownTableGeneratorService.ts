import {ParticipationRepository} from "../../repository";
import { Calendar } from "../../util";
import TableGeneratorService from "./TableGeneratorService";

export default class MarkdownTableGeneratorService implements TableGeneratorService {

    private repository : ParticipationRepository;
    
    constructor(repository :ParticipationRepository) {
        this.repository = repository;
    }

    private getHeader(yyyymm:string): string[] {
        const calendar = new Calendar(yyyymm);
        return ["참여자"].concat(calendar.getTotalDaysInMonths(({month,day}) => {
            return month + "/" + day;
        }));
    }

    private getDivider(length:number) : string[] {
        return new Array(length).fill("|--");;
    }

    private getRows(yyyymm:string, length : number, checkedMark : string, uncheckedMark:string) {
        
        let rows = "";
        const filledDays = new Array(length - 1).fill(false);
        const participants = this.repository.queryByYYYYMM(yyyymm);
        
        participants.forEach( participant => {
            const start = Math.max(parseInt(participant.participation[0].start) - 1, 0);
            const end = Math.min(parseInt(participant.participation[0].last), filledDays.length);
            const first = filledDays.slice(0, start)
            const second = participant.participation[0].attendance;
            const third = filledDays.slice(end , filledDays.length);
            const finalRow = first.concat(second.concat(third));
            rows += [participant.id].concat(finalRow.map( (v) => v ? checkedMark : uncheckedMark)).join("|") + '\n';
        })

        return rows;
    }

    populateTable(checked?:string, unchecked?: string): string {

        const checkedMark = checked ? checked : "O"
        const uncheckedMark = unchecked ? unchecked : "X"

        let table : string = "";
        const allYYYYMM = this.repository.queryAvailableYYYYMMM();

        allYYYYMM.forEach(  yyyymm => {
            const header = this.getHeader(yyyymm);
            const divider = this.getDivider(header.length);
            const headerArea = [header.join("|"),divider.join("")].join('\n');
            table += headerArea;
            table += '\n';
            table += this.getRows(yyyymm, header.length, checkedMark,uncheckedMark!);
            table += '\n';
        })
        return table
    }

}