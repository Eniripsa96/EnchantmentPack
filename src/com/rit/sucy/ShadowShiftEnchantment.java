package com.rit.sucy;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Has a chance to teleport and blind enemies when hit
 */
public class ShadowShiftEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public ShadowShiftEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.SHADOW_SHIFT, ItemSets.LEGGINGS.getItems(), 1);
    }

    /**
     * Teleports and blinds when hit
     *
     * @param user   player with the enchantment
     * @param target enemy that hit the player
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyDefenseEffect(LivingEntity user, LivingEntity target, int level, EntityDamageEvent event) {

        // Make sure the damage didn't come from a random source
        if (target == null) return;

        // Manage cooldowns
        if (user instanceof Player) {
            if (!timers.contains(((Player) user).getName()))
                timers.put(((Player) user).getName(), 0l);
            else if (System.currentTimeMillis() - timers.get(((Player) user).getName()) < cooldown(level))
                return;
        }

        // Apply the effect
        if (roll(level)) {
            double angle = Math.random() * Math.PI * 2;
            double x = user.getLocation().getX() + Math.cos(angle) * range(level);
            double z = user.getLocation().getZ() + Math.sin(angle) * range(level);
            double y = user.getLocation().getY();
            Location newLocation = new Location(user.getWorld(), x, y, z, user.getLocation().getYaw(), user.getLocation().getPitch());
            int range = radius(level);
            for (Entity entity : user.getNearbyEntities(range, range, range)) {
                if (entity instanceof LivingEntity)
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(
                            PotionEffectType.BLINDNESS, duration(level), tier(level)), true);
            }
            user.teleport(newLocation);

            // Update cooldown timer
            if (user instanceof Player)
                timers.put(((Player) user).getName(), System.currentTimeMillis());
        }
    }
}
