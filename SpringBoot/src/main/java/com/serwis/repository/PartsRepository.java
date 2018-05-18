package com.serwis.repository;

import com.serwis.entity.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 18.05.2018.
 */
@Repository
public interface PartsRepository extends JpaRepository<Parts,Integer>{
}
