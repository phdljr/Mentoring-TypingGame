
public class MyThread implements Runnable{
	public void run() {
		//스레드한테 실행시킬 코드
	}
	
	public static void main(String[] args) {
		Thread th = new Thread(new MyThread());
		th.start();
		ICanDoAnything();
	}
	
	public static void ICanDoAnything() {
		
	}
}
