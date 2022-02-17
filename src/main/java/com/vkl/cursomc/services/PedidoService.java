package com.vkl.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.cursomc.domain.ItemPedido;
import com.vkl.cursomc.domain.PagamentoComBoleto;
import com.vkl.cursomc.domain.Pedido;
import com.vkl.cursomc.domain.enums.EstadoPagamento;
import com.vkl.cursomc.repositories.ItemPedidoRepository;
import com.vkl.cursomc.repositories.PagamentoRepository;
import com.vkl.cursomc.repositories.PedidoRepository;
import com.vkl.cursomc.repositories.ProdutoRepository;
import com.vkl.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService; 
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido i : obj.getItens()) {
			i.setDesconto(0.0);
			i.setPrice(produtoRepository.findById(i.getProduto().getId()).get().getPrice());
			i.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}
