import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		new GameFrame();
		
		new Thread(new Runnable(){
			public void run() {
				while(true) {
					System.out.println("�̷�! ����� �ڽ�!");
				}
			}
		});
		
		nextCode();
	}

	public static void nextCode() {
		
	}
}
