package com.example.enchere.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.enchere.model.V_Enchere;

@Repository
public interface V_EnchereRepository extends CrudRepository<V_Enchere, Long>{
    
}
