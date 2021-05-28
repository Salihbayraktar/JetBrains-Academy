package Battleship;

import java.util.Scanner;

public class GameTable {
    private char[][] gameTable = new char[12][12];  // Extend the array to make controls easily
    private char[][] gameTableWithFog = new char[12][12];



    public GameTable() {
        fillTables();
    }

    public void setGameTable(char[][] gameTable) {
        this.gameTable = gameTable;
    }

    public char[][] getGameTable() {
        return gameTable;
    }

    public void fillTables() {
        for (int r = 0; r < gameTable.length; r++) {
            for (int c = 0; c < gameTable[0].length; c++) {
                gameTable[r][c] = '~';
                gameTableWithFog[r][c] = '~';
            }
        }
    }

    public void printGameTable() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int r = 1; r < gameTable.length - 1; r++) {
            char letter = (char) (64 + r);
            System.out.print(letter + " ");
            for (int c = 1; c < gameTable[0].length - 1; c++) {
                System.out.print(gameTable[r][c] + " ");
            }
            System.out.println();
        }
    }

    public void printGameTableWithFog() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int r = 1; r < gameTableWithFog.length - 1; r++) {
            char letter = (char) (64 + r);
            System.out.print(letter + " ");
            for (int c = 1; c < gameTableWithFog[0].length - 1; c++) {
                System.out.print(gameTableWithFog[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
        printGameTable();
    }


    public int[] extractCoordinates(String coordinatesCombined) {
        String[] coordinatesArr = coordinatesCombined.split(" ");
        int[] coordinates = new int[coordinatesArr.length * 2];  // 0 is firstRow, 1 is firstColumn, 2 is secondRow, 3 is secondColumn

        if (coordinatesArr.length == 1) {

            coordinates[0] = (byte) coordinatesArr[0].charAt(0) - 'A' + 1;

            coordinatesArr[0] = coordinatesArr[0].substring(1);

            coordinates[1] = Integer.parseInt(coordinatesArr[0]);

        } else if (coordinatesArr.length == 2) {

            coordinates[0] = (byte) coordinatesArr[0].charAt(0) - 'A' + 1;

            coordinatesArr[0] = coordinatesArr[0].substring(1);

            coordinates[1] = Integer.parseInt(coordinatesArr[0]);

            coordinates[2] = (byte) coordinatesArr[1].charAt(0) - 'A' + 1;

            coordinatesArr[1] = coordinatesArr[1].substring(1);

            coordinates[3] = Integer.parseInt(coordinatesArr[1]);

            if (coordinates[0] > coordinates[2]) {                   // arrange row coordinates from small to large

                coordinates[0] = coordinates[0] + coordinates[2];
                coordinates[2] = coordinates[0] - coordinates[2];
                coordinates[0] = coordinates[0] - coordinates[2];

            }

            if (coordinates[1] > coordinates[3]) {                   // arrange column coordinates from small to large

                coordinates[1] = coordinates[1] + coordinates[3];
                coordinates[3] = coordinates[1] - coordinates[3];
                coordinates[1] = coordinates[1] - coordinates[3];

            }
        }
        return coordinates;
    }

    public boolean controlCoordinates(int[] coordinates, int cells) {

        int rowSubtraction = (coordinates[2] - coordinates[0]) + 1;
        int columnSubtraction = (coordinates[3] - coordinates[1]) + 1;

        if (columnSubtraction == 1 && rowSubtraction != cells) {
            System.out.println("Error! Wrong length of the ship! Try again:");
            return false;
        } else if (rowSubtraction == 1 && columnSubtraction != cells) {
            System.out.println("Error! Wrong length of the ship! Try again:");
            return false;
        } else if (rowSubtraction != 1 && columnSubtraction != 1) {
            System.out.println("Error! Ship locations cannot be diagonal");
            return false;
        }

        if (coordinates[1] == coordinates[3]) {
            for (int i = coordinates[0]; i <= coordinates[2]; i++) {
                if (gameTable[i][coordinates[1]] == 'O') {
                    System.out.println("Error! Found 'O' on current location ROW");
                    return false;
                }
            }
        }

        if (coordinates[0] == coordinates[2]) {
            for (int i = coordinates[1]; i <= coordinates[3]; i++) {
                if (gameTable[coordinates[0]][i] == 'O') {
                    System.out.println("Error! Found 'O' on current location COLUMN");
                    return false;
                }
            }
        }

        int startColumn = coordinates[0] - 1;
        //System.out.println("startColumn : " + startColumn);
        int startRow = coordinates[1] - 1;                       //Start : Outside top left
        //System.out.println("startRow    : " + startRow);
        int endColumn = coordinates[2] + 1;
        //System.out.println("endColumn   : " + endColumn);
        int endRow = coordinates[3] + 1;                         //End : Outside bottom right
        //System.out.println("endRow      : "+ endRow);

        for (int i = startRow; i <= endRow; i++) {
            if (gameTable[startColumn][i] == 'O') {
                System.out.println("Error! Found 'O' on the top of the ship : " + false);
                return false;   //Control the top of the ship
            }
            if (gameTable[endColumn][i] == 'O') {
                System.out.println("Error! Found 'O' on the down of the ship : " + false);
                return false;     //Control the down of the ship
            }
        }

        for (int i = startColumn; i <= endColumn; i++) {
            if (gameTable[i][startRow] == 'O') {
                System.out.println("Error! Found 'O' on the left side of the ship : " + false);
                return false;      //Control the left side of the ship
            }
            if (gameTable[i][endRow] == 'O') {
                System.out.println("Error! Found 'O' on the right side of the ship : " + false);
                return false;        //Control the right side of the ship
            }
        }
        return true;
    }

    public void drawShips(int[] coordinates, int cells) {

        int rowSubtraction = (coordinates[2] - coordinates[0]) + 1;
        int columnSubtraction = (coordinates[3] - coordinates[1]) + 1;

        if (rowSubtraction == cells) {                                         //Is a vertical ship
            for (int i = coordinates[0]; i <= coordinates[2]; i++) {
                gameTable[i][coordinates[1]] = 'O';
            }

        } else if (columnSubtraction == cells) {                               //Is a horizontal ship
            for (int i = coordinates[1]; i <= coordinates[3]; i++) {
                gameTable[coordinates[0]][i] = 'O';
            }
        }
    }

    public void setShipsCoordinates() {
        Scanner scanner = new Scanner(System.in);
        String[] shipNames = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] cells = {5, 4, 3, 3, 2};
        printGameTable();

        for (int i = 0; i < 5; i++) {
            while (true) {
                System.out.printf("Enter the coordinates of the %s (%d cells): \n", shipNames[i], cells[i]);

                String shipCoordinate = scanner.nextLine();

                int[] shipCoordinates = extractCoordinates(shipCoordinate);

                if (controlCoordinates(shipCoordinates, cells[i])) {
                    drawShips(shipCoordinates, cells[i]);
                    break;
                }

            }
            printGameTable();
        }
    }

    public boolean controlShot(int[] shotCoordinate) {
        if (shotCoordinate[0] < 1 || shotCoordinate[0] > 10 || shotCoordinate[1] < 1 || shotCoordinate[1] > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }
        return true;
    }

    public boolean isGameEnd(char[][] gameTable) {
        for (int r = 1; r < gameTable.length - 1; r++) {
            for (int c = 1; c < gameTable.length - 1; c++) {
                if (gameTable[r][c] == 'O') return false;
            }
        }
        return true;
    }

    public boolean isShipSunk(int[] shootCoordinate) {
        int row = shootCoordinate[0];
        int column = shootCoordinate[1];
        //Vertical control start
        while (gameTable[row][column] == 'X') {
            row--;
            if (gameTable[row][column] == 'O') return false;
            if (gameTable[row][column] == '~') break;
        }
        row = shootCoordinate[0];
        while (gameTable[row][column] == 'X') {
            row++;
            if (gameTable[row][column] == 'O') return false;
            if (gameTable[row][column] == '~') break;
        }
        row = shootCoordinate[0];
        //Vertical control end

        //Horizontal control start
        while (gameTable[row][column] == 'X') {
            column--;
            if (gameTable[row][column] == 'O') return false;
            if (gameTable[row][column] == '~') break;
        }
        column = shootCoordinate[1];
        while (gameTable[row][column] == 'X') {
            column++;
            if (gameTable[row][column] == 'O') return false;
            if (gameTable[row][column] == '~') break;
        }
        //Horizontal control end
        return true;

    }

    public void shotTable() {
        System.out.println("Take a shot!");
        Scanner scanner = new Scanner(System.in);
        String shotLocation = scanner.nextLine();

        int[] shotCoordinate = extractCoordinates(shotLocation);

        if (controlShot(shotCoordinate)) {
            if (gameTable[shotCoordinate[0]][shotCoordinate[1]] == 'O' || gameTable[shotCoordinate[0]][shotCoordinate[1]] == 'X') {
                gameTable[shotCoordinate[0]][shotCoordinate[1]] = 'X';
                if (isShipSunk(shotCoordinate)) {
                    System.out.println("You sank a ship!");
                } else {
                    System.out.println("You hit a ship!");
                }
            } else {
                gameTable[shotCoordinate[0]][shotCoordinate[1]] = 'M';
                System.out.println("You missed!");
            }
        }
    }
}

