package com.sucy.enchant.potion.damaged.reflect;

import com.sucy.enchant.ConflictGroup;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.potion.PotionEnchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;

/**
 * Applies a potion effect to an enemy when they strike you
 */
public abstract class PotionReflect extends CustomEnchantment implements PotionEnchantment {

    private static final String CHANCE = "chance";
    private static final String DURATION = "duration";
    private static final String TIER = "tier";

    PotionReflect(final String name, final String description) {
        super(name, description);

        setGroup(ConflictGroup.POTION_REFLECT);
        setMaxLevel(5);
        setWeight(4);

        settings.set(CHANCE, 100, 0);
        settings.set(DURATION, 1, 0.5);
        settings.set(TIER, 1, 0);
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
    public void applyDefense(final LivingEntity user, final LivingEntity target, final int level, final EntityDamageEvent event) {
        if (Math.random() * 100 < settings.get(CHANCE, level))
            target.addPotionEffect(
                    new PotionEffect(type(), (int)(settings.get(DURATION, level) * 20), (int)settings.get(TIER, level)),
                    true);
    }
}
