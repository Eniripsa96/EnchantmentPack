package com.sucy.passive.data;

public enum ConflictGroup {

    DEFAULT ("Default"),
    FORCE ("Force"),
    POD ("Potion_Offensive_Defense"),
    ;

    private final String group;

    private ConflictGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }
}
