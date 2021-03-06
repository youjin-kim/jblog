package kr.co.itcen.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageConfig {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("kr/co/itcen/jblog/config/web/messages/messages_ko");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
