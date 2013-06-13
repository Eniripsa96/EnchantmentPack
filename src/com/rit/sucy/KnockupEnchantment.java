package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

/**
 * Has a chance to knock enemies up into the air on hit
 */
public class KnockupEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public KnockupEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.KNOCKUP, ItemSets.SWORDS.getItems(), 10);
    }

    /**
     * Knocks enemies up on hit
     *
     * @param user   player with the enchantment
     * @param target enemy that was hit
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (roll(level) && works(target))
            // Need to knock them up later to get past Minecraft's knockback on taking damage
            new KnockupTask(target, (float)speed(level)).runTaskLater(plugin, 1);
    }
}