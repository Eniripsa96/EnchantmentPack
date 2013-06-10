package com.rit.sucy;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Hashtable;

public abstract class TrapEnchantment extends CustomEnchantment {

    protected Hashtable<String, Long> timers;

    protected boolean[][] layout;
    protected int radius;

    public TrapEnchantment(String name, String[] items, int radius) {
        super (name, items, 1);
        this.radius = radius;
        timers = new Hashtable<String, Long>();
    }

    @Override
    public void applyMiscEffect(Player player, int enchantLevel, PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            if (Trap.isTrapActive(player.getName(), this)) {
                Trap trap = Trap.getTrap(player.getName(), this);
                if (trap.contains(player.getLocation())){
                    trap.remove();
                    if (timers.containsKey(player.getName()))
                        timers.put(player.getName(), timers.get(player.getName()) - cooldown(enchantLevel) / 4);
                    return;
                }
            }

            if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);
            if (System.currentTimeMillis() - timers.get(player.getName()) < cooldown(enchantLevel)) return;
            if (Trap.isTrapActive(player.getName(), this)) return;

            Location location = player.getLocation();
            location.setX(location.getX() - layout.length / 2);
            location.setZ(location.getZ() - layout[layout.length / 2].length / 2);
            for (int i = 0; i < layout.length; i++) {
                for (int j = 0; j < layout[i].length; j++) {
                    if (!layout[i][j]) {
                        location.setZ(location.getZ() + 1);
                        continue;
                    }
                    Block nonSolid = player.getWorld().getBlockAt(location);
                    location.setY(location.getY() - 1);
                    Block solid = player.getWorld().getBlockAt(location);
                    location.setY(location.getY() + 1);
                    if (nonSolid.getType().isSolid() || !solid.getType().isSolid()
                            || nonSolid.getType() == Material.REDSTONE_WIRE) {
                        player.sendMessage("This is not a suitable place for the trap's seal...");
                        return;
                    }
                    location.setZ(location.getZ() + 1);
                }
                location.setZ(location.getZ() - layout[i].length);
                location.setX(location.getX() + 1);
            }

            location.setX(player.getLocation().getX() - layout.length / 2);
            for (int i = 0; i < layout.length; i++) {
                for (int j = 0; j < layout[i].length; j++) {
                    if (!layout[i][j]) {
                        location.setZ(location.getZ() + 1);
                        continue;
                    }
                    player.getWorld().getBlockAt(location).setType(Material.REDSTONE_WIRE);
                    location.setZ(location.getZ() + 1);
                }
                location.setZ(location.getZ() - layout[i].length);
                location.setX(location.getX() + 1);
            }

            Trap.createTrap(player, this, player.getLocation(), radius, enchantLevel);
            timers.put(player.getName(), System.currentTimeMillis());
        }
    }

    public void removeTrap(Location center) {
        center.setX(center.getX() - layout.length / 2);
        center.setZ(center.getZ() - layout[layout.length / 2].length / 2);
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if (!layout[i][j]) {
                    center.setZ(center.getZ() + 1);
                    continue;
                }
                center.getWorld().getBlockAt(center).setType(Material.AIR);
                center.setZ(center.getZ() + 1);
            }
            center.setZ(center.getZ() - layout[i].length);
            center.setX(center.getX() + 1);
        }
    }

    public void onEnter(Trap trap, LivingEntity entity, int level) {}
    public void onLeave(Trap trap, LivingEntity entity, int level) {}
    public void onUpdate(Trap trap, int level) {}
    protected  long cooldown(int level) { return 20000; }
}