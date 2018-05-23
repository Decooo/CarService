package com.serwis.repository;

import com.serwis.entity.PartsOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jakub on 23.05.2018.
 */
@Repository
public interface PartsOrdersRepository extends JpaRepository<PartsOrders,Integer>{

	List<PartsOrders> findAllByIdOrders(int IdOrders);

}
