package com.sucy.enchant.potion.damaged.reflect;

import com.sucy.enchant.ConflictGroup;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Sets enemies on fire when they hit you
 */
public class Molten extends CustomEnchantment {

    private static final String CHANCE = "chance";
    private static final String DURATION = "duration";

    public Molten() {
        super("Molten", "Burns enemies when hit");

        setGroup(ConflictGroup.POTION_REFLECT);
        setMaxLevel(5);
        setWeight(4);
        addNaturalItems(ItemSet.CHESTPLATES.getItems());

        settings.set(CHANCE, 100, 0);
        settings.set(DURATION, 1, 0.5);
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
    public void applyDefense(final LivingEntity user, final LivingEntity target, final int level, final EntityDamageEvent event) {
        if (Math.random() * 100 < settings.get(CHANCE, level))
            target.setFireTicks((int)(settings.get(DURATION, level) * 20));
    }
}
