package lab10;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		//new FindMax().printMax();
		new FindMax().mThreadprintMax();
	}

	public void printMax() {
		// this is a single threaded version
		int max = findMax(0, array.length - 1);
		System.out.println("the max value is " + max);
	}

	public void mThreadprintMax(){
		//this is a multi-thread version
		findmaxTask t1=new findmaxTask(0,29);
		findmaxTask t2=new findmaxTask(30,59);
		findmaxTask t3=new findmaxTask(60,89);
		Thread Th1=new Thread(t1);
		Thread Th2=new Thread(t2);
		Thread Th3=new Thread(t3);
		
		Th1.start();
		Th2.start();
		Th3.start();
		
		try
		{
			Th1.join();
			Th2.join();
			Th3.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		
		int max=t1.getMax();
		if (max < t2.getMax())
			max=t2.getMax();
		if (max < t3.getMax())
			max=t3.getMax();
		System.out.println("the max value is " + max);
	}
	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
	
	class findmaxTask implements Runnable{
		int begin;
		int end;
		int max;
		
		public findmaxTask(int begin, int end){
			this.begin=begin;
			this.end=end;
		}
		
		public int getMax(){
			return max;
		}
		
		@Override
		public void run(){
			max=findMax(begin,end);
		}
	}
}
