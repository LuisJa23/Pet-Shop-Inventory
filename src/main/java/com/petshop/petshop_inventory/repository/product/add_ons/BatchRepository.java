package com.petshop.petshop_inventory.repository.product.add_ons;

import com.petshop.petshop_inventory.model.product.add_ons.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    Batch findByProductId(Long productId);


}
