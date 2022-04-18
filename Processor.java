public class Processor {

    int PC;
    int IR;
    int[] reg;

    public Boolean step(){
        return true;
    }

    public String dump(){
        return "dump here";
    }

    public void setMemory(Memory memory) {
    }

    public void setPC(int i) {
        int PC = i;
    }
}
