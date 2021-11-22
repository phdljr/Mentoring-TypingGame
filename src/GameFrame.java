import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame{
	
	private CardLayout cl = new CardLayout();
	private JPanel panel = new JPanel(); //dummy panel
	
	public GameFrame() {
		init();
		setLayout(cl);
		add("menu", new MenuPanel(this));
		add("game", panel);
	}
	
	public void changePanel(String name, int diff) {
		if(name.equals("game")) {
			remove(panel);
			panel = new GamePanel(this, diff);
			add(panel);
		}
		cl.next(getContentPane());
	}
	
	private void init() {
		setTitle("타이핑 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
