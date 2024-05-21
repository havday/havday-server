package com.wane.memberservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.wane.common", "com.wane.exception", "com.wane.util"})
public class CommonConfig {
}
