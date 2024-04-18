#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Feb  8 09:02:40 2022

@author: leanmazier
"""
import numpy as np
import time


from copy import copy
coord =[ (0,0),(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2)] 


def probasur32(listede3):
    listede3=list(listede3)
    C1=listede3.count(1)
    C2=listede3.count(2)
    
    if (C1 ==1 and C2==1) or (C2==2 and C1==1) or(C2==1 and C1==2) or(C2==0 and C1==0) :
        probL=0
        
    if (C1 ==0 and C2==3) :
        probL=1000
        
    if (C1 ==3 and C2==0) :
        probL=-1000
        
    if (C1 ==0 and C2==2) :
        probL=30  
        
    if (C1 ==2 and C2==0) :
        probL=-40 
        
    if (C1 ==0 and C2==1) :
        probL=10 
        
    if (C1 ==1 and C2==0) :
        probL=-10 

    
    return probL




def verifdiag2(plateau,case,d):
    listede3=[]
    for i in d:
        listede3+=[plateau[i]]
        #print(i)
        #print(plateau[case])
    probd=probasur32(listede3)
    #print(listede3)
    return probd

def jouerF(plateau ,case,joueur):
    plateau2=copy(plateau)
    plateau2[case]=joueur
    
    return plateau2




def probapartielle2(plateau,case=0):
    d1 = [(0,0),(1,1),(2,2)]
    d2 = [(0,2),(1,1),(2,0)]
    
    return verifdiag2(plateau,1,d1)+verifdiag2(plateau,1,d2)+probasur32(plateau[0,:])+probasur32(plateau[1,:])+probasur32(plateau[2,:])+probasur32(plateau[:,0])+probasur32(plateau[:,1])+probasur32(plateau[:,2]) 



           


    
def creationdico(plateau,joueur):
    dico ={}
    for x in range(3) :
        for y in range (3):
            case=x,y
            
            if plateau[case]==0:    
                plateau2=jouerF(plateau,case,joueur)
                dico[case]=[probapartielle2(plateau2,case),plateau2]
                
    return dico

def triedudico(dicoA):
    print(dicoA)
    a=0
    
    for i in dicoA:
        if a==0:
            case=i
            maxi =dicoA[case]
        a+=1
        if dicoA[i]>maxi:
            maxi=dicoA[i]
            case=i
             
    return case

        
def trieliste(liste,joueur):

        if joueur ==2:
            maxi =liste[0]                                  
            for i in liste:
                
                if i>maxi:
                    maxi=i
                    
            return maxi
        if joueur ==1:
            mini =liste[0]                                   
            for i in liste:
                if i<mini:
                    mini=i
            return mini

def jeux_theorique(plateau,joueur,nv_diff):
    #print("descendre")
    if nv_diff == 0:
        #print("choix : ",plateau, probapartielle2(plateau))
        return probapartielle2(plateau)
    
    
    
    joueur = joueur %2
    joueur +=1
    if joueur==0:        
        joueur =2
    print("joueur", joueur)

    valeur=[]
    casedispo=[]
    for x in range(3) :
        for y in range (3):
           
            case=x,y
            if plateau[case]==0:
                casedispo+=[case]
    #print("nb caes dispo :", len(casedispo))
    for i in casedispo  :   
        print("diff :",nv_diff)
        plateau2=jouerF(plateau,i,joueur)
        print(plateau2, probapartielle2(plateau2))
        valeur += [jeux_theorique(plateau2,joueur,nv_diff-1)]
        
         
    
    #print("remonter")
    print(valeur,joueur)
    print(trieliste(valeur,joueur))
    return trieliste(valeur,joueur)
                               
                
def choix_theorique(plateau,joueur,nv_diff) :             
    dico=creationdico(plateau,joueur)
    #print(dico)
    for i in dico :
        print("branche : ",dico[i][1])
        dico[i]=jeux_theorique(dico[i][1],joueur,nv_diff-1)
        
    return triedudico(dico)
        

def verifwin(plateau):
    
    for i in range(3):
        
        if (list(plateau[:,i]).count(1))==3  :
            print ("bravo vous avez gagnez")
            return True
        if (list(plateau[:,i]).count(2))==3  :
            print ("bien essayé")
            return True
        if (list(plateau[i,:]).count(1))==3  :
            print ("bravo vous avez gagnez")
            return True
        if (list(plateau[i,:]).count(2))==3  :
            print ("bien essayé")
            return True
        
        
    d1 = [(0,0),(1,1),(2,2)]
    d2 = [(0,2),(1,1),(2,0)]
    listeD1=[]
    listeD2=[]
    for i in d1:
        listeD1+=[plateau[i]]
    for i in d2:
        listeD2+=[plateau[i]]
    if listeD1.count(1)==3 or listeD2.count(1)==3   :
            print ("bravo vous avez gagnez")
            return True
    if listeD2.count(2)==3 or listeD1.count(2)==3  :
        print ("bien essayé")
        return True
        
    return False   

def vrai_jeu2():      
    plateau=np.array([[0,0,0],[0,0,0],[0,0,0]]) 
    nv_diff =int(input("a quel niveau de difficulté souhaitez vous jouer ?(chiffre)"))
    
    print(plateau)
    case_dispo=9
    win=False
    while case_dispo!=0 and win==False :
        x,y=input("sur quel ligne et colone jouez vous ? (format 'x y')").split()
        caseF= int(x)-1,int(y)-1
        if plateau[caseF]!=0:
            print("cette case est deja occupée, essayez encore")
            
        if plateau[caseF]==0:
            plateau[caseF]=1
            time.sleep(0.5)
            print("\n", plateau)
            win=verifwin(plateau)
            case_dispo+=-1
            if win ==True:
                break
            if case_dispo!=0 :
                case=choix_theorique(plateau,2,nv_diff)  
                plateau=jouerF(plateau,case,2)
                time.sleep(1)
                print ("\n",plateau)
                win=verifwin(plateau)
                case_dispo-=1
    
        if case_dispo ==0:
            print ("Egalité")
            break
'''        
plateau=np.array([[2,1,2],[0,1,0],[0,0,1]])        
choix_theorique(plateau,2,4) 
'''        
vrai_jeu2()