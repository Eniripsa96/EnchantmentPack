package com.sucy.enchant.active;

import com.sucy.enchant.api.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Rejuvenating extends CustomEnchantment {

    private static final String HEALTH = "health";

    public static Rejuvenating instance;

    public Rejuvenating() {
        super("Rejuvinating", "Heals you upon eating");

        setMaxLevel(5);
        setWeight(2);
        addNaturalItems(
                Material.COOKED_BEEF,
                Material.COOKED_CHICKEN,
                Material.COOKED_MUTTON,
                Material.COOKED_FISH,
                Material.COOKED_RABBIT,
                Material.COOKIE,
                Material.BREAD,
                Material.APPLE,
                Material.ROTTEN_FLESH,
                Material.SPIDER_EYE,
                Material.FERMENTED_SPIDER_EYE,
                Material.CARROT,
                Material.GOLDEN_CARROT,
                Material.GOLDEN_APPLE,
                Material.POTATO,
                Material.BAKED_POTATO);

        settings.set(HEALTH, 5, 5);

        instance = this;
    }

    public void apply(final Player player, final int level) {
        final double health = player.getHealth() + settings.get(HEALTH, level);
        player.setHealth(Math.min(health, player.getMaxHealth()));
    }
}
