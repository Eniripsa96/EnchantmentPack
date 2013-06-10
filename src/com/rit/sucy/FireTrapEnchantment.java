package com.rit.sucy;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class FireTrapEnchantment extends TrapEnchantment {

    int max;
    double powerBase;
    double powerBonus;
    long cooldownBase;
    long cooldownBonus;

    public FireTrapEnchantment(Plugin plugin) {
        super("Fire Trap", plugin.getConfig().getStringList("FireTrap.items").toArray(new String[0]), 4);
        max = plugin.getConfig().getInt("FireTrap.max");
        powerBonus = plugin.getConfig().getDouble("FireTrap.powerBonus");
        powerBase = plugin.getConfig().getDouble("FireTrap.powerBase") - powerBonus;
        cooldownBonus = (long)(1000 * plugin.getConfig().getDouble("FireTrap.cooldownBonus"));
        cooldownBase = (long)(1000 * plugin.getConfig().getDouble("FireTrap.cooldownBase")) + cooldownBonus;
        layout = new boolean[][] {
                { false, false,  true,  true,  true },
                { false, false, false,  true },
                {  true, false, false, false, false, false,  true },
                {  true,  true, false,  true, false,  true,  true },
                {  true, false, false, false, false, false,  true },
                { false, false, false,  true },
                { false, false,  true,  true,  true }};
    }

    @Override
    public void onEnter(Trap trap, LivingEntity entity, int level) {
        if (entity != trap.owner) {
            Location loc = entity.getLocation();
            entity.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), (float)(powerBase + powerBonus * level), true, false);
            trap.remove();
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