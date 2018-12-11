# Arkkitehtuurikuvaus

## Rakenne

Ohjelman koodi on jaettu kolmeen pakkaukseen, ja pakkausrakenne on seuraava: 

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/pakkausrakenne_1.jpg">

Pakkaus nooran.giftwish.ui sisältää JavaFX:llä toteutetun käyttöliittymäkoodin.
Pakkaus nooran.giftwish.domain sisältää sovelluslogiikan koodin.
Pakkaus nooran.giftwish.dao sisältää tietojen tallennuksesta vastaavan koodin.

## Käyttöliittymä

Ohjelman käyttöliittymä sisältää neljä erillistä näkymää:
. sisäänkirjautuminen
. uuden käyttäjän luominen
. toiveen kirjaaminen/muokkaaminen
. toisten käyttäjien toiveiden näkeminen

Näkymät toteutetaan Scene-olioina.

## Sovelluslogiikka

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/luokkakaavio.jpg">

Sovelluksen logiikka koostuu luokista User, Gift ja MakeWishes. Sovelluksen toiminnallisuus tapahtuu luokassa MakeWishes.


<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/luokkakaavio.jpg">










<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/suhteita kuvaava luokkakaavio.jpg">

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/sekvenssikaavio.jpg">

