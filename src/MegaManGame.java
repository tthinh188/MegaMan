import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MegaManGame extends JFrame{
	private boolean isPaused = false;
	private Panel panel = null;

	public MegaManGame() {
		super("Mega Man");
		panel = new Panel();
		add(panel);
		JMenuBar menubar = new JMenuBar();
		JMenu game = new JMenu("Game");
		JMenu help = new JMenu("Help");
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem resume = new JMenuItem("Resume");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem control = new JMenuItem("Control");
		JMenuItem about = new JMenuItem("About...");
		
		menubar.add(game);
		menubar.add(help);
		
		game.add(newGame);
		game.add(pause);
		game.add(resume);
		game.add(save);
		game.add(load);
		game.add(quit);
		help.add(about);
		help.add(control);
		
		newGame.addActionListener(e -> {
        	if( panel != null ) {
            	panel.pauseGame(true);
        	}
			int response = JOptionPane.showConfirmDialog(MegaManGame.this, "Start a new game?");
			if (response == JOptionPane.YES_OPTION) {
				panel.stopMusic();
				remove(panel);
				panel = new Panel();
				add(panel);
				revalidate();
				
				resume.setEnabled(true);
				resume.doClick();
			}
			else {
				if( panel != null && !isPaused ) {
					panel.pauseGame(false);
				}
			}
		});
		
		pause.addActionListener( e ->{
			panel.pauseGame(true);
			isPaused = true;
			
			resume.setEnabled(true);
			pause.setEnabled(false);
		});
		
		resume.addActionListener(e ->{
			panel.pauseGame(false);
			isPaused = false;
			
			resume.setEnabled(false);
			pause.setEnabled(true);
		});
		
		 quit.addActionListener ( e -> {
	        	if( panel != null ) {
	            	panel.pauseGame(true);
	        	}
	            quitDialog();
	            if( panel != null && !isPaused ) {
					panel.pauseGame(false);
				}
	        });
		
		pause.setEnabled(true);  
		resume.setEnabled(false);
		
		Path path = Paths.get("src/game_data/game_data.txt");
		
		if (Files.exists(path)) {
			load.setEnabled(true);
		}
		else {
			load.setEnabled(false);
		}
		
		save.addActionListener(e ->{
			panel.save();
			load.setEnabled(true);
		});
		
		load.addActionListener(e -> panel.load() );
		
		about.addActionListener(e ->{
			panel.pauseGame(true); 
			JOptionPane.showMessageDialog(MegaManGame.this, new JLabel(
					"<html><hr><b>MegaMan Game</b>"
							+ "<center>Developed by Thinh Phan<hr></html>"));
			if (!isPaused) {
				panel.pauseGame(false);
			}
		});
		
		control.addActionListener(e -> controlDialog());

		setJMenuBar(menubar);
		setSize(1024,768);
		setResizable(false);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		controlDialog();
	}
	
	private void controlDialog() {
		panel.pauseGame(true);
		JOptionPane.showMessageDialog(MegaManGame.this, new JLabel(
				"<html><hr><b></b>"
						+ "&#8592; : Move left"
						+ "<br>&#8593; : Jump"
						+ "<br>&#8594; : Move Right"
						+ "<br>&#9251; : Shoot"
						+ "<hr></html>"));
		if (!isPaused) {
			panel.pauseGame(false);
		}
	}
	
	private void quitDialog() {
        int response = JOptionPane.showConfirmDialog(MegaManGame.this, "Dare to Quit?");
        if (response == JOptionPane.YES_OPTION) {
        	remove(panel);
        	panel = null;
            dispose();
        }
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
	            JFrame f = new MegaManGame();
	            f.setVisible( true );
	        }
        });
	}

}
