package postos.de.combustiveis;

/**
 *
 * @author GoingS
 */
public class Posto {
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String bandeira;
    private String endereco;
    private String bairro;
    private String cep;
    
    public Posto(){
        cnpj = "";
        razaoSocial = "";
        nomeFantasia = "";
        bandeira = "";
        endereco = "";
        bairro = "";
        cep = "";
    }
    
    public void setCnpj(String novoCnpj){
        this.cnpj = novoCnpj;
    }
    
    public void setRazaoSocial(String novaRazaoSocial){
        this.razaoSocial = novaRazaoSocial;
    }
    
    public void setNomeFantasia(String novoNomeFantasia){
        this.nomeFantasia = novoNomeFantasia;
    }
    
    public void setEndereco(String novoEndereco){
        this.endereco = novoEndereco;
    }
    
    public void setBairro(String novoBairro){
        this.bairro = novoBairro;
    }
    
    public void setCep(String novoCep){
        this.cep = novoCep;
    }
    
}
