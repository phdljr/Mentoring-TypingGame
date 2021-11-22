import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		new GameFrame();
		
		new Thread(new Runnable(){
			public void run() {
				while(true) {
					System.out.println("이런! 비겁한 자식!");
				}
			}
		});
		
		nextCode();
	}

	public static void nextCode() {
		
	}
}
