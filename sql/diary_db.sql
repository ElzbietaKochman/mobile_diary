-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 06 Sty 2015, 20:21
-- Wersja serwera: 5.6.20
-- Wersja PHP: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `diary_db`
--

DELIMITER $$
--
-- Procedury
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSemestrType`(
    in data_p DATE
)
BEGIN
	DECLARE rok_s INTEGER;
    select count(*) into rok_s from rok_szkolny where data_p between pocz_roku and k_zimowego;
    
    if (rok_s > 0) then select 'zimowy' sem;
else select 'letni' sem;
end if;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
`id_admin` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `nazwa` varchar(15) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `admin`
--

INSERT INTO `admin` (`id_admin`, `id_user`, `nazwa`) VALUES
(1, 1, 'adminke');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `komunikator`
--

CREATE TABLE IF NOT EXISTS `komunikator` (
`id_wiadomosci` int(10) NOT NULL,
  `id_nadawcy` int(10) NOT NULL,
  `id_odbiorcy` int(10) NOT NULL,
  `odebrano` tinyint(1) NOT NULL,
  `data` date NOT NULL,
  `godzina` time NOT NULL,
  `tresc` text NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=59 ;

--
-- Zrzut danych tabeli `komunikator`
--

INSERT INTO `komunikator` (`id_wiadomosci`, `id_nadawcy`, `id_odbiorcy`, `odebrano`, `data`, `godzina`, `tresc`) VALUES
(43, 5, 3, 1, '2014-12-21', '12:00:00', 'Pierwsza wiadomosc od opiekuna do nauczyciela.'),
(45, 5, 4, 1, '2014-12-21', '12:00:00', 'Pierwsza wiadomosc od opiekuna do wychowawcy'),
(47, 3, 5, 1, '2014-12-20', '10:00:00', 'Pierwsza od nauczyciela do opiekuna'),
(48, 3, 5, 0, '2014-12-21', '15:00:00', 'Druga od nauczyciela do opiekuna'),
(49, 5, 3, 1, '2014-12-21', '18:00:00', 'Druga od opiekuna do nauczyciela'),
(50, 5, 3, 1, '2014-12-21', '08:53:20', 'odpowiedz+na+druga+od+nauczyciela'),
(51, 5, 3, 1, '2014-12-21', '09:02:27', 'odpowiedz+na+druga+od+nauczyciela'),
(52, 5, 4, 1, '2014-12-21', '09:11:05', 'rozpoczynamy+rozmowe+z+wychowawca'),
(53, 3, 5, 1, '2014-12-21', '09:42:31', 'pierwsza+odpowiedz+od+nau'),
(54, 4, 5, 1, '2014-12-21', '09:45:03', 'pierwsza+od+wychowawcy'),
(55, 4, 5, 1, '2014-12-21', '12:32:32', 'rozpoczynamy'),
(56, 5, 4, 1, '2014-12-21', '12:32:57', 'tak'),
(57, 5, 3, 1, '2014-12-21', '15:54:18', 'odpowiedz'),
(58, 5, 4, 1, '2014-12-21', '15:55:19', 'do+wychowawcy');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `login_data`
--

CREATE TABLE IF NOT EXISTS `login_data` (
`id_user` int(10) NOT NULL,
  `login` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Zrzut danych tabeli `login_data`
--

INSERT INTO `login_data` (`id_user`, `login`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'uczen', 'uczen', 'uczen'),
(3, 'nauczyciel', 'nauczyciel', 'nauczyciel'),
(4, 'wychowawca', 'wychowawca', 'wychowawca'),
(5, 'opiekun', 'opiekun', 'opiekun'),
(6, 'uczen2', 'uczen2', 'uczen');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `nauczyciel`
--

CREATE TABLE IF NOT EXISTS `nauczyciel` (
`id_nauczyciela` int(10) NOT NULL,
  `id_user` int(10) DEFAULT NULL,
  `nazwa` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `telefon` int(10) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Zrzut danych tabeli `nauczyciel`
--

INSERT INTO `nauczyciel` (`id_nauczyciela`, `id_user`, `nazwa`, `email`, `telefon`) VALUES
(1, 3, 'Nauczyciel nauczycie', 'nau.nau@wp.pl', 333333333),
(2, 4, 'Nauczyciel Wychowawc', 'nau.wych', 444444444);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `nieobecnosci`
--

CREATE TABLE IF NOT EXISTS `nieobecnosci` (
`id_nieobecnosci` int(10) NOT NULL,
  `id_ucznia` int(10) DEFAULT NULL,
  `id_przedmiotu` int(10) DEFAULT NULL,
  `godz_pocz` time DEFAULT NULL,
  `godz_kon` time DEFAULT NULL,
  `data` date DEFAULT NULL,
  `usprawiedliwione` varchar(3) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Zrzut danych tabeli `nieobecnosci`
--

INSERT INTO `nieobecnosci` (`id_nieobecnosci`, `id_ucznia`, `id_przedmiotu`, `godz_pocz`, `godz_kon`, `data`, `usprawiedliwione`) VALUES
(1, 1, 1, '08:00:00', '08:45:00', '2014-11-27', 'tak'),
(2, 1, 2, '08:55:00', '09:40:00', '2014-11-27', 'nie'),
(3, 1, 3, '08:00:00', '08:45:00', '2014-12-03', 'nie'),
(4, 1, 3, '08:55:00', '09:40:00', '2014-12-03', 'nie'),
(5, 1, 3, '09:55:00', '10:40:00', '2014-12-03', 'nie'),
(6, 1, 1, '12:00:00', '12:45:00', '2014-12-03', 'tak'),
(7, 1, 2, '12:00:00', '12:45:00', '2013-12-03', 'nie'),
(12, 2, 2, '12:00:00', '12:45:00', '2014-12-30', 'tak'),
(13, 2, 2, '12:00:00', '12:45:00', '2014-12-30', 'tak'),
(17, 2, 1, '06:00:00', '07:00:00', '2014-12-13', 'tak'),
(19, 2, 2, '12:00:00', '13:30:00', '2014-12-14', 'tak'),
(21, 2, 1, '12:00:00', '13:00:00', '2014-12-21', 'tak'),
(22, 2, 2, '09:00:00', '10:00:00', '2014-12-21', 'tak'),
(23, 2, 1, '20:00:00', '21:00:00', '2015-01-04', 'nie');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ocena`
--

CREATE TABLE IF NOT EXISTS `ocena` (
`id_oceny` int(10) NOT NULL,
  `id_przedmiotu` int(10) DEFAULT NULL,
  `id_ucznia` int(10) DEFAULT NULL,
  `wartosc` float(5,1) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `uwaga_oceny` varchar(150) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Zrzut danych tabeli `ocena`
--

INSERT INTO `ocena` (`id_oceny`, `id_przedmiotu`, `id_ucznia`, `wartosc`, `data`, `uwaga_oceny`) VALUES
(1, 1, 1, 4.0, '2014-11-05', 'ocena z klasowki'),
(2, 1, 1, 5.0, '2014-11-03', 'ocena z testu. Brawo!'),
(3, 2, 1, 3.0, '2014-11-01', 'ocena z odpowiedzi ustnej'),
(4, 2, 1, 2.0, '2014-11-19', 'Klasowka. Powinno byc lepiej!'),
(5, 1, 1, 2.0, '2014-12-01', 'Nic do dodania'),
(6, 2, 1, 3.0, '2014-12-03', 'Nic do dodania'),
(7, 3, 1, 3.0, '2014-11-27', 'brak komentarza'),
(8, 3, 1, 4.0, '2014-12-03', 'zadanie z informatyki zrobiono poprawnie'),
(9, 2, 1, 3.0, '2014-12-01', 'brak komentarza'),
(10, 1, 1, 4.0, '2014-11-01', 'bardzo ladnie!'),
(11, 1, 1, 4.5, '2014-11-01', 'bardzo ladnie!'),
(12, 2, 1, 2.0, '2013-12-08', 'Niebyt dobrze...'),
(13, 2, 1, 4.0, '2015-06-02', 'Jedna z najlepszych prac w klasie. Gratulacje!'),
(14, 4, 1, 4.5, '2013-12-03', 'Bardzo ladnie.'),
(15, 4, 1, 5.0, '2013-11-18', 'Brawo! Swietna praca!'),
(16, 4, 1, 3.0, '2013-12-19', 'Niespodziewanie slaba praca.'),
(17, 4, 1, 4.0, '2014-03-03', 'Brak komentarza.'),
(18, 4, 1, 4.5, '2014-03-25', 'Coraz lepiej.'),
(19, 1, 2, 3.0, '2014-12-16', 'Kiepsko. Staraj sie bardziej.'),
(20, 1, 2, 4.5, '2014-12-25', 'Widze znaczna poprawe.'),
(21, 3, 1, 2.0, '2014-11-27', 'Bardzo slaby wynik.'),
(22, 1, 1, 5.0, '2014-12-11', 'Swietna ocena'),
(23, 1, 2, 5.0, '2014-12-11', 'super'),
(24, 1, 1, 5.0, '2014-12-11', 'Swietna ocena'),
(25, 1, 2, 4.5, '2014-12-11', 'bardzo+dobra+praca%2C+drobne+bledy'),
(26, 1, 2, 3.0, '2014-12-11', 'przecietna+praca'),
(27, 1, 2, 3.5, '2014-12-12', 'koniecznie+do+poprawy'),
(28, 2, 1, 5.0, '2014-12-15', 'wspaniale'),
(29, 1, 2, 6.0, '2014-12-21', 'najlepsza+praca+w+klasie'),
(30, 3, 1, 5.0, '2014-12-21', 'dobra+praca'),
(31, 1, 2, 5.0, '2015-01-04', '%3A%29');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ocena_koncowa`
--

CREATE TABLE IF NOT EXISTS `ocena_koncowa` (
`id_ocenyK` int(10) NOT NULL,
  `id_ucznia` int(10) DEFAULT NULL,
  `id_przedmiotu` int(10) DEFAULT NULL,
  `id_rok` int(10) DEFAULT NULL,
  `semestralna` float(5,2) DEFAULT NULL,
  `koncowa` float(5,2) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Zrzut danych tabeli `ocena_koncowa`
--

INSERT INTO `ocena_koncowa` (`id_ocenyK`, `id_ucznia`, `id_przedmiotu`, `id_rok`, `semestralna`, `koncowa`) VALUES
(7, 1, 2, 2, 5.00, 3.00),
(8, 1, 1, 2, 4.00, 5.00),
(9, 1, 4, 2, 4.00, 4.50),
(10, 2, 1, 1, 5.00, 5.00),
(11, 1, 2, 1, 5.00, 3.00),
(12, 1, 3, 1, 4.00, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ogloszenia`
--

CREATE TABLE IF NOT EXISTS `ogloszenia` (
`id_ogloszenia` int(20) NOT NULL,
  `id_ucznia` int(10) DEFAULT NULL,
  `id_nauczyciela` int(10) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `godz_wyslania` time DEFAULT NULL,
  `temat` varchar(50) DEFAULT NULL,
  `tresc` varchar(500) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Zrzut danych tabeli `ogloszenia`
--

INSERT INTO `ogloszenia` (`id_ogloszenia`, `id_ucznia`, `id_nauczyciela`, `data`, `godz_wyslania`, `temat`, `tresc`) VALUES
(2, 1, 1, '2014-11-12', '12:00:00', 'srodowe zajecia', 'Przypominam, ze na srodowe zajrcia nalezy zabrac kalosze!'),
(3, 1, 2, '2014-11-12', '20:00:00', 'Wywiadowka', 'Przypominam, jutro o 15 w sali nr 4 odbedzie sie wywiadowka. '),
(4, 1, 2, '2014-12-01', '10:00:00', 'Zastepstwo', 'Dnia 2.12.2014r. zajecia lekcyjne w zastepstwie za p.Lis poprowadzi p. Anna Nowak.'),
(5, 1, 2, '2014-12-03', '12:30:00', 'Wycieczka do planetarium.', 'P. Lis organizuje wycieczke, cel -> Planetarium Slaskie, czas -> 16.12.2014. W celu uzyskania dokladniejszych informacji prosze kontaktowac sie z organizatorka.'),
(6, 2, 2, '2014-12-03', '12:30:00', 'Uczniowskie mikolajki', 'Z przyjemnoscia informujemy, ze dnia 6.12 sa organizowane uczniowskie mikolajki. Wszystkich uczniow zapraszamy tego dnia o godz. 13 do sali nr 10.'),
(11, 1, 1, '2014-12-13', '10:14:22', 'nauczyciel', 'nauczyciel+nauczyciel'),
(12, 2, 1, '2014-12-13', '10:14:22', 'nauczyciel', 'nauczyciel+nauczyciel'),
(13, 1, 2, '2014-12-21', '16:01:11', 'ogloszenie', 'wazna+wiadomosc'),
(14, 2, 2, '2014-12-21', '16:01:11', 'ogloszenie', 'wazna+wiadomosc');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `opiekun`
--

CREATE TABLE IF NOT EXISTS `opiekun` (
`id_opiekuna` int(10) NOT NULL,
  `id_user` int(10) DEFAULT NULL,
  `nazwa` varchar(20) DEFAULT NULL,
  `telefon` int(10) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `opiekun`
--

INSERT INTO `opiekun` (`id_opiekuna`, `id_user`, `nazwa`, `telefon`) VALUES
(1, 5, 'Nowak Iza', 661279689);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `plik`
--

CREATE TABLE IF NOT EXISTS `plik` (
`id_pliku` int(10) NOT NULL,
  `id_ucznia` int(10) NOT NULL,
  `nazwa` varchar(100) NOT NULL,
  `etykieta` varchar(150) NOT NULL,
  `data` date NOT NULL,
  `odczytany` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `plik`
--

INSERT INTO `plik` (`id_pliku`, `id_ucznia`, `nazwa`, `etykieta`, `data`, `odczytany`) VALUES
(2, 1, 'zdjecie_20150103_135610_-312066503.jpg', 'opiekun', '2015-01-03', 0),
(3, 1, 'zdjecie_20150104_125600_218619880.jpg', 'zdjatko', '2015-01-04', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `poziom_klasa`
--

CREATE TABLE IF NOT EXISTS `poziom_klasa` (
`id_klasy` int(10) NOT NULL,
  `id_grupy` int(10) DEFAULT NULL,
  `rok_szkolny` varchar(10) DEFAULT NULL,
  `poziom` varchar(2) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Zrzut danych tabeli `poziom_klasa`
--

INSERT INTO `poziom_klasa` (`id_klasy`, `id_grupy`, `rok_szkolny`, `poziom`) VALUES
(1, 1, '2014-2015', '3a'),
(2, 1, '2013-2014', '2a');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przedmiot`
--

CREATE TABLE IF NOT EXISTS `przedmiot` (
`id_przedmiotu` int(10) NOT NULL,
  `id_nauczyciela` int(10) DEFAULT NULL,
  `id_grupy` int(10) DEFAULT NULL,
  `nazwa` varchar(20) DEFAULT NULL,
  `rok_szkolny` varchar(9) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Zrzut danych tabeli `przedmiot`
--

INSERT INTO `przedmiot` (`id_przedmiotu`, `id_nauczyciela`, `id_grupy`, `nazwa`, `rok_szkolny`) VALUES
(1, 1, 1, 'matematyka', '2014-2015'),
(2, 2, 1, 'fizyka', '2014-2015'),
(3, 2, 1, 'informatyka', '2014-2015'),
(4, 1, 1, 'matematyka', '2013-2014'),
(5, 2, 1, 'informatyka', '2013-2014'),
(6, 2, 1, 'fizyka', '2013-2014');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rok_szkolny`
--

CREATE TABLE IF NOT EXISTS `rok_szkolny` (
`id_rok` int(10) NOT NULL,
  `rok_szkolny` varchar(10) NOT NULL,
  `pocz_roku` date DEFAULT NULL,
  `k_zimowego` date NOT NULL,
  `k_roku` date NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Zrzut danych tabeli `rok_szkolny`
--

INSERT INTO `rok_szkolny` (`id_rok`, `rok_szkolny`, `pocz_roku`, `k_zimowego`, `k_roku`) VALUES
(1, '2014-2015', '2014-09-01', '2015-01-31', '2015-06-19'),
(2, '2013-2014', '2013-09-01', '2014-02-01', '2014-06-28');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uczen`
--

CREATE TABLE IF NOT EXISTS `uczen` (
`id_ucznia` int(10) NOT NULL,
  `id_user` int(10) DEFAULT NULL,
  `id_grupy` int(10) DEFAULT NULL,
  `nazwa` varchar(20) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Zrzut danych tabeli `uczen`
--

INSERT INTO `uczen` (`id_ucznia`, `id_user`, `id_grupy`, `nazwa`) VALUES
(1, 2, 1, 'Nowak Uczen'),
(2, 6, 1, 'Drugi Uczen');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uczen_opiekun`
--

CREATE TABLE IF NOT EXISTS `uczen_opiekun` (
`id_relacja` int(10) NOT NULL,
  `id_ucznia` int(10) DEFAULT NULL,
  `id_opiekuna` int(10) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Zrzut danych tabeli `uczen_opiekun`
--

INSERT INTO `uczen_opiekun` (`id_relacja`, `id_ucznia`, `id_opiekuna`) VALUES
(1, 1, 1),
(2, 2, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uwaga`
--

CREATE TABLE IF NOT EXISTS `uwaga` (
`id_uwagi` int(10) NOT NULL,
  `id_ucznia` int(10) DEFAULT NULL,
  `id_nauczyciela` int(10) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `tresc` varchar(200) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Zrzut danych tabeli `uwaga`
--

INSERT INTO `uwaga` (`id_uwagi`, `id_ucznia`, `id_nauczyciela`, `data`, `tresc`) VALUES
(1, 1, 2, '2014-11-06', 'Rozmawia na lekcji, nie reaguje na upomnienia nauczyciela prowadzacego przedmiot.'),
(2, 1, 2, '2013-12-03', 'Przeszkadza w prowadzeniu zajec.'),
(3, 2, 2, '2014-12-09', 'Po raz czwarty nie odrobil zadania domowego.'),
(4, 1, 1, '2014-12-13', 'nauczyciel'),
(5, 1, 2, '2014-12-21', 'uwaga');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wychowawca`
--

CREATE TABLE IF NOT EXISTS `wychowawca` (
`id_wychowawcy` int(10) NOT NULL,
  `id_nauczyciela` int(10) DEFAULT NULL,
  `id_grupy` int(10) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `wychowawca`
--

INSERT INTO `wychowawca` (`id_wychowawcy`, `id_nauczyciela`, `id_grupy`) VALUES
(1, 2, 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
 ADD PRIMARY KEY (`id_admin`), ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Indexes for table `komunikator`
--
ALTER TABLE `komunikator`
 ADD PRIMARY KEY (`id_wiadomosci`);

--
-- Indexes for table `login_data`
--
ALTER TABLE `login_data`
 ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `nauczyciel`
--
ALTER TABLE `nauczyciel`
 ADD PRIMARY KEY (`id_nauczyciela`), ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Indexes for table `nieobecnosci`
--
ALTER TABLE `nieobecnosci`
 ADD PRIMARY KEY (`id_nieobecnosci`);

--
-- Indexes for table `ocena`
--
ALTER TABLE `ocena`
 ADD PRIMARY KEY (`id_oceny`);

--
-- Indexes for table `ocena_koncowa`
--
ALTER TABLE `ocena_koncowa`
 ADD PRIMARY KEY (`id_ocenyK`);

--
-- Indexes for table `ogloszenia`
--
ALTER TABLE `ogloszenia`
 ADD PRIMARY KEY (`id_ogloszenia`);

--
-- Indexes for table `opiekun`
--
ALTER TABLE `opiekun`
 ADD PRIMARY KEY (`id_opiekuna`), ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Indexes for table `plik`
--
ALTER TABLE `plik`
 ADD PRIMARY KEY (`id_pliku`);

--
-- Indexes for table `poziom_klasa`
--
ALTER TABLE `poziom_klasa`
 ADD PRIMARY KEY (`id_klasy`);

--
-- Indexes for table `przedmiot`
--
ALTER TABLE `przedmiot`
 ADD PRIMARY KEY (`id_przedmiotu`);

--
-- Indexes for table `rok_szkolny`
--
ALTER TABLE `rok_szkolny`
 ADD PRIMARY KEY (`id_rok`);

--
-- Indexes for table `uczen`
--
ALTER TABLE `uczen`
 ADD PRIMARY KEY (`id_ucznia`), ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Indexes for table `uczen_opiekun`
--
ALTER TABLE `uczen_opiekun`
 ADD PRIMARY KEY (`id_relacja`);

--
-- Indexes for table `uwaga`
--
ALTER TABLE `uwaga`
 ADD PRIMARY KEY (`id_uwagi`);

--
-- Indexes for table `wychowawca`
--
ALTER TABLE `wychowawca`
 ADD PRIMARY KEY (`id_wychowawcy`), ADD UNIQUE KEY `id_nauczyciela` (`id_wychowawcy`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `admin`
--
ALTER TABLE `admin`
MODIFY `id_admin` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT dla tabeli `komunikator`
--
ALTER TABLE `komunikator`
MODIFY `id_wiadomosci` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=59;
--
-- AUTO_INCREMENT dla tabeli `login_data`
--
ALTER TABLE `login_data`
MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `nauczyciel`
--
ALTER TABLE `nauczyciel`
MODIFY `id_nauczyciela` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT dla tabeli `nieobecnosci`
--
ALTER TABLE `nieobecnosci`
MODIFY `id_nieobecnosci` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT dla tabeli `ocena`
--
ALTER TABLE `ocena`
MODIFY `id_oceny` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT dla tabeli `ocena_koncowa`
--
ALTER TABLE `ocena_koncowa`
MODIFY `id_ocenyK` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT dla tabeli `ogloszenia`
--
ALTER TABLE `ogloszenia`
MODIFY `id_ogloszenia` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT dla tabeli `opiekun`
--
ALTER TABLE `opiekun`
MODIFY `id_opiekuna` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT dla tabeli `plik`
--
ALTER TABLE `plik`
MODIFY `id_pliku` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `poziom_klasa`
--
ALTER TABLE `poziom_klasa`
MODIFY `id_klasy` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT dla tabeli `przedmiot`
--
ALTER TABLE `przedmiot`
MODIFY `id_przedmiotu` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `rok_szkolny`
--
ALTER TABLE `rok_szkolny`
MODIFY `id_rok` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT dla tabeli `uczen`
--
ALTER TABLE `uczen`
MODIFY `id_ucznia` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT dla tabeli `uczen_opiekun`
--
ALTER TABLE `uczen_opiekun`
MODIFY `id_relacja` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT dla tabeli `uwaga`
--
ALTER TABLE `uwaga`
MODIFY `id_uwagi` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT dla tabeli `wychowawca`
--
ALTER TABLE `wychowawca`
MODIFY `id_wychowawcy` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `admin`
--
ALTER TABLE `admin`
ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `login_data` (`id_user`);

--
-- Ograniczenia dla tabeli `nauczyciel`
--
ALTER TABLE `nauczyciel`
ADD CONSTRAINT `nauczyciel_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `login_data` (`id_user`);

--
-- Ograniczenia dla tabeli `opiekun`
--
ALTER TABLE `opiekun`
ADD CONSTRAINT `opiekun_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `login_data` (`id_user`);

--
-- Ograniczenia dla tabeli `uczen`
--
ALTER TABLE `uczen`
ADD CONSTRAINT `uczen_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `login_data` (`id_user`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
