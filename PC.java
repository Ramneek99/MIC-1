import java.awt.*;

public class PC extends TextField {

    private int value;
    private int cycle_count;
    private static final int COUNT_MAX = 100;
    private Bus
            address_bus = null,
            in = null,
            out = null;
    private ControlLine
            mem_cl = null,
            store_cl = null,
            put_cl = null;

    public PC(Bus in, Bus out, Bus address_bus,
              ControlLine store_cl, ControlLine put_cl, ControlLine mem_cl) {
        super(10);
        this.in = in;
        this.out = out;
        this.address_bus = address_bus;
        this.store_cl = store_cl;
        this.put_cl = put_cl;
        this.mem_cl = mem_cl;
        setEditable(false);
        setText("0x0");
        cycle_count = 0;
    }

    public void store() {
        if (store_cl.getValue()[0]) {
            value = in.getValue();
            if (cycle_count == COUNT_MAX) {
                setText("0x" + Integer.toHexString(value).toUpperCase());
                cycle_count = 0;
            }
            else cycle_count++;
        }
    }

    public void put() {
        if (put_cl.getValue()[0]) {
            out.setValue(value);
            if ( cycle_count == COUNT_MAX) {
                setText("0x" + Integer.toHexString(value).toUpperCase());
                cycle_count = 0;
            }
            else cycle_count++;
        }
    }

    public void mem() {
        if (mem_cl.getValue()[MIR.FETCH]) {
            address_bus.setValue(value);
            if (cycle_count == COUNT_MAX) {
                setText("0x" + Integer.toHexString(value).toUpperCase());
                cycle_count = 0;
            }
            else cycle_count++;
        }
    }

    public void forceValue(int value) {
        this.value = value;
        setText("0x" + Integer.toHexString(value).toUpperCase());
    }

    public void refresh() {
        setText("0x" + Integer.toHexString(value).toUpperCase());
    }
}