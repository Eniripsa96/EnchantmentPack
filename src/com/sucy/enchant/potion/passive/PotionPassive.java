package com.sucy.enchant.potion.passive;

import com.sucy.enchant.ConflictGroup;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.potion.PotionEnchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

/**
 * Applies a potion effect passively
 */
public abstract class PotionPassive extends CustomEnchantment implements PotionEnchantment {

    private static final String TIER = "tier";

    PotionPassive(final String name, final String description) {
        super(name, description);

        setGroup(ConflictGroup.POTION_PASSIVE);
        setMaxLevel(2);
        setWeight(1);

        settings.set(TIER, 1, 1);
    }

    /**
     * Begins the effect when the enchantment is equipped
     *
     * @param user player with the enchantment
     * @param level  enchantment level
     */
    @Override
    public void applyEquip(final LivingEntity user, int level) {
        final int tier = (int)settings.get(TIER, level);
        user.addPotionEffect(new PotionEffect(type(), Integer.MAX_VALUE, tier), true);
    }

    /**
     * Remove the potion when the enchantment is unequipped
     *
     * @param entity holder of the enchantment
     * @param level enchantment level
     */
    @Override
    public void applyUnequip(final LivingEntity entity, final int level) {
        entity.removePotionEffect(type());
    }
}
