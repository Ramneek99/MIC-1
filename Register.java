import java.awt.*;

public class Register extends TextField /*Canvas*/ {

    private String name = null;
    protected int value;
    protected Bus in = null;
    protected Bus out = null;
    protected ControlLine store_cl = null;
    protected ControlLine put_cl = null;

    public Register(Bus in, Bus out, String name,
                    ControlLine store_cl, ControlLine put_cl) {
        super(10);
        this.in = in;
        this.out = out;
        this.name = name;
        this.store_cl = store_cl;
        this.put_cl = put_cl;
        setEditable(false);
        setText("0x" + Integer.toHexString(value).toUpperCase());
    }

    public void store() {
        if (store_cl.getValue()[0]) {
            value = in.getValue();
                setText("0x" + Integer.toHexString(value).toUpperCase());
        }
    }

    public void put() {
        if (put_cl.getValue()[0]) {
                setText("0x" + Integer.toHexString(value).toUpperCase());
            out.setValue(value);
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