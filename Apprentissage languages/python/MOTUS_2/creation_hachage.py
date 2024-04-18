#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon May  9 16:51:47 2022

@author: leanmazier
"""
"""import random , time, unidecode

filin = open("dicoprof_mot.txt", "r",encoding = "utf-8" )
listemot2=filin.readlines()
filin.close()
listemot=[]
for i in listemot2:
    if 4<=len(i) and len(i)<=9:
        listemot+=[unidecode.unidecode(i)[:len(i)-1]]

#print(listemot)

liste = [_ for _ in range (10000)]
hachage = {}
for a in  liste :
    hachage[a]=[]
"""

def chiffrage(mot):
    chiffre=''
    for lettre in mot :
        chiffre += bin(ord(lettre))[2::]
    return int(chiffre,2)%10000

def ajout (listemot ,hachage):
    for mot in listemot :
        hachage[chiffrage(mot)]+=[mot]
    
       
def verif (mot ,hachage):
    #print(hachage[chiffrage(mot)])
    print(chiffrage(mot))
    if mot in hachage[chiffrage(mot)] :
        
        return True
    else : return False
    
    
"""ajout(listemot ,hachage)
    
print(hachage)
print(listemot[6753])
print(verif(listemot[6753], hachage))

filin = open("dico_haché.txt", "x",encoding = "utf-8" )
filin.write(str(hachage))
filin.close()"""