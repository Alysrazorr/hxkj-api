package com.hxkj.domain;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author dsd
 * @version 2018/6/21 11:32
 */
@Entity
@Table(name = "T_USER")
@Where(clause = "DOMAIN_STATE != 'DELETED'")
@SuppressWarnings("all")
public class User extends BaseDomain {

    @Column(name = "U_USERNAME", length = 20)
    private String username;

    @Column(name = "U_PASSWORD", length = 20)
    private String password;

    @Column(name = "U_NICKNAME", length = 20)
    private String nickname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
