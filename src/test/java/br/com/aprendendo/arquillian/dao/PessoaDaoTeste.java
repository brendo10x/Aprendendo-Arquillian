package br.com.aprendendo.arquillian.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.aprendendo.arquillian.modelo.Pessoa;

@RunWith(Arquillian.class)
public class PessoaDaoTeste {
 

	@Deployment
	public static Archive<?> criarArquivoTeste() {
		Archive<?> arquivoTeste = ShrinkWrap.create(WebArchive.class, "aplicacaoTeste.war")
				// Adicionando o pacote inteiro da classe PessoaDao, ou seja incluí todas as outras classes deste pacote
				.addPackage(PessoaDao.class.getPackage())
				// Adicionando apenas a classe Pessoa, e não o pacote inteiro como na linha anterior
				.addClass(Pessoa.class)
				// Adicionando o arquivo persistence.xml para conexão JPA
				.addAsResource("META-INF/persistence.xml")
				// Adicionando o beans.xml para ativação do CDI
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return arquivoTeste;
	}

	@Inject
	PessoaDao pessoaDao;

	@Test
	@InSequence(1)
	public void testeSalvarPessoa() {
		Pessoa p1 = new Pessoa();
		p1.setIdade(10);
		p1.setNome("Patrício Neto");
		pessoaDao.salvar(p1);

		Pessoa p2 = new Pessoa();
		p2.setIdade(21);
		p2.setNome("Brendo Felipe");
		pessoaDao.salvar(p2);

	}

	@Test
	@InSequence(2)
	public void testeAtualizarPessoaP1() {
		Pessoa p1 = pessoaDao.buscar(1);
		p1.setNome("Pedro");
		p1.setIdade(11);
		pessoaDao.atualizar(p1);

		assertEquals("Pedro", p1.getNome());
		assertEquals(11, p1.getIdade().intValue());

	}

	@Test
	@InSequence(3)
	public void testeBuscarPessoaP2() {
		Pessoa p2 = pessoaDao.buscar(2);

		assertEquals("Brendo Felipe", p2.getNome());
		assertEquals(21, p2.getIdade().intValue());
	}

	@Test
	@InSequence(4)
	public void testeBuscarTodasPessoas() {
		List<Pessoa> pessoas = pessoaDao.buscarTodasPessoas();
		assertEquals(2, pessoas.size());
	}

}
