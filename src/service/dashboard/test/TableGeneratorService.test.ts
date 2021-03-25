import { DataSource, DataSourceStub } from "../../../datasource";
import { JSONFileDataSourceRepository } from "../../../repository";
import { MarkdownTableGeneratorService, TableGeneratorService } from "../..";

describe('목업 심플 데이터를 통한 TableGeneratorSerivce 테스트 - 1 목업데이터 202101년 1월 간단한 데이터.', () => {

    let tableGeneratorService: TableGeneratorService;

    let count = 0;
    let data = [
        {
            participationData : {data:[{"id":"jihyunhillpark","participation":[{"yyyymm":"202101","attendance":[],"start":"0","last":"0"}]}], update:"123"}
        },
        {
            participationData : {data:[{"id":"jihyunhillpark","participation":[{"yyyymm":"202102","attendance":[],"start":"0","last":"0"}]}], update:"123"}
        },
        {
            participationData : {data:[{"id":"jihyunhillpark","participation":[{"yyyymm":"202104","attendance":[],"start":"0","last":"0"}]}], update:"123"}
        },
        {
            participationData : {data:[{"id":"jihyunhillpark","participation":[{"yyyymm":"202104","attendance":[true,true,true,true],"start":"01","last":"04"}]}], update:"123"}
        },
        {
            participationData : {data:[
                {"id":"jihyunhillpark","participation":[{"yyyymm":"202104","attendance":[true,true,true,true],"start":"01","last":"04"}]},
                {"id":"jiwoo-choi","participation":[{"yyyymm":"202102","attendance":[true,true],"start":"26","last":"27"}]},
        ], update:"123"} 
        },
        {
            participationData : {data:[
                {"id":"jihyunhillpark","participation":[{"yyyymm":"202104","attendance":[true,true,true,true],"start":"01","last":"04"}]},
                {"id":"jiwoo-choi","participation":[{"yyyymm":"202104","attendance":[true,true],"start":"26","last":"27"}]},
                {"id":"jiwoo-choi","participation":[{"yyyymm":"202102","attendance":[true,true],"start":"26","last":"27"}]},
        ], update:"123"}
        }
    ]

    beforeEach( () => {

        DataSource.getInstance = jest.fn( () => new DataSourceStub(
            data[count++]
        ));

        tableGeneratorService = new MarkdownTableGeneratorService(
            new JSONFileDataSourceRepository()
        );


    })

    function mockHeaderMaker(numberOfDays:number, month:string) : string {
        let header = ["참여자"].concat(new Array(numberOfDays).fill(1).map( (v,idx) =>  month +"/" + ("000" + (idx+1)).slice(-2))).join("|")
        let divider = new Array(numberOfDays+1).fill("|--").join("");   
        return [header,divider].join('\n');
    }

    function mockRowMaker(participant: string, numberOfDays:number, checked: string, unchecked: string, data? : boolean[]) : string {
        if (data === undefined) {
            return [participant].concat(new Array(numberOfDays).fill(unchecked)).join("|") + '\n';
        } else {
            return [participant].concat(data!.map( v => v ? checked : unchecked)).join("|") + '\n';

        }
    }

    test('참석 데이터가 없을 경우 기본적으로 헤더와 데이터가 나와야한다. - 2021년 1월 데이터 (1일부터 31일까지)' , () => {
        expect(tableGeneratorService.populateTable()).toBe(mockHeaderMaker(31,"01") + '\n' + mockRowMaker('jihyunhillpark', 31, "O", "X") + '\n');
    })

    test('참석 데이터가 없을 경우 기본적으로 헤더와 데이터가 나와야한다. - 2021년 2월 데이터 (1일부터 28일까지)' , () => {
        expect(tableGeneratorService.populateTable()).toBe(mockHeaderMaker(28, "02") + '\n' + mockRowMaker('jihyunhillpark', 28, "O", "X") + '\n');
    })

    test('참석 데이터가 없다고 하더라도 기본적으로 헤더와 데이터가 나와야한다. - 2021년 4월 데이터 (1일부터 30일까지)' , () => {
        expect(tableGeneratorService.populateTable()).toBe(mockHeaderMaker(30, "04") + '\n' + mockRowMaker('jihyunhillpark', 30, "O", "X") + '\n');
    })

    test('부분데이터가 존재하면, 그 부분에 대해서는 참석여부를 가려주고 나머지는 채운다- 1일부터 4일까지 True가 주어진다. 2021년 4월 데이터 (1일부터 30일까지)' , () => {
        expect(tableGeneratorService.populateTable()).toBe(
            mockHeaderMaker(30, "04") + '\n' 
            + mockRowMaker(
                'jihyunhillpark'
                , 30
                , "O"
                , "X"
                , [true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false]
            )
            + '\n'
        );
    })

   

    test('2명의 참여자가 제공. 2021년 2,4월 데이터 (1일부터 30일까지)' , () => {
        expect(tableGeneratorService.populateTable()).toBe(
            mockHeaderMaker(30, "04") + '\n' 
            + mockRowMaker(
                'jihyunhillpark'
                , 30
                , "O"
                , "X"
                , [true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false]
            ) 
            + '\n'
            + mockHeaderMaker(28, "02") + '\n' 
            + mockRowMaker(
                'jiwoo-choi'
                , 28
                , "O"
                , "X"
                , [false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false]
            ) 
            + '\n'
        );
    })


    test('2명의 참여자가 제공. 2021년 2월(2명),4월(1명) 데이터 (1일부터 30일까지)' , () => {
        expect(tableGeneratorService.populateTable()).toBe(
            mockHeaderMaker(30, "04") + '\n' 
            + mockRowMaker(
                'jihyunhillpark'
                , 30
                , "O"
                , "X"
                , [true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false]
            ) 
            + mockRowMaker(
                'jiwoo-choi'
                , 30
                , "O"
                , "X"
                , [false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false]
            ) 
            + '\n'
            + mockHeaderMaker(28, "02") + '\n' 
            + mockRowMaker(
                'jiwoo-choi'
                , 28
                , "O"
                , "X"
                , [false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false]
            )    
            + '\n'  
        );
    })

});
