import java.awt.*;

public class MAR extends TextField{

    private int value;
    private Bus
            in = null,
            address_bus = null;
    private ControlLine
            store_cl = null,
            mem_cl = null;

    public MAR(Bus in, Bus address_bus, ControlLine store_cl, ControlLine mem_cl) {
        super(10);
        this.in = in;
        this.address_bus = address_bus;
        this.store_cl = store_cl;
        this.mem_cl = mem_cl;
        setEditable(false);
        refresh();
    }

    public void store() {
        if (store_cl.getValue()[0]) {
            value = in.getValue();
                refresh();
        }
    }

    public void mem() {
        if (mem_cl.getValue()[MIR.READ]){
            address_bus.setValue(value);
                refresh();
        }

        if (mem_cl.getValue()[MIR.WRITE]) {
            address_bus.setValue(value);
                refresh();
        }
    }

    public void refresh() {
        setText("0x" + Integer.toHexString(value).toUpperCase());
    }

    public void forceValue(int value) {
        this.value = value;
        refresh();
    }
}