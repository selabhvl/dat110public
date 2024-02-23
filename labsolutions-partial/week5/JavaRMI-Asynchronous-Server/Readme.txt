Java Remote Method Invocation (RMI) - Asynchronous RPC Server
The Java RMI is a language-based implementation of remote procedure call (RPC). 
This code example illustrates one way of constructing an asynchronous RPC Server

In this implementation, clients need to register their callback objects on the servercallback object.
Two interfaces are provided - ClientCallbackInterface and ServerCallbackInterface. The ClientCallbackInterface defines 