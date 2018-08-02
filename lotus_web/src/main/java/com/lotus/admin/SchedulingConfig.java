package com.lotus.admin;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = {"com.lotus.admin.schedul"})
@EnableScheduling
public class SchedulingConfig {

}
