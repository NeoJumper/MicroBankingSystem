const headerMap = new Map();

// 각 헤더에 하위 항목 추가
headerMap.set("header-account-management", {
    sidebar: [
        "account-open",
        "account-close",
        "account-close-cancel",
        "account-transfer",
        "cash-trade",
        "trade-list",
        "bulk-transfer",
        "savings-account-open",
        "savings-account-close",
        "reserve-list",
        "transfer-limit-update"
    ],
    sub: {
        "bulk-transfer" : ["bulk-transfer-result"],
        "trade-list": ["account-transfer-cancel"] // trade-list 내의 하위 URL을 관리
    }
});
headerMap.set("header-business-day-management", {
    sidebar: [
        "business-day-management",
        "business-day-close"
    ],
    sub: {}
});
headerMap.set("header-customer-management", {
    sidebar: [
        "customer-save", "customer-list", "customer-update", "otp-register"
    ],
    sub: {}
});

headerMap.set("header-employee-management", {
    sidebar: ["employee-save", "employee-list", "employee-update"],
    sub: {}
});

headerMap.set("header-dashboard", {
    sidebar: ["dashboard", "cash-exchange", "cash-exchange-close"],
    sub: {}
});


$(document).ready(function () {

    //registerClickEventOfHeaderMenu(); // header 클릭 이벤트

    // 사이드바 토글
    $("#sidebarCollapse").on("click", function () {
        $("#sidebar").toggleClass("active");
    });



});

function handleHeaderAndSidebar(roles) {
    const currentUrl = extractUrl(window.location.href);
    const selectedHeaderMenu = findHeader(currentUrl);
    const selectedSidebarMenu = findSidebar(currentUrl);
    console.log(selectedHeaderMenu);
    console.log(selectedSidebarMenu);


    // 사이드바 내용을 처리하고 완료 후 추가 작업 수행
    handleSidebarContent(selectedHeaderMenu , selectedSidebarMenu, roles)
        .then(() => {
            console.log(selectedHeaderMenu);
            // selectedHeaderMenu에 클래스 추가
            $('#' + selectedHeaderMenu  + ' > a').addClass('active-header');

            console.log(selectedSidebarMenu);

            // selectedSidebarMenu에 클래스 추가
            $('#sidebar-' + selectedSidebarMenu + ' > a').addClass('active-sidebar');

        })
        .catch((error) => {
            // 오류 처리
        });
}



function  handleSidebarContent(selectedHeaderMenu, selectedSidebarMenu, roles){
    switch (selectedHeaderMenu) {
        case 'header-account-management':
            //console.log('계좌 관리 선택됨');
            createAccountManagementSidebar(selectedSidebarMenu);
            break;
        case 'header-customer-management':
            //console.log('고객 관리 선택됨');
            createCustomerManagementSidebar(selectedSidebarMenu);
            break;
        case 'header-employee-management':
            //console.log('행원 관리 선택됨');
            createEmployeeManagementSidebar(selectedSidebarMenu);
            break;
        case 'header-business-day-management':
            //console.log('영업일 관리 선택됨');
            createBusinessDayManagementSidebar(selectedHeaderMenu, selectedSidebarMenu, roles);
            break;
        case 'header-dashboard':
            createDashboardSidebar(selectedSidebarMenu, roles);
            break;
        default:
            //console.log('알 수 없는 항목 선택됨');
            break;
    }

    return new Promise((resolve, reject) => {
        // 사이드바 내용 처리 로직
        // 작업이 끝난 후 resolve() 호출
        resolve();
    });
}
function createAccountManagementSidebar(selectedSidebarMenu) {
    var menuData = [
        {
            title: '예금 관리',
            icon: 'bi bi-bank',
            submenu: [
                { name: '계좌 개설', url: '/page/employee/account-open' },
                { name: '계좌 해지', url: '/page/employee/account-close' },
                { name: '이체한도 조회/변경', url : '/page/employee/transfer-limit-update'}
            ]
        },
        {
            title: '적금 관리',
            icon: 'bi bi-bank',
            submenu: [
                { name: '계좌 개설', url: '/page/employee/savings-account-open' },
                { name: '계좌 해지', url: '/page/employee/savings-account-close' },
            ]
        },
        {
            title: '계좌 이체',
            icon: 'bi bi-arrow-right-circle',
            submenu: [
                { name: '즉시/예약 이체', url: '/page/employee/account-transfer' },
                { name: '대량 계좌 이체', url: '/page/employee/bulk-transfer' },
                { name: '현금 입출금', url: '/page/employee/cash-trade' },
                { name: '거래 내역', url: '/page/employee/trade-list' },
                { name: '예약 이체 내역', url: '/page/employee/reserve-list' },

            ]
        }
    ];

    //console.log("계좌 관리 사이드바 생성");
    createSidebar(menuData);
}

function createCustomerManagementSidebar() {
    var menuData = [
        {
            title: '고객 관리',
            icon: 'bi bi-person',
            submenu: [
                { name: '고객 등록', url: '/page/common/customer-save' },
                { name: '고객 목록', url: '/page/common/customer-list' },
                { name: '고객 수정', url: '/page/common/customer-update' },
                { name: 'OTP 발급/재발급', url: '/page/common/otp-register' }

            ]
        }
    ];

    //console.log("고객 관리 사이드바 생성");
    createSidebar(menuData);
}

function createEmployeeManagementSidebar() {
    var menuData = [
        {
            title: '행원 관리',
            icon: 'bi bi-person-check',
            submenu: [
                { name: '행원 추가', url: '/page/manager/employee-save' },
                { name: '행원 목록', url: '/page/manager/employee-list' },
                { name: '행원 수정', url: '/page/manager/employee-update' }
            ]
        }
    ];

    //console.log("행원 관리 사이드바 생성");
    createSidebar(menuData);
}

function createBusinessDayManagementSidebar(selectedHeaderMenu, selectedSidebarMenu, roles) {
    var menuData;

    if(roles === "행원")
    {
        menuData = [{
            title: '마감 관리',
            icon: 'bi bi-file-earmark-check',
            submenu: [
                { name: '마감 상태 관리', url: '/page/employee/business-day-close' }
            ]
        }];
    }else{
        menuData = [
            {
                title: '영업일 관리',
                icon: 'bi bi-calendar',
                submenu: [
                    { name: '영업일 변경', url: '/page/manager/business-day-management' }
                ]
            },
            {
            title: '마감 관리',
            icon: 'bi bi-file-earmark-check',
            submenu: [
                { name: '마감 상태 관리', url: '/page/manager/business-day-close' }
            ]
            }

        ];
    }


    //console.log("영업일 관리 사이드바 생성");
    createSidebar(menuData);
}


function createBranchManagementSidebar() {
    var menuData = [
        {
            title: '지점 관리',
            icon: 'bi bi-house',
            submenu: [
                { name: '지점 추가', url: '/branch/add' },
                { name: '지점 수정/삭제', url: '/branch/manage' },
            ]
        }
    ];

    console.log("지점 관리 사이드바 생성");
    createSidebar(menuData);
}


function createDashboardSidebar(selectedSidebarMenu, roles) {
    var menuData;
    console.log(roles);
    if(roles === "행원")
    {
        menuData = [
            {
                title: '실적 관리',
                icon: 'bi bi-house',
                submenu: [
                    { name: '실적 관리', url: '/page/employee/dashboard' },
                ]
            }
        ];
    }else{
        menuData = [
            {
                title: '지점 관리',
                icon: 'bi bi-house',
                submenu: [
                    { name: '지점 운영', url: '/page/manager/dashboard' },
                ]
            },
            {
                title: '시재 관리',
                icon: 'bi bi-coin',
                submenu: [
                    { name: '인수도 거래', url: '/page/manager/cash-exchange' },
                    { name: '인수도 거래 마감', url: '/page/manager/cash-exchange-close' }
                ]
            }
        ];
    }

    createSidebar(menuData);
}

// 사이드바 동적 생성 함수
function createSidebar(menuItems) {
    $('#sidebar').empty();
    var sidebarHtml = '<ul class="list-unstyled components">';

    // 각 메뉴와 서브메뉴를 동적으로 생성
    menuItems.forEach(function (menu) {
        sidebarHtml += `
                <li class="menu">
                  <a href="#"><i class="${menu.icon}"></i> ${menu.title}</a>
                  <ul class="submenu list-unstyled components">
            `;

        menu.submenu.forEach(function (submenuItem) {
            let listId = 'sidebar-' + extractUrl(submenuItem.url);
            //console.log(submenuItem);
            sidebarHtml += `<li id="${listId}"><a href="${submenuItem.url}">${submenuItem.name}</a></li>`;
        });

        sidebarHtml += '</ul></li>';
    });

    sidebarHtml += '</ul>';

    // 생성된 HTML을 #sidebar에 추가
    $('#sidebar').html(sidebarHtml);
}

function findHeader(value) {
    for (const [header, { sidebar, sub }] of headerMap.entries()) {
        // sidebar에서 먼저 값 찾기
        if (sidebar.includes(value)) {
            return header;
        }

        // sub에서 값 찾기
        for (const [mainItem, subItems] of Object.entries(sub)) {
            if (subItems.includes(value)) {
                return header; // 헤더 반환
            }
        }
    }
    return null; // 해당 값에 속하는 헤더가 없을 경우
}
function findSidebar(value) {
    console.log("value",value);
    console.log("headerMap",headerMap)
    for (const { sidebar, sub } of headerMap.values()) {
        // sidebar에서 직접 값 찾기
        if (sidebar.includes(value)) {
            return value;
        }

        // sub에서 값 찾기
        for (const [mainItem, subItems] of Object.entries(sub)) {
            if (subItems.includes(value)) {
                return mainItem; // 하위 항목이면 상위 sidebar 항목 반환
            }
        }
    }
    return null; // 해당 값에 속하는 sidebar 항목이 없을 경우
}


function extractUrl(url){
    //console.log(url);
    const splitUrl1 = url.split("/").pop();
    return  splitUrl1.split("?").shift();
}

function toggleSidebar() {
    var sidebar = document.getElementById("leftSidebar-wrapper");
    var menubar = document.getElementById("menubar");
    var mainArea = document.getElementById("main-area");
    var isOpen = sidebar.classList.contains("open");

    if (isOpen) {
        sidebar.classList.remove("open");
        sidebar.classList.add("closed");
        menubar.classList.remove("show-content");
        menubar.classList.add("hidden-content"); // 숨기기

        mainArea.style.paddingLeft = "150px";
    } else {
        sidebar.classList.remove("closed");
        sidebar.classList.add("open");
        menubar.classList.remove("hidden-content"); // 표시하기
        menubar.classList.add("show-content");

        mainArea.style.paddingLeft = "320px";
    }
}