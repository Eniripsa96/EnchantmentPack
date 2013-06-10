package com.rit.sucy;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Hashtable;

public class DashEnchantment extends CustomEnchantment {

    Hashtable<String, Long> timers;

    int max;
    long cooldownBase;
    long cooldownBonus;
    double speedBase;
    double speedBonus;
    int damageBase;
    int damageBonus;

    Plugin plugin;

    public DashEnchantment(Plugin plugin) {
        super("Dash", plugin.getConfig().getStringList("Dash.items").toArray(new String[0]), 2);
        max = plugin.getConfig().getInt("Dash.max");
        cooldownBonus = (long)(1000 * plugin.getConfig().getDouble("Dash.cooldownBonus"));
        cooldownBase = (long)(1000 * plugin.getConfig().getDouble("Dash.cooldownBase")) + cooldownBonus;
        speedBonus = plugin.getConfig().getDouble("Dash.speedBonus");
        speedBase = plugin.getConfig().getDouble("Dash.speedBase") - speedBonus;
        damageBonus = plugin.getConfig().getInt("Dash.damageBonus");
        damageBase = plugin.getConfig().getInt("Dash.damageBase") - damageBonus;
        this.plugin = plugin;
        timers = new Hashtable<String, Long>();
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyMiscEffect(Player player, int level, PlayerInteractEvent event) {

        if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (System.currentTimeMillis() - timers.get(player.getName()) < cooldownBase - cooldownBonus) return;

            Vector vector = player.getLocation().getDirection();
            vector.setY(0);
            vector.multiply((speedBase + speedBonus) / vector.length());
            vector.setY(0.2);
            player.setVelocity(vector);

            DashTask task = new DashTask(plugin, player, damageBase + damageBonus, 3);
            task.runTask(plugin);

            timers.put(player.getName(), System.currentTimeMillis());
        }
    }
}
