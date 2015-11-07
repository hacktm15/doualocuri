-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 07 Noi 2015 la 20:18
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cdcol`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `cds`
--

CREATE TABLE IF NOT EXISTS `cds` (
  `titel` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `interpret` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `jahr` int(11) DEFAULT NULL,
`id` bigint(20) unsigned NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=7 ;

--
-- Salvarea datelor din tabel `cds`
--

INSERT INTO `cds` (`titel`, `interpret`, `jahr`, `id`) VALUES
('Beauty', 'Ryuichi Sakamoto', 1990, 1),
('Goodbye Country (Hello Nightclub)', 'Groove Armada', 2001, 4),
('Glee', 'Bran Van 3000', 1997, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cds`
--
ALTER TABLE `cds`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cds`
--
ALTER TABLE `cds`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;--
-- Database: `doi_pasi`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `mese`
--

CREATE TABLE IF NOT EXISTS `mese` (
`id` int(10) NOT NULL,
  `id_ul_local_al_mesei` int(10) DEFAULT NULL,
  `pub_id` int(10) DEFAULT NULL,
  `locuri` int(10) DEFAULT NULL,
  `is_free` tinyint(1) DEFAULT NULL,
  `zona` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pubs`
--

CREATE TABLE IF NOT EXISTS `pubs` (
`id` int(10) NOT NULL,
  `name` char(30) NOT NULL,
  `descriere` blob,
  `lat` float NOT NULL,
  `lng` float NOT NULL,
  `adresa` char(100) DEFAULT NULL,
  `is_open` tinyint(1) DEFAULT NULL,
  `start_luni` time DEFAULT NULL,
  `stop_luni` time DEFAULT NULL,
  `start_marti` time DEFAULT NULL,
  `stop_marti` time DEFAULT NULL,
  `start_miercuri` time DEFAULT NULL,
  `stop_miercuri` time DEFAULT NULL,
  `start_joi` time DEFAULT NULL,
  `stop_joi` time DEFAULT NULL,
  `start_vineri` time DEFAULT NULL,
  `stop_vineri` time DEFAULT NULL,
  `start_sambata` time DEFAULT NULL,
  `stop_sambata` time DEFAULT NULL,
  `start_duminica` time DEFAULT NULL,
  `stop_duminica` time DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Salvarea datelor din tabel `pubs`
--

INSERT INTO `pubs` (`id`, `name`, `descriere`, `lat`, `lng`, `adresa`, `is_open`, `start_luni`, `stop_luni`, `start_marti`, `stop_marti`, `start_miercuri`, `stop_miercuri`, `start_joi`, `stop_joi`, `start_vineri`, `stop_vineri`, `start_sambata`, `stop_sambata`, `start_duminica`, `stop_duminica`) VALUES
(1, '80s', '', 45.7409, 21.2398, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'manufactura', '', 45.7486, 21.2221, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'cuib', '', 45.7584, 21.2262, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'scart', '', 45.7431, 21.2242, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'capite', '', 45.748, 21.231, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
`id` int(10) NOT NULL,
  `table_id` int(10) DEFAULT NULL,
  `moment` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `utilizator`
--

CREATE TABLE IF NOT EXISTS `utilizator` (
`id` int(10) NOT NULL,
  `name` char(50) DEFAULT NULL,
  `salt` char(150) DEFAULT NULL,
  `hash` char(150) DEFAULT NULL,
  `pub` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `zone_restaurant`
--

CREATE TABLE IF NOT EXISTS `zone_restaurant` (
`id` int(10) NOT NULL,
  `id_pub` int(10) DEFAULT NULL,
  `denumire_zona` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mese`
--
ALTER TABLE `mese`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `masa_unica` (`id`,`id_ul_local_al_mesei`,`pub_id`);

--
-- Indexes for table `pubs`
--
ALTER TABLE `pubs`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilizator`
--
ALTER TABLE `utilizator`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `zone_restaurant`
--
ALTER TABLE `zone_restaurant`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mese`
--
ALTER TABLE `mese`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pubs`
--
ALTER TABLE `pubs`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `utilizator`
--
ALTER TABLE `utilizator`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `zone_restaurant`
--
ALTER TABLE `zone_restaurant`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;--
-- Database: `phpmyadmin`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_bookmark`
--

CREATE TABLE IF NOT EXISTS `pma_bookmark` (
`id` int(11) NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_column_info`
--

CREATE TABLE IF NOT EXISTS `pma_column_info` (
`id` int(5) unsigned NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_designer_coords`
--

CREATE TABLE IF NOT EXISTS `pma_designer_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `v` tinyint(4) DEFAULT NULL,
  `h` tinyint(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for Designer';

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_history`
--

CREATE TABLE IF NOT EXISTS `pma_history` (
`id` bigint(20) unsigned NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_navigationhiding`
--

CREATE TABLE IF NOT EXISTS `pma_navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_pdf_pages`
--

CREATE TABLE IF NOT EXISTS `pma_pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
`page_nr` int(10) unsigned NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_recent`
--

CREATE TABLE IF NOT EXISTS `pma_recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Salvarea datelor din tabel `pma_recent`
--

INSERT INTO `pma_recent` (`username`, `tables`) VALUES
('root', '[{"db":"doi_pasi","table":"pubs"},{"db":"doi_pasi","table":"utilizator"},{"db":"doi_pasi","table":"reservation"},{"db":"doi_pasi","table":"mese"},{"db":"phpmyadmin","table":"pma_column_info"},{"db":"phpmyadmin","table":"pma_designer_coords"},{"db":"phpmyadmin","table":"pma_history"},{"db":"phpmyadmin","table":"pma_pdf_pages"},{"db":"phpmyadmin","table":"pma_recent"},{"db":"phpmyadmin","table":"pma_relation"}]');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_relation`
--

CREATE TABLE IF NOT EXISTS `pma_relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_savedsearches`
--

CREATE TABLE IF NOT EXISTS `pma_savedsearches` (
`id` int(5) unsigned NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_table_coords`
--

CREATE TABLE IF NOT EXISTS `pma_table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT '0',
  `x` float unsigned NOT NULL DEFAULT '0',
  `y` float unsigned NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_table_info`
--

CREATE TABLE IF NOT EXISTS `pma_table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_table_uiprefs`
--

CREATE TABLE IF NOT EXISTS `pma_table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_tracking`
--

CREATE TABLE IF NOT EXISTS `pma_tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin,
  `data_sql` longtext COLLATE utf8_bin,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) unsigned NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_userconfig`
--

CREATE TABLE IF NOT EXISTS `pma_userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Salvarea datelor din tabel `pma_userconfig`
--

INSERT INTO `pma_userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2015-11-07 19:17:44', '{"collation_connection":"utf8mb4_general_ci","lang":"ro"}');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_usergroups`
--

CREATE TABLE IF NOT EXISTS `pma_usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `pma_users`
--

CREATE TABLE IF NOT EXISTS `pma_users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pma_bookmark`
--
ALTER TABLE `pma_bookmark`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pma_column_info`
--
ALTER TABLE `pma_column_info`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indexes for table `pma_designer_coords`
--
ALTER TABLE `pma_designer_coords`
 ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma_history`
--
ALTER TABLE `pma_history`
 ADD PRIMARY KEY (`id`), ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indexes for table `pma_navigationhiding`
--
ALTER TABLE `pma_navigationhiding`
 ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indexes for table `pma_pdf_pages`
--
ALTER TABLE `pma_pdf_pages`
 ADD PRIMARY KEY (`page_nr`), ADD KEY `db_name` (`db_name`);

--
-- Indexes for table `pma_recent`
--
ALTER TABLE `pma_recent`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma_relation`
--
ALTER TABLE `pma_relation`
 ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`), ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indexes for table `pma_savedsearches`
--
ALTER TABLE `pma_savedsearches`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indexes for table `pma_table_coords`
--
ALTER TABLE `pma_table_coords`
 ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indexes for table `pma_table_info`
--
ALTER TABLE `pma_table_info`
 ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma_table_uiprefs`
--
ALTER TABLE `pma_table_uiprefs`
 ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indexes for table `pma_tracking`
--
ALTER TABLE `pma_tracking`
 ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indexes for table `pma_userconfig`
--
ALTER TABLE `pma_userconfig`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma_usergroups`
--
ALTER TABLE `pma_usergroups`
 ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indexes for table `pma_users`
--
ALTER TABLE `pma_users`
 ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pma_bookmark`
--
ALTER TABLE `pma_bookmark`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma_column_info`
--
ALTER TABLE `pma_column_info`
MODIFY `id` int(5) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma_history`
--
ALTER TABLE `pma_history`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma_pdf_pages`
--
ALTER TABLE `pma_pdf_pages`
MODIFY `page_nr` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma_savedsearches`
--
ALTER TABLE `pma_savedsearches`
MODIFY `id` int(5) unsigned NOT NULL AUTO_INCREMENT;--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `categoriesr`
--

CREATE TABLE IF NOT EXISTS `categoriesr` (
  `IDCategory` int(8) NOT NULL,
  `CategoryName` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `categoriesr`
--

INSERT INTO `categoriesr` (`IDCategory`, `CategoryName`) VALUES
(1, 'Laptops'),
(2, 'Pc'),
(3, 'Ebook Reader');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `clientr`
--

CREATE TABLE IF NOT EXISTS `clientr` (
  `IDCLIENT` int(10) NOT NULL,
  `ClientName` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `clientr`
--

INSERT INTO `clientr` (`IDCLIENT`, `ClientName`) VALUES
(1, 'Jane'),
(2, 'John');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `courses`
--

CREATE TABLE IF NOT EXISTS `courses` (
  `codc` int(5) NOT NULL,
  `name` char(30) DEFAULT NULL,
  `proffessor` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `courses`
--

INSERT INTO `courses` (`codc`, `name`, `proffessor`) VALUES
(1, 'Laptops', 'adi'),
(2, 'dmd', 'dan'),
(3, 'gcc', 'gheo'),
(4, 'passc', 'iulia');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `grades`
--

CREATE TABLE IF NOT EXISTS `grades` (
  `cods` int(10) DEFAULT NULL,
  `codc` int(5) DEFAULT NULL,
  `grade` float DEFAULT NULL,
  `prezentare` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `grades`
--

INSERT INTO `grades` (`cods`, `codc`, `grade`, `prezentare`) VALUES
(1, 1, 3.2, 1),
(2, 1, 9, 1),
(3, 1, 7, 1),
(1, 2, 6, 1),
(2, 2, 10, 1),
(3, 2, 9.2, 1),
(1, 3, 8.5, 1),
(2, 3, 10, 1),
(3, 3, 10, 1),
(1, 4, 4.6, 1),
(2, 4, 9.2, 1),
(3, 4, 4.7, 1),
(1, 1, 5.2, 2),
(3, 4, 6, 2),
(1, 4, 4.9, 2),
(1, 4, 6.2, 3),
(4, 1, 3.2, 2);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `ordersr`
--

CREATE TABLE IF NOT EXISTS `ordersr` (
  `IDOrder` int(20) NOT NULL,
  `IDProduct` int(2) DEFAULT NULL,
  `IDClient` int(10) DEFAULT NULL,
  `ORDERNAME` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `ordersr`
--

INSERT INTO `ordersr` (`IDOrder`, `IDProduct`, `IDClient`, `ORDERNAME`) VALUES
(1, 1, 1, 'OrderNo.1'),
(2, 2, 1, 'OrderNo.2');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `productr`
--

CREATE TABLE IF NOT EXISTS `productr` (
  `IDProduct` int(10) NOT NULL,
  `ProductName` varchar(20) DEFAULT NULL,
  `Properties` int(10) DEFAULT NULL,
  `Categorie` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `productr`
--

INSERT INTO `productr` (`IDProduct`, `ProductName`, `Properties`, `Categorie`) VALUES
(1, 'Tableta', 3, 1),
(2, 'Laptop', 2, 2),
(3, 'Kindle', 1, 3);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `propertiesr`
--

CREATE TABLE IF NOT EXISTS `propertiesr` (
  `IDProprerty` int(10) NOT NULL,
  `IDCategory` int(8) DEFAULT NULL,
  `Qantity` int(5) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `Dimensiune` char(10) DEFAULT NULL,
  `Colour` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `propertiesr`
--

INSERT INTO `propertiesr` (`IDProprerty`, `IDCategory`, `Qantity`, `Price`, `Dimensiune`, `Colour`) VALUES
(1, 1, 10, 10.5, '10cm', 'red'),
(2, 1, 11, 20, '10cm', 'blue'),
(3, 2, 12, 30.1, '10cm', 'green');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `results`
--

CREATE TABLE IF NOT EXISTS `results` (
  `Team1ID` int(5) DEFAULT NULL,
  `Team2ID` int(5) DEFAULT NULL,
  `GoalsTeam1` int(2) DEFAULT NULL,
  `GoalsTeam2` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `Cods` int(10) NOT NULL,
  `Name` char(30) DEFAULT NULL,
  `birthday` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `student`
--

INSERT INTO `student` (`Cods`, `Name`, `birthday`) VALUES
(1, 'gigi', '0000-00-00'),
(2, 'ion', '0000-00-00'),
(3, 'andrei', NULL),
(4, 'iicb', NULL);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `teams`
--

CREATE TABLE IF NOT EXISTS `teams` (
  `teamID` int(5) NOT NULL,
  `team_name` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `teeeeeeeeeeeeeest`
--

CREATE TABLE IF NOT EXISTS `teeeeeeeeeeeeeest` (
  `id` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoriesr`
--
ALTER TABLE `categoriesr`
 ADD PRIMARY KEY (`IDCategory`);

--
-- Indexes for table `clientr`
--
ALTER TABLE `clientr`
 ADD PRIMARY KEY (`IDCLIENT`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
 ADD PRIMARY KEY (`codc`);

--
-- Indexes for table `ordersr`
--
ALTER TABLE `ordersr`
 ADD PRIMARY KEY (`IDOrder`);

--
-- Indexes for table `productr`
--
ALTER TABLE `productr`
 ADD PRIMARY KEY (`IDProduct`);

--
-- Indexes for table `propertiesr`
--
ALTER TABLE `propertiesr`
 ADD PRIMARY KEY (`IDProprerty`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
 ADD PRIMARY KEY (`Cods`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
 ADD PRIMARY KEY (`teamID`);
--
-- Database: `webauth`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `user_pwd`
--

CREATE TABLE IF NOT EXISTS `user_pwd` (
  `name` char(30) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `pass` char(32) COLLATE latin1_general_ci NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Salvarea datelor din tabel `user_pwd`
--

INSERT INTO `user_pwd` (`name`, `pass`) VALUES
('xampp', 'wampp');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user_pwd`
--
ALTER TABLE `user_pwd`
 ADD PRIMARY KEY (`name`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
