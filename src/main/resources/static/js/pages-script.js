document.addEventListener("DOMContentLoaded", function () {
	const isLocal = location.hostname === 'localhost' || location.hostname === '127.0.0.1';
	if (isLocal) {
	    return;
	}
	fetch("/session/status")
	.then(res => res.json())
	.then(data => {
	    if (data.valid) {
	        document.querySelectorAll(".auth-only").forEach(item => {
	            if (item.tagName === "LI" || item.tagName === "DIV") {
	                item.style.display = "block";
	            } else if (item.tagName === "HR") {
	                item.style.display = "block";
	            } else {
	                item.style.display = "inline-block";
	            }
	        });
	    }
	})
	.catch(err => {
	    console.error("세션 상태 확인 실패:", err);
	});
});

function openExternalSite(event) {
	event.preventDefault(); // 기본 링크 동작 방지
	var confirmMove = confirm("해당 사이트는 외부로 연결됩니다.\n계속하시겠습니까?");
	if (confirmMove) {
		window.open("https://smvno.sk7mobile.com/main.do", "_blank");
	}
}

let pendingUrl = '';

function checkPassword(event, url) {
    event.preventDefault();
    event.stopPropagation();
    
    const modalOverlay = document.getElementById('modalOverlay');
    if (!modalOverlay) {
        console.error('모달 요소를 찾을 수 없습니다.');
        return false;
    }
    
    pendingUrl = url;
    
    // 모달 표시 (애니메이션 포함)
    modalOverlay.style.display = 'flex';
    setTimeout(() => {
        modalOverlay.classList.add('show');
    }, 10);
    
    return false;
}

function confirmAccess() {
    const passwordInput = document.getElementById('adminPassword');
    const errorDiv = document.getElementById('passwordError');
    const enteredPassword = passwordInput ? passwordInput.value : '';
    
    if (enteredPassword === '8789') {
        if (pendingUrl) {
            window.location.href = pendingUrl;
            pendingUrl = null;
        }
        closeModal();
    } else {
        // 에러 메시지 표시
        if (errorDiv) {
            errorDiv.style.display = 'block';
        }
        if (passwordInput) {
            passwordInput.value = '';
            passwordInput.focus();
        }
    }
}

function closeModal() {
    const modalOverlay = document.getElementById('modalOverlay');
    if (modalOverlay) {
        modalOverlay.style.display = 'none';
        
        // 모달 닫을 때 에러 메시지도 숨김
        const errorDiv = document.getElementById('passwordError');
        if (errorDiv) {
            errorDiv.style.display = 'none';
        }
    }
}