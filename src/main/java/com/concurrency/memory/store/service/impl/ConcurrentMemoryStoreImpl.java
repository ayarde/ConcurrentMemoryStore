package com.concurrency.memory.store.service.impl;

import com.concurrency.memory.store.model.Item;
import com.concurrency.memory.store.service.ConcurrentMemoryStore;
import lombok.Data;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class ConcurrentMemoryStoreImpl implements ConcurrentMemoryStore {

    ConcurrentHashMap<String,Item> memoryStore;

    public ConcurrentMemoryStoreImpl(ConcurrentHashMap<String, Item> memoryStore) {
        this.memoryStore = memoryStore;
    }

    public void store(String key, Item item) throws IllegalArgumentException {
        if(key != null && item != null) {
            System.out.println("The stored key is: " + key);
            System.out.println("The stored Item is: " + item.toString());
            this.memoryStore.put(key, item);
        }
    }

    public void update(String key, int value1, int value2) {
        if(this.memoryStore.containsKey(key)){
            this.memoryStore.get(key).setValue1(value1);
            this.memoryStore.get(key).setValue2(value2);
            System.out.println("The updated Item is: "+ this.memoryStore.get(key).toString());
        } else {
            System.out.println("The Memory Store size is: "+ this.memoryStore.size());
            System.out.println("The Item not existed");
        }
    }

    public Iterator<Item> valueIterator() {
        return this.memoryStore.values().iterator();
    }

    public void remove(String key) {
        if(this.memoryStore.containsKey(key)){
            this.memoryStore.remove(key);
            System.out.println("The Item was removed with key: " + key);
        }
    }
}
