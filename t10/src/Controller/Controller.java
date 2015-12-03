package Controller;


import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import postos.de.combustiveis.*;
import postos.de.combustiveisGUI.PostosDeCombustiveisGUI;
import postos.de.combustiveisGUI.PostosPesquisadosGUI;
/**
 *
 * @author GoingS
 */
public class Controller {
    
    private static PostosDeCombustiveisGUI gui;
    private static final int alturaImagem = 140;
    private static final int larguraImagem = 260;
    
    
    public static void limparTodasCaixasTexto(){
        gui.nomeField.setText("");
        gui.cnpjField.setText("");
        gui.razaoSocialField.setText("");
        gui.bandeiraField.setText("");
        gui.enderecoField.setText("");
        gui.bairroField.setText("");
        gui.cepField.setText("");
        gui.labelImagem.setIcon(null);
        
        gui.tipoCombustivelField1.setText("");
        gui.dataCombustivelField.setText("");
        gui.precoCombustivelField.setText("");
        
        gui.listaCombustiveisList.removeAll();
        atualizarListaCombustiveis();
    }
    
    public static void caixasTextoPostoAtivadas(boolean ativada){
        gui.nomeField.setEditable(ativada);
        gui.cnpjField.setEditable(ativada);
        gui.razaoSocialField.setEditable(ativada);
        gui.bandeiraField.setEditable(ativada);
        gui.enderecoField.setEditable(ativada);
        gui.bairroField.setEditable(ativada);
        gui.cepField.setEditable(ativada);
    }
    
    public static void caixasTextoCombustivelAtivadas(boolean ativada){
        gui.tipoCombustivelField1.setEditable(ativada);
        gui.dataCombustivelField.setEditable(ativada);
        gui.precoCombustivelField.setEditable(ativada);
    }
    
    public static void preencherCaixasTexto(){
        Posto postoSelecionado = (Posto) gui.postosBox.getSelectedItem();
        if(postoSelecionado != null){
            gui.nomeField.setText(postoSelecionado.toString());
            gui.cnpjField.setText(postoSelecionado.getCnpj());
            gui.razaoSocialField.setText(postoSelecionado.getRazaoSocial());
            gui.bandeiraField.setText(postoSelecionado.getBandeira());
            gui.enderecoField.setText(postoSelecionado.getEndereco());
            gui.bairroField.setText(postoSelecionado.getBairro());
            gui.cepField.setText(postoSelecionado.getCep());
            
            gui.labelImagem.setIcon(redimensionarEConverter(postoSelecionado.getImagem()));
        }
        else 
            limparTodasCaixasTexto();
    
    }
    
    public static void atualizarBox(){
        gui.postosBox.removeAllItems();
        for (Posto listaPosto : gui.listaPostos)
            gui.postosBox.addItem(listaPosto);
    }
    
    public static void atualizarListaCombustiveis(){
        Posto postoSelecionado = (Posto) gui.postosBox.getSelectedItem();
        DefaultListModel model1 = new DefaultListModel();
        
        if(postoSelecionado != null)
            for(Combustivel combustivel : postoSelecionado.getCombustiveis())
                model1.addElement(combustivel);
        gui.listaCombustiveisList.setModel(model1);
        
        atualizarCaixasCombustiveis();
        atualizarListaHistorico();
    }
    
    public static void atualizarCaixasCombustiveis(){
        Combustivel combustivelSelecionado = (Combustivel) gui.listaCombustiveisList.getSelectedValue();
        if(combustivelSelecionado != null){
            gui.tipoCombustivelField1.setText(combustivelSelecionado.getTipo());
            gui.dataCombustivelField.setText(combustivelSelecionado.getData().toString());
            gui.precoCombustivelField.setText(combustivelSelecionado.getPreco() + "");
        }
        else{
            gui.tipoCombustivelField1.setText("");
            gui.dataCombustivelField.setText("");
            gui.precoCombustivelField.setText("");
        }  
    }
    
    public static void atualizarListaHistorico(){
        Combustivel combustivelSelecionado = (Combustivel) gui.listaCombustiveisList.getSelectedValue();
        DefaultListModel model1 = new DefaultListModel();
        
        if(combustivelSelecionado != null)
            for(Historico historico : combustivelSelecionado.getHistorico())
                model1.addElement(historico);
        
        gui.listaHistoricoPrecos.setModel(model1);
    }
    
    public static ImageIcon redimensionarEConverter(String imagemAntigaString){
        ImageIcon icone = new ImageIcon(imagemAntigaString);
            
        Image imagem = icone.getImage();
        Image novaImagem = imagem.getScaledInstance(larguraImagem, alturaImagem, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(novaImagem);
    }
    
    
    public static void postosBoxEvento(){
        preencherCaixasTexto();
        atualizarListaCombustiveis();
    }
    
    public static void botaoAdicionarEvento(){
        limparTodasCaixasTexto();
        caixasTextoPostoAtivadas(true);
        gui.postosBox.setEnabled(false);
        gui.botaoSalvar.setEnabled(true);
            
        Posto novoPosto = new Posto();
        gui.listaPostos.add(novoPosto);
        atualizarBox();
        gui.postosBox.setSelectedItem(novoPosto);
        atualizarListaCombustiveis();
    }
    
    public static void botaoEditarEvento() {                                            
        if(!gui.listaPostos.isEmpty()){
            gui.botaoSalvar.setEnabled(true);
            gui.postosBox.setEnabled(false);
            Controller.caixasTextoPostoAtivadas(true);
        }
    }
    
    public static void botaoSalvarEvento(){
        Posto postoSelecionado = (Posto) gui.postosBox.getSelectedItem();
        
        postoSelecionado.setNome(gui.nomeField.getText());
        postoSelecionado.setCnpj(gui.cnpjField.getText());
        postoSelecionado.setRazaoSocial(gui.razaoSocialField.getText());
        postoSelecionado.setBandeira(gui.bandeiraField.getText());
        postoSelecionado.setEndereco(gui.enderecoField.getText());
        postoSelecionado.setBairro(gui.bairroField.getText());
        postoSelecionado.setCep(gui.cepField.getText());
        
        caixasTextoPostoAtivadas(false);
        gui.botaoSalvar.setEnabled(false);
        gui.postosBox.setEnabled(true);
    }
    
    public static void botaoRemoverEvento(){
        Posto postoSelecionado = (Posto) gui.postosBox.getSelectedItem();
        
        if(postoSelecionado != null){
            gui.listaPostos.remove(postoSelecionado);
            atualizarBox();

            if(gui.listaPostos.size() > 0)
                gui.postosBox.setSelectedIndex(0);
            else
                gui.postosBox.setSelectedItem(null);
            preencherCaixasTexto();
            atualizarListaCombustiveis();
        }
    }
    
    public static void listaCombustiveisEvento(){
        Combustivel combustivelSelecionado = (Combustivel) gui.listaCombustiveisList.getSelectedValue();
        if(combustivelSelecionado != null){
            gui.tipoCombustivelField1.setText(combustivelSelecionado.getTipo());
            gui.dataCombustivelField.setText(combustivelSelecionado.getData().toString());
            gui.precoCombustivelField.setText(combustivelSelecionado.getPreco() + "");
            atualizarListaHistorico();
        }
    }
    
    public static void botaoAdicionarCombustivelEvento(){
        Posto postoSelecionado = (Posto) gui.postosBox.getSelectedItem();
        if(postoSelecionado != null){
            Combustivel novoCombustivel = new Combustivel();


            postoSelecionado.getCombustiveis().add(novoCombustivel);
            atualizarListaCombustiveis();
            gui.listaCombustiveisList.setSelectedValue(novoCombustivel, false);

            gui.botaoSalvarCombustivel.setEnabled(true);
            gui.postosBox.setEnabled(false);
            caixasTextoCombustivelAtivadas(true);
        }
    }
    
    public static void botaoRemoverCombustivelEvento(){
        Posto postoSelecionado = (Posto) gui.postosBox.getSelectedItem();
        if(postoSelecionado != null){
            Combustivel combustivelSelecionado = (Combustivel) gui.listaCombustiveisList.getSelectedValue();

            postoSelecionado.getCombustiveis().remove(combustivelSelecionado);
            atualizarListaCombustiveis();
        }
    }
    
    public static void botaoEditarCombustivelEvento(){
        Combustivel combustivelSelecionado = (Combustivel) gui.listaCombustiveisList.getSelectedValue();
        
        if(combustivelSelecionado != null){
            gui.botaoSalvarCombustivel.setEnabled(true);
            gui.postosBox.setEnabled(false);
            gui.listaCombustiveisList.setEnabled(false);
            caixasTextoCombustivelAtivadas(true);
        }
    }
    
    public static void botaoSalvarCombustivelEvento(){
        Combustivel combustivelSelecionado = (Combustivel) gui.listaCombustiveisList.getSelectedValue();
        
        if(gui.precoCombustivelField.getText().isEmpty())
            gui.precoCombustivelField.setText("0");
        
        combustivelSelecionado.setTipo(gui.tipoCombustivelField1.getText());
        
        combustivelSelecionado.alterarPrecoComHistorico(Float.parseFloat(gui.precoCombustivelField.getText()),
                 Data.stringToData(gui.dataCombustivelField.getText()));
        
        
        caixasTextoCombustivelAtivadas(false);
        gui.botaoSalvarCombustivel.setEnabled(false);
        gui.postosBox.setEnabled(true);
        gui.listaCombustiveisList.setEnabled(true);
        atualizarCaixasCombustiveis();
        atualizarListaHistorico();
        gui.listaCombustiveisList.setSelectedValue(combustivelSelecionado, true);
    }
    
    public static void botaoPesquisarPostosEvento() {                                                     
        if(!gui.listaPostos.isEmpty()){
            PostosPesquisadosGUI postosPesquisados = new PostosPesquisadosGUI(gui.listaPostos);
            postosPesquisados.setTitle("Pesquisando postos");
            postosPesquisados.setVisible(true);
        }
    }
    
    public static void botaoProcurarImagemEvento(){
        JFileChooser fc = new JFileChooser();
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File f = fc.getSelectedFile();
            String localArquivo = f.getAbsolutePath();
            Posto postoSelecionado = (Posto) gui.postosBox.getSelectedItem();
            postoSelecionado.setImagem(localArquivo);
            gui.labelImagem.setIcon(redimensionarEConverter(localArquivo));
        }
    }
    
    public static void main(String[] Args) throws FileNotFoundException, IOException{
        ArrayList<Posto> listaPostosR = new ArrayList<>();
        
        Posto postoAdicionado;
        String csvFile = "C:\\Users\\GoingS\\Desktop\\postos.csv";
        BufferedReader br;
        String linha;
        
        
        
        try {
            br = new BufferedReader(new FileReader(csvFile));
            
            while((linha = br.readLine()) != null){
                String[] valores = linha.split(",");
                postoAdicionado = new Posto();
                
                postoAdicionado.setNome(valores[0]);
                postoAdicionado.setBairro(valores[1]);
                postoAdicionado.setBandeira(valores[2]);
                postoAdicionado.setCep(valores[3]);
                postoAdicionado.setCnpj(valores[4]);
                postoAdicionado.setEndereco(valores[5]);
                postoAdicionado.setRazaoSocial(valores[6]);
                
                listaPostosR.add(postoAdicionado);
            } 
        } catch (FileNotFoundException e) {
        }
        catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
                       
        /*Combustivel c1 = new Combustivel("Etanol", new Data(20,11,2015), 2.12f);
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
        p2.setNome("Postão");
        p2.setRazaoSocial("grgr");
        
        
        p1.addCombustivel(c1);
        p1.addCombustivel(c2);
        p1.addCombustivel(c3);
        p1.rmvCombustivel("Etanol");
        
        listaPostosR.add(p1);
        listaPostosR.add(p2);*/
        
        gui = new PostosDeCombustiveisGUI(listaPostosR);
        gui.setVisible(true);
    }
}
