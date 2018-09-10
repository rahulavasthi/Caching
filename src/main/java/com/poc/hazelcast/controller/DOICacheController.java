package com.poc.hazelcast.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;
import com.poc.hazelcast.model.User;
import com.poc.hazelcast.service.DOICacheService;
import com.poc.hazelcast.service.UserService;

@RestController
public class DOICacheController {
	
	@Autowired
    private HazelcastInstance instance;  
	
	@Autowired
    private DOICacheService cacheService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/writeCache")
	public String writeCache(@RequestParam("values")String cacheValues) {
        Map<String,String> cacheMap = instance.getMap("DOI-Cache-Hazelcast-Map");    
        cacheMap.put("data",cacheValues);                 
        return "Value has been written to Hazelcast cache";
    }
	@GetMapping("/readCache")
    public String readCache() {
		Map<String,String> cacheMap = instance.getMap("DOI-Cache-Hazelcast-Map");
        System.out.println(cacheMap.size()+ " " + cacheMap.keySet());
        return "Hazelcast cached values are :" +cacheMap.get("data");                    
    }
	
	@GetMapping("/writeMultiCache")
	public String writeMultiCache(@RequestParam("values")String cacheValues) {
        MultiMap<String,String> cacheMap = instance.getMultiMap("DOI-Cache-Hazelcast-MultiMap");    
        cacheMap.put("data",cacheValues);                 
        return "Value has been written to Hazelcast cache";
    }
	@GetMapping("/readMultiCache")
    public String readMultiCache() {
		MultiMap<String,String> cacheMap = instance.getMultiMap("DOI-Cache-Hazelcast-MultiMap");
        System.out.println(cacheMap.size() + " " + cacheMap.keySet());
        return "Hazelcast cached values are :" +cacheMap.get("data");                    
    }
	
	@GetMapping("/cacheMethodReturn")
	public String cacheMethodReturn(@RequestParam("values")String cacheValues) {
		cacheService.cacheInfo(cacheValues);                
        return "Value has been written to Hazelcast cache";
    }
	
	@GetMapping("/readCacheMethodReturn")
	public List<String> readCacheMethodReturn() {
		Map<String,String> cacheMap = instance.getMap("DOI-Cache-Hazelcast-Map");
        System.out.println(cacheMap.size()+ " " + cacheMap.keySet());
        List<String> cachedValues= new ArrayList<>();
        cacheMap.keySet().forEach(key -> cachedValues.add(cacheMap.get(key)));
        return cachedValues;
    }
	
	@GetMapping("/clearCache")
	public String clearChache() {
		cacheService.clearCache();   
		userService.clearCache();   
        return "Cache cleared";
    }
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		System.out.println(System.currentTimeMillis());
		List<User> users= userService.getAllUser();
		System.out.println(System.currentTimeMillis());
        return users;
    }

}
