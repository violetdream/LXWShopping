package com.personal.tool.zookeeperConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 2019/10/11/0011
 * Create by 刘仙伟
 */
@ConfigurationProperties(prefix = "lock.zookeeper",ignoreInvalidFields = false)
@Data
public class ZooKeeperClientProperties {
    private String zkHosts;
    // session超时

    private int sessionTimeout = 30000;
    private int connectionTimeout = 30000;
    // 共享一个zk链接
    private boolean singleton = true;
    // 全局path前缀,常用来区分不同的应用
    private String namespace;

    @Override
    public String toString() {
        return "ZookeeperClientProperties{" +
                "zkHosts='" + zkHosts + '\'' +
                ", sessionTimeout=" + sessionTimeout +
                ", connectionTimeout=" + connectionTimeout +
                ", singleton=" + singleton +
                ", namespace='" + namespace + '\'' +
                '}';
    }
}
