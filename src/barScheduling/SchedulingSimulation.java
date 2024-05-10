//M. M. Kuttel 2024 mkuttel@gmail.com

package barScheduling;
// the main class, starts all threads


import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


public class SchedulingSimulation {
	static int noPatrons=100; //number of customers - default value if not provided on command line
	static int sched=0; //which scheduling algorithm, 0= FCFS
			
	static CountDownLatch startSignal;

	static int executionTotals; // this is for when we calculate throughput
	static AtomicInteger count = new AtomicInteger(0);
	
	static Patron[] patrons; // array for customer threads
	static Barman Andre;
	static FileWriter writer;

	public  void writeToFile(String data) throws IOException {
	    synchronized (writer) {
	    	writer.write(data);
	    }
	}
	// public void throughputTime(){
	// 	schedular 
	// }

	public static void main(String[] args) throws InterruptedException, IOException {
		noPatrons = 15;
		sched = 1;
		

		//deal with command line arguments if provided
		if (args.length==1) {
			noPatrons=Integer.parseInt(args[0]);  //total people to enter room
		} else if (args.length==2) {
			noPatrons=Integer.parseInt(args[0]);  //total people to enter room
			sched=Integer.parseInt(args[1]); 
		}

		BackGroundTimer task = new BackGroundTimer(count, 1000, "throughputs_"+Integer.toString(sched)+".csv"); // 1 seconds delay
        Thread thread = new Thread(task);
		
		writer = new FileWriter("turnaround_time_"+Integer.toString(sched)+".txt", false);
		Patron.fileW=writer;

		startSignal= new CountDownLatch(noPatrons+2);//Barman and patrons and main method must be raeady
		
		//create barman
        Andre= new Barman(startSignal,sched,noPatrons); 
     	Andre.start();
		thread.start(); // Timer thread will strat from here

  
	    //create all the patrons, who all need access to Andre
		patrons = new Patron[noPatrons];
		for (int i=0;i<noPatrons;i++) {
			patrons[i] = new Patron(i,startSignal,Andre,sched,count); // we are adding schedular to the constructor so that the patron knows how its done
			patrons[i].start();
		}
		
		System.out.println("------Andre the Barman Scheduling Simulation------");
		System.out.println("-------------- with "+ Integer.toString(noPatrons) + " patrons---------------");

      	startSignal.countDown(); //main method ready
      	
      	//wait till all patrons done, otherwise race condition on the file closing!
      	for (int i=0;i<noPatrons;i++) {
			patrons[i].join();
		}

    	System.out.println("------Waiting for Andre------");
    	Andre.interrupt();   //tell Andre to close up
    	Andre.join(); //wait till he has
      	task.stop();
		writer.close(); //all done, can close file
      	System.out.println("------Bar closed------");
	}

}
