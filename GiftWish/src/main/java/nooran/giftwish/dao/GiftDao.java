/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nooran.giftwish.dao;

import java.util.List;
import nooran.giftwish.domain.Gift;

/**
 *
 * Lahjatoiveiden tallentamisesta vastaava rajapintaluokka
 */
public interface GiftDao {
    Gift create(Gift gift) throws Exception;

    List<Gift> getAll();

    void setDone(int id) throws Exception;
    
    void remake(int id, String name, String content) throws Exception;
    
   

    
}
