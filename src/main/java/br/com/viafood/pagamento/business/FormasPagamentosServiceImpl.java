/**
 * 
 */
package br.com.viafood.pagamento.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.viafood.exceptions.exception.EntidadeComDependencia;
import br.com.viafood.pagamento.domain.model.FormaPagamento;
import br.com.viafood.pagamento.domain.repository.FormasPagamentosRepository;
import br.com.viafood.pagamento.exception.FormaPagamentoNaoEncontradaException;
import br.com.viafood.utils.constantes.Constantes;

/**
 * @author david
 *
 */
@Service
public class FormasPagamentosServiceImpl implements FormasPagamentosService {
	
	private FormasPagamentosRepository repository;
	
	@Autowired
	public FormasPagamentosServiceImpl(final FormasPagamentosRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<FormaPagamento> list() {
		return this.repository.findAll();
	}

	@Transactional
	@Override
	public FormaPagamento save(FormaPagamento formaPagamento) {
		return this.repository.save(formaPagamento);
	}

	@Override
	public FormaPagamento getById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> {
			throw new FormaPagamentoNaoEncontradaException(Constantes.FORMA_DE_PAGAMENTO_NAO_ENCONTRADA);
		});		
	}

	@Transactional
	@Override
	public void remove(Long id) {
		try {
			this.repository.delete(this.repository.findById(id).orElseThrow(() ->{
				throw new FormaPagamentoNaoEncontradaException(id);
			}));
			this.repository.flush();
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeComDependencia(String.format(Constantes.ENTIDADE_DEPENDENCIAS_VINCULADAS, id));
		}
	}
	
}
