
public class MyThread implements Runnable{
	public void run() {
		//���������� �����ų �ڵ�
	}
	
	public static void main(String[] args) {
		Thread th = new Thread(new MyThread());
		th.start();
		ICanDoAnything();
	}
	
	public static void ICanDoAnything() {
		
	}
}
