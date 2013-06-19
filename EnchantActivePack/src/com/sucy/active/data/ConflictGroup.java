package com.sucy.active.data;

public enum ConflictGroup {

    DEFAULT ("Default"),
    FORCE ("Force"),
    ;

    private final String group;

    private ConflictGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }
}
