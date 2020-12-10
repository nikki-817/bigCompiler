package com.company.Instruction;

import java.util.ArrayList;
import java.util.HashMap;

class ins {
    int index;
    InstructionType type;
    int op1;
    int op2;

    ins(int index, InstructionType type, int op1, int op2) {
        this.type = type;
        this.op1 = op1;
        this.op2 = op2;
        this.index = index;
    }
}

public class Instruction {
    private ArrayList<ins> instructionList = new ArrayList<>();
    private ArrayList<Byte> machCodeList = new ArrayList<>();
    private int codeOffset = 0;
    private static HashMap<InstructionType, Byte> insMap = new HashMap<>();
    {
        insMap.put(InstructionType.NOP, (byte)0x00);
        insMap.put(InstructionType.PUSH, (byte)0x01);
        insMap.put(InstructionType.POP, (byte)0x02);
        insMap.put(InstructionType.POPN, (byte)0x03);
        insMap.put(InstructionType.DUP, (byte)0x04);
        insMap.put(InstructionType.LOCA, (byte)0x0a);
        insMap.put(InstructionType.ARGA, (byte)0x0b);
        insMap.put(InstructionType.GLOBA, (byte)0x0c);
        insMap.put(InstructionType.LOAD8, (byte)0x10);
        insMap.put(InstructionType.LOAD16, (byte)0x11);
        insMap.put(InstructionType.LOAD32, (byte)0x12);
        insMap.put(InstructionType.LOAD64, (byte)0x13);
        insMap.put(InstructionType.STORE8, (byte)0x14);
        insMap.put(InstructionType.STORE16, (byte)0x15);
        insMap.put(InstructionType.STORE32, (byte)0x16);
        insMap.put(InstructionType.STORE64, (byte)0x17);
        insMap.put(InstructionType.ALLOC, (byte)0x18);
        insMap.put(InstructionType.FREE, (byte)0x19);
        insMap.put(InstructionType.STACKALLOC, (byte)0x1a);
        insMap.put(InstructionType.ADDI, (byte)0x20);
        insMap.put(InstructionType.SUBI, (byte)0x21);
        insMap.put(InstructionType.MULI, (byte)0x22);
        insMap.put(InstructionType.DIVI, (byte)0x23);
        //这里有4个浮点数相关的指令
        insMap.put(InstructionType.DIVU, (byte)0x28);
        insMap.put(InstructionType.SHL, (byte)0x29);
        insMap.put(InstructionType.SHR, (byte)0x2a);
        insMap.put(InstructionType.AND, (byte)0x2b);
        insMap.put(InstructionType.OR, (byte)0x2c);
        insMap.put(InstructionType.XOR, (byte)0x2d);
        insMap.put(InstructionType.NOT, (byte)0x2e);
        insMap.put(InstructionType.CMPI, (byte)0x30);
        insMap.put(InstructionType.CMPU, (byte)0x31);
        insMap.put(InstructionType.NEGI, (byte)0x34);
        insMap.put(InstructionType.ITOF, (byte)0x36);
        insMap.put(InstructionType.FTOI, (byte)0x37);
        insMap.put(InstructionType.SHRI, (byte)0x38);
        insMap.put(InstructionType.SETLT, (byte)0x39);
        insMap.put(InstructionType.SETGT, (byte)0x3a);
        insMap.put(InstructionType.BR, (byte)0x41);
        insMap.put(InstructionType.BRFALSE, (byte)0x42);
        insMap.put(InstructionType.BRTRUE, (byte)0x43);
        insMap.put(InstructionType.CALL, (byte)0x48);
        insMap.put(InstructionType.RET, (byte)0x49);
        insMap.put(InstructionType.CALLNAME, (byte)0x4a);
        insMap.put(InstructionType.SCANI, (byte)0x50);
        insMap.put(InstructionType.SCANC, (byte)0x51);
        insMap.put(InstructionType.PRINTI, (byte)0x54);
        insMap.put(InstructionType.PRINTC, (byte)0x55);
        insMap.put(InstructionType.PRINTS, (byte)0x57);
        insMap.put(InstructionType.PRINTLN, (byte)0x58);
        insMap.put(InstructionType.PANIC, (byte)0xfe);
    }

    public int getNextCodeOffset() {
        return codeOffset + 1;
    }

    public int getCodeOffset() {
        return codeOffset;
    }

    public void clearOffset() {
        this.codeOffset = 0;
    }

    public int pushBackInstruction(int insIndex, InstructionType type, int op1, int op2) {
        instructionList.add(new ins(insIndex, type, op1, op2));
        this.codeOffset++;
        return instructionList.size() - 1;
    }

    public void updateInstruction(int org, int op1, int op2) {
        instructionList.get(org).op1 = op1;
        instructionList.get(org).op2 = op2;
    }

}
