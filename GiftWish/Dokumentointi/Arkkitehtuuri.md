# Arkkitehtuurikuvaus

## Rakenne

Ohjelman koodi on jaettu kolmeen pakkaukseen, ja pakkausrakenne on seuraava: 

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/pakkausrakenne_1.jpg" width="160">

Pakkaus nooran.giftwish.ui sisältää JavaFX:llä toteutetun käyttöliittymäkoodin.
Pakkaus nooran.giftwish.domain sisältää sovelluslogiikan koodin.
Pakkaus nooran.giftwish.dao sisältää tietojen tallennuksesta vastaavan koodin.

## Käyttöliittymä

Ohjelman käyttöliittymä sisältää neljä erillistä näkymää:
..* sisäänkirjautuminen
..* uuden käyttäjän luominen
..* toiveen kirjaaminen/muokkaaminen
..* toisten käyttäjien toiveiden näkeminen

Näkymät toteutetaan Scene-olioina.

## Sovelluslogiikka

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/luokkakaavio.jpg" width="160" >

Sovelluksen logiikka koostuu luokista User, Gift ja MakeWishes. Sovelluksen toiminnallisuus tapahtuu luokassa MakeWishes.


<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/luokkakaavio.jpg" width="160">




## Tietojen pysyväistallennus

Pakkauksen noora.giftwish.dao luokat vastaavata tietojen pysyväistallennuksesta.



## Päätoiminnallisuudet

### Käyttäjän kirjautuminen
### Uuden käyttäjän luominen
### Toiveiden luominen/hallinnointi

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/sekvenssikaavio.jpg" width="160">

