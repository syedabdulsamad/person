package com.abdulsamadsyed.person.logging;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CacheEventLogger implements CacheEventListener<Object, Object> {
    @Override
    public void onEvent(
            CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        Logger.getGlobal().log(Level.INFO,
                "Cache logger: "
                        + cacheEvent.getKey() + " "
                        + cacheEvent.getOldValue() + " "
                        +  cacheEvent.getNewValue());
    }
}
