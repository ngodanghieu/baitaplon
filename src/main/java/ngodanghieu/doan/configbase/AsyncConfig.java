package ngodanghieu.doan.configbase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

  private Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

  @Bean("threadCallService")
  public TaskExecutor getAsyncThread4() {
    logger.info("*** init threadCallService size: {}", 3);
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setThreadNamePrefix("CallService-");
    return executor;
  }
  

}
