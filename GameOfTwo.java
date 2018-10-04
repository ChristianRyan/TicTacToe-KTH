import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Graphical inteface of home screen of either othello or tic tac toe.
 * @author Christian Ryan
 *
 */
public class GameOfTwo {
	JFrame welcomeFrame; // Frame of home screen
	
	public static void main(String[] args){
		new GameOfTwo();
	}
	
	public GameOfTwo(){
		welcomeScreen();
	}
	/**
	 * Presents a welcome screen to the user where they may choose to play tic tac toe or othello! 
	 */
	public void welcomeScreen(){
		welcomeFrame = new JFrame("Game Of Two");
		JButton othelloButton = new JButton("OTHELLO");
		othelloButton.addActionListener(new Othello());
		JButton ticTacToeButton = new JButton("TIC-TAC-TOE");
		ticTacToeButton.addActionListener(new TicTacToe());

		welcomeFrame.setLayout(new FlowLayout());
		JLabel congratsText = new JLabel("Welcome to this Game of Two. Please pick a game:" );
		congratsText.setFont(new Font("Helvetica", 1, 14));
		
		welcomeFrame.add(congratsText);
		welcomeFrame.add(othelloButton);
		welcomeFrame.add(Box.createRigidArea(new Dimension(5,40)));
		welcomeFrame.add(ticTacToeButton);
		welcomeFrame.add(Box.createRigidArea(new Dimension(5,40)));
		welcomeFrame.setSize(400, 120);
		welcomeFrame.setLocationRelativeTo(null);
		welcomeFrame.setVisible(true);
		welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * If the othello button was pressed, then the graphical interface (view) of the game is presented.
	 * @author Christian Ryan
	 *
	 */
	class Othello implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		}
	}
	/**
	 * If the tic tac toe button was pressed, then the graphical interface (view) of the game is presented.
	 * @author Christian Ryan
	 *
	 */
	class TicTacToe implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			welcomeFrame.dispose();
			new TicTacToeView(new TicTacToeModel());
		}
	}

}
