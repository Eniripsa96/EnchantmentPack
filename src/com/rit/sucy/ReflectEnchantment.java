package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

/**
 * Reflects damage back at attackers
 */
public class ReflectEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public ReflectEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.REFLECTION, ItemSets.CHESTPLATES.getItems());
    }

    /**
     * Reflects damage when hit
     *
     * @param user   player with the enchantment
     * @param target enemy that hit the player
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyDefenseEffect(LivingEntity user, LivingEntity target, int level, EntityDamageEvent event) {
        if (roll(level) && works(target))
                target.damage((int)(event.getDamage() * percent(level)), user);
    }
}
