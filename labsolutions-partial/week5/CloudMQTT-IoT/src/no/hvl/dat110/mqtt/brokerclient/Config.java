package no.hvl.dat110.mqtt.brokerclient;

public class Config {
	
	public static String topic = "Temp";
    public static int qos = 1;
    //public static String broker = "tcp://127.0.0.1:1883";			// mosquitto on localhost
    public static String broker = "tcp://broker.hivemq.com:1883";
    //public static String broker = "tcp://mqtt.eclipse.org:1883"; 		// MQTT eclipse test server
    //public static String broker = "tcp://m23.cloudmqtt.com:14331";
    public static String pub_clientId = "MQTT_Temperature_PUB";
    public static String sub_clientId = "MQTT_Temperature_SUB";
    //public static String username = "";
    //public static String password = "";
    
}
