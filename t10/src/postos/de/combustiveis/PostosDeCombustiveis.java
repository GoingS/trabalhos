/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postos.de.combustiveis;
import java.util.ArrayList;
/**
 * 
 *
 * @author GoingS
 */
public class PostosDeCombustiveis {
    
    public ArrayList<Posto> getPostosPorBairro(ArrayList<Posto> listaPostos, String bairroProcurado){
        ArrayList<Posto> postosDoBairro = new ArrayList<>(); 
        
        for(int i =0; i<listaPostos.size(); i++)
            if(listaPostos.get(i).getBairro().equals(bairroProcurado))
                postosDoBairro.add(listaPostos.get(i));
            
        return postosDoBairro;
    }
    
    public static void mainxd(String[] args) {
        ArrayList<Posto> listaPostos = new ArrayList<>();
        //ArrayList<Posto> postosPorBairro = new ArrayList<>();
        
        Combustivel c1 = new Combustivel("Etanol", new Data(20,11,2015), 2.12f);
        Combustivel c2 = new Combustivel("Diesel", new Data(20,11,2015), 3.12f);
        Combustivel c3 = new Combustivel("Gasolina", new Data(20,11,2015), 3.59f);
        
        
        Posto p1 = new Posto();
        p1.setBairro("Centro");
        p1.setBandeira("BR");
        p1.setCep("97010040");
        p1.setCnpj("12345678/0001-02");
        p1.setEndereco("Rua dos Andradas, 1235");
        p1.setNome("Meu Posto");
        p1.setRazaoSocial("Razao");
       
        Posto p2 = new Posto();
        p2.setBairro("ASDSAD");
        p2.setBandeira("BR");
        p2.setCep("SD");
        p2.setCnpj("gGGGG/fefe-02");
        p2.setEndereco("Rua dos Andradas, 448949");
        p2.setNome("ASDASD Posto");
        p2.setRazaoSocial("grgr");
        
        //c1.showCombustivel();
        p1.addCombustivel(c1);
        p1.addCombustivel(c2);
        p1.addCombustivel(c3);
        p1.rmvCombustivel("Etanol");
        
        listaPostos.add(p1);
        listaPostos.add(p2);
        
        for (Posto listaPosto : listaPostos) {
            listaPosto.showPosto();
        }
        
    }    
}
