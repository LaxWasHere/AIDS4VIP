package net.poweredbyscience.aids4vip;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Lax on 12/8/2016.
 */
public class InvListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent ev) {
        if (ev.getInventory().getHolder() instanceof  IInventoryHolder) {
            ev.setCancelled(true);
            ev.getWhoClicked().sendMessage(ChatColor.RED + "Store: " + AIDS4VIP.store);
            ev.getWhoClicked().closeInventory();

        }

    }
}
