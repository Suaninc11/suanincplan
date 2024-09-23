package com.suaninc.newsagency.domain;

import java.util.Date;

public class CommonCode {
	
    private Integer codeId;
    private String codeValue;
    private String codeName;
    private String codeDescription;
    private Integer parentCodeId;
    private String registeredDatetime;
    

    public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }
    
	public Integer getParentCodeId() {
		return parentCodeId;
	}

	public void setParentCodeId(Integer parentCodeId) {
		this.parentCodeId = parentCodeId;
	}

	public String getRegisteredDatetime() {
		return registeredDatetime;
	}

	public void setRegisteredDatetime(String registeredDatetime) {
		this.registeredDatetime = registeredDatetime;
	}
	
}