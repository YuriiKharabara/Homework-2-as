package org.example;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.EntryProcessor;
import com.hazelcast.map.IMap;
import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.collection.IQueue;
import java.util.Map;

// For part with map:

//public class Main {
//    public static void main(String[] args) {
//        Config config = new Config();
//        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
//
//        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
//        IMap<String, Integer> map = instance.getMap("my-distributed-map");
//
//        String key = "key";
//        map.putIfAbsent(key, 0);
//
//        for (int k = 0; k < 10_000; k++) {
//            map.executeOnKey(key, (EntryProcessor<String, Integer, Object>) entry -> {
//                Integer currentValue = entry.getValue();
//                entry.setValue(currentValue + 1);
//                return null;
//            });
//        }
//
//        System.out.println("Final Value with Optimistic Locking: " + map.get(key));
//    }
//}

// For part with Bounded Queue:



public class Main {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.addQueueConfig(new QueueConfig("my-bounded-queue")
                .setMaxSize(10));

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        IQueue<Integer> queue = instance.getQueue("my-bounded-queue");

        // Producer logic

        for(int i = 1; i <= 100; i++) {
            try {
                queue.put(i);
                System.out.println("Produced: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Consumer logic
        
//        while(true) {
//            try {
//                Integer value = queue.take();
//                System.out.println("Consumed: " + value);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}

