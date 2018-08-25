package com.renguangli.mamba.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.renguangli.mamba.entity.FastMail;

/**
 * @author renguangli
 * @since jdk1.8
 */
public interface FastMailMapper {
	
	@Select("select id,name,code from mamba_fast_mail")
	@Options(useCache = true, timeout = 120)
	List<FastMail> listFastMail();
}
