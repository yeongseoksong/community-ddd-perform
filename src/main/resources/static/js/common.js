// common.js

// 공통 Alert
function showAlert(message, type = 'info') {
    switch (type) {
        case 'success':
            alert("✅ " + message);
            break;
        case 'error':
            alert("❌ " + message);
            break;
        default:
            alert("ℹ️ " + message);
    }
}

// 폼 빈값 체크
function isEmpty(value) {
    return value == null || value.trim() === '';
}

// 로딩 표시 (예: AJAX 호출 시)
function showLoading() {
    $('#loading-overlay').show();
}

function hideLoading() {
    $('#loading-overlay').hide();
}

// 페이지 공통 초기화
$(document).ready(function () {
    console.log("📦 공통 스크립트 로딩됨.");
});
