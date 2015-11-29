package postos.de.combustiveis;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author GoingS
 */
public class postosPesquisadosGUI extends javax.swing.JFrame {

    /**
     * Creates new form postosPesquisadosGUI
     * @param listaPostos
     */
    
    private ArrayList<Posto> listaPostos;
    
    public postosPesquisadosGUI(ArrayList<Posto> novaListaPostos) {
        initComponents();
        this.listaPostos = novaListaPostos;
    }

    private postosPesquisadosGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void atualizarListaPostos(){
        DefaultListModel model1 = new DefaultListModel();
        
        for(Posto posto : this.buscarPostosPorBairro(listaPostos, postoPesquisadoField.getText()))
            model1.addElement(posto);
        
        listaPostosPesquisados.setModel(model1);
    }
    
    private ArrayList<Posto> buscarPostosPorBairro(ArrayList<Posto> listaPostos, String bairroProcurado){
        ArrayList<Posto> postosDoBairro = new ArrayList<>(); 
        
        for (Posto listaPosto : listaPostos) {
            if (listaPosto.getBairro().equals(bairroProcurado)) {
                postosDoBairro.add(listaPosto);
            }
        }
            
        return postosDoBairro;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        postoPesquisadoField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaPostosPesquisados = new javax.swing.JList();
        botaoOkPostosPesquisados = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        postoPesquisadoField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                postoPesquisadoFieldCaretUpdate(evt);
            }
        });

        jLabel1.setText("Digite o posto");

        jScrollPane1.setViewportView(listaPostosPesquisados);

        botaoOkPostosPesquisados.setText("Ok");
        botaoOkPostosPesquisados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoOkPostosPesquisadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(postoPesquisadoField)
                    .addComponent(botaoOkPostosPesquisados, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(postoPesquisadoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoOkPostosPesquisados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void postoPesquisadoFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_postoPesquisadoFieldCaretUpdate
        this.atualizarListaPostos();
    }//GEN-LAST:event_postoPesquisadoFieldCaretUpdate

    private void botaoOkPostosPesquisadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOkPostosPesquisadosActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botaoOkPostosPesquisadosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(postosPesquisadosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(postosPesquisadosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(postosPesquisadosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(postosPesquisadosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
           
        ArrayList<Posto> listaPostos = new ArrayList<>();
        
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
        
        System.out.println(p1.toString());
        
        Posto p2 = new Posto();
        p2.setBairro("ASDSAD");
        p2.setBandeira("BR");
        p2.setCep("SD");
        p2.setCnpj("gGGGG/fefe-02");
        p2.setEndereco("Rua dos Andradas, 448949");
        p2.setNome("Postão");
        p2.setRazaoSocial("grgr");
        
        //c1.showCombustivel();
        p1.addCombustivel(c1);
        p1.addCombustivel(c2);
        p1.addCombustivel(c3);
        p1.rmvCombustivel("Etanol");
        
        listaPostos.add(p1);
        listaPostos.add(p2);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new postosPesquisadosGUI(listaPostos).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoOkPostosPesquisados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaPostosPesquisados;
    private javax.swing.JTextField postoPesquisadoField;
    // End of variables declaration//GEN-END:variables
}
