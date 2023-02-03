package com.example.enchere.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "token")

public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "tableid")
    Long tableid;

    @Column(name = "tablename")
    String tablename;

    @Column(name = "tokenvalue")
    String value;

    @Column(name = "tokenexpirationdate")
    Timestamp expirationdate;

    @Column(name = "generationdate")
    Timestamp generationdate;

    public Token() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        setGenerationdate(now);
        now = null;
    }

    public static String sha1(String word) throws Exception {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(word.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }

    public void setValueToHash(String toHash) throws Exception {
        this.value = sha1(toHash);
    }

    public boolean isExpired() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (expirationdate != null && now.compareTo(expirationdate) >= 0) {
            now = null;
            return true;
        }
        return false;
    }
}
