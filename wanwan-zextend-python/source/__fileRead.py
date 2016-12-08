#!/usr/bin/python3
'''file read
'''
def fileRead():   
    f = open("E:\design\js\jquery\div-set.txt", "r")  
    while True:  
        line = f.readline()  
        if line:
            print(line)
            
fileRead();
