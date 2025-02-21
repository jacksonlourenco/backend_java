package com.teste.teste.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.teste.model.Produto;
import com.teste.teste.model.exception.ResourceNotFoundException;
import com.teste.teste.repository.ProdutoRepository;
import com.teste.teste.shared.ProdutoDTO;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> findAll() {

        //Retorna uma Lista de produto model.
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

      public Optional<ProdutoDTO> findById(Integer id) {
        //Obtendo optional de produto pelo id
        Optional<Produto> produto = produtoRepository.findById(id);

        //Se não encontrar lança a exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }

        //Convertendo o meu optional de produto em ProdutoDTO
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        //Criando e retornando um optional de ProdutoDTO.
        return Optional.of(dto);
    }

    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {

        //Remove o id para conseguir fazer o cadastro
        produtoDto.setId(null);

        //Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //Converter o produtoDTO em produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        //Salvar no BD
        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());

        //Retorna o ProdutoDTO atualizado.
        return produtoDto;
    }

    public void deletar(Integer id){
        //Verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);

        //Condição para quebrar o código caso não exista o ID
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o produto com o id " + id);
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        //Passar o ID ProdutoDto
        produtoDto.setId(id);

        //Criar o objeto de map.
        ModelMapper mapper = new ModelMapper();

        //Converter o ProdutoDTO em Produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Retornar o produtoDto atualizado
        produtoRepository.save(produto);

        //Retornar o produtoDto atualizado
        return produtoDto;
    }
}
