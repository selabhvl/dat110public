package no.hvl.dat110.mqtt.brokerclient.test;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import no.hvl.dat110.mqtt.brokerclient.Config;

/**
 * DAT110 DS-Lab 2
 *
 */
public class MQTTSubTest implements MqttCallback {
	
	
	public MQTTSubTest() throws MqttException {
    	
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        //connOpts.setUserName(Config.username);
        //connOpts.setPassword(Config.password.toCharArray());
        
        System.out.println("MQTTSubTest Connecting to broker: "+Config.broker);
        
        MqttClient client = new MqttClient(Config.broker, Config.sub_clientId, new MemoryPersistence());
        client.setCallback(this);
        client.connect(connOpts);
        System.out.println("MQTTSubTest Connected");
        
        client.subscribe(Config.topic, Config.qos);
        System.out.println("Subscribed to Topic: "+Config.topic);

	}
    
    /**
     * @see MqttCallback#connectionLost(Throwable)
     */
	public void connectionLost(Throwable cause) {
		System.out.println("Connection lost because: " + cause);
		System.exit(1);
		
	}
	
	/**
	 * @see MqttCallback#messageArrived(String, MqttMessage)
	 */
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
		
	}
	
	/**
	 * @see MqttCallback#deliveryComplete(IMqttDeliveryToken)
	 */
	public void deliveryComplete(IMqttDeliveryToken token) {
		// 
		
	}


	public static void main(String args[]) throws MqttException {
    	new MQTTSubTest();
    	
    }

}
