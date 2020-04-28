package com.concurrency.memory.store;

import com.concurrency.memory.store.model.Item;
import com.concurrency.memory.store.service.impl.ConcurrentMemoryStoreImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrencyMemoryStoreMain {
    public static  void  main (String[] args) throws InterruptedException {
        ConcurrentMemoryStoreImpl  concurrentMemoryStore = new ConcurrentMemoryStoreImpl(
                new ConcurrentHashMap<String, Item>());

        ExecutorService executor = Executors.newFixedThreadPool(10);

        executor.submit(() -> concurrentMemoryStore.store("Adidas",new Item(1,1)));
        executor.submit(() -> concurrentMemoryStore.store("Nike",new Item(2,2)));
        executor.submit(() -> concurrentMemoryStore.store("Rebook",new Item(3,3)));
        executor.submit(() -> concurrentMemoryStore.update("Adidas", 4,4));
        executor.submit(() -> concurrentMemoryStore.remove("Nike"));
        executor.submit(() -> concurrentMemoryStore.store("Under Armour",new Item(5,5)));
        executor.submit(() -> concurrentMemoryStore.store("New Balance",new Item(6,6)));
        executor.submit(()-> concurrentMemoryStore.valueIterator()
                .forEachRemaining(item -> System.out.println(item.toString())));

        executor.awaitTermination(3000, TimeUnit.MILLISECONDS);
        executor.shutdownNow();
    }
}
