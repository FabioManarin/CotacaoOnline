package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COTACAO")
public class Cotacao implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDCOTACAO")
	private int id;
	
	@ManyToOne
    @JoinColumn(name = "IDPRODUTO", nullable = false)
    private Produto produto;
	
	@Column(name = "VALOR", nullable = false, precision = 15, scale = 2)
    private double valor;
	
	@Column(length=100, nullable=false, name="NOME_FORNEC")
	private String nomeFornec;
	
	@Column(length=100, nullable=false, name="EMAIL_FORNEC")
	private String emailFornec;
	
	@Column(length=30, nullable=false, name="TELEFONE_FORNEC")
	private String telefoneFornec;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getNomeFornec() {
		return nomeFornec;
	}

	public void setNomeFornec(String nomeFornec) {
		this.nomeFornec = nomeFornec;
	}

	public String getEmailFornec() {
		return emailFornec;
	}

	public void setEmailFornec(String emailFornec) {
		this.emailFornec = emailFornec;
	}

	public String getTelefoneFornec() {
		return telefoneFornec;
	}

	public void setTelefoneFornec(String telefoneFornec) {
		this.telefoneFornec = telefoneFornec;
	}

	@Override
	public String toString() {
		return "Cotacao [id=" + id + ", produto=" + produto + ", valor=" + valor + ", nomeFornec=" + nomeFornec
				+ ", emailFornec=" + emailFornec + ", telefoneFornec=" + telefoneFornec + "]";
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
		Cotacao other = (Cotacao) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
