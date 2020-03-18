//Name: Stanley Lin
import java.util.*;

class Event {
	private double time;
	public int type;
	
	public Event(int t1, double t2) {
		this.type = t1;
		this.time = t2;
	}
	
	public int getType() {
		return this.type;
	}
	
	public double getTime() {
		return this.time;
	}
}

class EventList extends LinkedList{
	public EventList() {
		super();
	}
	
	public Object getMin() {
		return getFirst();
	}
	
	public void enqueue(Object obj) {
		add(obj);
	}
	
	public void dequeue() {
		removeFirst();
	}
}

class Queue extends LinkedList{
	public void enqueue(Object o) {
		add(o);
	}
	
	public Object dequeue() {
		return removeFirst();
	}
}
public class Project1 {
	public static double clock;
	public static double meanInterArrivalTime;
	public static double meanServiceTime;
	public static double sigma;
	public static double lastEventTime;
	public static double totalBusy;
	public static double maxQueueLength;
	public static double sumResponseTime;
	public static long num_Customers;
	public static long queueLength;
	public static long numberInService;
	public static long totalCustomers;
	public static long num_Departures;
	public static long longService;
	public static int busyPeriods;
	public final static int arrival = 1;
	public final static int departure = 2;
	
	public static EventList futureEventList;
	public static Queue customers;
	public static Random rand;
	
	public static void initialize() {
		clock = 0.0;
		queueLength = 0;
		numberInService = 0;
		lastEventTime = 0.0;
		maxQueueLength = 0;
		num_Departures = 0;
		busyPeriods = 0;
		Event e = new Event(arrival, getInterArrival());
		futureEventList.enqueue(e);
	}
	
	public static void processArrival(Event e) {
		customers.enqueue(e);
		queueLength++;
		
		if(numberInService == 0) {
			scheduleDeparture();
		}
		else {
			totalBusy += (clock - lastEventTime);
			busyPeriods++;
		}
		
		if(maxQueueLength < queueLength) {
			maxQueueLength = queueLength;
		}
		
		Event nextArrival = new Event(arrival, clock + expon(rand, meanInterArrivalTime));
		futureEventList.enqueue(nextArrival);
		lastEventTime = clock;
	}
	
	public static void scheduleDeparture() {
		double servTime;
		servTime = expon(rand, meanServiceTime);
		Event depart = new Event(departure, clock + servTime);
		futureEventList.enqueue(depart);
		numberInService = 1;
		queueLength--;
	}
	
	public static void processDeparture(Event e) {
		Event fin = (Event) customers.dequeue();
		
		double response = (clock - fin.getTime());
		
		sumResponseTime += response;
		
		if(response > 4.0) {
			longService++;
		}
		totalBusy += (clock - lastEventTime);
		num_Departures++;
		lastEventTime = clock;
		
		if(queueLength > 0) {
			scheduleDeparture();
		}
		else {
			numberInService = 0;
		}
	}
	
	public static double expon(Random r, double mean) {
		return -mean * Math.log(r.nextDouble());
	}
	
	public static double getInterArrival() {
		double x = Math.random();
		if(x == 0.0) {
			x = 0.1;
		}
		double perArrival = -6 * Math.log(x);
		
		return perArrival;
	}
	
	public static double getService() {
		double x = Math.random();
		if(x == 0.0) {
			x = 0.1;
		}
		double perService = -5 * Math.log(x);
		return perService;
	}
	
	public static void generateReport() {
		System.out.println("Number of Customers: " + totalCustomers);
		System.out.println("Avg. Interarrival Time: " + meanInterArrivalTime);
		System.out.println("Avg. Service Time: " + meanServiceTime);
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("Name: Stanley Lin");
		meanInterArrivalTime = getInterArrival();
		meanServiceTime = 5;
		totalCustomers = 1000;
		rand = new Random();
		
		futureEventList = new EventList();
		customers = new Queue();
		initialize();
		
		while(num_Departures < totalCustomers) {
			Event e = (Event) futureEventList.getMin();
			futureEventList.dequeue();
			clock = e.getTime();
			if(e.getType() == arrival) {
				processArrival(e);
			}
			else {
				processDeparture(e);
			}
		}
	}
}
