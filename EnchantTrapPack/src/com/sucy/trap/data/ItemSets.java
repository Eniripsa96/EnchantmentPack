package com.sucy.trap.data;

import org.bukkit.Material;

/**
 * Various sets of items for using as enchantments' natural items
 */
public enum ItemSets {

    SHOVELS (new Material[] {
            Material.WOOD_SPADE,
            Material.STONE_SPADE,
            Material.IRON_SPADE,
            Material.GOLD_SPADE,
            Material.DIAMOND_SPADE });

    /**
     * Item list
     */
    Material[] items;

    /**
     * Constructor
     *
     * @param items item list
     */
    private ItemSets(Material[] items) {
        this.items = items;
    }

    /**
     * @return item list
     */
    public Material[] getItems() {
        return items;
    }
}
