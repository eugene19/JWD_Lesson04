package by.epamtc.degtyarovea;

import java.util.Arrays;

public class MatrixCreator {

    public static void main(String[] args) {
        int size = 4;
        int[] array = {1, 2, 3, 4};

        // Task 1
        System.out.printf("1. Matrix: %n%s%n",
                matrixToString(createSquareMatrix(size)));

        // Task 2
        System.out.printf("2. Matrix with diagonal %n%s%n",
                matrixToString(createMatrixWithDiagonal(size)));

        // Task 3
        System.out.printf("3. Square array with 1 and 0 %n%s%n",
                matrixToString(createMatrixWithOneZero(size)));

        // Task 4
        System.out.printf("4. Square array with pow numbers %n%s%n",
                matrixToString(createSquareArrayWithPowNumbers(array)));

        // Task 5
        int[][] leftMatrix = {{1, -1}, {2, 0}, {3, 0}};
        int[][] rightMatrix = {{1, 1}, {2, 0}};

        System.out.printf("5. Multiplication of matrix: %n%s%n",
                matrixToString(multiplyMatrix(leftMatrix, rightMatrix)));

        // Task 6
        System.out.printf("6. Magic square: %n%s%n",
                matrixToString(createMagicSquare(size)));
    }

    public static int[][] createSquareMatrix(int size) {
        if (size <= 0) {
            return null;
        }
        int[][] array = new int[size][size];

        for (int i = 0; i < size; i++) {
            int counter = 1;

            if (i % 2 == 0) {
                for (int j = 0; j < size; j++) {
                    array[i][j] = counter++;
                }
            } else {
                for (int j = size - 1; j >= 0; j--) {
                    array[i][j] = counter++;
                }
            }
        }

        return array;
    }

    public static String matrixToString(int[][] matrix) {
        if (matrix == null) {
            return "Empty";
        }
        StringBuilder builder = new StringBuilder();

        for (int[] row : matrix) {
            builder.append(Arrays.toString(row))
                    .append("\n");
        }

        return builder.toString();
    }

    private static String matrixToString(double[][] matrix) {
        if (matrix == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();

        for (double[] row : matrix) {
            builder.append(Arrays.toString(row))
                    .append("\n");
        }

        return builder.toString();
    }

    public static int[][] createMatrixWithDiagonal(int size) {
        if (size <= 0) {
            return null;
        }
        int[][] array = new int[size][size];

        for (int i = 0; i < array.length; i++) {
            array[i][i] = (i + 1) * (i + 2);
        }

        return array;
    }

    public static int[][] createMatrixWithOneZero(int size) {
        if (size <= 0) {
            return null;
        }
        int[][] array = new int[size][size];
        int zeroInRow = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (j < zeroInRow ^ (array[i].length - 1 - j >= zeroInRow)) ? 1 : 0;
            }

            if (i < array.length / 2 - 1) {
                zeroInRow++;
            }
            if (i > array.length / 2 - 1) {
                zeroInRow--;
            }
        }

        return array;
    }

    public static double[][] createSquareArrayWithPowNumbers(int[] array) {
        double[][] square = new double[array.length][array.length];

        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) {
                square[i][j] = Math.pow(array[j], i + 1);
            }
        }

        return square;
    }

    public static int[][] multiplyMatrix(int[][] left, int[][] right) {
        if (left[0].length != right.length) {
            return null;
        }
        int[][] result = new int[left.length][right[0].length];

        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < right[0].length; j++) {
                for (int k = 0; k < right.length; k++) {
                    result[i][j] += left[i][k] * right[k][j];
                }
            }
        }

        return result;
    }

    public static int[][] createMagicSquare(int size) {
        if (size % 2 != 0) {
            return createOddMagic(size);
        } else if (size % 4 == 0) {
            return createEvenMagic(size);
        } else {
            return null;
        }
    }

    public static int[][] createOddMagic(int size) {
        int[][] matrix = new int[size][size];
        int x = size / 2;
        int y = size - 1;
        int count = 1;

        while (hasZero(matrix)) {
            matrix[(matrix.length - 1) - y][x] = count++;

            if (x == matrix.length - 1) {
                x = -1;
            }
            if (y >= matrix.length - 1) {
                y = -1;
            }
            x++;
            y++;

            if (matrix[matrix.length - 1 - y][x] != 0) {
                y--;
            }
        }

        return matrix;
    }

    private static boolean hasZero(int[][] matrix) {
        int count = 0;
        for (int[] ints : matrix) {
            for (int i : ints) {
                if (i == 0) {
                    count++;
                }
            }
        }
        return count != 0;
    }

    public static int[][] createEvenMagic(int size) {
        int[][] matrix = fillMatrix(size);

        rearrangementMainDiagonal(matrix);
        rearrangementSecondaryDiagonal(matrix);

        return matrix;
    }

    private static void rearrangementSecondaryDiagonal(int[][] matrix) {
        int count = 0;

        for (int i = 0; i < matrix.length / 2; i++) {
            int temp;
            temp = matrix[matrix.length - 1 - count][i];
            matrix[matrix.length - 1 - count][i] = matrix[i][matrix.length - 1 - count];
            matrix[i][matrix.length - 1 - count] = temp;
            count++;
        }
    }

    private static void rearrangementMainDiagonal(int[][] matrix) {
        int count = 0;

        for (int i = 0; i < matrix.length / 2; i++) {
            int temp;
            temp = matrix[i][i];
            matrix[i][i] = matrix[matrix.length - 1 - count][matrix.length - 1 - count];
            matrix[matrix.length - 1 - count][matrix.length - 1 - count] = temp;
            count++;
        }
    }

    private static int[][] fillMatrix(int size) {
        int[][] matrix = new int[size][size];
        int count = 1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = count++;
            }
        }

        return matrix;
    }
}
