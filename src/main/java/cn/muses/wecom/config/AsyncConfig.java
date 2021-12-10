/*
 * Copyright 2019 All rights reserved.
 */

package cn.muses.wecom.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AnnotationAsyncExecutionInterceptor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author jervis
 * @date 2021/1/12.
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {

    @Bean(name = AnnotationAsyncExecutionInterceptor.DEFAULT_TASK_EXECUTOR_BEAN_NAME)
    public TaskExecutor getDefaultTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(10);
        // 设置队列容量，缓冲1000个线程
        executor.setQueueCapacity(1000);
        // 设置线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(1000);
        // 核心线程不允许被回收
        executor.setAllowCoreThreadTimeOut(false);
        // 允许线程的空闲时间（秒），当核心线程外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称和分组
        executor.setThreadNamePrefix("Async.Thread-");
        executor.setThreadGroupName("Async");
        // 设置拒绝策略，这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池，然后继续销毁其他的Bean，这样这些异步任务的销毁就会先于异步线程池的销毁。
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        executor.setAwaitTerminationSeconds(30);

        return executor;
    }
}
