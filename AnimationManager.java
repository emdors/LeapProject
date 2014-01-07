import java.awt.Graphics;
import java.util.Iterator;

import javafx.scene.media.MediaPlayer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Gesture.Type;
import com.leapmotion.leap.HandList;

public class AnimationManager implements Runnable {

	// private JFrame mainJFrame;
	private DA drawingArea;

	private long timeAtEndOfLoop = 0, timeAtBeginningOfLoop = 0, pause = 0,
			timeLastGesture = 0;
	private final long DESIRED_DELAY = 40;

	private Controller leapMotionController;
	private Frame currFrame;
	private int blah;
	private MediaPlayer mediaPlayer1, mediaPlayer2;
	private MediaStuff mediaStuff;
	private String currgest;

	public AnimationManager(DA da) {
		drawingArea = da;
		leapMotionController = new Controller();
		blah = 0;
		mediaStuff = new MediaStuff();

		// can enable gestures here
		leapMotionController.enableGesture(Type.TYPE_SCREEN_TAP);
		leapMotionController.enableGesture(Type.TYPE_KEY_TAP);
		leapMotionController.enableGesture(Type.TYPE_CIRCLE);
		leapMotionController.enableGesture(Type.TYPE_SWIPE);
	}

	public void run() {
		while (true) {

			timeAtBeginningOfLoop = System.currentTimeMillis();
			currFrame = leapMotionController.frame();
			// can now get fingers etc.

			GestureList gestures = currFrame.gestures();
			Iterator<Gesture> gestIter = gestures.iterator();
			if (gestures.isEmpty()) {

			} else {
				for (int i = 0; i < gestures.count(); i++) {

					long currTime = System.currentTimeMillis();
					if (currTime - timeLastGesture < 400)
						break;

					// if we didn't exit the loop, then it must be ok to process
					// a new gesture, so
					// reset the timer
					timeLastGesture = currTime;

					Gesture currGesture = gestures.get(i);
					if (currGesture.type() == Type.TYPE_CIRCLE) {
						mediaStuff.playSong(2);
						System.out.println("circle");

					} else if (currGesture.type() == Type.TYPE_SWIPE) {
						if (mediaStuff.isPlaying(1))
							mediaStuff.scratchSong(1);
						System.out.println("swipe");

					}
				}

			}

			HandList hands = currFrame.hands();
			FingerList rightfingers = hands.rightmost().fingers();
			FingerList leftfingers = hands.leftmost().fingers();

			if (rightfingers.count() == 0 || leftfingers.count() == 0) {
				if (mediaStuff.isPlaying(1)) {
					mediaStuff.playSong(1);
					mediaStuff.lowerVolumeSong(1);
					System.out.println("fist");


				}

				else {
					mediaStuff.playSong(1);
					mediaStuff.lowerVolumeSong(1); // why the hell isn't this
													// working
					// mediaStuff.scratchSong1();
				}
			}

			drawingArea.repaint();

			timeAtEndOfLoop = System.currentTimeMillis();
			pause = DESIRED_DELAY - (timeAtEndOfLoop - timeAtBeginningOfLoop);
			if (pause < 0)
				pause = 1;

			try {
				Thread.sleep(pause);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	

}
