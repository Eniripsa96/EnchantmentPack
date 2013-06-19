package com.sucy.active.enchants;

import com.sucy.active.ConfigurableEnchantment;
import com.sucy.active.data.ConflictGroup;
import com.sucy.active.data.EnchantDefaults;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
public class Toss extends ConfigurableEnchantment {

    private Hashtable<String, TossTask> tasks = new Hashtable<String, TossTask>();

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Toss(Plugin plugin) {
        super(plugin, EnchantDefaults.TOSS, new Material[] { Material.DIAMOND_SWORD }, ConflictGroup.FORCE);
        description = "Picks up and throws enemies";
    }

    /**
     * Picks up an enemy
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyEntityEffect(Player player, int level, PlayerInteractEntityEvent event) {

        // Make sure its a valid target
        if(works(event.getRightClicked(), player)){
            LivingEntity enemy = (LivingEntity)event.getRightClicked();

            // Check the cooldown
            if (cooldown(level, player.getName()))
                return;

            if (tasks.containsKey(player.getName())) {
                TossTask task = tasks.get(player.getName());
                task.run();
                task.cancelled = true;
            }

            // Grab the target
            player.setPassenger(enemy);
            if (enemy instanceof Player)
                ((Player)enemy).sendMessage(ChatColor.GREEN+player.getDisplayName()+ChatColor.GRAY+" just picked you up with "+ ChatColor.LIGHT_PURPLE + "Toss "+level+ChatColor.GRAY+".");

            // Update the cooldown timer
            timers.put(player.getName(), System.currentTimeMillis());

            TossTask task = new TossTask(player);
            task.runTaskLater(plugin, duration(level));
            tasks.put(player.getName(), task);
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
        if(p.getPassenger() != null && p.getPassenger() instanceof LivingEntity){
            LivingEntity enemy = (LivingEntity)p.getPassenger();

            // Make sure it was a left click
            if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){

                event.setCancelled(true);

                // Launch the enemy
                Vector vector = p.getEyeLocation().getDirection();
                p.eject();
                vector.multiply(speed(level) / vector.length());
                vector.setY(vector.getY() / 2);
                enemy.setVelocity(vector);
                if (tasks.containsKey(player.getName())) {
                    TossTask task = tasks.get(player.getName());
                    task.cancelled = true;
                    tasks.remove(player.getName());
                }
                if (enemy instanceof Player)
                    ((Player)enemy).sendMessage(ChatColor.GREEN+p.getDisplayName()+ChatColor.GRAY+" just threw you with " + ChatColor.LIGHT_PURPLE + "Toss " + level+ChatColor.GRAY + ".");
            }
        }
    }
}