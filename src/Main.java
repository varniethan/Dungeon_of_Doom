import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Dungeon of Doom!");
        GameLogic game = new GameLogic();
        Command.printValidCommands();
        List<String> validCommands = Arrays.asList("HELLO", "LOOK", "MOVE N", "MOVE S", "MOVE E", "MOVE W", "PICKUP", "QUIT");
        String choice;
        while (true)
        {
            choice = game.userInput();
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
        }
    }
}