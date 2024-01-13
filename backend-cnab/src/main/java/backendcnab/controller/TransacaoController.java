package backendcnab.controller;

import backendcnab.model.Transacao;
import backendcnab.model.TransacaoReport;
import backendcnab.service.TransacaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping
    List<TransacaoReport> listAll(){
        return service.totaisTransacoesPorNomeDaLoja();
    }

}
