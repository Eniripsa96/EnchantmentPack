package com.sucy.enchant.active;

import com.rit.sucy.player.Protection;
import com.sucy.enchant.api.Cooldowns;
import com.sucy.enchant.api.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Heal extends CustomEnchantment {

    private static final String HEALTH = "health";
    private static final String MESSAGE = "message";
    private static final String YOURSELF = "yourself";

    public Heal() {
        super("Heal", "Heals yourself or a target ally");

        setMaxLevel(5);
        setWeight(2);
        addNaturalItems(Material.BLAZE_ROD);

        Cooldowns.configure(settings, 15, 0);
        settings.set(HEALTH, 2, 2);
        settings.set(MESSAGE, "&2You healed &6{player} &2for &6{health} health");
        settings.set(YOURSELF, "yourself");
    }

    @Override
    public void applyInteractEntity(Player player, int level, PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof LivingEntity && Protection.isAlly(player, (LivingEntity)event.getRightClicked())) {
            if (heal(player, (Player)event.getRightClicked(), level)) {
                return;
            }
        }

        heal(player, player, level);
    }

    @Override
    public void applyInteractBlock(Player player, int level, PlayerInteractEvent event) {
        heal(player, player, level);
    }

    private boolean heal(final Player user, final Player target, final int level) {
        if (Cooldowns.onCooldown(this, user, settings, level) || user.getHealth() == user.getMaxHealth()) {
            return false;
        }

        final double bonus = settings.get(HEALTH, level);
        final double newHealth = Math.min(target.getMaxHealth(), bonus + target.getHealth());
        target.setHealth(newHealth);
        Cooldowns.start(this, user);

        user.sendMessage(settings.getString(MESSAGE)
                .replace("{player}", target == user ? settings.getString(YOURSELF) : target.getName())
                .replace("{health}", Double.toHexString(bonus)));

        return true;
    }
}
