

# Ohjelmistotekniikka 2018  
## Harjoitustyö: GiftWish-sovellus

Harjoitustyönä teen sovelluksen johon voidaan koota koko lähisuvun joululahjatoiveet ja jossa kaikki voivat yhteisesti suunnitella kuka hankkii kenelle ja mitä lahjaksi. Jokainen voi kirjata omat toiveensa ja kaikki muut käyttäjät voivat kommentoida ja ideoida lahjanhankintaa yksittäisen kyseisen käyttäjännäkemättä.

## Dokumentaatio

[määrittelydokumentti](https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/M%C3%A4%C3%A4rittelydokumentti.md)

[työaikakirjanpito](https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/Ty%C3%B6aikakirjanpito.md)

[arkkitehtuuri](https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/arkkitehtuuri/pakkauskaavio.jpg)


## Releaset

[Viikko 5](https://github.com/NooraVino/ot-harjoitustyo/releases)

## Komentorivitoiminnot

### Testaus
Testit suoritetaan komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla:
```
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### Suoritettavan jarin generointi
Komento:
```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston: 
### JavaDoc

### Checkstyle
Tiedostoon checkstyle.xml määritellyt tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html
