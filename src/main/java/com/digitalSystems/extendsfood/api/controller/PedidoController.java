package com.digitalSystems.extendsfood.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.PedidoModelAssembler;
import com.digitalSystems.extendsfood.api.assembler.PedidoResumoModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.PedidoInputDisassembler;
import com.digitalSystems.extendsfood.api.model.PedidoModel;
import com.digitalSystems.extendsfood.api.model.PedidoResumoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.PedidoInput;
import com.digitalSystems.extendsfood.core.data.PageableTranslator;
import com.digitalSystems.extendsfood.domain.exception.EntidadeNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.exception.NegocioException;
import com.digitalSystems.extendsfood.domain.filter.PedidoFilter;
import com.digitalSystems.extendsfood.domain.model.Pedido;
import com.digitalSystems.extendsfood.domain.model.Usuario;
import com.digitalSystems.extendsfood.domain.model.dto.PedidoUsuario;
import com.digitalSystems.extendsfood.domain.repository.PedidoRepository;
import com.digitalSystems.extendsfood.domain.service.EmissaoPedidoService;
import com.digitalSystems.extendsfood.infrastructure.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private PedidoModelAssembler pedidoAssembler;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoresumoAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@GetMapping
    public Page<PedidoResumoModel> pesuisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
		
		pageable = traduzirPageable(pageable);
		
        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.filtrarPedidos(filtro), pageable);
        
        List<PedidoResumoModel> pedidosResumoModel = pedidoresumoAssembler.toCollectionModel(pedidosPage.getContent());
        
        Page<PedidoResumoModel> pedidosResumoModelPage = new PageImpl<>(
                pedidosResumoModel, pageable, pedidosPage.getTotalElements());
        
        return pedidosResumoModelPage;
    }
	
	@GetMapping("/usuario")
	public List<PedidoUsuario> buscarPedidosUsuario(){
		
		Usuario cliente = new Usuario();
		cliente.setId(1L);
		
		return  emissaoPedidoService.buscarPedidosUsuario(cliente);		
	}
    
    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
        
        return pedidoAssembler.toModel(pedido);
    }   
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);

            return pedidoAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
    
    private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"valorTotal", "valorTotal",
				"restaurante.nome", "restaurante.nome",
				"status", "status"
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
