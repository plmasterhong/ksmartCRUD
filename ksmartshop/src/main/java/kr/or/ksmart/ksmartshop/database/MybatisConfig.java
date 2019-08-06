package kr.or.ksmart.ksmartshop.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(value= {"kr.**.mapper"})
public class MybatisConfig{
	
   @Bean
   public SqlSessionFactory samsSqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
   		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:static/mapper/*.xml"));
		return sqlSessionFactoryBean.getObject(); 
   }
}
