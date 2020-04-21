package no.hvl.dat110.rest.basic;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args){
		
		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}
		  
        get("/hello", (request, response) -> "Hello World!");
    }
}
