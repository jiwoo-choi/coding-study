import Calendar from '../Calendar';

describe('달력 기능 테스트' , () => {
   
    let calendar: Calendar;
    
    beforeEach( () =>{ 
        Date.now = jest.fn( () => 1616000201408);
         calendar = new Calendar();
    })

    test('달력은 현재 일을 스트링으로 표시해줄 수 있어야 한다', () => {
        expect(calendar.builder().date.build()).toBe("18");
    });

    test('달력은 현재 월을 표시해줄 수 있어야 한다', () => {
        expect(calendar.builder().month.build()).toBe("03");
    });

    test('달력은 현재 요일을 표시해줄 수 있어야 한다', () => {
        expect(calendar.builder().day.build()).toBe("목요일");
    });

    test('달력은 현재 연도를 스트링으로 표시해줄 수 있어야 한다', () => {
        expect(calendar.builder().year.build()).toBe("2021");
    });

    test('달력은 현재 시간 표시해줄 수 있어야 한다', () => {
        expect(calendar.builder().hour.build()).toBe("01");
    });

    test('달력은 현재 분 표시해줄 수 있어야 한다', () => {
        expect(calendar.builder().minutes.build()).toBe("56");
    });

    test('달력은 한꺼번에 초 정보를 표현할 수 있어야 한다.', () => {
        expect(calendar.builder().seconds.build()).toBe("41");
    })

    test('달력은 한꺼번에 정보를 delimiter와 함께 표현할 수 있어야 한다.', () => {
        expect(calendar.builder("-").month.date.day.build()).toBe("03-18-목요일");
    })

    test('달력은 한꺼번에 정보를 delimiter와 함께 표현할 수 있어야 한다.', () => {
        expect(calendar.builder().yyyymm.build()).toBe("202103");
    })
    
    test('달력은 한꺼번에 정보를 delimiter가 복수개여도 대응할 수 있어야 한다.', () => {
        expect(calendar.builder("----").month.date.day.build()).toBe("03----18----목요일");
    })

    test('달력은 한꺼번에 정보를 중간에 delimiter가 변경되어도 대응될 수 있어야 한다', () => {
        expect(calendar.builder("----").month.delimiter("%").date.day.build()).toBe("03----18%목요일");
    })

    test('달력은 오늘이 (객체를 생성한 시점) 첫번재 날이 아니라면, 첫번째 날이 아니라고 해야한다', () => {
        Date.now = jest.fn( () => 1616000201408);
        calendar = new Calendar();
        expect(calendar.isFirstDayInMonth()).toBeFalsy();
    })

    test('달력은 오늘이 (객체를 생성한 시점) 첫번째 날이라면, 첫번째 날이라고 해야한다..', () => {
        Date.now = jest.fn( () => 1614556800000);
        calendar = new Calendar();
        expect(calendar.isFirstDayInMonth()).toBeTruthy();
    })

    test('달력은 오늘이 (객체를 생성한 시점) 마지막 날이 아니라면, 마지막 날이 아니라고 해야한다', () => {
        Date.now = jest.fn( () => 1616000201408);
        calendar = new Calendar();
        expect(calendar.isLastDayInMonth()).toBeFalsy();
    })

    test('달력은 오늘이 (객체를 생성한 시점) 마지막 날이면, 마지막 날이라고 해야한다.', () => {
        Date.now = jest.fn( () => 1614470400000);
        calendar = new Calendar();
        expect(calendar.isLastDayInMonth()).toBeTruthy();
    })

    test('달력은 실제로 시간이 바뀌어도 기준 날짜가 업데이트 되어서는 안된다.', () => {
        const dateString = calendar.builder("-").date.day.build();
        Date.now = jest.fn( () => 1614470400000);
        expect(dateString).toEqual(calendar.builder("-").date.day.build())
    })



    test('달력은 클라이언트가 원할 때 업데이트 될 수 있다.', () => {
        const dateString = calendar.builder("-").date.day.build();
        Date.now = jest.fn( () => 1614470400000);
        calendar.updateDate();
        expect(dateString).not.toEqual(calendar.builder("-").date.day.build());
    })

    test('달력끼리 상태를 싱크할 수 있어야 한다.', () => {
        //?
    })

    test('어제 정보를 정확하게 구할 수 있어야 한다', () => {
        //?
        Date.now = jest.fn( () => 1616000201408);
        calendar.updateDate();
        const today = calendar.builder().date.build();
        const yesterday = calendar.getYesterDay().builder().date.build();
        expect(parseInt(today) - parseInt(yesterday)).toBe(1);
    })

    test('어제 정보를 정확하게 구할 수 있어야 한다 (윤년 28일 3월 1일)', () => {
        //?
        Date.now = jest.fn( () => 1614556800000);
        calendar.updateDate();
        const today = calendar.builder().date.build();
        const yesterday = calendar.getYesterDay().builder().date.build();
        expect(Math.abs(parseInt(today) - parseInt(yesterday))).toBe(27);
    })

    
    //
    test('다음날 정보를 정확하게 구할 수 있어야 한다', () => {
        Date.now = jest.fn( () => 1616000201408);
        calendar.updateDate();
        const today = calendar.builder().date.build();
        const tomorrow = calendar.getTomorrow().builder().date.build();
        expect(parseInt(tomorrow) - parseInt(today)).toBe(1);
    })

    test('다음날 정보를 정확하게 구할 수 있어야 한다', () => {
        Date.now = jest.fn( () => 1614556800000);
        calendar.updateDate();
        const today = calendar.builder().date.build();
        const tomorrow = calendar.getTomorrow().builder().date.build();
        expect(parseInt(tomorrow) - parseInt(today)).toBe(1);
    })


    test('이번달 총 일수를 알려줘야 한다.', () => {

        Date.now = jest.fn( () => 1614470400000);
        calendar.updateDate();
        const mappedArray = calendar.getTotalDaysInMonths(({year, month, day}) => {
            return year;
        })
        expect(mappedArray.length).toBe(28);        
        expect(calendar.getTotalDaysInMonths(({year, month, day}) => {
            return year;
        })).toStrictEqual(new Array(28).fill(0).map(() => "2021"));
        expect(calendar.getTotalDaysInMonths(({year, month, day}) => {
            return month;
        })).toStrictEqual(new Array(28).fill(0).map(() => "02"));
        expect(calendar.getTotalDaysInMonths(({year, month, day}) => {
            return day;
        })).toStrictEqual(new Array(28).fill(0).map((val, index) => ("0"+(index+1)).slice(-2)));
    })
    


})


/**
 * 달력클래스가 해야하는 서비스 기능들.
 * 3. 이번주 표시 =-> 유일한관리서비스.
 * 4. 다음주 표시
 * 5. 이번달표시.
 * 6. 이번달 총 일수 표기.
 * 7. 첫번째달 표시.
 * 8. 한국 timezone 셋팅된 값.
 * 9. 업데이트.
 * 다음날, 이전날, 다음달, 이전달.
 * 첫번째날인지 구하라.
 * 마지막날인지 구하라.
 * 
 */