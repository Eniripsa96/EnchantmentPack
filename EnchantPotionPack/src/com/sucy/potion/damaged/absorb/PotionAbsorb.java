package com.sucy.potion.damaged.absorb;

import com.sucy.potion.ConfigurableEnchantment;
import com.sucy.potion.data.ConflictGroup;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.PotionEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

/**
 * Applies a potion effect to yourself when hit by an enemy
 */
public abstract class PotionAbsorb extends ConfigurableEnchantment implements PotionEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     * @param type   enchantment type
     * @param items  natural items
     */
    public PotionAbsorb(Plugin plugin, EnchantDefaults type, Material[] items) {
        super(plugin, type, items, ConflictGroup.PD);
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
        if (roll(level) && works(target, user))
            user.addPotionEffect(new PotionEffect(type(), duration(level), tier(level)), true);
    }
}
