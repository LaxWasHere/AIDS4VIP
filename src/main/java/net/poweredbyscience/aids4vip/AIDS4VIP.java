package net.poweredbyscience.aids4vip;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Lax on 12/7/2016.
 */
public class AIDS4VIP extends JavaPlugin {

    static String store;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new InvListener(), this);
        saveDefaultConfig();
        store = getConfig().getString("Store");
        aidsStart();
        aidzStart();
    }

    public void aidsStart() {
        final Scroller scroll = new Scroller("BUY VIP NOW TO GET RID OF DIS MESSAGE!", 16, 10, '&');
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    String s = scroll.next();
                    if (!p.hasPermission("server.vip")) {
                        sendActionBar(p, ChatColor.translateAlternateColorCodes('&',"&a&l"+s));
                        sendTitle(p, ChatColor.translateAlternateColorCodes('&',"&a&l"+s));
                        sendSubTitle(p, "Store: " + ChatColor.translateAlternateColorCodes('&',store));
                    }
                }
            }
        }.runTaskTimerAsynchronously(this,10,5);
    }

    public void aidzStart() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.hasPermission("server.vip")) {
                        openAidz(p);
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_COW_AMBIENT,1,1);
                    }
                }
            }
        }.runTaskTimerAsynchronously(this, 5, 600);
    }

    public void openAidz(Player p) {
        Inventory inv = Bukkit.createInventory(new IInventoryHolder(this), 9, ChatColor.RED + "ADS FOR YOU");
        inv.setItem(4, createItem(Material.BARRIER, 1, 0, "&cBUY VIP", "&cStore: " + store));
        p.openInventory(inv);
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

    public ItemStack createItem(Material material, int amount, int shrt, String displayname, String lore) {
        ItemStack i = new ItemStack(material, amount, (short) shrt);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayname));

        ArrayList<String> loreList = new ArrayList<String>();
        String[] lores = lore.split("~");
        loreList.addAll(Arrays.asList(lores));

        im.setLore(loreList);
        i.setItemMeta(im);
        return i;
    }
}
