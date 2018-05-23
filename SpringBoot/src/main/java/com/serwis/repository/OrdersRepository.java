package com.serwis.repository;

import com.serwis.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 23.05.2018.
 */
@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer>{
}
