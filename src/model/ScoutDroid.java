package model;

public class ScoutDroid extends Droid {
    private static int count = 0;

    public ScoutDroid()
    {
        super(70, 15, 5);
        String name = "ScoutDroid #" + ++count;
        this.setName(name);
    }
}
