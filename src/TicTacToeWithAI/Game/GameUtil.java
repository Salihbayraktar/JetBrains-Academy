package TicTacToeWithAI.Game;

import TicTacToeWithAI.Game.Enums.Command;
import TicTacToeWithAI.Game.Enums.States;
import TicTacToeWithAI.Players.*;

import java.util.Scanner;
import java.util.regex.Pattern;

public class GameUtil {

    public static void playGame() {

        Command command = setCommand();

        switch (command) {
            case EASY_VS_USER     -> playGame(new EasyBot(1),   new User(2));
            case EASY_VS_EASY     -> playGame(new EasyBot(1),   new EasyBot(2));
            case EASY_VS_MEDIUM   -> playGame(new EasyBot(1),   new MediumBot(2));
            case EASY_VS_HARD     -> playGame(new EasyBot(1),   new HardBot(2));
            case MEDIUM_VS_USER   -> playGame(new MediumBot(1), new User(2));
            case MEDIUM_VS_EASY   -> playGame(new MediumBot(1), new EasyBot(2));
            case MEDIUM_VS_MEDIUM -> playGame(new MediumBot(1), new MediumBot(2));
            case MEDIUM_VS_HARD   -> playGame(new MediumBot(1), new HardBot(2));
            case HARD_VS_USER     -> playGame(new HardBot(1),   new User(2));
            case HARD_VS_EASY     -> playGame(new HardBot(1),   new EasyBot(2));
            case HARD_VS_MEDIUM   -> playGame(new HardBot(1),   new MediumBot(2));
            case HARD_VS_HARD     -> playGame(new HardBot(1),   new HardBot(2));
            case USER_VS_USER     -> playGame(new User(1),      new User(2));
            case USER_VS_EASY     -> playGame(new User(1),      new EasyBot(2));
            case USER_VS_MEDIUM   -> playGame(new User(1),      new MediumBot(2));
            case USER_VS_HARD     -> playGame(new User(1),      new HardBot(2));
        }

    }

    public static void playGame(Player player1, Player player2) {
        GameTable.printTable();
        boolean firstPlayerMove = true;
        while (isGameContinue(GameTable.getState())) {

            if (firstPlayerMove) {
                player1.makeMove();
                firstPlayerMove = false;
            } else {
                player2.makeMove();
                firstPlayerMove = true;
            }
            GameTable.printTable();

        }
    }

    public static boolean isGameContinue(States gameState) {
        switch (gameState) {
            case X_WINS:
                System.out.println("X wins");
                return false;

            case O_WINS:
                System.out.println("O wins");
                return false;

            case DRAW:
                System.out.println("Draw");
                return false;

            case CONTINUE:
                //System.out.println("Game not finished");
                return true;
            default:
                System.out.println("Error wrong state");
                return true;
        }
    }

    public static Command setCommand() {
        Command command = Command.BAD_PARAMETERS;

        while (command == Command.BAD_PARAMETERS) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input command:");
            String inputCommand = scanner.nextLine();
            command = extractInputCommand(inputCommand);
        }
        return command;
    }

    public static boolean controlCoordinates(String coordinates) {
        coordinates = coordinates.replaceAll(" ", "");
        if (Pattern.compile("\\D").matcher(coordinates).find()) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if (coordinates.length() > 2) {
            System.out.println("Wrong length of coordinates!");
            return false;
        }

        int row = Integer.parseInt(coordinates.substring(0, 1)) - 1;
        int column = Integer.parseInt(coordinates.substring(1, 2)) - 1;

        if (row < 0 || row > 2 || column < 0 || column > 2) {
            //System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (GameTable.getValue(row, column) != '_') {
            //System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        return true;
    }

    public static Command extractInputCommand(String inputCommand) {
        String[] commands = inputCommand.split(" ");

        if (commands.length == 1 && commands[0].equalsIgnoreCase("exit")) {
            //System.out.println("Goodbye!");
            System.exit(-1);
            //return Command.EXIT;
        }
        if (commands.length == 3 && commands[0].equalsIgnoreCase("start")) {
            if (commands[1].equalsIgnoreCase("easy") && commands[2].equalsIgnoreCase("user")) {
                return Command.EASY_VS_USER;
            } else if (commands[1].equalsIgnoreCase("easy") && commands[2].equalsIgnoreCase("easy")) {
                return Command.EASY_VS_EASY;
            } else if (commands[1].equalsIgnoreCase("easy") && commands[2].equalsIgnoreCase("medium")) {
                return Command.EASY_VS_MEDIUM;
            } else if (commands[1].equalsIgnoreCase("easy") && commands[2].equalsIgnoreCase("hard")) {
                return Command.EASY_VS_HARD;
            }else if (commands[1].equalsIgnoreCase("medium") && commands[2].equalsIgnoreCase("user")) {
                return Command.MEDIUM_VS_USER;
            } else if (commands[1].equalsIgnoreCase("medium") && commands[2].equalsIgnoreCase("easy")) {
                return Command.MEDIUM_VS_EASY;
            } else if (commands[1].equalsIgnoreCase("medium") && commands[2].equalsIgnoreCase("medium")) {
                return Command.MEDIUM_VS_MEDIUM;
            } else if (commands[1].equalsIgnoreCase("medium") && commands[2].equalsIgnoreCase("hard")) {
                return Command.MEDIUM_VS_HARD;
            }else if (commands[1].equalsIgnoreCase("hard") && commands[2].equalsIgnoreCase("user")) {
                return Command.HARD_VS_USER;
            } else if (commands[1].equalsIgnoreCase("hard") && commands[2].equalsIgnoreCase("easy")) {
                return Command.HARD_VS_EASY;
            } else if (commands[1].equalsIgnoreCase("hard") && commands[2].equalsIgnoreCase("medium")) {
                return Command.HARD_VS_MEDIUM;
            } else if (commands[1].equalsIgnoreCase("hard") && commands[2].equalsIgnoreCase("hard")) {
                return Command.HARD_VS_HARD;
            }else if (commands[1].equalsIgnoreCase("user") && commands[2].equalsIgnoreCase("user")) {
                return Command.USER_VS_USER;
            } else if (commands[1].equalsIgnoreCase("user") && commands[2].equalsIgnoreCase("easy")) {
                return Command.USER_VS_EASY;
            } else if (commands[1].equalsIgnoreCase("user") && commands[2].equalsIgnoreCase("medium")) {
                return Command.USER_VS_MEDIUM;
            } else if (commands[1].equalsIgnoreCase("user") && commands[2].equalsIgnoreCase("hard")) {
                return Command.USER_VS_HARD;
            }
        }
        System.out.println("Bad parameters!");
        return Command.BAD_PARAMETERS;

    }

}
