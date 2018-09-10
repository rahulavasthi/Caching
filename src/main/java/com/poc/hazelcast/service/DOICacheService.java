package com.poc.hazelcast.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "DOI-Cache-Hazelcast-Map")
public class DOICacheService {
	@CacheEvict(allEntries = true)
	public void clearCache() {}
	
	@Cacheable
	public String cacheInfo(String doiParam) {
		System.out.println("Cacheable method execution");
		return "Cached " + doiParam;
	}
}
