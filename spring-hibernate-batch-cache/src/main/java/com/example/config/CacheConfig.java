package com.example.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;
import javax.cache.spi.CachingProvider;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        javax.cache.CacheManager jCacheManager = provider.getCacheManager();

        // ⏱ users cache: Store individual users for 1 minute
        if (jCacheManager.getCache("users") == null) {
            MutableConfiguration<Long, Object> userCacheConfig = new MutableConfiguration<Long, Object>()
                    .setStoreByValue(false)
                    .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(TimeUnit.MINUTES, 1)))  // <--- 1 MINUTE TTL
                    .setStatisticsEnabled(true);
            jCacheManager.createCache("users", userCacheConfig);
        }

        // ⏱ allUsers cache: Store all user list for 1 minute
        if (jCacheManager.getCache("allUsers") == null) {
            MutableConfiguration<String, Object> allUsersCacheConfig = new MutableConfiguration<String, Object>()
                    .setStoreByValue(false)
                    .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 30)))  // <--- 1 MINUTE TTL
                    .setStatisticsEnabled(true);
            jCacheManager.createCache("allUsers", allUsersCacheConfig);
        }

        return new JCacheCacheManager(jCacheManager);
    }

}
