package com.renguangli.mamba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *  spring-boot start class
 *  @author renguangli 2018/1/19 20:15
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("com.renguangli.mamba.mapper")
public class MambaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MambaApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MambaApplication.class);
    }
}
