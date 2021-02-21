package com.debug.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lingting 2021/2/20 20:32
 */
@RequestMapping("feign/test")
@FeignClient(value = "test", contextId = "test", fallbackFactory = TestFeignFallbackFactory.class)
public interface TestFeign {

	@GetMapping("get")
	String test();

}

@Slf4j
@Component
class TestFeignFallbackFactory implements FallbackFactory<TestFeign> {

	@Override
	public TestFeign create(Throwable cause) {
		log.error("error",cause);
		return null;
	}

}