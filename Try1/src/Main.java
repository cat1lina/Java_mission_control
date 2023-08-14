import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;


	




public class Main {
	
	public static int[] A = new int[6];
	public static int i=2;

	static class MyRunnable implements Runnable {
	    @Override
	    public void run() {
	    	
	        System.out.println("This is running concurrently: " + A[i]);
	    }
	}
	
	public static void main(String[] args) 
	{
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		window.add(new DemoPanel());
	
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		 
		
		 ExecutorService executorService = Executors.newFixedThreadPool(10); 
		 
		 
		 MyRunnable r= new MyRunnable(); 
	        executorService.execute(r);
	        executorService.execute(r);
	        executorService.shutdown();  
		
	}

}
