import ChartQueryGenerator from "../ChartQueryGenerator"
import { BarTypeChart } from '../ChartQueryGenerator'

describe('참여자별 월별 참석률 구하기', () => {
  let chartQueryGenerator : ChartQueryGenerator = new ChartQueryGenerator();

  test('데이터가 한개 있을 경우', () => {
    const barType : BarTypeChart = chartQueryGenerator.getMonthlyPariticipantRate({"id":"jihyunhillpark","participation":[{"yyyymm":"202104","attendance":[true,true,true,true],"start":"01","last":"04"}]})
    //.replace(/"/gi, "\'")
    expect(barType).toStrictEqual(
      {
        type : 'bar',
        data: {
          labels: ["2021-04"],
          datasets:[{
            "backgroundColor": "rgb(54,162,235)",
            data:[Math.round(((4/30) * 100))]
          }]
        },
        options : {
          legend : {
              display:false,
          },
          scales : {
            yAxes:[
                {
                    ticks:{
                        stepSize:5,
                        min:0,
                        max:100,    
                    }
                }
            ]
          }
        },
       
      } as BarTypeChart
    )
  }) 

  test('데이터가 두개 있을 경우', () => {
    const barType : BarTypeChart = chartQueryGenerator.getMonthlyPariticipantRate({
      "id":"jihyunhillpark",
      "participation":[
        {"yyyymm":"202102","attendance":[true,true],"start":"26","last":"27"},
        {"yyyymm":"202104","attendance":[true,true,true,true],"start":"01","last":"04"}
      ]
    })


    expect(barType).toStrictEqual(
      {
        type : 'bar',
        data: {
          labels: ["2021-02", "2021-04"],
          datasets:[{
            "backgroundColor": "rgb(54,162,235)",
            data:[Math.round(((2/28) * 100)), Math.round(((4/30) * 100))]
          }]
        },
        options : {
          legend : {
              display:false,
          },
          scales : {
            yAxes:[
                {
                    ticks:{
                        stepSize:5,
                        min:0,
                        max:100,
                    }
                }
            ]
          }
        },
        
      } as BarTypeChart
    )
  }) 

})

describe('월별로 참석자의 전체 참석률 구하기', () => {

  let chartQueryGenerator : ChartQueryGenerator = new ChartQueryGenerator();

  test('참여자가 한명 있을 경우', () => {

    const barType : BarTypeChart = chartQueryGenerator.getSumRateByPplSinceBeginning(
      [{
        id: "jihyunhillpark",
        participation : [
          {"yyyymm":"202102","attendance":[true,true],"start":"26","last":"27"},
          {"yyyymm":"202104","attendance":[true,true,true,true],"start":"01","last":"04"}
        ]
      }]
    );


    expect(barType).toStrictEqual(
      {
        type : 'bar',
        data: {
          labels: ["jihyunhillpark"],
          datasets:[{
            "backgroundColor": "rgb(255,99,132)",
            data:[6]
          }]
        },
        options : {
          legend : {
              display:false,
          }
      }
      } as BarTypeChart
    )

  })

  test('참여자가 두명 있을 경우', () => {

    const barType : BarTypeChart = chartQueryGenerator.getSumRateByPplSinceBeginning(
      [
        {id: "jihyunhillpark",
        participation : [
          {"yyyymm":"202102","attendance":[true,true],"start":"26","last":"27"},
          {"yyyymm":"202104","attendance":[true,true,true,true],"start":"01","last":"04"}
        ]
      },{
        id: "jiwoo-choi",
        participation : [
          {"yyyymm":"202102","attendance":[true,true,true,true,true,true],"start":"01","last":"06"},
          {"yyyymm":"202104","attendance":[true,true,true,true],"start":"01","last":"04"}
        ]
      }
    ]);

    expect(barType).toStrictEqual(
      {
        type : 'bar',
        data: {
          labels: ["jihyunhillpark", "jiwoo-choi"],
          datasets:[{
            "backgroundColor": "rgb(255,99,132)",
            data:[6, 10]
          }]
        },
        options : {
          legend : {
              display:false,
          }
      }
      } as BarTypeChart
    )

  })
})


describe('참석자별 지금까지 참석횟수 비교 차트', () => {
  let chartQueryGenerator : ChartQueryGenerator = new ChartQueryGenerator();

  test('참여자가 한명 있을 경우', () => {

    const barType : BarTypeChart = chartQueryGenerator.getSumRateByMonthSinceBegining(
      [
        {
          "year": "2021",
          "month": "01",
          "yyyymm": "202101",
          "attendance": [
            {
              "day": "23",
              "checked": [
                "jiwoo-choi",
                "jeesooyaa",
                "hoonti06",
                "jihyunhillpark"
              ],
              "issue_number": 1
            }
          ]
        }
      ]
    );


    expect(barType).toStrictEqual(
      {
        type : 'bar',
        data: {
          labels: ["202101"],
          datasets:[{
            "backgroundColor": "rgb(75,192,192)",
            data:[4]
          }]
        },
        options : {
          legend : {
              display:false,
          }
      }
      } as BarTypeChart
    )

  })

  test('참여자가 두명 있을 경우', () => {
    const barType : BarTypeChart = chartQueryGenerator.getSumRateByMonthSinceBegining(
     [
        {
          "year": "2021",
          "month": "01",
          "yyyymm": "202101",
          "attendance": [
            {
              "day": "23",
              "checked": [
                "jiwoo-choi",
                "jeesooyaa",
                "hoonti06",
                "jihyunhillpark"
              ],
              "issue_number": 1
            }
          ]
        },
        {
          "year": "2021",
          "month": "02",
          "yyyymm": "202102",
          "attendance": [
            {
              "day": "01",
              "checked": [
                "jiwoo-choi",
                "jihyunhillpark",
                "hoonti06",
                "jeesooyaa"
              ],
              "issue_number": 12
            },
          ]
        },
      ]
    );

    expect(barType).toStrictEqual(
      {
        type : 'bar',
        data: {
          labels: ["202101", "202102"],
          datasets:[{
            "backgroundColor": "rgb(75,192,192)",
            data:[4, 4]
          }]
        },
        options : {
          legend : {
              display:false,
          }
      }
      } as BarTypeChart
    )

  })
})