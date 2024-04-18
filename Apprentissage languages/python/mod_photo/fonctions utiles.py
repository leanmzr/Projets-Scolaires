#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun May  8 15:06:03 2022

@author: leanmazier
"""


#pour mettre une image dans un label 
self.label = QLabel(self) 
self.pixmap = QPixmap(self.imageOuverte) 
self.label.setPixmap(self.pixmap) 
self.layout.addWidget(self.label,0,0)

#pour ouvrir une image :

try:
  img = Image.open(NomImage)
except IOError:
  print("Erreur sur ouverture du fichier " + NomImage)
  sys.exit(1)



def FiltreRouge (img):
    img2=img.copy()
    taille=img2.size
    
    for x in range (taille[0]):
        for y in range (taille[1]):
            
            rouge,vert,bleu = img2.getpixel((x,y))
            valeur = int(rouge*299/1000+ vert*587/1000+ bleu*114/1000)
            filtreR = (valeur,0,0) #  tuple pour du rouge faites la meme fonction en changeant que ca pour les autres couleurs (ex bleu =(0,0, val))
            img2.putpixel((x,y),filtreR)
            
    img2.save('sauvegarde.jpg')
    
#exemple avec autre couleur :
def FiltreVert (img):
    img2=img.copy()
    taille=img2.size
    
    for x in range (taille[0]):
        for y in range (taille[1]):
            
            rouge,vert,bleu = img2.getpixel((x,y))
            valeur = int(rouge*299/1000+ vert*587/1000+ bleu*114/1000)
            filtreV = (0,valeur,0) #  tuple pour du rouge faites la meme fonction en changeant que ca pour les autres couleurs (ex bleu =(0,0, val))
            img2.putpixel((x,y),filtreV)
            
    img2.save('sauvegarde.jpg')
    
#negatif : (255-r,255-v,255-b)
#gris (val,val,val)
    
    
#fonction pour ouvrir une image
def onOpen(self):
    self.dialogue = QFileDialog() 
    self.chemin = self.dialogue.getOpenFileName(parent = None, caption = 'Ouvrir fichier', filter = 'Images ( *.jpg )',initialFilter='Images JPEG (*.jpg)')
    self.NomImage = self.chemin[0]
    
    #faire un layout pour afficher l'image, nous on a pas fait comme ca dcp je peux pas le mettre la



#fonction pour sauvegarder la photo avec les modification
def onSave(self):
    self.fileName = QFileDialog.getSaveFileName(self, 'Save File', '', '*.jpg')
    self.Isauv=Image.open(NomImageAsauvegarder)
    self.Isauv.save(self.fileName[0])
    
        
        
        
#pour le generateur, liez ca a tous vous slider et c'est bon :
    
def slider_position(self): 
    
        self.label2.setText('rgb('+str(self.slider.value())+','+str(self.slider2.value())+','+str(self.slider3.value())+')')
        self.label2.setStyleSheet('background-color: rgb('+str(self.slider.value())+','+str(self.slider2.value())+','+str(self.slider3.value())+')')
        

        self.labelbrouge.setText(str(self.slider.value()))
        self.labelvert.setText(str(self.slider2.value()))
        self.labelbleu.setText(str(self.slider3.value()))
        
        
        
        