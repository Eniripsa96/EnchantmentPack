package com.sucy.enchant.active;

import com.sucy.enchant.api.Cooldowns;
import com.sucy.enchant.api.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Fireball extends CustomEnchantment {

    public Fireball() {
        super("Fireball", "Launches a fireball");

        setMaxLevel(10);
        setWeight(10);

        Cooldowns.configure(settings, 20, -1.6);
        addNaturalItems(Material.BLAZE_ROD);
    }

    @Override
    public void applyInteractBlock(Player player, int level, PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (!Cooldowns.onCooldown(this, player, settings, level)) {
                player.launchProjectile(SmallFireball.class);
                Cooldowns.start(this, player);
            }
        }
    }
}
