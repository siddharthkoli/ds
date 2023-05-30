import ConcatModule.ConcatPOA;

public class ConcatImpl extends ConcatPOA {
    public ConcatImpl() {}

    public String concat(String s1, String s2) {
        return s1 + s2;
    }
}
