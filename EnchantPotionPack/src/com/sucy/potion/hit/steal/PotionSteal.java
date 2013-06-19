package com.sucy.potion.hit.steal;

import com.sucy.potion.ConfigurableEnchantment;
import com.sucy.potion.PotionEnchantment;
import com.sucy.potion.data.ConflictGroup;
import com.sucy.potion.data.EnchantDefaults;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

public abstract class PotionSteal extends ConfigurableEnchantment implements PotionEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     * @param type   enchantment type
     * @param items  natural items
     */
    public PotionSteal(Plugin plugin, EnchantDefaults type, Material[] items) {
        super(plugin, type, items, ConflictGroup.PDA);
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
            user.addPotionEffect(new PotionEffect(type(), duration(level), tier(level)), true);
    }
}
