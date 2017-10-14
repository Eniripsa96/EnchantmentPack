package com.sucy.enchant.active;

import com.sucy.enchant.api.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Grapple extends CustomEnchantment {

    private static final String RANGE = "range";
    private static final String SPEED = "speed";

    public static Grapple instance;

    public Grapple() {
        super("Grapple", "Pulls you towards your hook when reeling in");

        setMaxLevel(5);
        setWeight(1);
        addNaturalItems(Material.FISHING_ROD);

        settings.set(RANGE, 10, 2);
        settings.set(SPEED, 0.4, 0);

        instance = this;
    }

    public void apply(Player player, Fish hook, int level) {
        if (hook.getLocation().subtract(player.getLocation()).length() <= settings.get(RANGE, level)) {
            Vector speed = hook.getLocation().subtract(player.getLocation()).toVector().multiply(settings.get(SPEED, level));
            speed.setY(speed.getY() / 2);
            speed.setY(speed.getY() + 0.3);
            player.setVelocity(speed);
        }
    }
}
