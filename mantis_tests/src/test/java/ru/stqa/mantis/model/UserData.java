package ru.stqa.mantis.model;

public record UserData(String realName,String email, String username, String password,String accessLevel, Boolean enabled, Boolean _protected) {

    public UserData() {
        this("", "","", "",  "reporter",true, false);
    }

    public UserData withRealName(String realName) {
       return new UserData(realName,this.email, this.username, this.password,  this.accessLevel,this.enabled, this._protected);
    }
    public UserData withUserName(String username) {
        return new UserData(this.realName, this.email, username, this.password,  this.accessLevel,this.enabled, this._protected);
    }

    public UserData withEmail(String email) {
        return new UserData(this.realName, email, this.username, this.password,  this.accessLevel,this.enabled, this._protected);
    }

    public UserData withPassword(String password) {
        return new UserData(this.realName, this.email,this.username, password,  this.accessLevel,this.enabled, this._protected);
    }
    public UserData withAccessLevel(String accessLevel) {
        return new UserData(this.realName,this.email, this.username,  this.password,  accessLevel,this.enabled, this._protected);
    }

    public UserData withEnabled(Boolean enabled) {
        return new UserData(this.realName,  this.email, this.username, this.password,  this.accessLevel, enabled, this._protected);
    }
    public UserData withProtected(Boolean _protected) {
        return new UserData(this.realName,  this.email, this.username, this.password,  this.accessLevel,this.enabled, _protected);
    }
}
