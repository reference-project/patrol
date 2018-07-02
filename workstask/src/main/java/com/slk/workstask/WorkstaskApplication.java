package com.slk.workstask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import tk.mybatis.spring.annotation.MapperScan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@MapperScan(basePackages = "com.slk.workstask.mapper")
public class WorkstaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkstaskApplication.class, args);
	}


	/**
	 *
	 * 文件上传配置 设置上传文件最大字节
	 * Create by song-xl on 2018-04-13 02:47:49
	 */
	@Bean
	EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
		return (ConfigurableEmbeddedServletContainer container) -> {
			container.setSessionTimeout(60*60*24);
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


	@Bean
	public Converter<String, Date> addNewConvert() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//
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
