package com.sucy.enchant.potion.hit.inflict;

import com.sucy.enchant.ConflictGroup;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.potion.PotionEnchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;

/**
 * Applies a potion effect to an enemy on hit
 */
public abstract class PotionInflict extends CustomEnchantment implements PotionEnchantment {

    private static final String CHANCE = "chance";
    private static final String DURATION = "duration";
    private static final String TIER = "tier";

    PotionInflict(final String name, final String description) {
        super(name, description);

        setGroup(ConflictGroup.POTION_INFLICT);
        setMaxLevel(5);
        setWeight(4);

        settings.set(CHANCE, 100, 0);
        settings.set(DURATION, 1, 0.5);
        settings.set(TIER, 1, 0);
    }

    /**
     * Applies the potion to a struck target
     *
     * @param user   player with the enchantment
     * @param target enemy that hit the player
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyOnHit(final LivingEntity user, final LivingEntity target, final int level, final EntityDamageByEntityEvent event) {
        if (Math.random() * 100 < settings.get(CHANCE, level))
            target.addPotionEffect(
                    new PotionEffect(type(), (int)(settings.get(DURATION, level) * 20), (int)settings.get(TIER, level)),
                    true);
    }
}
