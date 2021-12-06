package wework.config;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jervis
 * @date 2021-11-30
 */
@ConfigurationProperties(prefix = "wechat.cp")
public class WxCpProperties {
    /**
     * 设置企业微信的corpId
     */
    private String corpId;

    /**
     * 企业微信应用配置
     */
    private List<AppConfig> appConfigs;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public List<AppConfig> getAppConfigs() {
        return appConfigs;
    }

    public void setAppConfigs(List<AppConfig> appConfigs) {
        this.appConfigs = appConfigs;
    }

    public static class AppConfig {
        /**
         * 设置企业微信应用的AgentId
         */
        private Integer agentId;

        /**
         * 设置企业微信应用的Secret
         */
        private String secret;

        /**
         * 设置企业微信应用的token
         */
        private String token;

        /**
         * 设置企业微信应用的EncodingAESKey
         */
        private String aesKey;

        public Integer getAgentId() {
            return agentId;
        }

        public void setAgentId(Integer agentId) {
            this.agentId = agentId;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAesKey() {
            return aesKey;
        }

        public void setAesKey(String aesKey) {
            this.aesKey = aesKey;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("corpId", corpId)
            .append("appConfigs", appConfigs)
            .toString();
    }
}
