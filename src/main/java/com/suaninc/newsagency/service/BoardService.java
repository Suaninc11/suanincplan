package com.suaninc.newsagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.ResourcesBoard;

public interface BoardService {

	public Page<ResourcesBoard> getBoardList(ResourcesBoard form, Pageable pageable);
	
}
