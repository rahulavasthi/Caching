package com.poc.hazelcast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.config.MultiMapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelCastConfig {
	@Bean
    public Config hazelCastConfigBean(){
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		MapConfig mapConfig = new MapConfig("DOI-Cache-Hazelcast-Map")
				.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                .setEvictionPolicy(EvictionPolicy.LRU)
                .setTimeToLiveSeconds(-1);		//Cache records will never expired;
		instance.getConfig().addMapConfig(mapConfig);
		
		MultiMapConfig multiMapConfig= new MultiMapConfig("DOI-Cache-Hazelcast-MultiMap");
		instance.getConfig().addMultiMapConfig(multiMapConfig);
		
		MapConfig userConfig = new MapConfig("user")
				.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                .setEvictionPolicy(EvictionPolicy.LRU)
                .setTimeToLiveSeconds(-1);		//Cache records will never expired;
		
		instance.getConfig().addMapConfig(userConfig);
		
		return instance.getConfig();
		
    }
	
	/*@Bean
    public Config hazelCastConfigBean(){
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("DOI-Cache-Hazelcast-Map")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(200));
    }*/
}
