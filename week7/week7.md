### Lab Week 7: 10/02 - 14/02

These exercises are based on Java Remote Method Invocation (RMI) implementation of RPC and Message-Oriented Middleware (MoM)

#### Exercise 1 - Synchronous RPC 

In this lab exercise, you will implement the IoT system that you have implemented in project 1 using the Java RMI.
https://github.com/selabhvl/dat110public-2020/tree/master/week7/JavaRMI-iotthreads-synchronous

The main task is to implement the RPC server that accepts temperature readings from the TemperatureDevice and that allows the DisplayDevice to read/get the temperature values from its shared variable.

- The TemperatureDevice will save its temperature readings in the temp variable of the RPC server (stub).

- The Display device will read the temperature reading from the temp variable in the RPC server.

You can use the example code here as inspiration:
https://github.com/selabhvl/dat110public-2020/tree/master/week6/JavaRMI-DSLab1


#### Exercise 2 - Asynchronous RPC client (Threads with a callback)

In this exercise, you will construct an 'asynchronous' version of the client-server RPC IoT system in Exercise 1 by using a callback mechanism. 
The idea is that the RPC server should notify/forward the temperature value to the Display device as soon as it receives the reading from the Temperature device.

You will need to implement a callback function for the Display device which must be registered on the Temperature RPC server.

An idea of one way to construct such an asynchronous RPC client-server system can be found in the example code: https://github.com/selabhvl/dat110public-2020/tree/master/week7/JavaRMI-Asynchronous-Client


#### Exercise 3 - Asynchronous RPC Server (Threads)

Consider the example code: https://github.com/selabhvl/dat110public-2020/tree/master/week7/JavaRMI-Asynchronous-Client

This example code demonstrates asynchronous model from the client side. That is, the client provides unblocking mechanism by spawning a new thread and providing a callback to wait for the result from the server while the client continues its work.

Modify the example code to construct an asynchronous RPC server such that the RPC server can accept multiple RPC clients connections without blocking.


#### Exercise 4 - Multicast RPC (Threads with a callback)

In this exercise, you will attempt to crack passwords with reasonable levels of complexity. The main goal is that we can use distributed processes to perform tasks in parallel (single computer with multicores or distributed systems).
For this purpose, we will use the Multicast RPC approach. We will divide the cracking job into the number of available distributed nodes that we have (in this case 2 nodes because
my CPU has 2 cores). Note that if your laptop has only one core, this will not be a parallel computation as the CPU with 1 core can only be busy with one job at a time. 
You will then need to use multiple computers to achieve this task.

##### Design
Password can be chosen from 52 alphabets (lowercase+uppercase) and 10 numbers. Our password character space is thus 62. To crack N letter password by exhaustive search (brute force), we need to search 62^N subsets. This is an exponential complexity job.
We assume that we do not know the length of the password we want to crack. However, for the purpose of this exercise, we will only search password with length of 5 or 6 i.e. 62^5 + 62^6 subsets. 
The idea is that we want to show that distributed processes can solve computationally intensive task faster than a single process. 
To start, run the BruteForce.java located in "no.hvl.dat110.crack" package to record how long it takes for a single process (core) to crack our specified password.
The PasswordUtility contains two methods; generateHashWithoutSalt and verifyHash. In each search, the hash of the characters we want to verify is generated and the generated hash is compared with the password hash to verify if they match.
To employ parallelism, we will distribute the search to 2 worker nodes (2 processes) located in "no.hvl.dat110.workernodes". The jobs are distributed from the class PassCrackCoordinatorClient.java located in no.hvl.dat110.rmiclient.

- The PassCrackImpl.java contains the actual implementations of remote methods defined in the PassCrackInterface.java. The PassCrackServer is a worker/server that binds a stub of PassCrackImpl in a registry and listens on the specified port.
- The WorkerNode1 and WorkerNode2 (no.hvl.dat110.workernodes) are instances of the PassCrackServer.
- The WorkerCallbackInterface contains remote methods that are invoked by Workers to notify of job receipt and also notify when the password is found.
- The implementation of the WorkerCallbackInterface (WorkerCallbackImpl) is registered in each worker node. This is done in the PassCrackCoordinatorClient class.

##### Task
You are provided with a nearly completed implementation of the system which is available here: https://github.com/selabhvl/dat110public-2020/tree/master/week7/JavaRMI-Multicast-exercise
- Implement the TODO tasks in the PassCrackImpl class
- To run the code, first start WorkerNode1 and WorkerNode2, then run the coordinator class "PassCrackCoordinatorClient". If your implementation is correct, jobs should be distributed and the call to crackPassword in the workers should start the cracking task.
Note, it may take a while before you see any result.

#### Exercise 5 - Message-Oriented Middleware (MoM) using MQTT protocol

These exercises are based on Message-Oriented Middleware. In this exercise, you will use a MoM model to solve the IoT system challenge. You will be using a free MQTT middleware (broker) and a publish-subscribe architecture.
The MQTT uses the publish-subscribe model where publishers(servers/clients) publish on a topic and subscribers(clients) subscribe to the topic to receive messages based on this topic.

In this exercise, you will implement the IoT system using a message-oriented middleware based in the 'cloud' and optionally, a broker installed locally on your machine.

To get started, you should perform tasks 1 and 2.

##### Task 1 - Setup the eclipse MQTT broker
We will use the free eclipse and public MQTT broker for subscribing to and publishing messages to topics. Please read the terms of usage here: https://iot.eclipse.org/getting-started/
The broker url is: tcp://mqtt.eclipse.org:1883

##### Task 2 - Test Connection to the eclipse MQTT broker

To test whether you can connect to the MQTT broker, you need to provide the following information in the Config class (located in no.hvl.dat110.mqtt.brokerclient.test) which are then used by the publisher and subscriber classes. These can be obtained from your CloudMQTT account:

broker: tcp://mqtt.eclipse.org:1883
No username or password is needed for this test broker.
Test that you can connect to eclipse MQTT and publish/subscribe to the ‘Temp’ topic by running the main method in the MQTTSubTest and MQTTPubTest classes.

##### Exercise 5.1 - IoT System with Message Broker
You will be implementing the virtual IoT devices as clients using the Eclipse Paho MQTT https://www.eclipse.org/paho/ for publishing and subscribing. That is: the TemperatureDevice publishes the temperature reading to the eclipse MQTT using the topic "Temp" while the DisplayDevice subscribes to the topic "Temp" on eclipse MQTT from where it receives the temperature reading.

To get you started, you are provided with an initial implementation of the system which is available from here:

https://github.com/selabhvl/dat110public-2020/tree/master/week7/CloudMQTT-IoT-Exercise

as an Eclipse project.
The Paho client jar is located under the lib folder. Make sure you import the jar as an external jar into your eclipse project (Configure build path).

The project contains implementations of a virtual temperature sensor and a display that you have used in previous exercises. Your task is to implement the missing parts in the TemperatureDevice and DisplayDevice classes. 

Run the IoTSystem class located in the package "no.hvl.dat110.simulation" to test that your implementation is working.

A short tutorial on Paho-MQTT client that explains how MQTT works and the meanings of the configuration parameters can be found here: https://github.com/selabhvl/dat110public-2020/blob/master/week7/mqtt-paho-client-tutorial.pdf

##### Exercise 5.2 (Optional) - Use Mosquitto broker instead of the free eclipse broker

Instead of using the free eclipse MQTT broker, you should now download and configure Mosquitto message broker on your machine.
Download from: http://mosquitto.org/download/
Follow the instructions for installation. 

To switch the broker to Mosquitto in your client source code (using default configurations):
- Start the Mosquitto broker on your machine
- Go to the Config class and change the 'broker = "tcp://<hostname:port>";' to something like: broker = "tcp://localhost:1883";
- Clear the username and password fields.
- Run the IoTSystem to see that everything is properly configured.