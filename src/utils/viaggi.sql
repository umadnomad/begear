SET FOREIGN_KEY_CHECKS=0;
#USE `viaggi`;
# Exporting data from `viaggi`
# Data for table `viaggi`.`cliente`:
INSERT INTO `viaggi`.`cliente` VALUES (1, 'Ivan', 'Ciccarelli', 'CCCVNI66B06A909J', '1966-02-06', '2017-07-31 14:26:22', '333569679', 'ivanciccarelli1966@mail.it');
INSERT INTO `viaggi`.`cliente` VALUES (0, 'Silvio', 'Danesi', 'DNSSLV67M25I611Y', '1967-08-25', '2017-07-31 14:24:44', '330359079', 'silviodanesi1967@mail.it');
INSERT INTO `viaggi`.`cliente` VALUES (2, 'Artemio', 'Paglia', 'PGLRTM65A12A056A', '1965-01-12', '2017-07-31 14:28:37', '331532645', 'artemiopaglia1965@mail.it');
INSERT INTO `viaggi`.`cliente` VALUES (4, 'Loretta', 'Pastorino', 'PSTLTT80M69A993Y', '1980-08-29', '2017-07-31 14:30:23', '333351395', 'lorettapastorino1980@mail.it');
INSERT INTO `viaggi`.`cliente` VALUES (3, 'Raffaella', 'Strada', 'STRRFL78H55D062Z', '1978-06-15', '2017-07-31 14:28:37', '320885650', 'raffaellastrada1978@mail.it');
# Data for table `viaggi`.`vacanza`:
INSERT INTO `viaggi`.`vacanza` VALUES (0, 'Nel cuore della Foresta Nera');
INSERT INTO `viaggi`.`vacanza` VALUES (1, 'Approdo del Re');
INSERT INTO `viaggi`.`vacanza` VALUES (2, 'Nido dell\'Aquila');
INSERT INTO `viaggi`.`vacanza` VALUES (3, 'Lambrate per sognare');
INSERT INTO `viaggi`.`vacanza` VALUES (4, 'Tour \"I fiordi\"');
# Data for table `viaggi`.`villeggiante`:
INSERT INTO `viaggi`.`villeggiante` VALUES ('CCCVNI66B06A909J', 'ivvy.cicchetto', 1);
INSERT INTO `viaggi`.`villeggiante` VALUES ('CCCVNI66B06A909J', 'ivvy.cicchetto', 1);
INSERT INTO `viaggi`.`villeggiante` VALUES ('CCCVNI66B06A909J', 'ivvy.cicchetto', 2);
INSERT INTO `viaggi`.`villeggiante` VALUES ('DNSSLV67M25I611Y', 'silver', 0);
INSERT INTO `viaggi`.`villeggiante` VALUES ('PGLRTM65A12A056A', 'artemio65', 3);
INSERT INTO `viaggi`.`villeggiante` VALUES ('PGLRTM65A12A056A', 'artemio65', 3);
INSERT INTO `viaggi`.`villeggiante` VALUES ('PSTLTT80M69A993Y', 'lore', 0);
INSERT INTO `viaggi`.`villeggiante` VALUES ('PSTLTT80M69A993Y', 'lore', 0);
INSERT INTO `viaggi`.`villeggiante` VALUES ('PSTLTT80M69A993Y', 'lore', 0);
INSERT INTO `viaggi`.`villeggiante` VALUES ('PSTLTT80M69A993Y', 'lore', 1);
#...done.
SET FOREIGN_KEY_CHECKS=1;
