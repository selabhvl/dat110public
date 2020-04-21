package no.hvl.dat110.cloudservice;

import com.google.gson.Gson;

public class Counters {
	
	private int red,green;

	public Counters () {
		this.red = 0;
		this.green = 0;
	}

	public Counters (int red, int green) {
		this.red = red;
		this.green = green;
	}
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}
	
	public String toJson () {
    	
    	Gson gson = new Gson();
    	    
    	String jsonInString = gson.toJson(this);
    	
    	return jsonInString;
    }
}
