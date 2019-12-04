public class Bus {

    private int value;

    private Object value2 = null;

    public void setValue2(Object value2) {
        this.value2 = value2;
    }

    public Object getValue2() {
        return value;
    }

    public Bus() {}

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}