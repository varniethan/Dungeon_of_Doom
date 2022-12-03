import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Dungeon of Doom!");
        GameLogic game = new GameLogic();
        System.out.println("Here are the game protocol commands:");
        System.out.println("|---------|-------------------------------------------------|");
        System.out.println("| Command | Action                                          |");
        System.out.println("|---------|-------------------------------------------------|");
        System.out.println("| HELLO   | Total amount of gold required to win            |");
        System.out.println("| GOLD    | Displays current gold owned                     |");
        System.out.println("| PICKUP  | Picks up the gold in given location             |");
        System.out.println("| MOVE    | Move <direction>. Direction can be N, S, E or W.|");
        System.out.println("| LOOK    | Show the map around the player                  |");
        System.out.println("| QUIT    | Ends the game                                   |");
        System.out.println("|---------|-------------------------------------------------|");
        System.out.println("Enter above commands to play the game :>");
        List<String> validCommands = Arrays.asList("HELLO", "LOOK", "MOVE N", "MOVE S", "MOVE E", "MOVE W", "PICKUP", "QUIT");
        String choice = game.userInput();
        while (true)
        {
            if (validCommands.contains(choice))
            {
                String arr[] = choice.split(" ", 2);
                String command = arr[0];
                switch (command)
                {
                    case "HELLO":
                        Command.hello(game.map.getGoldRequired());
                        break;
                    case "GOLD":
                        Command.gold(game.player.getGoldOwned());
                        break;
                    case "PICKUP":
                        Command.pickup(game.pickupCommand('P'));
                        break;
                    case "MOVE":
                        Command.move(game.moveCommand('P',choice.charAt(5)));
                        break;
                    case "LOOK":
                        Command.look(game.player.look(game.map));
                        break;
                    case "QUIT":
                        Command.quit(game.checkWinningCondition());
                        break;
                }
            }
            else
            {
                System.out.println("Command FAILED.");
            }
//            input = game.bot.getNextAction();
//            if (input == "LOOK") {
//                bot.passArray(look(botCoords));
//            }
//            movePlayer(input, botPlayer);
            // Useful to turn this on if you want to see what happens behind the scenes every turn!!
            game.printMap();

           choice = game.userInput();

        }
    }
}