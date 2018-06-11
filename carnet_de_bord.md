# Carnet de bord

## 11/06/18

### Ce qui a été fait
* Affichage des sbires sur le terrain (invocable avec 4 touches pour chaque joueur), avec un nouveau sprite
* Fenetre de choix d'automates, en fonction d'un fichier
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/6.png )
* Barre de peinture fonctionnelle
* Integration d'une musique de fond, ainsi que des bruitages

### TODO
* Sprite du portail
* Gerer la fermeture de la fenetre
* Inverser la barre de progression de la peinture du 2eme joueur
* Explosion visuelle d'un mur quand on le frappe
___
## 08/06/18

### Ce qui a été fait
* Affichage + maj timer
* Affichage+ maj pourcentage (score)
* Affichage + maj bonus
* Page de fin (statistique) finalisée
* Customisation personnage

### Todo
* Faire des images de sbires, de portail
* Page de crédit
* Affichage barre de peinture


___
## 07/06/18

### Ce qui a été fait
* Résolution du bug de la taille de la fenetre aléatoire, maintenant les fenetres font toutes la meme taille
* Enchainement de plusieurs fenetres (fenetre d'accueil, fenetre des credits, fenetre des regles, fenetre de jeu)
* Création de zbire sans comportement
* Changement des sprites du joueur
* Creation d'un portail de téléportation
* Implémentation du comportement frapper, on peut maintenant : 
  * frapper le joueur adverse, il retourne donc à sa position initiale
  * frapper des murs, ils prennent 3 coups avant de disparaitre


### TODO
* Commencer à réflechir aux automates
* Inclure la fenetre de fin a la fin du jeu
* resoudre les bug mineur (peinture, clignotement de l'ecran)
* Gerer l'affichage graphique :
  * du stock de peinture
  * Des icones des sbires que l'on a ramassé
  * du score, et du temps


___
## 6/06/18

### 1ère Presentation

Notre jeu a été accepté sans trop de modification. Nous avons 3 extensions en plus :
* Faire un jeu jouable sur 2 supports (claviers, controller, ordinateurs)
* Faire une IA contre qui on pourrait jouer
* Faire des statistiques sur la partie

# TODO

* Affichage du score, temps, bonus
* Faire une personalisation des sprites jouable
* Portail de teleportation : **Marceau**
* Hit : **Elisa/Loïc**
* Statistiques : **Guillaume**
* Correction de l'affichage de base : **Jade**
* Invocation des sbires : **Léo**

___

## 5/06/18

### Avancement interface graphique

Jade continue à créer les différents éléments que l'on affichera à l'écran, aidé par Elisa.
Loïc et Léo essayent de faire une fenetre qui s'adapte a la taille de l'ecran.

### Avancement GamePlay

Marceau et Guillaume ont implémenté la gestion du stock de peinture. C'est fonctionnel, mais pas encore relié à l'interface graphique.

### Création du diapo

On a créé un diapo pour la 1ere soutenance. On y explique le fonctionnement de notre jeux, et ce qu'on voudrait y implémenter.

___
## 4/06/18
 
Nous avons mis à jour le diagramme de classe de classe :
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/uml.png )

Nous avons avancé le prototype. Nous avons incrusté le gameplay sur une image de fond. Nous avons rajouté des jauges de peintures (non fonctionnelles), des items permettants d'accelerer notre personnage, de geler l'adversaire.
![alt text](https://github.com/TheTisiboth/PLA_2018/blob/master/images/proto/6.png )

___

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

