-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Dim 11 Février 2018 à 23:27
-- Version du serveur :  5.7.20-0ubuntu0.17.04.1
-- Version de PHP :  7.0.22-0ubuntu0.17.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `crowdfunding_project`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `cat_id` int(9) NOT NULL,
  `cat_name` varchar(50) NOT NULL,
  `cat_label` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `category`
--

INSERT INTO `category` (`cat_id`, `cat_name`, `cat_label`) VALUES
(1, 'Animaux', 'animaux'),
(2, 'Jeux vidéo', 'jeux-video');

-- --------------------------------------------------------

--
-- Structure de la table `project`
--

CREATE TABLE `project` (
  `pro_id` int(9) NOT NULL,
  `pro_user` int(9) NOT NULL,
  `pro_category` int(9) NOT NULL,
  `pro_name` varchar(100) NOT NULL,
  `pro_description` text NOT NULL,
  `pro_amount` varchar(100) NOT NULL,
  `pro_created_at` varchar(100) NOT NULL,
  `pro_end_at` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `project_contribution`
--

CREATE TABLE `project_contribution` (
  `pcon_id` int(9) NOT NULL,
  `pcon_user` int(9) NOT NULL,
  `pcon_project` int(9) NOT NULL,
  `pcon_amount` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `use_id` int(9) NOT NULL,
  `use_username` varchar(50) NOT NULL,
  `use_password` varchar(50) NOT NULL,
  `use_role` int(1) NOT NULL DEFAULT '1',
  `use_mail` varchar(100) NOT NULL,
  `use_firstname` varchar(100) NOT NULL,
  `use_lastname` varchar(100) NOT NULL,
  `use_credit` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`use_id`, `use_username`, `use_password`, `use_role`, `use_mail`, `use_firstname`, `use_lastname`, `use_credit`) VALUES
(1, 'root', 'root', 0, 'root', 'root', 'root', '1000');

-- --------------------------------------------------------

--
-- Structure de la table `user_token`
--

CREATE TABLE `user_token` (
  `utok_id` int(9) NOT NULL,
  `utok_user` int(9) NOT NULL,
  `utok_key` varchar(300) NOT NULL,
  `utok_created_at` varchar(50) NOT NULL,
  `utok_expire_at` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `user_token`
--

INSERT INTO `user_token` (`utok_id`, `utok_user`, `utok_key`, `utok_created_at`, `utok_expire_at`) VALUES
(4, 1, 'Y2NhZjA4MTBhYmJlYWUyMmM1ZWY4ZDBkMWE4ZmEzYjJiNjUwNGI2Ng==', '1518381936', '172800'),
(5, 1, 'MTRkZTcwODU3NGQ5ODA3MzkyNjc2OTA0ZWEyMzEzMjJmMGNjNjM4MA==', '1518382741', '172800'),
(6, 1, 'Y2ZmOGYzZTk0OWZkMDY3ZjFhNzZiNGJmZjFmYjZjZDdmYTU1N2M4Nw==', '1518383491', '172800'),
(7, 1, 'MDExY2JjZGViYjg4ZjI3MTIyOGZhMzViNDY3M2MyOGU0ZDU5ZmFiMA==', '1518383502', '172800'),
(8, 1, 'NjBjYzc2OGYzMDljMDYyZWZlYWM5ZjQ0NDRmMzBjNDQ4MDZmMThlZA==', '1518383602', '172800'),
(9, 1, 'NGUwMGUyYjZhMThlOTU0YjAyOGI4YTBhYzI4Y2E1YjkzMzlkNTUyZQ==', '1518383656', '172800'),
(10, 1, 'M2E2YWFjNDMxY2YzZWM4Njc0MDU4Y2ExY2IwNDY4YjdmODRmMTc0Yg==', '1518385927', '172800');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cat_id`);

--
-- Index pour la table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`pro_id`);

--
-- Index pour la table `project_contribution`
--
ALTER TABLE `project_contribution`
  ADD PRIMARY KEY (`pcon_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`use_id`);

--
-- Index pour la table `user_token`
--
ALTER TABLE `user_token`
  ADD PRIMARY KEY (`utok_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `cat_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `project`
--
ALTER TABLE `project`
  MODIFY `pro_id` int(9) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `project_contribution`
--
ALTER TABLE `project_contribution`
  MODIFY `pcon_id` int(9) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `use_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `user_token`
--
ALTER TABLE `user_token`
  MODIFY `utok_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
