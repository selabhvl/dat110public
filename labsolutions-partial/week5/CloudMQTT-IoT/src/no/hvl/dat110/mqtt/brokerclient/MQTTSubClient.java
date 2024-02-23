package no.hvl.dat110.mqtt.brokerclient;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTSubClient implements MqttCallback {
	
	private String message;
	private boolean messageArrived = false;
	
	public MQTTSubClient() throws MqttException {
		
		MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        //connOpts.setUserName(Config.username);
        //connOpts.setPassword(Config.password.toCharArray());
        
        System.out.println("MQTTSubclient Connecting to broker: "+Config.broker);
        
        try (MqttClient client = new MqttClient(Config.broker, Config.sub_clientId, new MemoryPersistence())) {
			client.setCallback(this);
			client.connect(connOpts);
			System.out.println("MQTTSubclient Connected");
			
			client.subscribe(Config.topic, Config.qos);
		}
        System.out.println("Subscribed to message: "+Config.topic);
	}
	
	
	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("Connection lost because: " + cause);
		System.exit(1);
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		//System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
		
		// TODO: get the message payload 	
		// Hint: messageArrived method is a callback function that is triggered when the broker forwards the message
		// print out the temp
		System.out.println("Display [Temp]: "+new String(message.getPayload()));
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// 
		
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean isMessageArrived() {
		return messageArrived;
	}


	public void setMessageArrived(boolean messageArrived) {
		this.messageArrived = messageArrived;
	}

}
