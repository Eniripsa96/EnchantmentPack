package com.sucy.potion.hit.inflict;

import com.sucy.potion.ConfigurableEnchantment;
import com.sucy.potion.data.ConflictGroup;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.PotionEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

/**
 * Applies a potion effect to an enemy on hit
 */
public abstract class PotionInflict extends ConfigurableEnchantment implements PotionEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     * @param type   enchantment type
     * @param items  natural items
     */
    public PotionInflict(Plugin plugin, EnchantDefaults type, Material[] items) {
        super(plugin, type, items, ConflictGroup.POA);
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
        if (roll(level) && works(target, user))
            target.addPotionEffect(new PotionEffect(type(), duration(level), tier(level)), true);
    }
}
