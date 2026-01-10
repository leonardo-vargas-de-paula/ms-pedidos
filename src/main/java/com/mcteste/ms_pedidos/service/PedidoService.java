package com.mcteste.ms_pedidos.service;

import com.mcteste.ms_pedidos.model.ItemPedido;
import com.mcteste.ms_pedidos.model.Pedido;
import com.mcteste.ms_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido salvarPedido(Pedido pedido){
        if (pedido.getItens() != null){
            for(ItemPedido item: pedido.getItens()){
                item.setPedido(pedido);
            }

        }
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

}
