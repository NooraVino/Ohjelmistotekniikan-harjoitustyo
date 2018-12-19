# Testausdokumentti

Ohjelmaa on testattu sekä JUnitilla että manuaalisesti.

## Sovelluslogiikka
Tärkeimmät testit ovat integraatiotestit MakeWishesGiftTest ja MakeWishesUserTest jotka testaavat sovelluslogiikan toimivuutta. Testejä varten on luotu luokat FakeGiftDao ja FakeUserDao. Myös luokat User ja Gift ovat testattu.

## Dao-luokat

Myös Dao-luokkien toiminnallisuus on testattu.

## Testauskattavuus
Käyttöliittymä ei ole mukana testikattavuudessa. Sovelluksen testauksen rivikattavuus on 80% ja haarautumakattavuus 73%.

<img src="https://github.com/NooraVino/ot-harjoitustyo/blob/master/GiftWish/Dokumentointi/kuvat/testikattavuus.png" width="700">


## Toiminnallisuudet

Toiminnallisuudet on käyty läpi ja käyttäjätunnuksen luomiselle on määritelty ehdot. Toiveen voi tallentaa ilman nimeä tai sisältöä. 
