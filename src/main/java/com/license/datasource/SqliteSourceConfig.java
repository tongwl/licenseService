package com.license.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.mapper.sqlite", sqlSessionTemplateRef = "sqliteSqlSessionTemplate")
public class SqliteSourceConfig {
    @Bean(name="sqliteDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.sqlite")
    @Primary
    public DataSource sqliteDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "sqliteSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqliteSqlSessionFactory(@Qualifier("sqliteDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //设置mapper的位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/sqlite/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "sqliteTransactionManager")
    @Primary
    public DataSourceTransactionManager sqliteTransactionManager(@Qualifier("sqliteDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqliteSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqliteSqlSessionTemplate(@Qualifier("sqliteSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
