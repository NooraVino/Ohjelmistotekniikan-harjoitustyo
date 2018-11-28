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

    public User(String username, String password) {
        this.userName = username;
        this.passWord = password;

    }
    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.passWord;
    }

//    public void setUserName(String name) {
//        this.userName = name;
//    }
//
//    public void setPassWord(String passWord) {
//        this.passWord = passWord;
//    }
//
//  
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return this.userName.equals(other.userName);
    }

}
