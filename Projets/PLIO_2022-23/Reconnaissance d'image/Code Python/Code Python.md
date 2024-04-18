# Code Python

---

```python

import serial
import cv2
import numpy as np
import time

tshirt_cascade = cv2.CascadeClassifier('cascade.xml')
pantalon_cascade = cv2.CascadeClassifier('cascade_pant.xml')
ser = serial.Serial('COM3',9600)
global timer
timer =0
```

Au dessus, on voit que le programme charge deux fichiers XML dits “cascade” qui sont des fichiers résultant de l’apprentissage effectué par OpenCV avec le processus décrit dans la page(1). On configure le port de sortie vers l’Arduino en indiquant le port USB correspondant (COM3) ainsi que la vitesse de sortie des bits. Serial est la bibliothèque nous permettant de faire le lien entre le code Python de la reconnaissance graphique avec le code C de la carte Arduino.

[Travail de reconnaissance graphique avec OpenCV](Travail%20de%20reconnaissance%20graphique%20avec%20OpenCV%20e6bafbe103914af8900958b119dec170.md)

---

```python

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
```

Le code lit les images de la caméra, convertit l'image en niveaux de gris, détecte les vêtements dans l'image à l'aide des cascades, dessine un rectangle autour de chaque vêtement détecté, vérifie si le vêtement a été détecté récemment pour éviter les doublons, et envoie des données à la carte Arduino via le port série. Le code fonctionne en boucle infinie jusqu'à ce que l'utilisateur appuie sur la touche "échap". Toutes les commandes en lien avec la “mémoire” sont des commandes permettant de s’assurer d’avoir détecté correctement afin de ne pas commettre d’erreurs qui lanceraient le pliage sans réfléchir.

---

```python
def ajoutmem(memoire, aj):
    memret = [memoire[1], memoire[2], memoire[3], aj]
    return memret
```

Comme on peut le constater, toutes les fonctions sont étroitement liées et se servent l’une de l’autre pour fonctionner. Les fonctions ajoutmem, verifmem, verifcoord, link, et countdown s’enchaînent toutes les unes après les autres, prenant la sortie de la fonction précédente en entrée, etc. Les fonctions mémoires (ajoutmem et verifmem) stockent des coordonnées de carrés et vérifient des coordonnées s’enchaînant.

```python
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

def verifcoord(coord1, coord2):
    espace = []
    for i in range(10):
        espace += [coord1 + i]
        espace += [coord1 - i]
    if coord2 in espace:
        return True
    else:
        return False
```

La fonction verifcoord vérifie que les carrés se succédant soient suffisamment proches pour avoir une détection stable et appropriée.

```python
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
```

 La fonction link permet, en fonction du type de vêtement détecté, d’envoyer un signal spécifique pour le pliage du vêtement.

```python
def countdown(num_of_secs):
    
    while num_of_secs:
        timer =1
        m, s = divmod(num_of_secs, 60)
        min_sec_format = '{:02d}:{:02d}'.format(m, s)
        print(min_sec_format, end='/r')
        time.sleep(1)
        num_of_secs -= 1
    timer =0
```

 Enfin, la fonction countdown permet de “freeze” le programme et la détection d’images pendant la durée du pliage, afin de ne pas détecter autre chose qui pourrait perturber les commandes aux moteurs.

![FF4BE1C6-2512-45D7-8421-37FFC156CD00.png](Code%20Python%2060a62da372c04e0fbb3b420093632d20/8b06af6a-28df-4d8c-8ca9-77aa3d0ea0bd.png)

![633F3445-94C6-4DD6-B97C-5DF52329B270.png](Code%20Python%2060a62da372c04e0fbb3b420093632d20/90b6c4ae-d0dd-4adb-929c-03d252be5b4a.png)

![475283D5-EAF7-45DC-8D8F-266C03C1DBFB.png](Code%20Python%2060a62da372c04e0fbb3b420093632d20/475283D5-EAF7-45DC-8D8F-266C03C1DBFB.png)

![2D7D252B-3FE2-45CA-B016-401901E4CC02.png](Code%20Python%2060a62da372c04e0fbb3b420093632d20/2D7D252B-3FE2-45CA-B016-401901E4CC02.png)

![9785D7B1-6FEE-433D-AC3C-0A40556A2F78.png](Code%20Python%2060a62da372c04e0fbb3b420093632d20/9785D7B1-6FEE-433D-AC3C-0A40556A2F78.png)