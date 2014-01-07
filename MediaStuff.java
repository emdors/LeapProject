import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.leapmotion.leap.Gesture;

public class MediaStuff {

	private MediaPlayer mediaPlayer1, mediaPlayer2;
	private ArrayList<Media> songlist;

	public MediaStuff() {
		JFXPanel fxPanel = new JFXPanel();

		FilenameFilter mp3Filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".mp3")) {
					return true;
				} else {
					return false;
				}
			}
		};

		// this will look for a directory named "sounds" in our project
		// directory
		File soundDir = new File("sounds");
		songlist = new ArrayList<Media>();

		for (String currFile : soundDir.list(mp3Filter)) {
			File currMP3FullPath = new File(soundDir + File.separator
					+ currFile);
			// The Media constructor takes a full qualified filename for the
			// piece of media...
			// the toURI() method returns a fully qualified filename of the
			// form:
			// file://path/to/folder/and/song.mp3
			// the file:// is like http:// or ftp://...it indicates what
			// protocol to use and
			// where the object is (a URI is a Uniform Resource Identifier)
			Media currSong = new Media(currMP3FullPath.toURI().toString());
			songlist.add(currSong);
		}

		// exit and print angry message if there are no songs found
		if (songlist.isEmpty()) {
			System.out.println("Error...no mp3 files found in "
					+ soundDir.toURI());
			System.exit(1);
		}

		mediaPlayer1 = new MediaPlayer(songlist.get(0));
		if (songlist.size() > 1)
			mediaPlayer2 = new MediaPlayer(songlist.get(1));
		else {
			System.out
					.println("Only found one song...button two now controls a copy of song 1");
			mediaPlayer2 = new MediaPlayer(songlist.get(0));
		}

	}

	public void raiseVolumeSong(int yes) {
		if (yes == 1) {// add parameter to make it easier to add other songs
			double currVolume = mediaPlayer1.getVolume();
			if (currVolume <= 0.9)
				mediaPlayer1.setVolume(Math.abs(currVolume + 0.1));
		} else {
			double currVolume = mediaPlayer2.getVolume();
			if (currVolume <= 0.9)
				mediaPlayer2.setVolume(Math.abs(currVolume + 0.1));
		}
	}

	public void lowerVolumeSong(int yes) {
		if (yes == 1) {// add parameter to make it easier to add other songs
			double currVolume = mediaPlayer1.getVolume();
			if (currVolume <= 0.9)
				mediaPlayer1.setVolume(Math.abs(currVolume - 0.1));
		} else {
			double currVolume = mediaPlayer2.getVolume();
			if (currVolume <= 0.9)
				mediaPlayer2.setVolume(Math.abs(currVolume - 0.1));
		}
	}

	public void playSong(int yes) {
		if (yes == 1) {
			if (mediaPlayer1.getCurrentRate() > 0.0)
				mediaPlayer1.pause();
			else
				mediaPlayer1.play();
		} else {
			if (mediaPlayer2.getCurrentRate() > 0.0)
				mediaPlayer2.pause();
			else
				mediaPlayer2.play();
		}
	}

	public void scratchSong(int yes) {
		if (yes == 1) {
			if (mediaPlayer1.getCurrentRate() > 0.0) {
				// rewind song by x milliseconds (where x is parameter to
				// Duration
				// constructor)
				Duration currPos = mediaPlayer1.getCurrentTime();
				currPos = currPos.subtract(new Duration(500.0));
				mediaPlayer1.seek(currPos);
			} else
				mediaPlayer1.play();
		} else {
			if (mediaPlayer1.getCurrentRate() > 0.0) {
				// rewind song by x milliseconds (where x is parameter to
				// Duration
				// constructor)
				Duration currPos = mediaPlayer2.getCurrentTime();
				currPos = currPos.subtract(new Duration(500.0));
				mediaPlayer2.seek(currPos);
			} else
				mediaPlayer2.play();
		}
	}

	public boolean isPlaying(int i) {
		if (i == 1) {
			if (mediaPlayer1.getCurrentRate() > 0.0)
				return true;
			else
				return false;
		} else {
			if (mediaPlayer2.getCurrentRate() > 0.0)
				return true;
			else
				return false;
		}
	}

	public double getVolume(int i) {
		if (i == 1)
			return mediaPlayer1.getVolume();
		else
			return mediaPlayer2.getVolume();
	}
}
