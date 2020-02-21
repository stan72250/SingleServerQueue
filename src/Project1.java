import java.util.*;

public class Project1 {
	double arrival;
	double delays;
	double waits;
	double service;
	int numOfJobs;
	
	void initializeParameters() {
		arrival = 0.0;
		delays = 0.0;
		waits = 0.0;
		service = 0.0;
		numOfJobs = 0;
	}
	
	double getAvg_InterArrival() {
		return arrival / numOfJobs;
	}
	
	double getAvg_ServiceTime() {
		return service / numOfJobs;
	}
	
	double getAvg_Delay() {
		return delays / numOfJobs;
	}
	
	double getAvg_Wait() {
		return waits / numOfJobs;
	}
	
	public static double getInterArrival() {
		Random rand = new Random();
		double r = rand.nextInt(10);
		if(r < 1) {
			return 30.0;
		}
		
		else if(r < 4) {
			return 10.0;
		}
		else {
			return 20.0;
		}
	}
	
	public static double getService() {
		Random rand = new Random();
		double r = rand.nextInt(10);
		if(r < 1) {
			return 20.0;
		}
		else if(r < 4) {
			return 8.0;
		}
		else {
			return 15.0;
		}
	}
	
	public void simulate() {
		double serviceTime, interarrivalTime, departureTime = 0, delay, wait;
		initializeParameters();
		service += getService();
		departureTime += service;
		for(int i = 0; i < 1000; i++) {
			numOfJobs++;
			interarrivalTime = getInterArrival();
			arrival += interarrivalTime;
			if(arrival < departureTime) {
				delay = departureTime - arrival;
			}
			else {
				delay = 0.0;
			}
			serviceTime = getService();
			wait = delay + serviceTime;
			departureTime = arrival + wait;
			delays += delay;
			waits += wait;
			service += serviceTime;
			if(numOfJobs <= 20) {
				System.out.println("Job: " + numOfJobs + ", Arrival Time: " + interarrivalTime + ", Service Time: " + serviceTime + ", Current Time: " + arrival);
			}
		}
		System.out.println("Results for 1000 service completions");
		System.out.println("Total time elapsed: " + arrival + " secs.");
		System.out.println("Average Interarrival Time: " + getAvg_InterArrival() + " secs.");
		System.out.println("Average Service: " + getAvg_ServiceTime() + " secs.");
		System.out.println("Average Delay: " + getAvg_Delay() + " secs.");
		System.out.println("Average Wait Time: " + getAvg_Wait() + " secs.");
	}
	
	public static void main(String[] args) {
		System.out.println("Name: Stanley Lin");
		Project1 ssq = new Project1();
		ssq.simulate();
	}
}
