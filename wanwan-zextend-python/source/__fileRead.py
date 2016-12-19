#!/usr/bin/python3
'''file read
'''
def fileRead():   
    f = open("E:\design\linux\linux.cet.4", "r")  
    while True:  
        line = f.readline()  
        if line:
            print(line)
            
fileRead();
