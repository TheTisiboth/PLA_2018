# PLA
## Nom du jeu : COLORicm

### Nombre de joueurs : 2 joueurs

### But du jeu : 
Colorier le plus grand nombre de cases dans un temps imparti.

### Règle du jeu : 
Une partie dure 3 minutes.
Chaque joueur dispose d'un stock de peinture au départ (30 cases). 
Lorsqu'un joueur passe sur une case qui n'est pas de sa couleur, elle est coloriée avec la couleur du personnage, et sa jauge de peinture diminue.
Quand le personnage n'a plus de peinture, il ne peut plus colorier de cases.
Il est possible de ramasser des recharges de peinture, des boosts, des sbires.
Lorsqu'un joueur frappe un autre joueur, il est renvoyé à sa position initiale.
Chaque joueur peut collecter au maximum 4 sbires différents.
Sur le terrain, il y a des obstacles qui peuvent être détruits afin de poser de la peinture à leur place.
Sur le terrain, il y a des portails, qui permettent de se téléporter sur la map à un endroit aléatoire

### Boosts :
Les boosts sont appliqués aux joueurs
* accélération : Le joueur est 2 fois plus rapide pendant 10 cases
* gel : Le joueur adverse est immobilisé pendant 5 secondes

### Recharge de peinture :
Lorsqu'un joueur ramasse une recharge de peinture il remplit sa jauge de peinture (15 cases).

### Obstacle : 
Les obstacles peuvent être détruits par un hit de joueur ou par l'explosion d'un sbire kamikaze.

### Comportement :
* **HIT** : Permet de taper. Lorsqu'il tape un obstacle, il le détruit, si il tape un sbire, il lui enlève 3 cases de déplacement et si c'est le personnage adverse alors il le renvoie à sa case de départ.


* **WIZZ** : Pose un mur

* **POP** : Peind la case sur laquelle on passe

* **Pick** : Efface la peinture

* **Kamikaze** : Explose, en colorant les cases adjacentes



