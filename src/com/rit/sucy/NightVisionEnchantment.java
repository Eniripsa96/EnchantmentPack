package com.rit.sucy;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import java.util.Hashtable;
import java.util.Map;

public class NightVisionEnchantment extends CustomEnchantment {

    static Hashtable<String, PotionRunnable> timers = new Hashtable<String, PotionRunnable>();

    Plugin plugin;
    int max;
    int tierBase;
    int tierBonus;

    public NightVisionEnchantment(Plugin plugin) {
        super("Night Vision", plugin.getConfig().getStringList("NightVision.items").toArray(new String[0]));
        this.plugin = plugin;
        max = plugin.getConfig().getInt("NightVision.max");
        tierBonus = plugin.getConfig().getInt("NightVision.tierBonus");
        tierBase = plugin.getConfig().getInt("NightVision.tierBase") - tierBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyEquipEffect(Player player, int enchantLevel) {
        PotionRunnable runnable = new PotionRunnable(player, PotionEffectType.NIGHT_VISION, tierBase + tierBonus * enchantLevel);
        runnable.runTaskTimer(plugin, 0, 95);
        timers.put(player.getName(), runnable);
    }

    @Override
    public void applyUnequipEffect(Player player, int enchantLevel) {
        timers.get(player.getName()).cancel();
        timers.remove(player.getName());
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }

    public static void initializePlayer(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet == null) return;
        if (helmet.getType() == Material.AIR) return;

        for (Map.Entry<CustomEnchantment, Integer> entry : EnchantmentAPI.getEnchantments(helmet).entrySet())
            if (entry.getKey().name().equalsIgnoreCase("Night Vision"))
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