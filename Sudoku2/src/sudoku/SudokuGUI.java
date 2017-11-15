package sudoku;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

/**
 *
 * @author cstuser
 */
public class SudokuGUI extends JFrame implements ActionListener, FocusListener {

    static final int SIZE = 9;

    JTextField[][] cellTF = new JTextField[9][9];
    JButton resetB, solveB, exitB;
    int[][] sudokuAr = new int[SIZE][SIZE];

    public SudokuGUI() {

        JFrame wframe = new JFrame();
        wframe.setTitle("Sudoku Solver");
        Container pane = wframe.getContentPane();
        pane.setLayout(null);
        wframe.setSize(390, 455);
        Font textFont = new Font("Arial", Font.BOLD, 16);
        

        VarifyInput inputVarify = new VarifyInput();

        //creating array of cells
        int y = 2;
        for (int i = 0; i < SIZE; i++) {
            int x = 2;
            for (int j = 0; j < SIZE; j++) {
                cellTF[i][j] = new JTextField(1);
                cellTF[i][j].setSize(40, 40);
                cellTF[i][j].setLocation(x, y);
                cellTF[i][j].setHorizontalAlignment(JTextField.CENTER);
                cellTF[i][j].setFont(textFont);
                cellTF[i][j].setDocument(new JTextFieldLimit(1));
                cellTF[i][j].setInputVerifier(inputVarify);
                //cellTF[i][j].addActionListener(this);
                cellTF[i][j].addFocusListener(this);

                pane.add(cellTF[i][j]);
                if (j == 2 || j == 5) {
                    x = x + 45;
                } else {
                    x = x + 40;
                }

            }
            if (i == 2 || i == 5) {
                y = y + 45;
            } else {
                y = y + 40;
            }

        }

        resetB = new JButton("Reset");
        solveB = new JButton("Solve");
        exitB = new JButton("Exit");
        pane.add(resetB);
        pane.add(solveB);
        pane.add(exitB);
        resetB.setSize(100, 30);
        solveB.setSize(100, 30);
        exitB.setSize(100, 30);

        resetB.setLocation(13, 380);
        solveB.setLocation(137, 380);
        exitB.setLocation(261, 380);

        solveB.addActionListener(this);
        resetB.addActionListener(this);
        exitB.addActionListener(this);

        wframe.setVisible(true);
        wframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Solve":
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        try {
                            sudokuAr[i][j] = Integer.parseInt(cellTF[i][j].getText());
                        } catch (NumberFormatException nfEx) {
                            sudokuAr[i][j] = 0;
                        }
                    }

                }
                if (!Sudoku.isValidAll(SIZE, sudokuAr)) {
                   JOptionPane.showMessageDialog(null, "Verify initial cells values", "Input Error", JOptionPane.WARNING_MESSAGE);
                    //System.out.println("error");
                } else {

                    if (Sudoku.doSudoku(sudokuAr)) {
                        for (int i = 0; i < SIZE; i++) {
                            for (int j = 0; j < SIZE; j++) {
                                if(cellTF[i][j].getText().equals("")){
                                cellTF[i][j].setText(sudokuAr[i][j] + "");
                                cellTF[i][j].setForeground(Color.BLUE);
                                }
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No solution found!", "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "Reset":
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        cellTF[i][j].setText(null);
                        cellTF[i][j].setForeground(Color.BLACK);
                        cellTF[i][j].setBackground(Color.WHITE);
                    }
                }
                break;
            case "Exit":
                System.exit(0);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (e.getSource() == cellTF[i][j]) {
                    cellTF[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                
                if (e.getSource() == cellTF[i][j] && !cellTF[i][j].getText().equals("")) {
                    cellTF[i][j].setBackground(Color.GRAY);
                }
            }
        }

    }
}
