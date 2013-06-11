package com.rit.sucy;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonTrapEnchantment extends TrapEnchantment {

    int max;
    long cooldownBase;
    long cooldownBonus;
    int tierBase;
    int tierBonus;
    double durationBase;
    double durationBonus;

    public PoisonTrapEnchantment(Plugin plugin) {
        super("Poison Trap", 4);
        max = plugin.getConfig().getInt("PoisonTrap.max");
        cooldownBonus = (long)(1000 * plugin.getConfig().getDouble("PoisonTrap.cooldownBonus"));
        cooldownBase = (long)(1000 * plugin.getConfig().getDouble("PoisonTrap.cooldownBase")) + cooldownBonus;
        tierBonus = plugin.getConfig().getInt("PoisonTrap.tierBonus");
        tierBase = plugin.getConfig().getInt("PoisonTrap.tierBase") - tierBonus;
        durationBonus = plugin.getConfig().getDouble("PoisonTrap.durationBonus");
        durationBase = plugin.getConfig().getDouble("PoisonTrap.durationBase") - durationBonus;
        layout = new boolean[][] {
                { false, false, false,  true },
                { false, false,  true,  true,  true },
                { false,  true, false, false, false,  true },
                {  true,  true, false,  true, false,  true,  true },
                { false,  true, false, false, false,  true },
                { false, false,  true,  true,  true },
                { false, false, false,  true }};
    }

    @Override
    public void onUpdate(Trap trap, int level) {
        for (LivingEntity entity : trap.inRange) {
            if (entity != trap.owner) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON,
                        (int)(20 * (durationBase + durationBonus * level)) - 1, tierBase + tierBonus * level), true);
            }
        }
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    protected long cooldown(int level) {
        return cooldownBase - cooldownBonus * level;
    }
}