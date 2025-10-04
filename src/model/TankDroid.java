package model;

public class TankDroid extends Droid {
    private static int count = 0;

    public TankDroid()
    {
        super(150, 20, 20);
        String name = "TankDroid #" + ++count;
        this.setName(name);
    }
}
