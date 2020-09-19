package br.com.viafood.restaurante.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.viafood.restaurante.domain.model.Restaurante;

public interface RestauranteRepositoryCustom {

	public List<Restaurante> getByNomeAndTaxaInicialTaxaFinal(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
}