from socket import *
#!/usr/bin/env python
import signal
import sys

serverPort = 1200
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))
serverSocket.listen(1)

print('The server is ready to receive')

while True:
 connectionSocket, addr = serverSocket.accept()
 request = connectionSocket.recv(1024).decode()
 print(request)



 #connectionSocket.send(response.encode())

 def signal_handler(signal, frame):
        # close the socket here
        serverPort.close()
        sys.exit(0)
 signal.signal(signal.SIGINT, signal_handler)
 
 
 #connectionSocket.send(capitalizedSentence.encode())