package com.suaninc.newsagency.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourcesBoard {

    private Integer board_id;			// 게시글 ID
    private String category;			// 카테고리 (가입/변경/해지)
	private String carrier;				// 통신사 (SKT/KT/LGU+/알뜰폰 등)
	private String title;				// 제목
    private String content;				// 내용
    private Integer view_count;			// 조회수
	private Integer download_count;		// 다운로드수
    private String file_yn;				// 파일 첨부 여부 (Y/N)
	private String notice_yn;			// 공지사항 여부 (Y/N)
    private String lastUpdatedDatetime;	// 수정일
    private String registeredDatetime;	// 생성일
	    
}
