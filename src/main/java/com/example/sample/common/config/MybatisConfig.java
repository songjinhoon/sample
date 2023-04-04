package com.example.sample.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MybatisConfig {

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

        Resource[] mapperResources = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**");
        Resource configResource = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml");

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(configResource);
        sessionFactoryBean.setMapperLocations(mapperResources);

        return sessionFactoryBean.getObject();
    }

}
