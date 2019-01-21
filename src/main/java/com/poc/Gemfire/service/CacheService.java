package com.poc.Gemfire.service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.Gemfire.model.Customer;

@Service
public class CacheService {

	protected static final String CACHE_SERVICE_URL = "http://localhost:8081/api/{id}";

	private volatile boolean cacheMiss = false;

	private final RestTemplate quoteServiceTemplate = new RestTemplate();

	public boolean isCacheMiss() {
		boolean cacheMiss = this.cacheMiss;
		this.cacheMiss = false;
		return cacheMiss;
	}

	protected void setCacheMiss() {
		this.cacheMiss = true;
	}

	@Cacheable("customer")
	public String requestCustomer(Long id) {
		setCacheMiss();
		return requestCustomer(CACHE_SERVICE_URL, Collections.singletonMap("id", id));
	}

	@CachePut(cacheNames = "Quotes", key = "#result.id")
	public String requestRandomCustomer() {
		setCacheMiss();
		return requestCustomer(CACHE_SERVICE_URL);
	}

	protected String requestCustomer(String URL) {
		return requestCustomer(URL, Collections.emptyMap());
	}

	protected String requestCustomer(String URL, Map<String, Object> urlVariables) {

		return Optional.ofNullable(this.quoteServiceTemplate.getForObject(URL, Customer.class, urlVariables))
			.map(Customer::getFirstname)
			.orElse(null);
	}
}
