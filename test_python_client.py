import socket
import sys
import time

# Create a UDP socket
def connectToServer(server_address):
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    # Connect the socket to the port on the server given by the caller
    print >>sys.stderr, 'connecting to %s port %s' % server_address
    sock.connect(server_address)
    return sock


if __name__=='__main__':
    server_address = ('192.168.75.1', 8003)
    sock = connectToServer(server_address)
    while True:    
        try:
            message = 'OK from EMILY interface'
            print >>sys.stderr, 'sending "%s"' % message
            sock.sendall(message)        
            amount_received = 0
            amount_expected = len(message)
            while sock:
                try:
                    data = sock.recv(2048)            
                    print 'received "%s"' % data
                    time.sleep(1)
                except socket.error, msg:
                        #sys.stderr.write("[ERROR] %s\n" % msg[1])
                        sock = connectToServer(server_address)
                        break
        
        finally:
            sock.close()