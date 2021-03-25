import {MonthlyRepository} from "../../repository";
import { Calendar } from "../../util";
import {GithubAPISerivce} from "./GithubToolkitCRUDService";

export interface AttendantCheckService {
    checkAttendants():void;
    updateNewMetaInfo():void;
} 

export default class GithubIssueCheckService implements AttendantCheckService {

    private githubAPIService : GithubAPISerivce;
    private dateManager : Calendar;
    private monthlyRepository: MonthlyRepository;

    constructor(githubAPIService : GithubAPISerivce, dateManager: Calendar, repository : MonthlyRepository) { 
        this.githubAPIService = githubAPIService;
        this.dateManager = dateManager;
        this.monthlyRepository = repository;
    }

    updateNewMetaInfo(): void {
        const tomorrow = this.dateManager.getTomorrow();
        this.githubAPIService.createIssue( (ok, issue_number, dateManager) => {
            if (!ok) throw new Error("cannot create issue using github API");
            this.monthlyRepository.addNewMeta(tomorrow, issue_number);
        })
    }

    checkAttendants(): void {
        const yyyymm = this.dateManager.builder().yyyymm.build();
        const last_attendant = this.monthlyRepository.queryLatestAttedanceByYYYYMM(yyyymm);
        if (last_attendant === null) throw new Error("cannot found issue number from " + yyyymm);
        this.githubAPIService.readIssue(last_attendant.issue_number, (ok, attendants) => {
            if (!ok) throw new Error("cannot read issue from github API");
            this.monthlyRepository.updateAttendants(yyyymm, last_attendant.day, attendants);
        })
    }
    
}