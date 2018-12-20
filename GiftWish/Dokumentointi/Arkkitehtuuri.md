# Arkkitehtuurikuvaus

## Rakenne

Ohjelman koodi on jaettu kolmeen pakkaukseen, ja pakkausrakenne on seuraava: 

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/pakkausrakenne_1.jpg" width="200">

Pakkaus nooran.giftwish.ui sisältää JavaFX:llä toteutetun käyttöliittymäkoodin.
Pakkaus nooran.giftwish.domain sisältää sovelluslogiikan koodin.
Pakkaus nooran.giftwish.dao sisältää tietojen tallennuksesta vastaavan koodin.

## Käyttöliittymä

Ohjelman käyttöliittymä sisältää viisi erillistä näkymää:
* sisäänkirjautuminen
* uuden käyttäjän luominen
* toiveen kirjaaminen /poistaminen
* toiveen muokkaaminen
* toisten käyttäjien toiveiden näkeminen

Näkymät toteutetaan Scene-olioina.

## Sovelluslogiikka

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/luokkakaavio.jpg" width="300" >

Sovelluksen logiikka koostuu luokista User, Gift ja MakeWishes. Sovelluksen toiminnallisuus tapahtuu luokassa MakeWishes.


<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/luokkakaavio.jpg" width="300">


## Tietojen pysyväistallennus

Pakkauksen noora.giftwish.dao luokat vastaavata tietojen pysyväistallennuksesta.

## Päätoiminnallisuudet

### Käyttäjän kirjautuminen

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/toiminnallisuus.jpg" width="300" >

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/sekvenssikaavio.jpg" width="200">

