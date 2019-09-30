-- Creazione db_cicerone
DROP DATABASE IF EXISTS db_cicerone;
create schema db_cicerone;

-- Creazione utente per la gestione del database con permessi di CRUD
drop user 'springuser'@'%';
create user 'springuser'@'%' identified by '10000days';
grant select, insert, delete, update on db_cicerone.* to 'springuser'@'%';

-- Riempimento database
use db_cicerone;





-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: db_cicerone
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_entity`
--

DROP TABLE IF EXISTS `account_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account_entity` (
  `id_account` varchar(32) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `id_anagrafica` varchar(32) DEFAULT NULL,
  `numero_notifiche` int(11) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `ruolo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_account`),
  UNIQUE KEY `UK_20qtato7w9n532h7xx1o5x0f` (`email`),
  UNIQUE KEY `UK_6ptjvog248y1yiwydmnf6cexn` (`id_anagrafica`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_entity`
--

LOCK TABLES `account_entity` WRITE;
/*!40000 ALTER TABLE `account_entity` DISABLE KEYS */;
INSERT INTO `account_entity` VALUES ('77e21bb89a584fb491bd2ea1e83ef257','aeonss@live.com','83f2530fc07a44e5a02d796a4f079fae',0,'$2a$10$1GPZ4.d6njJoW3FuhX9TnuhPuGQTbzThhw/BD5mmL4sjRka6X6s26','Globetrotter'),('82a3b0c3dd0a4c768edef9c73cdcca32','g.caiati4@studenti.uniba.it','4048b07c27df4aaaad8b5c239427e516',0,'$2a$10$9L3US.wok3oXAIcPpSomeONdcIdXqjqtc5E0HrjvK4//V2GNnSnuq','Globetrotter'),('7e16d4f3cee04ec0a7104dac7f23ee52','d.depasquale5@studenti.uniba.it','19f937d249e74052ab10559078518902',0,'$2a$10$8LcBcN1cMS6cEYUQH/NaUOehnkd7B9IZSKxlFEAnHImYBOIOZExIa','Globetrotter'),('a1f2b92bf171491d887ba5a5a574ecb1','g.napoli4@studenti.uniba.it','5d9bf4d647a74c3091ea15bd877799fe',0,'$2a$10$tLIlKczD0oCJmjmYOD78p.Nk7EvtZfPyjejptFOYMzyM52NmfWPEy','globetrotter'),('5f1e51072e06451f8195cd6cedca1bf2','m.sifanno@gmail.com','abf20c8a18a14a559a8aa4ac3af118fb',0,'$2a$10$WYVHb7LL4FuxFZ5i8aql6Oki6IarJcclNdzqjJsDotnu/hL8GREiS','Globetrotter');
/*!40000 ALTER TABLE `account_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anagrafica_entity`
--

DROP TABLE IF EXISTS `anagrafica_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `anagrafica_entity` (
  `id_anagrafica` varchar(32) NOT NULL,
  `citta` varchar(100) DEFAULT NULL,
  `cognome` varchar(100) DEFAULT NULL,
  `data_di_nascita` datetime DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `numero_di_telefono` varchar(10) DEFAULT NULL,
  `sesso` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_anagrafica`),
  UNIQUE KEY `UK_dx3tx9q50u283qsgcfvwe7sje` (`numero_di_telefono`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anagrafica_entity`
--

LOCK TABLES `anagrafica_entity` WRITE;
/*!40000 ALTER TABLE `anagrafica_entity` DISABLE KEYS */;
INSERT INTO `anagrafica_entity` VALUES ('83f2530fc07a44e5a02d796a4f079fae','Bitonto, Bari, Italia','Fallacara','1994-04-29 22:00:00','Antonio','3403562503',_binary '\0'),('4048b07c27df4aaaad8b5c239427e516','Bitonto, Bari, Italia','Caiati','1995-03-08 23:00:00','Giuseppe','3291327862',_binary '\0'),('19f937d249e74052ab10559078518902','Bitonto, Bari, Italia','De Pasquale','1994-11-30 23:00:00','Davide','3332828794',_binary '\0'),('5d9bf4d647a74c3091ea15bd877799fe','Santo Spirito, Bari, Italia','Napoli','1994-03-12 23:00:00','Giovanni','3339084567',_binary '\0');
/*!40000 ALTER TABLE `anagrafica_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attivita_entity`
--

DROP TABLE IF EXISTS `attivita_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `attivita_entity` (
  `id_attivita` varchar(32) NOT NULL,
  `data_apertura` datetime DEFAULT NULL,
  `data_modifica` datetime DEFAULT NULL,
  `id_creatore` varchar(32) DEFAULT NULL,
  `id_itinerario` varchar(32) DEFAULT NULL,
  `stato_attivita` varchar(20) DEFAULT NULL,
  `titolo_attivita` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_attivita`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attivita_entity`
--

LOCK TABLES `attivita_entity` WRITE;
/*!40000 ALTER TABLE `attivita_entity` DISABLE KEYS */;
INSERT INTO `attivita_entity` VALUES ('c6cbd694597f424cb6dd0c1994b636e6','2019-07-15 22:00:00','2019-07-15 22:00:00','7e16d4f3cee04ec0a7104dac7f23ee52','056736e6d9574d149bb1797f56873970','aperto','Andiamo in centro!'),('b63acded691f44a6adcbec4c44188e7e','2019-07-15 22:00:00','2019-07-15 22:00:00','77e21bb89a584fb491bd2ea1e83ef257','dd71a3ec9f9c4b839b581e62fe2db58b','aperto','Grigliata sotto le stelle'),('e80501bd69b24de6a0499e9be1e802ed','2019-07-15 22:00:00','2019-07-15 22:00:00','7e16d4f3cee04ec0a7104dac7f23ee52','74c12d2b994647fcae60664af560dde3','aperto','Back to the root!'),('784f6cfb57a44787a55ccf5bfc181393','2019-07-15 22:00:00','2019-07-15 22:00:00','82a3b0c3dd0a4c768edef9c73cdcca32','e8fa30a19b294cbbb3dac0374096dd8b','aperto','Documentare il gotico del Duomo di Milano '),('2776777af542462b928f5cef860b00f7','2019-07-15 22:00:00','2019-07-15 22:00:00','a1f2b92bf171491d887ba5a5a574ecb1','ef047dcaf006481997729a05d03bf614','aperto','Notte di San Lorenzo'),('8154cf57f98b41fcbba326f36818cd88','2019-07-15 22:00:00','2019-07-15 22:00:00','7e16d4f3cee04ec0a7104dac7f23ee52','e8b91360fc214d5b94f9b4bd6502191d','aperto','Conosciamo insieme la città'),('66aca03e5a1642359386ffc3bbf2dab8','2019-07-15 22:00:00','2019-07-15 22:00:00','a1f2b92bf171491d887ba5a5a574ecb1','a7fa9ea60a05448e968f43608157294f','chiuso','Outlet Summer Festival'),('929d4a45a5084692b2d101d43f552ad7','2019-07-15 22:00:00','2019-07-15 22:00:00','82a3b0c3dd0a4c768edef9c73cdcca32','c0b506a35aba4bea954f6328f9b075a5','aperto','Esercizi di respirazione e tecniche yoga per una giornata in relax'),('0cbda64c52f04233b4d11a388bf0f091','2019-07-15 22:00:00','2019-07-15 22:00:00','a1f2b92bf171491d887ba5a5a574ecb1','4a3bdb592deb49288a700e13e8d8394e','aperto','Picnic in vigna'),('939b71335ed346cb8f5bffdd0123e835','2019-07-16 22:00:00','2019-07-16 22:00:00','82a3b0c3dd0a4c768edef9c73cdcca32','216a36138079472d97f17e997fcf7da7','aperto','Festival Jazz Music'),('042d911175a64da7865a94ccdf2d3121','2019-07-16 22:00:00','2019-07-16 22:00:00','77e21bb89a584fb491bd2ea1e83ef257','6c3313e14bf94a4a92ac680aaed0bceb','chiuso','Test Feedback 1'),('5a956b12969c409fa1a6577c220bc04d','2019-07-16 22:00:00','2019-07-16 22:00:00','82a3b0c3dd0a4c768edef9c73cdcca32','0b77c2cc785948f38918574fc6964390','chiuso','Test Feedback 2');
/*!40000 ALTER TABLE `attivita_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback_entity`
--

DROP TABLE IF EXISTS `feedback_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `feedback_entity` (
  `id_feedback` varchar(32) NOT NULL,
  `commento` varchar(500) DEFAULT NULL,
  `data_rilascio` datetime DEFAULT NULL,
  `id_account` varchar(32) DEFAULT NULL,
  `id_attivita` varchar(32) DEFAULT NULL,
  `valutazione` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_feedback`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_entity`
--

LOCK TABLES `feedback_entity` WRITE;
/*!40000 ALTER TABLE `feedback_entity` DISABLE KEYS */;
INSERT INTO `feedback_entity` VALUES ('5e97f16288a74ef1b4517fcee3669c29','Feedback Test effettuato. ','2019-07-16 22:00:00','82a3b0c3dd0a4c768edef9c73cdcca32','042d911175a64da7865a94ccdf2d3121',3);
/*!40000 ALTER TABLE `feedback_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_itinerario_entity`
--

DROP TABLE IF EXISTS `foto_itinerario_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `foto_itinerario_entity` (
  `foto` varchar(200) NOT NULL,
  `id_itinerario` varchar(32) NOT NULL,
  PRIMARY KEY (`foto`,`id_itinerario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_itinerario_entity`
--

LOCK TABLES `foto_itinerario_entity` WRITE;
/*!40000 ALTER TABLE `foto_itinerario_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_itinerario_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itinerario_entity`
--

DROP TABLE IF EXISTS `itinerario_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `itinerario_entity` (
  `id_itinerario` varchar(32) NOT NULL,
  `data_incontro` datetime DEFAULT NULL,
  `descrizione_attivita` varchar(500) DEFAULT NULL,
  `nome_luogo` varchar(100) DEFAULT NULL,
  `ora_incontro` datetime DEFAULT NULL,
  `retribuzione` bit(1) DEFAULT NULL,
  `valore_retribuzione` double DEFAULT NULL,
  `x_incontro` double DEFAULT NULL,
  `y_incontro` double DEFAULT NULL,
  PRIMARY KEY (`id_itinerario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itinerario_entity`
--

LOCK TABLES `itinerario_entity` WRITE;
/*!40000 ALTER TABLE `itinerario_entity` DISABLE KEYS */;
INSERT INTO `itinerario_entity` VALUES ('056736e6d9574d149bb1797f56873970','2019-07-20 22:00:00','Il giro prevede la visita del centro storico della città di Bitonto e la presentazione di tutti i punti culturali che esso offre.','Piazza Guglielmo Marconi 62, 70032 Bitonto BA','1970-01-01 18:30:00',_binary '',100,41.10796,16.69146),('dd71a3ec9f9c4b839b581e62fe2db58b','2019-08-14 22:00:00','Una fantastica grigliata nella mia casa in campagna per festeggiare l\'avvenire del ferragosto.\r\nE\' possibile portare da mangiare oppure accedere al banchetto con una piccola quota.','Traversa Aspromonte 16, 70032 Bitonto BA','1970-01-01 11:30:00',_binary '\0',0,41.10307,16.68959),('74c12d2b994647fcae60664af560dde3','2019-08-14 22:00:00','L\'attività prevede un giro per la città di Bari visitando i punti più frequentati dalla movida barese e il contatto diretto con gli usi e costumi che la caratterizzano.','Piazza Aldo Moro 51, 70122 Bari BA','1970-01-01 14:00:00',_binary '\0',0,41.11835,16.86923),('e8fa30a19b294cbbb3dac0374096dd8b','2020-07-09 22:00:00','L\'itinerario prevederà la visita dei luoghi di maggior interesse nella città di Milano con particolare attenzione al gotico del Duomo. \r\nIl pranzo sarà a sacco. ','Duomo, Milano, Lombardia, Italia','1970-01-01 08:00:00',_binary '\0',0,45.4647,9.1935),('ef047dcaf006481997729a05d03bf614','2019-08-09 22:00:00','Relax in riva al mare','Via Vittorio da Bormida 2, 70044 Polignano a Mare BA','1970-01-01 19:00:00',_binary '\0',0,40.99748,17.21633),('e8b91360fc214d5b94f9b4bd6502191d','2019-07-26 22:00:00','L\'itinerario consiste in una piacevole passeggiata nel centro storico della città di Giovinazzo e il conseguente spostamento sul lungomare della stessa','Piazza Vittorio Emanuele 58, 70054 Giovinazzo BA','1970-01-01 21:00:00',_binary '',50,41.18756,16.67213),('a7fa9ea60a05448e968f43608157294f','2019-07-16 22:00:00','Buona musica e divertimento assicurato','Via Bari 82, 70010 Valenzano BA','1970-01-01 21:00:00',_binary '',20,41.04456,16.88411),('c0b506a35aba4bea954f6328f9b075a5','2019-08-18 22:00:00','Questa attività non comprende un vero e proprio percorso bensì permette, a chiunque voglia partecipare a questo genere di attività, di godere di una fantastica giornata in totale relax accompagnata da esercizi di respirazione e tecniche di yoga (consigliata per tutti gli appassionati di questo mondo). ','Foresta Umbra, Foggia, Italia','1970-01-01 14:00:00',_binary '\0',0,41.8203,15.9898),('4a3bdb592deb49288a700e13e8d8394e','2019-08-02 22:00:00','Metti un picnic gourmet in una serata fresca d’estate tra balle di fieno sotto un cielo stellato, dell’ottimo vino, una compagnia allegra, l’atmosfera della musica d’autore, l’emozione che solo il cinema all’aperto può regalarti.“','Martina Franca, Puglia, Italia','1970-01-01 18:00:00',_binary '',10,40.69847,17.19688),('216a36138079472d97f17e997fcf7da7','2019-12-05 23:00:00','Questa attività sarà prevista nella zona industriale di Roma e prevederà un concerto con moltissimi complessi che si esibiranno in musica jazz. Musica e cultura a Roma. ','Roma, Roma, Italia','1970-01-01 18:30:00',_binary '',15,41.9032,12.4957),('6c3313e14bf94a4a92ac680aaed0bceb','2019-07-16 22:00:00','Test per il feedback','Via Marchese di Montrone 8, 70122 Bari BA','1970-01-01 10:12:00',_binary '\0',0,41.12541,16.86635),('0b77c2cc785948f38918574fc6964390','2019-07-16 22:00:00','Questa è un\'attività per la prova del feedback.','Londra, Inghilterra','1970-01-01 21:30:00',_binary '\0',0,51.5064,-0.1272);
/*!40000 ALTER TABLE `itinerario_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lingua_account_entity`
--

DROP TABLE IF EXISTS `lingua_account_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lingua_account_entity` (
  `id_anagrafica` varchar(32) NOT NULL,
  `lingua` varchar(32) NOT NULL,
  PRIMARY KEY (`id_anagrafica`,`lingua`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lingua_account_entity`
--

LOCK TABLES `lingua_account_entity` WRITE;
/*!40000 ALTER TABLE `lingua_account_entity` DISABLE KEYS */;
INSERT INTO `lingua_account_entity` VALUES ('5d9bf4d647a74c3091ea15bd877799fe','Italian');
/*!40000 ALTER TABLE `lingua_account_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lingua_attivita_entity`
--

DROP TABLE IF EXISTS `lingua_attivita_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lingua_attivita_entity` (
  `id_itinerario` varchar(32) NOT NULL,
  `lingua` varchar(100) NOT NULL,
  PRIMARY KEY (`id_itinerario`,`lingua`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lingua_attivita_entity`
--

LOCK TABLES `lingua_attivita_entity` WRITE;
/*!40000 ALTER TABLE `lingua_attivita_entity` DISABLE KEYS */;
INSERT INTO `lingua_attivita_entity` VALUES ('056736e6d9574d149bb1797f56873970','English'),('056736e6d9574d149bb1797f56873970','French'),('056736e6d9574d149bb1797f56873970','German'),('056736e6d9574d149bb1797f56873970','Italian'),('0b77c2cc785948f38918574fc6964390',''),('216a36138079472d97f17e997fcf7da7','English'),('216a36138079472d97f17e997fcf7da7','French'),('216a36138079472d97f17e997fcf7da7','Italian'),('4a3bdb592deb49288a700e13e8d8394e','Italian'),('6c3313e14bf94a4a92ac680aaed0bceb','Italian'),('74c12d2b994647fcae60664af560dde3',''),('74c12d2b994647fcae60664af560dde3','English'),('74c12d2b994647fcae60664af560dde3','French'),('74c12d2b994647fcae60664af560dde3','German'),('74c12d2b994647fcae60664af560dde3','Irish'),('74c12d2b994647fcae60664af560dde3','Italian'),('74c12d2b994647fcae60664af560dde3','Swedish'),('a7fa9ea60a05448e968f43608157294f','English'),('c0b506a35aba4bea954f6328f9b075a5','English'),('c0b506a35aba4bea954f6328f9b075a5','Italian'),('dd71a3ec9f9c4b839b581e62fe2db58b','English'),('dd71a3ec9f9c4b839b581e62fe2db58b','Italian'),('e8b91360fc214d5b94f9b4bd6502191d','English'),('e8b91360fc214d5b94f9b4bd6502191d','Italian'),('e8fa30a19b294cbbb3dac0374096dd8b','Italian'),('ef047dcaf006481997729a05d03bf614','Italian');
/*!40000 ALTER TABLE `lingua_attivita_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifica_entity`
--

DROP TABLE IF EXISTS `notifica_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notifica_entity` (
  `id_notifica` varchar(32) NOT NULL,
  `data_apertura` datetime DEFAULT NULL,
  `id_attivita` varchar(32) DEFAULT NULL,
  `id_ricevente` varchar(32) DEFAULT NULL,
  `nome_evento` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_notifica`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifica_entity`
--

LOCK TABLES `notifica_entity` WRITE;
/*!40000 ALTER TABLE `notifica_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifica_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partecipanti_entity`
--

DROP TABLE IF EXISTS `partecipanti_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `partecipanti_entity` (
  `id_account` varchar(32) NOT NULL,
  `id_attivita` varchar(32) NOT NULL,
  PRIMARY KEY (`id_account`,`id_attivita`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partecipanti_entity`
--

LOCK TABLES `partecipanti_entity` WRITE;
/*!40000 ALTER TABLE `partecipanti_entity` DISABLE KEYS */;
INSERT INTO `partecipanti_entity` VALUES ('77e21bb89a584fb491bd2ea1e83ef257','5a956b12969c409fa1a6577c220bc04d'),('7e16d4f3cee04ec0a7104dac7f23ee52','66aca03e5a1642359386ffc3bbf2dab8'),('82a3b0c3dd0a4c768edef9c73cdcca32','042d911175a64da7865a94ccdf2d3121');
/*!40000 ALTER TABLE `partecipanti_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `richiesta_entity`
--

DROP TABLE IF EXISTS `richiesta_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `richiesta_entity` (
  `id_richiesta` varchar(32) NOT NULL,
  `data_richiesta` datetime DEFAULT NULL,
  `id_attivita` varchar(32) DEFAULT NULL,
  `id_richiedente` varchar(32) DEFAULT NULL,
  `stato_richiesta` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_richiesta`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `richiesta_entity`
--

LOCK TABLES `richiesta_entity` WRITE;
/*!40000 ALTER TABLE `richiesta_entity` DISABLE KEYS */;
INSERT INTO `richiesta_entity` VALUES ('22c21947f68d475eb614cb82fdfb17aa','2019-07-15 22:00:00','66aca03e5a1642359386ffc3bbf2dab8','7e16d4f3cee04ec0a7104dac7f23ee52','accettata'),('9fd72f0a3c554faf9aeb642dbdbd4be5','2019-07-16 22:00:00','5a956b12969c409fa1a6577c220bc04d','77e21bb89a584fb491bd2ea1e83ef257','accettata'),('94d67f5f3e1447d084576d8233770ef8','2019-07-16 22:00:00','042d911175a64da7865a94ccdf2d3121','82a3b0c3dd0a4c768edef9c73cdcca32','rifiutata'),('78d16dcb8116406db74c292e13a33840','2019-07-16 22:00:00','042d911175a64da7865a94ccdf2d3121','82a3b0c3dd0a4c768edef9c73cdcca32','accettata'),('77672e33ff0d43d2ade63e85ac787dd5','2019-07-16 22:00:00','042d911175a64da7865a94ccdf2d3121','a1f2b92bf171491d887ba5a5a574ecb1','rifiutata'),('1633190f754a49eab55a584707e591c0','2019-07-16 22:00:00','5a956b12969c409fa1a6577c220bc04d','a1f2b92bf171491d887ba5a5a574ecb1','rifiutata'),('1da4ab09604e4f118f25b09ee428fd02','2019-07-16 22:00:00','c6cbd694597f424cb6dd0c1994b636e6','77e21bb89a584fb491bd2ea1e83ef257','in sospeso'),('2ad4849f31464589a1e67ee52f975582','2019-07-16 22:00:00','8154cf57f98b41fcbba326f36818cd88','77e21bb89a584fb491bd2ea1e83ef257','in sospeso'),('14b0a60b6f824508b75c599c6182172c','2019-07-16 22:00:00','e80501bd69b24de6a0499e9be1e802ed','77e21bb89a584fb491bd2ea1e83ef257','in sospeso'),('cb40249342fd458fb1ff3b3e8ef86361','2019-07-16 22:00:00','929d4a45a5084692b2d101d43f552ad7','77e21bb89a584fb491bd2ea1e83ef257','in sospeso'),('e266b5d1e59b46598ea23fb09a926e28','2019-07-16 22:00:00','784f6cfb57a44787a55ccf5bfc181393','77e21bb89a584fb491bd2ea1e83ef257','in sospeso');
/*!40000 ALTER TABLE `richiesta_entity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-17 22:41:19
