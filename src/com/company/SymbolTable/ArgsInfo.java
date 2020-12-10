package com.company.SymbolTable;

public class ArgsInfo {
    private boolean isConstant;
    private ValType type;
    private String name;

    public ArgsInfo(boolean isConstant, ValType type, String name) {
        this.isConstant = isConstant;
        this.type = type;
        this.name = name;
    }

    public boolean isConstant() {
        return isConstant;
    }

    public ValType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
