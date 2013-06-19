package com.sucy.trap.data;

public enum ConflictGroup {

    DEFAULT ("Default"),
    TRAP ("Redstone_Trap"),
    ;

    private final String group;

    private ConflictGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }
}
