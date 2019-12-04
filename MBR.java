
import java.awt.*;

public class MBR extends TextField{

    private int value, next_val;
    private Bus
            in = null,
            out = null;
    private ControlLine
            memory_cl = null,
            store_cl = null,
            mbr_cl = null,
            mbru_cl = null;
    private ControlLine o_cl = null;
    private boolean fetch = false;

    public MBR(Bus in, Bus out, ControlLine memory_cl, ControlLine mbr_cl,
               ControlLine mbru_cl, ControlLine o_cl) {
        super(10);
        this.in = in;
        this.out = out;
        this.store_cl = store_cl;
        this.mbr_cl = mbr_cl;
        this.mbru_cl = mbru_cl;
        this.o_cl = o_cl;
        this.memory_cl = memory_cl;
        setEditable(false);
        setText("0x" + Integer.toHexString(value).toUpperCase());
    }

    public void store() {
        if (memory_cl.getValue()[0]) {
            value = in.getValue();
            o_cl.setValue2(255);
            setText("0x" + Integer.toHexString(value).toUpperCase());
        }
    }

    public void put() {
        if (mbr_cl.getValue()[0]) {
            if ((value & 128) == 0)
                out.setValue(value & 255);
            else
                out.setValue(value | -256);
        }
        else if (mbru_cl.getValue()[0]) {
            out.setValue(value & 255);
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