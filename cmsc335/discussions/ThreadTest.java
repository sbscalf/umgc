public class ThreadTest {
	static class MyThread extends Thread {
		public void run() {
			System.out.println("Hello from MyThread!");
		}
	}

	static class MyRunnable implements Runnable {
		public void run() {
			System.out.println("Hello from MyRunnable!");
		}
	}
	
	public static void main(String[] args) {
		// Thread subclass
		Thread myThread = new MyThread();
		myThread.start();
		
		// Anonymous Thread
		(new Thread() {
			public void run() {
				System.out.println("Hello from anonymous Thread!");
			}
		}).start();
		
		// Runnable implementation
		Runnable myRunnable = new MyRunnable();
		(new Thread(myRunnable)).start();
		
		// Anonymous Runnable
		(new Thread(new Runnable() {
			public void run() {
				System.out.println("Hello from anonymous Runnable!");
			}
		})).start();
		
		// Lambda Runnable
		(new Thread(() -> System.out.println("Hello from lambda Runnable!")
		)).start();
		
		// Lambda call method
		(new Thread(() -> myMethod())).start();
	}
	
	synchronized static void myMethod() {
		System.out.println("Hello from synchronized method!");
	}
}
