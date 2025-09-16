package com.suaninc.newsagency.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.ResourcesBoard;

public interface BoardDao {

	Page<ResourcesBoard> selectBoardList(ResourcesBoard form, Pageable pageable);
	
}
