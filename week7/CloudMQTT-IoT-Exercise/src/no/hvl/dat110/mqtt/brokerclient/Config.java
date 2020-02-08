package no.hvl.dat110.mqtt.brokerclient;

public class Config {
	
	public static String topic = "Temp";							// You can change it to any topic of your choice
    public static int qos = 1;										// reliability of the message
    public static String broker = "tcp://mqtt.eclipse.org:1883"; 	// MQTT eclipse test broker/server
    public static String pub_clientId = "MQTT_Temperature_PUB";
    public static String sub_clientId = "MQTT_Temperature_SUB";
    public static String username = "";								// 
    public static String password = "";								// 
    
}
