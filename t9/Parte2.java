/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parte2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GoingS
 */

class Veiculo{
    private final char tipo;
    private final String placa;
    
    public Veiculo(char xTipo, String xPlaca){
        tipo = xTipo;
        placa = xPlaca;
    }
    
    public char getTipo(){
        return tipo;
    }
}

class Estacionamento{
    private Veiculo veiculoEstacionado;
    private long horaEntrada;
    
    public Estacionamento(){
        veiculoEstacionado = null;
        horaEntrada = 0;
    }
    public void addVeiculo(Veiculo xVeiculoEstacionado){
        if(xVeiculoEstacionado.getTipo() == 'c' || xVeiculoEstacionado.getTipo() == 'm'){
            horaEntrada = System.currentTimeMillis();
            veiculoEstacionado = xVeiculoEstacionado;
        }
    }
    
    public float rmvVeiculo(){
        if(veiculoEstacionado != null){
            float horas = (System.currentTimeMillis() - horaEntrada)/1000f;
            char tipo = veiculoEstacionado.getTipo();
            
            horaEntrada = 0;
            veiculoEstacionado = null;

            if(tipo == 'c')
                return 3*horas;
            else 
                return 1.5f*horas;
        }
        
        else return 0;
    }
    
    

}
public class Parte2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Veiculo v1 = new Veiculo('c', "ABC1234");
        Veiculo v2 = new Veiculo('m', "DFG5678");
        Estacionamento e1 = new Estacionamento();
        
        
        System.out.println("Para simplificar, nesse exemplo, o preco eh por segundo e nao por horas.");
        
        e1.addVeiculo(v1);
        
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Parte2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.printf("v1: Preco: %.2f\n", e1.rmvVeiculo());
        
        e1.addVeiculo(v2);
        
        try {
            Thread.sleep(3500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Parte2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.printf("v2: Preco: %.2f\n", e1.rmvVeiculo());
        
        
    }
    
}
