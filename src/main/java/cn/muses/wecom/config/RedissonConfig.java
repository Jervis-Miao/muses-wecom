package cn.muses.wecom.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.FstCodec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SubscriptionMode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Khan
 */
@Configuration
public class RedissonConfig {

    @Value("${redis.sentinel.ser1.host:}")
    private String ser1Host;
    @Value("${redis.sentinel.ser2.host}")
    private String ser2Host;
    @Value("${redis.sentinel.ser3.host}")
    private String ser3Host;
    @Value("${redis.sentinel.ser1.port}")
    private String ser1Port;
    @Value("${redis.sentinel.ser2.port}")
    private String ser2Port;
    @Value("${redis.sentinel.ser3.port}")
    private String ser3Port;
    @Value("${redis.sentinel.lowRel.masterName}")
    private String lowRelMasterName;

    @Value("${redis.sentinel.default.readMode}")
    private ReadMode readMode;
    @Value("${redis.sentinel.default.subscriptionMode}")
    private SubscriptionMode subscriptionMode;
    @Value("${redis.sentinel.default.subscriptionConnectionMinimumIdleSize}")
    private int subscriptionConnectionMinimumIdleSize;
    @Value("${redis.sentinel.default.subscriptionConnectionPoolSize}")
    private int subscriptionConnectionPoolSize;
    @Value("${redis.sentinel.default.slaveConnectionMinimumIdleSize}")
    private int slaveConnectionMinimumIdleSize;
    @Value("${redis.sentinel.default.slaveConnectionPoolSize}")
    private int slaveConnectionPoolSize;
    @Value("${redis.sentinel.default.masterConnectionMinimumIdleSize}")
    private int masterConnectionMinimumIdleSize;
    @Value("${redis.sentinel.default.masterConnectionPoolSize}")
    private int masterConnectionPoolSize;
    @Value("${redis.sentinel.default.idleConnectionTimeout}")
    private int idleConnectionTimeout;
    @Value("${redis.sentinel.default.connectTimeout}")
    private int connectTimeout;
    @Value("${redis.sentinel.default.timeout}")
    private int timeout;
    @Value("${redis.sentinel.default.retryAttempts}")
    private int retryAttempts;
    @Value("${redis.sentinel.default.retryInterval}")
    private int retryInterval;
    @Value("${redis.sentinel.default.failedSlaveReconnectionInterval}")
    private int failedSlaveReconnectionInterval;
    @Value("${redis.sentinel.default.failedSlaveCheckInterval}")
    private int failedSlaveCheckInterval;
    @Value("${redis.sentinel.default.database}")
    private int database;
    @Value("${redis.sentinel.default.subscriptionsPerConnection}")
    private int subscriptionsPerConnection;

    @Bean("fstCodec")
    public FstCodec fstCodec() {
        return new FstCodec();
    }

    @Bean("jsonJacksonCodec")
    public JsonJacksonCodec jsonJacksonCodec() {
        return new JsonJacksonCodec();
    }

    @Bean("jdkCodec")
    public SerializationCodec jdkCodec() {
        return new SerializationCodec();
    }

    @Bean("StringCodec")
    public StringCodec stringCodec() {
        return new StringCodec();
    }

    @Bean("redissonClient")
    public RedissonClient initRedisson(@Qualifier("fstCodec") Codec codec) {
        Config config = new Config();
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
        sentinelServersConfig.setMasterName(lowRelMasterName).addSentinelAddress("redis://" + ser1Host + ":" + ser1Port,
            "redis://" + ser2Host + ":" + ser2Port, "redis://" + ser3Host + ":" + ser3Port);
        sentinelServersConfig.setReadMode(readMode);
        sentinelServersConfig.setSubscriptionMode(subscriptionMode);
        sentinelServersConfig.setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize);
        sentinelServersConfig.setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize);
        sentinelServersConfig.setSlaveConnectionMinimumIdleSize(slaveConnectionMinimumIdleSize);
        sentinelServersConfig.setSlaveConnectionPoolSize(slaveConnectionPoolSize);
        sentinelServersConfig.setMasterConnectionMinimumIdleSize(masterConnectionMinimumIdleSize);
        sentinelServersConfig.setMasterConnectionPoolSize(masterConnectionPoolSize);
        sentinelServersConfig.setIdleConnectionTimeout(idleConnectionTimeout);
        sentinelServersConfig.setConnectTimeout(connectTimeout);
        sentinelServersConfig.setTimeout(timeout);
        sentinelServersConfig.setRetryAttempts(retryAttempts);
        sentinelServersConfig.setRetryInterval(retryInterval);
        sentinelServersConfig.setFailedSlaveReconnectionInterval(failedSlaveReconnectionInterval);
        sentinelServersConfig.setFailedSlaveCheckInterval(failedSlaveCheckInterval);
        sentinelServersConfig.setDatabase(database);
        sentinelServersConfig.setSubscriptionsPerConnection(subscriptionsPerConnection);
        // 序列化方式
        config.setCodec(codec);
        return Redisson.create(config);
    }
}
