import socket

HOST = "127.0.0.1"  # The server's hostname or IP address
PORT = 8080  # The port used by the server

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
   
s.sendto(b"Hello, world",(HOST,PORT))
data = s.recv(1024)

print(f"Received {data!r}")