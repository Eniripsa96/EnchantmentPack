package com.rit.sucy;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Hashtable;

/**
 * Picks up and then throws an enemy
 */
public class TossEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public TossEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.TOSS, new String[] { "diamond_sword" });
    }

    /**
     * Picks up an enemy
     *
     * @param player player with the enchantment
     * @param level enchantment level
     * @param event event details
     */
    @Override
    public void applyEntityEffect(Player player, int level, PlayerInteractEntityEvent event) {

        // Make sure the target is living
        if(event.getRightClicked() instanceof LivingEntity){
            LivingEntity enemy = (LivingEntity)event.getRightClicked();
            long cooldown = cooldown(level);

            // Make sure the cooldown timer is not null
            if (!timers.containsKey(player.getName())) timers.put(player.getName(), 0l);

            // Check the cooldown
            if (System.currentTimeMillis() - timers.get(player.getName()) > cooldown) {

                // Grab the target
                player.setPassenger(enemy);
                if (enemy instanceof Player)
                    ((Player)enemy).sendMessage(ChatColor.GREEN+player.getDisplayName()+ChatColor.GRAY+" just picked you up with "+ ChatColor.LIGHT_PURPLE + "Toss "+level+ChatColor.GRAY+".");

                // Update the cooldown timer
                timers.put(player.getName(), System.currentTimeMillis());

            // On-cooldown message
            }else{
                player.sendMessage("You have " + ((int)(cooldown - System.currentTimeMillis() + timers.get(player.getName())) / 1000 + 1) + " seconds left.");
            }
        }
    }

    /**
     * Throws a grabbed target
     *
     * @param player player with enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyMiscEffect(Player player, int level, PlayerInteractEvent event){
        Player p = event.getPlayer();

        // Make sure the player has grabbed an enemy
        if(p.getPassenger() !=null && p.getPassenger() instanceof LivingEntity){
            LivingEntity enemy = (LivingEntity)p.getPassenger();

            // Make sure it was a left click
            if(event.getAction() == Action.LEFT_CLICK_AIR){

                // Launch the enemy
                Vector vector = p.getEyeLocation().getDirection();
                p.eject();
                vector.multiply(speed(level) / vector.length());
                vector.setY(vector.getY() / 2);
                enemy.setVelocity(vector);
                if (enemy instanceof Player)
                    ((Player)enemy).sendMessage(ChatColor.GREEN+p.getDisplayName()+ChatColor.GRAY+" just threw you with " + ChatColor.LIGHT_PURPLE + "Toss " + level+ChatColor.GRAY + ".");
            }
        }
    }
}