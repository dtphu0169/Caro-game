package Caro;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class CaroGui extends JFrame {
	private Container pane;
	private String currentPlayer;
	private JButton[][] board;
	private boolean hasWinner;
	private JMenu menu;
	private JMenuBar menubar;
	private JMenuItem quit;
	private JMenuItem newGame, backMenu;

	public CaroGui(String mode) {
		super();
		pane = getContentPane();
		pane.setLayout(new GridLayout(12, 12));
		currentPlayer = "x";
		board = new JButton[12][12];
		hasWinner = false;
		setIconImage(CaroIntro.logo.getImage());
		intializeBoard(mode);
		intializeMenubar();
		setTitle("Caro game");
		setSize(600, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void intializeMenubar() {
		menubar = new JMenuBar();
		menu = new JMenu("File");
		newGame = new JMenuItem("New game");
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetBoard();
			}
		});
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		backMenu = new JMenuItem("Back to Menu");
		backMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CaroIntro();
			}
		});
		menu.add(newGame);
		menu.add(backMenu);
		menu.add(quit);
		menubar.add(menu);
		setJMenuBar(menubar);
	}

	private void resetBoard() {
		currentPlayer = "x";
		hasWinner = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j].setText("");
			}
		}
	}

	private void intializeBoard(String mode) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				JButton btn = new JButton("");
				btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				board[i][j] = btn;

				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (((JButton) e.getSource()).getText().equals("") && hasWinner == false) {

							if (mode.equals("2 Player")) {
								btn.setText(currentPlayer);
								checkWinner();
								togglePlayer();
							}
							if (mode.equals("Easy Computer")) {
								btn.setText(currentPlayer);
								checkWinner();
								EasyCom();
								checkWinner();
								togglePlayer();
							}
							if (mode.equals("Normal Computer")) {
								btn.setText(currentPlayer);
								checkWinner();
								NormalCom();
								checkWinner();
								togglePlayer();
							}
							if (mode.equals("Hard Computer")) {
								btn.setText(currentPlayer);
								checkWinner();
								HardCom();
								checkWinner();
								togglePlayer();
							}
						}
					}
				});
				pane.add(btn);
			}

		}

	}

	private void togglePlayer() {
		if (currentPlayer.equals("x")) {
			currentPlayer = "o";
		} else {
			currentPlayer = "x";
		}
	}

	private void BufferCom() {
		Random rd = new Random();
		int i = rd.nextInt(12);
		int j = rd.nextInt(12);
		if (board[i][j].getText().equals("") && !hasWinner) {
			board[i][j].setText(currentPlayer);
			checkWinner();
		} else if (hasWinner) {

		} else {
			BufferCom();
		}
	}

	private void EasyCom() {
		int countTemp = countStep();
		if(!hasWinner) {
		for (int i = 1; i < board.length-3; i++) {// hàng dọc
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].getText().equals(board[i+1][j].getText()) && 
						board[i][j].getText().equals(board[i+2][j].getText()) && 
						board[i][j].getText().equals(currentPlayer) && !hasWinner && 
						board[i+3][j].getText().equals("")) {
					togglePlayer();
					board[i+3][j].setText(currentPlayer);
					break;
				}
			}
		}
		for (int i = 1; i < board.length-3; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].getText().equals(board[i+1][j].getText()) && 
						board[i][j].getText().equals(board[i+2][j].getText()) && 
						board[i][j].getText().equals(board[i+3][j].getText()) && 
						board[i][j].getText().equals(currentPlayer) && !hasWinner && countTemp == countStep() &&
						board[i-1][j].getText().equals("")) {
					togglePlayer();
					board[i-1][j].setText(currentPlayer);
					break;
				}
			}
		}
		for (int i = 0; i < board.length; i++) {// hàng ngang
			for (int j = 1; j < board.length-3; j++) {
				if (board[i][j].getText().equals(board[i][j+1].getText()) && 
						board[i][j].getText().equals(board[i][j+2].getText()) && 
						board[i][j].getText().equals(currentPlayer) && !hasWinner && countTemp == countStep() &&
						board[i][j+3].getText().equals("")) {
					togglePlayer();
					board[i][j+3].setText(currentPlayer);
					break;
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 1; j < board.length-3; j++) {
				if (board[i][j].getText().equals(board[i][j+1].getText()) && 
						board[i][j].getText().equals(board[i][j+2].getText()) && 
						board[i][j].getText().equals(board[i][j+3].getText()) && 
						board[i][j].getText().equals(currentPlayer) && !hasWinner && countTemp == countStep() &&
						board[i][j-1].getText().equals("")) {
					togglePlayer();
					board[i][j-1].setText(currentPlayer);
					break;
				}
			}
		}
		for (int i = 1; i < board.length-4; i++) {// hàng chéo huyền
			for (int j = 1; j < board.length-4; j++) {
				if (board[i][j].getText().equals(board[i+1][j+1].getText()) && 
						board[i][j].getText().equals(board[i+2][j+2].getText()) && 
						board[i][j].getText().equals(currentPlayer) && !hasWinner && countTemp == countStep() &&
						board[i+3][j+3].getText().equals("")) {
					togglePlayer();
					board[i+3][j+3].setText(currentPlayer);
					break;
				}
			}
		}
		for (int i = 1; i < board.length-3; i++) {
			for (int j = 1; j < board.length-3; j++) {
				if (board[i][j].getText().equals(board[i+1][j+1].getText()) && 
						board[i][j].getText().equals(board[i+2][j+2].getText()) && 
						board[i][j].getText().equals(board[i+3][j+3].getText()) && 
						board[i][j].getText().equals(currentPlayer) && !hasWinner && countTemp == countStep() &&
						board[i-1][j-1].getText().equals("")) {
					togglePlayer();
					board[i-1][j-1].setText(currentPlayer);
					break;
				}
			}
		}
		for (int i = 0; i < board.length-3; i++) {// hàng chéo sắc
			for (int j = board.length-2; j > 3; j--) {
				if (board[i][j].getText().equals(board[i+1][j-1].getText()) && 
						board[i][j].getText().equals(board[i+2][j-2].getText()) && 
						board[i][j].getText().equals(currentPlayer) && !hasWinner && countTemp == countStep() &&
						board[i+3][j-3].getText().equals("")) {
					togglePlayer();
					board[i+3][j-3].setText(currentPlayer);
					break;
				}
			}
		}
		for (int i = 0; i < board.length-4; i++) {
			for (int j = board.length-1; j > 4; j--) {
				if (board[i][j].getText().equals(board[i+1][j-1].getText()) && 
						board[i][j].getText().equals(board[i+2][j-2].getText()) && 
						board[i][j].getText().equals(board[i+3][j-3].getText()) && 
						board[i][j].getText().equals(currentPlayer) && !hasWinner && countTemp == countStep() &&
						board[i+4][j-4].getText().equals("")) {
					togglePlayer();
					board[i+4][j-4].setText(currentPlayer);
					break;
				}
			}
		}
		if (countStep() == countTemp) {
			togglePlayer();
			BufferCom();
		}
	}}

	private void NormalCom() {
		if (!hasWinner) {
			togglePlayer();
			if (board[0][0].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(currentPlayer)
					&& board[2][2].getText().equals("")) {
				board[2][2].setText(currentPlayer);
			} else if (board[2][2].getText().equals(board[1][1].getText())
					&& board[1][1].getText().equals(currentPlayer) && board[0][0].getText().equals("")) {
				board[0][0].setText(currentPlayer);
			} else if (board[2][2].getText().equals(board[0][0].getText())
					&& board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals("")) {
				board[1][1].setText(currentPlayer);
			} else if (board[0][2].getText().equals(board[1][1].getText())
					&& board[1][1].getText().equals(currentPlayer) && board[2][0].getText().equals("")) {
				board[2][0].setText(currentPlayer);
			} else if (board[2][0].getText().equals(board[1][1].getText())
					&& board[1][1].getText().equals(currentPlayer) && board[0][2].getText().equals("")) {
				board[0][2].setText(currentPlayer);
			} else if (board[2][0].getText().equals(board[0][2].getText())
					&& board[2][0].getText().equals(currentPlayer) && board[1][1].getText().equals("")) {
				board[1][1].setText(currentPlayer);
			} else {
				for (int i = 0; i < board.length; i++) {
					if (board[i][0].getText().equals(board[i][1].getText())
							&& board[i][1].getText().equals(currentPlayer) && board[i][2].getText().equals("")) {
						board[i][2].setText(currentPlayer);
						break;
					} else if (board[i][2].getText().equals(board[i][1].getText())
							&& board[i][1].getText().equals(currentPlayer) && board[i][0].getText().equals("")) {
						board[i][0].setText(currentPlayer);
						break;
					} else if (board[i][2].getText().equals(board[i][0].getText())
							&& board[i][0].getText().equals(currentPlayer) && board[i][1].getText().equals("")) {
						board[i][1].setText(currentPlayer);
						break;
					} else if (board[0][i].getText().equals(board[1][i].getText())
							&& board[1][i].getText().equals(currentPlayer) && board[2][i].getText().equals("")) {
						board[2][i].setText(currentPlayer);
						break;
					} else if (board[2][i].getText().equals(board[1][i].getText())
							&& board[1][i].getText().equals(currentPlayer) && board[0][i].getText().equals("")) {
						board[0][i].setText(currentPlayer);
						break;
					} else if (board[2][i].getText().equals(board[0][i].getText())
							&& board[0][i].getText().equals(currentPlayer) && board[1][i].getText().equals("")) {
						board[1][i].setText(currentPlayer);
						break;
					} else if (i == board.length - 1) {
						togglePlayer();
						EasyCom();
						break;
					}
				}
			}
		}
	}

	private void HardCom() {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].getText() != "") {
					count++;
				}
			}
		}
		if (count == 1 && board[1][1].getText().equals(currentPlayer)) {
			togglePlayer();
			board[0][0].setText(currentPlayer);
		} else if (count == 1 && board[1][1].getText().equals("")) {
			togglePlayer();
			board[1][1].setText(currentPlayer);
		} else if (count == 3) {
			if (board[0][1].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)
					&& board[0][2].getText().equals("")) {
				togglePlayer();
				board[0][2].setText(currentPlayer);
			} else if (board[2][1].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)
					&& board[2][2].getText().equals("")) {
				togglePlayer();
				board[2][2].setText(currentPlayer);
			} else if (board[1][0].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)
					&& board[2][0].getText().equals("")) {
				togglePlayer();
				board[2][0].setText(currentPlayer);
			} else if (board[1][0].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer)
					&& board[0][0].getText().equals("")) {
				togglePlayer();
				board[0][0].setText(currentPlayer);// dự đoán đường chéo góc
			} else if (board[0][0].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)
					&& board[2][0].getText().equals("")) {
				togglePlayer();
				board[2][0].setText(currentPlayer);
			} else if (board[0][0].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)
					&& board[0][2].getText().equals("")) {
				togglePlayer();
				board[0][2].setText(currentPlayer);
			} else if (board[2][0].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer)
					&& board[0][0].getText().equals("")) {
				togglePlayer();
				board[0][0].setText(currentPlayer);
			} else if (board[2][0].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)
					&& board[2][2].getText().equals("")) {
				togglePlayer();
				board[2][2].setText(currentPlayer);
			} else if (board[0][2].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer)
					&& board[0][0].getText().equals("")) {
				togglePlayer();
				board[0][0].setText(currentPlayer);
			} else if (board[0][2].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)
					&& board[2][2].getText().equals("")) {
				togglePlayer();
				board[2][2].setText(currentPlayer);
			} else if (board[2][2].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer)
					&& board[0][2].getText().equals("")) {
				togglePlayer();
				board[0][2].setText(currentPlayer);
			} else if (board[2][2].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer)
					&& board[2][0].getText().equals("")) {
				togglePlayer();
				board[2][0].setText(currentPlayer);// dự đoán đường chéo ngựa
			} else if (board[2][2].getText().equals(currentPlayer) && board[0][0].getText().equals(currentPlayer)
					&& board[1][0].getText().equals("")) {
				togglePlayer();
				board[1][0].setText(currentPlayer);
			} else if (board[0][2].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)
					&& board[2][1].getText().equals("")) {
				togglePlayer();
				board[2][1].setText(currentPlayer);// tránh rơi điểm góc
			} else if (board[1][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)
					&& board[2][0].getText().equals("")) {
				togglePlayer();
				board[2][0].setText(currentPlayer);// dự đoán tạo 2 đường góc
			} else {// only run in count = 3
				NormalCom();
			}
		} else {
			NormalCom();
		}
	}

	private void checkWinner() {
		for (int i = 0; i < board.length - 4; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].getText().equals(board[i + 1][j].getText())
						&& board[i][j].getText().equals(board[i + 2][j].getText())
						&& board[i][j].getText().equals(board[i + 3][j].getText())
						&& board[i][j].getText().equals(board[i + 4][j].getText())
						&& board[i][j].getText().equals(currentPlayer) && !hasWinner) {
					JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
					hasWinner = true;
				}

			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length - 4; j++) {
				if (board[i][j].getText().equals(board[i][j + 1].getText())
						&& board[i][j].getText().equals(board[i][j + 2].getText())
						&& board[i][j].getText().equals(board[i][j + 3].getText())
						&& board[i][j].getText().equals(board[i][j + 4].getText())
						&& board[i][j].getText().equals(currentPlayer) && !hasWinner) {
					JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
					hasWinner = true;
				}
			}
		}

		for (int i = 0; i < board.length-4; i++) {
			for (int j = 0; j < board.length-4; j++) {
				if (board[i][j].getText().equals(board[i + 1][j + 1].getText())
						&& board[i][j].getText().equals(board[i + 2][j + 2].getText())
						&& board[i][j].getText().equals(board[i + 3][j + 3].getText())
						&& board[i][j].getText().equals(board[i + 4][j + 4].getText())
						&& board[i][j].getText().equals(currentPlayer) && !hasWinner) {
					JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
					hasWinner = true;
				}
			}
		}
		
		for (int i = 0; i < board.length-4; i++) {
			for (int j = board.length-1; j >3; j--) {
				if (board[i][j].getText().equals(board[i+1][j-1].getText())
						&& board[i][j].getText().equals(board[i+2][j-2].getText())
						&& board[i][j].getText().equals(board[i+3][j-3].getText())
						&& board[i][j].getText().equals(board[i+4][j-4].getText())
						&& board[i][j].getText().equals(currentPlayer) && !hasWinner) {
					JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
					hasWinner = true;
				}
			}
		}

	}

	private int countStep() {
		int count =0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (!board[i][j].getText().equals("")) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		new CaroGui("Easy Computer");
	}
}
