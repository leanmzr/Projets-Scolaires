#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Apr 27 20:29:28 2022

@author: leanmazier
"""

#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Mar 18 22:26:56 2022

@author: leanmazier
"""


import sys
from PyQt5.QtWidgets import QApplication, QWidget , QLabel, QMainWindow, QPushButton,QGridLayout, QMessageBox,QVBoxLayout
from PyQt5.QtCore import QCoreApplication ,Qt
from PyQt5.QtGui import QPixmap, QIcon
import random , time
from PyQt5 import QtTest
import random , time,ast, unidecode

#
from creation_hachage import verif 

filin = open("dico_haché.txt", "r" , encoding='utf-8')

hachage=ast.literal_eval(filin.readline())
filin.close()

#Mot_Choisit= random.choice(random.choice(hachage))
#


#
filin = open("dicoprof_mot.txt", "r",encoding = "utf-8" )
listemot=filin.readlines()
filin.close()
Mot_Choisit=unidecode.unidecode(random.choice(listemot))
while 4>len(Mot_Choisit) or len(Mot_Choisit)>9:
    Mot_Choisit=unidecode.unidecode(random.choice(listemot))
#
print(Mot_Choisit)

def verif2(mot_test,mot):
    if verif (mot_test , hachage)==True and mot_test[0]==mot[0]:
        return True
    else:
        return False

def find( lettre, listdumot):
    for i, l in enumerate(listdumot):
        #print(l)
        if l== lettre :
            #print(i)
            return i
        
        


class Memory (QMainWindow):
    
    def __init__ (self):
        super().__init__()
        cp='#685FFF'
        self.deco= "*{border: 4px solid "+cp+"; border-radius: 30px; font-size: 35px; color : "+cp+"; padding : 25px 20px; margin : 20px 20px;} *:hover{color : '#FFFFFF';background: '"+cp+"';}"
        self.setStyleSheet('background-color: white;')
        
        self.setWindowTitle('motus')
        self.layout = QGridLayout()
        self.comptRemp=0
        self.mot_test=''
        self.liste_B=[]
        self.bouton_clavier={}
        self.comp2=0
        clavier=[['a','z','e','r','t','y','u','i','o','p'],['q','s','d','f','g','h','j','k','l','m'],['w','x','c','v','b','n','<-']]
        self.decoC= "*{border: 4px solid '"+cp+"'; border-radius: 10px; font-size: 12px; color : '"+cp+"'; padding : 5px 2px; margin : 2px 2px;} *:hover{color : '#FFFFFF';background: '"+cp+"';}"
        self.decocase= "border: 3px solid '#F6F5FE'; border-radius: 5px; font-size: 12px; color : rgb(255,255,255,0); padding : 5px 2px; margin : 2px 2px; "
        self.decoEc="border: 3px solid '#F6F5FE'; border-radius: 5px; font-size: 12px; color : "+cp+"; padding : 5px 2px; margin : 2px 2px; "
        self.decocaseR = "background-color : red; border: 1px solid '#F6F5FE'; border-radius: 5px; font-size: 12px; color : #F6F5FE; padding : 5px 2px; margin : 2px 2px; "
        self.decocaseJ = "background-color : yellow; border: 1px solid '#F6F5FE'; border-radius: 5px; font-size: 12px; color : black; padding : 5px 2px; margin : 2px 2px; "
        self.decoCR= "*{background-color : red; border: 4px solid 'red'; border-radius: 10px; font-size: 12px; color : #F6F5FE; padding : 5px 2px; margin : 2px 2px;} *:hover{color : 'black';background: white;}"
        self.decoCJ="*{background-color : yellow; border: 4px solid 'yellow'; border-radius: 10px; font-size: 12px; color : black; padding : 5px 2px; margin : 2px 2px;} *:hover{color : 'black';background: white;}"
        #afffichage des cartes
        self.mot=Mot_Choisit
        
        
        self.label = QLabel('MOTUS') 
    
        #self.label.setFixedHeight( 100)
        
        self.label.setStyleSheet("font-size: 30pt; font-family: sans-serif;color : rgb(255,0,255); ")
        #self.label.setAlignment(Qt.AlignCenter)
        self.layout.addWidget(self.label,0,4,1,3)
        
        
        
        
        a=1
        for essaie in range (len(self.mot)-1):
            a+=1
            for i in range (len(self.mot)-1):
                
                    self.bouton = QPushButton(self.mot[i])
                    self.liste_B+=[self.bouton]
                    if i==0 :
                        self.bouton.setStyleSheet(self.decoEc)
                        
                    else:
                         self.bouton.setStyleSheet(self.decocase)
                    self.bouton.setEnabled(False)
                    self.layout.addWidget(self.bouton,2+essaie,i)

        
        #clavier
        
        
        for ligne in clavier:
            a+=1
            b=-1
            for i in ligne :
                b+=1
                
                self.bouton = QPushButton(i)
                self.bouton.setStyleSheet(self.decoC)
                self.bouton_clavier[i]=[self.bouton]
                self.bouton.clicked.connect(self.clavierClick)
                self.layout.addWidget(self.bouton,a+1,b)
                
        
        
        #layout
        
    
        
        self.widget = QWidget()
        self.widget.setLayout(self.layout)
        self.setCentralWidget(self.widget)  

    def clavierClick(self):
        
        if self.sender().text() == '<-':
            self.mot_test = self.mot_test[:-1]
            self.comptRemp-=1
            btn=self.liste_B[self.comptRemp%(len(self.mot)-1)+(len(self.mot)-1)*self.comp2]
            btn.setText(' ')
            
            btn.setStyleSheet(self.decocase)
            
        
        if self.sender().text() != '<-':
            self.mot_test+=self.sender().text()
            
            btn=self.liste_B[self.comptRemp%(len(self.mot)-1)+(len(self.mot)-1)*self.comp2]
            btn.setText(self.sender().text())
            btn.setStyleSheet(self.decoEc)
            
            
            self.comptRemp+=1
            
            if self.comptRemp ==(len(self.mot)-1):
                QtTest.QTest.qWait(700)
                
                print(self.mot_test)
                
                if verif2(self.mot_test,self.mot)==True:
                    self.Affichage_Mot()
                else :
                    for i in range (len(self.mot)-1):
                        if i==0 :
                            self.liste_B[self.comp2*(len(self.mot)-1)+i].setStyleSheet(self.decoEc)
                            self.liste_B[self.comp2*(len(self.mot)-1)+i].setText(self.mot[0])
                        else :
                            self.liste_B[self.comp2*(len(self.mot)-1)+i].setStyleSheet(self.decocase)
                    self.comp2+=-1
                    self.liste_B[self.comp2*(len(self.mot)-1)].setText(self.mot[0])
                    
                
                self.comp2+=1
                self.comptRemp=0
                self.mot_test=''
            
    def Affichage_Mot (self):
        win=0
        listedumot=list(self.mot)
        for i in range (len(self.mot_test)):
            #print(listedumot)
            if self.mot[i]==self.mot_test[i]:
                
                self.liste_B[self.comp2*(len(self.mot)-1)+i].setStyleSheet(self.decocaseR)
                listedumot[i]='X'
                self.bouton_clavier[self.mot[i]][0].setStyleSheet(self.decoCR)
                #print(type(self.bouton_clavier[self.mot_test[i]]))
                win+=1
            elif self.mot_test[i] in listedumot:
                self.liste_B[self.comp2*(len(self.mot)-1)+i].setStyleSheet(self.decocaseJ)
                listedumot[find(self.mot_test[i],listedumot)]='X'
                #print(self.bouton_clavier[self.mot_test[i]][0].palette().window().color().name() )
                if  self.bouton_clavier[self.mot_test[i]][0].palette().window().color().name() !='ff0000':
                    self.bouton_clavier[self.mot_test[i]][0].setStyleSheet(self.decoCJ)
        
        if win==len(self.mot)-1:
            self.popup = QMessageBox(QMessageBox.Warning , 'victoire' , ' Waaaaa trop forte.. tu as gagné en seulement : '+str(self.comp2)+" esssais")
            self.popup.show()
            
  
        

    
            
app = QCoreApplication.instance()
if app is None:
    app = QApplication(sys.argv)

window = Memory()

window.setFixedWidth(750)
window.setFixedHeight(550)
#window.setStyleSheet("background: #FFFFFF;")




window.show() 

app.exec_()