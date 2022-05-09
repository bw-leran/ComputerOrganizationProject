import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Processor {

    private int numRegs = 8;
    private int[] reg = new int[numRegs];
    private int PC, IR;
    private Memory memory;

    public void setMemory(Memory m) {
        memory = m;
    }

    public void setPC(int addr) {
        PC = addr;
    }

    public boolean step() {
        IR = memory.read(PC++);
        if (IR == 0) {
            return true;
        }
        int opcode = IR >> 8;
        int arg1 = (IR >> 4) & 0xf;
        int arg2 = IR & 0xf;
        switch (opcode) {
            case 1: // load
                reg[arg1] = memory.read(reg[arg2]);
                break;
            case 2: // loadc
                reg[arg1] = memory.read(PC++);
                break;
            case 3: // store
                memory.write(reg[arg1], reg[arg2]);
                break;
            case 4: // add
                reg[arg1] = reg[arg1] + reg[arg2];
                break;
            case 5:  // mul
                reg[arg1] = reg[arg1] * reg[arg2];
                break;
            case 6:  // sub
                reg[arg1] = reg[arg1] - reg[arg2];
                break;
            case 7:  // div
                if (reg[arg2] == 0) {
                    System.out.println("divide by 0 error!");
                    System.exit(1);
                }
                reg[arg1] = reg[arg1] / reg[arg2];
                break;
            case 8: // and
                if (reg[arg1] != 0 && reg[arg2] != 0) {
                    reg[arg1] = 1;
                } else {
                    reg[arg1] = 0;
                }
                break;
            case 9:  // or
                if (reg[arg1] != 0 || reg[arg2] != 0) {
                    reg[arg1] = 1;
                } else {
                    reg[arg1] = 0;
                }
                break;
            case 10: // not
                if (reg[arg2] != 0) {
                    reg[arg1] = 0;
                } else {
                    reg[arg1] = 1;
                }
                break;
            case 11:  // lshift
                reg[arg1] = reg[arg2] << 1;
                break;
            case 12:  // rshift
                reg[arg1] = reg[arg2] >> 1;
                break;
            case 13:  // bwc
                reg[arg1] = reg[arg1] & reg[arg2];
                break;
            case 14:   // bwd
                reg[arg1] = reg[arg1] | reg[arg2];
                break;
            case 15:
                if (reg[arg1] != 0) {
                    PC = reg[arg2];
                }
                break;
            default:
                System.out.println("unrecognized opcode: " + opcode);
                System.exit(1);
        }
        return false;
    }

    public void dump() {
        System.out.println("Registers:");
        for (int i = 0; i < numRegs; i++) {
            System.out.println("reg[" + Integer.toHexString(i) + "] = " + Integer.toHexString(reg[i]));
        }
        System.out.println("PC = " + Integer.toHexString(PC));
        System.out.println("IR = " + Integer.toHexString(IR));
    }

    public void dumpToJField(JTextArea textfield) {

        String reg_0 = "reg[0] = " + String.valueOf(reg[0]) + "\n";
        String reg_1 = "reg[1] = " + String.valueOf(reg[1]) + "\n";
        String reg_2 = "reg[2] = " + String.valueOf(reg[2]) + "\n";
        String reg_3 = "reg[3] = " + String.valueOf(reg[3]) + "\n";
        String reg_4 = "reg[4] = " + String.valueOf(reg[4]) + "\n";
        String reg_5 = "reg[5] = " + String.valueOf(reg[5]) + "\n";
        String reg_6 = "reg[6] = " + String.valueOf(reg[6]) + "\n";
        String reg_7 = "reg[7] = " + String.valueOf(reg[07]) + "\n";
        String pc = "PC = " + String.valueOf(PC) + "\n";
        String ir = "IR = " + String.valueOf(IR) + "\n";

        textfield.setText("Registers:\n"+reg_0+reg_1+reg_2+reg_3+reg_4+reg_5+reg_6+reg_7+pc+ir);
    }

}