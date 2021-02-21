package com.debug.consumer.thread;

import com.debug.feign.TestFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lingting 2021/2/20 20:35
 */
@Slf4j
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class TestThread {

	private final TestFeign testFeign;

}
