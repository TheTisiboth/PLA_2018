# COLORicm

# Avancement du projet 
Pour voir l'avancement du projet, se référer au fichier carnet_de_bord.md

# Manuel de l'utilisateur 

Pour accéder au manuel utilisateur, il suffit d'ouvrir le fichier manuel_user_compressed.pdf
Il contient toutes les règles, les touches,etc, permettant de comprendre comment jouer au jeu

# Présentation 

Pour voir le PDF de notre présentation, il suffit d'ouvrir le fichier COLORicm.pdf.


## Lancement du jeu / Run the game

* Il suffit de double cliquer sur le fichier COLORicm.jar
* You just have to dobble click on the file COLORIcm.jar

### Objectif :
* colorier le plus vite possible un plateau (peinture limitée)
* multijoueurs : 1 contre 1 (2 joueurs)
* Lorsque l'on se déplace, on colorie les cases sur lesquelles on est passé
* On peut ramasser des items nous permettant de : ralentir, accelerer, geler...
* 1 point de vie, et lorsque l'on prend un dégat, on retourne a notre base
* Ramassage et activation d'item automatique
* Vitesse de déplacement des personnages peut etre modifiée
* Les 2 joueurs peuvent frapper
* Choix de classe au début du jeu, qui influence sur la chance d'obtenir certains types de sbire


###  Objets :
* Item qui recharge la peinture
* Item qui retire les couleurs des autres dans un certain rayon
* Item qui explose en projetant la couleur de celui qui l'a récupéré
* Obstacles sur le plateau à détruire pour pouvoir mettre sa couleur
* 4 types de sbires ramassables et stockables (on peut en stocker 4 différents)
* Speed : accelere le déplacement

###  Type de sbire :
* Peintre : meurt au bout de X cases coloriées
* Kamikaze : Explose au bout de X temps, en faisant des dégats et en coloriant les cases adjacentes
* Filou : Colorie vraiment beaucoup de cases OU en efface certaines
* Nettoyeur : efface la peinture de l'adversaire

### Fin du jeu : 
* Au bout de X temps, celui qui a colorié la plus grande partie de la map à gagné

### Variante :
* plusieurs couleurs à choisir au début du jeu
* Passage secret qui permet d’accéder à un autre endroit de la carte

### Personnage :
* Choix sexe
* Choix couleur de cheveux
* Choix vêtements
* Choix du nom

###  Actions :
* HIT : Frapper
* MOVE : se deplace sur une case adjacente (pas de diagonale)
* POP : téléportation aléatoire sur le plateau
* WIZZ : à voir
