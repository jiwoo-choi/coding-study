import {MonthlyRepository} from "../../repository";
import { Calendar } from "../../util";
import {GithubAPISerivce} from "./GithubToolkitCRUDService";

export interface AttendantCheckService {
    checkAttendants():Promise<void>;
    updateNewMetaInfo():Promise<void>;
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

    updateNewMetaInfo(): Promise<void> {
        return new Promise((resolve, reject) => {
            this.githubAPIService.createIssue( (ok, issue_number) => {
                if (!ok) reject(new Error("cannot create issue using github API"));
                this.monthlyRepository.addNewMeta(this.dateManager, issue_number);
                resolve();
            })
        });
    }

    checkAttendants(): Promise<void> {
        return new Promise( (resolve, reject) => {
            const yyyymm = this.dateManager.getYesterDay().builder().yyyymm.build();
            const last_attendant = this.monthlyRepository.queryLatestAttedanceByYYYYMM(yyyymm);
            if (last_attendant === null) {
                reject(new Error("cannot found issue number from " + yyyymm));
                return;
            }
            this.githubAPIService.readIssue(last_attendant.issue_number, (ok, attendants) => {
                if (!ok) reject(new Error("cannot read issue from github API"));
                this.monthlyRepository.updateAttendants(yyyymm, last_attendant.day, attendants);
                resolve();
            })
        })
    }
    
}