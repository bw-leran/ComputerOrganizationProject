public class Memory {

    private int[] cell;
    private int cap;

    public Memory(int cap) {
        this.cap = cap;
        cell = new int[cap];
    }

    public Memory() {
        this(256);
    }

    public int read(int addr) {
        if (addr < 0 || cap <= addr) {
            System.out.println("bad address: " + addr);
            System.exit(1);
        }
        return cell[addr];
    }

    public void write(int addr, int data) {
        if (addr < 0 || cap <= addr) {
            System.out.println("bad address: " + addr);
            System.exit(1);
        }
        cell[addr] = data;
    }

    public void dump() {
        System.out.println("Memory:");
        for (int i = 0; i < cap; i++) {
            System.out.println("cell[" + Integer.toHexString(i) + "] = " + Integer.toHexString(cell[i]));
        }
    }

}