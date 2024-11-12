// footer.js 파일

const jQueryScript = document.createElement('script');
jQueryScript.src = 'https://code.jquery.com/jquery-3.6.0.min.js';
document.body.insertAdjacentElement('beforeend', jQueryScript);

// Bootstrap
const bootstrapScript = document.createElement('script');
bootstrapScript.src = 'https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js';
bootstrapScript.integrity = 'sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz';
bootstrapScript.crossOrigin = 'anonymous';
document.body.appendChild(bootstrapScript);


// SweetAlert
const sweetalertScript = document.createElement('script');
sweetalertScript.src = 'https://unpkg.com/sweetalert/dist/sweetalert.min.js';
document.body.appendChild(sweetalertScript);

// 전역 쉼표 처리 함수
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');  // 천 단위 쉼표 추가
}

// 전역 쉼표 제거 함수
function convertNumber(str) {
    str = String(str.replace(/[^\d]+/g, ''));  // 숫자를 제외한 모든 문자 제거


    if (/^0{2,}/.test(str)) {
        // 두 번 이상 연속된 0을 잘라내고 나머지 부분 반환
        return str.replace(/^0{2,}/, '0');
    }
    return str;
}

// 숫자를 한글로 변환
function convertToKoreanNumber(number) {
    var inputNumber  = number < 0 ? false : number;
    var unitWords    = ['', '만', '억', '조', '경'];
    var splitUnit    = 10000;
    var splitCount   = unitWords.length;
    var resultArray  = [];
    var resultString = '';

    for (var i = 0; i < splitCount; i++){
        var unitResult = (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
        unitResult = Math.floor(unitResult);
        if (unitResult > 0){
            resultArray[i] = unitResult;
        }
    }

    for (var i = 0; i < resultArray.length; i++){
        if(!resultArray[i]) continue;
        resultString = String(numberFormat(resultArray[i])) + unitWords[i] + resultString;
    }

    return resultString;
}
function numberFormat(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
// 페이지 로드 시 개인 마감 상태 확인
function isClosed(){
    $.ajax({
        url: '/api/common/business-day-close/status',
        type: 'GET',
        success: function (response) {
            var closeOverlay = document.getElementById("close-overlay");
            if(closeOverlay) { // close-overlay 요소가 존재하는지 확인
                if(response == "CLOSED"){
                    closeOverlay.style.display = "flex";
                } else if(response =="OPEN"){
                    closeOverlay.style.display = "none";
                }
            }
        }
    })
}

function getParameterByName(name, url) {
    // URL에서 쿼리 파라미터를 찾기 위한 정규식 생성
    const urlParams = new URLSearchParams(new URL(url).search);
    return urlParams.get(name); // 해당 파라미터의 값을 반환
}