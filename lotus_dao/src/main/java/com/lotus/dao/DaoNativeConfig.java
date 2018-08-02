package com.lotus.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.lotus.dao")
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = "com.lotus.dao.mapper")
@PropertySource("classpath:db.properties")
public class DaoNativeConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getRequiredProperty("jdbc.driver"));
        props.put("url", environment.getRequiredProperty("jdbc.url"));
        props.put("username", environment.getRequiredProperty("jdbc.username"));
        props.put("password", environment.getRequiredProperty("jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext ap) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.lotus");
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{pageInterceptor});
        //sessionFactory.setMapperLocations();
        return sessionFactory;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "jedisPool")
    public JedisPool jedispool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(30000); //  最大等待时间
        config.setMaxTotal(32);         //  最大连接数
        config.setMinIdle(6);           //  允许最小的空闲连接数
        config.setTestOnBorrow(false);  //  申请到连接时是否效验连接是否有效,对性能有影响,建议关闭
        config.setTestOnReturn(false);  //  使用完连接放回连接池时是否效验连接是否有效,对性能有影响,建议关闭
        config.setTestWhileIdle(true);  //  申请到连接时,如果空闲时间大于TimeBetweenEvictionRunsMillis时间,效验连接是否有效,建议开启,对性能有效不大
        config.setTimeBetweenEvictionRunsMillis(30000); //TestWhileIdle的判断依据
        return new JedisPool(config, environment.getRequiredProperty("redis.host"),
                environment.getRequiredProperty("redis.port", Integer.class),
                environment.getRequiredProperty("redis.timeout", Integer.class),
                environment.getRequiredProperty("redis.password"));
    }
}
