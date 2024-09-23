package com.suaninc.newsagency.domain;

<<<<<<< HEAD
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

=======
public class CommonCode {
    private String codeValue;
    private String codeName;
    private String codeDescription;

    // Getters and Setters
>>>>>>> 6d971a4cc491035c9e57842d3fa3e8643b8f5651
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
<<<<<<< HEAD
    
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
	
=======
>>>>>>> 6d971a4cc491035c9e57842d3fa3e8643b8f5651
}