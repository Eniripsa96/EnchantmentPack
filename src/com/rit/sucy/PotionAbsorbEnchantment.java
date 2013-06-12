package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

/**
 * Applies a potion effect to yourself when hit by an enemy
 */
public abstract class PotionAbsorbEnchantment extends ConfigurableEnchantment implements PotionEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     * @param type   enchantment type
     * @param items  natural items
     */
    public PotionAbsorbEnchantment(Plugin plugin, EnchantDefaults type, String[] items) {
        super(plugin, type, items);
    }

    /**
     * Applies the potion when hit
     *
     * @param user   player with the enchantment
     * @param target enemy that hit the player
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyDefenseEffect(LivingEntity user, LivingEntity target, int level, EntityDamageEvent event) {
        if (target == null) return;
        if (roll(level))
            user.addPotionEffect(new PotionEffect(type(), duration(level), tier(level)), true);
    }
}
