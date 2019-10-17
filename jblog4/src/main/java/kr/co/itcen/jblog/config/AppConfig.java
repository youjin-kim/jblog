package kr.co.itcen.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import kr.co.itcen.config.app.DBConfig;
import kr.co.itcen.config.app.MybatisConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"kr.co.itcen.jblog.service","kr.co.itcen.jblog.repository","kr.co.itcen.jblog.aspect"})
@Import({DBConfig.class, MybatisConfig.class})
public class AppConfig {

}
