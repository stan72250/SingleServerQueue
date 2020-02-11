import java.util.*;
import java.lang.Math;
public class Main {
	
	
	public static void main(String[] args) {
		LinkedList<Job> jq = new LinkedList<>();
		List<Integer> arrival_times = new ArrayList<Integer>();
		
		int num_jobs = 1000;
		int arrival_time = 0;
		double time = 0.0;
		int avg_length = 10;
		int avg_arrival = 13;
		int job_num = 0;
		int count = 0;
		
		Random rand = new Random();
		System.out.println("Stanley Lin");
		
		while (count < num_jobs) {
			job_num++;
			
			int interArrival = (int) (avg_arrival * (-Math.log(1 - rand.nextDouble())));
			int job_length = (int) (avg_length * (-Math.log(1 - rand.nextDouble())));
			
			if(jq.size() == 0) arrival_time += arrival_time;
			
			else {
				arrival_time = jq.getLast().arrival_Time + interArrival;
			}
			
			Job next = new Job(job_num, arrival_time, job_length);
			jq.add(next);
			
			while(time < jq.getFirst().arrival_Time) 
				time++;
			
			if(jq.getFirst().job_Length <= 5 || count == num_jobs) {
				time += jq.getFirst().job_Length;
				jq.getFirst().job_Length -= jq.getFirst().job_Length;
				jq.remove();
				count++;
			}
			else {
				time += 5;
				jq.getFirst().getService();
				jq.addLast(jq.getFirst());
				jq.removeFirst();
				count++;
			}	
			System.out.println("Job " + job_num + ", arrival time: " + arrival_time + ", job length: " + job_length + ", Time: " + time);
			arrival_times.add(arrival_time);
		}
		
		
		System.out.println();
		System.out.println("Total completion time: " + (time/ 1000) + " secs. ");
		System.out.println("Avg. Interarrival Time: " + arrival_times.stream().mapToInt(value -> value).sum() / 1000 + " secs.");
	}
}
