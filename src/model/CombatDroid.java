package model;

import jdk.jshell.spi.ExecutionControl;

public class CombatDroid extends Droid {
    private static int count = 0;

    public CombatDroid()
    {
        super(100, 25, 10);
        String name = "CombatDroid #" + ++count;
        this.setName(name);
    }
}
