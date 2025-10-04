package model;

public class MedicDroid extends Droid {
    private static int count = 0;

    public MedicDroid()
    {
        super(80, 10, 8);
        String name = "MedicDroid #" + ++count;
        this.setName(name);
    }
}