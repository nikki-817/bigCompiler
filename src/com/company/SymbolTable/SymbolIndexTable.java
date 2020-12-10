package com.company.SymbolTable;

import com.company.error.GrammerError.GrammerError;

import java.util.ArrayList;
import java.util.HashMap;

class symbolTable {
    int nextOffset;
    HashMap<String, SymbolEntry> symbols = new HashMap<>();
    symbolTable next;   // 下一层
    symbolTable prev;   // 上一层
}

public class SymbolIndexTable {
    private int currentLevel;
    private ArrayList<globalConstants> gblConsTable = new ArrayList<>();
    private ArrayList<globalFunctions> gblFuncTable = new ArrayList<>();
    private HashMap<String, Integer> mapConst = new HashMap<>();
    private HashMap<String, Integer> mapFunc = new HashMap<>();

    public ArrayList<globalConstants> getGblConsTable() {
        return gblConsTable;
    }

    public ArrayList<globalFunctions> getGblFuncTable() {
        return gblFuncTable;
    }

    private symbolTable table = new symbolTable();

    public int getCurrentLevel() {
        return currentLevel;
    }

    public symbolTable getTable() {
        return table;
    }

    public int getFunIndex(){
        return gblFuncTable.size() - 1;
    }

    // 判断是否重复
    public boolean isDuplicate(String name) {
        if (table.symbols.get(name) != null)
            return true;
        return false;
    }

    // 向符号表中加入符号
    public void insertSymbol(String name, SymbolEntry symbolEntry) {
        int offset = -1; // -1存放函数的声明
        if (symbolEntry.getType() != SymbolType.FUNCTION) {
            offset = this.table.nextOffset;
            symbolEntry.setOffset(offset);
            this.table.nextOffset++;
        }
        this.table.symbols.put(name, symbolEntry);
    }

    public void insertGlobalConst(String name, char type, String value, boolean isConstant) {
        int index = gblConsTable.size();
        if (!isConstant){
            gblConsTable.add(new globalConstants(index, type, value, name, false));
        }
        else{
            gblConsTable.add(new globalConstants(index, type, value, name, true));
        }
        mapConst.put(name, index);
    }

    public globalConstants getGlobalConst(String name){
        if(mapConst.get(name) == null)
            return null;
        else
            return gblConsTable.get(mapConst.get(name));
    }

    public void insertGlobalFunc(String name, int params_size, int level, ArrayList<ArgsInfo> args, int localsLen, RtnType type) {
        int index = gblFuncTable.size();
        int nameIndex = mapConst.get(name);
        mapFunc.put(name, index);
        gblFuncTable.add(new globalFunctions(index, nameIndex, params_size, level, args, localsLen, type, name));
    }

    public globalFunctions getGlobalFunc(String name) {
        if(mapFunc.get(name) == null)
            return null;
        else
            return gblFuncTable.get(mapFunc.get(name));
    }

    // 找某个标识符，从本模块往上模块找
    public SymbolEntry findSymbol(String name) {
        symbolTable table = this.table;
        while (table != null) {
            if (table.symbols.get(name) != null)
                return table.symbols.get(name);
            table = table.prev;
        }
        return null;
    }

    // 进入下一层
    public void nextLevel() {
        if (this.table.next == null) {
            this.table.next = new symbolTable();
            this.table.next.prev = this.table;
        }
        this.currentLevel++;
        this.table = this.table.next;
    }

    // 返回上一层
    public void previousLevel() throws GrammerError {
        if (this.table.prev == null)
            throw new GrammerError();
        this.currentLevel--;
        this.table = this.table.prev;
        this.table.next = null;
    }

    public void previousLevelForSum() throws GrammerError{
        if (this.table.prev == null)
            throw new GrammerError();
        this.currentLevel--;
        this.table = this.table.prev;
    }

    public int getOffset(){
        return table.nextOffset;
    }


}


