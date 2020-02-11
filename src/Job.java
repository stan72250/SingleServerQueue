
public class Job {
	int job_Number;
	int arrival_Time;
	int job_Length;
	
	public Job(int j_num, int at, int j_length) {
		this.job_Number = j_num;
		this.arrival_Time = at;
		this.job_Length = j_length;
	}
	
	public void getService() {
		job_Length -= 5;
	}
}
