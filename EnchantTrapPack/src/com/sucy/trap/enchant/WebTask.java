package com.sucy.trap.enchant;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class WebTask extends BukkitRunnable {

    List<Block> blocks;

    public WebTask(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void run() {
        for (Block block : blocks) {
            if (block.getType() == Material.WEB)
                block.setType(Material.AIR);
        }
    }
}
