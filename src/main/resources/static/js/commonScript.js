function applyFormDownload(preview = false) {
    var formElement = document.getElementById('dataForm');
    var dataForm = new FormData(formElement);

    var formattedDate = getFormattedDate();
    var templateName = document.getElementById('templateName');

    fetch('/homepage/inscribeView/autoComplete', {
        method: 'POST',
        body: dataForm
    })
    .then(response => response.blob())
    .then(blob => {
        const url = window.URL.createObjectURL(blob);

        if (preview) {
            const pdfWindow = window.open("", "PDF Preview", "width=800,height=600");
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
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = templateName + '_' + formattedDate;
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
        }
    })
    .catch(error => console.error('Error downloading the document:', error));
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
    const selectElement = document.getElementById('automaticTransferSelect');
    const cardForm = document.getElementById('cardForm');
    const accountForm = document.getElementById('accountForm');

    const isCard = selectElement.value === 'card';

    cardForm.style.display = isCard ? 'block' : 'none';
    accountForm.style.display = isCard ? 'none' : 'block';

    toggleFormFields(cardForm, isCard);
    toggleFormFields(accountForm, !isCard);
}

function toggleFormFields(form, enable) {
    const inputs = form.querySelectorAll('input, select');
    inputs.forEach(input => {
        input.disabled = !enable;  // 활성화/비활성화 처리
    });
}

function toggleCustomerForm() {
    var selectElement = document.getElementById('openingCategorySelect');
    var newCustomerForm = document.getElementById('newCustomerForm');
    var moveCustomerForm = document.getElementById('moveCustomerForm');

    var isNewCustomer = selectElement.value === 'new';

    newCustomerForm.style.display = isNewCustomer ? 'block' : 'none';
    moveCustomerForm.style.display = isNewCustomer ? 'none' : 'block';

    toggleFormFields(newCustomerForm, isNewCustomer);
    toggleFormFields(moveCustomerForm, !isNewCustomer);
}

function toggleFormFields(form, enable) {
    const inputs = form.querySelectorAll('input, select, textarea');
    inputs.forEach(input => {
        input.disabled = !enable;  // 활성화/비활성화 처리
    });
}


function toggleMVNOField() {
    var selectElement = document.getElementById('previousCarrierSelect');
    var eeeeField = document.getElementById('mvno');

    if (selectElement.value === 'previousCarrier4') {
        eeeeField.style.display = 'block';
        eeeeField.disabled = false;
    } else {
        eeeeField.style.display = 'none';
        eeeeField.disabled = true;
    }
}

function formatCurrency(value) {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

document.addEventListener('DOMContentLoaded', function() {
    var now = new Date();

    var year = now.getFullYear();  // 연도
	var shortYear = year.toString().slice(-2);
    var month = ('0' + (now.getMonth() + 1)).slice(-2);  // 월 (0부터 시작하므로 +1)
    var day = ('0' + now.getDate()).slice(-2);  // 일

    document.getElementById('commonYear').value = year;
	document.getElementById('commonShortYear').value = shortYear;
    document.getElementById('commonMonth').value = month;
    document.getElementById('commonDay').value = day;
});

window.onload = function() {
    document.getElementById('automaticTransferSelect').dispatchEvent(new Event('change'));
	document.getElementById('openingCategorySelect').dispatchEvent(new Event('change'));
    updateHiddenFields();
}