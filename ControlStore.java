public class ControlStore {

    private static final int INSTR_COUNT = 512;
    private Mic1Instruction store[];
    private ControlLine store_cl = null;
    private Bus out = null;

    public ControlStore(ControlLine store_cl, Bus out) {
        this.store_cl = store_cl;
        store = new Mic1Instruction[INSTR_COUNT];
        this.out = out;
    }

    public Mic1Instruction[] getStore() {
        return store;
    }

    public void setStore(Mic1Instruction store[]) {
        this.store = store;
    }

    public void poke() {
        int value = store_cl.getValue2();
        if (value < INSTR_COUNT) ;
        out.setValue(value);
    }

    public Mic1Instruction getInstruction(int index) {
        return store[index];
    }
}