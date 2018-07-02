package com.slk.patrol;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import tk.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@SpringBootApplication
@MapperScan(basePackages = "com.slk.patrol.mapper.*")
public class InspectionApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(InspectionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InspectionApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		logger.info("服务启动完成!");
	}


	/**
	 *
	 * 文件上传配置 设置上传文件最大字节
	 * Create by song-xl on 2018-04-13 02:47:49
	 */
	@Bean
	EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
		return (ConfigurableEmbeddedServletContainer container) -> {
			if (container instanceof TomcatEmbeddedServletContainerFactory) {
				TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
				tomcat.addConnectorCustomizers(
						(connector) -> {
							connector.setMaxPostSize(10000000); // 10 MB
						}
				);
			}
		};
	}


	/**
	 *
	 * 规范日期格式
	 * Create by song-xl on 2018-05-16 05:27:45
	 */
	@Bean
	public Converter<String, Date> addNewConvert() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
				Date date = null;
				try {
					date = sdf.parse((String) source);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}

}
