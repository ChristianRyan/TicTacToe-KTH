import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Creates the visual graphical interface of the Tic Tac Toe program for the user.
 * @author Christian Ryan
 *
 */
public class TicTacToeView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public TicTacToeModel tttModel;
	public TicTacToeManipulation tttMani;

	public final int GRIDSIZE; // number of cells within the grid. i.e. 3x3 => GRIDSIZE = 3.
	public final int width = 600; // Size of window (X-coordinate).
	public final int height = 600; // Size of window (Y-coordinate).

	public JButton gameButtons[][]; // Used to know what button was pressed.
	public JPanel gamePanels[][]; // Panels to give message to user if used panel has been pressed.
/**
 * Creates the main window of the game. The panels and buttons are to be created and place upon construction.
 * @param tttModel - Logical model of Tic Tac Toe inteface.
 */
	public TicTacToeView(TicTacToeModel tttModel) {
		this.tttModel = tttModel;
		this.GRIDSIZE = this.tttModel.GRIDSIZE;

		setSize(width, height);
		setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		setLocationRelativeTo(null);

		gameButtons = new JButton[GRIDSIZE][GRIDSIZE];
		gamePanels = new JPanel[GRIDSIZE][GRIDSIZE];
		
		//Placing the buttons from the controller depending on the predefined GRIDSIZE
		for (int row = 0; row < GRIDSIZE; row++) {
			for (int column = 0; column < GRIDSIZE; column++) {
				tttMani = new TicTacToeManipulation(this.tttModel, this);
				gameButtons[row][column] = tttMani.ticTacToeButton;
				gamePanels[row][column] = tttMani;
				gameButtons[row][column].addActionListener(this); //Used to know which button is pressed within the grid.
				gamePanels[row][column].addMouseListener(new MouseListen()); // Used to know which panel is pressed.
				add(tttMani);
			}
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/**
	 * Panel can only be pressed if the button has been removed. This means that the user has clicked on the same cell twice.
	 * If this happens then the user is presented with appropriate information of their action.
	 * @author Christian Ryan
	 *
	 */
	class MouseListen implements MouseListener {
		public void mousePressed(MouseEvent e) {
			JFrame repeatPress = new JFrame("You've already clicked on this cell");
			JLabel repeatText = new JLabel("You've already clicked on this cell. Please carefully press on another cell.");
			repeatPress.add(repeatText);
			repeatPress.setSize(450, 70);
			repeatPress.setVisible(true);
			repeatPress.setLocationRelativeTo(null);
			repeatPress.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		public void mouseClicked(MouseEvent arg0) {		
		}
		public void mouseEntered(MouseEvent arg0) {
		}
		public void mouseExited(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}

/**
 * If a game button is pressed, it sends the source information to the model which processes the information
 * and ensures that the next move is correctly created.
 */
	public void actionPerformed(ActionEvent ae) {
		for (int row = 0; row < GRIDSIZE; row++) {
			for (int column = 0; column < GRIDSIZE; column++) {
				if (ae.getSource() == gameButtons[row][column]) { // Tells us which button was clicked.
					System.out.println("Game button " + row + " " + column + " was clicked.");
					tttModel.updateBoard(row, column, TicTacToeManipulation.isCross); //Updates logical map of X and O's.
					tttMani.checkIfOver();
				}
			}
		}
	}
}