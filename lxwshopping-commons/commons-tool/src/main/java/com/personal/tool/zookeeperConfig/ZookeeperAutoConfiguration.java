package com.personal.tool.zookeeperConfig;

import org.apache.curator.framework.CuratorFrameworkFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2019/10/11/0011
 * Create by 刘仙伟
 */
@Configuration
@EnableConfigurationProperties(value=ZooKeeperClientProperties.class)
@ConditionalOnClass({CuratorFrameworkFactory.class})
public class ZookeeperAutoConfiguration {
    private static  final Logger logger= LoggerFactory.getLogger(CuratorFrameworkFactory.class);

    @Autowired
    private ZooKeeperClientProperties zooKeeperClientProperties;

    @Bean
    public CuratorFrameworkClient createCuratorFrameworkClient(){
        logger.info(zooKeeperClientProperties.toString());
        return  new CuratorFrameworkClient(zooKeeperClientProperties);
    }
}
