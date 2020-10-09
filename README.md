# AlgDat2020Oblig2
2. Obligatoriske Arbeidskrav

Student nr: s344188
Navn: John Fredrik Dall

Jeg har riktignok beskrevet mye av det jeg har gjort og hvordan jeg har tenkt i commit-loggen. Men skal også beskrive i grove trekk her!

Når det kommer til warnings eksisterer det fire warnings i den fullførte oppgaven. Disse advarslene kommer på grunn av Objects.requireNotNull();
funksjonene som er brukt i noen av metodene. Advarslen sier at dersom det blir forsøkt med en nullverdi som parameter til en av disse metodene 
vil programmet kaste en NullPointException. Dette anser jeg som en del av hvordan programmet fungerer, og har latt stå. 

Oppgave 1:
De to første metodene her var relativt greit, ettersom de bare skal returnere uprosessert informasjon om listen.

Konstruktøren public DobbeltLenketListe(T[] a) 
- Først sørge for at listen ikke = null
- Finne riktig hode peker ved å finne først ikke-null verdi
- Så fylle de resterende verdiene inn etter hode (med forrige/neste-pekere)
- Så avslutte med å sette siste verdi som hale
- Tester OK!

Oppgave 2:
- Starte hver streng med en [
- traverse gjennom listen ved hjelp av neste-pekere
- avslutt med ]
- i omvedntString brukes samme prinsippet, men starter ved halen og bruker forrige-pekere
- Tester OK!


Oppgave 3:
- finnNode
- Starte med en hjelpeverdi
- Sjekke om indeks er i første eller andre halvdel av listen
- Starter enten på hale eller hode ut ifra indeksverdi
- Itererer så gjennom listen for å finne noden til indeks.
- hent og oppdater enkelt løst ved hjelp av finnNode(int indeks);
- brukte lang tid på å få subliste til å fungere, pga feil gjort i finnNode(int indeks); metoden tidligere.
- Tester oK!

Oppgave 4:
- int indeksTil(T verdi);
- Sjekke for null-verdi
- opprette hjelpe-node
- iterere over listen og sammenlikne
- returner indeks til funnet verdi, eller returner -1 dersom ikke funnet
- boolean inneholder(T verdi) kodes enkelt ved indeksTil(T verdi);
- return false ved -1.

Oppgave 5:
- Passe på null-verdi!
- kontrollere for rikig indeks
- Først legg inn på førsteplass, flytte hode-peker til ny node. Passe på hode.forrige = null.
- Så inn på siste plass, ny hale peker. Passe på hale.neste =null
- Til slutt legge ny node i mellom to andre
- Tester OK!

Oppgave 6: 
- Tok her utgangspunkt i unlink metodene jeg fant i LinkedList
- Måtte selvfølgeig tilpasse de siden unlink node og remove er delt ulike metoder
- Oppsto flere problemer her ettersom jeg fant feil i finnNode metoden. Mye debugging
- Fikk aldri effektivitetsdifferansen mellom metodene til å bli god nok, 
selv om jeg ikke brukte den ene i den andre.
- Mistenker at måten jeg finner verdien i fjern(T verdi) er lite effektiv, 
uten at jeg forstår hvordan jeg ellers skulle funnet den

Oppgave 8:
- Brukte de samme fremgangsmetodene fra ukesoppgavene
- Tester OK!
