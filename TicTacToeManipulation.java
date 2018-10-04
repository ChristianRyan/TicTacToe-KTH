import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates the controller for the user for the Tic Tac Toe game.
 * @author Christian Ryan
 *
 */
public class TicTacToeManipulation extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton ticTacToeButton; // User-controlled button.
	public JFrame gameOverFrame; // User-controlled end screen.
	private int width; // Used to make appropriate sizes for the figures.
	private int height; // Used to make appropriate sizes for the figures.
	private int GRIDSIZE; // Used to make appropriate sizes for the figures.

	public TicTacToeModel tttModel;
	public TicTacToeView tttView;
	
	public static boolean isCross = false; // Start with a cross.
/**
 * Creates a JPanel that adds a button upon construction.
 * @param myModel - Mathematical and logical model for Tic Tac Toe.
 * @param myView - Graphical interface for the user.
 */
	TicTacToeManipulation(TicTacToeModel myModel, TicTacToeView myView) {
		this.tttView = myView;
		this.GRIDSIZE = myView.GRIDSIZE;
		this.width = myView.width;
		this.height = myView.height;
		this.tttModel = myModel;
		
		// Creates the implemented JPanel and determines it's appearance.
		setLayout(new BorderLayout());
		setSize(this.width, this.height);
		ticTacToeButton = new JButton();
		add(ticTacToeButton);
		ticTacToeButton.addActionListener(new MyListener());
	}

	class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			remove(ticTacToeButton); // Removes button after button press as it is no longer useful. (Contents may be shown)
			toggleCross(); // Toggles the X/O.
			repaint();
			revalidate();
		}
	}
	//Toggles the X/O. If X has just been used then O is next up, vice versa.
	public void toggleCross() {
		if (isCross) {
			isCross = false;
		} else {
			isCross = true;
		}
	}
/**
 * Draws simplistic circles according to its parameters.
 * @param g - Graphical component.
 * @param x - x coordinate of circle's center. 
 * @param y - y coordinate of circle's center.
 * @param diameter - diameter of circle.
 */
	public void drawCircle(Graphics g, int x, int y, int diameter) {
		x = x - (diameter / 2);
		y = y - (diameter / 2);
		g.fillOval(x, y, diameter, diameter); // Fills the circle with a predetermined color.
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isCross) { // If X:
			g.setColor(Color.RED);
			for (int i = 0; i < 10; i++) { //This is to get a fat X symbol.
				g.drawLine((int) Math.round(width / (GRIDSIZE * 4)) + i, (int) Math.round(height / (GRIDSIZE * 4)),
						(int) Math.round((3 * width) / (GRIDSIZE * 4)) + i,
						(int) Math.round((3 * height) / (GRIDSIZE * 4)));
				g.drawLine((int) Math.round(width / (GRIDSIZE * 4)) + i,
						(int) Math.round((3 * height) / (GRIDSIZE * 4)),
						(int) Math.round((3 * width) / (GRIDSIZE * 4)) + i, (int) Math.round(height / (GRIDSIZE * 4)));
			}
		} else { // If O:
			g.setColor(Color.BLUE);
			drawCircle(g, (int) Math.round(width / (GRIDSIZE * 2)), (int) Math.round(height / (GRIDSIZE * 2)),
					(int) Math.round(width / (GRIDSIZE * 2)));
			g.setColor(getBackground()); // This is to get a donought-shaped circle.
			drawCircle(g, (int) Math.round(width / (GRIDSIZE * 2)), (int) Math.round(height / (GRIDSIZE * 2)),
					(int) Math.round(width / (GRIDSIZE * 2)) - 20);
		}

	}
	
	/**
	 * Presents the user with appropriate information regarding who won 
	 * and offers the user to replay, exit or go back to the home screen.
	 */
		public void checkIfOver() {
			if (tttModel.gameOver) {
				gameOverFrame = new JFrame("GAME OVER");
				gameOverFrame.setLayout(new FlowLayout());
				//Buttons of which the user may choose from.
				JButton restartButton = new JButton("RESTART");
				restartButton.addActionListener(new Restart());
				JButton exitButton = new JButton("EXIT");
				exitButton.addActionListener(new Exit());
				JButton homeScreenButton = new JButton("HOME SCREEN");
				homeScreenButton.addActionListener(new HomeScreen());
				
				JLabel winnerString;
				
				if (!TicTacToeManipulation.isCross) { //If cross won
					winnerString = new JLabel("Cross (Red)");
					winnerString.setForeground(Color.RED);
				} else { //If nought won
					winnerString = new JLabel("Nought (Blue)"); 
					winnerString.setForeground(Color.BLUE);
				}
				
				JLabel congratsText = new JLabel("CONGRATULATIONS! ");
				JLabel congratsText2 = new JLabel("won the game!");
				congratsText.setFont(new Font("Helvetica", 1, 14));
				congratsText2.setFont(new Font("Helvetica", 1, 14));
				winnerString.setFont(new Font("Helvetica", 1, 14));
				// Adds descriptive text
				gameOverFrame.add(congratsText);
				gameOverFrame.add(winnerString);
				gameOverFrame.add(congratsText2);
				//Adds control buttons
				gameOverFrame.add(restartButton);
				gameOverFrame.add(Box.createRigidArea(new Dimension(5, 40)));
				gameOverFrame.add(homeScreenButton);
				gameOverFrame.add(Box.createRigidArea(new Dimension(5, 40)));
				gameOverFrame.add(exitButton);
				
				gameOverFrame.setSize(400, 120);
				gameOverFrame.setLocationRelativeTo(this);
				gameOverFrame.setVisible(true);
				gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		}
		/**
		 * Closes current windows and calls the Tic Tac Toe interace agian.
		 * @author Christian Ryan
		 */
		class Restart implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				gameOverFrame.dispose();
				tttView.dispose();
				new TicTacToeView(new TicTacToeModel());
			}
		}
		/**
		 * Closes current windows and releases all occupied resources.
		 * @author Christian Ryan
		 */
		class Exit implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				gameOverFrame.dispose();
				tttView.dispose();
			}
		}
		/**
		 * Closes current windows and calls GameOfTwo. I.e. restarting the program!
		 * @author Christian Ryan
		 */
		class HomeScreen implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				gameOverFrame.dispose();
				tttView.dispose();
				new GameOfTwo();
			}
		}

}
