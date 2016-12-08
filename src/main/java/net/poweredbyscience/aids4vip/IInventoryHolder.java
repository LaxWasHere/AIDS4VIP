package net.poweredbyscience.aids4vip;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by Lax on 12/8/2016.
 */
public class IInventoryHolder implements InventoryHolder{

    private AIDS4VIP plogen;

    public IInventoryHolder(AIDS4VIP plogen) {
        this.plogen = plogen;
    }

    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(null, 54);
    }
}
