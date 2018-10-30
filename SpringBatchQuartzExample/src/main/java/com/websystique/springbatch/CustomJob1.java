package com.websystique.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.websystique.db.AuthMapper;

public class CustomJob1 extends TemplateClass {

	private static final long serialVersionUID = 8603000272317907085L;
	
	static final Logger logger = LoggerFactory.getLogger("stdLogger");
	
	@Override
	public void performJob() {
		try {
			logger.debug("=====");
			logger.debug("performJob");
			
			AuthMapper authMapper = (AuthMapper) getMapper(AuthMapper.class);
			logger.debug(authMapper.getMmbInfoMmbId(null));
			
			logger.debug("=====");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
