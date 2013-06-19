package com.sucy.active.enchants;

import com.sucy.active.ConfigurableEnchantment;
import com.sucy.active.data.EnchantDefaults;
import org.bukkit.Material;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class Grapple extends ConfigurableEnchantment {

    public Grapple(Plugin plugin) {
        super(plugin, EnchantDefaults.GRAPPLE, new Material[] { Material.FISHING_ROD });
        description = "Pulls you towards your hook when reeling in";
    }

    public void apply(Player player, Fish hook, int level) {
        if (hook.getLocation().subtract(player.getLocation()).length() <= range(level)) {
            Vector speed = hook.getLocation().subtract(player.getLocation()).toVector().multiply(speed(level));
            speed.setY(speed.getY() / 2);
            speed.setY(speed.getY() + 0.3);
            player.setVelocity(speed);
        }
    }
}
