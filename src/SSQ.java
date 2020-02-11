import java.text.DecimalFormat;
import java.util.*;
class SSQ {
	public double arrival, delay, wait, service;
	public int num_jobs;
	
	public void initParams() {
		arrival = 0.0;
		delay = 0.0;
		wait = 0.0;
		service = 0.0;
		num_jobs = 0;
	}
	
	public double get_avgInterarrival() {
		return arrival / num_jobs;
	}
	public double get_avgServiceTime() {
		return service / num_jobs;
	}
	
	public double get_avgDelay() {
		return delay / num_jobs;
	}
	public double get_avgWait() {
		return wait / num_jobs;
	}
}

class Driver {
	public static double getInterArrival() {
		Random r = new Random();
		double rand = r.nextInt(10);
		if(rand < 1) return 30.0;
		else if (rand < 4) return 10.0;
		else return 20.0;
	}
	
	public static double getService() {
		Random r = new Random();
		double rand = r.nextInt(10);
		if(rand < 1) return 20.0;
		else if (rand < 4) return 8.0;
		else return 15.0;
		
	}
	public static void main(String[] args) {
		SSQ sim = new SSQ();
		sim.initParams();
		double serviceTime;							
	    double interarrivalTime;					
	    double departureTime = 0;					
	    double delay;								
	    double wait;								
	    sim.num_jobs = 1;
	    
	    sim.service += getService();
	    departureTime += sim.service;
	    
	    for(int i = 2; i <= 1000; i++) {
	    	interarrivalTime = getInterArrival();
	    	sim.arrival += interarrivalTime;
	    	if(sim.arrival < departureTime) delay = departureTime - sim.arrival;
	    	else delay = 0.0;
	    	serviceTime = getService();
	    	wait = delay + serviceTime;
	    	departureTime = sim.arrival + wait;
	    	sim.delay += delay;
	    	sim.wait += wait;
	    	sim.service += serviceTime;
	    	sim.num_jobs = i;
	    }
	    DecimalFormat f = new DecimalFormat("###0.00");
		System.out.println("Stanley Lin");
		System.out.println("SINGLE SERVER QUEUE SIMULATION");
	    System.out.println("Jobs Processed                  : " + sim.num_jobs + " jobs" );
        System.out.println("Average Interarrival Time       : " + f.format(sim.get_avgInterarrival()) + " secs.");
        System.out.println("Average Service Time            : " + f.format(sim.get_avgServiceTime()) + " secs.");
        System.out.println("Average Delay                   : " + f.format(sim.get_avgDelay()) + " secs.");
        System.out.println("Average Wait Time               : " + f.format(sim.get_avgWait()) + " secs.");
	}
}
