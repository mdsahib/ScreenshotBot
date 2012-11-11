import java.util.Calendar;

public class CalendarProcess {
	
	private String date;	
	private String time;
	private Calendar calendar;

	
	
	public  CalendarProcess()
	{
		calendar = Calendar.getInstance();
		setTime();
		setDate();	
		
	}
	
	
	//Construct the time in string format hh-mm-ss
	public void setTime()
	{
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		
		time=hour + "-" + min + "-" +sec;
		
	}//end of getTime
	
	
		public String getTime()
	{
		return time;
		
	}//public String getTime()
	
	
	
		//Construct the time in string format hh-mm-ss
	public void setDate()
	{
		int month = calendar.get(Calendar.MONTH) + 1;
		int  year = calendar.get(Calendar.YEAR);
		int dom = calendar.get(Calendar.DAY_OF_MONTH);
		
		date =dom + "-" + month + "-" +year;
		
	}//end of setDate
	
		public String getDate()
	{
		return date;
		
	}//public String getDate()
	

	
	
	public static void main(String[] args) {
        //
        // Get various information from the Date object.         
        //
		
	CalendarProcess cal= new CalendarProcess();
	System.out.println(cal.getTime());
	System.out.println(cal.getDate());

    }
}
