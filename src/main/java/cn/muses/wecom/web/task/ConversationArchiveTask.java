package cn.muses.wecom.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
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
