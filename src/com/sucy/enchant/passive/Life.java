package com.sucy.enchant.passive;

import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import com.sucy.skill.SkillAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Life extends CustomEnchantment {

    private static final String HEALTH = "health";

    public Life() {
        super("Life", "Grants bonus health when equipped");

        setMaxLevel(6);
        setWeight(2);
        addNaturalItems(ItemSet.CHESTPLATES.getItems());

        settings.set(HEALTH, 5, 5);
    }

    @Override
    public void applyEquip(final LivingEntity user, final int level) {
        final double health = settings.get(HEALTH, level);
        if (Bukkit.getPluginManager().isPluginEnabled("SkillAPI") && user instanceof Player) {
            SkillAPI.getPlayerData((Player) user).addMaxHealth(health);
        } else {
            user.setMaxHealth(user.getMaxHealth() + health);
        }
    }

    @Override
    public void applyUnequip(LivingEntity user, int level) {
        final double health = settings.get(HEALTH, level);
        if (Bukkit.getPluginManager().isPluginEnabled("SkillAPI") && user instanceof Player) {
            SkillAPI.getPlayerData((Player) user).addMaxHealth(-health);
        } else {
            user.setMaxHealth(user.getMaxHealth() - health);
        }
    }
}
