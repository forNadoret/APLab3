package battle;

import model.Droid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {
    private List<String> battleLog = new ArrayList<>();
    private int turnCounter = 0;

    public void battleOneVsOne(Droid d1, Droid d2) {
        battleLog.clear();
        battleLog.add("=== A fight between " + d1.getName() + " and " + d2.getName() + " ===");

        while (d1.getHealth() > 0 && d2.getHealth() > 0) {
            attack(d1, d2);
            if (d2.getHealth() <= 0) break;
            attack(d2, d1);
        }

        battleLog.add("=== Fight finished ===");

        d1.restoreHealth();
        d2.restoreHealth();
    }

    public void battleTeamVsTeam(List<Droid> team1, List<Droid> team2) {
        battleLog.clear();
        turnCounter = 0;
        battleLog.add("=== Team Battle Start ===");
        boolean loopBoolean = true;

        while (loopBoolean) {
            battleLog.add("\n--- Round " + ++turnCounter + " ---");

            battleLog.add("Team 1 attacks:");
            for (Droid attacker : team1) {
                if (attacker.getHealth() <= 0) continue;
                Droid defender = chooseTarget(team2);
                if (defender == null) {
                    battleLog.add("Team 1 won!");
                    loopBoolean = false;
                    break;
                }
                attack(attacker, defender);
            }

            battleLog.add("Team 2 attacks:");
            for (Droid attacker : team2) {
                if (attacker.getHealth() <= 0) continue;
                Droid defender = chooseTarget(team1);
                if (defender == null) {
                    battleLog.add("Team 2 won!");
                    loopBoolean = false;
                    break;
                }
                attack(attacker, defender);
            }

            battleLog.add(getTeamStatus("Team 1", team1));
            battleLog.add(getTeamStatus("Team 2", team2));
        }

        for (Droid d : team1)
        {
            d.restoreHealth();
        }

        for (Droid d : team2)
        {
            d.restoreHealth();
        }
    }

    private void attack(Droid attacker, Droid defender) {
        int damage = Math.max(attacker.getDamage() - defender.getArmour(), 0);
        defender.setHealth(Math.max(defender.getHealth() - damage, 0));
        logAttack(attacker, defender, damage);
    }

    private void logAttack(Droid attacker, Droid defender, int damage) {
        if (damage == 0) {
            battleLog.add(attacker.getName() + " attacks " + defender.getName() + ", but it's ineffective! " +
                    defender.getName() + " has " + defender.getHealth() + " hp left.");
        } else {
            battleLog.add(attacker.getName() + " attacks " + defender.getName() + " for " + damage +
                    ". " + defender.getName() + " has " + defender.getHealth() + " hp left.");
        }
    }

    private String getTeamStatus(String teamName, List<Droid> team) {
        StringBuilder sb = new StringBuilder(teamName + " status: ");
        for (Droid d : team) {
            sb.append(d.getName()).append(" ").append(d.getHealth()).append("hp; ");
        }
        return sb.toString();
    }

    public List<String> getBattleLog() {
        return battleLog;
    }

    private Droid chooseTarget(List<Droid> enemyTeam) {
        List<Droid> aliveEnemies = enemyTeam.stream()
                .filter(d -> d.getHealth() > 0)
                .toList();

        if (aliveEnemies.isEmpty()) return null;
        return aliveEnemies.get(new Random().nextInt(aliveEnemies.size()));
    }
}
