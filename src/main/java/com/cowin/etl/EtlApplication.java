package com.cowin.etl;

import com.cowin.etl.cache.MetaCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EtlApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(EtlApplication.class, args);
		MetaCache metaCache = configurableApplicationContext.getBean(MetaCache.class);
		metaCache.load();
	}

}
