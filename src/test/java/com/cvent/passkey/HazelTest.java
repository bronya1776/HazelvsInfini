package com.cvent.passkey;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;
import java.util.UUID;

/**
 * @author Boris Bronstein
 * @version 1.0
 */
public class HazelTest {
    @Rule
    public org.junit.rules.TestRule benchmarkRun = new BenchmarkRule();

    private static HazelcastInstance instance;

    private static Map<String, String> map;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Config cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
        map = instance.getMap("benchmark");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        instance.getLifecycleService().shutdown();
    }

    @BenchmarkOptions(benchmarkRounds = 1000000, warmupRounds = 100000)
    @Test
    public void testHazelcast() throws Exception {
        map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
}
