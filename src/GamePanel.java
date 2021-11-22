import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel{
	
	private GameFrame gf;
	private int difficulty;
	private int score;
	
	private Thread[] th = new Thread[5];
	private WordRainLabel[] wr = new WordRainLabel[5]; 
	private Vector<String> word_list = new Vector<String>();
	private int word_index = 0;
	private boolean isPlaying = true;
	
	private JLabel score_lb;
	private JPanel word_panel;
	private JTextField anwser_tf;
	private JButton exit_btn;
	
	private Object key; // 스레드 공유변수 접근 문제를 처리해주는 레퍼런스
	
	public GamePanel(GameFrame gf, int diff) {
		this.gf = gf;
		this.difficulty = diff;
		
		init();
		addEventListener();
		setWordList();
		startGame();
	}
	
	private void startGame() {
		for(int i=0;i<wr.length;i++) {
			wr[i] = new WordRainLabel(popWord(), i*120);
			word_panel.add(wr[i]);
			th[i] = new Thread(wr[i]);
			th[i].start();
		}
	}
	
	private void setWordList() {
		File f = new File("word.txts");
		try {
			Scanner sc = new Scanner(f);
			
			while(sc.hasNextLine()) {
				word_list.add(sc.nextLine());
			}
			
			sc.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "파일을 읽어올 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private String popWord() {
		return word_list.elementAt((++word_index)%word_list.size());
	}
	
	private void init() {
		setBackground(SystemColor.info);
		setLayout(null);
		setSize(700,600);
		
		word_panel = new JPanel();
		word_panel.setLayout(null);
		word_panel.setBackground(Color.PINK);
		word_panel.setBounds(12, 94, 660, 430);
		add(word_panel);
		
		JLabel score_title_lb = new JLabel("점수");
		score_title_lb.setHorizontalAlignment(SwingConstants.CENTER);
		score_title_lb.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 40));
		score_title_lb.setBounds(134, 12, 124, 72);
		add(score_title_lb);
		
		score_lb = new JLabel("0");
		score_lb.setForeground(Color.RED);
		score_lb.setHorizontalAlignment(SwingConstants.CENTER);
		score_lb.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 40));
		score_lb.setBounds(270, 12, 210, 65);
		add(score_lb);
		
		anwser_tf = new JTextField();
		anwser_tf.setBounds(199, 528, 281, 29);
		add(anwser_tf);
		anwser_tf.setColumns(10);
		
		exit_btn = new JButton("나가기");
		exit_btn.setBounds(530, 527, 141, 29);
		add(exit_btn);
		
		anwser_tf.setFocusable(true);
	}
	
	private void addEventListener() {
		exit_btn.addActionListener((e)->{
			isPlaying = false;
			gf.changePanel("menu", 0); //메뉴 이동 시, 변수값 의미 x
		});
		
		anwser_tf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10){ //엔터키
					String text = anwser_tf.getText();
					
					for(WordRainLabel w: wr) {
						if(w.word.equals(text)) {
							score += 100;
							w.reset();
							score_lb.setText(Integer.toString(score));
							break;
						}
					}

					anwser_tf.setText("");
				}
			}
		});
	}
	
	private class WordRainLabel extends JLabel implements Runnable{
		private int x, y;
		private String word = "";
		private Random r = new Random();
		
		public WordRainLabel(String word, int posX) {
			this.word = word;
			this.x = posX;
			
			setText(word);
			setBounds(x, y, 120, 12);
			setVisible(true);
		}

		@Override
		public void run() {
			while(isPlaying) {
				y+=difficulty;
				
				if(y>=word_panel.getHeight()) {
					reset();
					synchronized(key) {
						score-=50;
						score_lb.setText(Integer.toString(score));
					}
				}
				
				setLocation(x, y);
				try {
					Thread.sleep(r.nextInt(500)+50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void reset() {
			y = 0;
			word = popWord();
			setText(word);
			setLocation(x, y);
		}
	}
}