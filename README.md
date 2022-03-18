# quizz_ui

Notre sujet concerne la définition d’un DSL permettant de créer une interface de quiz pour
une web application. Cette interface affiche les éléments principaux d’un quiz, permet de
jouer et de répondre aux questions posées.
Nous sommes partis sur un DSL externe car nous souhaitions former notre propre syntaxe
afin de représenter tous les concepts possibles dans le cadre de la définition d’une interface
web. Nous avons exploité cet aspect lors de la déclaration du positionnement de nos
éléments dans l’interface et pour décrire nos composants. Nous avons choisi Antlr car nous
l’avions utilisé dans le projet précédent et nous savions qu’il permettait de répondre à notre
besoin pour ce projet.

Notre langage est textuel et est proche de notre domain model car nous sommes partis sur
des fonctionnalités à implémenter pour en déduire une syntaxe.
Le but de notre DSL est de permettre au designer de créer différents types de quiz, il peut
s’agir d’un quiz à choix multiple, à choix unique ou des réponses ouvertes. En effet, un quiz
peut avoir la forme d’une évaluation, d’un jeu ou bien d’un test de personnalité.
C’est pourquoi, le designer peut décider s’il est possible de revenir en arrière ou non, et
peut inclure une limite de temps etc…

## Diagramme de classes

![Diagramme de classe du projet quiz ui](/assets/classdiagram.PNG)

## Exemples

![exemples d'interfaces](/assets/exemples.PNG)
