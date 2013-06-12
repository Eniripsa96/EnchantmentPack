package com.rit.sucy;

/**
 * Various sets of items for using as enchantments' natural items
 */
public enum ItemSets {

    SWORDS (new String[] { "wood_sword", "stone_sword", "iron_sword", "gold_sword", "diamond_sword"} ),
    AXES (new String[] { "wood_axe", "stone_axe", "iron_axe", "gold_axe", "diamond_axe" }),
    PICKAXES (new String[] { "wood_pickaxe", "stone_pickaxe", "iron_pickaxe", "gold_pickaxe", "diamond_pickaxe" }),
    SHOVELS (new String[] { "wood_spade", "stone_spade", "iron_spade", "gold_spade", "diamond_spade" }),
    HELMETS (new String[] { "leather_helmet", "chainmail_helmet", "iron_helmet", "gold_helmet", "diamond_helmet" }),
    BOOTS (new String[] { "leather_boots", "chainmail_boots", "iron_boots", "gold_boots", "diamond_boots" }),
    CHESTPLATES (new String[] { "leather_chestplate", "chainmail_chestplate", "iron_chestplate", "gold_chestplate", "diamond_chestplate" }),
    LEGGINGS (new String[] { "leather_leggings", "chainmail_leggings", "iron_leggings", "gold_leggings", "diamond_leggings" });

    /**
     * Item list
     */
    String[] items;

    /**
     * Constructor
     *
     * @param items item list
     */
    private ItemSets(String[] items) {
        this.items = items;
    }

    /**
     * @return item list
     */
    public String[] getItems() {
        return items;
    }
}
