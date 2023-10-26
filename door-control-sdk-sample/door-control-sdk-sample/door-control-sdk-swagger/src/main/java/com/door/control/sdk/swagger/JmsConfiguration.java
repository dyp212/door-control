package com.door.control.sdk.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JmsConfiguration {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 ThreadPoolTaskExecutor taskExecutor = null;
	
	@PostConstruct
	public void initExecutor() {
		logger.info("初始化线程池");
		taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);//线程池大小
        taskExecutor.setMaxPoolSize(50);//线程池最大线程数
        taskExecutor.setKeepAliveSeconds(300);
        taskExecutor.setQueueCapacity(25);//最大等待任务数
        taskExecutor.initialize();
	}
	
	
	// topic模式的ListenerContainerÍ
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setPubSubDomain(true);
		bean.setTaskExecutor(taskExecutor);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}

	// queue模式的ListenerContainer
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setTaskExecutor(taskExecutor);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}
}
