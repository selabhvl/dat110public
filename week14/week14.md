## DAT110: Distributed Systems and Network Technology

### Lab Week 14: 30/3 - 3/4

### Exercise 14.1 - Project work

Complete the remaining tasks on project 3:

https://github.com/selabhvl/dat110-project3-startcode/blob/master/README.md

### Exercise 14.2 - Interfaces and IP address configuration

Make sure that your PC is connected to your local area network either via cable or via WiFi.

Start a terminal (mac) or a command prompt (windows) on your PC. Use the ifconfig (mac) or ipconfig tool (windows) to find the following information:

1. what IP network interfaces does your PC have?
- What is the IP address of each network interface?
- What is the link-layer address of each network interface?
- What is the network mask and network address of each interface?
- What DNS server(s) have been configured?
- What default gateways have been configured?

### Exercise 14.3 - Connectivity and routes

Start a terminal (mac) or a command prompt (windows) on your PC.

Use the `ping` tool to check connectivity to the `www.example.com`host.

Use the traceroute (mac) / tracert (windows) tool to obtain information about the route that a datagram travels from you PC to the the `www.example.com`host.

1. What is the IP addresses of the interfaces that the datagrams passes through.

2. Can you locate the IP address of your default gateway as identified in Exercise 14.2 on the route?

### Exercise 14.4 - Dynamic Host Configuration Protocol

Start the WireShark application and set it up such that it captures packets on the network interface with which you are connected to the Internet (wireless or cabled interface).

We will study the operation of the DHCP protocol. To see only DHCP packet, you can use the following `bootp` as a filter. Now do the following:

1. Switch of your cabled or wireless network interface on your PC (can also be done using flight mode).
2. Use ifconfig/ipconfig (as in Exercise 14.2) to see whether your network interface is configured with an IP address (it should not be)
3. Try to switch on you network interface again. What DHCP packets are captured?
4. Use ifconfig/config to check that your network interface has now been configured.

### Exercise 14.5 - Networking status

Experiment with the netstat command-line tool on your PC. What kind of information does the tool provide?

### Exercise 14.6 - Fragmentation and Maximum Transfer Unit

Consider an IP datagram with a total size of 3000 bytes (including 20 byte header) that needs to be transmitted across a communication link where the maximum transfer unit (MTU) is 1620 bytes. Assume that the original datagram identification is 888.

1. How many IP datagrams will the original IP datagram be divided into?
2. What will the amount of data, identification, offset, and flag be in the resulting IP datagrams?

### Exercise 14.7 - IP addresses and Routing - Exam Exercise 2019

#### a)

What 32-bit bit-pattern does the IP address 224.192.40.7 correspond to?

#### b)

Consider the CIDR (Class Internet Domain Routing) address block 224.192.40.0/22. What interval of addresses does this block span?

#### c)

Consider a router with the following forwarding table.

![](assets/markdown-img-paste-20200325114925185.png)

At which interface (1-4) will the router forward packets with the following destination IP addresses? (justify the answers)

1.	224.192.42.10
2.	224.192.57.14
3.	10.53.40.7
