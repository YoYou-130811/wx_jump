package com.ball.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "com.ball.wx.mapper")
@EnableScheduling
public class WxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxApplication.class, args);
	}
}
