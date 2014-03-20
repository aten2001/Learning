package booksite;

/**
 * Mock implementation of java's datae class
 * @author gokulvanan
 *
 */
public class Date {

	public int[] DAYS ={0, 31, 29, 31,30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	public int date;
	public int month;
	public int year;
	
	
	public Date(int day, int month, int year){
		if(validDate(day,month,year)){
			this.date=day;
			this.month=month;
			this.year=year;
		}
	}
	
	public Date next(){
		if(validDate(date+1, month, year)) return new Date(date+1,month,year);
		if(validDate(1,month+1,year)) return new Date(1,month+1,year);
		return new Date(1,1,year+1);
	}
	
	private boolean validDate(int day, int month, int year){
		if(month < 1 || month > 11) return false;
		if(day < 1 || day > DAYS[month]) return false;
		if(month ==2 && day == 29 && !isLeapYear(year)) return false;
		return true;
	}
	
	private boolean isLeapYear(int yr){
		if(yr % 400 == 0) return true;
		if(yr % 100 == 0) return false;
		return yr % 4 == 0;
	}
}
