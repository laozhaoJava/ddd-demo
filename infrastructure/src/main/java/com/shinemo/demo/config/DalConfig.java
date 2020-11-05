package com.shinemo.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.shinemo.ds.ConfProxy;
import com.shinemo.ds.Database;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DalConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer config = new MapperScannerConfigurer();
        config.setBasePackage("com.shinemo.demo.mapper");
        config.setSqlSessionFactoryBeanName("mybatisSqlSessionFactory");
        return config;
    }

    @Primary
    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource(@Value("${datasource.url}") String url,@Value("${datasource.username}") String username,
                                      @Value("${datasource.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(50);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return dataSource;
    }

    @Primary
    @Bean(name = "mybatisSqlSessionFactory")
    @DependsOn("dataSource")
    public SqlSessionFactoryBean mybatisSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        return sqlSessionFactoryBean;
    }

    @Primary
    @Bean
    @DependsOn("mybatisSqlSessionFactory")
    public SqlSessionTemplate sqlSession(
            @Qualifier("mybatisSqlSessionFactory") SqlSessionFactoryBean mybatisSqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(mybatisSqlSessionFactory.getObject());
        return sqlSessionTemplate;
    }

    @Primary
    @Bean
    @DependsOn("dataSource")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

}