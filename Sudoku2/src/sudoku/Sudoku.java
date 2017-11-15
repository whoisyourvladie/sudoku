package sudoku;

import java.util.*;

public class Sudoku {

//    static Scanner console = new Scanner(System.in);
    static public int[][] sudo = new int[9][9];
//    static public int countrec = 0;

    public static void main(String[] args) {
        SudokuGUI ss=new SudokuGUI();

          
       
    }

    

    public static boolean doSudoku(int[][] sudo) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                if (sudo[i][j] != 0) {
                    continue;
                }
                for (int num = 1; num <= 9; num++) {
                    if (isValid(i, j, num, sudo)) {
                        sudo[i][j] = num;
                        if (doSudoku(sudo)) {

                            return true;
                        } else {

                            sudo[i][j] = 0;
                        }
                    }
                }
                return false;
            }
        }

        return true;

    }

    public static int[] position() {
        int[] pos = new int[2];
        for (int i = 8; i >= 0; i--) {
            for (int j = 8; j >= 0; j--) {
                if (sudo[i][j] == 0) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        return pos;
    }

    public static int[] nextPosition() {
        int[] newpos = new int[2];
        int occurence = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (sudo[i][j] == 0) {
                    occurence++;

                    if (occurence == 2) {

                        newpos[0] = i;
                        newpos[1] = j;
                        return newpos;
                    }
                }

            }
        }
        return newpos;
    }

//    public static boolean isDone() {
//        for (int[] s : sudo) {
//            for (int ss : s) {
//                if (ss == 0) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

//    public static int[][] fillSudo() {
//        int[][] sud = new int[9][9];
//
//        System.out.println("Input three ints: 1.row 2.column 3.value\n"
//                + "To terminate input any negative");
//        do {
//            try {
//
//                sud[console.nextInt()][console.nextInt()] = console.nextInt();
//
//            } catch (InputMismatchException e) {
//                System.out.println("Wrong input");
//
//            }
//        } while (console.nextInt() > 0);
//        return sud;
//    }

    public static boolean isValidAll(int size, int[][] sudoku) {
        
        boolean valid=true;
        
        
        

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (sudoku[i][j] != 0) {
                    valid=isValidFixed(i, j,sudoku);
                    if (valid==false) {
                        
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static boolean isValidFixed(int row, int col,  int[][] sudo) {

        int row_n = 0, row_k = 0, col_n = 0, col_k = 0;

        for (int i = 0; i < 9; i++) {
            
            if ((sudo[row][i] == sudo[row][col] || sudo[i][col] == sudo[row][col])&&(i!=col&&i!=row)) {
                //System.out.println("the first for line 69");
                return false;
            }
        }
        if (row <= 2 && col <= 2) {
            //  System.out.println("line 75");
            row_n = 0;
            row_k = 2;
            col_n = 0;
            col_k = 2;
        } else if (row <= 2 && col > 2 && col <= 5) {
            //   System.out.println("line 81");
            row_n = 0;
            row_k = 2;
            col_n = 3;
            col_k = 5;
        } else if (row <= 2 && col > 5 && col <= 8) {
            //  System.out.println("line 87");
            row_n = 0;
            row_k = 2;
            col_n = 6;
            col_k = 8;
        } else if (row > 2 && row <= 5 && col <= 2) {
            // System.out.println("line 93");
            row_n = 3;
            row_k = 5;
            col_n = 0;
            col_k = 2;
        } else if (row > 2 && row <= 5 && col > 2 && col <= 5) {
            // System.out.println("line 99");
            row_n = 3;
            row_k = 5;
            col_n = 3;
            col_k = 5;
        } else if (row > 2 && row <= 5 && col > 5 && col <= 8) {
            //  System.out.println("line 105");
            row_n = 3;
            row_k = 5;
            col_n = 6;
            col_k = 8;
        } else if (row > 5 && row <= 8 && col <= 2) {
            //  System.out.println("line 111");
            row_n = 6;
            row_k = 8;
            col_n = 0;
            col_k = 2;
        } else if (row > 5 && row <= 8 && col > 2 && col <= 5) {
            //   System.out.println("line 118");
            row_n = 6;
            row_k = 8;
            col_n = 3;
            col_k = 5;
        } else if (row > 5 && row <= 8 && col > 5 && col <= 8) {
            //    System.out.println("line 123");
            row_n = 6;
            row_k = 8;
            col_n = 6;
            col_k = 8;
        }

        for (int i = row_n; i <= row_k; i++) {
            for (int j = col_n; j <= col_k; j++) {
                //        System.out.println("subSudo[" + i + "][" + j + "]=" + sud[i][j]);
                if ((sudo[i][j] == sudo[row][col])&&(i!=row&&i!=col)) {
                    //          System.out.println("line 133");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(int row, int col, int value, int[][] sudo) {

        int row_n = 0, row_k = 0, col_n = 0, col_k = 0;

        for (int i = 0; i < 9; i++) {
            if (sudo[row][i] == value || sudo[i][col] == value) {
                //System.out.println("the first for line 69");
                return false;
            }
        }
        if (row <= 2 && col <= 2) {
            //  System.out.println("line 75");
            row_n = 0;
            row_k = 2;
            col_n = 0;
            col_k = 2;
        } else if (row <= 2 && col > 2 && col <= 5) {
            //   System.out.println("line 81");
            row_n = 0;
            row_k = 2;
            col_n = 3;
            col_k = 5;
        } else if (row <= 2 && col > 5 && col <= 8) {
            //  System.out.println("line 87");
            row_n = 0;
            row_k = 2;
            col_n = 6;
            col_k = 8;
        } else if (row > 2 && row <= 5 && col <= 2) {
            // System.out.println("line 93");
            row_n = 3;
            row_k = 5;
            col_n = 0;
            col_k = 2;
        } else if (row > 2 && row <= 5 && col > 2 && col <= 5) {
            // System.out.println("line 99");
            row_n = 3;
            row_k = 5;
            col_n = 3;
            col_k = 5;
        } else if (row > 2 && row <= 5 && col > 5 && col <= 8) {
            //  System.out.println("line 105");
            row_n = 3;
            row_k = 5;
            col_n = 6;
            col_k = 8;
        } else if (row > 5 && row <= 8 && col <= 2) {
            //  System.out.println("line 111");
            row_n = 6;
            row_k = 8;
            col_n = 0;
            col_k = 2;
        } else if (row > 5 && row <= 8 && col > 2 && col <= 5) {
            //   System.out.println("line 118");
            row_n = 6;
            row_k = 8;
            col_n = 3;
            col_k = 5;
        } else if (row > 5 && row <= 8 && col > 5 && col <= 8) {
            //    System.out.println("line 123");
            row_n = 6;
            row_k = 8;
            col_n = 6;
            col_k = 8;
        }

        for (int i = row_n; i <= row_k; i++) {
            for (int j = col_n; j <= col_k; j++) {
                //        System.out.println("subSudo[" + i + "][" + j + "]=" + sud[i][j]);
                if (sudo[i][j] == value) {
                    //          System.out.println("line 133");
                    return false;
                }
            }
        }
        return true;
    }

    public static void printSudoku(int[][] sudo) {
        for (int i = 0; i < 9; i++) {
            System.out.print("\n————————————\n|");
            for (int j = 0; j < 9; j++) {
                System.out.print(sudo[i][j] + "|");
            }
        }
        System.out.println("\n");
    }

}
