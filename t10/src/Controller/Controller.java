package Controller;


import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private static final int alturaImagem = 152;
    private static final int larguraImagem = 275;
    private static final String localCsv = System.getProperty("user.dir") + "\\recursos\\csv";
    
    private static void limparTodasCaixasTexto(){
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
    
    private static void caixasTextoPostoAtivadas(boolean ativada){
        gui.nomeField.setEditable(ativada);
        gui.cnpjField.setEditable(ativada);
        gui.razaoSocialField.setEditable(ativada);
        gui.bandeiraField.setEditable(ativada);
        gui.enderecoField.setEditable(ativada);
        gui.bairroField.setEditable(ativada);
        gui.cepField.setEditable(ativada);
    }
    
    private static void caixasTextoCombustivelAtivadas(boolean ativada){
        gui.tipoCombustivelField1.setEditable(ativada);
        gui.dataCombustivelField.setEditable(ativada);
        gui.precoCombustivelField.setEditable(ativada);
    }
    
    private static void preencherCaixasTexto(){
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
    
    private static void atualizarBox(){
        gui.postosBox.removeAllItems();
        for (Posto listaPosto : gui.listaPostos)
            gui.postosBox.addItem(listaPosto);
    }
    
    private static void atualizarListaCombustiveis(){
        Posto postoSelecionado = (Posto) gui.postosBox.getSelectedItem();
        DefaultListModel model1 = new DefaultListModel();
        
        if(postoSelecionado != null)
            for(Combustivel combustivel : postoSelecionado.getCombustiveis())
                model1.addElement(combustivel);
        gui.listaCombustiveisList.setModel(model1);
        
        atualizarCaixasCombustiveis();
        atualizarListaHistorico();
    }
    
    private static void atualizarCaixasCombustiveis(){
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
    
    private static void atualizarListaHistorico(){
        Combustivel combustivelSelecionado = (Combustivel) gui.listaCombustiveisList.getSelectedValue();
        DefaultListModel model1 = new DefaultListModel();
        
        if(combustivelSelecionado != null)
            for(Historico historico : combustivelSelecionado.getHistorico())
                model1.addElement(historico);
        
        gui.listaHistoricoPrecos.setModel(model1);
    }
    
    private static ImageIcon redimensionarEConverter(String imagemAntigaString){
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
        Posto novoPosto = new Posto();
        gui.listaPostos.add(novoPosto);
        atualizarBox();
        gui.postosBox.setSelectedItem(novoPosto);
        
        caixasTextoPostoAtivadas(true);
        gui.postosBox.setEnabled(false);
        gui.botaoSalvar.setEnabled(true);
        preencherCaixasTexto();
        atualizarListaCombustiveis();
        
    }
    
    public static void botaoEditarEvento() {                                            
        if(gui.postosBox.getSelectedItem() != null){
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
        
        atualizarPostoCsv(gui.listaPostos);
        
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
            
            atualizarPostoCsv(gui.listaPostos);
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
            
            atualizarCombustivelCsv(gui.listaPostos);
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
        
        atualizarCombustivelCsv(gui.listaPostos);
        atualizarHistoricoCsv(gui.listaPostos);
    }
    
    public static void botaoPesquisarPostosEvento() {                                                     
        if(!gui.listaPostos.isEmpty()){
            PostosPesquisadosGUI postosPesquisados = new PostosPesquisadosGUI(gui.listaPostos);
            postosPesquisados.setTitle("Pesquisando postos");
            postosPesquisados.setVisible(true);
        }
    }
    
    public static void botaoProcurarImagemEvento(){
        if(gui.postosBox.getSelectedItem() != null){
            JFileChooser fc = new JFileChooser();
            if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                File f = fc.getSelectedFile();
                String localArquivo = f.getAbsolutePath();
                Posto postoSelecionado = (Posto) gui.postosBox.getSelectedItem();
                postoSelecionado.setImagem(localArquivo);
                gui.labelImagem.setIcon(redimensionarEConverter(localArquivo));
                
                atualizarPostoCsv(gui.listaPostos);
            }
        }
    }
    
    
    private static ArrayList<Posto> lerPostos(ArrayList<Posto> listaPostosNova, String local){
        BufferedReader br;
        String linha;
        
        try {
            Posto postoAdicionado;
            br = new BufferedReader(new FileReader(local + "\\postos.csv"));
            
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
                postoAdicionado.setImagem(valores[7]);
                
                listaPostosNova.add(postoAdicionado);
            } 
        } catch (FileNotFoundException e) {
        }
        catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPostosNova;
    }
    
    private static ArrayList<Posto> lerCombustiveis(ArrayList<Posto> listaPostosNova, String local){
        BufferedReader br;
        String linha;
        
        try {
            Combustivel combustivelAdicionado;
            Posto postoDoCombustivel;
            br = new BufferedReader(new FileReader(local + "\\combustiveis.csv"));
            
            
            while((linha = br.readLine()) != null){
                String[] valores = linha.split(",");
                postoDoCombustivel = Posto.buscarPosto(listaPostosNova, valores[0]);
                
                if(postoDoCombustivel != null){
                    combustivelAdicionado = new Combustivel();
                    
                    combustivelAdicionado.setTipo(valores[1]);
                    combustivelAdicionado.setData(Data.stringToData(valores[2]));
                    combustivelAdicionado.setPreco(Float.parseFloat(valores[3]));
                    postoDoCombustivel.addCombustivel(combustivelAdicionado);
                }
                
                
            } 
        } catch (FileNotFoundException e) {
        }
        catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaPostosNova;
    }
    
    private static ArrayList<Posto> lerHistorico(ArrayList<Posto> listaPostosNova, String local){
        BufferedReader br;
        String linha;
        
        try {
            Posto postoDoCombustivel;
            Combustivel combustivelDoHistorico = null;
            Historico historicoSelecionado;
            
            br = new BufferedReader(new FileReader(local + "\\historicos.csv"));
            
            
            while((linha = br.readLine()) != null){
                String[] valores = linha.split(",");
                postoDoCombustivel = Posto.buscarPosto(listaPostosNova, valores[0]);
                if(postoDoCombustivel != null)
                    combustivelDoHistorico = Combustivel.buscarCombustivel(postoDoCombustivel.getCombustiveis(), valores[1]);
                
                if(combustivelDoHistorico != null){
                    historicoSelecionado = new Historico();
                    
                    historicoSelecionado.setData(Data.stringToData(valores[2]));
                    historicoSelecionado.setPreco(Float.parseFloat(valores[3]));
                    
                    combustivelDoHistorico.getHistorico().add(historicoSelecionado);
                }
                
                
            } 
        } catch (FileNotFoundException e) {
        }
        catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPostosNova;
    }
    
    
    
    private static void adicionarPostoCsv(Posto postoNovo){
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(localCsv + "\\postos.csv", true));
            escritor.append(postoNovo.toCsv() + '\n');
            escritor.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void atualizarPostoCsv(ArrayList<Posto> listaPostos){
        
         try {
            PrintWriter escritorPrint = new PrintWriter(new FileWriter(localCsv + "\\postos.csv"));
            
            escritorPrint.print("");
            
            for(Posto x : listaPostos)
                adicionarPostoCsv(x);
            
            escritorPrint.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void adicionarCombustivelCsv(Combustivel combustivelNovo, Posto postoDeOrigem){
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(localCsv + "\\combustiveis.csv", true));
            escritor.append(postoDeOrigem.toString() + ',' + combustivelNovo.toCsv());
            escritor.newLine(); // CARE
            escritor.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void atualizarCombustivelCsv(ArrayList<Posto> listaPostos){
        try {
            PrintWriter escritorPrint = new PrintWriter(new FileWriter(localCsv + "\\combustiveis.csv"));
            
            escritorPrint.print("");
            
            for(Posto p : listaPostos)
                for(Combustivel c : p.getCombustiveis())
                    adicionarCombustivelCsv(c, p);
            
            escritorPrint.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void adicionarHistoricoCsv(Historico HistoricoNovo, Combustivel combustivelDeOrigem, Posto postoDeOrigem){
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(localCsv + "\\historicos.csv", true));
            
            escritor.append(postoDeOrigem.toString() + ',' + combustivelDeOrigem.toString() + ',' + HistoricoNovo.toCsv());
            escritor.newLine(); // CARE
            escritor.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void atualizarHistoricoCsv(ArrayList<Posto> listaPostos){
        try {
            PrintWriter escritorPrint = new PrintWriter(new FileWriter(localCsv + "\\historicos.csv"));
            
            escritorPrint.print("");
            
            for(Posto p : listaPostos)
                for(Combustivel c : p.getCombustiveis())
                    for(Historico h : c.getHistorico())
                        adicionarHistoricoCsv(h, c, p);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] Args) throws FileNotFoundException, IOException{
        ArrayList<Posto> listaPostosR = new ArrayList<>();
        
        
        listaPostosR = lerPostos(listaPostosR, localCsv);
        listaPostosR = lerCombustiveis(listaPostosR, localCsv);
        listaPostosR = lerHistorico(listaPostosR, localCsv);
        
        
        
        
        gui = new PostosDeCombustiveisGUI(listaPostosR);
        gui.setVisible(true);
    }
}
