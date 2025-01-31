package no.hvl.dat110.mqtt.brokerclient;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MQTTPubClient {
	
	private MqttClient publisherClient;
	
	
	public MQTTPubClient() {
		
	}
	
	public void publish(String temp) throws MqttPersistenceException, MqttException, InterruptedException {
		
		// TODO - see MQTTPubTest
		
	}
	
	/**
	 * Call method to create a connection to the MQTT Broker
	 */
	public void connect() {
        
		// TODO - see MQTTPubTest
	}
}
