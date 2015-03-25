import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class generates the User Interface for the Sudoku and connects the
 * different buttons with respective panels.
 */

public class SudokuView {

	private JPanel gameBoardPanel;
	private SudokuSolver sudokuSolver;
	private int[][] gameBoard;
	private OneLetterField[][] f;
	private JButton clearButton, solveButton;
	private JPanel commandPanel;

	/**
	 * Constructor.
	 * 
	 * @param sudokuSolver
	 * @param title
	 */
	public SudokuView(SudokuSolver sudokuSolver, String title) {
		f = new OneLetterField[9][9];
		this.sudokuSolver = sudokuSolver;
		gameBoardPanel = new JPanel();
		commandPanel = new JPanel();
		
		clearButton = new JButton("Clear");
		solveButton = new JButton("Solve");
		clearButton.addActionListener(new ClearButtonListener());
		solveButton.addActionListener(new SolveButtonListener());

		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		commandPanel.setLayout(new FlowLayout());
		gameBoardPanel.setPreferredSize(new Dimension(600, 600));
		gameBoardPanel.setLayout(new GridLayout(9, 9));
		

		frame.add(commandPanel, BorderLayout.SOUTH);
		frame.add(gameBoardPanel, BorderLayout.CENTER);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				f[i][j] = new OneLetterField();
				if ((j > 2 && j < 6) && (i < 3 || i > 5)) {
					f[i][j].setBackground(Color.LIGHT_GRAY);

				} else if (i > 2 && i < 6 && (j < 3 || j > 5)) {

					f[i][j].setBackground(Color.LIGHT_GRAY);
				}

				gameBoardPanel.add(f[i][j]);
			}
		}

		commandPanel.add(clearButton);
		commandPanel.add(solveButton);
		gameBoard = sudokuSolver.getGameBoard();

		frame.pack();
		frame.setVisible(true);
	}

	private void updateDrawing() {
		gameBoardPanel.repaint();
	}
	

	/** Button Listener for Clear. */

	private class ClearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					f[i][j].setText("");
				}
			}
			sudokuSolver.clearBoard();
			updateDrawing();
		}
	}

	/** Button Listener for Solve. */

	private class SolveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sudokuSolver.clearBoard();
			int temp = 10;
			
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (!f[i][j].getText().isEmpty()) {
						temp = Integer.parseInt(f[i][j].getText());
						if (temp == 0) {
							JOptionPane.showMessageDialog(null,
									"Zeros not allowed", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						gameBoard[i][j] = temp;
					}
				}
			}
			if (!sudokuSolver.gameBoardScan()) {
				JOptionPane.showMessageDialog(null,
						"Sudoku could not be solved", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			sudokuSolver.setGameBoard(gameBoard);
			if (sudokuSolver.solve()) {
				gameBoard = sudokuSolver.getGameBoard();
			} else {
				JOptionPane.showMessageDialog(null,
						"Sudoku could not be solved", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					f[i][j].setText(Integer.toString(gameBoard[i][j]));
				}
			}
		}
	}
}