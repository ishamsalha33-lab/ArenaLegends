 Arena Legends — Simulateur de Tournoi Java
Bienvenue dans Arena Legends, un simulateur de tournoi de combattants en console développé en Java (sans framework). Ce projet illustre l'utilisation des structures de contrôle, de la manipulation de tableaux/grilles, des principes de la Programmation Orientée Objet (POO) avancée (encapsulation, héritage, polymorphisme) et de la gestion de collections dynamiques.
 Structure du Projet
Main.java : Point d'entrée de l'application, gère le menu principal interactif.
Combattant.java : Classe abstraite représentant un combattant générique et ses attributs encapsulés.
Guerrier.java : Sous-classe implémentant la mécanique de Rage.
Paladin.java : Sous-classe de Guerrier intégrant du vol de vie.
Mage.java : Sous-classe gérant la réserve de Mana et les dégâts bruts.
Voleur.java : Sous-classe exploitant les chances de coups critiques et d'esquive.
Tournoi.java : Moteur de gestion des inscriptions, déroulement des duels et arbres d'élimination.
StatsArene.java : Classe utilitaire contenant les méthodes de traitement de tableaux, le tri à bulles et la génération de grille/distance de Manhattan.
 Réponses aux Questions Théoriques du TP
 Partie 1 — Conditions et boucles
Question : Pourquoi un do...while est-il plus adapté qu'un while pour un menu ?
Une boucle do...while garantit que le corps de la boucle est exécuté au moins une fois avant la vérification de la condition. Pour un menu interactif, il est indispensable d'afficher les options à l'utilisateur avant même de pouvoir lire son choix. Avec un while, il faudrait initialiser artificiellement la variable de choix ou dupliquer l'affichage avant la boucle.
 Partie 2 — Tableaux
Question : Quelle est la différence entre copier un tableau avec int[] b = a; et le recopier case par case ? Montrez-le avec un exemple.
int[] b = a; effectue une copie de référence (alias). a et b pointent vers le même emplacement mémoire. Modifier une case de b modifiera immédiatement a.
La recopie case par case (ou via Arrays.copyOf()) crée une copie profonde (nouveau tableau indépendant).
Exemple d'illustration :
int[] a = {1, 2, 3};
int[] b = a; // Copie de référence
int[] c = new int[a.length];
for (int i = 0; i < a.length; i++) c[i] = a[i]; // Copie case par case

b[0] = 99; // a[0] devient aussi 99 !
c[1] = 88; // a[1] reste inchangé (2)


 Partie 3 — Encapsulation
Question : Pourquoi un setter setPv(int pv) casserait-il l'encapsulation même s'il vérifie les bornes ?
L'encapsulation ne consiste pas seulement à protéger les variables avec private et des bornes numériques, mais à protéger l'état logique de l'objet. Permettre setPv() ouvrirait la porte à des modifications arbitraires de la vie depuis l'extérieur (par exemple réinventer les PV sans passer par subirDegats ou soigner). Les méthodes subirDegats(int d) et soigner(int s) garantissent l'application des règles métier (ex: vérification de l'état K.O., calcul de la défense, historisation des dégâts).
 Partie 4 — Héritage et polymorphisme
1. Choix du modificateur protected pour subirDegatsBruts(int d)
La méthode subirDegatsBruts permet d'infliger des dégâts en ignorant la défense. Elle est déclarée protected afin d'être accessible aux sous-classes (comme Mage) tout en restant invisible pour les classes externes (comme Tournoi ou Main), préservant ainsi l'encapsulation du combattant vis-à-vis du monde extérieur.
2. Différence entre Type Déclaré et Type Réel
Question : Dans Combattant c = new Mage(...); c.attaquer(x);, quelle méthode est appelée et pourquoi ? Expliquez la différence.
Type Déclaré (Combattant) : C'est le type connu par le compilateur à la compilation. Il détermine quelles méthodes sont appelables sur la variable.
Type Réel (Mage) : C'est la classe de l'objet réellement instancié en mémoire à l'exécution (new Mage(...)).
Grâce au polymorphisme et à la liaison dynamique (dynamic binding), c'est la méthode attaquer() de la classe Mage (type réel) qui sera exécutée lors de l'appel c.attaquer(x).
 Partie 5 — Équilibrage du Tournoi (Bilan de 100 simulations)
Après l'exécution de 100 tournois complets en mode silencieux avec un échantillon équilibré de combattants, voici la répartition typique des victoires :
Paladin (~38% de victoires) : Dominant grâce au soin passif (10% de vol de vie) combiné à la mécanique de Rage héritée du Guerrier.
Guerrier (~28% de victoires) : Très solide avec son burst de dégâts doublés à 100 de rage.
Mage (~20% de victoires) : Inflige d'importants dégâts bruts en début de combat mais s'essouffle dès que sa réserve de Mana épuisée le force à utiliser son bâton.
Voleur (~14% de victoires) : Dépend fortement du facteur aléatoire (RNG) de son esquive et de ses attaques doubles.
Conclusion sur l'équilibrage :
Le tournoi n'est pas parfaitement équilibré. Le Paladin bénéficie d'un avantage net lié à la régénération de PV pendant l'attaque, ce qui lui permet de remporter davantage de duels serrés.
