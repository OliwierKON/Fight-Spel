import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        Opponent opponent = new Opponent();
        String tryAgain;
        do {
            System.out.println("It's Fighting Time!");
            System.out.print("Please enter your name ");
            player.setName(scanner.nextLine());
            opponent.generateOpponent();
            while (player.getHealth() > 0 && opponent.getHealth() > 0) {
                System.out.println("\n" + player.getName() + ": " + player.getHealth() + " health");
                System.out.println(opponent.getName() + ": " + opponent.getHealth() + " health");
                System.out.println("1. Punch");
                System.out.println("2. Kick");
                System.out.println("3. Parry");
                System.out.print("Enter your move: ");
                int playerMove = 0;
                boolean validMove = false;
                while (!validMove) {
                    try {
                        playerMove = scanner.nextInt();
                        scanner.nextLine();
                        validMove = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid move. Please enter a valid move (1, 2, or 3): ");
                        scanner.nextLine();
                    }
                }
                int opponentMove = (int) (Math.random() * 3) + 1;
                if (playerMove == opponentMove) {
                    System.out.println("Both players chose the same move, no damage taken.");
                } else if (playerMove == 1) {
                    if (opponentMove == 2) {
                        System.out.println(player.getName() + " punched faster than" + opponent.getName() + "kicked.");
                        opponent.setHealth(opponent.getHealth() - 40);
                    } else {
                        System.out.println(player.getName() + "Got parried by" + opponent.getName());
                        player.setHealth(player.getHealth()-40);
                    }
                } else if (playerMove == 2) {
                    if (opponentMove == 1) {
                        System.out.println(opponent.getName() + " punched faster than " + player.getName() + "kicked.");
                        player.setHealth(player.getHealth() - 40);
                    } else {
                        System.out.println(opponent.getName() + " got hit by " + player.getName() + "'s kick.");
                        opponent.setHealth(opponent.getHealth()-40);
                    }
                } else if (playerMove == 3) {
                    if (opponentMove == 1) {
                        System.out.println(opponent.getName() + " Got parried by " + player.getName());
                        opponent.setHealth(opponent.getHealth()-40);
                    } else {
                        System.out.println(player.getName() + " Got hit by " + opponent.getName() + "'s kick");
                        player.setHealth(player.getHealth() -10 );
                    }
                }
            }
            if (player.getHealth() <= 0) {
                System.out.println("\n" + opponent.getName() + " wins!");
            } else {
                System.out.println("\n" + player.getName() + " wins!");
            }
            System.out.println("If you want to play again, say yes");
            tryAgain = scanner.nextLine();
            if(!tryAgain.equalsIgnoreCase("yes"))
            { System.out.println("Exiting Game, Goodbye");
            }

            } while (tryAgain.equalsIgnoreCase("yes"));
        scanner.close();
    }
}

class Player {
    private String name;
    private int health = 100;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

class Opponent {
    private String[] names = {"Bob", "Mike", "Sara", "Emily", "John"};
    private String name;
    private int health = 100;

    public void generateOpponent() {
        int randomIndex = (int) (Math.random() * names.length);
        name = names[randomIndex];
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}