import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPanel extends JPanel{
	
	private GameFrame gf;
	
	private JButton easy_btn;
	private JButton normal_btn;
	private JButton hard_btn;
	
	public MenuPanel(GameFrame gf) {
		this.gf = gf;
		init();
	}
	
	private void init() {
		setBackground(SystemColor.info);
		setLayout(null);
		setSize(700,600);
		
		JLabel title_lb = new JLabel("타이핑 게임");
		title_lb.setFont(new Font("문체부 쓰기 정체", Font.PLAIN, 60));
		title_lb.setHorizontalAlignment(SwingConstants.CENTER);
		title_lb.setBounds(137, 20, 421, 161);
		add(title_lb);
		
		easy_btn = new JButton("쉬운 난이도");
		easy_btn.setBounds(216, 200, 266, 64);
		add(easy_btn);
		
		normal_btn = new JButton("보통 난이도");
		normal_btn.setBounds(216, 330, 266, 64);
		add(normal_btn);
		
		hard_btn = new JButton("어려움 난이도");
		hard_btn.setBounds(216, 470, 266, 64);
		add(hard_btn);
		
		MyListener listener = new MyListener();
		easy_btn.addActionListener(listener);
		normal_btn.addActionListener(listener);
		hard_btn.addActionListener(listener);
	}
	
	private class MyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int difficulty = 0;
			
			if(e.getSource() == easy_btn) {
				difficulty = 3;
			}
			else if(e.getSource() == normal_btn) {
				difficulty = 6;
			}
			else if(e.getSource() == hard_btn) {
				difficulty = 10;
			}
			
			gf.changePanel("game", difficulty);
		}
	}
}
