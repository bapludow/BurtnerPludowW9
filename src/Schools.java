import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import au.com.bytecode.opencsv.CSVReader;

public class Schools {
	
	// Attributes
	private ArrayList<School> schools;
	private ArrayList<String[]> data = new ArrayList<String[]>();
	private CSVReader csvreader;
	private double radius;
	
	//Constructors 
	public Schools() {
		schools = new ArrayList<School>();
	}
	
	// Methods
	
	public void importData(CSVReader csvreader, double radius) {
		String[] record = null;
		try {
			while((record = csvreader.readNext()) != null) {
				data.add(record);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("something went wrong");
		}
		
		for (int i = 0; i < data.size(); i++) {
			StringTokenizer tokenizer = new StringTokenizer(data.get(i)[0], ",");
			ArrayList<String> hold = new ArrayList<String>(4);
			while(tokenizer.hasMoreTokens()) {
				hold.add(tokenizer.nextToken());
			}
			schools.add(new School(hold.get(0), Double.valueOf(hold.get(1)), Double.valueOf(hold.get(2)), hold.get(3), radius));
		}
	}

	public int size() {
		return schools.size();
	}
	
	public School get(int i) {
		return schools.get(i);
	}
	
	public ArrayList<School> getSchools() {
		return schools;
	}

	public void setSchools(ArrayList<School> schools) {
		this.schools = schools;
	}

	public ArrayList<String[]> getData() {
		return data;
	}

	public void setData(ArrayList<String[]> data) {
		this.data = data;
	}

	public CSVReader getCsvreader() {
		return csvreader;
	}

	public void setCsvreader(CSVReader csvreader) {
		this.csvreader = csvreader;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}
