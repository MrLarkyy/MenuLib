package xyz.larkyy.menulib;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuItem {

    private final String id;
    private final ItemStack itemStack;
    private final Consumer<InventoryClickEvent> action;
    private final List<Integer> slots;

    public MenuItem(String id, ItemStack is, Consumer<InventoryClickEvent> action, List<Integer> slots) {
        this.id = id;
        this.itemStack = is;
        this.action = action;
        this.slots = slots;
    }

    public String getId() {
        return id;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public void activate(InventoryClickEvent e) {
        action.accept(e);
    }

    public static Builder builder(String id, ItemStack is) {
        return new Builder(id,is);
    }

    public static class Builder {
        private final String id;
        private final ItemStack is;
        private List<Integer> slots;
        private Consumer<InventoryClickEvent> action;

        public Builder(String id, ItemStack is) {
            this.id = id;
            this.is = is;
            slots = new ArrayList<>();
            action = a -> {};
        }

        public Builder slots(List<Integer> slots) {
            this.slots = slots;
            return this;
        }

        public Builder action(Consumer<InventoryClickEvent> action) {
            this.action = action;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(id,is,action,slots);
        }
    }
}
