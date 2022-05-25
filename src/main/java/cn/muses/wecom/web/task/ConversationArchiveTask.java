package cn.muses.wecom.web.task;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tencent.wework.FinanceService;

/**
 * @author jervis
 * @description 会话存档定时任务
 * @date 2021/7/26 15:50
 **/
@Component
public class ConversationArchiveTask {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinanceService financeService;

    /** 促销规则缓存 **/
    protected final LoadingCache<Long, String> promotionCache;

    @Autowired
    public ConversationArchiveTask() {
        this.promotionCache =
            Caffeine.newBuilder().maximumSize(10).expireAfterAccess(5, TimeUnit.MINUTES).build((a) -> "sss");
    }

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void FinanceTask() {
        logger.info("会话存档定时任务执行----------------->");
        Long seqLong = 0L;
        financeService.getChatData(seqLong, (data) -> this.saveData(data));
        logger.info("会话存档定时任务执行完成----------------->");
    }

    private void saveData(JSONObject json) {
        System.out.println(json);
    }
}
