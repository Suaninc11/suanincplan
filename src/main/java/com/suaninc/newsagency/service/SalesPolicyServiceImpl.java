package com.suaninc.newsagency.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.suaninc.newsagency.dao.SalesPolicyDao;
import com.suaninc.newsagency.domain.SalesPolicy;

@Service
public class SalesPolicyServiceImpl implements SalesPolicyService {
	
    @Autowired
    private SalesPolicyDao salesPolicyDao;

    @Override
    public List<String> getDistinctContractPeriods() {
        return salesPolicyDao.selectDistinctContractPeriods();
    }

    @Override
    public List<String> getDistinctActivationTypes() {
        return salesPolicyDao.selectDistinctActivationTypes();
    }

    @Override
    public List<String> getDistinctProductNames() {
        return salesPolicyDao.selectDistinctProductNames();
    }

    @Override
    public List<String> getDistinctPlanNames() {
        return salesPolicyDao.selectDistinctPlanNames();
    }

    @Override
    public List<String> getActivationTypesByContractPeriod(String contractPeriod) {
        return salesPolicyDao.selectActivationTypesByContractPeriod(contractPeriod);
    }

    @Override
    public List<String> getProductNamesByConditions(SalesPolicy form) {
        return salesPolicyDao.selectProductNamesByConditions(form);
    }

    @Override
    public List<String> getPlanNamesByConditions(SalesPolicy form) {
        return salesPolicyDao.selectPlanNamesByConditions(form);
    }
    
    @Override
    public SalesPolicy getPlanDetail(SalesPolicy form) {
        return salesPolicyDao.selectPlanDetail(form);
    }

    @Override
    public SalesPolicy getUrlPathByConditions(SalesPolicy form) {
        return salesPolicyDao.selectUrlPathByConditions(form);
    }
    
    @Override
    public Page<SalesPolicy> getSalesPolicyList(SalesPolicy form, Pageable pageable) {
        return salesPolicyDao.selectSalesPolicyList(form, pageable);
    }
    
    @Override
    public void uploadSalesPolicyExcel(MultipartFile file) throws IOException {
        List<SalesPolicy> policyList = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // 0번째는 헤더
            Row row = sheet.getRow(i);
            if (row == null) continue;

            SalesPolicy policy = new SalesPolicy();
            policy.setSalesPolicyName(getString(row.getCell(0)));         // 1. 판매정책명
            policy.setAgencyName(getString(row.getCell(1)));              // 2. 대리점명
            policy.setStartDatetime(getString(row.getCell(2)));           // 3. 시작일시
            policy.setEndDatetime(getString(row.getCell(3)));             // 4. 종료일시
            policy.setProductCode(getString(row.getCell(4)));             // 5. 단말코드
            policy.setProductName(getString(row.getCell(5)));             // 6. 단말명
            policy.setIsUsedProduct(getString(row.getCell(6)));           // 7. 중고여부
            policy.setPlanName(getString(row.getCell(7)));                // 8. 요금제명
            policy.setContractPeriod(getString(row.getCell(8)));          // 9. 약정기간
            policy.setActivationType(getString(row.getCell(9)));          // 10. 개통유형
            policy.setSubsidyType(getString(row.getCell(10)));            // 11. 지원금 구분
            policy.setBaseAmt(getInteger(row.getCell(11)));               // 12. 기본료
            policy.setDiscountAmt(getInteger(row.getCell(12)));           // 13. 요금할인
            policy.setAdditionalDiscountAmt(getInteger(row.getCell(13))); // 14. 추가할인
            policy.setDeviceAmt(getInteger(row.getCell(14)));             // 15. 단말출고가
            policy.setOfficialSubsidy(getInteger(row.getCell(15)));       // 16. 공시지원금
            policy.setAgencySubsidy(getInteger(row.getCell(16)));         // 17. 대리점지원금
            policy.setInstallmentPrincipal(getInteger(row.getCell(17)));  // 18. 할부원금
            policy.setInstallmentFee(getInteger(row.getCell(18)));        // 19. 할부이자
            policy.setNewJoinCommission(getInteger(row.getCell(19)));     // 20. 신규 수수료
            policy.setMovingCommission(getInteger(row.getCell(20)));      // 21. 번호이동 수수료
            policy.setChangeCommission(getInteger(row.getCell(21)));      // 22. 기기변경 수수료
            policy.setClassification(getString(row.getCell(22)));         // 23. 분류
            policy.setUrlPath(getString(row.getCell(23)));                // 24. 웹 URL
            policy.setMobileUrlPath(getString(row.getCell(24)));          // 25. 모바일 URL

            policyList.add(policy);
        }

        workbook.close();
        salesPolicyDao.insertBulkSalesPolicy(policyList);
    }

    private String getString(Cell cell) {
        return (cell == null) ? "" : cell.toString().trim();
    }
    
    private Integer getInteger(Cell cell) {
        if (cell == null) return null;
        try {
            return (int) cell.getNumericCellValue();
        } catch (Exception e) {
            try {
                return Integer.parseInt(cell.toString().trim());
            } catch (NumberFormatException ex) {
                return null;
            }
        }
    }
    
	@Override
	public SalesPolicy getSalePolicyInfo(String id) {
		return salesPolicyDao.selectSalePolicyInfo(id);
	}
    
}
