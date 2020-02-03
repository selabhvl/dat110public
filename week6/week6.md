## DAT110: Distributed Systems and Network Technology

### Lab Week 6: 3/2 - 7/2

#### Exercise 6.1 - Project 1

Complete task 2 and task 3 on project 1:

https://github.com/selabhvl/dat110-project1-startcode/blob/master/README.md

#### Exercise 6.2 - Language-based RPC (JavaRMI) ####

A complete (and simple) example of using Java Remote Method Invocation (RMI) as discussed in the lecture can be found in this link.
See the ReadMe file on how to run this example code.

https://github.com/selabhvl/dat110public-2020/blob/master/week6/JavaRMI-DSLab1.

Import the code into your IDE and go through the implementation, run the example to understand how it works.

##### Task

If you run the example code, you will observe that the server (ComputeServer) is still listening on the specified port 9000
after the client task is finished.
You will need to manually terminate the server to close this port.

As a simple task, implement an additional method on the server that can be remotely invoked by client to terminate the server once the 
client is done with the addNumber operation. 


