import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainJFrame extends JFrame {

	private Thread aniThread;
	private AnimationManager aniManager;
	private DA drawingArea;

	public MainJFrame() {
		super("Beth Desta & Emily Dorsey Project");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Box bottomBox = Box.createHorizontalBox();
		drawingArea = new DA(620, 620);

		getContentPane().setLayout(new BorderLayout());

		aniManager = new AnimationManager(drawingArea);
		aniThread = new Thread(aniManager);
		aniThread.start();

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(drawingArea, BorderLayout.CENTER);

		getContentPane().add(bottomBox, BorderLayout.SOUTH);

		// this sets the size (400 by 300) and the location relative to the
		// top-left of the screen
		// (so it starts over 100 pixels, but not down any from the top)
		setBounds(100, 0, 600, 300);

		setVisible(true);

		// set da = to a new DA

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainJFrame();
			}
		});

	}

}
