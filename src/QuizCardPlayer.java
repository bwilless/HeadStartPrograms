import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;
import java.io.*;

public class QuizCardPlayer {

	private JTextArea display;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private QuizCard currentCard;
	private int currentCardIndex;
	private JFrame frame;
	private JButton nextButton;
	private boolean isShowAnswer;
	
	public static void main (String[] args) {
		QuizCardPlayer reader = new QuizCardPlayer();
		reader.go();
	}
	
	public void go() {
		
		frame = new JFrame("Quiz Card Player");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 18);
		
		display = new JTextArea(10, 20);
		display.setFont(bigFont);
		
		display.setLineWrap(true);
		display.setEditable(false);
		
		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		nextButton = new JButton("Show Question");
		
		mainPanel.add(qScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
		
		loadMenuItem.addActionListener(new OpenMenuListener());
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(640, 500);
		frame.setVisible(true);
		
	}

	public class NextCardListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			
			if(isShowAnswer) {
				// show the answer because they've seen the question
				display.setText(currentCard.getAnswer());
				nextButton.setText("Next Card");
				isShowAnswer = false;
			} else {
				// show next question
				if(currentCardIndex < cardList.size()) {
					showNextCard();
				} else {
					display.setText("That was the last card");
					nextButton.setEnabled(false);
				}
			}
		}
	}
	
	public class OpenMenuListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());
		}
	}
	
	
	private void loadFile(File file) {
		
		cardList = new ArrayList<QuizCard>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null) {
				makeCard(line);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Could not read the card file: " + file.getName() );
			e.printStackTrace();}
		
		// Set the current card index to the first element
		currentCardIndex = 0;
		
		// Start the quizzing by showing the first card!
		showNextCard();
	}
	
	private void makeCard(String lineToParse) {
		
		String[] result = lineToParse.split("/");
		QuizCard card = new QuizCard(result[0], result[1]);
		cardList.add(card);
		System.out.println("Added card: " + card);
	
	}
	
	private void showNextCard() {
		
		currentCard = cardList.get(currentCardIndex);
		currentCardIndex++;
		display.setText(currentCard.getQuestion());
		nextButton.setText("Show Answer");
		isShowAnswer = true;
	}
	
	class QuizCard{
		
		private String question;
		private String answer;
		private boolean showMe;
		
		public String getQuestion() {
			return question;
		}
		public String getAnswer() {
			return answer;
		}
		
		public boolean showCard() {
			return showMe;
		}
		
		QuizCard(String ques, String answ){

			question = ques;
			answer = answ;
			showMe = true;
		}
	
		public String toString() {
			return String.format("Question: %s\n Answer: %s \n", question, answer);
		}
	}
}

