package backendcnab.service;

import backendcnab.model.TransacaoReport;
import backendcnab.model.enums.TipoTransacao;
import backendcnab.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransacaoService {

    private final TransacaoRepository repo;

    public TransacaoService(TransacaoRepository repo) {
        this.repo = repo;
    }

    public List<TransacaoReport> totaisTransacoesPorNomeDaLoja() {

        var transacoes = repo.findAllByOrderByNomeDaLojaAscIdDesc();

        Map<String, TransacaoReport> reportMap = new LinkedHashMap<>();
        transacoes.forEach(transacao -> {
            String nomeDaLoja = transacao.nomeDaLoja();
            BigDecimal valor = transacao.valor();

            reportMap.compute(nomeDaLoja, (key, existingReport) -> {
               var report = (existingReport != null) ? existingReport :
               new TransacaoReport(key, BigDecimal.ZERO, new ArrayList<>());

               transacao.withValor(valor);
               return report.addTotal(valor).addTransacao(transacao);
            });
        });


        return new ArrayList<>(reportMap.values());
    }

}
