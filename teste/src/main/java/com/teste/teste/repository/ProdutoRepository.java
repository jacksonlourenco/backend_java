package com.teste.teste.repository;

import org.springframework.stereotype.Repository;
import com.teste.teste.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
