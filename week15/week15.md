## DAT110: Distributed Systems and Network Technology

### Lab Week 14: 10/4 - 5/4

### Exercise 1: Network Layer - Data Plane

From Chapter 4 in the networking book

##### Review questions

R9 (forwarding), R17 (encapsulation), R20 (fragmentation), R21 (network interfaces), R22 (IP addresses) , R24 (forwarding)

##### Problems

- P1 (forwarding tables), P5 (forwarding tables), P6 (longest prefix matching), P7 (forwarding tables)
- P8 (network addresses), P11 (network prefixes), P12 (subnets)
- P14 (fragmentation), P15 (fragmentation)
- P16 (network address translation)

### Exercise 2: Network Layer - Control Plane

From Chapter 5 in the networking book

- Review question R5 (counting to infinity problem)
- Problem P3 (run Dikjstra's algorithm on an example network)
- Problem P8 (run Belmann-Ford Distance Vector Algorithm on an example)

### Exercise 3: Link-layer - Exam Exercise 2019

##### a)

Explain what information is stored in an ARP table

##### b)

Consider a local area network consisting of three hosts with IP and MAC addresses as specified in the figure below and a switch with interfaces numbered 1-3 as specified in the figure. Assume that the ARP tables on all hosts are empty and that the switch-table on the switch is empty.

![](assets/markdown-img-paste-20200409164038903.png)

What is the content of the ARP table on the host with IP address 112.223.334.1 after an IP datagram from 112.223.334.1 to 112.223.334.3 has been sent? **Justify your answer**

##### c)

What will the content of the switch-table be after an IP datagram from 112.223.334.1 to 112.223.334.3 has been sent? **Justify your answer**

# NOTE: THE EXERCISES BELOW ARE NOT PART OF 2023 VERSION

### Exercise 4: Stopable-thread abstraction revisited (NOT IN 2023)

The RDT framework for reliable data transfer studied earlier, and the network routing framework (NFR) to be explored in the coming weeks rely on the stopable-thread abstraction for implementation of protocol entities and network interfaces.

The aim of this exercise it to become familiar with the stopable-threads abstraction which builds on top of Java threads.

#### Stopable-threads

The implementation of the `Stopable`-class is shown below and can also be found in the repository at https://github.com/lmkr/stopablethread.git

```java
public abstract class Stopable extends Thread {

	private boolean stop = false;
	protected String name;

	public Stopable(String name) {
		this.name = name;
	}

	public synchronized void doStop() {
		stop = true;
	}

	private synchronized boolean doCont() {
		return !stop;

	}

	protected void starting() {

	}

	protected void stopping() {

	}

	protected abstract void doProcess();

	public void run() {

		starting();

		while (doCont()) {

			doProcess();

		}

		stopping();

	}
}

```

A stopable thread will start by executing the `starting`-method which can be used for initialisation purposes. Then the `doProcess`-method will be continuously executed until either the stopable thread itself or another thread invokes the `doStop`-method. Before the stopable thread terminates, it will execute the `stopping`-method which can be used for any clean-up that needs to be performed.

A subclass of the Stopable-thread class can then overwrite the `starting`- and `stopping`-methods and implement the `doProcess`-method to obtain the desired functionality.

The programming contract is that the code in the `doProcess`-method cannot contain method calls that will block forever and/or not make progress as that may prevent the `doCont()`-method to be executed for checking whether the stopable-thread is still to continnue.

#### IoT temperature sensor and display system

Start by cloning the https://github.com/lmkr/stopablethread.git repository and import the `stopablethread` project into your IDE.

The `example`-package in the repository contains the start of an implementation of the IoT-system from earlier consisting of a temperature sensor and a display communicating with shared memory.

You are to complete the implementation of the system by having the main-thread create a stopable threads for the sensor and the display. The main-thread should use `Thread.sleep` to let the two stopable threads run for a while and then stop the two threads. The main-thread should use `join`-method to wait for the termination of the two stopable threads before it stops itself.

Furthermore, you are to complete the implementation of the sensor stopable-thread and the display stopable-thread such that the resulting system would produce output similar to what is specified below. The sensor should wait 1 second between sensor-readings and the display should also wait 1 second between updating the display with the current temperature.

```
IoT system starting ...
Sensor device starting ...
Display device starting ...
DISPLAY: 0
READING: -12
DISPLAY: -12
[READING/DISPLAY removed from here to shorten output]
READING: 6
DISPLAY: 6
Sensor device stopping ...
Display device stopping ...
IoT system shutting down ...
```

### Exercise 5: Network Routing Framework (NOT IN 2023)

Exercises 1-3 related to the NRF framework: https://github.com/lmkr/dat110-nrf/blob/master/README.md
