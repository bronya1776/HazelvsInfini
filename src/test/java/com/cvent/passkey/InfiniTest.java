package com.cvent.passkey;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.UUID;

/**
 * @author Boris Bronstein
 * @version 1.0
 */
public class InfiniTest {
    @Rule
    public org.junit.rules.TestRule benchmarkRun = new BenchmarkRule();

    private static org.infinispan.Cache<String, String> map;

    @BeforeClass
    public static void beforeClass() throws Exception {
        DefaultCacheManager manager = new DefaultCacheManager();
        manager.defineConfiguration("benchmark", new ConfigurationBuilder().build());
        map = manager.getCache("benchmark");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        map.stop();
    }

    @BenchmarkOptions(benchmarkRounds = 1000000, warmupRounds = 100000)
    @Test
    public void testInfinispan() throws Exception {
        map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
}
