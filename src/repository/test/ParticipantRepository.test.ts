import { getMockDatabase, getMockDate, MockDatabase, MockDate2021 } from "../../testing_util";
import { JSONFileDataSourceRepository, ParticipationRepository } from "../../repository";
import { DataSource, DataSourceStub } from "../../datasource";

describe('participationRepository 기능테스트', () => {

    let participationRepository : ParticipationRepository;

    DataSource.getInstance = jest.fn( () => new DataSourceStub(getMockDatabase(MockDatabase.ONEFULLDATA)))
    Date.now = jest.fn( () => getMockDate(MockDate2021.APR_FIRST));
    
    /// ⚠️ 주의 : DataSource.getInstance()는 실제 Repository 객체에 들어가는 인스턴스와 다릅니다. 테스팅을 위해 사용합니다.
    const datsSource = DataSource.getInstance()
    datsSource.saveData();
    participationRepository = new JSONFileDataSourceRepository();

    test( 'Repository에서 활용하는 데이터는 데이터 소스에서 가져온 데이터와 일치해야한다.' , () => {
        const monthData = participationRepository.queryAllParticipants();
        const expected = getMockDatabase(MockDatabase.ONEFULLDATA).participationData!.data;
        expect(monthData).toStrictEqual(expected);
    });

    test( 'ID를 기준으로 각 참여자들의 참석 데이터를 정확하게 쿼리할 수 있어야 한다.' , () => {

        const monthQuery1 = participationRepository.queryById("jihyunhillpark");
        const query1Expected = getMockDatabase(MockDatabase.ONEFULLDATA).participationData!.data[0];
        expect(monthQuery1).toStrictEqual(query1Expected);
        const monthQuery2 = participationRepository.queryById("jiwoo-choi");
        expect(monthQuery2).toBeNull();
    });


    test( ' yyyymm에 해당하는 참여자들 정보만 받아올 수 있어야 한다.' , () => {
        const monthQuery1 = participationRepository.queryByYYYYMM("202101");
        const query1Expected = getMockDatabase(MockDatabase.ONEFULLDATA).participationData!.data
        expect(monthQuery1).toStrictEqual(query1Expected);
        const monthQuery2 = participationRepository.queryByYYYYMM("202102");
        expect(monthQuery2).toStrictEqual([]);
    });

    test( '참석자들 기준으로 모두 참여한 yyyymm를 반환해야한다' , () => {
        const yyyyms = participationRepository.queryAvailableYYYYMMM();
        expect(yyyyms).toStrictEqual(["202101"]);
    });

})