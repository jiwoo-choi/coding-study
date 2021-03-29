import { DataSource, DataSourceStub } from "../../datasource";
import { Calendar } from "../../util";
import JSONFileDataSourceRepository from "../JSONFileDataSourceRepository";
import MonthlyRepository from "../MonthlyRepository";
import { getMockDate ,getMockDatabase, MockDate2021, MockDatabase} from '../../testing_util'
import { DashboardManageService, MarkDownChartGeneratorService, MarkdownTableGeneratorService } from "../../service";
import { ParticipationRepository } from "../../repository";

/** 모든 월간 데이터 리스트 가져오기 */
/** 월간 데이터중 yyyymm에 해당하는 데이터를 가져온다 */
/** 기록된 마지막 참여정보를 가져옵니다 (CreateIssue할때 만들었던 issue number를 의미) */
/** 해당 날짜에 참석한 인원들 리스트를 DB에 업데이트 */
/** 받은 date를 기준으로 메타 데이터를 추가합니다. */


describe('monthlyRepository 기능테스트', () => {

    let monthlyRepository : MonthlyRepository;

    DataSource.getInstance = jest.fn( () => new DataSourceStub(getMockDatabase(MockDatabase.ONEFULLDATA)))
    Date.now = jest.fn( () => getMockDate(MockDate2021.APR_FIRST));
    
    /// ⚠️ 주의 : DataSource.getInstance()는 실제 Repository 객체에 들어가는 인스턴스와 다릅니다. 테스팅을 위해 사용합니다.
    const datsSource = DataSource.getInstance()
    datsSource.saveData();
    monthlyRepository = new JSONFileDataSourceRepository();

    test( 'Repository에서 활용하는 데이터는 데이터 소스에서 가져온 데이터와 일치해야한다.' , () => {
        const monthData = monthlyRepository.queryAllMonthly();
        const expected = getMockDatabase(MockDatabase.ONEFULLDATA).monthlyData!.data;
        expect(monthData).toStrictEqual(expected);
    });

    test( 'YYYYMM을 기준으로 마지막 참석 데이터를 정확하게 쿼리할 수 있어야 한다.' , () => {
        const monthQuery1 = monthlyRepository.queryLatestAttedanceByYYYYMM("202101");
        const query1Expected = getMockDatabase(MockDatabase.ONEFULLDATA).monthlyData!.data[0].attendance[0];
        expect(monthQuery1).toStrictEqual(query1Expected);
        const monthQuery2 = monthlyRepository.queryByYYYYMMInMontly("202102");
        expect(monthQuery2).toBeNull();
    });

    test( 'YYYYMM을 기준으로 가져온 데이터는 정확하게 YYYYMM을 찾을 수 있어야 한다.' , () => {
        const monthQuery1 = monthlyRepository.queryByYYYYMMInMontly("202101");
        const query1Expected = getMockDatabase(MockDatabase.ONEFULLDATA).monthlyData!.data[0];
        expect(monthQuery1).toStrictEqual(query1Expected);

        const monthQuery2 = monthlyRepository.queryByYYYYMMInMontly("202102");
        expect(monthQuery2).toBeNull();
    });

    test( '새로운 메타데이터를 추가했을 경우 정확하게 반영되어야한다.' , () => {
        const monthQuery1 = monthlyRepository.addNewMeta(new Calendar(), 1);
        const monthData = monthlyRepository.queryAllMonthly();
        const newData = [...getMockDatabase(MockDatabase.ONEFULLDATA).monthlyData!.data]
        newData.push({
            "yyyymm" : "202104",
            "year":"2021",
            "month":"04",
            "attendance" : [
                {
                    "checked" : [],
                    "day" : "01",
                    "issue_number" : 1
                }
            ]
        });

        expect(monthData).toStrictEqual(newData);

    });


    test( '새로운 참석 데이터를 업데이트 했을 경우 정확하게 업데이트 되어야한다.' , () => {
        const monthQuery1 = monthlyRepository.updateAttendants("202101", "23", ["test"])
        const monthData = monthlyRepository.queryAllMonthly();
        expect(monthData[0].attendance[0].checked).toStrictEqual(["test"]);

    });

    test( '새로운 참석 데이터를 업데이트 했을 경우 표도 업데이트되어야한다' , () => {
        const monthQuery1 = monthlyRepository.updateAttendants("202101", "27", ["jihyunhillpark"])
        const markdownchart = new MarkDownChartGeneratorService((monthlyRepository as unknown as ParticipationRepository), monthlyRepository);
        const markdowntable = new MarkdownTableGeneratorService((monthlyRepository as unknown as ParticipationRepository));
        const dashboard = new DashboardManageService(markdowntable, markdownchart, new Calendar());
        const monthData = monthlyRepository.queryAllMonthly();
        dashboard.updateDashboard("./README2.md")
        // expect().toStrictEqual(["test"]);
    });


})