package mahdialemiclub.repository;

import mahdialemiclub.model.Item;
import java.util.*;
import java.util.stream.Collectors;

public class Inventory {
    private Map<String, Item> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addItem(Item item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("Varan med ID " + item.getId() + " finns redan.");
        }
        items.put(item.getId(), item);
    }

    public Item findItemById(String id) {
        Item item = items.get(id);
        if (item == null) {
            throw new IllegalArgumentException("Varan med ID " + id + " finns ej");
        }
        return item;
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public List<Item> getAvailableItems() {
        return items.values().stream()
                .filter(Item::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Item> filterItemsByType(Class<?> type) {
        return items.values().stream()
                .filter(type::isInstance)
                .collect(Collectors.toList());
    }

    public boolean removeItem(String id) {
        return items.remove(id) != null;
    }
}
