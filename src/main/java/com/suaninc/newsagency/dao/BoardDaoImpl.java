package com.suaninc.newsagency.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.suaninc.newsagency.domain.ResourcesBoard;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    private SqlSession sqlSession;

	@Override
	public Page<ResourcesBoard> selectBoardList(ResourcesBoard form, Pageable pageable) {
	    // 전체 레코드 수 가져오기
	    int total = sqlSession.selectOne("board.selectBoardListCount", form);
	    
	    // 페이징을 위한 파라미터 설정 (Map으로 묶음)
	    Map<String, Object> params = new HashMap<>();
	    params.put("form", form);
	    params.put("offset", pageable.getOffset());
	    params.put("pageSize", pageable.getPageSize());
	    
	    // 페이징된 리스트 가져오기
	    List<ResourcesBoard> boardList = sqlSession.selectList("board.selectBoardList", params);
	    
	    // Page 객체로 변환하여 반환
	    return new PageImpl<>(boardList, pageable, total);
	}

}
