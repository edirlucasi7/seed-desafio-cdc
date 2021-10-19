package com.deveficiente.casadocodigo.detalhecompra;

import java.math.BigDecimal;

import com.deveficiente.casadocodigo.fechacompra.Compra;
import com.deveficiente.casadocodigo.fechacompra.CupomAplicado;
import com.deveficiente.casadocodigo.fechacompra.ItemPedido;
import com.deveficiente.casadocodigo.fechacompra.Pedido;
import com.deveficiente.casadocodigo.paisestado.Estado;
import com.deveficiente.casadocodigo.paisestado.Pais;

public class DetalhesComprasResponse {

	private String email;
	private String nome;
	private String sobreNome;
	private String documento;
	private String endereco;
	private String cidade;
	private Pais pais;
	private Estado estado;
	private String telefone;
	private String cep;
	private Pedido pedido;
	private CupomAplicado codigoCupom;
	private Boolean existeCupom;  
	private BigDecimal valorCompraCupomAplicado;
	
	public DetalhesComprasResponse(Compra compra) {
		this.email = compra.getEmail();
		this.nome = compra.getNome();
		this.sobreNome = compra.getSobreNome();
		this.documento = compra.getDocumento();
		this.endereco = compra.getEndereco();
		this.cidade = compra.getCidade();
		this.pais = compra.getPais();
		this.estado = compra.getEstado();
		this.telefone = compra.getTelefone();
		this.cep = compra.getCep();
		this.pedido = compra.getPedido();
		this.codigoCupom = compra.getCodigoAplicado();
		this.existeCupom = existeCupom();
		if(existeCupom()) {
			this.valorCompraCupomAplicado = valorCompraCupomAplicado();			
		}
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public Pais getPais() {
		return pais;
	}

	public Estado getEstado() {
		return estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public CupomAplicado getCodigoCupom() {
		return codigoCupom;
	}
	
	public boolean isExisteCupom() {
		return existeCupom;
	}

	public BigDecimal getValorCompraCupomAplicado() {
		return valorCompraCupomAplicado;
	}

	private Boolean existeCupom() {
		return codigoCupom!=null;
	}
	
	private BigDecimal valorCompraCupomAplicado() {
		BigDecimal totalPedido = pedido.getItens().stream().map(ItemPedido::total).reduce(BigDecimal.ZERO,
				(atual, proximo) ->	atual.add(proximo));
		
		return totalPedido.subtract(totalPedido.multiply(codigoCupom.getPercentualDescontoMomento()));
	}

}
