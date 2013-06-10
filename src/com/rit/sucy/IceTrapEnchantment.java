package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class IceTrapEnchantment extends TrapEnchantment {

    int max;
    long cooldownBase;
    long cooldownBonus;

    public IceTrapEnchantment(Plugin plugin) {
        super("Ice Trap", plugin.getConfig().getStringList("IceTrap.items").toArray(new String[0]), 4);
        max = plugin.getConfig().getInt("IceTrap.max");
        cooldownBonus = (long)(1000 * plugin.getConfig().getDouble("IceTrap.cooldownBonus"));
        cooldownBonus = (long)(1000 * plugin.getConfig().getDouble("IceTrap.cooldownBase")) + cooldownBonus;
        layout = new boolean[][] {
                {  true,  true, false,  true,  true },
                {  true, false, false, false,  true },
                { false, false,  true, false, false },
                {  true, false, false, false,  true },
                {  true,  true, false,  true,  true }};
    }

    @Override
    public void onLeave(Trap trap, LivingEntity entity, int level) {
        if (entity != trap.owner) {
            Vector direction = trap.center.toVector().subtract(entity.getLocation().toVector());
            direction = direction.multiply(1 / direction.length());
            entity.setVelocity(direction);
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
