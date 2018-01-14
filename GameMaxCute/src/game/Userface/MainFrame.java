package game.Userface;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import game.MainControl.MapOne;
import game.Object.MainChar;

public class MainFrame implements SentResult {
	private JFrame frame;
	private MapOne map;
	private CardLayout card;
	private int CurrentLevel;
	private MainChar mainC;
	private Memory memory;

	public static void main(String[] args) {
		MainFrame main = new MainFrame();
		main.memory = new Memory();
		main.mainC = new MainChar(0, 0);
		main.frame.getContentPane().add(main.FirstPanel(), "select");
		main.card.show(main.frame.getContentPane(), "select");
		main.frame.setVisible(true);
	}

	public MainFrame() {
		super();
		CurrentLevel = 0;
		frame = new JFrame();
		frame.setTitle("Game");
		frame.setLocation(300, 300);
		frame.setSize(1650, 1050);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Object stringArray[] = { "Yes", "No" };
				if (CurrentLevel == 0) {
					System.exit(0);
				} else {
					map.isRunning = false;
					int selection = JOptionPane.showOptionDialog(frame, "Are you sure?", "Select an Option",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, stringArray[0]);
					switch (selection) {
					case JOptionPane.YES_OPTION:
						int selection2 = JOptionPane.showOptionDialog(frame, "Save game?", "Select an Option",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray,
								stringArray[0]);
						switch (selection2) {
						case JOptionPane.YES_OPTION:
							memory.save(CurrentLevel, mainC);
							System.exit(0);
							break;
						case JOptionPane.NO_OPTION:
							System.exit(0);
							break;
						}
						break;
					case JOptionPane.NO_OPTION:
						map.isRunning = true;
						map.startGame();
					
						break;
					}
				}
			}
		});
		card = new CardLayout();
		frame.getContentPane().setLayout(card);
	}

	public JPanel FirstPanel() {
		JPanel panel;
		JButton GameContinue;
		JButton NewGame;
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage background = null;
				try {
					background = ImageIO.read(new File("src/sprite/1.jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				int x = (getWidth() - background.getWidth()) / 2;
				int y = (getHeight() - background.getHeight()) / 2;
				g.drawImage(background, x, y, null);

			}
		};
		GameContinue = new JButton("Continue");
		GameContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CurrentLevel = memory.getCurrentLevel();
				mainC = memory.getMain();
				newLevel(CurrentLevel);
				super.mouseClicked(e);
			}
		});
		NewGame = new JButton("New Game");
		NewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CurrentLevel = 1;
				newLevel(1);
				super.mouseClicked(e);
			}
		});
		panel.add(GameContinue);
		panel.add(NewGame);
		return panel;
	}

	public void newLevel(int level) {
		map = new MapOne(this, level,mainC);
		frame.getContentPane().add(map, "map");
		card.show(frame.getContentPane(), "map");
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
		map.setFocusable(true);
		map.requestFocusInWindow();
		map.startGame();
	}

	@Override
	public void sentResult(String command) {
		if (command.contentEquals("next") == true) {
			mainC = map.getMain();
			CurrentLevel = CurrentLevel + 1;
			frame.getContentPane().remove(1);
			newLevel(CurrentLevel);

		} else if (command.contentEquals("final") == true) {

			JOptionPane.showMessageDialog(frame, "Congratulation, You did well");
			frame.getContentPane().remove(1);
			CurrentLevel = 0;
			
		} else if (command.contentEquals("die") == true) {
			Object stringArray[] = { "Yes", "No" };
			int selection = JOptionPane.showOptionDialog(frame, "Continue playing?", "Select an Option",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, stringArray[0]);
			switch (selection) {
			case JOptionPane.YES_OPTION:
				frame.getContentPane().remove(1);
				newLevel(CurrentLevel);
				break;
			case JOptionPane.NO_OPTION:
				System.out.print("NO");
				int selection2 = JOptionPane.showOptionDialog(frame, "Save file?", null, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, stringArray, stringArray[0]);
				switch (selection2) {
				case JOptionPane.YES_OPTION:
					mainC = map.getMain();
					memory.save(CurrentLevel, mainC);
					break;
				case JOptionPane.NO_OPTION:
					break;
				}
				frame.getContentPane().remove(1);
				CurrentLevel = 0;
				break;
			}

		} else if (command.contentEquals("save") == true) {
			mainC = map.getMain();
			memory.save(CurrentLevel, mainC);
			map.isRunning = true;
		} else if (command.contentEquals("reward1") == true) {
			JOptionPane.showMessageDialog(frame, "Congratulation, You won first reward");
//			map.isRunning=true;
//			map.startGame();
		} else if (command.contentEquals("reward2") == true) {
			JOptionPane.showMessageDialog(frame, "Congratulation, You won second reward");
//			map.isRunning=true;
//			map.startGame();
		} else if (command.contentEquals("reward3") == true) {
			JOptionPane.showMessageDialog(frame, "Congratulation, You won third reward");
//			map.isRunning=true;
//			map.startGame();
		}

	}

}
