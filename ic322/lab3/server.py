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

 stream = request.split(" ")
 file = stream[1]
 file = file[1:]

 f1 = open(file, "rb")
 content = f1.read()
 f1.close()

 response = """
 HTTP/1.1 200 OK

""".encode() + content

 connectionSocket.send(response)
 connectionSocket.close()
 