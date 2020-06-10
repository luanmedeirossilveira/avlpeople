/* Comentários da classe Identificando
 * Classe projetada para identificação e ação do formulário de pesquisa
 * ***********************************************************
 * Atributos privados:
 * 
 * ArrayList<AVLTree> tree = new ArrayList() => Cria uma arvore
 * ArrayList<Pessoa> pessoa = new ArrayList() => Cria um nó
 * String[] separada => Separa os campos do arquivo csv
 * String[] linhas => Identifica as linhas do arquivo csv
 * int valorCampos => Identifica quantos nós foram colocados na árvore
 * ***********************************************************
 * Constrututor:
 * public Identificando(){} => vazio
 * ************************************************************
 * Métodos Públicos:
 *
 * void record(String nomeArquivo) => Lê todos os dados do arquivo csv
 * int cpfSearch(String numeroCpf) => Procura por CPF de pessoas da árvore passado por
 *                                    parâmetro e retorna os dados da pessoa dona do CPF
 * void stringInformada(String inicial) => Procura pessoas que iniciam com a letra passada *                                         por parâmetro
 * Date stringToDate(String data1) => Transforma uma string em date
 * void consultaData(String dataInicio, String dataFim) => Retorna dados de todos clientes *                                com as datas entre o @param dataInicio e @param dataFim
 * *************************************************************
 *
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Identificando<E> {
    private ArrayList<AVLTree> tree = new ArrayList();
    private ArrayList<Pessoa> pessoa = new ArrayList();
    private String[] separada;
    private String[] linhas;
    private int valorCampos;
    
    public Identificando(){}

    public void record(String nomeArquivo) throws FileNotFoundException, IOException{
        try{
                FileReader fr = new FileReader(nomeArquivo);
                BufferedReader in = new BufferedReader(fr);
                String linha = in.readLine();
                int numLinha = 0;
                
                while(linha != null) {
                    String[] separada = linha.split("\\s");
                    numLinha ++;
                    linha = in.readLine();
                }
                in.close();
                
                fr = new FileReader(nomeArquivo);
                in = new BufferedReader(fr);
                int cont = 0;
                int contSeparada = 0;
                int tamanho = 0;
                for(int i = 0; i < numLinha; i++) {//escaneia o texto e passa para um array de String
                    linha = in.readLine();
                    linhas = linha.split("\t");
                    separada = linhas[cont].split(";");

                    Pessoa simbPessoa = new Pessoa();
                    AVLTree newTree = new AVLTree();
                    int x = 0;
                    contSeparada++;
                    while((contSeparada >= 0) && (x == 0)){
                        simbPessoa.setChave(1);
                        simbPessoa.setCpf(separada[cont++]);
                        simbPessoa.setRg(separada[cont++]);
                        simbPessoa.setNome(separada[cont++]);
                        simbPessoa.setData(separada[cont++]);
                        simbPessoa.setCidade(separada[cont++]);
                        pessoa.add(simbPessoa);
                        for (int j = 0+tamanho; j < pessoa.size(); j++) {
                            newTree.insert(pessoa.get(j));
                            newTree.calcBal();
                            newTree = newTree.checkBal();
                            tree.add(newTree);
                            System.out.println(tree.get(j).getPessoa());
                            tamanho++;
                        } 
                        x++;
                        cont = 0;
                    }
                    tree.toString();
                    valorCampos++;
                }
              
                in.close();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /*
    
     * Consulta uma única pessoa pelo seu CPF e exibir seus dados na tela
     * 
     */
    public int cpfSearch(String numeroCpf){
        int max = 0;
        Pessoa newPessoa;
        String numero = null;
        for(int i = 0; i < valorCampos; i++){
          for(AVLTree cpfTree: tree){
                while(max < valorCampos){
                    cpfTree = tree.get(max);
                    cpfTree.calcBal();
                    cpfTree = cpfTree.checkBal();
                    Pessoa cpf = cpfTree.getPessoa();
                    newPessoa = new Pessoa(cpf.getCpf());
                    numero = newPessoa.getCpf();
                    if(numeroCpf.equals(numero)){
                        System.out.println(tree.get(max).getPessoa());
                    }
                    max++;
                }
            }
        }
        return 2;
    }

    /*
     * Consultar todas as pessoas cujo nome comece com uma string informada pelo 
     * usuário e exibir na tela todos os dados dessas pessoas na forma de lista. 
     */
    public void stringInformada(String inicial){
        int max = 0;
        Pessoa newPessoa;
        String searchName = null;
        for(int i = 0; i < valorCampos; i++){
          //localiza os cpf para por em uma árvore
          for(AVLTree nomeTree: tree){
            while(max < valorCampos){
                nomeTree = tree.get(max);
                nomeTree.calcBal();
                nomeTree = nomeTree.checkBal();
                Pessoa nome = nomeTree.getPessoa();
                newPessoa = new Pessoa(nome.getCpf(), nome.getNome());
                
                searchName = newPessoa.getNome();
                searchName = searchName.substring(0,1);
                inicial = inicial.toUpperCase();
                if(inicial.equals(searchName)){
                    System.out.println(tree.get(max).getPessoa());
                }
                max++;
            }
          }
        }
    }
    /*
      Consultar todas as pessoas cuja data de nascimento esteja em um intervalo estabelecido pelo
      usuário e exibir na tela todos os dados dessas pessoas na forma de lista.
    */
    public Date stringToDate(String data1) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        f.setLenient(false);
        java.util.Date d1 = null;
        try{
            d1 = f.parse(data1);
        } catch (ParseException e) {}
        return d1;
    }
    public void consultaData(String dataInicio, String dataFim){
        int max = 0;
        Pessoa newPessoa;
        Date dataI = stringToDate(dataInicio);
        Date dataF = stringToDate(dataFim);
        for(int i = 0; i < valorCampos; i++){
          //localiza os cpf para por em uma árvore
            for(AVLTree dataTree: tree){
                while(max < valorCampos){
                    dataTree = tree.get(max);
                    dataTree.calcBal();
                    dataTree = dataTree.checkBal();
                    Pessoa data = dataTree.getPessoa();
                    newPessoa = new Pessoa(data.getCpf(), data.getNome(), data.getData());
                    
                    Date dataComparative = stringToDate(newPessoa.getData());
                    if(dataComparative.after(dataI) && dataComparative.before(dataF)){
                        System.out.println(tree.get(max).getPessoa());
                    }
                    max++;
                }
            }
        }
    }
}