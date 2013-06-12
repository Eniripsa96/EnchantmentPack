package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

/**
 * Applies a potion effect to an enemy on hit
 */
public abstract class PotionInflictEnchantment extends ConfigurableEnchantment implements PotionEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     * @param type   enchantment type
     * @param items  natural items
     */
    public PotionInflictEnchantment(Plugin plugin, EnchantDefaults type, String[] items) {
        super(plugin, type, items);
    }

    /**
     * Applies potion effect on hit
     *
     * @param user   player with the enchantment
     * @param target enemy that was hit
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (roll(level))
            target.addPotionEffect(new PotionEffect(type(), duration(level), tier(level)), true);
    }
}
