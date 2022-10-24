/*
Game by draft_fusion


Questions & Answers:
-------------------------------
Q: What is this game?
A: It was a game made in 48 hours for a jam, unfortunately I was not able to finish it in time.
-------------------------------
Q: Why is everything in one file? Are you insane!?
A: Possibly although I was bored and wanted to challenge myself
-------------------------------
Q: Okay, what is this game about???
A: Survival + Zombies + Crafting = Kinda fun and cool!
-------------------------------
Q: How dose this game work?
A: Well RNG is used with a thread + the build in randomizer in Java, although I wanted it to be based on old school games
- where RNG is also inputs which can alter the in game memory for a desired output.
-------------------------------
Q: Why is this code sloppy?
A: You know when you are tired and do not feel like writing something great? It happens, I am only human.
- I tried my best to make it as optimized as I could while not running out of time!
-------------------------------
Q: What would you do differently?
A: I would make a separate file that is a rng maker + a file where you can save the game
-------------------------------
Q: Am I allowed to rewrite the game?
A: I do not know at the moment, I would like the help of maybe making a better version of this game.


Please note there are glitches that occur when playing this game. It was a pain to deal with inventory management.
Also wanted to implement more things into the game but randomness is one thing that makes things more fun!

*/


package com.draft_fusion;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main extends JFrame {

    static ArrayList < String > enemyCluster = new ArrayList < String > ();
    static ArrayList < String > worldTraps = new ArrayList < String > ();
    static ArrayList < String > inventory = new ArrayList < String > ();
    static String lineSeparate = "+----------------------------------------------------------------------------------+\n";
    static byte gameLoop = 0, enemySpawn = 17, growth = 0, plants = 0, poisonAmount = 0, playerLevel = 1, playerEnergy = (byte)(4 + playerLevel), playerAttack = 2, playerHealth = (byte)(3 + playerLevel), playerCapHealth = playerHealth, maxPlayerHealth = 20, playerExp = 0, playerScore = 0, monsterAttack = playerLevel, maxStorage = 8, enemyHealth = 3, critHit = playerEnergy, monsterExp = 1, playerMissRate = 2, inventoryNum = 0, playerNextLevel = (byte)(playerLevel + 3), bossTimer  = 37;
    static String playerName = " ";
    static long gameSeed, tempSeed;
    static String[] items = {
            "Can of Beans",
            "Bottle of Water",
            "Bag of Carrot Seeds",
            "??? Pill",
            "Rusted Key",
            "Wait that is all there was..",
            "Old cloth",
            "Empty bottle",
            "Empty can",
            "Glass Jar"
    };
    static String[] rareItems = {
            "Skull key",
            "Tiny ruby",
            "Rusted Key",
            "First Aid Kit",
            "Knife"
    };
    static String[] craftedItems = {
            "Campfire", //uses up 2 sticks and a log
            "Sharp Stick", //uses up 1 rock
            "Torch", //uses up 1 cloth and a Campfire
            "Wooden trap", // uses up 3 Sharp sticks
            "Carrot seed", // bag of carrot seeds give 1-3
            "Carrot", // 1 - 3
            "Magic Water", // Tiny ruby and glass jar and Bag of carrot seed
            "Wooden Planter" //uses 3 logs
    };
    static String[] enemies = {
            "Zombie",
            "Ghoulish Zombie",
            "Zombie Dog",
            "Gray Phantom",
            "Skeleton",
            "Mushroom Infected Zombie"
    };

    static String[] bosses = {
            "Witch",
            "Dragon",
            "Thunder Bird",
            "Fire Bird",
            "Harpy",
            "Succubus"

    };

    static String[] bodyPart = {
            "Head",
            "Arm",
            "Chest",
            "Leg",
            "Neck"
    };

    public static Random rng = new Random(System.nanoTime());
    public static boolean randomText = rng.nextBoolean();

    public static void main(String[] args) throws InterruptedException {
        //Start of Game
        rng.setSeed(gameSeed);
        Scanner input = new Scanner(System.in);


        System.out.println(lineSeparate);
        System.out.println("\t\t\t\t Draft Fusion \n\t\t\t\t *presents* \n\n\t\t\t Creators Of The Haunted \n\n\n");
        System.out.println("Alpha Version 1.4.8 (bugs are to be expected)\n");
        System.out.println(lineSeparate + "\n");
        System.out.println("What do they call you?\n");

        playerName = input.nextLine();

        for (int i = 0; i < playerName.length(); i++) {
            tempSeed += playerName.charAt(i);
        }

        gameSeed = tempSeed;

        System.out.println(playerName);

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(lineSeparate);
        System.out.println("You find yourself in the depths of the night as you stand lost in the deep dark woods..");
        System.out.println("All you know is that you must wait here in this location..");
        Thread.sleep(8770);

        inventory.add(items[0]);


        System.out.println("What would you like to do?\n");

        while (true) {
            int monsterPicked = rng.nextInt(enemies.length);
            inventory.remove("Wait that is all there was..");

            Collections.sort(inventory);

            if (playerHealth <= 0 || playerHealth == 0 || playerHealth < 0) {
                playerDead();
            }

            if (playerExp >= playerNextLevel || playerExp > playerNextLevel) {
                levelUp();
            }

            if (playerEnergy == 0 || playerEnergy <= 0) {
                passOut();
            }

            dayCheck();


            if (gameLoop == enemySpawn) {
                System.out.println("You hear something approaching you!");
                Thread.sleep(3000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
                switch (monsterPicked) {
                    case 0:
                        enemyCluster.add(enemies[0]);
                        System.out.println("It's a " + enemyCluster.get(0) + "!");
                        monsterAttack = (byte)(rng.nextInt(2) + 1);
                        enemyHealth = (byte)(enemyHealth + rng.nextInt(2) + 1);
                        enemySpawn += rng.nextInt(12) + 1;
                        monsterExp = (byte)(3 + rng.nextInt(2) + 1);
                        fight();
                        break;

                    case 1:
                        enemyCluster.add(enemies[1]);
                        System.out.println("It's a " + enemyCluster.get(0) + "!");
                        monsterAttack = (byte)(rng.nextInt(3) + 1);
                        enemyHealth = (byte)(enemyHealth + rng.nextInt(3) + 1);
                        enemySpawn += rng.nextInt(15) + 1;
                        monsterExp = (byte)(3 + rng.nextInt(2) + 1);
                        fight();
                        break;


                    case 2:
                        enemyCluster.add(enemies[2]);
                        System.out.println("It's a " + enemyCluster.get(0) + "!");
                        monsterAttack = (byte)(rng.nextInt(3) + 1);
                        enemyHealth = (byte)(enemyHealth + rng.nextInt(2) + 1);
                        enemySpawn += rng.nextInt(3) + 1;
                        monsterExp = (byte)(4 + rng.nextInt(2) + 1);
                        fight();
                        break;

                    case 3:
                        enemyCluster.add(enemies[3]);
                        System.out.println("It's a " + enemyCluster.get(0) + "!");
                        monsterAttack = (byte)(rng.nextInt(5) + 1);
                        enemyHealth = (byte)(enemyHealth + rng.nextInt(4) + 1);
                        enemySpawn += rng.nextInt(8) + 1;
                        monsterExp = (byte)(6 + rng.nextInt(2) + 1);
                        fight();
                        break;

                    case 4:
                        enemyCluster.add(enemies[4]);
                        System.out.println("It's a " + enemyCluster.get(0) + "!");
                        monsterAttack = (byte)(rng.nextInt(4) + 1);
                        enemyHealth = (byte)(enemyHealth + rng.nextInt(3) + 1);
                        enemySpawn += rng.nextInt(8) + 1;
                        monsterExp = (byte)(5 + rng.nextInt(2) + 1);
                        fight();
                        break;

                    case 5:
                        enemyCluster.add(enemies[5]);
                        System.out.println("It's a " + enemyCluster.get(0) + "!");
                        monsterAttack = (byte)(rng.nextInt(2) + 1);
                        enemyHealth = (byte)(enemyHealth + rng.nextInt(2) + 1);
                        enemySpawn += rng.nextInt(2) + 1;
                        monsterExp = (byte)(3 + rng.nextInt(2) + 1);
                        fight();
                        break;

                    default:
                        enemyCluster.add(enemies[0]);
                        System.out.println("It's a " + enemyCluster.get(0) + "!");
                        monsterAttack = (byte)(rng.nextInt(2) + 1);
                        enemyHealth = (byte)(enemyHealth + rng.nextInt(2) + 1);
                        enemySpawn += rng.nextInt(12) + 1;
                        fight();
                        break;
                }
            }

            System.out.println(lineSeparate + "\n");
            System.out.println("======" + "[Day: " + gameLoop + "]" + "====" + "[Health: " + playerHealth + "]" + "====" + "[Energy: " + playerEnergy + "]" + "====" + "[Level: " + playerLevel + "]" + "====" + "[Exp: " + playerExp + "]" + "====" + "[Next: " + playerNextLevel + "]" + "\n");
            System.out.println("[1]: Work [2]: Rest [3]: Build [4]: Stash\n");

            String command = input.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
            switch (command) {
                case "1", "One", "Work", "work", "W", "w":
                    int randomObject = rng.nextInt(14) + 1;
                    int unlockedChest = rng.nextInt(3) + 1;
                    int unlockChance = rng.nextInt(7) + 1;
                    int chestLoot = rng.nextInt(items.length) + 1;
                    int rareLoot = rng.nextInt(rareItems.length) + 1;

                    //Random object picker
                    switch (randomObject) {
                        case 1:
                            if (inventory.size() == maxStorage) {
                                System.out.println("You look around and found a rock..");
                                inventoryCheck();

                            } else {
                                System.out.println("You look around and found a rock..");
                                inventory.add("Rock");
                                playerEnergy -= 1;
                            }

                            break;

                        case 2:
                            if (inventory.size() == maxStorage) {
                                System.out.println("You search around the area and found a rusted key..");
                                inventoryCheck();

                            } else {
                                System.out.println("You search around the area and found a rusted key..");
                                inventory.add("Rusted Key");
                                playerEnergy -= 1;
                                gameLoop += 1;
                            }

                            break;

                        case 3:
                            if (inventory.size() == maxStorage) {
                                System.out.println("You look around and found a stick..");
                                inventoryCheck();

                            } else {
                                System.out.println("You look around and found a stick..");
                                inventory.add("Stick");
                                playerEnergy -= 1;
                            }

                            break;

                        case 4:
                            if (inventory.size() == maxStorage) {
                                System.out.println("You look around and found a log..");
                                inventoryCheck();

                            } else {
                                System.out.println("You look around and found a log..");
                                inventory.add("Log");
                                playerEnergy -= 2;
                            }

                            break;

                        case 5:
                            System.out.println("You look around and found an old wooden chest..");

                            if (unlockedChest != 3) {
                                System.out.println("It appears to be locked..");
                                if (inventory.contains("Rusted Key")) {
                                    System.out.println("You pull out the rusted key and put it into the locked chest..");
                                    Thread.sleep(9500);
                                    if (unlockChance != 7) {
                                        System.out.println("The key breaks in two becoming unusable..");
                                        inventory.remove("Rusted Key");
                                        playerEnergy -= 1;

                                    } else {
                                        if (unlockChance != 7) {
                                            System.out.println("The key breaks in two becoming unusable..");
                                            inventory.remove("Rusted Key");
                                            playerEnergy -= 1;
                                        } else {
                                            System.out.println("You look into the chest to find..");

                                            if (items[chestLoot] == items[7]) {
                                                if (inventory.size() == maxStorage) {
                                                    inventoryCheck();
                                                    break;

                                                } else {
                                                    System.out.println("A " + items[chestLoot] + "!");
                                                    inventory.add(items[chestLoot]);
                                                    chestLoot = rng.nextInt(items.length);
                                                }
                                                break;

                                            } else if (items[chestLoot] != items[7]) {
                                                if (inventory.size() == maxStorage) {
                                                    inventoryCheck();
                                                    break;

                                                } else {
                                                    System.out.println("A " + items[chestLoot] + "!");
                                                    inventory.add(items[chestLoot]);
                                                    chestLoot = rng.nextInt(items.length);
                                                    Thread.sleep(3000);

                                                    if (inventory.size() == maxStorage) {
                                                        inventoryCheck();
                                                        break;

                                                    } else {
                                                        System.out.println("A " + items[chestLoot] + "!");
                                                        inventory.add(items[chestLoot]);
                                                        chestLoot = rng.nextInt(items.length);
                                                        Thread.sleep(3000);

                                                    }

                                                    if (inventory.size() == maxStorage) {
                                                        inventoryCheck();
                                                        break;

                                                    } else {
                                                        System.out.println("A " + items[chestLoot] + "!");
                                                        inventory.add(items[chestLoot]);
                                                        chestLoot = rng.nextInt(items.length);
                                                        Thread.sleep(3000);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                System.out.println("It appears to be locked..");

                                if (inventory.contains("Rusted Key")) {
                                    System.out.println("You pull out the rusted key and put it into the locked chest..");

                                    if (unlockChance != 7) {
                                        System.out.println("The key breaks in two becoming unusable..");
                                        inventory.remove("Rusted Key");
                                        playerEnergy -= 1;

                                    } else {
                                        System.out.println("A " + items[chestLoot] + "!");
                                        inventory.add(items[chestLoot]);
                                        chestLoot = rng.nextInt(items.length);
                                        Thread.sleep(3000);

                                        if (inventory.size() == maxStorage) {
                                            inventoryCheck();

                                        } else {
                                            System.out.println("A " + items[chestLoot] + "!");
                                            inventory.add(items[chestLoot]);
                                            chestLoot = rng.nextInt(items.length);
                                            Thread.sleep(3000);

                                        }

                                        if (inventory.size() == maxStorage) {
                                            inventoryCheck();

                                        } else {
                                            System.out.println("A " + items[chestLoot] + "!");
                                            inventory.add(items[chestLoot]);
                                            chestLoot = rng.nextInt(items.length);
                                            Thread.sleep(3000);
                                        }
                                    }
                                }
                            }

                            break;

                        case 9:
                            System.out.println("You look around and find a bone..");
                            if (inventory.size() == maxStorage) {
                                inventoryCheck();

                            } else {
                                inventory.add("Bone");
                                playerEnergy -= 1;
                                gameLoop += 1;
                            }

                            break;

                        case 12:
                            System.out.println("Woahhh! You found an old gray chest!");
                            Thread.sleep(2500);
                            if (inventory.contains(rareItems[0])) {
                                System.out.println("You pull out the rusted key and put it into the locked chest..");

                                if (unlockChance != 7) {
                                    System.out.println("The key breaks in two becoming unusable..");
                                    inventory.remove("Skull Key");
                                    playerEnergy -= 1;

                                } else {
                                    System.out.println("A " + rareItems[rareLoot] + "!");
                                    inventory.add(rareItems[rareLoot]);
                                    rareLoot = rng.nextInt(rareItems.length);
                                    Thread.sleep(3000);

                                    if (inventory.size() == maxStorage) {
                                        inventoryCheck();
                                        break;

                                    }

                                }
                                if (inventory.contains("Rusted Key")) {
                                    System.out.println("You tired to put the rusted key into the lock but it won't even fit!");
                                } else {
                                    System.out.println("You do not have a key to open it with..");
                                }
                            } else {
                                System.out.println("You do not have a key to open it with..");
                            }

                            break;

                        default:
                            System.out.println("You look around and find nothing of interest..");
                            playerEnergy -= 1;
                            gameLoop += 1;
                            break;
                    }
                    break;

                case "2", "Two", "Rest", "rest", "R", "r":
                    if (playerEnergy < playerEnergy - 2 || playerEnergy <= 1) {
                        passOut();
                        gameLoop += 1;

                    } else {
                        System.out.println("You do not feel sleepy..");
                    }

                    break;

                case "3", "Three", "three", "T", "t", "Craft", "craft", "C", "c", "Build", "build", "B", "b":
                    System.out.println(lineSeparate);
                    System.out.println("Available crafts | [8]: Return");

                    //Campfire
                    if(inventory.contains("Log") & inventory.contains("Stick") & inventory.contains("Stick")){
                        System.out.print("[1]" + craftedItems[0] + " | ");

                    }

                    //Sharp Stick
                    if(inventory.contains("Stick") & inventory.contains("Rock")){
                        System.out.print("[2]" + craftedItems[1] + " | ");

                    }

                    //Torch
                    if(inventory.contains("Stick") & inventory.contains("Old cloth") & inventory.contains(craftedItems[0])){
                        System.out.print("[3]" + craftedItems[2] + " | ");

                    }


                    if(inventory.contains(craftedItems[1]) & inventory.contains(craftedItems[1]) & inventory.contains(craftedItems[1])){
                        System.out.print("[4]" + craftedItems[3] + " | ");


                    }

                    //Carrot seeds
                    if(inventory.contains(items[2])){
                        System.out.print("[5]" + craftedItems[4] + " | ");


                    }

                    //Carrot
                    if(inventory.contains(craftedItems[4])){
                        System.out.print("[6]" + craftedItems[5] + " | ");

                    }

                    if(inventory.contains(rareItems[1]) & inventory.contains(items[9]) & inventory.contains(items[2])){
                        System.out.println("[7]" + craftedItems[6]);
                    }

                    if(inventory.size() == 0 || inventory.size() < 0 || inventory.size() <= 0){
                        System.out.println("You have nothing to make.");
                    }

                    /*for (String j: craftedItems) {
                        System.out.print(j + " | ");
                    }*/

                    System.out.println("\n");
                    input.reset();
                    command = input.nextLine();

                    switch (command) {

                        //campfire
                        case "1":
                            if (inventory.contains("Log") & inventory.contains("Stick") & inventory.contains("Stick")) {
                                System.out.println("You spend your time building a " + craftedItems[0] + "..");
                                inventory.remove("log");
                                inventory.remove("Stick");
                                inventory.remove("Stick");
                                inventory.add(craftedItems[0]);
                                playerExp += 1;
                            } else {
                                System.out.println("You do not have all the materials needed to make a " + craftedItems[0] + "!");
                            }
                            break;

                        //sharp stick
                        case "2":
                            if (inventory.contains("Stick") & inventory.contains("Rock")) {
                                System.out.println("You spend your time building a " + craftedItems[1] + "..");
                                inventory.remove("Rock");
                                inventory.remove("Stick");
                                inventory.add(craftedItems[1]);
                                playerExp += 2;
                            } else {
                                System.out.println("You do not have all the materials needed to make a " + craftedItems[1] + "!");
                            }
                            break;

                        //torch
                        case "3":
                            if (inventory.contains("Stick") & inventory.contains("Old cloth") & inventory.contains(craftedItems[0])) {
                                System.out.println("You spend your time building a " + craftedItems[2] + "..");
                                inventory.remove(craftedItems[0]);
                                inventory.remove("Stick");
                                inventory.remove("Old cloth");
                                inventory.add(craftedItems[2]);
                                playerExp += 2;
                            } else {
                                System.out.println("You do not have all the materials needed to make a " + craftedItems[2] + "!");
                            }
                            break;

                        //wooden trap
                        case "4":
                            if (inventory.contains("Stick") & inventory.contains("Stick") & inventory.contains("Stick")) {
                                System.out.println("You spend your time building a " + craftedItems[3] + "..");
                                inventory.remove(craftedItems[1]);
                                inventory.remove(craftedItems[1]);
                                inventory.remove(craftedItems[1]);
                                inventory.add(craftedItems[3]);
                                playerExp += 1;
                            } else {
                                System.out.println("You do not have all the materials needed to make a " + craftedItems[3] + "!");
                            }
                            break;

                        //carrot seed
                        case "5":
                            if (inventory.contains(items[2])) {
                                System.out.println("You spend your time building a " + craftedItems[4] + "..");
                                inventory.remove(craftedItems[1]);
                                for (int i = 0; i < rng.nextInt(3) + 1; i++) {
                                    if (inventory.size() >= maxStorage || inventory.size() == maxStorage || inventory.size() > maxStorage) {
                                        System.out.println("Your stash has one too many items..");
                                    } else {
                                        inventory.add(craftedItems[4]);
                                        playerExp += 1;
                                    }
                                }

                            } else {
                                System.out.println("You do not have all the materials needed to make a " + craftedItems[4] + "!");
                            }
                            break;

                        //carrot
                        case "6":
                            if (inventory.contains(craftedItems[4])) {
                                System.out.println("You spend your time planting a " + craftedItems[5] + "..");
                                inventory.remove(craftedItems[4]);
                                for (int i = 0; i < rng.nextInt(3) + 1; i++) {
                                    if (inventory.size() >= maxStorage || inventory.size() > maxStorage || inventory.size() == maxStorage) {
                                        inventory.add(craftedItems[5]);
                                        playerExp += 1;
                                    }
                                }

                            } else {
                                System.out.println("You do not have all the materials needed to make a " + craftedItems[5] + "!");
                            }
                            break;

                        //Magic water
                        case "7":
                            if (inventory.contains(rareItems[1]) & inventory.contains(items[9]) & inventory.contains(items[2])) {
                                System.out.println("You spend your time building a " + craftedItems[6] + "..");
                                inventory.remove(rareItems[1]);
                                inventory.remove(items[9]);
                                inventory.remove(items[2]);
                                inventory.add(craftedItems[6]);
                                playerExp += 6;
                            } else {
                                System.out.println("You do not have all the materials needed to make a " + craftedItems[6] + "!");
                            }
                            break;

                        default:
                            break;

                    }

                    break;

                case "4", "Four", "four", "F", "f", "Stash", "stash", "S", "s":
                    if (inventory.toArray().length < 1) {
                        System.out.println("You do not have anything..");
                    } else {
                        System.out.println(lineSeparate);
                        for (String i: inventory) {
                            System.out.print(i + " | ");
                        }

                        System.out.println("\n");

                        System.out.println("[1]: Drop [2]: Return [3]: Use");

                        input.reset();
                        command = input.nextLine();

                        switch (command) {
                            case "1", "Drop", "drop", "D", "d":
                                inventoryDrop();
                                break;

                            case "2", "Return", "return", "R", "r":
                                break;

                            case "3", "Use", "use", "U", "u":
                                if (inventory.toArray().length > 0) {
                                    System.out.println("What would you like to use?");
                                    for (int i = 0; i < inventory.size(); i++) {
                                        System.out.print("[" + (i + 1) + "]: " + inventory.get(i) + " | ");
                                    }

                                    //use items starting from 1 - max number
                                    input.reset();
                                    command = input.nextLine();
                                    System.out.println("\n");

                                    switch (command) {
                                        case "1":
                                            inventoryNum = 0;
                                            System.out.println("You use the " + inventory.get(inventoryNum));
                                            checkItem();
                                            break;

                                        case "2":
                                            inventoryNum = 1;
                                            System.out.println("You use the " + inventory.get(inventoryNum));
                                            checkItem();
                                            break;

                                        case "3":
                                            inventoryNum = 2;
                                            System.out.println("You use the " + inventory.get(inventoryNum));
                                            checkItem();
                                            break;

                                        case "4":
                                            inventoryNum = 3;
                                            System.out.println("You use the " + inventory.get(inventoryNum));
                                            checkItem();
                                            break;

                                        case "5":
                                            inventoryNum = 4;
                                            System.out.println("You use the " + inventory.get(inventoryNum));
                                            checkItem();
                                            break;

                                        case "6":
                                            inventoryNum = 5;
                                            System.out.println("You use the " + inventory.get(inventoryNum));
                                            checkItem();
                                            break;

                                        case "7":
                                            inventoryNum = 6;
                                            System.out.println("You use the " + inventory.get(inventoryNum));
                                            checkItem();
                                            break;

                                        case "8":
                                            inventoryNum = 7;
                                            System.out.println("You use the " + inventory.get(inventoryNum));
                                            checkItem();
                                            break;

                                        default:
                                            break;
                                    }

                                    System.out.println("\n");

                                } else {
                                    System.out.println("You do no have anything to use..");

                                }
                                break;

                            default:
                                break;
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }

    private static void inventoryCheck() {
        Scanner input = new Scanner(System.in);
        System.out.println("Your stash is too full!");
        System.out.println("Would you like to drop something? [1]: Yes [2]: No");
        System.out.println(lineSeparate);

        input.reset();
        String command = input.nextLine();
        System.out.println("\n");

        if ("1".equals(command)) {
            inventoryDrop();
        }
    }

    private static void inventoryDrop(){
        Scanner input = new Scanner(System.in);
        if (inventory.toArray().length > 1) {
            System.out.println("What would you like to leave behind?");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.print("[" + (i + 1) + "]: " + inventory.get(i) + " | ");
            }

            //drop items starting from 1 - max number
            input.reset();
            String command = input.nextLine();
            System.out.println("\n");

            switch (command) {
                case "1":
                    System.out.println("You dropped a " + inventory.get(0));
                    inventory.remove(0);
                    break;

                case "2":
                    System.out.println("You dropped a " + inventory.get(1));
                    inventory.remove(1);
                    break;

                case "3":
                    System.out.println("You dropped a " + inventory.get(2));
                    inventory.remove(2);
                    break;

                case "4":
                    System.out.println("You dropped a " + inventory.get(3));
                    inventory.remove(3);
                    break;

                case "5":
                    System.out.println("You dropped a " + inventory.get(4));
                    inventory.remove(4);
                    break;

                case "6":
                    System.out.println("You dropped a " + inventory.get(5));
                    inventory.remove(5);
                    break;

                case "7":
                    System.out.println("You dropped a " + inventory.get(6));
                    inventory.remove(6);
                    break;

                case "8":
                    System.out.println("You dropped a " + inventory.get(7));
                    inventory.remove(7);
                    break;

                default:
                    break;
            }

            System.out.println("\n");

        } else {
            System.out.println("You do no have anything to drop..");

        }
    }

    private static void dayCheck() throws InterruptedException {
        switch (gameLoop) {
            case 5:
                System.out.println("...");
                System.out.println("You hear a faint sound from far off in the distance..\n");
                Thread.sleep(6000);
                System.out.println("\n");
                break;

            case 15:
                if (randomText) {
                    System.out.println("...");
                    System.out.println("\nYou hear a faint sound from far off in the distance..");
                    System.out.println("You feel as though it may be closer than you think..");
                    Thread.sleep(6000);
                    System.out.println("..");
                    System.out.println("\n");
                    Thread.sleep(2000);
                    System.out.println("You feel like something.. or someone.. is watching you..\n");
                    System.out.println("..");
                    System.out.println("\n");
                    Thread.sleep(4000);
                }
                break;

            case 37:
                if(randomText){
                        System.out.println("You begin to hear a faint sound almost of laughter..");
                        bossFight();
                }

            default:
                if (randomText) {
                    System.out.println("...");
                    System.out.println("\n");
                }
                break;
        }
    }

    private static void bossFight() throws InterruptedException {
        System.out.println("Something flies by..");
        if(randomText){
            System.out.println("...");
        }

        System.out.println("It now sits within the night sky..");
        Thread.sleep(10000);
        System.out.println("It stares into you.");
        Thread.sleep(12345);
        System.out.println("Laughter emits from it as it closes in!");

        enemyCluster.add(bosses[rng.nextInt(bosses.length) + 1]);
        System.out.println("It's a " + enemyCluster.get(0) + "!");
        monsterAttack = (byte)(rng.nextInt(9) + 1);
        enemyHealth = (byte)(enemyHealth + rng.nextInt(9) + 1);
        enemySpawn += rng.nextInt(12) + 1;
        bossTimer += rng.nextInt(37) + 1;

        fight();

    }

    private static void passOut() {
        Random rng4 = new Random(System.nanoTime());
        System.out.println("You pass out sleeping on the cold and uncomfortable ground..\n");
        playerEnergy += rng4.nextInt(5) + 2;

        if (playerHealth < playerHealth - 2) {
            playerHealth += rng4.nextInt(2) + 1;
        }
        System.out.println("\n");
    }

    private static void checkItem() throws InterruptedException {
        Random rng3 = new Random(gameSeed);
        int pillChance = rng3.nextInt(6) + 1;

        //Can of beans
        if (inventory.get(inventoryNum).contains(items[0])) {
            System.out.println("You pry open the " + items[0] + "!");
            playerHealth += 4;
            Thread.sleep(4000);

            if (playerHealth >= playerCapHealth) {
                System.out.println("You are recovered to full health!");
                playerHealth = playerCapHealth;
                Thread.sleep(3000);
            } else {
                System.out.println("You recovered " + 4 + " health!");
                Thread.sleep(2000);
            }

            inventory.remove(inventoryNum);

            //Bottle of water
        } else if (inventory.get(inventoryNum).contains(items[1])) {
            System.out.println("You open the " + items[1] + "!");
            playerEnergy += 4;
            Thread.sleep(4000);
            System.out.println("You recovered " + 4 + " energy!");
            Thread.sleep(2000);

            inventory.remove(inventoryNum);

            //Bag of carrot seeds
        } else if (inventory.get(inventoryNum).contains(items[2])) {
            System.out.println("You eat the " + items[2] + "!");
            playerEnergy += 2;
            Thread.sleep(4000);
            System.out.println("You recovered " + 2 + " energy!");
            Thread.sleep(2000);

            inventory.remove(inventoryNum);

            //Pills
        } else if (inventory.get(inventoryNum).contains(items[3])) {
            System.out.println("You consume the questionable " + items[3] + "!");
            Thread.sleep(4000);

            switch (pillChance) {
                case 6:
                    System.out.println("You begin to feel dizzy!");
                    playerEnergy -= 3;
                    inventory.remove(inventoryNum);
                    break;

                case 5:
                    System.out.println("You feel uneasy after taking the pill..");
                    poisonAmount += 3;
                    break;

                case 4:
                    System.out.println("Consuming the questionable " + items[3] + " gave you " + pillChance + " energy!");
                    playerEnergy += pillChance;
                    inventory.remove(inventoryNum);
                    break;

                case 3:
                    System.out.println("Consuming the questionable " + items[3] + " gave you " + pillChance + " health!");
                    if (playerHealth >= maxPlayerHealth) {
                        System.out.println("That's odd.. nothing happened..");
                        inventory.remove(inventoryNum);

                    } else {
                        playerHealth += pillChance;
                        inventory.remove(inventoryNum);
                    }
                    break;

                case 2:
                    if (pillChance == 2) {
                        System.out.println("Consuming the questionable " + items[3] + " gave you " + pillChance + " exp!");
                        playerExp += pillChance;
                        inventory.remove(inventoryNum);
                    }
                    break;

                default:
                    System.out.println("Consuming the questionable " + items[3] + " gave you " + pillChance + " level(s)!");
                    playerLevel += pillChance;
                    inventory.remove(inventoryNum);
                    break;
            }

            //Rusted Key
        } else if (inventory.get(inventoryNum).contains(items[4]) || inventory.get(inventoryNum).contains(rareItems[2])) {
            System.out.println("You don't have anything to open!");

            //Empty
        } else if (inventory.get(inventoryNum).contains(items[5])) {

            //Old cloth
        } else if (inventory.get(inventoryNum).contains(items[6])) {
            System.out.println("You use the " + items[6] + " nothing happened..");

            //Empty bottle
        } else if (inventory.get(inventoryNum).contains(items[7])) {
            System.out.println("You use the " + items[7] + " nothing happened..");

            //Empty can
        } else if (inventory.get(inventoryNum).contains(items[8])) {
            System.out.println("You use the " + items[8] + " nothing happened..");

            //Glass jar
        } else if (inventory.get(inventoryNum).contains(items[9])) {
            System.out.println("You use the " + items[9] + " nothing happened..");

            //Skull Key
        } else if (inventory.get(inventoryNum).contains(rareItems[0])) {
            System.out.println("You use the " + rareItems[0] + " nothing happened..");

            //Tiny Ruby
        } else if (inventory.get(inventoryNum).contains(rareItems[1])) {
            System.out.println("You use the " + rareItems[1] + " nothing happened..");

            //First Aid Kit
        } else if (inventory.get(inventoryNum).contains(rareItems[0])) {
            System.out.println("You open the " + rareItems[0] + "!");
            playerHealth += 10;
            Thread.sleep(4000);

            if (playerHealth >= playerCapHealth) {
                System.out.println("You are recovered to full health!");
                playerHealth = playerCapHealth;
                Thread.sleep(3000);
            } else {
                System.out.println("You recovered " + 10 + " health!");
                Thread.sleep(2000);
            }

            inventory.remove(inventoryNum);

            //Knife
        } else if (inventory.get(inventoryNum).contains(rareItems[0])) {
            System.out.println("You use the " + rareItems[0] + " nothing happened..");

            //Campfire
        } else if (inventory.get(inventoryNum).contains(craftedItems[0])) {
            System.out.println("You use the " + craftedItems[0] + " its nice and warm!");
            playerEnergy += 1;
            playerHealth += 1;

            inventory.remove(inventoryNum);

            //Sharp Stick
        } else if (inventory.get(inventoryNum).contains(craftedItems[1])) {
            System.out.println("You use the " + craftedItems[1] + " but nothing happened..");

            //Torch
        } else if (inventory.get(inventoryNum).contains(craftedItems[2])) {
            System.out.println("You use the " + craftedItems[2] + " but nothing happened..");

            //wooden trap
        } else if (inventory.get(inventoryNum).contains(craftedItems[3])) {
            System.out.println("You use the " + craftedItems[3] + " and placed it on the ground!");
            worldTraps.add(craftedItems[3]);

            inventory.remove(inventoryNum);

            //Carrot seed
        } else if (inventory.get(inventoryNum).contains(craftedItems[4])) {
            System.out.println("You begin to eat the " + craftedItems[4] + "..");
            playerHealth += 1;
            Thread.sleep(4000);

            if (playerHealth >= playerCapHealth) {
                System.out.println("You are recovered to full health!");
                playerHealth = playerCapHealth;
                Thread.sleep(3000);
            } else {
                System.out.println("You recovered " + 1 + " health!");
                Thread.sleep(2000);
            }

            inventory.remove(inventoryNum);

            //Carrot
        } else if (inventory.get(inventoryNum).contains(craftedItems[5])) {
            System.out.println("You eat the " + craftedItems[5] + "!");
            playerEnergy += 2;
            Thread.sleep(4000);
            System.out.println("You recovered " + 2 + " energy!");
            Thread.sleep(2000);

            inventory.remove(inventoryNum);

            //Magic Water
        } else if (inventory.get(inventoryNum).contains(craftedItems[6])) {
            System.out.println("You begin to drink the " + craftedItems[6] + "..");
            playerHealth += rng3.nextInt(10) + 1;
            playerEnergy += rng3.nextInt(10) + 1;
            playerExp += rng3.nextInt(10) + 1;
            Thread.sleep(4000);

            if (playerHealth >= playerCapHealth) {
                System.out.println("You feel like brand new!");
                playerHealth = playerCapHealth;
                Thread.sleep(3000);
            } else {
                System.out.println("I wonder what happened..");
                Thread.sleep(2000);
            }

            inventory.remove(inventoryNum);

        } else if (inventory.get(inventoryNum).contains("Stick")) {
            System.out.println("You use the Stick but nothing happened..");

        } else if (inventory.get(inventoryNum).contains("Rock")) {
            System.out.println("You use the Rock but nothing happened..");

        } else if (inventory.get(inventoryNum).contains("Log")) {
            System.out.println("You use the Log but nothing happened..");

        } else if (inventory.get(inventoryNum).contains("Rusted Key")) {
            System.out.println("You use the Rusted Key but nothing happened..");

        } else if (inventory.get(inventoryNum).contains("Bone")) {
            System.out.println("You use the Bone but nothing happened..");

        } else {
            System.out.println("..");
        }
    }

    private static void levelUp() throws InterruptedException {
        Random rng2 = new Random(System.nanoTime());

        int randStatBoost = rng2.nextInt(6) + 1;
        System.out.println("You have grown by: " + "+" + 1 + " level!");
        playerLevel += 1;
        playerScore += 5;

        randStatBoost = rng2.nextInt(6) + 1;
        System.out.println("Your attack increased by: " + "+" + randStatBoost);
        playerAttack += randStatBoost;
        Thread.sleep(3500);

        randStatBoost = rng2.nextInt(6) + 1;
        System.out.println("Your health increase by: " + "+" + randStatBoost);
        playerHealth += randStatBoost;
        Thread.sleep(3500);

        randStatBoost = rng2.nextInt(6) + 1;
        if (rng2.nextInt(23) + 1 == 23) {
            System.out.println("Surprisingly your energy increase by: " + "+" + 1 + "!");
            playerEnergy += 1;
            Thread.sleep(3500);
        }

        playerNextLevel += playerNextLevel + playerLevel + rng2.nextInt(3) + 1;
    }

    static void fight() throws InterruptedException {
        Scanner fightInput = new Scanner(System.in);
        Random rng1 = new Random(System.nanoTime());
        boolean runAway = rng1.nextBoolean();

        int attackType = rng1.nextInt(4) + 1;

        Thread.sleep(3000);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("The " + enemyCluster.get(0) + " edges near..");

        if(worldTraps.size() > 0 || worldTraps.size() >= 0){
            if(rng.nextInt(6) + 1 == 6 || rng.nextInt(6) + 1 == 3){
                if(bossTimer == gameLoop){
                    System.out.println("It flys passed the wooden trap!");
                }

                System.out.println("SNAP!\nThe " + enemyCluster.get(0) + " got entangled in the wooden trap!");
                enemyHealth -= rng.nextInt(4) + 2;
            }
        }

        Thread.sleep(3000);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("What should you do?");

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

        if (inventory.contains("Sharp Stick")) {
            playerAttack += 1;

        } else if (inventory.contains("Torch")) {
            playerAttack += 2;

        } else if (inventory.contains("Knife")) {
            playerAttack += 3;

        } else {

        }

        while (enemyHealth != 0 && enemyCluster.size() > 0) {
            System.out.println(lineSeparate);
            System.out.println("\t\t\t\t\t\t\t" + "-" + enemyCluster.get(0) + "-" + "\t\t\t\n\t\t\t\t\t\t\t\t\t\t" + enemyHealth + "\n");
            System.out.println(lineSeparate);
            System.out.println("\t\t\t\t" + "[1]: Attack [2]: Stash [3]: Run");
            System.out.println(lineSeparate);
            System.out.println("+--------- Health: " + playerHealth + " --------- " + "Energy: " + playerEnergy + " --------- " + " Level: " + playerLevel + " --------- " + " Exp: " + playerExp + "------+");

            System.out.println("The " + enemyCluster.get(0) + " lunges at your " + bodyPart[rng1.nextInt(bodyPart.length)] + "!");

            if (enemyCluster.contains(enemies[5])) {
                System.out.println("The " + enemyCluster.get(0) + "breaths spores!");
                Thread.sleep(1234);

                if (rng1.nextBoolean()) {
                    System.out.println(playerName + " got infected with spores!");
                    playerEnergy -= 1;
                    Thread.sleep(1234);
                }
            }

            if (enemyCluster.contains(enemies[3])) {
                System.out.println("It stares into your soul...");
                if (rng1.nextBoolean()) {
                    System.out.println("Its voice tares the air open with a loud shriek and shoots a fire ball!");
                    playerEnergy -= 2;
                    playerHealth -= monsterAttack * 2;
                }
            }

            if(enemyCluster.contains(bosses[0])){
                System.out.print("The " + bosses[0] + " begins to murmur to itself");
                Thread.sleep(1000);
                System.out.println(".");
                Thread.sleep(1000);
                System.out.println(".");
                Thread.sleep(1000);
                System.out.println(".");

                if(rng.nextInt(6) + 1 == 6){ //Summon the dead also adds more health and attack to itself
                    System.out.println("It begins a chant!");
                    monsterAttack += rng.nextInt(12) + 1;
                    enemyHealth += rng.nextInt(12) + 1;

                    enemyCluster.add(enemies[0]);
                    System.out.println("It's a " + enemyCluster.get(0) + "!");
                    monsterAttack = (byte)(rng.nextInt(2) + 1);
                    enemyHealth = (byte)(enemyHealth + rng.nextInt(2) + 1);


                }else if(rng.nextInt(6) + 1 == 5){ //Gives itself more attack and less health
                    System.out.println("It begins a chant!");
                    monsterAttack += rng.nextInt(16) + 1;
                    enemyHealth -= rng.nextInt(6) + 1;

                }else if(rng.nextInt(6) + 1 == 4){ //Gives itself more health and less attack
                    System.out.println("It begins a chant!");
                    enemyHealth += rng.nextInt(16) + 1;
                    monsterAttack -= rng.nextInt(3) + 1;

                }else if(rng.nextInt(6) + 1 == 3){ //Gives itself more health and attack
                    System.out.println("It begins a chant!");
                    monsterAttack += rng.nextInt(12) + 1;
                    enemyHealth += rng.nextInt(12) + 1;

                }else if(rng.nextInt(6) + 1 == 2){ //Gives itself more attack
                    System.out.println("It begins a chant!");
                    monsterAttack += rng.nextInt(6) + 1;

                }else if(rng.nextInt(6) + 1 == 1){ //Gives itself more health
                    System.out.println("It begins a chant!");
                    enemyHealth += rng.nextInt(6) + 1;

                }else{
                    System.out.println("It begins a chant!");

                }
            }

            if(attackType == 1){
                System.out.println("POWWWWW!!!");
                playerHealth -= monsterAttack + 3 + playerLevel;
                System.out.println("The " + enemyCluster.get(0) + " bites you HARD in the " + bodyPart[rng1.nextInt(bodyPart.length) + 1] + " for " + monsterAttack + 3 + playerLevel + " health!!!");
                Thread.sleep(2345);

                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

                if (playerHealth == 0 || playerHealth <= 0) {
                    System.out.println("\n");
                    System.out.println(lineSeparate);
                    playerDead();
                }
            }

            if(attackType == 2 || attackType == 4){
                System.out.println("The " + enemyCluster.get(0) + " quickly misses!");
                Thread.sleep(1000);
            }

            if(attackType == 3){
                System.out.println("The " + enemyCluster.get(0) + " bites you in the " + bodyPart[rng1.nextInt(bodyPart.length)] + " for " + monsterAttack + " health!");
                playerHealth -= monsterAttack;
                Thread.sleep(1000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

                if (playerHealth == 0 || playerHealth <= 0) {
                    playerDead();
                }
            }

            String options = fightInput.nextLine();
            switch (options) {
                case "1", "Attack", "attack":
                    if (critHit == (byte)(rng1.nextInt(playerEnergy) + 1)) {
                        System.out.println("POWWWWW!!!");
                        System.out.println("You hit the " + enemyCluster.get(0) + " in the " + bodyPart[rng1.nextInt(bodyPart.length)] + " for " + playerAttack + " health!!!");
                        enemyHealth -= playerAttack + 3 + playerLevel;
                        Thread.sleep(1000);

                        if (enemyHealth == 0 || enemyHealth <= 0) {
                            enemyDefeated();
                        }
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

                    } else {
                        if (playerMissRate == rng1.nextInt(playerMissRate) + 1) {
                            System.out.println("You attempt to hit the " + enemyCluster.get(0) + " in the " + bodyPart[rng1.nextInt(bodyPart.length)] + "!");
                            Thread.sleep(1000);
                            System.out.println("Unfortunately you missed!");
                            Thread.sleep(2000);
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

                        } else {
                            System.out.println("You hit the " + enemyCluster.get(0) + " in the " + bodyPart[rng1.nextInt(bodyPart.length)] + " for " + playerAttack + " health!");
                            enemyHealth -= playerAttack;
                            Thread.sleep(1000);
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

                            if (enemyHealth == 0 || enemyHealth <= 0) {
                                enemyDefeated();
                            }
                        }
                    }
                    break;

                case "2":
                    if (inventory.toArray().length > 0) {
                        System.out.println("What would you like to use?");
                        for (int i = 0; i < inventory.size(); i++) {
                            System.out.print("[" + (i + 1) + "]: " + inventory.get(i) + " | ");
                        }

                        //use items starting from 1 - max number
                        fightInput.reset();
                        options = fightInput.nextLine();
                        System.out.println("\n");

                        switch (options) {
                            case "1":
                                inventoryNum = 0;
                                System.out.println("You use the " + inventory.get(inventoryNum));
                                checkItem();
                                break;

                            case "2":
                                inventoryNum = 1;
                                System.out.println("You use the " + inventory.get(inventoryNum));
                                checkItem();
                                break;

                            case "3":
                                inventoryNum = 2;
                                System.out.println("You use the " + inventory.get(inventoryNum));
                                checkItem();
                                break;

                            case "4":
                                inventoryNum = 3;
                                System.out.println("You use the " + inventory.get(inventoryNum));
                                checkItem();
                                break;

                            case "5":
                                inventoryNum = 4;
                                System.out.println("You use the " + inventory.get(inventoryNum));
                                checkItem();
                                break;

                            case "6":
                                inventoryNum = 5;
                                System.out.println("You use the " + inventory.get(inventoryNum));
                                checkItem();
                                break;

                            case "7":
                                inventoryNum = 6;
                                System.out.println("You use the " + inventory.get(inventoryNum));
                                checkItem();
                                break;

                            case "8":
                                inventoryNum = 7;
                                System.out.println("You use the " + inventory.get(inventoryNum));
                                checkItem();
                                break;

                            default:
                                break;
                        }

                        System.out.println("\n");

                    } else {
                        System.out.println("You do no have anything to use..");

                    }
                    break;

                case "3":
                    if (runAway) {
                        System.out.println("You tried to run away...");
                        Thread.sleep(1234);
                        System.out.println("Running away successfully!");
                        Thread.sleep(1234);
                        break;
                    } else {
                        System.out.println("You tried to run away...");
                        Thread.sleep(1234);
                        System.out.println("But couldn't!");
                        Thread.sleep(1234);
                    }
                    break;

                default:
                    break;
            }
        }
    }

    static void enemyDefeated() throws InterruptedException {
        System.out.println("The " + enemyCluster.get(0) + " has returned to dust!");
        System.out.println("You gained " + monsterExp + " exp" + "!");
        if(worldTraps.size() > 0){
            worldTraps.remove(0);
        }

        if(rng.nextInt(3) + 1 == 3){
            System.out.println("It appears the " + enemyCluster.get(0) + " has dropped a " + rareItems[rng.nextInt(rareItems.length) + 1] + "!");
            if(inventory.size() > maxStorage || inventory.size() >= maxStorage || inventory.size() == maxStorage){
                inventoryCheck();
            }else{
                inventory.add(rareItems[rng.nextInt(rareItems.length) + 1]);
            }
        }
        playerExp += monsterExp;
        enemyCluster.clear();
        gameLoop += 1;
        Thread.sleep(10000);
    }

    static void playerDead() throws InterruptedException {
        System.out.println("You died");
        System.out.println("Day: " + gameLoop + " Score: " + playerScore + " Seed: " + gameSeed);
        Thread.sleep(45000);
        System.exit(0);
    }
}