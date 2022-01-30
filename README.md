# Jumărea Ștefan Dorin - 321CC

Consider că tema a fost dificilă prin complexitate(mult cod de scris independent ce trebuie legat la final), nu prin cerințe grele sau implementări tricky.

Timp de lucru, ~2 săptămâni, ~3+ h / zi.

# Compilare și testare

Tema nu a fost făcută folosind un IDE, directorul `PROIECT` este doar directorul de lucru
deschis și compilat în Intellij.
**NU** recomand folosirea lui.

Toată tema a fost lucrată de la început în directorul `project`.

Scripturile compile.sh și run.sh din directorul `project` complilează sursele și rulează testul.
Folosiți comanda `bash compile.sh` pentru compilare.
Folosiți comanda `bash run.sh` pentru a rula programul.

Există o metodă de clearScreen ce funcționează doar în terminal, nu și în IDE, care va curăța
ecranul pentru o mutare nouă. Metoda doar face outputul mai lizibil și nu influențează cu nimic
jocul dacă acesta se rulează dintr-un IDE.

# Modul de implementare și desfășurare

În fișierul sources/Test.java este implementată testarea aplicației.
Pentru rularea scenariului de test harcodat cerut se va lăsa comentată linia 14.
**Pentru rularea jocului în modul complet de funcționare se va comenta linia 13
și se va decomenta linia 14.**

În modul de joc hardcodat, lupta cu inamicul nu se va termina înainte ca sa se utilizeze cele 2 poțiuni și toate tipurile de atac, chiar dacă viața inamicului va ajunge sub 0.
Daca viața jucătorului va ajunge sub 0 în timpul jocului harcodat, acesta se va regenera.

## Rularea jocului în modul de funcționare complet are următorul flow:
* Se încarcă conturile aflate în fișierul JSON (./sources/json-test-files/accounts.json).
* Se alege indexul contului dorit.
* **Se cere parola contului selectat**. Dacă parola este greșită, se oprește execuția.
* Se vor încărca și printa caracterele deținute de jucător.
* Se alege indexul caracterului dorit.
* Se va printa mapa, o poveste specifică celulei curente și opțiunile.
* În cazul în care se alege o mutare, se va realiza mutarea.
* În cazul în care se alege folosirea unei "Potion" se va alege indexul acesteia.

In cazul în care caracterul descopera o celulă de tip:
* Shop - se printează lista de potions valabile și se poate cumpăra una.
* Enemy - caracterul și inamicul vor avea pe rând ture de atac/folosire potion
* Finish - se adaugă 50 la experiența caracterului. Daca experiența depășește 100 de adauga un level.

După terminarea unui joc, noile date vor fi salvate în fișierul ./sources/json-test-files/accounts.json.

Toate metodele din clasa Game care încep cu secvența `__hard__` sunt metode folosite la rularea
exemplului harcodat.
Tot restul metodelor sunt folosite la jocul complet.

Dessing patternurile folosite sunt:
* Singleton pentru clasa Grid
* Factory pentru caracterele jocului

În clasa Grid, constructorii sunt declarați de tip `private`, iar metoda statică
`getMap()` verifică dacă a fost instanțiat vreun obiect de tip Grid, dacă nu apelează
un constructor și returnează obiectul cerut.

În clasa AccountFactory, metoda statică `factory` returnează un caracter cu tipul dat ca parametru.

# Detalii de implementare

* Pentru rularea completă a jocului, se apeleaza metoda `run` din clasa Game.
* Se intră într-o buclă infinită în care se tratează fiecare tip de celulă separat.
* Pentru fiecare tip de celulă se afișează una din poveștile încărcate din fișierul JSON.
* Pentru celulele de tip ENEMY / SHOP se apelează metodele `fight`, respectiv `buyPotion`.
* Pentru celulele de tip EMPTY se face o mutare.
* Pentru celula FINISH se crește experiența caracterului, se salvează progresul și se iese din buclă.

## În interiorul metodei `fight`
* Se crează o buclă cât timp caracterul și inamicul au viața > 0.
* În interiotul buclei, se apelează metodele succesiv `attack` și `enemyAttack`.
* Metoda `attack` lasă caracterul să aleagă tipul de atac, sau dacă utilizeaza o Potion.
* Metoda `enemyAttack` va ataca caracterul cu un tip de atac generat aleator (50% șansa de atac normal, 50/3 % șansă de atac special).
* La finalul luptei, dacă caracterul învinge inamicul va primi 10 coins.

## În interiorul metodei `buyPotion`
* Se afișează numarul de coins din inventarul caracterului.
* Jucătorul alege indexul Potion ului dorit.
* Se apeleaza metoda `buyPotion` din clasa Character care verifica dacă obiectul poate fi adăugat la inventar.

* Pentru folosirea unei Potiuni, se apelează metoda `usePotion`.
* Metoda afișează toate obiectele de tip Potion din inventarul caracterului.
* Jucătorul alege indexul obiectului dorit.
* Se apelează metoda potionEffect din cadrul clasei Potion.

*Pentru a se salva progresul jucătorului, se încarcă fișierul JSON într-un string, se modifică caracterul cu care s-a jucat jocul curent, apoi se pune stringul înapoi în fișier.*
După fiecare joc contul folosit cel mai recent va apărea primul în la alegerea contului, lafel și caracterul folosit ultima oară.

În afara impelmentărilor descrise mai sus, restul claselor și metodelor au fost implementate după cerința dată.
