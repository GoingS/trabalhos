/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postos.de.combustiveis;

/**
 *
 * @author GoingS
 */
public class Data {
    private int dia;
    private int mes;
    private int ano;
    
    @Override
    public String toString(){
        return "" + this.dia + '/' + this.mes + '/' + this.ano;
    }
    
    public Data(int novoDia, int novoMes, int novoAno){
        this.dia = novoDia;
        this.mes = novoMes;
        this.ano = novoAno;
    }
    
    public void setDia(int dia){
        this.dia = dia;
    }
    
    public void setMes(int mes){
        this.mes = mes;
    }
    
    public void setAno(int ano){
        this.ano = ano;
    }
    
    public void setDataComString(String dataNova){
        
    }
    
    public void showData(){
        System.out.printf("%d/%d/%d\n", this.dia, this.mes, this.ano);
    }
}
