package com.debug.consumer;

import com.debug.consumer.thread.TestThread;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author lingting 2021/2/20 20:38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TestConsumer extends Thread implements InitializingBean {

	private final TestThread thread;

	@SneakyThrows
	@Override
	public void run() {
		System.out.println("kafka 消息处理");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

}
