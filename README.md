# PLA
## Nom du jeu :

### Objectif :
* manger / colorer le plus vite possible (une pizza) / un plateau (peinture illimité)
* multijoueurs : 1 contre 1 joueurs
* Lorsque l'on se déplace, on colorie les cases sur lesquelles on est passé
* On peut ramasser des items nous permettant de : ralentir, accelerer, geler...
* Pas de points de vie (ou alors 1 point de vie, et lorsque l'on prend un dégat, on retourne a notre base)
* Ramassage et peinture du sol automatique
* Vitesse de déplacement des personnages peut etre modifié
* Les 2 joueurs peuvent frapper
* Choix de classe au début du jeu, qui influe sur la chance d'obtenir certain type de sbire


###  Objets :
* Item qui retire les couleurs des autres dans un certain rayon
* Item qui explose en projettant la couleur de celui qui l'a récupéré
* Obstacles sur le plateau à détruire pour pouvoir mettre sa couleur
* On ramasse un item nous donnant un sbire (on peut en stocker 1 ou plusieurs)
* Glue : ralenti l'adversaire
* Speed : accelere le déplacement

###  Type de sbire :
* Peintre : meurt au bout de X cases coloriées
* Kamikaze : Explose au bout de X temps, en faisant des dégats et en coloriant les cases adjacentes
* Filou : Colorie vraiment beaucoup de cases OU en efface certaine
* Nettoyeur : efface la peinture de l'adversaire

### Fin du jeu : 
* Au bout de X temps, celui qui a colorié la plus grande partie de la map à gagné

### Variante :
* plusieurs couleurs à choisir au début du jeu
* Pousser l’autre du plateau : s’il tombe il disparaît pendant 1 seconde et on peut
gagner du terrain en attendant
* Olives sur la pizza : 1 seul mystère qui permet d’accéder à un raccourci vers les
autres olives aléatoirement
* Invoquer un petit IA sbire qui va mettre X tâches colorées puis disparaître
* Une roulette à pizza qui tranche les bonhommes sur le plateau qui disparaissent et
reviennent 2s plus tard

### Personnage :
* Choix sexe
* Choix couleur de cheveux
* Choix coupe de cheveux
* Choix vêtements
* Choix chapeau
* Choix sac à dos => banane (se démarquer!)
* Choix du nom

###  Actions :
* HIT : Envoie une bombe
* MOVE : se deplace sur une case adjacente (pas de diagonale)
* POP : téléportation aléatoire sur le plateau
* WIZZ : invoque 1 sbire 
