package com.serwis.repository;

import com.serwis.entity.TypeParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 18.05.2018.
 */
@Repository
public interface TypePartsRepository extends JpaRepository<TypeParts,Integer>{
}
