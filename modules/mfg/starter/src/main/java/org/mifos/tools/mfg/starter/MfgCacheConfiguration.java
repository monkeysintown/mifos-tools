///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.starter;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableCaching
@Configuration
class MfgCacheConfiguration {
    /// NOTE: customize cache manager here if necessary

    @Bean
    CacheManager cacheManager(RemovalListener<Object, Object> removalListener) {
        var manager = new CaffeineCacheManager();

        manager.setCaffeine(Caffeine.newBuilder()
                .evictionListener(removalListener)
                .removalListener(removalListener)
                .maximumSize(1_000)
                .expireAfterWrite(Duration.ofMinutes(10))
                .recordStats());

        return manager;
    }
}
