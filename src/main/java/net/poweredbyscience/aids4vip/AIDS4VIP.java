package net.poweredbyscience.aids4vip;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Lax on 12/7/2016.
 */
public class AIDS4VIP extends JavaPlugin {

    static String store;

    public void onEnable() {
        saveDefaultConfig();
        store = getConfig().getString("Store");
        aidsStart();
    }

    public void aidsStart() {
        final Scroller scroll = new Scroller("&aBUY VIP &l&fNOW &aTO GET RID OF DIS MESSAGE!", 16, 10, '&');
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    String s = scroll.next();
                    if (!p.hasPermission("server.vip")) {
                        sendActionBar(p, ChatColor.translateAlternateColorCodes('&',s));
                        sendTitle(p, ChatColor.translateAlternateColorCodes('&',s));
                        sendSubTitle(p, "Store: " + ChatColor.translateAlternateColorCodes('&',store));
                    }
                }
            }
        }.runTaskTimerAsynchronously(this,10,5);
    }

    public void sendActionBar(Player player, String message){
        CraftPlayer p = (CraftPlayer) player;
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc,(byte)2);
        p.getHandle().playerConnection.sendPacket(ppoc);
    }

    public void sendTitle(Player player, String message) {
        CraftPlayer p = (CraftPlayer) player;
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutTitle ppot = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, cbc, 1,2,3);
        p.getHandle().playerConnection.sendPacket(ppot);
    }

    public void sendSubTitle(Player player, String message) {
        CraftPlayer p = (CraftPlayer) player;
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutTitle ppot = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, cbc, 1,2,3);
        p.getHandle().playerConnection.sendPacket(ppot);
    }
}
