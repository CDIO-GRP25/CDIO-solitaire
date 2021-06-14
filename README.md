# CDIO-solitaire
Noter til udvikling af 7-kabale app:

- Der skal kunne rykkes hele bunker - evt ved brug af en recursion, tag pos[4] ryk 4 så 3 osv.

- Hvordan rykker vi ting til og fra drawpile, da den ikke skal kunne rykkes bagvendt. evt. ved at rykke baglæns i decket. Eller oprette en counter der holder styr på hvor i deckt vi er, og som så rykker frem og tilbage alt efter hvor i decket vi er 


- Evt. find en måde at skrive ranks op så det ikke bare er int, så det gør det mere expressive.


Regler der skal implementeres:

Der er kun en konge der skal kunne rykkes til en tom bunke. (fixed)

Der skal vises nye kort når der bliver rykket (fixed)

Der skal tjekkes hvorvidt om kort rent faktisk er tilladt at ligge ovenpå hinanden. (fixed)

Plan for setup til merge med app/api
buildpiles:
    deal alle cards ud som før, men alle er "hidden".
    StateDTO aflæses og alle topcards som er hidden fjernes og der lægges nye kort ovenpå.
    hvis ryk revealer et kort skal det stadig være hidden, derefter fjernes og erstattes med StateDTO-kort.
        Altså bare iterate og der hvor topcard er hidden, skal der erstattes med DTO-kortet
deck:
    tomt fra start.
    tilføj DTO-kort hvis tom eller hvis topcard != DTO-kort
suits:
    tomt sjovt nok
    one way traffic, så ingen brug for opdateringer fra billeder egentlig.