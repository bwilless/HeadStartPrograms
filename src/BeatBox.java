import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class BeatBox {
	
	private JFrame frame;
	private JButton startButton;
	private JButton stopButton;
	private JButton tempoUpButton;
	private JButton tempoDownButton;
	private DrumType drums[];
	private JPanel checkBoxPanel[];
	private Font font;
			
	public static void main (String[] args) {
	
		BeatBox gui = new BeatBox();
		gui.setupGui();
		
		
	}
	
	private void setupGui() {

		// Create the JFrame
		frame = new JFrame("Cyber Beat Box");
		
		// Create the 4 buttons we need and a JPannel to hold them all
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		tempoUpButton = new JButton("Tempo Up");
		tempoDownButton = new JButton("Tempo Down");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		// Place the swing (button) objects into the panel
		// Add the panel to the frame's East region
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(tempoUpButton);
		buttonPanel.add(tempoDownButton);
		frame.getContentPane().add(BorderLayout.EAST, buttonPanel);

		font = new Font("Courier", Font.BOLD,15);
		
		// drums will hold each of the 16 drums.  
		drums = new DrumType[16];
		
		// Create each drumType
		String[] drumTypeList = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Visraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
		
		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(1);
		grid.setHgap(2);
		
		// instrumentTypePanel will hold all the labels for each drum type
		JPanel instrumentTypePanel = new JPanel(grid);
		instrumentTypePanel.setLayout(new BoxLayout(instrumentTypePanel, BoxLayout.Y_AXIS));

		// instrumentcheckBoxPanel is the center panel that will contain 16 checkBoxPanels.
		JPanel instrumentCheckBoxPanel = new JPanel(grid);
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
			checkBoxPanel[i] = new JPanel(grid);
			checkBoxPanel[i].setLayout(new BoxLayout(checkBoxPanel[i], BoxLayout.X_AXIS));
			
			// Add the checkBox List for the current drum tpye
			for(int j = 0; j < 16; j++) {
				
				checkBoxPanel[i].add(drums[i].checkBoxList[j]);
			}
			instrumentCheckBoxPanel.add(checkBoxPanel[i]);
		}
		
		frame.getContentPane().add(BorderLayout.WEST, instrumentTypePanel);
		frame.getContentPane().add(BorderLayout.CENTER, instrumentCheckBoxPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
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

}
