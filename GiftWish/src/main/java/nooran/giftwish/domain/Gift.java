/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nooran.giftwish.domain;

/**
 *
 * @author vino
 */
public class Gift {
    private String name;
    private String content;
    
    public Gift() {
        this.name = "";
        this.content = "";
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void writeContent(String content) {
        this.content = content;
    }

    public String getName(String name) {
        return this.name;
    }

    public String getContent(String content) {
        return this.content;
    }

    
}
