package com.sucy.potion.hit.steal;

import com.sucy.potion.ConfigurableEnchantment;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class Berserking extends PotionSteal {

    public Berserking(Plugin plugin) {
        super(plugin, EnchantDefaults.BERSERKING, ItemSets.AXES.getItems());
        description = "Grants bonus damage on attack";
    }

    public PotionEffectType type() {
        return PotionEffectType.INCREASE_DAMAGE;
    }
}
