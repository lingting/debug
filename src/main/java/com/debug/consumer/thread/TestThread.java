package com.debug.consumer.thread;

import com.debug.feign.TestFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lingting 2021/2/20 20:35
 */
@Slf4j
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class TestThread extends Thread implements InitializingBean {

	private final TestFeign testFeign;

	@Override
	public void run() {
		testFeign.test();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

}
