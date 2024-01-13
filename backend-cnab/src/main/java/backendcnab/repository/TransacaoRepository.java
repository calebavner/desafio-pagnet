package backendcnab.repository;

import backendcnab.model.Transacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

    //select * from transacao order by nome_loja asc, id desc
    List<Transacao> findAllByOrderByNomeDaLojaAscIdDesc();
}
