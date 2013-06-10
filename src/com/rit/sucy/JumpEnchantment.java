package com.rit.sucy;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import java.util.Hashtable;
import java.util.Map;

public class JumpEnchantment extends CustomEnchantment {

    static Hashtable<String, PotionRunnable> timers = new Hashtable<String, PotionRunnable>();

    Plugin plugin;
    int max;
    int tierBase;
    int tierBonus;

    public JumpEnchantment(Plugin plugin) {
        super("Jump", plugin.getConfig().getStringList("Jump.items").toArray(new String[0]));
        max = plugin.getConfig().getInt("Jump.max");
        tierBonus = plugin.getConfig().getInt("Jump.tierBonus");
        tierBase = plugin.getConfig().getInt("Jump.tierBase") - tierBonus;
        this.plugin = plugin;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyEquipEffect(Player player, int enchantLevel) {
        if (!timers.containsKey(player.getName())) {
            PotionRunnable runnable = new PotionRunnable(player, PotionEffectType.JUMP, tierBase + tierBonus * enchantLevel);
            runnable.runTaskTimer(plugin, 0, 95);
            timers.put(player.getName(), runnable);
        }
    }

    @Override
    public void applyUnequipEffect(Player player, int enchantLevel) {
        if (!timers.containsKey(player.getName()))
        {
            timers.get(player.getName()).cancel();
            timers.remove(player.getName());
        }
        player.removePotionEffect(PotionEffectType.JUMP);
    }

    public static void initializePlayer(Player player) {
        ItemStack boot = player.getInventory().getBoots();
        if (boot == null) return;
        if (boot.getType() == Material.AIR) return;

        for (Map.Entry<CustomEnchantment, Integer> entry : EnchantmentAPI.getEnchantments(boot).entrySet())
            if (entry.getKey().name().equalsIgnoreCase("Jump"))
                entry.getKey().applyEquipEffect(player, entry.getValue());
    }

    public static void clearPlayer(String playerName) {
        if (timers.containsKey(playerName)) {
            timers.get(playerName).cancel();
            timers.remove(playerName);
        }
    }

    public static void clearPlayers() {
        for (String player : timers.keySet()) {
            clearPlayer(player);
        }
    }
}
