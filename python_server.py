# -*- coding: utf-8 -*-
"""
Created on Thu Apr 20 11:45:13 2017

@author: vinay
"""
import socket
import sys
sys.path.append(r"C:\Python27\Lib")
sys.path.append(r"C:\Python27\Lib\Json")
import clr
import time
import json
import re, string
clr.AddReference("MissionPlanner.Utilities")
import MissionPlanner #import *
clr.AddReference("MissionPlanner.Utilities") #includes the Utilities class



#get the data from mission planner
def getCurrentData():
    listofvalues = {}
    # cs is current status class given by mission planner
    listofvalues['armed'] = bool(cs.armed) 
    listofvalues['battary'] = float(cs.battery_remaining)
    listofvalues['wireless'] = float(cs.linkqualitygcs)
    listofvalues['gps_status'] = float(cs.gpsstatus)
    listofvalues['emily_speed'] = float(cs.groundspeed)
    listofvalues['dist2home'] = float(cs.DistToHome)
    listofvalues['dist2waypoint'] = float(cs.wp_dist)
    listofvalues['emily_location'] = {'lat':float(cs.lat), 'lng':float(cs.lng)}
    listofvalues['emily_home_location'] = {'lat':float(cs.HomeLocation.Lat), 'lng':float(cs.HomeLocation.Lng)}

    try:
        jsonarray = json.dumps(listofvalues, sort_keys=True, indent=4, separators=(',', ': '))
        return str(jsonarray)
        #print len(str(jsonarray))
    except: 
        sys.stderr.write("[ERROR] JSON array dump error\n")
     #emily_compass = 
    	return str('JSON ERROR')
    	#way_points = 
    
    	



sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
print 'Sockets created'

HOST = socket.gethostbyname(socket.gethostname())   # Symbolic name meaning all available interfaces

PORT = 8000 # Arbitrary non-privileged port 


# Bind socket to local host and port 
while True:
    try:     
        sock.bind((HOST,PORT)) 
        sock.listen(1)
        break
    except socket.error, msg:
        #print 'Bind failed. Error Code:'
        PORT += 1
        #sys.stderr.write("[PORT] %d in use taking next available port\n" % PORT)
        continue
        
print 'Socket bind completed'


while True:
    print 'waiting for a connection'
    print 'Enter fallowing details in EMILY interface and connect'
    print 'IP     : ' + str(HOST)
    print 'PORT : ' + str(PORT)
    connection, client_address = sock.accept()
    try:
        print 'client connected:', client_address
        data = connection.recv(1024)
        print 'received "%s"' % data
        if data:
            print 'sending data to EMILY interface......'
            while True:
                try:              
                    currentstates = getCurrentData() 
                    connection.sendall(currentstates)
                except socket.error, msg:
                    sys.stderr.write("[ERROR] %s\n" % msg[1])
                    break
    finally:
        connection.close()

		
		
		
