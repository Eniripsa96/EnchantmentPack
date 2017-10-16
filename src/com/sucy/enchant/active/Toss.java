package com.sucy.enchant.active;

import com.rit.sucy.text.TextFormatter;
import com.sucy.enchant.ConflictGroup;
import com.sucy.enchant.api.Cooldowns;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.Tasks;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

/**
 * Picks up and then throws an enemy
 *
 * Original Author: CeramicTitan
 * Modified by: Eniripsa96
 */
public class Toss extends CustomEnchantment {

    private static final String MESSAGE = "message";
    private static final String SPEED = "speed";
    private static final String DURATION = "duration";

    private Hashtable<UUID, BukkitTask> tasks = new Hashtable<>();
    private HashMap<UUID, Long> times = new HashMap<>();

    public Toss() {
        super("Toss", "Picks up and throws a target");

        setGroup(ConflictGroup.FORCE);
        setMaxLevel(5);
        setWeight(1);
        addNaturalItems(Material.DIAMOND_SWORD);

        Cooldowns.configure(settings, 15, 0);
        settings.set(SPEED, 1, 0.5);
        settings.set(DURATION, 5, 0);
        settings.set(MESSAGE, "&6{player} &7just picked you up with &dToss &7.");
    }

    /**
     * Picks up an enemy
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyInteractEntity(final Player player, final int level, final PlayerInteractEntityEvent event) {
        if (Cooldowns.onCooldown(this, player, settings, level)) return;

        final LivingEntity target = (LivingEntity)event.getRightClicked();

        // Grab the target
        player.addPassenger(target);
        target.sendMessage(TextFormatter.colorString(settings.getString(MESSAGE).replace("{player}", player.getName())));

        // Update the cooldown timer
        Cooldowns.start(this, player);

        // Run the release task
        tasks.put(player.getUniqueId(), Tasks.schedule(player::eject, (int)(settings.get(DURATION, level) * 20)));
        times.put(player.getUniqueId(), System.currentTimeMillis() + 200);
    }

    /**
     * Throws a grabbed target
     *
     * @param player player with enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyInteractBlock(final Player player, final int level, final PlayerInteractEvent event){

        // Make sure the player has grabbed an enemy
        if (tasks.containsKey(player.getUniqueId()) && times.get(player.getUniqueId()) < System.currentTimeMillis()) {

            final double speed = settings.get(SPEED, level);
            final List<Entity> passengers = player.getPassengers();
            player.eject();
            for (final Entity entity : passengers) {
                // Launch the enemy
                final Vector vector = player.getEyeLocation().getDirection();
                vector.multiply(speed / vector.length());
                vector.setY(vector.getY() / 2);
                entity.setVelocity(vector);
            }

            // Cancel the release task
            tasks.remove(player.getUniqueId()).cancel();
        }
    }

    @Override
    public void applyUnequip(final LivingEntity user, final int level) {
        times.remove(user.getUniqueId());
        tasks.remove(user.getUniqueId());
    }
}