import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class BeatBox {
	
	private JFrame frame;
	private DrumType drums[];
	private Font font;
			
	public static void main (String[] args) {
	
		BeatBox gui = new BeatBox();
		gui.setupGui();
		
		
	}
	
	private void setupGui() {

		// Create the JFrame
		frame = new JFrame("Cyber Beat Box");

		JButton startButton;
		JButton stopButton;
		JButton tempoUpButton;
		JButton tempoDownButton;
		JPanel checkBoxPanel[];
		
		
		// Create the 4 buttons we need and a JPannel to hold them all
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		tempoUpButton = new JButton("Tempo Up");
		tempoDownButton = new JButton("Tempo Down");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		// Wire the buttons to thier handlers
		startButton.addActionListener(new StartButton());
		stopButton.addActionListener(new StopButton());
		tempoUpButton.addActionListener(new TempoUpButton());
		tempoDownButton.addActionListener(new TempoDownButton());
				
		// Place the swing (button) objects into the panel
		// Add the panel to the frame's East region
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(tempoUpButton);
		buttonPanel.add(tempoDownButton);
		frame.getContentPane().add(BorderLayout.EAST, buttonPanel);

		// define a font for the JLabel ojbects
		font = new Font("Courier", Font.BOLD,15);
		
		// drums will hold each of the 16 drums.  
		drums = new DrumType[16];
		
		// Create each drumType
		String[] drumTypeList = {"  Bass Drum", "  Closed Hi-Hat", "  Open Hi-Hat", 
				"  Acoustic Snare", "  Crash Cymbal", "  Hand Clap", "  High Tom", 
				"  Hi Bongo", "  Maracas", "  Whistle", "  Low Conga", "  Cowbell", 
				"  Visraslap", "  Low-mid Tom", "  High Agogo", "  Open Hi Conga"};
		
		// instrumentTypePanel will hold all the labels for each drum type
		JPanel instrumentTypePanel = new JPanel();
		instrumentTypePanel.setLayout(new BoxLayout(instrumentTypePanel, BoxLayout.Y_AXIS));

		// instrumentcheckBoxPanel is the center panel that will contain 16 checkBoxPanels.
		JPanel instrumentCheckBoxPanel = new JPanel();
		instrumentCheckBoxPanel.setLayout(new BoxLayout(instrumentCheckBoxPanel, BoxLayout.Y_AXIS));
		
		checkBoxPanel = new JPanel[16]; 		
		
		/*  Build everything out.  What do we need to do?
		 *  1.  For each drum type
		 *  1.1 set the drum name
		 *  1.2 add the rowLabel to the instrumentTypePanel
		 *  1.3 add the already created JCheckBoxe(s) to the checkBoxPanel 
		 *  2.  add the populated/built-out checkBoxPanel to the instrumentCheckBoxPanel*/
		
		
		for(int i = 0; i < 16; i++) {

			// Create each drum list object.  Pass in a drum name String. 
			drums[i] = new DrumType(drumTypeList[i]);
						
			// Add the drum.rowLabel to the instrumentTypePanel
			instrumentTypePanel.add(drums[i].rowLabel);

			// create a new checkBoxPanel.
			checkBoxPanel[i] = new JPanel();
			checkBoxPanel[i].setLayout(new BoxLayout(checkBoxPanel[i], BoxLayout.X_AXIS));
			
			// Add the checkBox List for the current drum type
			for(int j = 0; j < 16; j++) {
				
				checkBoxPanel[i].add(drums[i].checkBoxList[j]);
			}
			instrumentCheckBoxPanel.add(checkBoxPanel[i]);
		}
		
		frame.getContentPane().add(BorderLayout.WEST, instrumentTypePanel);
		frame.getContentPane().add(BorderLayout.CENTER, instrumentCheckBoxPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 400);
		frame.setVisible(true);
	}
	
	class DrumType {
		
		String drum;
		JCheckBox [] checkBoxList = new JCheckBox[16];  
		JLabel rowLabel;

	DrumType (String drumName){
		drum = drumName;
		rowLabel = new JLabel(drumName);
		rowLabel.setFont(font);
		for(int i = 0; i < 16; i++) {
			checkBoxList[i] = new JCheckBox();
			checkBoxList[i].setSelected(false);
			
		}
	}
		
	}

	class StartButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("User hit the Start Button");
		}
	}

	class StopButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("User hit the Stop Button");
		}
	}

	class TempoUpButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("User hit the Tempo Up Button");
		}
	}


	class TempoDownButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("User hit the Tempo Down Button");
		}
	}
}