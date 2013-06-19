package com.sucy.active.enchants;

import com.sucy.active.ConfigurableEnchantment;
import com.sucy.active.data.EnchantDefaults;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class Fireball extends ConfigurableEnchantment {

    public Fireball(Plugin plugin) {
        super(plugin, EnchantDefaults.FIREBALL, new Material[] {Material.BLAZE_ROD }, 10);
        description = "Launches a fireball";
    }

    @Override
    public void applyMiscEffect(Player player, int level, PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (cooldown(level, player.getName())) return;
            player.launchProjectile(SmallFireball.class);
        }
    }
}
