package no.hvl.dat110.mqtt.brokerclient.test;

import java.util.Random;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import no.hvl.dat110.mqtt.brokerclient.Config;

/**
 * 
 * DAT110 course
 * 
 */

public class MQTTPubTest {
    
    private MqttClient publisherClient;
    private Random rand;
    
	public MQTTPubTest() {
		
		rand = new Random();
	}
	
	private void publish() throws MqttPersistenceException, MqttException, InterruptedException {
		
		//loop 
		for(int i=0; i<10; i++){
			String temp = String.valueOf(rand.nextInt(60));
	    	System.out.println("Publishing on Topic Temp: "+temp);
	        MqttMessage message = new MqttMessage(temp.getBytes());
	        message.setQos(Config.qos);
	        publisherClient.publish(Config.topic, message);
	        Thread.sleep(4000);
		}
		
		System.exit(0);
		
	}
	
	private void connect() {
        MemoryPersistence persistence = new MemoryPersistence();

        try {
        	publisherClient = new MqttClient(Config.broker, Config.pub_clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            //connOpts.setUserName(Config.username);
            //connOpts.setPassword(Config.password.toCharArray());
            System.out.println("MQTTPubTest Connecting to broker: "+Config.broker);
            publisherClient.connect(connOpts);
            System.out.println("MQTTPubTest Connected");
            
        } catch(MqttException e) {
            System.out.println("reason "+e.getReasonCode());
            System.out.println("msg "+e.getMessage());
            System.out.println("loc "+e.getLocalizedMessage());
            System.out.println("cause "+e.getCause());
            System.out.println("exception "+e);
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) throws InterruptedException, MqttPersistenceException, MqttException {
        
		MQTTPubTest mqttpub = new MQTTPubTest();
		mqttpub.connect();
		mqttpub.publish();
		
    }
}
