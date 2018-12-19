# Käyttöohje

lataa tiedosto [giftwish.jar]()

## Ohjelman käynnistäminen

Ohjelman voi käynnistää komennolla:

```
java -jar giftwish.jar
```

## Kirjautuminen
Aloitusnäkymästä voit joko kirjautua sisään olemassaolevilla tunnuksilla tai siirtyä luomaan uutta tunnusta. Ohjelma ilmoittaa sinulle jos käyttäjätunnus tai salasana eivät ole oikein.
Syötettyäsi nimen ja salasanan pääset kirjautumaan sisään painamalla __kirjaudu sisään__.
Uuden käyttäjätunnuksen pääset luomaan painamalla __luo uusi__.

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/kirjautuminen.png" width="300">

## Uuden käyttäjän lisääminen
Tässä näkymässä voit luoda uuden käyttäjätunnuksen. Käyttäjätunnuksen ja salasanan pitää olla yli kolme merkkiä pitkiä ja ohjelma ilmoittaa sinulle mikäli tunnuksesi on liian lyhyt tai samananiminen käyttäjä on jo olemassa. Uuden käyttäjän voit luoda painamalla __luo uusi__. Jos tunnuksen luonti onnistuu nii ohjelma siirtyy pääsivulle.

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/uusi_kayttaja.png" width="300">

## Uuden toiveen lisääminen ja toiveen poistaminen
Tässä näkymässä on listattuna kaikki toiveesi. Voit halutessasi poistaa toiveen painamalla _poista_. Pääset muokkaamaan toivettasi painamalla __muokkaa__. Voit poistua sovelluksesta vasta kirjauduttuasi ulos painamalla __kirjaudu ulos__.

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/pääsivu.png" width="300">

## Toiveen muokkaaminen
Tässä näkymässä voit muokata toivettasi. Muokkaa tekstikenttiä ja paina sen jälkeen __tallenna__. Ohjelma siirtyy tämän jälkeen pääsivulle. Jos haluat poistua tallentamatta muutoksia paina __älä tallenna__ jolloin ohjelma siirtyy tallentamatta muutoksia pääsivulle.Voit myös kirjautua ulos painamalla __kirjaudu ulos__. Tällöin tekemäsi muutokset tallentuvat automaattisesti__.

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/muokkaus_sivu.png" width="300">

## Muiden käyttäjien toiveiden tarkastelu
Tässä näkymässä näet muiden käyttäjien toiveet. Tämä sivu jää kesken eikä sillä voi tehdä muuta kuin palata takaisin.

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/muiden_toiveet.png" width="300">

