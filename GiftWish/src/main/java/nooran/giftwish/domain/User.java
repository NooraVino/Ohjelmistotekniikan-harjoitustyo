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
public class User {

    private String userName;
    private String passWord;

    public User() {
        this.userName = "";
        this.passWord = "";

    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String setUseName(String name) {
        return this.userName;
    }

    public String getPassWord(String passWord) {
        return this.passWord;
    }

}
