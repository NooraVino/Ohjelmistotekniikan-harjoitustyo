

# Ohjelmistotekniikka 2018  
## Harjoitustyö: GiftWish-sovellus

Harjoitustyönä teen sovelluksen johon voidaan koota joululahjatoiveita. Jokainen käyttäjä voi kirjata omat toiveensa, poistaa ja muokata niitä. Tarkoituksena oli, että sovelluksessa voi myös nähdä muiden toiveet ja myös kommentoida niitä, mutta tältä osin sovellus jäi keskeneräiseksi. 

## Dokumentaatio
[Arkkitehtuuri](https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/Arkkitehtuuri.md) 

[Käyttöohje](https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/K%C3%A4ytt%C3%B6ohje.md)

[Määrittelydokumentti](https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/M%C3%A4%C3%A4rittelydokumentti.md)

[Testausdokumentti](https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/Testausdokumentti.md)

[Työaikakirjanpito](https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/Ty%C3%B6aikakirjanpito.md)


## Releaset 

[Loppupalautus](https://github.com/NooraVino/ot-harjoitustyo/releases/tag/Loppupalautus1)

[Viikko 5](https://github.com/NooraVino/ot-harjoitustyo/releases)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla:
```
mvn test
```
Testikattavuusraportti luodaan komennolla:
```
mvn jacoco:report
```
Kattavuusraportti löytyy polusta:  target/site/jacoco/index.html

### Suoritettavan jarin generointi

Komento:
```
mvn package
```
generoi jar-tiedoston. 

### JavaDoc
generoi javaDocit:
```
mvn javadoc:javadoc
```

JavaDocit polusta: target/site/apidocs/index.html
### Checkstyle
Checkstyle tyylitarkistukset suoritetaan komennolla:

```
mvn jxr:jxr checkstyle:checkstyle
```
Virheilmoitukset löytyvät polusta: target/site/checkstyle.html
