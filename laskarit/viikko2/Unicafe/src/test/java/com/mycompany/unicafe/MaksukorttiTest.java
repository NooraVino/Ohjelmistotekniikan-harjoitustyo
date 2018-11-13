package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void alkuSaldoOikein() {
        String vastaus = kortti.toString();
        assertEquals("saldo: 0.10", vastaus);
    }

    @Test
    public void RahanLatausKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(25);
        assertEquals("saldo: 0.35", kortti.toString());
    }

    @Test
    public void otaRahaaVahentaaSaldoaOikein() {
        kortti.lataaRahaa(25);
        kortti.otaRahaa(10);
        assertEquals("saldo: 0.25", kortti.toString());
    }

    @Test
    public void otaRahaaEiVahennaNegatiiviseksi() {
        kortti.otaRahaa(15);
        assertEquals("saldo: 10.00", kortti.toString());
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }
}
