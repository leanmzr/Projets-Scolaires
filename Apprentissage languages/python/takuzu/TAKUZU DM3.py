#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri May 27 21:09:57 2022

@author: leanmazier
"""



import sys
from PyQt5.QtWidgets import  QWidget,QMessageBox, QToolBar, QPushButton, QGridLayout, QLabel, QApplication, QMainWindow, QAction, QStatusBar,QInputDialog
from PyQt5.QtCore import QCoreApplication  ,QSize
from PyQt5 import QtCore
from PyQt5.QtGui import QIcon, QKeySequence
from PyQt5.QtGui import QPixmap 
import random
import numpy as np

class Fenetre(QMainWindow): 
    def __init__(self): 
        
        super().__init__() 
        
    # initialisation du format de la fentre
        self.setWindowTitle("Takuzu") 
        self.setGeometry(0,100,1000 ,700)
        self.setStyleSheet('background-color : white  ')
        
    #variable
        self.difficulte = 0
        self.depart = 0
    #layout et grille
        self.OnGrille44()
        
    # création des actions
        # Ouverture d'une grille 4x4
        self.Grille33 = QAction(QIcon("icones/grid-small.png"), "Grille 4x4", self)
        self.Grille33.setStatusTip("Ouvrir une grille 4x4")
        self.Grille33.triggered.connect(self.OnGrille44)
        self.Grille33.setShortcut(QKeySequence('Ctrl+G'))
		
        # Ouverture d'une grille 6x6
        self.Grille66 = QAction(QIcon("icones/grid.png"), "grille 6x6", self)
        self.Grille66.setStatusTip("Ouvrir une grille 6x6")
        self.Grille66.triggered.connect(self.OnGrille66)
        self.Grille66.setShortcut(QKeySequence('Ctrl+X'))
        
        # Choix de la taille de la grille
        self.ChoixGrille = QAction(QIcon("icones/application-list.png"),"Choisir la taille", self)
        self.ChoixGrille.setStatusTip("Choix")
        self.ChoixGrille.triggered.connect(self.OnChoixGrille)
        self.ChoixGrille.setShortcut('Ctrl+C')
        
        # Niveaux de difficulté
        self.Jdebutant = QAction(QIcon("icones/medal-silver.png"), "Niveau Debutant")
        self.Jdebutant.setStatusTip("Jouer avec un niveau de difficulté facile")
        self.Jdebutant.triggered.connect(self.onDiff0)
        self.Jdebutant.setShortcut(QKeySequence('Ctrl+D'))
		
        self.Jintermediaire = QAction(QIcon("icones/medal.png"), "Niveau Intermediaire")
        self.Jintermediaire.setStatusTip("Jouer avec un niveau de difficulté difficile")
        self.Jintermediaire.triggered.connect(self.onDiff1)
        self.Jintermediaire.setShortcut(QKeySequence('Ctrl+I'))
        
        # Quitter
        self.quit2=QAction(QIcon("icones/stickman-run-dash.png"), "sortir")
        self.quit2.setStatusTip("Passer en affichage Photomaton par 4")
        self.quit2.triggered.connect(self.onQuit)
        self.quit2.setShortcut(QKeySequence('Ctrl+Q'))
        
        # Affichage solution
        self.resolution=QAction(QIcon("icones/wand.png"), "Resolution")
        self.resolution.setStatusTip("Afficher la solution")
        self.resolution.triggered.connect(self.onSolution)
        self.resolution.setShortcut(QKeySequence('Ctrl+S'))
        
        # Règles et équipe
        self.Aregle=QAction(QIcon("icones/script-text.png"), "Regle du jeu")
        self.Aregle.setStatusTip("Ouvrir les regles du jeu")
        self.Aregle.triggered.connect(self.onRegle)
        self.Aregle.setShortcut(QKeySequence('Ctrl+R'))
        
        self.Aqui=QAction(QIcon("icones/user-thief-female.png"), "qui")
        self.Aqui.setStatusTip("qui somme nous ?")
        self.Aqui.triggered.connect(self.onQui)
        self.Aqui.setShortcut(QKeySequence('Ctrl+N'))
        
        self.setStatusBar(QStatusBar(self))
        
    # Ajout et connexion des actions au menu
        self.menuGrille = self.menuBar().addMenu("Grille")
        self.menuGrille.addAction(self.Grille33)
        self.menuGrille.addAction(self.Grille66)
        self.menuGrille.addAction(self.ChoixGrille)
        
        self.menuJouer = self.menuBar().addMenu("Jouer")        
        self.menuJouer.addAction(self.Jdebutant)        
        self.menuJouer.addAction(self.Jintermediaire)        
       
        
        self.menuResolution = self.menuBar().addMenu("Resolution")
        self.menuResolution.addAction(self.resolution)
        
        self.menuPropos = self.menuBar().addMenu("A propos")
        self.menuPropos.addAction(self.Aregle)
        self.menuPropos.addSeparator()
        self.menuPropos.addAction(self.Aqui)

        self.menuQ = self.menuBar().addMenu("Quitter")
        self.menuQ.addAction(self.quit2)
        
    # Ajout et création de la barre d'outils
        self.toolbar = QToolBar("Barre d'outils")
        self.toolbar.setIconSize(QSize(16,16))
        self.toolbar.setFixedHeight(32)
        
        self.addToolBar(self.toolbar)
        
        self.toolbar.addAction(self.Grille33)
        self.toolbar.addAction(self.Grille66)
        self.toolbar.addAction(self.ChoixGrille)
        self.toolbar.addAction(self.Jintermediaire)
        self.toolbar.addAction(self.Jdebutant)
        self.toolbar.addAction(self.resolution)
        self.toolbar.addAction(self.Aregle)
        self.toolbar.addAction(self.Aqui)
        self.toolbar.addAction(self.quit2)
        
        
    #affichage d'une grille 4x4 -> appelle de onGrille() avec la taille 4 + messagebox
    def OnGrille44(self):   
        #self.depart est la pour ne pas demander de changer de grille au lancement/depart
        if self.depart !=0:
            self.box = QMessageBox(QMessageBox.Question,"Attention","Êtes vous sur de vouloir changer de grille ?", QMessageBox.Yes| QMessageBox.No)
            if self.box.exec() == QMessageBox.Yes:
                self.OnGrille(4)
        else :
            self.OnGrille(4)
            
        self.depart+=1
        
    #affichage d'une grille 6x6 -> appelle de onGrille() avec la taille 6 + messagebox       
    def OnGrille66(self):   
        self.box = QMessageBox(QMessageBox.Question,"Attention","Êtes vous sur de vouloir changer de grille ?", QMessageBox.No| QMessageBox.Yes)
        if self.box.exec() == QMessageBox.Yes:
            self.OnGrille(6)
        
        
        
    def OnChoixGrille(self):
        # meme si le layout est adaptable a toutes taille 8x8 met trop de temps a se generer 
        items = ('2','4','6','8')

        taille, ok= QInputDialog.getItem(self, "Choix de la taille", 
           "Choisissez la taille de la grille", items, 0, False)
        taille = int(taille)
        self.OnGrille(taille)
    
    #fonction layout de la grille avec la taille en parametre
    def OnGrille(self,taille):
        
        self.taille = taille
        self.layout=QGridLayout()
        #grille de jeu n'est pas une liste avec les chiffres des boutons mais un dictionnaire avec les boutons leur position (bouton pas .text()) 
        self.GrilleJeu = {}
        self.GrilleSol = self.creationSol(taille) #generation d'une grille selon la taille
        self.masque = self.creationMasque(taille) #generation d'un masque (liste de 1 et -1)
        
        marg = 150/taille  #addaptation du layout selon la taille
        a=-1
        for i in range(taille) :
            for j in range(taille) :
                a+=1
                if self.masque[a] == -1:  #boutons a ne pas afficher
                    self.bouton = QPushButton(' ')
                    bord='#0853F3 '
                if self.masque[a] == 1:
                    self.bouton = QPushButton(str(self.GrilleSol[i,j])) #boutons a afficher
                    self.bouton.setEnabled(False)
                    bord='#000B96 '
                self.bouton.clicked.connect(self.OnBoutonGrille)
                self.bouton.setStyleSheet('background-color :#FFFFFF ;'
                                           'border: 4px solid '+bord+';'+
                                          "margin : 0px "+str(marg)+";"+
                                          "padding : "+str(marg)+" 0px;"+
                                          "border-radius: "+str(marg+10)+"px;"+
                                          "color : black;"+
                                          "font-size: "+str(marg)+"pt; font-family: sans-serif;color : rgb(0,0,255); "
                                          )
        
                self.GrilleJeu[(i,j)] = self.bouton  #ajout du bouton au dictionnaire associer a sa position ici grille jeu est une grille 1d mais elle devient 2d avant verif 
                self.layout.addWidget(self.bouton,i,j)
                
        
        #texte affiché lors de la verification
        self.textVerif =QLabel(" Appuyez sur 'vérifier' pour \n recevoir une indication. ")
        self.textVerif.setAlignment(QtCore.Qt.AlignCenter)
        self.textVerif.setStyleSheet("font-size: 20pt; font-family: sans-serif;color : rgb(255,0,255); "+
                                          "margin : 0px "+str(marg/2 )+";"+
                                          "padding : "+str(marg/2 )+" 0px;"
                                          )
        self.layout.addWidget(self.textVerif,taille+1,0,1,2)
        
        #bouton pour recevoir un indice (une case s'affiche)
        self.BIndice = QPushButton('Indice')
        self.BIndice.clicked.connect(self.onIndice)
        self.BIndice.setStyleSheet('background-color :#FFFFFF ;'+
                                           'border: 4px solid #000B96;'+
                                          "margin : 0px "+str(marg/2 )+";"+
                                          "padding : "+str(marg/2 )+" 0px;"+
                                          "border-radius: 10px;"
                                          )
        self.layout.addWidget(self.BIndice,taille+2,0)
        
        #bouton pour afficher la solution
        self.BSolution = QPushButton('solution')
        self.BSolution.clicked.connect(self.onSolution)
        self.BSolution.setStyleSheet('background-color :#FFFFFF ;'+
                                           'border: 4px solid #000B96;'+
                                          "margin : 0px "+str(marg/2 )+";"+
                                          "padding : "+str(marg/2 )+" 0px;"+
                                          "border-radius: 10px;"
                                          )
        self.layout.addWidget(self.BSolution,taille+2,taille-1)
        
        #bouton pour verifier son coup 
        self.Bverif = QPushButton('verifier')
        self.Bverif.clicked.connect(self.onVerif)
        self.Bverif.setStyleSheet('background-color :#FFFFFF ;'+
                                           'border: 4px solid #000B96;'+
                                          "margin : 0px "+str(marg/2 )+";"+
                                          "padding : "+str(marg/2 )+" 0px;"+
                                          "border-radius: 10px;"
                                          )
        self.layout.addWidget(self.Bverif,taille+2,int((taille-1)/2),1,2)
        
        if self.difficulte ==0 :
            self.nmbcoeur = 5
        if self.difficulte ==1 :     #difficulté associée au nombre de vie 
            self.nmbcoeur = 3
        
        
        self.labelvie = QLabel(' ') 
        self.pixmap = QPixmap('icones/coeur'+str(self.nmbcoeur)+'.png')   #image des coeurs de vie changeant selon le nombre qu'il en reste
        self.labelvie.setPixmap(self.pixmap) 
        self.layout.addWidget(self.labelvie,taille+1,int(taille/2),1,int(taille/2))
        
        
        self.widget = QWidget()
        self.widget.setLayout(self.layout)
        self.setCentralWidget(self.widget) 
        
    #changer le chiffre sur le bouton
    def OnBoutonGrille (self):
        
        if self.sender().text()== ' ' :
            self.sender().setText('0')
        elif self.sender().text()== '0':
            self.sender().setText('1')
        elif self.sender().text()== '1':
            self.sender().setText(' ')
        #self.grille 
        self.verificationWin()    #verification de victoire
        

    def onIndice (self):
        
        T=self.taille-1
        grilleS = list(self.GrilleSol)
        i,j = random.randint(0,T),random.randint(0,T)     #choix random d'une position 
        lisTemp = []
        for v,r in self.GrilleJeu.items() :   #creation d'une liste temporaire des texts associés aux boutons pour savoir si il en reste des vides = ' '
            lisTemp += [r.text()]
        
        while self.GrilleJeu[(i,j)].text() != ' ' or ' ' not in lisTemp:   # modification de la position si le chiffre est deja affiché
            i,j = random.randint(0,T),random.randint(0,T) 

        self.GrilleJeu[(i,j)].setText(str(grilleS[i][j]))      
        self.verificationWin()    
        

    def onQuit(self):   #note a moi : a finir 
        self.box = QMessageBox(QMessageBox.Question,"Quitter le jeu","Voulez-vous quitter TUKIMO ?", QMessageBox.Yes| QMessageBox.No)
        if self.box.exec() == QMessageBox.Yes:
            self.close()
        elif self.box.exec() == QMessageBox.No :
            pass
        
    
    #fonction qui affiche la solution
    def onSolution(self):
        grilleJ = list(self.GrilleSol)
        a=-1
        for i in range(self.taille):
            for j in range(self.taille):
                a+=1
                self.GrilleJeu[(i,j)].setText(str(grilleJ[i][j]))   
        self.verificationWin()
                
    #deux fonctions identiques selon le niveau de difficulté demandé
    def onDiff0(self):
        self.box = QMessageBox(QMessageBox.Question,"Attention","Passer au niveau facile, êtes vous sûr de changer de grille ?", QMessageBox.No| QMessageBox.Yes)
        if self.box.exec() == QMessageBox.Yes:
            self.difficulte = 0 
            self.setStyleSheet('background-color : white')
            self.OnGrille(4)    #retour a la grille de base 4x4
            
    def onDiff1(self):
        self.box = QMessageBox(QMessageBox.Question,"Attention","Passer au niveau intermédiaire, êtes vous sûr de changer de grille ?", QMessageBox.No| QMessageBox.Yes)
        if self.box.exec() == QMessageBox.Yes:
            self.difficulte = 1
            self.setStyleSheet('background-color : black')
            self.OnGrille(4)
            
    # Affichage des règles du jeu
    def onRegle (self):
        self.layout=QGridLayout()
        self.labeRegle = QLabel(" Les Regles du jeu :") 
        self.labeRegle.setStyleSheet("font-size: 40pt; font-family: sans-serif;color : rgb(255,0,255);")
        self.labeRegle.setAlignment(QtCore.Qt.AlignCenter)
        self.layout.addWidget(self.labeRegle,0,0)
         
        self.labeltext = QLabel('Le but est de remplir la grille de 0 et de 1 selon les criteres suivant : \n \n 1. Dans une ligne, il doit y avoir autant de 0 que de 1 \n 2. Dans une colonne, il doit y avoir autant de 0 que de 1 \n 3. Il ne peut pas y avoir deux lignes identiques dans une grille \n 4. Il ne peut pas y avoir deux colonnes identiques dans une grille \n 5. Dans une ligne ou une colonne, il ne peut y avoir plus de deux 0 ou deux 1 à \n la suite (on ne peut pas avoir trois 0 de suite ou trois 1 de suite) \n\n Lors du clic sur le bouton verifier : \n vous aurez une indication sur la validité des derniers coups depuis le dernier clic \n\n Lors du clic sur le bouton Indice : \n Une des cases vide sera revélée \n\n Lorsque vous avez terminé une partie : \n Vous pouvez choisir de rejouer ou quitter le jeu \n\n Vous perdez une vie a chaque verification incorrect \n Si vous n avez plus de vie la partie est terminer, vous pouvez rejouer \n\n En difficulté Facile vous avez 5 vies \n En difficulté Difficile vous avez 3 vies \n Peu importe la taille de la grille, le nombre de vie ne change pas \n\n Si vous completez toute la grille :\n Vous saurez si elle est correcte, valide ou non \n\n') 
        self.labeltext.setStyleSheet("font-size: 20pt; font-family: sans-serif;")
        self.labeltext.setAlignment(QtCore.Qt.AlignCenter)
        self.layout.addWidget(self.labeltext,1,0)
        
        
        self.widget = QWidget()
        self.widget.setLayout(self.layout)
        self.setCentralWidget(self.widget) 
        self.depart=0
        
    def onQui(self):
        self.layout=QGridLayout()
        self.labelequipe = QLabel(" L'equipe :") 
        self.labelequipe.setStyleSheet("font-size: 50pt; font-family: sans-serif;color : rgb(255,0,255);")
        self.labelequipe.setAlignment(QtCore.Qt.AlignCenter)
        self.layout.addWidget(self.labelequipe,0,0,1,3)
        self.labelT = QLabel(" Nous sommes 3 etudiantes de la majestueuse école ESME sudria,\n nous avons realisé et crée ce projet avec passion et talent ! \n Nous esperons que le resultat vous plaira") 
        self.labelT.setStyleSheet("font-size: 20pt; font-family: sans-serif;")
        self.labelT.setAlignment(QtCore.Qt.AlignCenter)
        self.layout.addWidget(self.labelT,1,0,1,3)
        
        nous = [  'BO \n Organisatrice et designeur','LEAN \n Realisatrice et perfectionniste', 'SILYA \n Conceptrice et coordinatrice ']
        #presentation de nous
        for i in range(3):
            self.labelphoto = QLabel(' iug') 
            self.pixmap = QPixmap('photos/visage'+str(i)+'.png')
            self.labelphoto.setPixmap(self.pixmap) 
            self.labelphoto.setAlignment(QtCore.Qt.AlignCenter)
            self.layout.addWidget(self.labelphoto,2,i)
            
            self.labeltext = QLabel(nous[i]) 
            self.labeltext.setAlignment(QtCore.Qt.AlignCenter)
            self.labeltext.setStyleSheet("font-size: 20pt; font-family: sans-serif;")
            self.layout.addWidget(self.labeltext,3,i)
        
        self.widget = QWidget()
        self.widget.setLayout(self.layout)
        self.setCentralWidget(self.widget) 
        self.depart=0
   
    #fonction de verification 2: verifie la validité d'un coup 
    def onVerif (self):
        var=1
        listTemp=[]
        listFinal=[]

        for i in range(self.taille):
            for j in range(self.taille):
                var+=1
                a=self.GrilleJeu[(i,j)].text()   # creation d'une grille en remplacent tout les '  ' (qui ne sont pas 1,0) par un nombre (pour eviter que la fonction pense qu'il y en ai 3 d'affillé ect)
                if a ==' ':
                    a=var
                listTemp+=[a]
            listFinal += [listTemp]
            listTemp = []
                
        
        listFinal = np.array(listFinal)
        
        texteval = ''
        
        b=self.verif(listFinal)      #appel de la 2e fonction de verification qui sert aussi a generer une grille 
        
        #verifi si le coup est correct
        comp=0
        for i in range(self.taille):
            for j in range(self.taille):
                if str(listFinal[i,j]) in ['1','0'] :
                    if str(self.GrilleSol[i,j]) != str(self.GrilleJeu[(i,j)].text()):
                        comp+=1
                        
        #si le coup est valide + correct           
        if b == True:
            if comp >=1 :
                texteval = 'Coup valide mais incorrect.'
            else :
                texteval='Coup valide et correct.'
        #si le coup est invalide et donc incorrect
        elif b != True :
            texteval = 'Coup invalide :'+str(b)
            self.nmbcoeur-=1
            self.pixmap = QPixmap('coeur'+str(self.nmbcoeur)+'.png') 
            self.labelvie.setPixmap(self.pixmap) 
        
        
        self.textVerif.setText(str(texteval))
        
        
    #verification d'echec par rapoort aux vies
        if self.nmbcoeur ==0 :
          self.box = QMessageBox(QMessageBox.Question,"ECHEC","Vous avez perdu... ! \n Voulez vous rejouer ?", QMessageBox.Yes| QMessageBox.No)
          if self.box.exec() == QMessageBox.Yes:
              self.OnGrille44()
          elif self.box.exec() == QMessageBox.No :
              self.close()     
        
    #création d'une grille selon la taille
    def creationSol (self,taille):
        liste = []
        depart = []
        for i in range(int(taille/2)):     #creation d'une liste composée du meme nombre de 1 que de 0 (base du melange)
            depart+=['1']
            depart+=['0']
    
        while self.verif(liste) != True :   # verification que la grille est correcte selon les regles  (boucle while pas terrible et entraine un bug de chargement lors d'une grille 8x8)
            liste = []
            for i in range(taille) :        #si elle ne l'est pas : creation d'une nouvelle grille a partir de la base
                liste += [random.sample(depart, len(depart ))]
            liste = np.array(liste)  
        print(liste)
        return liste
    
    #fonction de verification 2: validation d'une grille. appellée deux fois : lors de la creation et lors de la verification d'un coup
    #elle return le probleme si il en a et true si il n'y en a pas
    def verif(self,liste):
    
        if liste == [] :
            return 'liste vide'
        else : 
            var = []
            for i in liste.T:
                #verif pas de colone identique
                if list(i) in var :
                    return 'Deux colonnes identiques'
                var+=[list(i)]
    
                #verif pas 3 d'affillé en colone
                val0 =- 1
                val1 = -1
                val2 = -1
                for a in list(i):
                    val0 , val1, val2  = a ,val0 ,val1
                    if val1==val2==val0 :
                        return " Trois "+str(val0)+" d'affilée"
                
                if  list(i).count('1')>self.taille/2:
                        return "Trop de 1 dans une colonne"   #verif autant de 1 que de 0 dans une colone
                if  list(i).count('0')>self.taille/2:
                        return "Trop de 0 dans une colonne"
             
                    
            var = []   
            for i in liste:
                
                #verif ligne pas les memes
                val = list(i)
                if val in var :
                    return 'deux lignes identiques'
                var+=[val]
                
                #verif pas 3 daff en ligne
                val0 =- 1
                val1 = -1
                val2 = -1
                for a in val:
                    val0 , val1, val2  = a ,val0 ,val1
                    if val1==val2==val0 :
                        return " Trois "+str(val0)+" d'affilée"
                    
                
                if  val.count('1')>self.taille/2:
                        return "Trop de 1 dans une ligne" #verif autant de 1 que de 0 dans une ligne 
                if  val.count('0')>self.taille/2:
                        return "Trop de 0 dans une ligne"
                    
            return True 
        
    #fonction qui crée un masque 
    def creationMasque(self ,taille):
        nombreDevoile = int(taille**1.5)  # proportionnel a la taille
        masque =[]
        for i in range(nombreDevoile):
            masque+=[1]
        for i in range(taille*taille-nombreDevoile):
            masque+=[-1]
        masque = random.sample(masque, len(masque))
        return masque

    #verification en cas de victoire -> liste finie  # note a moi meme : je devrais l'appeler que dans la liste est pleine
    def verificationWin2(self):
        etat = True
        
        grilleJ = list(self.GrilleSol)
        a=-1
        
        for i in range(self.taille):
            for j in range(self.taille):
                a+=1
                if str(grilleJ[i][j]) != str(self.GrilleJeu[(i,j)].text()):
                    etat = False
                    
        if etat == True :
            self.box = QMessageBox(QMessageBox.Question,"BRAVO","Vous avez gagné ! \n Voulez vous rejouer ?", QMessageBox.Yes| QMessageBox.No)
            if self.box.exec() == QMessageBox.Yes:
                self.OnGrille44()
            elif self.box.exec() == QMessageBox.No :
                self.close()
    def verificationWin(self):
       etat = True
       
       grilleJ = list(self.GrilleSol)
       a=-1
       b = False
       for i in range(self.taille):
           for j in range(self.taille):
               a+=1
               if str(grilleJ[i][j]) != str(self.GrilleJeu[(i,j)].text()):
                   etat = False
               
               if  str(self.GrilleJeu[(i,j)].text()) == ' ':
                   
                   b = True
                   
                   
       if etat == True :
           self.box = QMessageBox(QMessageBox.Question,"BRAVO","Vous avez gagné ! \n Voulez vous rejouer ?", QMessageBox.Yes| QMessageBox.No)
           if self.box.exec() == QMessageBox.Yes:
               self.OnGrille44()
           elif self.box.exec() == QMessageBox.No :
               self.close()           
       elif b == False :
            
            listTemp=[]
            listFinal=[]
    
            for i in range(self.taille):
                for j in range(self.taille):
                    
                    a=self.GrilleJeu[(i,j)].text()   # creation d'une grille en remplacent tout les '  ' (qui ne sont pas 1,0) par un nombre (pour eviter que la fonction pense qu'il y en ai 3 d'affillé ect)
                    listTemp+=[a]
                listFinal += [listTemp]
                listTemp = []
                    
            
            listFinal = np.array(listFinal)
        
            validite = self.verif(np.array(listFinal))
            if validite != True :
                text= 'incrorecte et invalide'
            else :
                text= 'incrorecte mais valide'
            
            self.box = QMessageBox(QMessageBox.Question,"BRAVO","Vous avez Terminer !\n mais la grille est "+text+" \n Voulez vous rejouer ?", QMessageBox.Yes| QMessageBox.No)
            if self.box.exec() == QMessageBox.Yes:
                self.OnGrille44()
            elif self.box.exec() == QMessageBox.No :
                self.close() 
             
             
             


app = QCoreApplication.instance() 
if app is None: 
    app = QApplication(sys.argv) 

window = Fenetre() 
window.show() 
app.exec_()