import { DataType } from "../entity"

export enum MockDatabase {
    ONEFULLDATA
}
export default function getMockDatabase(database : MockDatabase) : DataType {

    switch(database) {
        case MockDatabase.ONEFULLDATA: {
            return {
                monthlyData: {
                    "data": [
                        {
                        "year": "2021",
                        "month": "01",
                        "yyyymm": "202101",
                        "attendance": [
                            {
                            "day": "23",
                            "checked": [
                                "jihyunhillpark"
                            ],
                            "issue_number": 1
                            }
                        ]
                    }],
                    update : "update string"
                },
                participationData: {
                    "data": [
                        {
                        "id": "jihyunhillpark",
                        "participation": [
                            {
                            "yyyymm": "202101",
                            "attendance": [
                                true,
                            ],
                            "start": "23",
                            "last": "23"
                            },
                        ]
                    }],
                    update : "123"
                },
                presetParticipants : [
                    {
                        github : "jihyunhillpark",
                        boj:"jihyunhillpark"
                    }
                ]
            }
        }
    }
}
