package com.serwis.repository;

import com.serwis.entity.IssuedParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 31.05.2018.
 */
@Repository
public interface IssuedPartsRepository extends JpaRepository<IssuedParts,Integer>{
}
