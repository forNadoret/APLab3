package model;

public class SniperDroid extends Droid {
    private static int count = 0;

    public SniperDroid()
    {
        super(60, 35, 5);
        String name = "SniperDroid #" + ++count;
        this.setName(name);
    }
}
