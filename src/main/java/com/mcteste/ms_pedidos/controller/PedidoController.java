package com.mcteste.ms_pedidos.controller;

import com.mcteste.ms_pedidos.model.Pedido;
import com.mcteste.ms_pedidos.service.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    PedidoService pedidoService;
    private final RabbitTemplate rabbitTemplate;
    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    public PedidoController(PedidoService pedidoService, RabbitTemplate rabbitTemplate) {
        this.pedidoService = pedidoService;
        this.rabbitTemplate = rabbitTemplate;
    }



    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido){
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
        rabbitTemplate.convertAndSend("",routingKey,pedidoSalvo);

        return "Descrição pedido: " + pedido.getDescricao();
    }

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.listarPedidos();
    }
}
