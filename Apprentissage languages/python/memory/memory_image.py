#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Mar 18 22:26:56 2022

@author: leanmazier
"""


import sys
from PyQt5.QtWidgets import QApplication, QWidget , QLabel, QMainWindow, QPushButton,QGridLayout, QMessageBox,QHBoxLayout
from PyQt5.QtCore import QCoreApplication ,Qt
from PyQt5.QtGui import QPixmap, QIcon
import random , time
from PyQt5 import QtTest



class Memory (QMainWindow):
    def __init__ (self):
        super().__init__()
        
        self.setWindowTitle('Memory')
        
        self.layout = QGridLayout()
        self.liste_B=[]
        self.listeChoix=[]
        cartes=[1,1,2,2,3,3,4,4,5,5]
        self.score=0
        self.deco= "*{border: 4px solid '#7640F6'; border-radius: 30px; font-size: 35px; color : '#7640F6'; padding : 25px 20px; margin : 20px 20px;} *:hover{color : '#FFFFFF';background: '#7640F6';}"
        
        #afffichage des cartes
        
        for i in range (2):
            for j in range (5):
                carte=random.choice(cartes)
                cartes.remove(carte)
                self.bouton = QPushButton(str(carte))
                self.bouton.clicked.connect(self.onClick)
                self.bouton.setStyleSheet('background-color :#E1DCEC ;'+
                                         
                                          'color: rgba(255, 255, 255, 0);'+
                                          "padding : 50px 50px;"+
                                          "border-radius: 10px;"
                                          )
                
                
                #self.bouton.setStyleSheet('color : black; background-color : #7B81ED')
                self.bouton.setEnabled(False)
                self.liste_B+=[self.bouton]
                self.layout.addWidget(self.bouton,i+1,j)
                
        # score
        
        self.label_score =QLabel("Coups : "+str(self.score))
        self.label_score.setAlignment(Qt.AlignCenter)
        self.label_score.setStyleSheet( "background-color :#7640F6  ;"+
                                      
                                        "margin : 20px 0px;"+
                                        "font-size: 25px;" +
                                        "color : 'white';" +
                                        "padding : 10px 0px;"
                                       )
        
        self.layout.addWidget(self.label_score,0,0,1,5)
        
        
        # bouton du bas
        
        self.bouton_bas = QPushButton("PLAY")
        self.bouton_bas.setEnabled(True)
        self.bouton_bas.setStyleSheet( self.deco)

        self.bouton_bas.clicked.connect(self.reussite)
        #self.bouton_bas.setAlignment(Qt.AlignCenter)
        self.layout.addWidget(self.bouton_bas,3,1,1,3)
        
        
        #layout
        
    
        
        self.widget = QWidget()
        self.widget.setLayout(self.layout)
        self.setCentralWidget(self.widget)  
        
    def Choix(self):
        
        if len(self.listeChoix)==2:
            if self.listeChoix[0][0]==self.listeChoix[1][0]:
                
                return 'oui'
            else :
                return 'non'
        return 'rien'
            
    def reussite(self):
            
            self.bouton_bas.setEnabled(False)
            self.bouton_bas.setText('faites un choix')
            self.bouton_bas.setStyleSheet(self.deco)
            for i in self.liste_B:
                i.setEnabled(True)
                

            
        
    def onClick(self) :
        
        self.sender().setEnabled(False)
        self.sender().setStyleSheet('color : blue;'+
                                    "background-image : url(images/cartememo"+str(self.sender().text())+".png);"+
                                    'color: rgba(255, 255, 255, 0);'+
                                          "padding : 50px 50px;"+
                                          "border-radius: 10px;")
        
        self.listeChoix+=[(self.sender().text(),self.sender())]
        print(len(self.listeChoix))
        
        # repiocher
        
        if self.Choix() == 'rien' :
            
            self.bouton_bas.setText('Encore une')
            self.bouton_bas.setStyleSheet(self.deco)
            
        #mauvaise pioche 
        
        if self.Choix() == 'non' :
            QtTest.QTest.qWait(1000)
            self.bouton_bas.setText("raté !")
            self.bouton_bas.setStyleSheet(self.deco)
            self.listeChoix[0][1].setEnabled(True)
            self.listeChoix[1][1].setEnabled(True)
            self.listeChoix[0][1].setStyleSheet('background-color :#E1DCEC;'+
                                          'color: rgba(255, 255, 255, 0);'+
                                          "padding : 50px 50px;"+
                                          "border-radius: 10px;"
                                          )
            self.listeChoix[1][1].setStyleSheet('background-color :#E1DCEC;'+
                                          'color: rgba(255, 255, 255, 0);'+
                                          "padding : 50px 50px;"+
                                          "border-radius: 10px;"
                                          )
            self.listeChoix=[]
            self.score+=1
            self.label_score.setText("Coups : "+str(self.score))
            
            
        # paire 
        
        if self.Choix() == 'oui' :
            self.bouton_bas.setText('trop fort !')
            self.bouton_bas.setStyleSheet(self.deco)
            self.listeChoix=[]
            self.score+=1
            self.label_score.setText("Coups : "+str(self.score))
            
        # verif fin victoire
         
        a=0 
        for i in self.liste_B:
           
           if i.isEnabled()==True:
               a+=1
        if a==0:
            self.popup = QMessageBox(QMessageBox.Warning , 'victoire' , ' Waaaaa trop fort.. tu as gagné avec seulement : '+str(self.score)+" Coups")
            self.popup.show()
            self.bouton_bas.setEnabled(True)
            QtTest.QTest.qWait(1000)
            self.bouton_bas.setText('c est reparti !')
            self.close()
            
    
            
app = QCoreApplication.instance()
if app is None:
    app = QApplication(sys.argv)

window = Memory()

window.setFixedWidth(750)
window.setFixedHeight(550)
window.setStyleSheet("background: #FFFFFF;")




window.show() 

app.exec_()