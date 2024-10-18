$(document).ready(function() {
    handleCurrentBusinessDay();
    handleNextBusinessDay();
    registerClickEventOfBusinessDayResetBtnOfModal();
});

// -----------------------------------------------------------------------------




// 영업일 관리

let nextBusinessDay = '';
let currentBusinessDay = '';
let currentStatus;

function registerClickEventOfBusinessDayResetBtnOfModal() {
    $('#business-day-reset-modal-reset-btn').on('click', function () {
        $('#business-day-reset-modal').modal('hide');
        resetBusinessDay();
    });
}

/**
 * @Description
 * 현재 영업일 란 채우기
 * 현재 영업일이 마감됐을 때만 영업일 변경, 영업일 시작 버튼 활성화
 */
function handleCurrentBusinessDay(){
    $.ajax({
        url: '/api/common/current-business-day',
        type: 'GET',
        success: function(response) {
            currentBusinessDay = response.businessDate.substring(0, 10);
            currentStatus = response.status;

            $('#current-business-day').val(currentBusinessDay);
            handleCalendar();

            if(response.status === "OPEN")
            {

                $("#business-day-update-modal-btn").removeClass("basic-btn").addClass("closed-btn");
                $("#business-day-update-modal-btn").prop("disabled", true);

                $("#business-day-update-modal-update-btn").removeClass("basic-btn").addClass("closed-btn");
                $("#business-day-update-modal-update-btn").prop("disabled", true);

            }

        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}

// 다음 영업일 란 채우기
function handleNextBusinessDay(){
    $.ajax({
        url: '/api/common/next-business-day',
        type: 'GET',
        success: function(response) {
            // 성공 시 처리할 로직 작성
            nextBusinessDay = response.businessDate.substring(0, 10);
            $('#next-business-day').val(nextBusinessDay);
            $('#business-day-update-modal-next-business-day').html("다음 영업일&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;" + nextBusinessDay);
        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리할 로직 작성
            console.error('에러 발생:', error);
        }
    });
}

function resetBusinessDay(){
    $.ajax({
        url: '/api/manager/business-day-reset',
        type: 'PATCH',
        success: function(response) {
            swal({
                title: "영업 시작",
                text: "영업일 되돌리기 작업이 성공적으로 완료되었습니다.",
                icon: "success",
                button: "닫기",
            }).then(() => {
                // swal 경고창이 닫힌 후에 리다이렉트
                window.location.href = '/page/manager/business-day-management';
            });
        },
        error: function(xhr, status, error) {
            swal({
                title: "변경 실패",
                text: xhr.responseText,
                icon: "error",
                button: "닫기",
            })
            console.error('Error updating business day:', error);
        }
    });

    // 결과 JSON 데이터 출력 (또는 전송)
    console.log(businessDayUpdate);
}

// 달력 관련 전역 변수
let today;
let currentMonth;
let currentYear;

const monthAndYear = document.getElementById("month-year");
const calendarBody = document.getElementById("calendar-body");

const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월",
    "7월", "8월", "9월", "10월", "11월", "12월"];

function handleCalendar(){
    today = new Date(currentBusinessDay);
    currentMonth = today.getMonth();
    currentYear = today.getFullYear();

    showCalendar(currentMonth, currentYear);
}

document.getElementById("prev").addEventListener("click", () => {
    previous();
});

document.getElementById("next").addEventListener("click", () => {
    next();
});

function showCalendar(month, year) {

    calendarBody.innerHTML = "";

    // 해당 월의 첫째 날과 마지막 날 가져오기
    let firstDay = (new Date(year, month, 1)).getDay();
    let daysInMonth = 32 - new Date(year, month, 32).getDate();

    // 이전 달의 마지막 날짜 가져오기
    let prevMonthLastDate = new Date(year, month, 0).getDate();

    // 월과 연도 표시
    monthAndYear.innerHTML = `${year}년 ${monthNames[month]}`;

    // 날짜 생성
    let date = 1;
    for (let i = 0; i < 6; i++) {
        let row = document.createElement("tr");

        for (let j = 0; j < 7; j++) {
            let cell = document.createElement("td");
            let cellContent;

            // 요일에 따른 클래스 추가
            if (j === 0) {
                cell.classList.add('holiday'); // 일요일
            } else if (j === 6) {
                cell.classList.add('holiday'); // 토요일
            }


            if (i === 0 && j < firstDay) {
                cellContent = prevMonthLastDate - firstDay + j + 1;
                cell.classList.add("not-current-month");
                cell.appendChild(document.createTextNode(cellContent));
            } else if (date > daysInMonth) {
                cellContent = date - daysInMonth;
                cell.classList.add("not-current-month");
                cell.appendChild(document.createTextNode(cellContent));
                date++;
            } else {
                cellContent = date;
                // 날짜 숫자 추가
                cell.appendChild(document.createTextNode(cellContent));

                if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                    // badge 요소 생성 및 추가
                    let badge = document.createElement('span');
                    badge.className = 'badge rounded-pill text-bg-primary';
                    badge.innerText = currentStatus; // 필요한 경우 텍스트 변경
                    cell.classList.add('active');
                    cell.appendChild(badge);
                }

                date++;
            }

            row.appendChild(cell);
        }

        calendarBody.appendChild(row);
    }
}

function previous() {
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
}

function next() {
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
}
