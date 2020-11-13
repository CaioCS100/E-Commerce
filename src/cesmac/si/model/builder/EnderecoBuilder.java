package cesmac.si.model.builder;

import cesmac.si.model.Endereco;

import java.util.Date;

public class EnderecoBuilder {

    private Endereco endereco;

    public EnderecoBuilder() {
        this.endereco = new Endereco();
    }

    public EnderecoBuilder comId(Long id) {
        this.endereco.setId(id);
        return this;
    }

    public EnderecoBuilder comCep(String cep) {
        this.endereco.setCep(cep);
        return this;
    }

    public EnderecoBuilder comLogradouro(String logradouro) {
        this.endereco.setLogradouro(logradouro);
        return this;
    }

    public EnderecoBuilder comBairro(String bairro) {
        this.endereco.setBairro(bairro);
        return this;
    }

    public EnderecoBuilder comCidade(String cidade) {
        this.endereco.setCidade(cidade);
        return this;
    }

    public EnderecoBuilder comUf(String uf) {
        this.endereco.setUf(uf);
        return this;
    }

    public EnderecoBuilder comComplemento(String complemento) {
        this.endereco.setComplemento(complemento);
        return this;
    }

    public EnderecoBuilder comNumero(String numero) {
        this.endereco.setNumero(numero);
        return this;
    }

    public EnderecoBuilder comPontoReferencia(String pontoReferencia) {
        this.endereco.setPontoReferencia(pontoReferencia);
        return this;
    }

    public Endereco construir() {
        return this.endereco;
    }
}
