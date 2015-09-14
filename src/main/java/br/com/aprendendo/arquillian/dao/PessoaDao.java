package br.com.aprendendo.arquillian.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.aprendendo.arquillian.modelo.Pessoa;

@Stateless 
public class PessoaDao {

	@PersistenceContext(unitName = "teste")
	EntityManager em;
 
	public void salvar(Pessoa p) {
		em.persist(p);
	}
	
	public void atualizar(Pessoa p) {
		em.merge(p);
	}

	public Pessoa buscar(int id) {
		return em.find(Pessoa.class, id);
	}

	public List<Pessoa> buscarTodasPessoas() {
		return em.createQuery("SELECT p FROM Pessoa p ORDER BY p.id", Pessoa.class).getResultList();
	}
}
