
import javax.sound.midi.*;
import java.util.*;

public class MiniMusicCmdLine {

	public static void main (String[] args) {
		MiniMusicCmdLine mini = new MiniMusicCmdLine();
		int instrument, note = 0;
		Scanner input = new Scanner(System.in);

		
		while(note != -1) {
			System.out.print("Enter two integers (Instrument), (Note).  -1 -1 to exit:  ");
			instrument = input.nextInt();
			note = input.nextInt();
			System.out.println("Instrument: " + instrument + " Note: " + note);
			if(note < 0) {
				break;
			}
			
			mini.play(instrument, note);
		}
		
	}
		
	public void play(int instrument, int note) {

		try {
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

//			MidiEvent event = null;

			ShortMessage first = new ShortMessage();
			first.setMessage(192, 1, instrument, 0);
			MidiEvent changeInstrument = new MidiEvent(first, 1);
			track.add(changeInstrument);
			
			
			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1, note, 100);
			MidiEvent noteOn = new MidiEvent(a, 1);
			track.add(noteOn);
			
			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, note, 100);
			MidiEvent noteOff = new MidiEvent(b, 16);
			track.add(noteOff);
			
			player.setSequence(seq);
			player.start();
			
		} catch(MidiUnavailableException ex) {
			ex.printStackTrace();;

		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}

	
}
