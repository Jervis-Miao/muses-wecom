/*
 * Copyright 2021 XYZ Co., Ltd. All Rights Reserved
 */
package cn.muses.wecom.builder;

import java.util.Arrays;
import java.util.Random;

import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.taskcard.TaskCardButton;

/**
 * @author miaoqiang
 * @date 2021/12/10.
 */
public class WxCpMessageBuilder {

    /**
     * 文本消息
     *
     * @return
     */
    public static WxCpMessage buildText() {
        return WxCpMessage.TEXT().toParty("2").content("秒杀客户将在5分钟后开始").build();
    }

    /**
     * 图文消息
     *
     * @return
     */
    public static WxCpMessage buildNews() {
        return WxCpMessage.NEWS().toParty("2")
            .addArticle(NewArticle.builder().title("中秋节礼品领取").description("今年中秋节公司有豪礼相送").url("https://www.xyz.cn")
                .picUrl("http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png")
                .btnText("更多").build())
            .build();
    }

    /**
     * 任务卡片消息
     *
     * @return
     */
    public static WxCpMessage buildTaskCard() {
        final WxCpMessage message = WxCpMessage.TASKCARD()
            .taskId(String.valueOf(System.currentTimeMillis()))
            .toParty("2")
            .title("领奖通知")
            .description(
                "恭喜你获得iPhone 13抽奖机会一次，抽奖码：" + (new Random().nextInt(999999) % 900000 + 100000)
                    + "\n请于2021年12月12日前点击领奖按钮抽取！")
            .url("https://www.xyz.cn")
            .buttons(Arrays.asList(TaskCardButton.builder()
                .key("111")
                .color("orange")
                .name("点击领奖")
                .bold(Boolean.TRUE)
                .build(),
                TaskCardButton.builder()
                    .key("222")
                    .color("orange")
                    .name("忍痛割爱")
                    .bold(Boolean.TRUE)
                    .build()))
            .build();
        return message;
    }
}
