package com.rit.sucy;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

/**
 * Pulls in all nearby enemies on hit
 */
public class GravityEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public GravityEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.GRAVITY, ItemSets.SWORDS.getItems(), 1);
    }

    /**
     * Pulls in nearby enemies on hit
     *
     * @param user   player with the enchantment
     * @param target enemy that was hit
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (roll(level)) {
            int range = range(level);
            for (Entity entity : user.getNearbyEntities(range, range, range)) {
                if (works(entity)) {
                    ((LivingEntity) entity).damage(damage(level));
                    entity.setVelocity(user.getLocation().subtract(entity.getLocation()).toVector().multiply(0.1));
                }
            }
        }
    }
}
