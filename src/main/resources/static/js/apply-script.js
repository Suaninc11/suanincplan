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
		
	let templateCoordinatesInput = document.getElementById('templateCoordinateData');
    let templateCoordinates = [];

    if (templateCoordinatesInput && templateCoordinatesInput.value) {
        try {
            templateCoordinates = JSON.parse(templateCoordinatesInput.value);
            // 숫자형 필드 정리
            templateCoordinates.forEach(c => {
                c.templateImageOrder = Number(c.templateImageOrder);
                c.fontSize = Number(c.fontSize) || 12;
                c.coordinateXAxis = Number(c.coordinateXAxis) || 0;
                c.coordinateYAxis = Number(c.coordinateYAxis) || 0;
            });
        } catch (e) {
            console.error("templateCoordinateData 파싱 실패:", e);
        }
    }

    console.log("이미지 좌표 파싱 결과:", JSON.stringify(templateCoordinates, null, 2));

    const imageContainers = document.querySelectorAll(".template-image-container");

    imageContainers.forEach((container, containerIndex) => {
        const img = container.querySelector("img");
        if (!img) return;

        // 이미지 순서를 data-order에서 가져오거나 index 기반으로 설정
        let imageOrder = parseInt(img.dataset.order);
        if (isNaN(imageOrder)) {
            imageOrder = containerIndex + 1; // 1부터 시작
            img.dataset.order = imageOrder;
        }

        console.log(`이미지 ${imageOrder}번 처리 중...`);

        // 해당 이미지 순서 좌표만 필터링
        const imageCoordinates = templateCoordinates.filter(
            c => c.templateImageOrder === imageOrder
        );

        console.log(`이미지 ${imageOrder}번의 좌표 개수:`, imageCoordinates.length);

        imageCoordinates.forEach((coordinate, coordIndex) => {
            const overlay = document.createElement('span');
            // commonCheck의 경우 고유한 ID 생성을 위해 coordIndex 추가
            const uniqueId = coordinate.templateCoordinateName === 'commonCheck' 
                ? `overlay_${coordinate.templateCoordinateName}_${coordinate.templateInputOption || 'default'}_${coordIndex}_${img.id || containerIndex}`
                : `overlay_${coordinate.templateCoordinateName}_${coordinate.templateInputOption || 'default'}_${img.id || containerIndex}`;
            
            overlay.id = uniqueId;
            overlay.className = 'overlay-text';
            overlay.style.position = 'absolute';
            overlay.style.display = 'none';
            overlay.style.pointerEvents = 'none';
            overlay.style.whiteSpace = 'pre';
            overlay.style.fontWeight = coordinate.fontStyle === 'bold' ? 'bold' : 'normal';

            // 데이터 속성
            overlay.dataset.imageId = img.id || `img_${containerIndex}`;
            overlay.dataset.inputOption = coordinate.templateInputOption || '';
            overlay.dataset.coordinateName = coordinate.templateCoordinateName;
            overlay.dataset.coordIndex = coordIndex; // commonCheck 구분을 위한 인덱스

            container.appendChild(overlay);
            console.log(`오버레이 생성: ${overlay.id}`);

            const input = document.getElementById(coordinate.templateCoordinateName);
            if (input) {
                console.log(`입력 필드 연결: ${coordinate.templateCoordinateName}`);

                input.addEventListener('input', () => {
                    updateOverlayPosition(img, overlay, input, coordinate, container);
                });

                if (coordinate.templateInputType === 'check') {
                    input.addEventListener('change', () => {
                        updateOverlayPosition(img, overlay, input, coordinate, container);
                    });
                }

                // commonCheck인 경우 초기에 체크 표시
                if (coordinate.templateCoordinateName === 'commonCheck') {
                    updateOverlayPosition(img, overlay, input, coordinate, container);
                } else if (input.value && input.value.trim() !== '') {
                    updateOverlayPosition(img, overlay, input, coordinate, container);
                }
            } else {
                console.warn(`입력 필드를 찾을 수 없음: ${coordinate.templateCoordinateName}`);
            }
        });

        // 이미지 로드 및 리사이즈 이벤트 처리
        const updateImageOverlays = () => {
            console.log(`이미지 ${imageOrder}번 오버레이 전체 업데이트`);
            updateAllOverlays(img, container);
        };
        
        if (img.complete && img.naturalWidth && img.naturalHeight) {
            console.log(`이미지 ${imageOrder}번 이미 로드됨 - 즉시 오버레이 업데이트`);
            updateImageOverlays();
        } else {
            console.log(`이미지 ${imageOrder}번 로드 대기 중`);
            img.addEventListener("load", updateImageOverlays);
        }
        window.addEventListener("resize", updateImageOverlays);
    });

    function updateOverlayPosition(img, overlay, input, coordinate, container) {
        let isVisible = false;

		if (coordinate.templateInputType === 'check') {
		    if (coordinate.templateCoordinateName === 'commonCheck') {
		        // commonCheck는 항상 체크 표시 (input이 null일 수 있음)
		        isVisible = true;
		        console.log(`commonCheck 체크마크 표시: ${overlay.id}`);
			} else if (input) {
			    // 일반 체크박스의 경우: input 값과 templateInputOption이 일치하는지 확인
			    const inputValue = input.value ? input.value.toString() : '';
			    const templateOption = coordinate.templateInputOption || '';
			    isVisible = inputValue === templateOption;
			    console.log(`체크박스 처리 - 필드: ${coordinate.templateCoordinateName}, 입력값: "${inputValue}", 옵션: "${templateOption}", 표시: ${isVisible}`);
			}
		} else {
            isVisible = input && input.value && input.value.trim() !== '';
        }

        overlay.style.display = isVisible ? 'block' : 'none';
        if (!isVisible) {
            console.log(`오버레이 숨김: ${overlay.id}`);
            return;
        }

        if (!img.naturalWidth || !img.naturalHeight) {
            console.warn("이미지가 아직 로드되지 않음");
            return;
        }

		// object-fit: contain 보정
		const scale = Math.min(
		    img.clientWidth / img.naturalWidth,
		    img.clientHeight / img.naturalHeight
		);

		const displayedWidth = img.naturalWidth * scale;
		const displayedHeight = img.naturalHeight * scale;

		const offsetX = (img.clientWidth - displayedWidth) / 2;
		const offsetY = (img.clientHeight - displayedHeight) / 2;

		const baseLeft = img.offsetLeft + offsetX;
		const baseTop = img.offsetTop + offsetY;

		const scaledX = coordinate.coordinateXAxis * scale;
		const scaledY = coordinate.coordinateYAxis * scale;

		const left = baseLeft + scaledX;
		let top = baseTop + scaledY;

		const scaledFont = Math.max(10, Math.round(coordinate.fontSize * scale));

		// 체크마크인 경우 Y 좌표 추가 조정 (백엔드와 맞추기 위해)
		if (coordinate.templateInputType === 'check') {
		    // 체크마크의 경우 Y 좌표를 아래로 더 조정
		    top = baseTop + scaledY + (scaledFont * 0.3); // 폰트 크기의 30% 정도 아래로
		} else {
			top = baseTop + scaledY - (scaledFont * 0.7); // 폰트 크기의 30% 정도 아래로
		}

		overlay.style.left = left + "px";
		overlay.style.top = top + "px";

		// 체크마크인 경우 중앙 정렬을 위한 transform 적용  
		if (coordinate.templateInputType === 'check') {
		    overlay.style.transform = 'translate(-50%, -50%)';
		    overlay.style.textAlign = 'center';
		    overlay.style.fontSize = '20px'; // 임시로 크게
		    overlay.textContent = '✔';
		} else {
		    overlay.style.transform = 'none';
		    overlay.style.textAlign = 'left';
		}

        // 폰트 스케일링
        overlay.style.fontSize = `${scaledFont}px`;
        overlay.style.lineHeight = `${Math.round(scaledFont * 1.1)}px`;

		if (coordinate.templateInputType !== 'check') {
		    overlay.textContent = input ? input.value : '';
		} else {
	        overlay.textContent = '✔';
	        console.log(`commonCheck 위치 설정: left=${left}, top=${top}, fontSize=${scaledFont}px`);
		}

    }

    function updateAllOverlays(img, container) {
        const imageOrder = Number(img.dataset.order || 1);
        const coordsForImage = templateCoordinates.filter(
            c => c.templateImageOrder === imageOrder
        );

        console.log(`updateAllOverlays: 이미지 ${imageOrder}번, 좌표 ${coordsForImage.length}개 처리`);

        coordsForImage.forEach((coord, index) => {
            // commonCheck 오버레이를 찾기 위한 더 정확한 선택자
            let overlay;
            if (coord.templateCoordinateName === 'commonCheck') {
                overlay = container.querySelector(
                    `.overlay-text[data-coordinate-name="${coord.templateCoordinateName}"][data-coord-index="${index}"]`
                );
            } else {
                overlay = container.querySelector(
                    `.overlay-text[data-coordinate-name="${coord.templateCoordinateName}"][data-input-option="${coord.templateInputOption || ''}"]`
                );
            }
            
            if (!overlay) {
                console.warn(`오버레이를 찾을 수 없음: ${coord.templateCoordinateName} (인덱스: ${index})`);
                return;
            }

            console.log(`처리 중 ${index + 1}/${coordsForImage.length}: ${coord.templateCoordinateName}`);
            
            // commonCheck는 입력 필드가 없어도 처리
            if (coord.templateCoordinateName === 'commonCheck') {
                console.log(`commonCheck 오버레이 업데이트: ${overlay.id}`);
                updateOverlayPosition(img, overlay, null, coord, container);
            } else {
                const input = document.getElementById(coord.templateCoordinateName);
                if (input) {
                    updateOverlayPosition(img, overlay, input, coord, container);
                } else {
                    console.warn(`입력 필드 없음: ${coord.templateCoordinateName}`);
                }
            }
        });
    }
	
	const currentPath = window.location.pathname;

	document.querySelectorAll("#layout-menu .menu-link").forEach(link => {
	    if (link.getAttribute("href") === currentPath) {
	        const menuItem = link.closest(".menu-item");
	        const isSubMenuItem = link.closest(".menu-sub");
	        
	        if (!isSubMenuItem) {
	            // 최상위 메뉴만 active 클래스 추가
	            menuItem.classList.add("active");
	            link.classList.add("active");
	        }
	        // 하위 메뉴 아이템은 active 클래스를 추가하지 않음
	        
	        // 상위 메뉴 열기
	        let parent = link.closest(".menu-item");
	        while (parent) {
	            parent.classList.add("open");
	            parent = parent.closest("ul.menu-sub")?.closest(".menu-item") || null;
	        }
	    }
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
		
		var catchCallPlus = document.getElementById("catchCallPlus").value;
		if (catchCallPlus == "catchCallPlus2") {
		    dataForm.append("catchCallPlusName", "캐치콜 플러스");
			dataForm.append("catchCallPlusAmt", "880");
		}
	} else if (templateName == "조이텔") {
		
		var depositorName = document.querySelector("input[name='depositorName']").value;
		var depositorBirthdate = document.querySelector("input[name='depositorBirthdate']").value;
		var depositorNumber = document.querySelector("input[name='depositorNumber']").value;
		var relationship = document.querySelector("input[name='relationship']").value;
		
		var mobileCarrier = document.querySelector("input[name='mobileCarrier']").value;
		var monthlyBillingAmt = document.querySelector("input[name='monthlyBillingAmt']").value;
		
		var mobileCarrierMonthlyBillingAmt = mobileCarrier + " (" + monthlyBillingAmt + "원)"; 
		dataForm.set("mobileCarrier", mobileCarrierMonthlyBillingAmt);

		if (depositorName || depositorBirthdate || depositorNumber || relationship) {
		    dataForm.append("depositorCheck", "depositorCheck");
		}
	}
	// 유모바일(청소년) 또는 유모바일(미디어로그) 조건 처리
	if (templateName === "유모바일(청소년)" || templateName === "유모바일(미디어로그)") {
	    var carrierPlan = document.getElementById("mobileCarrier").value;

	    const targetPlans = [
	        "LTE 스페셜 [약정형]",
	        "LTE (7GB+/통화기본) [약정형]",
	        "LTE (1GB+/통화기본) [약정형]",
			"LTE 스페셜 플러스 [약정형]",
			"LTE (15GB+/100분) [약정형]",
			"LTE (10GB+/통화기본) [약정형]"
	    ];

	    if (targetPlans.includes(carrierPlan)) {
			dataForm.append("penalty3Months", "29,700");
			dataForm.append("penalty9Months", "59,400");
			dataForm.append("discount12Months", "118,800");
			dataForm.append("discount12MonthsCheck", "discount12MonthsCheck");
			dataForm.append("label12Months", "12");
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
        if (preview) {
            // 모달 PDF 프리뷰
            const url = URL.createObjectURL(blob);
            const iframe = document.getElementById("pdfPreviewIframe");
            iframe.src = url;

            const modal = new bootstrap.Modal(document.getElementById('pdfPreviewModal'));
            modal.show();
        } else {
            // 기존 다운로드 로직
            var formattedDate = getFormattedDate();
            var templateName = document.getElementById('templateName').value;

            var a = document.createElement('a');
            a.style.display = 'none';
            a.href = URL.createObjectURL(blob);
            a.download = templateName + '_' + formattedDate + ".pdf";
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        }

        hideLoadingMessage(); // 로딩 메시지 숨기기
    })
    .catch(error => {
        console.error('Error downloading the document:', error);
        hideLoadingMessage();
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

function openExternalSite(event) {
	event.preventDefault(); // 기본 링크 동작 방지
	var confirmMove = confirm("해당 사이트는 외부로 연결됩니다.\n계속하시겠습니까?");
	if (confirmMove) {
		window.open("https://smvno.sk7mobile.com/main.do", "_blank");
	}
}

function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			document.getElementById("address").value = data.address;
			document.getElementById("addressDetail").focus();
		}
	}).open();
}

function execDaumPostcodeForCard() {
    new daum.Postcode({
        oncomplete: function(data) {
            let addr = data.roadAddress || data.jibunAddress;
            document.getElementById("cardAddress").value = addr;
            document.getElementById("cardAddressDetail").focus();
        }
    }).open();
}

function execDaumPostcodeForAccount() {
    new daum.Postcode({
        oncomplete: function(data) {
            let addr = data.roadAddress || data.jibunAddress;
            document.getElementById("accountAddress").value = addr;
            document.getElementById("accountAddressDetail").focus();
        }
    }).open();
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

window.onload = function() {
    // 숨겨진 필드 업데이트
    updateHiddenFields();
    // 고객 필드 초기화
    toggleCustomerFields();
};