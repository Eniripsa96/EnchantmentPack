package com.sucy.enchant.passive;

import com.sucy.enchant.api.Cooldowns;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Gain health on striking an enemy
 */
public class Lifesteal extends CustomEnchantment {

    private static final String CHANCE = "chance";
    private static final String HEALTH = "health";
    private static final String COOLDOWN = "cooldown";

    public Lifesteal() {
        super("Lifesteal", "Gain health on attack");

        setMaxLevel(5);
        setWeight(1);
        addNaturalItems(ItemSet.HOES.getItems());

        settings.set(CHANCE, 100, 0);
        settings.set(HEALTH, 1, 1);
        settings.set(COOLDOWN, 5, 0);
    }

    /**
     * Gains health on hit
     *
     * @param user   player with the enchantment
     * @param target enemy that was hit
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyOnHit(final LivingEntity user, final LivingEntity target, final int level, final EntityDamageByEntityEvent event) {

        if (Cooldowns.onCooldown(this, user, settings, level)) return;

        // Check the probability
        if (Math.random() * 100 > settings.get(CHANCE, level)) return;

        // Gain health depending on enchantment level
        final double health = user.getHealth() + settings.get(HEALTH, level);
        user.setHealth(Math.min(user.getMaxHealth(), health));

        // Update the cooldown timer
        Cooldowns.start(this, user);
    }
}
