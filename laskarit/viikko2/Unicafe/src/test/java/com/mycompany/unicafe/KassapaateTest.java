/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vino
 */
public class KassapaateTest {
    Kassapaate kassapaate;
    
    
    public KassapaateTest() {
        
    }
   
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
       
    }
    @Test
    public void  alkuOikea() {
        int rahaa = kassapaate.kassassaRahaa();
        assertEquals(100, rahaa);
        
    }
    
  

  
}
