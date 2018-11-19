package com.qk365.collector.web.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.ReadMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.redisson")
@PropertySource("classpath:redisson.properties")
@Configuration
public class RedissonConfig{

    private String masterAddress;
    private String slaveAddresses;
    private ReadMode readMode;
    private String password;
    private int database;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws Exception {
        Config config = new Config();
        config.useMasterSlaveServers().setMasterAddress(masterAddress)
                .addSlaveAddress(slaveAddresses)
                .setReadMode(readMode)
                .setDatabase(database)
                .setPassword(password);
        return Redisson.create(config);

    }

    public String getMasterAddress() {
        return masterAddress;
    }

    public void setMasterAddress(String masterAddress) {
        this.masterAddress = masterAddress;
    }

    public String getSlaveAddresses() {
        return slaveAddresses;
    }

    public void setSlaveAddresses(String slaveAddresses) {
        this.slaveAddresses = slaveAddresses;
    }

    public ReadMode getReadMode() {
        return readMode;
    }

    public void setReadMode(ReadMode readMode) {
        this.readMode = readMode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }
}
