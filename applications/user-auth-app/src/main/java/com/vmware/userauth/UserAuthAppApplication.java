package com.vmware.userauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.vmware.userauth.repository")
@EntityScan(basePackages = "com.vmware.userauth.model")
public class UserAuthAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthAppApplication.class, args);
	}

}
