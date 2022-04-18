public class Memory {

    int[] cell;
    int cap = 256;

    public Memory(int cap2) {
    }

    public static Integer read(int addr) {
        return addr;
    }

    public static Integer write(int addr, int data) {
        return data;
    }

    public static String dump() {
        return "dump here";
    }
}
