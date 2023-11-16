package com.produto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produto.entities.Produto;
import com.produto.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Produto", description = "API REST DE GERENCIAMENTO DO PRODUTO")
@RestController
@RequestMapping("/Produto")
@CrossOrigin(origins = "*")
public class ProdutoController {

	private final ProdutoService ProdutoService;

	@Autowired
	public ProdutoController(ProdutoService ProdutoService) {
		this.ProdutoService = ProdutoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza Produto por ID")
	public ResponseEntity<Produto> buscaProdutoControlId(@PathVariable Long id) {
		Produto Produto = ProdutoService.getProdutoById(id);
		if (Produto != null) {
			return ResponseEntity.ok(Produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "apresenta todos os Produtos")
	public ResponseEntity<List<Produto>> buscaTodasLigacoesControl() {
		List<Produto> Produto = ProdutoService.getAllProdutos();
		return ResponseEntity.ok(Produto);
	}

	@PostMapping
	@Operation(summary = "cadastra os Produtos")
	public ResponseEntity<Produto> saveProdutoControl(@RequestBody @Valid Produto Produto) {
		Produto saveProduto = ProdutoService.saveProduto(Produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveProduto);
	}

	@PutMapping("/{id}")
	@Operation(summary = "altera os Produtos")
	public ResponseEntity<Produto> alteraProdutoControl(@PathVariable Long id, @RequestBody @Valid Produto Produto) {
		Produto alteraProduto = ProdutoService.changeProduto(id, Produto);

		if (alteraProduto != null) {
			return ResponseEntity.ok(Produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "deleta os Produtos")
	public ResponseEntity<String> deleteProdutoControl(@PathVariable Long id) {
		boolean delete = ProdutoService.deleteProduto(id);
		if (delete) {
			return ResponseEntity.ok().body("O produto foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}