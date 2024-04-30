# -*- coding: utf-8 -*-
"""
Created on Mon Mar 13 17:52:00 2023
@author: Moi
"""

import serial
import cv2
import numpy as np
import time

tshirt_cascade = cv2.CascadeClassifier('cascade.xml')
pantalon_cascade = cv2.CascadeClassifier('cascade_pant.xml')
ser = serial.Serial('COM3',9600)

global timer
timer =0

def countdown(num_of_secs):
    
    while num_of_secs:
        timer =1
        m, s = divmod(num_of_secs, 60)
        min_sec_format = '{:02d}:{:02d}'.format(m, s)
        print(min_sec_format, end='/r')
        time.sleep(1)
        num_of_secs -= 1
    timer =0




def ajoutmem(memoire, aj):
    memret = [memoire[1], memoire[2], memoire[3], aj]
    return memret


def verifcoord(coord1, coord2):
    espace = []
    for i in range(10):
        espace += [coord1 + i]
        espace += [coord1 - i]
    if coord2 in espace:
        return True
    else:
        return False


def verifmem(memoire,typ_vet):
    case = 0
    for i in range(3):
        coord = 0
        for j in range(4):
            if verifcoord(memoire[i][j], memoire[i+1][j]):
                coord += 1
        if coord < 4:
            return 00000000
        else:
            case += 1
    if case != 3:
        return 000000000
    else:
        print('marche')
        
        return typ_vet
    
def link(typvet):
    print(typvet)
    
    if typvet == 1 :
        ser.write(b'01')
        countdown(15)
    elif typvet == 2 :
        ser.write(b'2')
        countdown(15)
    else:
        ser.write(b'00')
    
    


cap = cv2.VideoCapture(1)
i = 0
memoire = [(1, 1, 1, 110), (1, 1, 1, 1), (1, 1, 1, 1), (1, 1, 1, 1)]
while True:
    i += 1
    ret, img = cap.read()
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    tshirts = tshirt_cascade.detectMultiScale(
        gray, 1.15, 10, minSize=[170, 170])
    for (x, y, w, h) in tshirts:
        cv2.rectangle(img, (x, y), (x+w, y+h), (255, 0, 0), 2)
        if timer == 0 :
            memoire = ajoutmem(memoire, (x, y, w, h))
            typvet = verifmem(memoire,1)
            link(typvet)
           


    #tshirts2 = tshirt_cascade.detectMultiScale(gray, 1.3, 6, minSize=[170,170])
    # for (x,y,w,h) in tshirts2:
        #cv2.rectangle(img,(x,y),(x+w,y+h), (255,255,0), 2)

    pants = pantalon_cascade.detectMultiScale(
        gray, 1.2, 15, minSize=[170, 170])
    for (x1, y1, w1, h1) in pants:
        cv2.rectangle(img, (x1, y1), (x1+w1, y1+h1), (0, 0, 255), 2)
        
        if timer == 0 :
            memoire = ajoutmem(memoire, (x1, y1, w1, h1))
            
            typvet = verifmem(memoire,2)
            link(typvet)
    cv2.imshow('img', img)
    k = cv2.waitKey(30) & 0xff
    if k == 27:
        break
    
ser.close()
cap.release()
cv2.destroyAllWindows()
print("aczvre")
