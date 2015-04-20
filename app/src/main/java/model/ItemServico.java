package model;

public class ItemServico {

    public ItemServico(){

    }

    private String Codigo;
    private String Valor;
    private String PrazoEntrega;
    private String ValorMaoPropria;
    private String ValorAvisoRecebimento;
    private String ValorValorDeclarado;
    private String EntregaDomiciliar;
    private String EntregaSabado;
    private String Erro;
    private String MsgErro;
    private String ValorSemAdicionais;
    private String obsFim;

//rui teste

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        this.Codigo = codigo;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        this.Valor = valor;
    }

    public String getPrazoEntrega() {
        return PrazoEntrega;
    }

    public void setPrazoEntrega(String prazoEntrega) {
        this.PrazoEntrega = prazoEntrega;
    }

    public String getValorMaoPropria() {
        return ValorMaoPropria;
    }

    public void setValorMaoPropria(String valorMaoPropria) {
        this.ValorMaoPropria = valorMaoPropria;
    }

    public String getValorAvisoRecebimento() {
        return ValorAvisoRecebimento;
    }

    public void setValorAvisoRecebimento(String valorAvisoRecebimento) {
        this.ValorAvisoRecebimento = valorAvisoRecebimento;
    }

    public String getValorValorDeclarado() {
        return ValorValorDeclarado;
    }

    public void setValorValorDeclarado(String ValorDeclarado) {
        this.ValorValorDeclarado = ValorDeclarado;
    }

    public String getEntregaDomiciliar() {
        return EntregaDomiciliar;
    }

    public void setEntregaDomiciliar(String entregaDomiciliar) {
        this.EntregaDomiciliar = entregaDomiciliar;
    }

    public String getEntregaSabado() {
        return EntregaSabado;
    }

    public void setEntregaSabado(String entregaSabado) {
        this.EntregaSabado = entregaSabado;
    }

    public String getErro() {
        return Erro;
    }

    public void setErro(String erro) {
        this.Erro = erro;
    }

    public String getMsgErro() {
        return MsgErro;
    }

    public void setMsgErro(String msgErro) {
        this.MsgErro = msgErro;
    }

    public String getValorSemAdicionais() {
        return ValorSemAdicionais;
    }

    public void setValorSemAdicionais(String valorSemAdicionais) {
        this.ValorSemAdicionais = valorSemAdicionais;
    }

    public String getObsFim() {
        return obsFim;
    }

    public void setObsFim(String obsFim) {
        this.obsFim = obsFim;
    }



}
