package postos.de.combustiveis;

import java.util.ArrayList;

/**
 *
 * @author GoingS
 */
public class Combustivel {
    private String tipo;
    private Data dataColetaPreco;
    private float preco;
    private ArrayList<Historico> historicoDePrecos;
    
    public Combustivel(){
        this.tipo = " ";
        this.dataColetaPreco = new Data(0,0,0);
        this.preco = 0f;
        this.historicoDePrecos = new ArrayList<Historico>();
    }
    
    
    public Combustivel(String novoTipo, Data novaData, float novoPreco){
        this.tipo = novoTipo;
        this.dataColetaPreco = novaData;
        this.preco = novoPreco;
        this.historicoDePrecos = new ArrayList<Historico>();
    }
    
    @Override
    public String toString(){
        return this.tipo;
    }
    
    public void alterarPrecoComHistorico(float novoPreco, Data novaData){
        this.historicoDePrecos.add(new Historico(this.preco, this.dataColetaPreco));
        this.preco = novoPreco;
        this.dataColetaPreco = novaData;
    }
    
    public void showCombustivel(){
        System.out.println(this.tipo);
        dataColetaPreco.showData();
        System.out.println(this.preco);
    }
    
    public void setTipo(String novoTipo){
        this.tipo = novoTipo;
    }
    
    public void setData(Data novaData){
        this.dataColetaPreco = novaData;
    }
    
    public void setPreco(float precoNovo){
        this.preco = precoNovo;
    }
    
    
    public String getTipo(){
        return this.tipo;
    }
    
    public Data getData(){
        return this.dataColetaPreco;
    }
    
    public float getPreco(){
        return this.preco;
    }
    
    public ArrayList<Historico> getHistorico(){
        return this.historicoDePrecos;
    }
}
