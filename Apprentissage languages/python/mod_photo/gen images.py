# -*- coding: utf-8 -*-
"""
Created on Sun Apr 24 16:06:38 2022

@author: Moi
"""

import sys
from PyQt5.QtWidgets import QFileDialog, QWidget,QMessageBox, QToolBar, QPushButton, QGridLayout, QLabel, QApplication, QMainWindow, QAction, QStatusBar, QSlider
from PyQt5.QtCore import QCoreApplication , Qt ,QSize
from PyQt5.QtGui import QIcon, QKeySequence
from PyQt5.QtGui import QPixmap 
from PIL import Image
 

class Fenetre(QMainWindow): 
    def __init__(self): 
        
        super().__init__() 
        
    #initalisation du forma de la fentre
        self.setWindowTitle("Générateur d'images") 
        self.resize(700,300)
        #self.setFixedSize(700,700) on sait qu'on aurait du mettre 700x300 mais soit la fenetre prenait la taille du label, soit si on fixait a 300 c'etait trop petit
        self.layout=QGridLayout()
        self.setStyleSheet('background-color : white')
        
    #creation des actions
        # réinitialiser
        self.reinit = QAction(QIcon("robot.png"), "Réinitialiser", self)
        self.reinit.setStatusTip("Réinitialiser le projet en cours")
        self.reinit.triggered.connect(self.onFilterR)
        self.reinit.setShortcut(QKeySequence('Ctrl+D'))
		
        # ouvrir
        self.ouvrir = QAction(QIcon("blue-folder-open-feed.png"), "Ouvrir", self)
        self.ouvrir.setStatusTip("Ouvrir un fichier")
        self.ouvrir.triggered.connect(self.onOpen)
        self.ouvrir.setShortcut(QKeySequence('Ctrl+O'))
        
        # sauvegarder
        self.save = QAction(QIcon("disk.png"),"Sauvegarder", self)
        self.save.setStatusTip("Sauvegarder le fichier en cours")
        self.save.triggered.connect(self.onSave)
        self.save.setShortcut('Ctrl+S')
        
        # Action des filtres
        self.rouge = QAction(QIcon("color-adjustment-red.png"), "Filtre rouge")
        self.rouge.setStatusTip("Appliquer un filtre rouge")
        self.rouge.triggered.connect(self.onFilterR)
        self.rouge.setShortcut(QKeySequence('Ctrl+R'))
		
        self.vert = QAction(QIcon("color-adjustment-green.png"), "Filtre vert")
        self.vert.setStatusTip("Appliquer un filtre vert")
        self.vert.triggered.connect(self.onFilterR)
        self.vert.setShortcut(QKeySequence('Ctrl+V'))
        
        self.bleu = QAction(QIcon("color-adjustment.png"), "Filtre bleu")
        self.bleu.setStatusTip("Appliquer un filtre bleu")
        self.bleu.triggered.connect(self.onFilterR)
        self.bleu.setShortcut(QKeySequence('Ctrl+B'))
        
        self.gris = QAction(QIcon("cushion-gray.png"), "Filtre gris")
        self.gris.setStatusTip("Appliquer un filtre gris")
        self.gris.triggered.connect(self.onFilterR)
        self.gris.setShortcut(QKeySequence('Ctrl+G'))
        
        self.neg = QAction(QIcon("ui-color-picker-switch.png"), "Filtre négatif")
        self.neg.setStatusTip("Appliquer un filtre négatif")
        self.neg.triggered.connect(self.onFilterR)
        self.neg.setShortcut(QKeySequence('Ctrl+N'))
        
        #quitter
        self.quit=QAction(QIcon("door-open-out.png"), "Quitter")
        self.quit.setStatusTip("Quitter le générateur d'images")
        self.quit.triggered.connect(self.onQuit)
        self.quit.setShortcut(QKeySequence('Ctrl+Q'))
        
        #division par 4
        self.photomaton=QAction(QIcon("border-all.png"), "Par 4")
        self.photomaton.setStatusTip("Passer en affichage Photomaton par 4")
        self.photomaton.triggered.connect(self.onPhoto4)
        self.photomaton.setShortcut('Ctrl+P')
        
        #generateur de couleur
        self.jaugeaction=QAction(QIcon("color.png"), "Choix du filtre")
        self.jaugeaction.setStatusTip("Definir le filtre a l'aide de la jauge de couleur")
        self.jaugeaction.triggered.connect(self.onJauge)
        self.jaugeaction.setShortcut('Ctrl+C')
        
        self.setStatusBar(QStatusBar(self))
        
    # Ajout et connection des actions au menu
        self.menuImage = self.menuBar().addMenu("Image")
        self.menuImage.addAction(self.reinit)
        self.menuImage.addAction(self.ouvrir)
        self.menuImage.addAction(self.save)
        
        self.menuFiltre = self.menuBar().addMenu("Filtre")        
        self.menuFiltre.addAction(self.rouge)        
        self.menuFiltre.addAction(self.vert)        
        self.menuFiltre.addAction(self.bleu)
        self.menuFiltre.addSeparator()
        self.menuFiltre.addAction(self.neg)
        self.menuFiltre.addAction(self.gris)
        
        self.menuPhotomaton = self.menuBar().addMenu("Photomaton")
        self.menuPhotomaton.addAction(self.photomaton)
        
        self.menuCouleur = self.menuBar().addMenu("Couleur")
        self.menuCouleur.addAction(self.jaugeaction)
        
        self.menuQuitter = self.menuBar().addMenu("Quitter")
        self.menuQuitter.addAction(self.quit)
        
    # Ajout et creation de la bar d'outil
        self.toolbar = QToolBar("Barre d'outils")
        self.toolbar.setIconSize(QSize(16,16))
        self.toolbar.setFixedHeight(32)
        
        self.addToolBar(self.toolbar)
        self.toolbar.addAction(self.quit)
        self.toolbar.addAction(self.ouvrir)
        self.toolbar.addAction(self.save)
        self.toolbar.addAction(self.rouge)
        self.toolbar.addAction(self.bleu)
        self.toolbar.addAction(self.vert)
        self.toolbar.addAction(self.neg)
        self.toolbar.addAction(self.gris)
        self.toolbar.addAction(self.photomaton)
        self.toolbar.addAction(self.jaugeaction)
        self.toolbar.addAction(self.reinit)
        
        #def/ouvertur image
        self.imageOuverte = 'singe.jpg'
        self.imageSauv = self.imageOuverte
        
        try:
          self.img = Image.open(self.imageOuverte)
        except IOError:
          print("Erreur sur ouverture du fichier " + self.imageOuverte)
          sys.exit(1)
          
        self.layout=QGridLayout()
        
        #Image dans label
        self.label = QLabel(self) 
        self.pixmap = QPixmap(self.imageOuverte) 
        self.label.setPixmap(self.pixmap) 
        self.layout.addWidget(self.label,0,0,3,1)
        
        #fenetre a la bonne taille
        #self.setGeometry(0, 0, self.pixmap.width(), self.pixmap.height())
       
        #Dimensions en bas de page²
        largeur = str(self.pixmap.width())
        hauteur = str(self.pixmap.height())
        self.label2 = QLabel((largeur + " X " + hauteur))
        self.layout.addWidget(self.label2,7,0)
        
        self.widget = QWidget()
        self.widget.setLayout(self.layout)
        self.setCentralWidget(self.widget) 
        
    # fonction de modification de l'image selon le filtre et le 'type' de filtre 
    def Filtre (self,img,filtre):
        img2=img.copy()
        taille=img2.size
        
        for x in range (taille[0]):
            for y in range (taille[1]):
                r,v,b=img2.getpixel((x,y))
                val= int(r*299/1000+ v*587/1000+ b*114/1000)
                
                # Quand le parametre Filtre viens de la jauge de couleur
                if type(filtre) is tuple :
                    valeur=(int(filtre[0]*val),int(filtre[1]*val),int(filtre[2]*val))
                    
                # Quand le parametre Filtre viens des fonction filtre
                elif type(filtre) is str :
                    dicoFiltre={'Filtre rouge':(val,0,0), 'Filtre vert':(0,val,0), 'Filtre bleu':(0,0,val), 'Filtre gris':(val,val,val), 'Filtre négatif':(255-r,255-v,255-b), 'Réinitialiser':(r,v,b)}
                    valeur=dicoFiltre[filtre]
                    
                img2.putpixel((x,y),valeur)
        img2.save('test.jpg')

    #fonction resset 1, avant reset2 quand elle est appelé par le menu -> reset general = retour a derniere photo ouverte
    def onReset (self):
        NomImage = self.imageOuverte
        self.onReset2(NomImage)
        
    #fonction reset 2, appelé a chaque debut de fonction pour reset le layout (surtout celui du generateur de couleur) (depend de l'argument)
    def onReset2(self, NomImage):
        
        try:
          self.img = Image.open(NomImage)
        except IOError:
          print("Erreur sur ouverture du fichier " + NomImage)
          sys.exit(1)
        self.layout=QGridLayout()
  
        #image dans label
        self.label = QLabel(self) 
        self.pixmap = QPixmap(NomImage) 
        self.label.setPixmap(self.pixmap) 
        self.label.resize(self.pixmap.width(), 
                          self.pixmap.height())
        self.layout.addWidget(self.label,0,0,6,1)
        
        #dimensions en bas de page
        largeur = str(self.pixmap.width())
        hauteur = str(self.pixmap.height())
        self.label2 = QLabel((largeur + " X " + hauteur))
        self.layout.addWidget(self.label2,7,0)
        
        #fentere a la bonne taille
        self.setGeometry(0, 0, self.pixmap.width(), self.pixmap.height())
        
        self.widget = QWidget()
        self.widget.setLayout(self.layout)
        self.setCentralWidget(self.widget)  
        
    #fonction fermant la fernetre
    def onQuit(self):
        self.box = QMessageBox(QMessageBox.Question,"Quitter le générateur","Voulez-vous quitter le générateur d'images ?", QMessageBox.Yes| QMessageBox.No)
        if self.box.exec() == QMessageBox.Yes:
            self.close()
        elif self.box.exec() == QMessageBox.No :
            pass
        
    #fonction pour ouvrir une image (seulement en jpg)
    def onOpen(self):
        self.boite = QFileDialog() 
        self.chemin = self.boite.getOpenFileName(parent = None, caption = 'Ouvrir fichier', filter = 'Images ( *.jpg )',initialFilter='Images JPEG (*.jpg)')
        if self.chemin is tuple: # pour eviter un message d'erreur dans le terminal si l'utilisateur clique sur cancel
            self.imageOuverte = self.chemin[0]
            #layout avec reset
            self.onReset2(self.imageOuverte)
            self.imageSauv = self.imageOuverte
    
    #fonction pour sauvegarder la photo avec les modification
    def onSave(self):
        self.fileName = QFileDialog.getSaveFileName(self, 'Save File', '', '*.jpg')
        if self.fileName is tuple: # pour eviter un message d'erreur dans le terminal si l'utilisateur clique sur annuler
            self.image=Image.open(self.imageOuverte)
            self.image.save(self.fileName[0])
        
    #fonction qui divise par 4 la derniere image modifier (avec filtre) 
    def onPhoto4(self):
        #layout avec reset
        self.onReset2(self.imageSauv)
        img2= self.img.copy()
        img=self.img
        taille=img.size
        
        ax=-1
        ay=-1
        for y in range (taille[1]):
            if y%2==0:
                ay+=1
            for x in range (taille[0]):
                c=img.getpixel((x,y))
                if x%2==0:
                    ax+=1
                if ax==(taille[0]/2):
                    ax=0
                if x%2==0 and y%2==0:
                    loc=(0+ax,0+ay)
                if x%2==0 and y%2==1:
                    loc=(0+ax,(taille[1]/2)+ay)
                if x%2==1 and y%2==0:
                    loc=((taille[0]/2)+ax,0+ay)
                if x%2==1 and y%2==1:
                    loc=((taille[0]/2)+ax,(taille[1]/2)+ay)
                loc=(int(loc[0]),int(loc[1]))
                img2.putpixel(loc,c)
        img2.save('test.jpg')
        
        self.img=img2    
        self.imageSauv = 'test.jpg'
        self.pixmap = QPixmap('test.jpg') 
        self.label.setPixmap(self.pixmap)
        
    #premiere fonction qui appel Filtre avec un nom str en parametre pour le dico
    def onFilterR(self):
        #reset du layout
        self.onReset2(self.imageOuverte)
        
        self.Filtre(self.img,self.sender().text())
        self.imageSauv = 'test.jpg'        
        self.pixmap = QPixmap('test.jpg') 
        self.label.setPixmap(self.pixmap)
        
    #fonction qui affiche le generateur et le lie au deuxieme appel de la fonction Filtre 
    def onJauge (self):
        NomImage = self.imageSauv 
        self.layout=QGridLayout()
        self.setWindowTitle("Image") 
        
        #image dans label
        self.label = QLabel(self) 
        self.pixmap = QPixmap(NomImage) 
        self.pixmap = self.pixmap.scaled(int(self.pixmap.width()*2/3), int(self.pixmap.height()*2/3), Qt.KeepAspectRatio, Qt.FastTransformation)
        self.label.setPixmap(self.pixmap) 
        self.label.resize(self.pixmap.width(), 
                          self.pixmap.height())
        self.layout.addWidget(self.label,0,0)
        
        #fentere a la bonne taille
        self.setGeometry(0, 0, self.pixmap.width(), self.pixmap.height())
        
        #bouton sur le quel on appuie pour valider la couleur choisi
        self.Bfiltre=QPushButton('get')
        self.Bfiltre.clicked.connect(self.changementImage2)
        self.layout.addWidget(self.Bfiltre,0,1)
        
        #label qui change de couleur
        self.label2 = QLabel('Ma couleur', self) 
        self.label2.setAlignment(Qt.AlignCenter)
        self.label2.setStyleSheet( "background-color : white;")
        self.layout.addWidget(self.label2,4,0)
        
    #slider et leur label
        self.slider = QSlider(Qt.Horizontal)
        self.slider.setTickPosition(QSlider.TicksBelow)        
        self.slider.setMinimum(0) 
        self.slider.setMaximum(255) 
        self.slider.sliderMoved.connect(self.slider_position) 
        
        self.layout.addWidget(self.slider,1,0)
        self.labrouge= QLabel('Rouge')
        self.labrouge.setStyleSheet('color : red;')
        self.layout.addWidget(self.labrouge,1,1)
        
        self.slider2 = QSlider(Qt.Horizontal)
        self.slider2.setTickPosition(QSlider.TicksBelow)
        self.slider2.setMinimum(0) 
        self.slider2.setMaximum(255) 
        self.slider2.sliderMoved.connect(self.slider_position)          
        self.layout.addWidget(self.slider2,2,0)
        
        self.labvert= QLabel('Vert')
        self.labvert.setStyleSheet('color : green;')
        self.layout.addWidget(self.labvert,2,1)
        
        self.slider3 = QSlider(Qt.Horizontal)
        self.slider3.setTickPosition(QSlider.TicksBelow)
        self.slider3.setTickInterval(10)
        self.slider3.setMinimum(0) 
        self.slider3.setMaximum(255) 
        self.slider3.sliderMoved.connect(self.slider_position)
        self.layout.addWidget(self.slider3,3,0)
        
        self.labbleu= QLabel('Bleu')
        self.labbleu.setStyleSheet('color : blue;')
        self.layout.addWidget(self.labbleu,3,1)
        
        self.widget = QWidget()
        self.widget.setLayout(self.layout)
        self.setCentralWidget(self.widget) 
    
    #fonction du generateur de couleur 
    def slider_position(self): 
        #modification de la couleur du label
        self.label2.setText('rgb('+str(self.slider.value())+','+str(self.slider2.value())+','+str(self.slider3.value())+')')
        self.label2.setStyleSheet('background-color: rgb('+str(self.slider.value())+','+str(self.slider2.value())+','+str(self.slider3.value())+')')
        
        #modificatoin du nom assoscié aux couleurs en leur valeur
        self.labrouge.setText(str(self.slider.value()))
        self.labvert.setText(str(self.slider2.value()))
        self.labbleu.setText(str(self.slider3.value()))
            
    #deuxieme appel de Filtre avec un tuple en parametre
    def changementImage2(self):
        img= self.img
        rouge = self.slider.value()
        vert = self.slider2.value()
        bleu = self.slider3.value()
        filtre = ((rouge/255),(vert/255),(bleu/255))
        self.Filtre(img,filtre)
        self.imageSauv = 'test.jpg' 
        self.pixmap = QPixmap('test.jpg') 
        self.pixmap = self.pixmap.scaled(int(self.pixmap.width()*2/3), int(self.pixmap.height()*2/3), Qt.KeepAspectRatio, Qt.FastTransformation)
        self.label.setPixmap(self.pixmap)



app = QCoreApplication.instance() 
if app is None: 
    app = QApplication(sys.argv) 

window = Fenetre() 
window.show() 
app.exec_()
