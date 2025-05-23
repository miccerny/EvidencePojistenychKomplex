# Webová aplikace pro evidenci pojištěnců a jejich pojištění

> Aktuální verze projektu je dostupná ve větvi `refactoring`, kde proběhly poslední úpravy a čistší struktura kódu.

## Použité technologie
- **Java 19** - hlavní jazyk projektu
- **MySQL** - relační databáze pro ukládání dat
- **Spring boot** - backendový framwork
- **Thymeleaf** - šablonovací engine pro generování HTML
- **Boostrap** - pro stylování a responzivní design
- **HTML/CSS** - pro strukturu a vzhled frontendu

## Aplikace obsahuje kompletní správu (CRUD) pojištěných (např. "Jan Novák") a jejich pojištění (např. "pojištění bytu"):
- Vytvoření pojištěného
- Vytvoření pojištění
- Zobrazení detailu pojištěného včetně jeho pojištění
- Zobrazení detailu pojištění
- Zobrazení seznamu pojištěných
- Odstranění pojištěného včetně všech jeho pojištění
- Odstranění pojištění (pouze s přihlášením administrátor)
- Editace pojištěného (pouze s přihlášením administrátor)
- Editace pojištění pojištěného (pouze s přihlášením administrátor)
- Podporuje rozlišení (uživatel, administrátor)
- Entity jsou uloženém v SQL databázi

## Jak spustit projekt
1. Instalace MySQL. Uprav v souboru **application.properties** přihlašovací údaje včetně portu pro spuštění
2. Naklonuj repozitář
   ```bash
   git clone https://github.com/miccerny/EvidencePojistenychKomplex.git

3. Otevři projekt v IDE (např. IntelliJ IDEA)
4. **mvn spring-boot:run** nebo spusť  **InsuranceRecordsApplication.java** jako Spring Boot app
5. Otevři v prohlížeči **http://localhost:8080**

## Ukázky aplikace
### Evidence Pojištěných bez přihlášeného uživatele(anonymní)
![Popis obrázku](https://github.com/miccerny/EvidencePojistenychKomplex/blob/5bfe89f5ad3c26683782a22e4209f664308a7174/screenshot/EvidencePojistenych.png)

### Evidence pojištění konkrétního pojištěnce bez přihlášení uživatele (anonymní)
![Popis obrázku](https://github.com/miccerny/EvidencePojistenychKomplex/blob/master/screenshot/Sn%C3%ADmek%20obrazovky%20z%202025-03-15%2013-01-17.png)

### Evidence pojištěnců s přihlášením jako administrátor
![Popis obrázku](https://github.com/miccerny/EvidencePojistenychKomplex/blob/master/screenshot/Sn%C3%ADmek%20obrazovky%20z%202025-03-15%2013-01-44.png)

### Editace pojištěného s přihlášením jako administrátor
![Popis obrázku](https://github.com/miccerny/EvidencePojistenychKomplex/blob/master/screenshot/Sn%C3%ADmek%20obrazovky%20z%202025-03-15%2013-01-55.png)

### Zobrazení detailu pojištění s přihlášením jako administrátor
![Popis obrázku](https://github.com/miccerny/EvidencePojistenychKomplex/blob/master/screenshot/Sn%C3%ADmek%20obrazovky%20z%202025-03-15%2013-02-06.png)

## Poznámky
### Možné rozšíření aplikace o následující:
Přidání pojistné události
Vazba mezi uživatelem a pojištěncem pro správu pojištění
Správa uživatelů pod adminstrátorským účtem ve webovém rozhraní
