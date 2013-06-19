package com.sucy.passive.enchants;

import com.sucy.passive.ConfigurableEnchantment;
import com.sucy.passive.data.EnchantDefaults;
import com.sucy.passive.data.ItemSets;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

/**
 * Has a chance to strike lightning on hit
 */
public class Lightning extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Lightning(Plugin plugin) {
        super(plugin, EnchantDefaults.LIGHTNING, ItemSets.AXES.getItems());
        description = "Chance to strike lightning on hit";
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
        if (roll(level) && works(target, user))
            target.getWorld().strikeLightning(target.getLocation());
    }
}
