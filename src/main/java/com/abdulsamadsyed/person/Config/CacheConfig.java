package com.abdulsamadsyed.person.Config;


import com.abdulsamadsyed.person.model.Person;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;

import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;

import org.springframework.cache.annotation.EnableCaching;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {
    public final static String PERSON_CACHE = "personCache";

    @Bean
    CacheManager myCacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();

        CacheConfiguration configuration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                Long.class, Person.class,ResourcePoolsBuilder
                        .newResourcePoolsBuilder().offheap(1, MemoryUnit.MB))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20))).build();

        javax.cache.configuration.Configuration stringDoubleConfiguration =
                Eh107Configuration.fromEhcacheCacheConfiguration(configuration);

        cacheManager.createCache(PERSON_CACHE, stringDoubleConfiguration);
        return cacheManager;
    }
}
