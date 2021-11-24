package com.gutotech.loteriasapi.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.loteriasapi.model.Resultado;
import com.gutotech.loteriasapi.model.ResultadoId;
import com.gutotech.loteriasapi.repository.ResultadoRepository;

@Service
public class ResultadoService {

	@Autowired
	private ResultadoRepository repository;

	public List<Resultado> findByLoteria(String loteria) {
		return repository.findById_Loteria(loteria)
				.stream()
				.sorted(Comparator.comparing(Resultado::getConcurso).reversed())
				.collect(Collectors.toList());
	}

	public Resultado findByLoteriaAndConcurso(String loteria, int concurso) {
		return repository.findById(new ResultadoId(loteria, concurso)).orElse(null);
	}

	public Resultado findLatest(String loteria) {
		List<Resultado> resultados = findByLoteria(loteria);

		return resultados.size() > 0 ? resultados.get(0) : new Resultado();
	}

	public Resultado save(Resultado resultado) {
		return repository.save(resultado);
	}

	public void saveAll(List<Resultado> resultados) {
		repository.saveAll(resultados);
	}

	public void deleteAll() {
		repository.deleteAll();
	}
}