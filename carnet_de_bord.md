# Carnet de bord

## 4/06/18
 
Nous avons mis à jour le diagramme de classe de classe :
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/uml.png )

Nous avons avancé le prototype. Nous avons incrusté le gameplay sur une image de fond. Nous avons rajouté des jauges de peintures (non fonctionnelles), des items permettants d'accelerer notre personnage, de geler l'adversaire.

Nous avons le diagramme de classe de classe suivant :
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/6.png )


## 1/06/18

### Prototypage
* Affichage d'une fenetre avec un rond rouge fixe, et un rond bleu se déplaçant arbitrairement dans une direction
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/1.png )
* Affichage d'une carte quadrillée, on peut controler le déplacement du rond bleu.
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/2.png)
* Gestion de la collision avec le bord de la carte
* Gestion des collisions avec l'adversaire, avec les obstacles (placé aléatoirement),gestion de la peinture,  affichage du score dans le terminal
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/3.png )
* Affichage de sprite
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/4.png )
* Creation d'une image de fond 
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/background.png )
* Creation d'une image d'accueil
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/accueil.png)


### Diagramme de classe

Nous avons le diagramme de classe de classe suivant :
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/5.png )



___

## 31/05/18

### TODO
* Création d'un prototype d'image de fond
* Quadrillage de la map : Jade et Marceau
* Gestion de collision : Guillaume et Leo
* Structuration du projet : Loïc et Elisa

### Penser à
* Avoir une configuration commune sur Eclipse
* Décider qui est responsable du merge sur master (respect du workflow)

___

## 30/05/18

### Objectifs pour la présentation
* Faire un planning  
* Faire un diapo
* les convaincres qu'on est capable de réaliser ce qu'on leur propose
* Faire les prototypes

### Organisation MVC

* Modele : contient les structures de données
* Vue : Interface graphique. C'est ce que l'on choisit d'afficher à l'écran
* Controleur : Traite les actions de l'utilisateur, modifie le modele et la vue en conséquence

Il faut utiliser cette architecture, pour bien découper les différentes tâches.

### Prototypage
(tester les parties difficiles)

* Affichage de plusieurs fenetre (rafraichissement de l'image, gestion des différents éléments graphique)
* Gestion des collisions
* Gestion d'evenement (appui clavier, souris)
* Gestion de l'arbre du parser, pour le rendre utilisable ?
* Lecture de fichier contenant les automates ?

### Attribution des roles :
* Jade : Interface graphique
* Guillaume : Automates
* Léo : Polyvalent
* Elisa : Automates
* Loïc : Automates
* Marceau : Interface graphique

___

## 29/05/18

### Définition du Game design
* Brainstorming sur les caractéristiques de notre jeu

### Attribution des roles :
* Jade : Interface graphique
* Guillaume : Automate
* Léo : Polyvalent
* Elisa : Automates
* Loïc : 
* Marceau : 

### Prototypage
(tester les parties difficiles)

* Affichage de plusieurs fenetre (rafraichissement de l'image)
* Gestion de l'arbre du parser, pour le rendre utilisable
* Gestion d'evenement (appui clavier, souris)
* Lecture de fichier contenant les automates

