package com.example.programmaifttt.Tests;

import com.example.programmaifttt.Counter.*;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CounterTest {
    //COUNTER CLASS TESTS
    @Test
    public void testCounter() {
        Counter counter = new Counter("test", 10);
        assertEquals("test", counter.getName());
        assertEquals(10, counter.getValue());
    }

    @Test
    public void testSetValue() {
        Counter counter = new Counter("test", 10);
        counter.setValue(20);
        assertEquals(20, counter.getValue());
    }

    //COUNTERMANAGER CLASS TESTS
    @Test
    public void testCreateCounter() {
        CounterManager manager = new CounterManager();
        Counter counter = manager.createCounter("test", 10);
        assertEquals("test", counter.getName());
        assertEquals(10, counter.getValue());
    }

    @Test
    public void testGetCounter() {
        CounterManager manager = new CounterManager();
        manager.createCounter("test", 10);
        Counter counter = manager.getCounter("test");
        assertNotNull(counter);
        assertEquals("test", counter.getName());
    }

    @Test
    public void testSetCounterValue() {
        CounterManager manager = new CounterManager();
        manager.createCounter("test", 10);
        manager.setCounterValue("test", 20);
        Counter counter = manager.getCounter("test");
        assertNotNull(counter);
        assertEquals(20, counter.getValue());
    }

    @Test
    public void testGetAllCounters() {
        CounterManager manager = new CounterManager();
        manager.createCounter("test1", 10);
        manager.createCounter("test2", 20);
        Map<String, Integer> counters = manager.getAllCounters();
        assertEquals(2, counters.size());
        assertEquals(10, counters.get("test1").intValue());
        assertEquals(20, counters.get("test2").intValue());
    }
}