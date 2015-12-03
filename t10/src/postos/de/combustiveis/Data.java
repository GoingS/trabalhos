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
    
    public Data(int novoDia, int novoMes, int novoAno){
        this.dia = novoDia;
        this.mes = novoMes;
        this.ano = novoAno;
    }
    
     @Override
    public String toString(){
        if(this.dia == 0 && this.mes == 0 && this.ano == 0)
            return "";
        else
            return "" + this.dia + '/' + this.mes + '/' + this.ano;
    }
    
    public static Data stringToData(String dataEmString){
        int diaNovo, mesNovo, anoNovo;
        dataEmString = dataEmString.replace(" ", ""); 
        String[] valores = dataEmString.split("/");
        
        if(valores.length == 3){
            diaNovo = Integer.parseInt(valores[0]);
            mesNovo = Integer.parseInt(valores[1]);
            anoNovo = Integer.parseInt(valores[2]);
        }
        else{
            diaNovo = 0;
            mesNovo = 0;
            anoNovo = 0;
        }
        
        return new Data(diaNovo,mesNovo,anoNovo);
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
