# Travail de reconnaissance graphique avec OpenCV

# Introduction à l'utilisation du logiciel OpenCV

OpenCV, acronyme de Open Source Computer Vision Library, est une bibliothèque de traitement d'images et de vidéos en temps réel. Elle a été créée en 1999 par Intel et est maintenant maintenue par Willow Garage et Itseez. OpenCV est une librairie gratuite et open-source sous licence BSD. Elle est compatible avec plusieurs langages de programmation tels que C++, Python, Java et MATLAB.

OpenCV permet de manipuler des images et des vidéos en temps réel, de réaliser des tâches telles que la reconnaissance faciale, la détection d'objets, la reconnaissance de caractères, la segmentation d'image, le suivi d'objets, etc. De plus, OpenCV offre des outils pour la création d'interfaces graphiques utilisateur (GUI) pour l'affichage des résultats de traitement.

En utilisant OpenCV, les développeurs peuvent créer des applications de vision par ordinateur pour diverses applications telles que la surveillance, la reconnaissance de mouvements, la vision industrielle, la robotique, la réalité augmentée, etc. OpenCV est une véritable référence dans le domaine de la vision par ordinateur et est utilisé par de nombreuses organisations dans le monde entier.

---

## Partir sur de bonnes bases :

Nous nous sommes basés sur cette vidéo qui nous a beaucoup aidés. Elle montre comment utiliser OpenCV pour créer un jeu vidéo. C'est intéressant de voir d'autres applications que la détection de visages ou de formes. Nous avons donc dû adapter légèrement notre code à notre projet, mais la vidéo est très claire et permet de s'y retrouver facilement, même pour des débutants complets.

[Vidéo de référence pour la familiarisation et la construction des premiers modèles de reconnaissance](https://www.youtube.com/watch?v=XrCAvs9AePM)

Vidéo de référence pour la familiarisation et la construction des premiers modèles de reconnaissance

---

## Détection d'image avec OpenCV

OpenCV offre de nombreuses fonctions pour la détection d'objets dans des images. Les fonctionnalités les plus couramment utilisées sont la détection de visages et la détection de formes.

### Détection de visages

La détection de visages est une tâche courante en vision par ordinateur. OpenCV fournit plusieurs algorithmes pour la détection de visages, notamment l'algorithme de Viola-Jones. Cet algorithme utilise un classificateur en cascade pour détecter les visages dans une image. Les classificateurs en cascade sont construits à partir d'un grand nombre d'images positives et négatives. Les images positives contiennent des exemples de l'objet que l'on souhaite détecter (dans ce cas, des visages), tandis que les images négatives ne contiennent pas cet objet. L'algorithme de Viola-Jones utilise ensuite des fenêtres glissantes pour scanner l'image et détecter les visages dans celle-ci.

OpenCV fournit également des fonctions pour l'annotation de visages détectés. Ces fonctions permettent de dessiner des rectangles autour des visages détectés dans une image.

### Détection de formes

La détection de formes est une tâche plus générale que la détection de visages. OpenCV fournit plusieurs algorithmes pour la détection de formes, notamment l'algorithme de Hough et l'algorithme de GrabCut.

L'algorithme de Hough est utilisé pour la détection de lignes et de cercles dans une image. L'algorithme de GrabCut est utilisé pour la segmentation d'image. Il permet de séparer une image en plusieurs régions en utilisant des informations de couleur et de texture.

### Training en cascade

OpenCV permet également de créer des classificateurs en cascade personnalisés pour la détection d'objets. Pour cela, il est nécessaire de créer des fichiers XML contenant des informations sur les images positives et négatives, ainsi que sur les caractéristiques de l'objet que l'on souhaite détecter. Ces fichiers XML peuvent ensuite être utilisés pour entraîner un classificateur en cascade.

### Création d'échantillons

OpenCV fournit également des outils pour la création d'échantillons pour la détection d'objets. Ces outils permettent de créer des images positives et négatives pour l'entraînement de classificateurs en cascade. Les images positives contiennent des exemples de l'objet que l'on souhaite détecter, tandis que les images négatives ne contiennent pas cet objet.

En utilisant ces outils, il est possible de créer des classificateurs en cascade personnalisés pour la détection d'objets dans des images. Ces classificateurs peuvent être utilisés pour une grande variété d'applications en vision par ordinateur.

---

# Péripéties

Lors de la création de notre programme de reconnaissance graphique pour la machine à plier les vêtements, nous avons rencontré plusieurs difficultés techniques. Tout d'abord, la détection de vêtements était particulièrement complexe car il fallait prendre en compte la grande variété de textures et de couleurs des vêtements. Nous avons dû tester diverses méthodes de segmentation d'image pour trouver celle qui fonctionnait le mieux dans notre contexte.

De plus, nous avons également dû faire face à des problèmes de performance lors de l'exécution en temps réel du programme. La détection de vêtements nécessite une grande quantité de puissance de calcul et nous avons dû optimiser notre code à plusieurs reprises afin d'améliorer les temps de réponse du système.

Enfin, nous avons également rencontré des problèmes d'usure de la machine qui ont entraîné des erreurs de détection. Dans ces cas, nous avons dû effectuer des réglages manuels et redémarrer le programme pour assurer une détection précise des vêtements.

Nous avons également rencontré des problèmes de calibrage de la caméra, qui affectaient la qualité des images capturées. Nous avons dû ajuster les paramètres de la caméra pour améliorer la netteté de l'image et ainsi permettre une détection plus précise des vêtements.

De plus, nous avons remarqué que la détection de certains types de vêtements posait plus de difficultés que d'autres. Par exemple, les vêtements de couleur claire étaient plus difficiles à détecter que les vêtements foncés, même avec les réglages de caméra appropriés. 

Enfin, nous avons fait face à des défis liés à la complexité de la machine elle-même. La machine à plier les vêtements que nous avons utilisée était particulièrement sophistiquée, avec de nombreux composants électroniques et mécaniques. Nous avons dû travailler en étroite collaboration avec les ingénieurs mécaniques pour assurer une coordination parfaite entre le système de détection et le reste de la machine.

Malgré ces difficultés, nous sommes fiers d'avoir réussi à créer un programme de reconnaissance graphique performant pour la machine à plier les vêtements. Nous sommes convaincus que notre travail permettra d'améliorer significativement l'efficacité de la machine et facilitera la vie de nombreux utilisateurs.