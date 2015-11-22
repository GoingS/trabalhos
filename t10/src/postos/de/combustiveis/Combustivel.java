package postos.de.combustiveis;

/**
 *
 * @author GoingS
 */
public class Combustivel {
    private String tipo;
    private Data dataColetaPreco;
    private float preco;
    
    
    public Combustivel(String novoTipo, Data novaData, float novoPreco){
        this.tipo = novoTipo;
        this.dataColetaPreco = novaData;
        this.preco = novoPreco;
    }
    
    public void showCombustivel(){
        System.out.println(this.tipo);
        dataColetaPreco.showData();
        System.out.println(this.preco);
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
}
