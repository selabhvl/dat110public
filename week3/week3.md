## DAT110: Distributed Systems and Network Technology

### Lab Week 3: 17/01 - 21/01

**REMEMBER** to complete the end of week quiz for this week (and also end of week quiz for the previous week (if you have not already done so).

Note that some of the exercises below are marked as **optional**. These represents more challenging exercises. The first mandatory project will be about socket and network programming in Java. So even if Exercise 4.6 is optional, it is highly recommended to try to solve it in order to be well-prepared for undertaking the first project.

#### Exercise 1 - Layering and encapsulation

Consider a small network with two hosts H1 and H2 and two routers R1 and R2. H1 is connected to R1, R1 is connected to R2, and R2 is connected to H2 via communication links.

Assume that we have some data in a datagram that is to be sent from H1 via R1 and R2 to H2. What encapsulation and decapsulation will happen on the boundaries between the network layer and link layer along the way?

#### Exercise 2 - Internet protocols and time sequence diagrams

Problem P3 in Chap. 2 of the networking book with the modification that you should draw a [time sequence diagram]( https://en.wikipedia.org/wiki/Sequence_diagram) showing the interaction between the different protocol entities. Time sequence diagrams are widely used in computer science to illustrate the exchange of messages in protocols.

**NOTE** There is a difference between the "global edition" and the "US edition" of the networking book. For those not having the global edition you can find the problems and the figures being referred to here:

https://hvl.instructure.com/courses/15566/files/folder/labs

#### Exercise 3 - DNS and HTTP Wireshark

Perform the Wireshark exercises on HTTP and DNS described at http://www-net.cs.umass.edu/wireshark-labs/Wireshark_HTTP_v7.0.pdf and http://www-net.cs.umass.edu/wireshark-labs/Wireshark_DNS_v7.0.pdf

#### Exercise 4 - Threads and inter-thread communication

Distributed and networked applications involve implementing exchange of message between processes that runs on different computers. In many cases, however, implementation of the application of a distributed system also involves writing multi-threaded applications where threads synchronise and communicate using shared memory. This is for instance the case when implementing multi-threaded servers and when implementing sending and reception of messages in communication protocols.

The main purpose of this exercise is to briefly recap inter-thread communication and synchronisation in Java before starting on network programming. The exercise uses the API for concurrent programming in Java https://docs.oracle.com/javase/tutorial/essential/concurrency/

We consider a small (emulated) IoT system consisting of a temperature device (sensor) and a display device. The sensor device and the display device runs as individual threads where the sensor-thread with periodic intervals reads the current temperature and the display-threads periodically display the current temperature.

An illustration of the system is shown below

![](iotthreads/iotsystem.jpg)


##### 4.1

Pull the code base for the system from the github repository: https://github.com/selabhvl/dat110public

##### 4.2

The implementation of the system is in the Eclipse-project at: https://github.com/selabhvl/dat110public/tree/master/week3/iotthreads

Study the implementation of the five classes in the project

- [`TemperatureDevice`](https://github.com/selabhvl/dat110public/blob/master/week3/iotthreads/src/no/hvl/dat110/threading/TemperatureDevice.java) implementing the device that reports the temperature.
- [`TemperaturSensor`](https://github.com/selabhvl/dat110public/blob/master/week3/iotthreads/src/no/hvl/dat110/threading/TemperatureSensor.java) that simulates the sensing of the temperature.
- [`TemperatureMeasurement`](https://github.com/selabhvl/dat110public/blob/master/week3/iotthreads/src/no/hvl/dat110/threading/TemperatureMeasurement.java) that represents a measured temperature.
- [`DisplayDevice`](https://github.com/selabhvl/dat110public/blob/master/week3/iotthreads/src/no/hvl/dat110/threading/DisplayDevice.java) implementing the device used to display the measured temperature.
- [`IoTSystem`](https://github.com/selabhvl/dat110public/blob/master/week3/iotthreads/src/no/hvl/dat110/threading/IoTSystem.java) which configures the system and starts the threads for the temperature and display devices.

You can run the application by running the main-method in the `IoTSystem` class.

How does the sensor-thread and the display-thread exchange the temperature, i.e., how is the inter-thread communication implemented?

##### 4.3

Augment the IoT system such that multiple display-threads are created each displaying the current temperature.

##### 4.4

Modify the IoT system from such that multiple temperature devices (threads) can update the temperature measurement. Make sure that only one of them is doing it at a time (hint: what does the modifier [`synchronised`](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html) do in java?)

##### 4.5 (optional)

Modify the IoT system from item 4.4.2 such that instead of using a [`sleep()`](https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html) in the display-thread, then [`wait and notify`](https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html) are used such that the sensor-thread wakeup the display-thread when a new temperature has been reported.

#### Exercise 5 - UDP and TCP socket and network programming

Consider the client-server echo network application covered in the lectures this week. The source Java code for the TCP and the UDP implementation is available from https://github.com/selabhvl/dat110public/tree/master/week3/tcpexample and https://github.com/selabhvl/dat110public/tree/master/week3/udpexample

The implementation uses the Java Socket API as documented here:

https://docs.oracle.com/javase/10/docs/api/java/net/package-summary.html

A drawing giving an overview of the example is provided below.

![](assets/markdown-img-paste-20200122144706694.png)

![](assets/markdown-img-paste-20200122144242467.png)

##### 5.1

Open the two projects in Eclipse and run both the UDP and the TCP client-server example, make the two examples run.

Study the implementation to to understand how network programming with sockets works.

##### 5.2 (optional, you may also choose to just run the applications via the IDE)

Use Eclipse (your IDE) to build an executable jar-file for the UDP client and server and the TCP client and server. Run the two executable jar-files in a shell/command prompt using

`
java -jar X.jar <command-line arguments>
`

##### 5.3 (easiest to do if you are both on the same network)

Team up with one of the other students and try to run the client-side on one machine and the server side on another machine. You need to find the IP address of the machine where you intend to run the server. Also, depending on which TCP/UDP port you choose to use, you may need to configure the firewall on the machine in order to allow the TCP/UDP to pass in and out of your machine.

##### 5.4

Experiment with the network application when exposing to different fault-scenarios.

- What happens if you start two servers on the same port?
- What happens if the TCP client attempts to connect to the server but the server is not running?
- Modify the server such that it sleeps for some number of seconds before returning a response. What happens if a TCP client attempts to connect when there is already another TCP client request being served?

##### 5.5 (optional)

Would it be easy to modify the current TCP implementation with a keep-alive feature such that a TCP connection does not have to be created for each request from the same client?

#### Exercise 6 - Socket and network programming (optional, but recommended)

Consider the IoT system example from exercise 4.4:

https://github.com/selabhvl/dat110public/tree/master/week3/iotthreads/src/no/hvl/dat110/threading

where communication between the temperature device and the display device was performed using a shared memory object, and where the temperature device and display device were running as two threads on the same JVM.

Revise the implementation such the temperature device and the display device runs as separate processes and uses sockets for communication between the two entities. The temperature device should act as as client reporting temperature, and the display should act as a server receiving request to display the current temperature.

Implement both a TCP and a UDP variant. Use the example code from exercise 4.5 above as inspiration for how to implement the IoT system as a networked application using sockets.
