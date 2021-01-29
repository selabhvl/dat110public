package no.hvl.dat110.mqtt.brokerclient;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MQTTSubClient implements MqttCallback {

	
	public MQTTSubClient() throws MqttException {
		
		// TODO - see MQTTSubTest
	}
	
	
	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("Connection lost because: " + cause);
		System.exit(1);
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		
		// TODO: get the message payload 	
		// Hint: messageArrived method is a callback function that is called from the MQTT broker
		// print out the temp (See the MQTTSubTest)

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// 
		
	}

}
