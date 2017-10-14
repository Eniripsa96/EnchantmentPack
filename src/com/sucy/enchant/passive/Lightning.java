package com.sucy.enchant.passive;

import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Has a chance to strike lightning on hit
 */
public class Lightning extends CustomEnchantment {

    private static final String CHANCE = "chance";

    public Lightning() {
        super("Lightning", "Chance to strike lightning on hit");

        setMaxLevel(5);
        setWeight(2);
        addNaturalItems(ItemSet.AXES.getItems());

        settings.set(CHANCE, 4, 4);
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
    public void applyOnHit(final LivingEntity user, final LivingEntity target, final int level, final EntityDamageByEntityEvent event) {
        if (Math.random() * 100 < settings.get(CHANCE, level))
            target.getWorld().strikeLightning(target.getLocation());
    }
}
