package com.api.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.demo.model.Produto;
import com.api.demo.model.exception.ResourceNotFoundExcepition;
import com.api.demo.repository.ProdutoRepository;
import com.api.demo.shared.ProdutoDTO;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> obterTodos(){
        //Retorna uma lista de produto model.
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
            .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
            .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> obterPorId(Integer id){
        //Obtendo optional de produto pelo ID.
        Optional<Produto> produto = produtoRepository.findById(id);

        //Exception caso não encontre
        if(produto.isEmpty()){
            throw new ResourceNotFoundExcepition("Produto com id " + id + " não encontrado!");
        }

        //Convertendo em Produto DTO
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        //Retornando o Produto DTO
        return Optional.of(dto);
    }

    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
        produtoDto.setId(null);

        ModelMapper mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDto, Produto.class);

        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());

        return produtoDto;
    }

    public void deletar(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundExcepition("Não foi possível deletar o produto com o id " + id);
        }

        produtoRepository.deleteById(id);
    }

    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        // Passar o ID para o produtoDto
        produtoDto.setId(id);

        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter o DTO em um Produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Atualizar o produto no BD
        produtoRepository.save(produto);

        // Retornar o produtoDto atualizado
        return produtoDto;
    }

}
