import java.awt.*;

public class ALU extends TextField{

    private Bus a_bus = null,
            b_bus = null,
            out = null;

    private ControlLine alu_cl = null,
            n_cl = null,
            z_cl = null;

    int result;

    public ALU(Bus a_bus, Bus b_bus, Bus out,
               ControlLine alu_cl, ControlLine n_cl,
               ControlLine z_cl) {
        super(30);
        this.a_bus = a_bus;
        this.b_bus = b_bus;
        this.out = out;
        this.alu_cl = alu_cl;
        this.n_cl = n_cl;
        this.z_cl = z_cl;
        setEditable(false);
    }

    public void poke() {
        //    System.out.print("ALU poked: ");

        int a = 0;
        int b = 0;
        result = 0;
        boolean value[] = alu_cl.getValue();
        if (value[MIR.ENA])
            a = a_bus.getValue();
        if (value[MIR.ENB])
            b = b_bus.getValue();
        if (value[MIR.INVA])
            a = ~ a;
        if (value[MIR.F0]) {
            if (value[MIR.F1])
                result = a + b;
            else
                result = ~ b;
        }
        else {
            if (value[MIR.F1])
                result = (a | b);
            else
                result = (a & b);
        }
        if (value[MIR.INC])
            result++;
        setText(toString());
        out.setValue(result);
        boolean z_ar[] = {result == 0};
        boolean n_ar[] = {result < 0};
        z_cl.setValue(z_ar);
        n_cl.setValue(n_ar);
    }

    public String toString() {
        boolean value[] = alu_cl.getValue();
        String s = new String();
        if (value[MIR.INVA])
            s += "NOT ";
        if (value[MIR.ENA])
            s += "A ";
        else
            s += "0 ";
        if (value[MIR.F0]) {
            if (value[MIR.F1])
                s += "+ ";
            else
                s = "NOT ";
        }
        else {
            if (value[MIR.F1])
                s += "OR ";
            else
                s += "AND ";
        }
        if (value[MIR.ENB])
            s += "B ";
        else
            s += "0 ";
        if (value[MIR.INC])
            s += "+ 1 ";
        s += "= 0x" + Integer.toHexString(result).toUpperCase();
        return s;
    }
}