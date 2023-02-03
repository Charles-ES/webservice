package com.example.enchere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enchere.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByValue(String value);

    Token findByTableidAndTablenameAndExpirationdateIsNull(Long tableid, String tablename);

    Token findByValueAndExpirationdateIsNull(String value);

    Token findByTablenameAndValueAndExpirationdateIsNull(String string, String value);

}
