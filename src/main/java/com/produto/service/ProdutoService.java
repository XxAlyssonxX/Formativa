package com.produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produto.entities.Produto;
import com.produto.repository.ProdutoRepository;



@Service
public class ProdutoService {
	private final ProdutoRepository ProdutoRepository;
	

	@Autowired
	public ProdutoService(ProdutoRepository ProdutoRepository) {
		this.ProdutoRepository = ProdutoRepository;
	}

	public List<Produto> getAllProdutos() {
		return ProdutoRepository.findAll();
	}

	public Produto getProdutoById(Long id) {
		Optional<Produto> Produto = ProdutoRepository.findById(id);
		return Produto.orElse(null);
	}

	public Produto saveProduto(Produto Produto) {
		return ProdutoRepository.save(Produto);
	}

	public Produto changeProduto(Long id, Produto changeU) {
		Optional<Produto> existeProduto = ProdutoRepository.findById(id);
		if (existeProduto.isPresent()) {
			changeU.setId(id);
			return ProdutoRepository.save(changeU);
		}
		return null;
	}

	public boolean deleteProduto(Long id) {
		Optional<Produto> existeProduto = ProdutoRepository.findById(id);
		if (existeProduto.isPresent()) {
			ProdutoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
