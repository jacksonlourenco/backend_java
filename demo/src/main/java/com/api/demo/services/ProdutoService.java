package com.api.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.demo.model.Produto;
import com.api.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> obterTodos(){
        return produtoRepository.obterTodos();
    }

    public Optional<Produto> obterPorId(Integer id){
        return produtoRepository.obterPorId(id);
    }

    public Produto adicionar(Produto produto){
        return produtoRepository.adicionar(produto);
    }

    public void deletar(Integer id){
        produtoRepository.deletar(id);
    }

    public Produto atualizar(Integer id, Produto produto){
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }

}
