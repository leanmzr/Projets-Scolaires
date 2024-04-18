#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Mar 18 20:23:08 2022

@author: leanmazier
"""

import sys
from PyQt5.QtWidgets import QApplication, QWidget , QLabel, QMainWindow, QPushButton,QGridLayout, QMessageBox
from PyQt5.QtCore import QCoreApplication ,Qt
from PyQt5.QtGui import QPixmap, QIcon
import random , time
from PyQt5 import QtTest



class Memory (QMainWindow):
    def __init__ (self):
        super().__init__()
        
        self.setWindowTitle('memories')

        self.layout = QGridLayout()
        self.liste_B=[]
        self.listeChoix=[]
        cartes=[1,1,2,2,3,3,4,4,5,5]
        for i in range (2):
            for j in range (5):
                carte=random.choice(cartes)
                cartes.remove(carte)
                self.bouton = QPushButton(str(carte))
                self.bouton.clicked.connect(self.onClick)
                self.bouton.setStyleSheet('background-color :#121557; color: rgba(255, 255, 255, 0);')
                
                #self.bouton.setStyleSheet('color : black; background-color : #7B81ED')
                self.bouton.setEnabled(False)
                self.liste_B+=[self.bouton]
                self.layout.addWidget(self.bouton,i,j)
            
        self.bouton_bas = QPushButton("c'est partie !")
        self.bouton_bas.setEnabled(True)
        
        
        self.bouton_bas.clicked.connect(self.reussite)
        #self.bouton_bas.setAlignment(Qt.AlignCenter)
        self.layout.addWidget(self.bouton_bas,2,0,1,5)
        
               
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
        self.bouton_bas.setText('A vous de jouer')
        for i in self.liste_B:
            i.setEnabled(True)
            i.setStyleSheet('background-color :#121557; color: rgba(255, 255, 255, 0);')

        
        
    def onClick(self) :
        
        self.sender().setEnabled(False)
        self.sender().setStyleSheet('color : blue; background-color : #7BBDED')
        
        self.listeChoix+=[(self.sender().text(),self.sender())]
        print(len(self.listeChoix))
        if self.Choix() == 'rien' :
            
            self.bouton_bas.setText('faites un deuxieme choix')
            self.bouton_bas.setStyleSheet('color : black')
            
        if self.Choix() == 'non' :
            QtTest.QTest.qWait(1000)
            self.bouton_bas.setText('erreur recommencez')
            self.bouton_bas.setStyleSheet('color : red')
            self.listeChoix[0][1].setEnabled(True)
            self.listeChoix[1][1].setEnabled(True)
            self.listeChoix[0][1].setStyleSheet('background-color :#121557; color: rgba(255, 255, 255, 0);')
            self.listeChoix[1][1].setStyleSheet('background-color :#121557; color: rgba(255, 255, 255, 0);')
            self.listeChoix=[]
            
            
        if self.Choix() == 'oui' :
            self.bouton_bas.setText('Bravo')
            self.bouton_bas.setStyleSheet('color : green')
            self.listeChoix=[]
            
            
        
        a=0 
        for i in self.liste_B:
           
           if i.isEnabled()==True:
               a+=1
        if a==0:
            self.popup = QMessageBox(QMessageBox.Warning , 'victoire' , ' Bravo tout est découvert !')
            self.popup.show()
            self.bouton_bas.setEnabled(True)
            self.bouton_bas.setText('c est partie !')
            self.close()
            
    
            
app = QCoreApplication.instance()
if app is None:
    app = QApplication(sys.argv)

window = Memory()




window.show() 

app.exec_()