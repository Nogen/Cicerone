# Cicerone
App that helps cheap travelling thanks to user's time and knowledge sharing.


ISTRUZIONI PER L'UTILIZZO DEL PROGRAMMA
1)Dipendenze esterne:
	-MySql server 8.0: https://dev.mysql.com/downloads/mysql/
	-Maven: https://maven.apache.org/download.cgi

2)Dopo aver installato le dipendenze:
	2.1)aprire il cmd
	2.2)posizionarsi nel cartella dell'installazione locale di mysql server: .\MySQL\MySQL Server 8.0\bin\
	2.3)accedere a mysql da root: >mysql -u root -p
	2.4)caricare lo script di generazione del database: mysql>source PERCORSOSCRIPT\script-configurazione-database.sql
	2.5)Chiudere eventuali processi locali in ascolto sulla porta 8080
	2.6)posizionarsi nella cartella dei sorgenti del codice: PERCORSOSORGENTI\cicerone\
		2.6.1)Opzioni per run:
			-mvn spring-boot:run
		2.6.2)Opzioni per deploy e run:
			-mvn package -DskipTests
			-posizionarsi nella cartella target: PERCORSOSORGENTI\cicerone\target\
			-java -jar Cicerone-0.3.0.war
	2.7)Aprire il browser e digitare nella barra dell'url: http://localhost:8080/
