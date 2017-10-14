package com.sucy.enchant.potion.passive;

import com.sucy.enchant.api.Tasks;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

/**
 * Passively grants bonus jump height
 */
public class Gears extends PotionPassive {

    public Gears() {
        super("Gears", "Passively grants movement speed bonus");

        addNaturalItems(ItemSet.BOOTS.getItems());
    }
    public PotionEffectType type() {
        return PotionEffectType.SPEED;
    }

    @Override
    public void applyDefense(
            final LivingEntity user, final LivingEntity target, final int level, final EntityDamageEvent event) {
        Tasks.schedule(() -> applyEquip(user, level));
    }
}
