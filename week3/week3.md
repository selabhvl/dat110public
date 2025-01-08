## DAT110: Distributed Systems and Network Technology

### Lab Week 3 - Software tools installation

The first task in the lab-exercises in the first week is to make sure that you have a **working installation** of the software tools that are to be used in the course. In case of problems, contact one of the teaching assistants of the course.

#### Exercise 0 - Textbooks

Make sure that you have access to the two textbooks that are to be used in the course.

#### Exercise 1 - Quiz

Remember to complete the end of week 2 quiz (see Canvas)

#### Exercise 2 - Watch a movie

Watch the video [Warriers of the Net](https://www.youtube.com/watch?v=PBWhzz_Gn10) (15 minutes) which provides a popular science introduction to Internetworking which is one of the main topics of the course.

#### Exercise 3 - Java Development Kit

Make sure that you have an installation of

- A [Java Development Kit](https://www.oracle.com/java/technologies/javase-downloads.html) (JDK)
- An Integrated Development Environment (IDE) or editor for the Java programming language. [Eclipse](https://www.eclipse.org/downloads/packages/release/2020-12/r/eclipse-ide-java-developers), [IntelliJ](https://www.jetbrains.com/idea/) or any other IDE for Java development will work.
- A [Git client](https://git-scm.com/downloads) - either standalone or as an IDE plugin, or [GitHub desktop](https://desktop.github.com/)

Perform a simple test to check that the tools are working properly. This can be done by cloning the git repository of the course:

https://github.com/selabhvl/dat110public

and run the HTTP client example at:

https://github.com/selabhvl/dat110public/tree/master/week2/apphttpclient

that was briefly demonstrated in the lecture/lecture video.

#### Exercise 4 - Wireshark

Wireshark is a network protocol analyser that can be used to capture and analyse packets that are sent to/from a computing device.

Download and install the [Wireshark](https://www.wireshark.org) tool.

Test that the installation is working. This can be done by trying to capture packet sent to/from your wireless network card. Open a browser and point it to http://www.example.com. Find the HTTP request and response messages that were sent/received.

A detailed description can be found here: https://www-net.cs.umass.edu/wireshark-labs/Wireshark_Intro_v8.0.pdf

#### Exercise 5 - The Postman tool

The Postman tool is a tool that can be used for API development environment for networked applications.

Download and install the [Postman](https://www.postman.com/downloads/) tool.

Test that the tool is working. This can be done by creating a HTTP GET request message targeting http://www.example.com and observing the HTTP response message that is returned.

#### Exercise 6 - Communication Protocols (highly exam relevant)

Problem P1 (Protocols) in Chap. 1 of Kurose and Ross.

#### Exercise 7 - Communication Metrics (highly exam relevant)

Problems P6 and P10 (Communication Metrics) in Chap. 1 of Kurose and Ross.

This exercise is a good warm-up exercise for the lecture on communication metrics.

#### Exercise 8 - Maven (optional for now - can be done later)

Maven is a software project management tool that can be used to compile, build, and test application independently of any IDE.

Download and install the [Maven](https://maven.apache.org) tool.

Test that the installation works. This can be done by creating a Maven project and then use Maven to compile the project. It suffices that that project contains a Hello World program in Java.
