package proj.vinod.assignment.code;

// Class which encapsulate company share details extracted from CSV file
public class Company implements Comparable<Company> {
	private String name;
	private int year;
	// Initial 3 characters of the month
	private String month;
	// Considering share value as integer as per example sample
	private int shareValue;
	
	public Company(String name,int year,String month,int shareValue){
		this.name=name;
		this.year=year;
		this.month=month;
		this.shareValue=shareValue;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public int getShareValue() {
		return shareValue;
	}


	public void setShareValue(int shareValue) {
		this.shareValue = shareValue;
	}

	/* CompareTo is implemented this way because expected output is 
	based on greatest value of share value only */
	@Override
	public int compareTo(Company compareWith) {
		// if object we are comparing with (compareWith) is null then current object(this) is greater
		if(compareWith==null)
			return 1;
		return Integer.valueOf(this.shareValue).compareTo(compareWith.shareValue);
	}
	
	
}
