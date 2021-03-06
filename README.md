# SemanticWeb

## Utilisation du projet

### Pour compiler le projet
	javac TransitionMatrix.java

### Pour lancer le projet
	java TransitionMatrix graph.txt lambda epsilon

Par exemple :
	java TransitionMatrix graphs/small-20-pages.txt 0.85 0.001


## Résultats

### Question 1
Varier epsilon ne change presque pas la valeur du vecteur page rank. En effet, passer epsilon de 10^-3 à 10^-4 ne change le résultat qu'à partir de la 4e décimale (et intervertit 2 pages de numéros 2 et 15), et passer epsilon de 10^-4 à 10^-5 ne change que la 5e décimale. On constate donc qu'epsilon n'a (en tout cas, sur l'exemple du small graph donné) que peu d'impact sur le classement (où la première place du classement correspond au score page rank le plus faible).

Epsilon = 0.001 --> 0.06474 0.04816 0.03639 0.05342 0.04613 0.02528 0.03612 0.05328 0.05504 0.04784 0.08076 0.09141 0.04681 0.05654 0.02107 0.03640 0.02803 0.05931 0.03513 0.07812

Epsilon = 0.0001 --> 0.06473 0.04836 0.03635 0.05343 0.04610 0.02524 0.03622 0.05331 0.05495 0.04780 0.08059 0.09155 0.04696 0.05665 0.02107 0.03627 0.02806 0.05938 0.03504 0.07793

Epsilon = 0.00001 --> 0.06473 0.04835 0.03636 0.05343 0.04610 0.02524 0.03621 0.05331 0.05496 0.04780 0.08062 0.09153 0.04695 0.05664 0.02107 0.03628 0.02805 0.05937 0.03505 0.07796

Epsilon = 0.001	 |  Epsilon = 0.0001  |  Epsilon = 0.00001
\----------------------------------------------------------
Page 0 --> 16		Page 0 --> 16		 Page 0 --> 16 	
Page 1 --> 10       Page 1 --> 10        Page 1 --> 10
Page 2 --> 5        Page 2 --> 6         Page 2 --> 6
Page 3 --> 12       Page 3 --> 12        Page 3 --> 12
Page 4 --> 7        Page 4 --> 7         Page 4 --> 7
Page 5 --> 1        Page 5 --> 1         Page 5 --> 1
Page 6 --> 4        Page 6 --> 4         Page 6 --> 4
Page 7 --> 11       Page 7 --> 11        Page 7 --> 11
Page 8 --> 13       Page 8 --> 13        Page 8 --> 13
Page 9 --> 9        Page 9 --> 9         Page 9 --> 9
Page 10 --> 18      Page 10 --> 18       Page 10 --> 18
Page 11 --> 19      Page 11 --> 19       Page 11 --> 19
Page 12 --> 8       Page 12 --> 8        Page 12 --> 8
Page 13 --> 14      Page 13 --> 14       Page 13 --> 14
Page 14 --> 0       Page 14 --> 0        Page 14 --> 0
Page 15 --> 6       Page 15 --> 5        Page 15 --> 5
Page 16 --> 2       Page 16 --> 2        Page 16 --> 2
Page 17 --> 15      Page 17 --> 15       Page 17 --> 15
Page 18 --> 3       Page 18 --> 3        Page 18 --> 3
Page 19 --> 17      Page 19 --> 17       Page 19 --> 17

### Question 2
En ajoutant des autorités on voit qu'elles sont classés premières tandis que les hubs sont eux derniers (premier --> max du vecteur page_rank). Le fichier small-custom.txt modifile le graph small en y ajoutant un hub et une autorité. O est une autorité et 2 est un hub.

Epsilon = 0.001 : 0.18472 0.03612 0.00785 0.03836 0.05334 0.03758 0.02941 0.05682 0.03823 0.05408 0.07393 0.08346 0.04230 0.04729 0.01825 0.02291 0.03746 0.04321 0.02489 0.06977

Page 0 --> 19
Page 1 --> 5
Page 2 --> 0
Page 3 --> 9
Page 4 --> 13
Page 5 --> 7
Page 6 --> 4
Page 7 --> 15
Page 8 --> 8
Page 9 --> 14
Page 10 --> 17
Page 11 --> 18
Page 12 --> 10
Page 13 --> 12
Page 14 --> 1
Page 15 --> 2
Page 16 --> 6
Page 17 --> 11
Page 18 --> 3
Page 19 --> 16

### Question 3
On peut faire progresser artificiellement une page dans le classement en ajoutant des liens de cette page vers elle-même. Par exemple, la page 3 (pour l'instant 12e du classement), passe 19e si on lui ajoute 10 liens vers elle-même. Une façon de ne pas être piégé par ce genre de techniques serait tout simplement de ne pas prendre en compte ces liens.

Une autre solution est de créer des pages factices référençant la page que l'on souhaite faire progresser. De la même manière, avec 5 pages factices comportant chacune un lien vers la page 3, on fait progresser cette dernière de la 12e à la 21e place.

### Question 4
Lorsque lambda est égal à 0, le page rank vaut 1/N sur chacune de ses dimensions. Lorsqu'il vaut 1 le score de l'autorité est encore plus important qu'avec lambda = 0.85 et celui du hub est égal à 0 (logique puisque la probabilité ne prend plus en compte la possibilité de sauter d'une page à une autre). Lorsque lambda est très proche de 0, le score des pages est extrêmement proche puisque la probabilité de prendre un lien est très faible et ce qui implique que l'autorité à un score très peu supérieur à celui du hub.

lambda = 0.85 : 0.18472 0.03612 0.00785 0.03836 0.05334 0.03758 0.02941 0.05682 0.03823 0.05408 0.07393 0.08346 0.04230 0.04729 0.01825 0.02291 0.03746 0.04321 0.02489 0.06977

lambda = 0 : 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000 0.05000

lambda = 1 : 0.20532 0.03256 0.00000 0.03309 0.05425 0.03700 0.02442 0.05653 0.03564 0.05611 0.08229 0.09351 0.04177 0.04696 0.01224 0.01606 0.03633 0.03750 0.02020 0.07823

lambda = 0.1 : 0.06871 0.04886 0.04526 0.05002 0.04960 0.04740 0.04811 0.05091 0.04908 0.04941 0.05074 0.05178 0.04888 0.04994 0.04653 0.04794 0.04745 0.05152 0.04757 0.05027

