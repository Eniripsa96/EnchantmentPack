package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

/**
 * Sets enemies on fire when they hit you
 */
public class MoltenEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public MoltenEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.MOLTEN, ItemSets.CHESTPLATES.getItems(), 2);
    }

    /**
     * Sets attackers on fire
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
            target.setFireTicks(duration(level));
    }
}
