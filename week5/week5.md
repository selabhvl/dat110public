## DAT110: Distributed Systems and Network Technology

### Lab Week 5: 27/01 - 31/01

**REMEMBER** to complete the end of week 4 quiz if you have not already done so. There is no quiz in week 5.

Not that some of the exercises below are marked as **optional**. These represents more challenging exercises.

#### Exercise 5.1 - Domain Name System

Problems P7 and P8 in Chap. 2 of the networking book.

#### Exercise 5.2 - Project work

Complete task 1 and start on task 2 on project 1 (will be made available Monday 27/1)

#### Exercise 5.3 - Secure HTTP

Consider the implementation of the simple HTTP client that uses HTTPS for communication:

https://github.com/selabhvl/dat110public-2020/tree/master/week4/httpsappclient

Use the client to access a webpage via https and use Wireshark to inspect the messages exchanges. Can you get information about what the content of the retreived web page is? Why/Why not?

Compare the messages exchanges with the messages exchanged when using the non-secure variant of the HTTP client:

https://github.com/selabhvl/dat110public-2020/tree/master/week3

#### Exercise 5.4 - Echo Client in a different programming language (optional)

Choose a programming language different from Java. Implement the echo client from:

https://github.com/selabhvl/dat110public-2020/tree/master/week4/udpexample

in that language. Make the implementation interact with the server-side implemented in Java.

One option would be to run the Python echo client from the networking book (see Chapter 2.8)

#### Exercise 5.5 - Web Service Client (optional)

Consider the implementation of the simple HTTP client that uses HTTPS for communication:

https://github.com/selabhvl/dat110public-2020/tree/master/week4/httpsappclient

Revise and augment the implementation such that the client is able to interact with the dweet.io REST web service API:

https://dweet.io/play/

As an example, the client should be able to perform GET and POST operations against the service to retrieve and post information on a topic. You may want to prototype the HTTP requests using Postman before implementing them in the Java code.

#### Exercise 5.6 - Multi-threaded Echo TCP server (optional and challenging)

Consider the echo client-server implementation based on TCP sockets:

https://github.com/selabhvl/dat110public-2020/tree/master/week4/tcpexample

The implementation of the server-side is single threaded which has the effect that the server is only able to service one client at a time. For the echo service this is not a real problem as the handling of a request to convert a text string takes hardly any time. If the task to performed by the server-side takes longer time, then the clients may experience delays, i.e., a poor quality of service. Servers are therefore often multi-threaded so that they can server several clients at a time.

##### Exercise 5.6.1

Modify the server such that the server waits a certain amount of time before sending back a response (the converted text) to the client. This is to simulate some heavier processing on the server side. Set the time to for instance 10 seconds.

Try to run two instances of the client at the same time. What happens?

##### Exercise 5.6.2

Augment the server implementation such that when it is accepting a new connection, a new thread is started to handle the request and then main thread goes back waiting for incoming connections. Repeat the experiment from 5.5.1. What behaviour is observed?

##### Exercise 5.6.3

Creating a new thread for each new incoming connection is problematic because it makes it easy to perform denial of service attacks where a lot of clients connects at the same time forcing the server to created an excessive amount of threads. Modify the implementation from 5.5.2 such that the server has a pool of threads (of some fixed size) than can be used to handle requests. If all threads are used to handle requests that new requests will have wait.

##### Exercise 5.6.4

In addition to having a TCP port in which the server delivers its service (in this case uppercase conversion) it is also often the case that the server has a TCP port that can be used for management. Example is to stop and restart the server and also adjust the number of threads that can be used to service requests.

Modify the server such that it uses TCP port 8080 to service requests and TCP port 8081 for management. As part of this you need to device a management protocol that can be used for administrating the server.

##### Exercise 5.6.5

Modify the server such that it relies on the HTTPS protocol for management.
