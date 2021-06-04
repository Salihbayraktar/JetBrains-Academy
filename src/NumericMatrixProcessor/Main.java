package NumericMatrixProcessor;

import java.util.Scanner;

public class Main {

    public static double determinantResult = 0;

    public static double[][] getMatrixFromUser() {
        Scanner consoleScanner = new Scanner(System.in);
        Scanner stringScanner;
        System.out.println("Enter size of matrix:");
        String input = consoleScanner.nextLine();
        stringScanner = new Scanner(input);
        int m1 = stringScanner.nextInt();
        int n1 = stringScanner.nextInt();
        double[][] matrix = new double[m1][n1];
        System.out.println("Enter matrix:");
        for (int r = 0; r < m1; r++) {
            input = consoleScanner.nextLine();
            stringScanner = new Scanner(input);
            for (int c = 0; c < n1; c++) {
                matrix[r][c] = stringScanner.nextDouble();
            }

        }
        return matrix;
    }

    public static double[][] addition(double[][] firstMatrix, double[][] secondMatrix) {

        if (firstMatrix.length != secondMatrix.length || firstMatrix[0].length != secondMatrix[0].length) {
            System.out.println("The operation cannot be performed.");
            return new double[][]{};
        }

        double[][] sumMatrix;

        sumMatrix = new double[firstMatrix.length][firstMatrix[0].length];
        for (int r = 0; r < firstMatrix.length; r++) {

            for (int c = 0; c < firstMatrix[0].length; c++) {
                sumMatrix[r][c] = firstMatrix[r][c] + secondMatrix[r][c];
            }
        }
        return sumMatrix;
    }

    public static void printMatrix(double[][] matrix) {

        System.out.println("--------------------");
        System.out.println("The result is : ");

        for (double[] doubles : matrix) {
            for (int c = 0; c < matrix[0].length; c++) {
                System.out.print(doubles[c] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    public static double[][] multiplication(double[][] matrix, double constant) {

        for (int r = 0; r < matrix.length; r++) {

            for (int c = 0; c < matrix[0].length; c++) {

                matrix[r][c] = matrix[r][c] * constant;
            }
        }

        return matrix;
    }

    public static double[][] multiplication(double[][] firstMatrix, double[][] secondMatrix) {

        if (firstMatrix[0].length != secondMatrix.length) {
            System.out.println("The operation cannot be performed.");
            return new double[][]{};
        }

        double[][] multiplicationMatrix = new double[firstMatrix.length][secondMatrix[0].length];

        for (int r = 0; r < multiplicationMatrix.length; r++) {

            for (int c = 0; c < multiplicationMatrix[0].length; c++) {


                for (int commonRowColumn = 0; commonRowColumn < secondMatrix.length; commonRowColumn++) {

                    multiplicationMatrix[r][c] += firstMatrix[r][commonRowColumn] * secondMatrix[commonRowColumn][c];

                }

            }

        }

        return multiplicationMatrix;
    }

    public static double[][] mainDiagonal(double[][] matrix) {
        double[][] diagonal = new double[matrix[0].length][matrix.length];

        for (int r = 0; r < matrix.length; r++) {

            for (int c = 0; c < matrix[0].length; c++) {

                diagonal[c][r] = matrix[r][c];

            }

        }

        return diagonal;
    }

    public static double[][] sideDiagonal(double[][] matrix) {   //

        double[][] diagonal = new double[matrix[0].length][matrix.length];

        for (int r = 0; r < matrix.length; r++) {

            for (int c = matrix[0].length - 1; c >= 0; c--) {

                diagonal[r][c] = matrix[matrix[0].length - c - 1][matrix.length - r - 1];

            }

        }

        return diagonal;
    }

    public static double[][] verticalLine(double[][] matrix) {
        double[][] verticalLine = new double[matrix.length][matrix[0].length];

        for (int r = 0; r < matrix.length; r++) {

            for (int c = 0; c < matrix[0].length; c++) {

                verticalLine[r][c] = matrix[r][matrix[0].length - c - 1];

            }

        }

        return verticalLine;

    }

    public static double[][] horizontalLine(double[][] matrix) {
        double[][] horizontalLine = new double[matrix.length][matrix[0].length];

        for (int r = 0; r < matrix.length; r++) {

            for (int c = 0; c < matrix[0].length; c++) {

                horizontalLine[matrix.length - r - 1][c] = matrix[r][c];

            }

        }

        return horizontalLine;
    }

    public static double determinant(double[][] matrix) {

        if (matrix.length == 2 && matrix[0].length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double[][] subMatrix = new double[matrix.length - 1][matrix[0].length - 1];

        determinantResult = 0;

        for (int i = 0; i < matrix.length; i++) {

            double cofactor = matrix[0][i];

            int sign = i % 2 == 0 ? 1 : -1;

            for (int r = 0; r < matrix.length - 1; r++) {

                for (int c = 0, mainMatrixColumn = 0; c < matrix[0].length - 1; c++, mainMatrixColumn++) {

                    if (mainMatrixColumn == i) mainMatrixColumn++;

                    subMatrix[r][c] = matrix[r + 1][mainMatrixColumn];

                }

            }

            determinantResult += sign * cofactor * determinant(subMatrix);

        }

        return determinantResult;

    }

    public static double[][] inverseMatrix(double[][] matrix) {
        double determinant = determinant(matrix);

        if (determinant == 0) {
            System.out.println("This matrix doesn't have an inverse.");
            return new double[][]{};
        }

        double[][] cofactorAndDiagonalMatrix = mainDiagonal(cofactorMatrix(matrix));

        double constant = 1 / determinant;

        return multiplication(cofactorAndDiagonalMatrix, constant);
    }

    public static double[][] cofactorMatrix(double[][] matrix) {

        double[][] cofactorMatrix = new double[matrix.length][matrix[0].length];
        double[][] subMatrix = new double[matrix.length - 1][matrix[0].length - 1];

        for (int r = 0; r < cofactorMatrix.length; r++) {

            for (int c = 0; c < cofactorMatrix[0].length; c++) {

                for (int subR = 0, mainR = 0; subR < cofactorMatrix.length - 1; subR++, mainR++) {


                    for (int subC = 0, mainC = 0; subC < cofactorMatrix[0].length - 1; subC++, mainC++) {

                        if (mainR == r) mainR++;

                        if (mainC == c) mainC++;

                        subMatrix[subR][subC] = matrix[mainR][mainC];

                    }

                }

                int sign = (r + c) % 2 == 0 ? 1 : -1;
                cofactorMatrix[r][c] = determinant(subMatrix) * sign;

            }

        }

        return cofactorMatrix;
    }


    public static void main(String[] args) {

        double[][] matrix;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. Add matrices
                    2. Multiply matrix by a constant
                    3. Multiply matrices
                    4. Transpose matrix
                    5. Calculate a determinant
                    6. Inverse matrix
                    0. Exit""");
            int condition = scanner.nextInt();
            System.out.println("Your choice: " + condition);
            switch (condition) {
                case 0 -> {
                    System.out.println("Good Bye!");
                    System.exit(-1);
                }
                case 1 -> printMatrix(addition(getMatrixFromUser(), getMatrixFromUser()));
                case 2 -> {
                    System.out.println("Enter the constant: ");
                    double constant = scanner.nextDouble();
                    printMatrix(multiplication(getMatrixFromUser(), constant));
                }
                case 3 -> printMatrix(multiplication(getMatrixFromUser(), getMatrixFromUser()));
                case 4 -> {
                    System.out.println("""
                            1. Main diagonal
                            2. Side diagonal
                            3. Vertical line
                            4. Horizontal line""");
                    scanner.nextLine();
                    int transposeCondition = scanner.nextInt();
                    System.out.println("Your choice: " + transposeCondition);
                    switch (transposeCondition) {
                        case 1 -> printMatrix(mainDiagonal(getMatrixFromUser()));
                        case 2 -> printMatrix(sideDiagonal(getMatrixFromUser()));
                        case 3 -> printMatrix(verticalLine(getMatrixFromUser()));
                        case 4 -> printMatrix(horizontalLine(getMatrixFromUser()));
                        default -> System.out.println("Invalid Transpose matrix Choice!");
                    }
                }
                case 5 -> {
                    matrix = getMatrixFromUser();
                    if (matrix.length != matrix[0].length) {
                        System.out.println("The operation cannot be performed.");
                        break;
                    }
                    double determinantResult = determinant(matrix);
                    System.out.println("The result is : ");
                    System.out.println(determinantResult);
                }
                case 6 -> {
                    matrix = getMatrixFromUser();
                    if (matrix.length != matrix[0].length) {
                        System.out.println("The operation cannot be performed.");
                        break;
                    }
                    printMatrix(inverseMatrix(matrix));
                }
                default -> System.out.println("Invalid choice!");
            }

        }

    }
}

