package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 280, 250, 240};
    public static int[] heroesDamage = {20, 25, 15};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Medic"};


    public static void printStatistcs() {
        System.out.println("_____________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " +
                    heroesHealth[i]);
        }
        System.out.println("_____________");
    }


    public static void round() {
        if (bossHealth > 0) {
            changeBossDefence();
            bossHit();
        }
        heroesHit();
        heroesHeal();
        printStatistcs();
    }

    public static void heroesHeal() {
        Random r = new Random();
        int healAmount = r.nextInt(50) + 1;
        if (heroesHealth[3] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                    heroesHealth[i] = heroesHealth[i] + healAmount;
                    System.out.println("Medic healed " + heroesAttackType[i]
                            + " +" + healAmount);
                    break;
                }
            }
        }
    }


    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Chose " + bossDefenceType);
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                } else {
                    Random r = new Random();
                    int coeff = r.nextInt(5) + 2;
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(heroesAttackType[i]
                            + " hits critically "
                            + heroesDamage[i] * coeff);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Game started");
        printStatistcs();
        while (!isGameFinished()) {
            round();
        }
    }


    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDied = true;

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDied = false;
            }
        }
        if (allHeroesDied) {
            System.out.println("Boss Won!!!");
        }
        return allHeroesDied;
    }


}
