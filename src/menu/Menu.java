package menu;

import battle.Battle;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    Battle battle = new Battle();
    List<Droid> droids = new ArrayList<>();
    private final int teamSize = 3;

    public void start() throws InterruptedException {
        while (true) {
            System.out.println("\n======= Droid Battle =======");
            System.out.println("1. Create a droid");
            System.out.println("2. Show the list of droids");
            System.out.println("3. Battle 1 vs 1");
            System.out.println("4. Battle team vs team");
            System.out.println("5. Save the last battle to a file");
            System.out.println("6. Replay a battle from a saved file");
            System.out.println("7. Exit");

            System.out.print("\nEnter a number: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showListOfDroids();
                    break;
                case 3:
                    startOneVsOne();
                    break;
                case 4:
                    startTeamVsTeam();
                    break;
                case 5:
                    saveBattle();
                    break;
                case 6:
                    readBattle();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Wrong number. Try again!");
                    break;
            }
        }
    }

    private void createDroid() {
        while (true) {
            System.out.println("\n======= Choose droid type =======");
            System.out.println("1. Combat");
            System.out.println("2. Medic");
            System.out.println("3. Scout");
            System.out.println("4. Sniper");
            System.out.println("5. Tank");
            System.out.println("6. Exit");

            System.out.print("\nEnter a number: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    droids.add(new CombatDroid());
                    break;
                case 2:
                    droids.add(new MedicDroid());
                    break;
                case 3:
                    droids.add(new ScoutDroid());
                    break;
                case 4:
                    droids.add(new SniperDroid());
                    break;
                case 5:
                    droids.add(new TankDroid());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Wrong choice. Try again!");
                    break;
            }
        }
    }

    private void showListOfDroids() {
        for (Droid d : droids) {
            System.out.println(d);
        }
    }

    private void startOneVsOne() throws InterruptedException {
        if (droids.size() < 2) {
            System.out.println("There must be AT LEAST TWO created droids!");
            return;
        }

        System.out.println("Choose two droids for a battle 1v1: ");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).toString());
        }

        List<Integer> forbiddenIndices = new ArrayList<>();

        Droid droid1 = chooseDroid("Choose a droid №1: ", droids, forbiddenIndices);
        forbiddenIndices.add(droids.indexOf(droid1));
        Droid droid2 = chooseDroid("Choose a droid №2: ", droids, forbiddenIndices);

        battle.battleOneVsOne(droid1, droid2);
        for (String s : battle.getBattleLog()) {
            System.out.println(s);
            Thread.sleep(1000);
        }
    }

    private void startTeamVsTeam() throws InterruptedException {
        if (droids.size() < 6) {
            System.out.println("There must be AT LEAST SIX created droids!");
            return;
        }

        System.out.println("Choose six droids for a team battle 3v3: ");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).toString());
        }

        List<Integer> forbiddenIndices = new ArrayList<>();

        List<Droid> droidTeam1 = chooseTeam(forbiddenIndices);
        List<Droid> droidTeam2 = chooseTeam(forbiddenIndices);

        battle.battleTeamVsTeam(droidTeam1, droidTeam2);
        for (String s : battle.getBattleLog()) {
            System.out.println(s);
            Thread.sleep(1000);
        }
    }

    private Droid chooseDroid(String message, List<Droid> droids, List<Integer> forbiddenIndices) {
        int choice = 0;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= droids.size() && !forbiddenIndices.contains(choice - 1)) {
                    break;
                }
            } else {
                sc.next();
            }
            System.out.println("Wrong choice. Try again!");
        }
        return droids.get(choice - 1);
    }

    private void saveBattle() {
        try (PrintWriter file = new PrintWriter("battle.txt")) {
            for (String s : battle.getBattleLog()) {
                file.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readBattle() {
        try {
            File file = new File("battle.txt");
            Scanner scFile = new Scanner(file);

            while (scFile.hasNextLine()) {
                String line = scFile.nextLine();
                Thread.sleep(1000);
                System.out.println(line);
            }
            scFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Droid> chooseTeam(List<Integer> forbiddenIndices)
    {
        List<Droid> droidTeam = new ArrayList<>();

        System.out.println("Choose the team of 3: ");
        for (int i = 0; i < teamSize; i++) {
            Droid d = chooseDroid("Choose a droid №" + (i + 1) + ": ", droids, forbiddenIndices);
            droidTeam.add(d);
            forbiddenIndices.add(droids.indexOf(d));
        }

        return droidTeam;
    }
}