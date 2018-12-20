package io.it.sc.ribbonapp;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonAppApplication {

	@Inject
	RestTemplate restTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(RibbonAppApplication.class, args);
	}

	@GetMapping("")
	public String getData() {
		return restTemplate.getForEntity("http://service", String.class).getBody();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
}

