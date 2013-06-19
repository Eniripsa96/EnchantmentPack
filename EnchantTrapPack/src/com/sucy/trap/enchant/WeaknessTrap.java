package com.sucy.trap.enchant;

import com.sucy.trap.data.EnchantDefaults;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class WeaknessTrap extends PotionTrap {

    public WeaknessTrap(Plugin plugin) {
        super(plugin, EnchantDefaults.WEAKNESS_TRAP, 4, PotionEffectType.WEAKNESS);
        description = "Places a trap that lowers the damage of enemies";
        layout = new boolean[][]{
                {  true, false, false, false,  true },
                { false,  true, false,  true },
                { false, false, false, false, false },
                { false,  true, false,  true },
                {  true, false, false, false,  true }};
    }
}
