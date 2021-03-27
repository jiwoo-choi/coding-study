export enum MockDate2021 {
    APR_FIRST, JAN_THIRTYFIRST, FEB_TWENTYEIGHTH
}
export default function getmMockDate2021(dateSetting : MockDate2021) : number {
    switch(dateSetting) {
        case MockDate2021.APR_FIRST:
            return 1617235200000;
        case MockDate2021.JAN_THIRTYFIRST:
            return 1614470400000;
        case MockDate2021.FEB_TWENTYEIGHTH:
            return 1612051200000;
    }
}
