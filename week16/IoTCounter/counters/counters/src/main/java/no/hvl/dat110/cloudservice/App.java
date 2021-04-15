package no.hvl.dat110.cloudservice;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static Counters counters = null;
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		counters = new Counters();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		get("/hello", (req, res) -> "Welcome to the IoT red green cloud service");
		
        get("/counters", (req, res) -> counters.toJson());
               
        put("/counters", (req,res) -> {
        
        	Gson gson = new Gson();
        	
        	counters = gson.fromJson(req.body(), Counters.class);
        
            return counters.toJson();
        	
        });
    }
    
}
