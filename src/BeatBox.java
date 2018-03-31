import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class BeatBox {
	
	private JFrame frame;
	private ArrayList<DrumType> drums;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	
	// Create each drumType
	String[] drumTypeList = {"  Bass Drum", "  Closed Hi-Hat", "  Open Hi-Hat", 
			"  Acoustic Snare", "  Crash Cymbal", "  Hand Clap", "  High Tom", 
			"  Hi Bongo", "  Maracas", "  Whistle", "  Low Conga", "  Cowbell", 
			"  Visraslap", "  Low-mid Tom", "  High Agogo", "  Open Hi Conga"};

	int[] instrumentKey = {32,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	
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

		// drums will hold each of the 16 drums.  
		drums = new ArrayList<DrumType>();
		
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
			drums.add(new DrumType(drumTypeList[i]));
						
			// Add the drum.rowLabel to the instrumentTypePanel
			instrumentTypePanel.add(drums.get(i).rowLabel);

			// create a new checkBoxPanel.
			checkBoxPanel[i] = new JPanel();
			checkBoxPanel[i].setLayout(new BoxLayout(checkBoxPanel[i], BoxLayout.X_AXIS));
			
			// Add the checkBox List for the current drum type
			for(int j = 0; j < 16; j++) {
				
				checkBoxPanel[i].add(drums.get(i).checkBoxList.get(j));
			}
			instrumentCheckBoxPanel.add(checkBoxPanel[i]);
		}
		
		frame.getContentPane().add(BorderLayout.WEST, instrumentTypePanel);
		frame.getContentPane().add(BorderLayout.CENTER, instrumentCheckBoxPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 400);
		frame.setVisible(true);
		
		setUpMidi();
	}
	
	public void buildTrackAndStart() {
		int[] trackList = null;
		
		sequence.deleteTrack(track);
		track = sequence.createTrack();
		
		for(int i = 0; i < 16; i++) {
			trackList = new int[16];
			
			int key = instrumentKey[i];
			
			for(int j = 0; j < 16; j++) {
				
				JCheckBox jc = drums.get(i).checkBoxList.get(j);
				
				if(jc.isSelected()) {
					trackList[j] = key;
				} else {
					trackList[j] = 0;
				}
			}
			
			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,16));
			
		}
		
		track.add(makeEvent(192,9,1,0,15));
		
		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
	
	class DrumType {
		
		String drum;
		ArrayList<JCheckBox> checkBoxList = new ArrayList<JCheckBox>();
		JLabel rowLabel;
		// define a font for the JLabel ojbects
		Font font = new Font("Courier", Font.BOLD,15);
		
		DrumType (String drumName){
			drum = drumName;
			rowLabel = new JLabel(drumName);
			rowLabel.setFont(font);
			for(int i = 0; i < 16; i++) {
				checkBoxList.add(new JCheckBox());
				checkBoxList.get(i).setSelected(false);
			}
		}
		
	}

	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {e.printStackTrace();}
	}
		
	class StartButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			System.out.println("User hit the Start Button");
			buildTrackAndStart();
		}
	}

	class StopButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			System.out.println("User hit the Stop Button");
			sequencer.stop();
		}
	}

	class TempoUpButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			System.out.println("User hit the Tempo Up Button");
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor * 1.03));
		}
	}


	class TempoDownButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			System.out.println("User hit the Tempo Down Button");
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor * .97));

		}
	}
	
	public void makeTracks(int[] list) {
		for (int i = 0; i < 16; i++) {
			int key = list[i];
			
			if(key != 0) {
				track.add(makeEvent(144,9,key,100, i));
				track.add(makeEvent(128,9,key,100, i+1));
			}
		}
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		
		MidiEvent event = null;
		
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		}catch (Exception e ) {e.printStackTrace();}
		
		return event;
		
	}
	
}