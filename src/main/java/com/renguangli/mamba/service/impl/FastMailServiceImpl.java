package com.renguangli.mamba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.renguangli.mamba.entity.FastMail;
import com.renguangli.mamba.mapper.FastMailMapper;
import com.renguangli.mamba.service.FastMailService;

/**
 * @author renguangli
 * @since jdk1.8
 */
@Service
public class FastMailServiceImpl implements FastMailService{
	
	private FastMailMapper fastMailMapper;

	/* (non-Javadoc)
	 * @see com.renguangli.mamba.service.FastMailService#listFastMail()
	 */
	@Override
	public List<FastMail> listFastMail() {
		return fastMailMapper.listFastMail();
	}

}
