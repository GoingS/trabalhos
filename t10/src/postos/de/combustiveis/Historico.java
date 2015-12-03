package postos.de.combustiveis;

/**
 *
 * @author GoingS
 */
public class Historico {
    
    private float preco;
    private Data dataDeAlteracao;
    
    public Historico(float novoPreco, Data novaData){
        this.preco = novoPreco;
        this.dataDeAlteracao = novaData;
    }
    
    public Historico(){
        this.preco = 0;
        this.dataDeAlteracao = new Data(0,0,0);
    }
    
    @Override
    public String toString(){
        return this.dataDeAlteracao.toString() + " - " + this.preco;
    }
    
    public String toCsv(){
        return (this.dataDeAlteracao.toString() + "," +
                this.preco);      
    }
    
    public void setPreco(float novoPreco){
        this.preco = novoPreco;
    }
    
    public void setData(Data novaData){
        this.dataDeAlteracao = novaData;
    }
    
    public float getPreco(){
        return this.preco;
    }
    
    public Data getData(){
        return this.dataDeAlteracao;
    }
}
