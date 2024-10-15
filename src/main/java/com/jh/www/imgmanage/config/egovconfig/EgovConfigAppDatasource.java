package com.jh.www.imgmanage.config.egovconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(value="com.jh.www.imgmanage", sqlSessionFactoryRef="sqlSession")
public class EgovConfigAppDatasource {

    @Primary
    @Bean(name="datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = {"sqlSession", "egov.sqlSession"})
    public SqlSessionFactoryBean sqlSession(@Qualifier("datasource") DataSource dataSource, ApplicationContext applicationContext) throws IOException {


        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.jh.www.imgmanage.domain");
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*/*_mapper.xml"));

        return factoryBean;
    }

    @Primary
    @Bean
    public SqlSessionTemplate egovSqlSessionTemplate(@Qualifier("sqlSession") SqlSessionFactory sqlSession) {
        return new SqlSessionTemplate(sqlSession);
    }
}
