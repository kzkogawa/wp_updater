package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.WpPostmetaMapper;

@Component
public class DemoService implements IDemoService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WpPostmetaMapper postmetaMapper;
	
	@Transactional
	@Override
	public void log() {
		log.debug(postmetaMapper.selectByPrimaryKey(new Long(1)).toString());
	}

}
