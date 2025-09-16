package com.suaninc.newsagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suaninc.newsagency.dao.BoardDao;
import com.suaninc.newsagency.domain.ResourcesBoard;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
    private BoardDao boardDao;

    @Override
    public Page<ResourcesBoard> getBoardList(ResourcesBoard form, Pageable pageable) {
        return boardDao.selectBoardList(form, pageable);
    }
    
}
