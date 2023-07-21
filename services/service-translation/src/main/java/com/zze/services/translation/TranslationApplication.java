package com.zze.services.translation;

import live.lingting.component.security.annotation.EnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lingting 2023-04-24 13:48
 */
@EnableResourceServer
@SpringBootApplication
public class TranslationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslationApplication.class);
	}

}
