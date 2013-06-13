package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Gains movement speed on attack
 */
public class FervorEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public FervorEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.FERVOR, ItemSets.SWORDS.getItems());
    }

    /**
     * Grants the effects on hit
     *
     * @param user   player with the enchantment
     * @param target entity that was hit
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (roll(level) && works(target))
            user.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration(level), tier(level)), true);
    }
}
