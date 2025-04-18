document.addEventListener("DOMContentLoaded", function () {
    fetch("/session/status")
        .then(res => res.json())
        .then(data => {
            if (data.valid) {
                document.querySelectorAll(".auth-only").forEach(item => {
                    // 태그 종류별로 display 속성 지정
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

function applyFormDownload(preview = false) {
	
	var formElement = document.getElementById('dataForm');
	var dataForm = new FormData(formElement);
	
	var templateName = document.getElementById("templateName").value;
	
	if (templateName === "7모바일") {
        var skTelinkNumber = document.getElementById("skTelinkNumber");
        
        if (document.getElementById("newOption").checked) {
            var mobileNumber = document.getElementById("mobileNumber").value;
            skTelinkNumber.value = mobileNumber;
        } else if (document.getElementById("moveOption").checked) {
            var portabilityNumber = document.getElementById("portabilityNumber").value;
            skTelinkNumber.value = portabilityNumber;
        }
    } else if (templateName == "KTM모바일") {
		var relationshipOption = document.getElementById("relationshipOption").value;
		var relationship = "";
	    if (relationshipOption == "relationshipOption1") {
	        relationship = "부";
	    } else if (relationshipOption == "relationshipOption2") {
	        relationship = "모";
	    } else if (relationshipOption == "") {
	        relationship = "";
	    }
		
		var category = document.querySelector('input[name="openingCategory"]:checked').value;
		var mobileNumber = document.getElementById("mobileNumber").value;
		var portabilityNumber = document.getElementById("portabilityNumber").value;
		
		if (category === 'move' && mobileNumber == "") {
			document.getElementById('mobileNumber').value = portabilityNumber;
		}
			
		document.getElementById('relationship').value = relationship;
	} else if (templateName == "조이텔") {
		
		var depositorName = document.querySelector("input[name='depositorName']").value;
		var depositorBirthdate = document.querySelector("input[name='depositorBirthdate']").value;
		var depositorNumber = document.querySelector("input[name='depositorNumber']").value;
		var relationship = document.querySelector("input[name='relationship']").value;

		if (depositorName || depositorBirthdate || depositorNumber || relationship) {
		    dataForm.append("depositorCheck", "depositorCheck");
		}
	}

	var formattedDate = getFormattedDate();
	var templateName = document.getElementById('templateName').value;

	// disabled 필드를 폼에서 제거하는 로직
	var inputs = formElement.querySelectorAll('input:disabled, select:disabled, textarea:disabled');
	inputs.forEach(function(input) {
	    dataForm.delete(input.name); // FormData에서 해당 필드를 삭제
	});
	
	for (var pair of dataForm.entries()) {
	    console.log(pair[0] + ': ' + pair[1]);
	}

    showLoadingMessage(); // 로딩 메시지 표시

    fetch('/homepage/inscribeView/autoComplete', {
        method: 'POST',
        body: dataForm
    })
    .then(response => response.blob())
    .then(blob => {
        var url = window.URL.createObjectURL(blob);

        if (preview) {
            var pdfWindow = window.open("", "PDF Preview", "width=800,height=600");
            if (pdfWindow) {
                pdfWindow.document.write(
                    `<html>
                        <head><title>PDF Preview</title></head>
                        <body style="margin: 0;">
                            <embed width="100%" height="100%" src="${url}" type="application/pdf">
                        </body>
                    </html>`
                );
            } else {
                alert("팝업 차단이 설정되어 있습니다. 팝업 차단을 해제하고 다시 시도해 주세요.");
            }
        } else {
            var a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = templateName + '_' + formattedDate;
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
        }

        hideLoadingMessage(); // 로딩 메시지 숨기기
    })
    .catch(error => {
        console.error('Error downloading the document:', error);
        hideLoadingMessage(); // 에러 발생 시에도 로딩 메시지 숨기기
    });
}

function updateHiddenFields() {
    var selectElement = document.getElementById('carrierPlanSelect');
    var selectedOption = selectElement.options[selectElement.selectedIndex];

    var carrierPlanId = selectedOption.value;
    var carrierPlanName = selectedOption.getAttribute('data-plan-name');
    var mobileCarrier = selectedOption.getAttribute('data-mobile-carrier');
    var monthlyBillingAmt = selectedOption.getAttribute('data-monthly-billing');
    var basicAmt = selectedOption.getAttribute('data-basic-amt');
    var discountAmt = selectedOption.getAttribute('data-discount-amt');
    var voiceCall = selectedOption.getAttribute('data-voice-call');
    var mobileData = selectedOption.getAttribute('data-mobile-data');
    var mobileQos = selectedOption.getAttribute('data-mobile-qos');
    var mobileMessage = selectedOption.getAttribute('data-mobile-message');

    document.getElementById('carrierPlanId').value = carrierPlanId;
    document.getElementById('carrierPlanName').value = carrierPlanName;
    document.getElementById('mobileCarrier').value = mobileCarrier;
    document.getElementById('monthlyBillingAmt').value = monthlyBillingAmt;
    document.getElementById('basicAmt').value = basicAmt;
    document.getElementById('discountAmt').value = discountAmt;
    document.getElementById('voiceCall').value = voiceCall;
    document.getElementById('mobileData').value = mobileData;
    document.getElementById('mobileQos').value = mobileQos;
    document.getElementById('mobileMessage').value = mobileMessage;

    document.getElementById('monthlyBillingAmtDisplay').value = formatCurrency(monthlyBillingAmt || 0) + '원';
    document.getElementById('basicAmtDisplay').value = formatCurrency(basicAmt || 0) + '원';
    document.getElementById('discountAmtDisplay').value = formatCurrency(discountAmt || 0) + '원';
}

function getFormattedDate() {
    var now = new Date();
    var year = now.getFullYear();
    var month = ('0' + (now.getMonth() + 1)).slice(-2);
    var day = ('0' + now.getDate()).slice(-2);
    var hours = ('0' + now.getHours()).slice(-2);
    var minutes = ('0' + now.getMinutes()).slice(-2);

    return year + month + day + hours + minutes;
}

function toggleTransferForm() {
    // 라디오 버튼에서 선택된 값 가져오기
    var isCard = document.querySelector('input[name="automaticTransfer"]:checked').value === 'card';

    // 선택된 값에 따라 폼 보이기
    document.getElementById('cardForm').style.display = isCard ? 'block' : 'none';
    document.getElementById('accountForm').style.display = isCard ? 'none' : 'block';
}



function toggleFormFields(form, enable) {
    var inputs = form.querySelectorAll('input, select');
    inputs.forEach(input => {
        input.disabled = !enable;  // 활성화/비활성화 처리
    });
}

function toggleCustomerFields() {
    // 라디오 버튼에서 선택된 값을 가져옴
    var category = document.querySelector('input[name="openingCategory"]:checked').value;

    var activationNumber = document.getElementById('activationNumber');
    var portabilityNumber = document.getElementById('portabilityNumber');
    var previousCarrierLabel = document.getElementById('previousCarrierLabel');
    var previousCarrierSelect = document.getElementById('previousCarrierSelect');
    var mvno = document.getElementById('mvno');
    var commonMoveCheck = document.getElementById('commonMoveCheck');

    if (category === 'new') {
        // 신규 고객 폼: 이전 통신사 숨김
        mvno.style.display = 'none';
        previousCarrierLabel.style.display = 'none';
        previousCarrierSelect.style.display = 'none';
        activationNumber.disabled = false;
        portabilityNumber.disabled = true;
        previousCarrierSelect.disabled = true;
        mvno.disabled = true;
        commonMoveCheck.disabled = true;
    } else if (category === 'move') {
        // 번호이동 고객 폼: 이전 통신사 보임
        previousCarrierLabel.style.display = 'block';
        previousCarrierSelect.style.display = 'block';
        activationNumber.value = ''; // 값 초기화
        activationNumber.disabled = true;
        portabilityNumber.disabled = false;
        previousCarrierSelect.disabled = false;
        commonMoveCheck.disabled = false;
    }
}

function toggleMVNOField() {
    var previousCarrier = document.getElementById('previousCarrierSelect').value;
    var mvnoField = document.getElementById('mvno');

    if (previousCarrier === 'previousCarrier4') {
        mvnoField.style.display = 'block'; // MVNO 필드 표시
        mvnoField.disabled = false;
    } else {
        mvnoField.style.display = 'none'; // MVNO 필드 숨김
        mvnoField.disabled = true;
    }
}

function formatCurrency(value) {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function showLoadingMessage() {
    var loadingMessage = document.getElementById('loadingMessage');
    loadingMessage.style.display = 'block';
    loadingMessage.style.visibility = 'visible';
    loadingMessage.style.opacity = '1';
}

function hideLoadingMessage() {
    var loadingMessage = document.getElementById('loadingMessage');
    loadingMessage.style.opacity = '0';
    setTimeout(() => {
        loadingMessage.style.visibility = 'hidden';
        loadingMessage.style.display = 'none';
    }, 500); // 0.5초 뒤에 display를 none으로 설정
}

document.addEventListener('DOMContentLoaded', function() {
    var now = new Date();

    var year = now.getFullYear();  // 연도
	var shortYear = year.toString().slice(-2);
	var shortShortYear = year.toString().slice(-1);
    var month = ('0' + (now.getMonth() + 1)).slice(-2);  // 월 (0부터 시작하므로 +1)
    var day = ('0' + now.getDate()).slice(-2);  // 일

    document.getElementById('commonYear').value = year;
	document.getElementById('commonShortYear').value = shortYear;
	document.getElementById('commonShortShortYear').value = shortShortYear;
    document.getElementById('commonMonth').value = month;
    document.getElementById('commonDay').value = day;
});

function checkPassword() {
    var password = prompt("비밀번호를 입력하세요:");

    if (password === null || password === "") {
        alert("비밀번호를 입력하지 않았습니다.");
        return false;
    }
    if (password === "8789") {
        return true;
    } else {
        alert("비밀번호가 틀렸습니다.");
        return false;
    }
}

function openExternalSite(event) {
	event.preventDefault(); // 기본 링크 동작 방지
	var confirmMove = confirm("해당 사이트는 외부로 연결됩니다.\n계속하시겠습니까?");
	if (confirmMove) {
		window.open("https://smvno.sk7mobile.com/main.do", "_blank");
	}
}

window.onload = function() {
    // 숨겨진 필드 업데이트
    updateHiddenFields();
    // 고객 필드 초기화
    toggleCustomerFields();
};