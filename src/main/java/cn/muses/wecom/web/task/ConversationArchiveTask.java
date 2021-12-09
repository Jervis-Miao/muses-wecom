package cn.muses.wecom.web.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wework.FinanceService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jervis
 * @description 会话存档定时任务
 * @date 2021/7/26 15:50
 **/
@Slf4j
@Component("conversationArchiveTask")
public class ConversationArchiveTask {

    @Autowired
    private FinanceService financeService;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void FinanceTask() {
        log.info("会话存档定时任务执行----------------->");
        Long seqLong = 0L;
        financeService.getChatData(seqLong, (data) -> this.saveData(data));
        log.info("会话存档定时任务执行完成----------------->");
    }

    private void saveData(JSONObject json) {
        System.out.println(json);
    }
}
