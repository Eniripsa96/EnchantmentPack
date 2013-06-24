package com.sucy.active.enchants;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.active.ConfigurableEnchantment;
import com.sucy.active.data.EnchantDefaults;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class Heal extends ConfigurableEnchantment {

    public Heal(Plugin plugin) {
        super(plugin, EnchantDefaults.HEAL, new Material[] { Material.BLAZE_ROD }, 2);
        description = "Heals yourself or a target ally";
        suffixGroups.add(SuffixGroups.HEALTH.getKey());
    }

    @Override
    public void applyEntityEffect(Player player, int level, PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player && !works(event.getRightClicked(), player)) {
            if (event.getRightClicked() instanceof LivingEntity) {
                if (cooldown(level, player.getName())) return;
                int health = ((LivingEntity) event.getRightClicked()).getHealth() + health(level);
                if (health > ((LivingEntity) event.getRightClicked()).getMaxHealth())
                    health = ((LivingEntity) event.getRightClicked()).getMaxHealth();
                int bonus = health - ((LivingEntity) event.getRightClicked()).getHealth();
                ((LivingEntity) event.getRightClicked()).setHealth(health);
                if (bonus > 0) {
                    timers.put(player.getName(), System.currentTimeMillis());
                    player.sendMessage(ChatColor.GREEN + "You healed " + ChatColor.GOLD + ((Player)event.getRightClicked()).getName()
                            + ChatColor.GREEN + " for " + ChatColor.GOLD + health + " health");
                }
            }
            return;
        }

        if (cooldown(level, player.getName())) return;
        int health = player.getHealth() + health(level);
        if (health > player.getMaxHealth())
            health = player.getMaxHealth();
        int bonus = health - player.getHealth();
        player.setHealth(health);
        if (bonus > 0) {
            timers.put(player.getName(), System.currentTimeMillis());
            player.sendMessage(ChatColor.GREEN + "You healed yourself for " + ChatColor.GOLD + bonus + " health");
        }
    }

    @Override
    public void applyMiscEffect(Player player, int level, PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (cooldown(level, player.getName())) return;
            int health = player.getHealth() + health(level);
            if (health > player.getMaxHealth())
                health = player.getMaxHealth();
            int bonus = health - player.getHealth();
            player.setHealth(health);
            if (bonus > 0) {
                timers.put(player.getName(), System.currentTimeMillis());
                player.sendMessage(ChatColor.GREEN + "You healed yourself for " + ChatColor.GOLD + bonus + " health");
            }
        }
    }
}
