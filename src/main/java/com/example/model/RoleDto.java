package com.example.model;


public class RoleDto {
    private String nameRole;
    private boolean applied;

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String name) {
        this.nameRole = name;
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }
}
