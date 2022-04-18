public class Memory {

    int[] cell;
    int cap = 256;

    public Memory(int cap) {
    }

    public Integer read(int addr) {
        return addr;
    }

    public Integer write(int addr, int data) {
        return data;
    }

    public String dump() {
        return "dump here";
    }
}
