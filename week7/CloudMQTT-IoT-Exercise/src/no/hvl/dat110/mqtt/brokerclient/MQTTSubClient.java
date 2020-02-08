package no.hvl.dat110.mqtt.brokerclient;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTSubClient implements MqttCallback {

	
	public MQTTSubClient() throws MqttException {
		
		MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(Config.username);
        connOpts.setPassword(Config.password.toCharArray());
        
        System.out.println("MQTTSubclient Connecting to broker: "+Config.broker);
        
        MqttClient client = new MqttClient(Config.broker, Config.sub_clientId, new MemoryPersistence());
        client.setCallback(this);
        client.connect(connOpts);
        System.out.println("MQTTSubclient Connected");
        
        client.subscribe(Config.topic, Config.qos);
        System.out.println("Subscribed to topic: "+Config.topic);
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
		// Hint: messageArrived method is a callback function that is called from the MQTT broker
		// Use a variable that could be called from client (e.g. DisplayDevice) to store the result here

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// 
		
	}

}
