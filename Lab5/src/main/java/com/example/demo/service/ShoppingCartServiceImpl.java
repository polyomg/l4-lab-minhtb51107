package com.example.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.model.DB;
import com.example.demo.model.Item;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Integer, Item> map = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item item = DB.items.get(id);
        if (item != null) {
            if (map.containsKey(id)) {
                item = map.get(id);
                item.setQty(item.getQty() + 1);
            } else {
                item = new Item(item.getId(), item.getName(), item.getPrice(), 1);
                map.put(id, item);
            }
        }
        return item;
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);
        if (item != null) {
            item.setQty(qty);
        }
        return item;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return map.values();
    }

    @Override
    public int getCount() {
        return map.values().stream().mapToInt(Item::getQty).sum();
    }

    @Override
    public double getAmount() {
        return map.values().stream().mapToDouble(i -> i.getPrice() * i.getQty()).sum();
    }
}
