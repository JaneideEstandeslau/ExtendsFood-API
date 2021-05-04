package com.digitalSystems.extendsfood.domain.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.NegocioException;
import com.digitalSystems.extendsfood.domain.exception.PedidoNaoEncontradoException;
import com.digitalSystems.extendsfood.domain.model.Endereco;
import com.digitalSystems.extendsfood.domain.model.FormaPagamento;
import com.digitalSystems.extendsfood.domain.model.ItemComplemento;
import com.digitalSystems.extendsfood.domain.model.Pedido;
import com.digitalSystems.extendsfood.domain.model.Produto;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.model.Usuario;
import com.digitalSystems.extendsfood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemComplementoService itemComplementoService;
	
	public Pedido emitir(Pedido pedido) {
		
		validarPedido(pedido);
		validarItens(pedido);
		
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
	    pedido.calcularValorTotal();

	    return pedidoRepository.save(pedido);
	}
	
	private void validarPedido(Pedido pedido) {
		
		Restaurante restaurante = restauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());
		Usuario usuario = usuarioService.buscarOuFalhar(pedido.getCliente().getId());
		Endereco endereco = enderecoService.buscarOuFalhar(usuario.buscarEnderecoAtivo().getId());
		
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setCliente(usuario);
		pedido.setEndereco(endereco);
		
		 if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
		        throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
		                formaPagamento.getDescricao()));
		    }
		
	}
	
	private void validarItens(Pedido pedido) {
		
		Set<ItemComplemento> itensComplemento = new HashSet<>();
		
	    pedido.getItensPedido().forEach(item -> {
	    	
	        Produto produto = produtoService.buscarProdutoDoRestaurante(
	                pedido.getRestaurante().getId(), item.getProduto().getId());
	        
	        item.setPedido(pedido);
	        item.setProduto(produto);
	        item.setPrecoUnitario(produto.getPreco());
	        
	        item.getItensComplemento().forEach(complemento -> {
	        	ItemComplemento itemComplemento = itemComplementoService.buscarOuFalhar(complemento.getId());
	        	
	        	itensComplemento.add(itemComplemento);
	        });
	        
	        item.setItensComplemento(itensComplemento);
	    });
	}
	
	public Pedido buscarOuFalhar(Long pedidoId) {
		return pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}
}
