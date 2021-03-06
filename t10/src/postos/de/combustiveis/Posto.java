package postos.de.combustiveis;

import java.util.ArrayList;

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
    private String imagem;
    private ArrayList<Combustivel> combustiveis;
    
    public Posto(){
        cnpj = "";
        razaoSocial = "";
        nomeFantasia = "";
        bandeira = "";
        endereco = "";
        bairro = "";
        cep = "";
        imagem = System.getProperty("user.dir") + "\\recursos\\imagens\\SemImagem.png";
        combustiveis = new ArrayList<>();
    }
    
    public void showPosto(){
        System.out.println(this.cnpj);
        System.out.println(this.razaoSocial);
        System.out.println(this.nomeFantasia);
        System.out.println(this.bandeira);
        System.out.println(this.endereco);
        System.out.println(this.bairro);
        System.out.println(this.cep);
        
        System.out.println("- Tipos de combustiveis:");
        for(int i=0; i<this.combustiveis.size(); i++)
            System.out.println(combustiveis.get(i).getTipo());
    }
    
    @Override
    public String toString(){
        return this.nomeFantasia;
    }
    
    public String toCsv(){
        return (this.nomeFantasia+","+
                this.bairro+","+
                this.bandeira+","+
                this.cep+","+
                this.cnpj+","+
                this.endereco+","+
                this.razaoSocial+","+
                this.imagem);
    }
    
    public String getCnpj(){
        return this.cnpj;
    }
    
    public String getRazaoSocial(){
        return this.razaoSocial;
    }
    
    public String getNome(){
        return this.nomeFantasia;
    }
    
    public String getBandeira(){
        return this.bandeira;
    }
    
    public String getEndereco(){
        return this.endereco;
    }
    
    public String getBairro(){
        return this.bairro;
    }
    
    public String getCep(){
        return this.cep;
    }
    
    public String getImagem(){
        return this.imagem;
    }
    
    public ArrayList<Combustivel> getCombustiveis(){
        return this.combustiveis;
    }
    
    
    public void setCnpj(String novoCnpj){
        this.cnpj = novoCnpj;
    }
    
    public void setRazaoSocial(String novaRazaoSocial){
        this.razaoSocial = novaRazaoSocial;
    }
    
    public void setNome(String novoNomeFantasia){
        this.nomeFantasia = novoNomeFantasia;
    }
    
    public void setBandeira(String novaBandeira){
        this.bandeira = novaBandeira;
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
    
    public void setImagem(String novaImagem){
        this.imagem = novaImagem;
    }
    
    public void addCombustivel(Combustivel novoCombustivel){
        combustiveis.add(novoCombustivel);
    }
    
    public void rmvCombustivel(String tipoRemovido){
        for(int i=0; i<combustiveis.size();i++){
            if(combustiveis.get(i).getTipo().equals(tipoRemovido))
                combustiveis.remove(i);
        }
    }
    
    public static Posto buscarPosto(ArrayList<Posto> listaPostos, String nomeProcurado){
        for(Posto posto : listaPostos){
            if(posto.nomeFantasia.equals(nomeProcurado))
                return posto;
        }
        return null;
    }
    
}
