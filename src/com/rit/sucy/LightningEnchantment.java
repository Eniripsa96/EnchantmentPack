package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

/**
 * Has a chance to strike lightning on hit
 */
public class LightningEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public LightningEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.LIGHTNING, ItemSets.AXES.getItems());
    }

    /**
     * Strikes lightning on hit
     *
     * @param user   player with the enchantment
     * @param target enemy that was hit
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (roll(level))
            target.getWorld().strikeLightning(target.getLocation());
    }
}
