package com.concurrency.memory.store.model;

import lombok.Data;

@Data
public class Item {
    private int value1;
    private int value2;

    public Item(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}
