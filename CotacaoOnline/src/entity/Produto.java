package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PRODUTO")
public class Produto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDPRODUTO")
	private int id;
	
	@Column(length=255, nullable=false, name="NOME")
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="DATA_INICIAL_COTACAO")
	private Date dataInicialCotacao;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="DATA_FINAL_COTACAO")
	private Date dataFinalCotacao;
	
	public Produto(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicialCotacao() {
		return dataInicialCotacao;
	}

	public void setDataInicialCotacao(Date dataInicialCotacao) {
		this.dataInicialCotacao = dataInicialCotacao;
	}

	public Date getDataFinalCotacao() {
		return dataFinalCotacao;
	}

	public void setDataFinalCotacao(Date dataFinalCotacao) {
		this.dataFinalCotacao = dataFinalCotacao;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", dataInicialCotacao=" + dataInicialCotacao
				+ ", dataFinalCotacao=" + dataFinalCotacao +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
