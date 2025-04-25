# Študentská databáza – Java projekt

## Popis

Tento projekt je konzolová aplikácia v jazyku Java, ktorá spravuje databázu študentov technickej univerzity. Umožňuje evidenciu študentov, manipuláciu so známkami a prácu s dátami cez SQL databázu.

## Funkcionalita

Aplikácia umožňuje:

- Pridávanie nových študentov podľa zvoleného odboru (Telekomunikácie alebo Kyberbezpečnosť)
- Zaznamenávanie známok študentov (1 – 5)
- Výpočet študijného priemeru
- Odstránenie študenta podľa ID
- Vyhľadanie študenta a výpis jeho údajov
- Spustenie špeciálnej dovednosti podľa odboru:
  - Morseova abeceda pre Telekomunikácie
  - Hashovanie mena pre Kyberbezpečnosť
- Abecedné zoradenie študentov v rámci odboru
- Výpočet priemeru známok pre každý odbor
- Výpis počtu študentov v každej skupine
- Uloženie a načítanie jednotlivých študentov zo súboru
- Uloženie všetkých dát do SQL databázy pri ukončení
- Načítanie všetkých dát zo SQL databázy pri spustení

## Technické detaily

- Objektovo orientované programovanie (OOP)
- Využitie abstraktnej triedy `Student`
- Dve dedičné triedy:
  - `TelekomunikacieStudent`
  - `KyberbezpecnostStudent`
- Dynamická dátová štruktúra: `ArrayList<Student>`
- Práca so súbormi (ukladanie/načítanie objektov)
- SQLite databáza na ukladanie dát pri ukončení programu

## Použité technológie

- Java
- SQLite (prístup cez JDBC)
- Eclipse IDE

## Spustenie

1. Skontroluj, že máš SQLite JDBC driver vo svojom classpath.
2. Spusti triedu `Main`.
3. Postupuj podľa výpisu v konzole.

## Poznámka

Databázový súbor `students.db` sa vytvára automaticky a obsahuje tabuľky študentov a známok. SQL databáza sa načítava pri spustení a ukladá pri ukončení programu.
