CONNECTFOUR in JAVA
Gerwin Puttenstein
Groep 39

Een implementatie van het spel vier op een rij in java.
Het kan zijn dat er onverwachte fouten optreden of dat de dingen niet helemaal gaan zoals de bedoeling is.
Mocht er iets onverwachts gebeuren, is meestal de beste oplossing om het programma opnieuw te starten.

------------

CONTENTS

------------

	1) Starten van een Server
	2) Starten van een Client
	3) Spelen van een spel
	4) Chatten met mede spelers
	5) Sluiten van de Client
	6) Sluiten van de Server

-------------------------------------------------------

1) Starten van een Client

------------

Een Client kan worden gestart door StartGUI te starten.
Hier zijn drie textvelden en twee knoppen te zien.
In de velden kunnen respectievelijk de naam, het ip adres van de server en de poort waar de server op luistert worden ingevoerd.
Als men kiest voor de eerste optie, standalone, wordt een nieuwe BoardGUI geopend en kan er een spel worden gespeeld tegen een AI.
Als dit spel voorbij is worden de knoppen disabled en is het voorbij.
Als men voor de tweede optie kiest, Multiplayer, moeten de drie textvelden zijn ingevuld met correcte informatie. Mocht dit niet het geval zijn stopt het programma.
Als deze informatie wel correct is ingevoerd start er een nieuwe GUI, ClientGUI, en is men verbonden met de server.

Wil men nog meer Clients starten, moet deze stap het gewenste aantal keren uitgevoerd worden.

-------------------------------------------------------

2) Starten van een Server

------------

Een Server kan gestart worden door de ServerStartGUI aan te roepen. Dit opent een nieuw venster met een tekstveld en een knop.
In dit veld moet een poort worden ingevuld.
Als men dan op de knop drukt met een geldige poort, wordt er een nieuwe server gestart.
Als er een nieuwe server wordt gestart, opent er een nieuwe GUI, ServerGUI, en kunnen de berichten worden bekeken en de namen van de Clients die zijn verbonden.

-------------------------------------------------------

3) Spelen van een spel

------------

Als men een spel wil spelen, moeten er twee clients verbonden zijn met de server.
Dan moeten beide clients op play drukken.
Wanneer er twee clients willen spelen, wordt er een nieuwe ServerGame aangemaakt en zullen er twee schermen openen. Dit zijn de borden waarop gespeeld wordt, voor elke client een.
Er kunnen zetten gedaan worden als de knoppen enabled zijn.
Zodra het spel over is, dus als er een winnaar is of als het bord vol is, worden de BoardGUIs afgesloten en wordt er een bericht gestuurd naar de spelers in de game wie er had gewonnen.

-------------------------------------------------------

4) Chatten met mede spelers

------------

Er is een mogelijkheid om te chatten met mede spelers.
Dit kan gedaan worden door binnen de ClientGUI in het tekstveld boven aan een bericht te typen en deze te sturen door op Chat te drukken

-------------------------------------------------------

5) Sluiten van de Client

------------

De Client kan disconnecten van de server door of op het knopje quit binnen de gui te drukken, of op het kruisje van de gui te drukken. Dit zorgt ervoor dat de GUI wordt gesloten en de verbinding wordt verbroken.

-------------------------------------------------------

6) Sluiten van de Server

------------

De GUI van de Server kan gesloten worden door op het kruisje te drukken.
Echter wordt er door een bug in de implementatie de Server zelf niet volledig afgesloten. Dit kan gedaan worden door handmatig het programma zelf te termineren. Anders blijft de server draaien.

















