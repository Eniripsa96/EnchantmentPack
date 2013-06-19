package com.sucy.passive.enchants;

import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;
import com.sucy.passive.ConfigurableEnchantment;
import com.sucy.passive.data.EnchantDefaults;
import com.sucy.passive.data.ItemSets;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Hashtable;
import java.util.Map;

public class Regenerative extends ConfigurableEnchantment {

    Hashtable<String, RegenTask> tasks = new Hashtable<String, RegenTask>();

    public Regenerative(Plugin plugin) {
        super(plugin, EnchantDefaults.REGENERATIVE, ItemSets.CHESTPLATES.getItems());
        description = "Restores health slowly over time";
    }

    @Override
    public void applyEquipEffect(Player player, int level) {
        if (tasks.contains(player.getName()))
            tasks.get(player.getName()).cancel();

        RegenTask task = new RegenTask(player);
        task.runTaskTimer(plugin, (int)(cooldown(level) / 50), (int)(cooldown(level) / 50));
        tasks.put(player.getName(), task);
    }

    public void initializePlayer(Player player) {
        for (ItemStack item : player.getEquipment().getArmorContents()) {
            for (Map.Entry<CustomEnchantment, Integer> entry : EnchantmentAPI.getEnchantments(item).entrySet()) {
                if (entry.getKey().name().equals(name()))
                    entry.getKey().applyEquipEffect(player, entry.getValue());
            }
        }
    }

    public void applyUnequipEffect(Player player, int level) {
        if (tasks.contains(player.getName())) {
            tasks.get(player.getName()).cancel();
            tasks.remove(player.getName());
        }
    }

    public void clearTasks() {
        for (RegenTask task : tasks.values())
            task.cancel();
        tasks.clear();
    }
}
