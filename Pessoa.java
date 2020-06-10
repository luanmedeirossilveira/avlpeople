/* Comentários da classe Identificando
 * Classe projetada para identificação e ação do formulário de pesquisa
 * ***********************************************************
 * Atributos privados:
 * 
 * private int chave => Chave de identificação única do nó
 * private String cpf => alocação do cpf do elemento pessoa
 * private String rg => alocação do rg do elemento pessoa
 * private String nome => alocação do nome do elemento pessoa
 * private String dataNascimento => alocação da data Nasc do elemento pessoa
 * private String cidade => alocação da cidade do elemento pessoa
 * ***********************************************************
 * Constrututor:
 * Pessoa() => vazio
 * Pessoa(String cpf) => atribui cpf
 * Pessoa(String cpf, String nome) => atribui cpf, nome
 * Pessoa(String cpf, String nome, dn) => atribui cpf, nome, dataNas
 * Pessoa(String cpf, String rg, String nome, String dn, String cidade) => Atribui tds
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

public class Pessoa{
  private int chave;
  private String cpf;
  private String rg;
  private String nome;
  private String dataNascimento;
  private String cidade;

  public Pessoa(){
    this.chave = 0;
    this.cpf = null;
    this.rg = null;
    this.nome = null;
    this.dataNascimento = null;
    this.cidade = null;
  }
  public Pessoa(String cpf){
    this.cpf = cpf;
  }
  public Pessoa(String cpf, String nome){
    this.cpf = cpf;  
    this.nome = nome;
  }
  public Pessoa(String cpf, String nome, String dn){
    this.cpf = cpf;
    this.nome = nome;
    this.dataNascimento = dn;
  }
  public Pessoa(String cpf, String rg, String nome, String dn, String cidade){
    this.cpf = cpf;
    this.rg = rg;
    this.nome = nome;
    this.dataNascimento = dn;
    this.cidade = cidade;
  }

  //Métodos Get/Set
  public int getChave(){
    return this.chave;
  }
  public String getCpf(){
    return this.cpf;
  }
  public String getRg(){
    return this.rg;
  }
  public String getNome(){
    return this.nome;
  }
  public String getData(){
    return this.dataNascimento;
  }
  public String getcidade(){
    return this.cidade;
  }

  public void setChave(int Chave){
    this.chave = chave;
  }
  public void setCpf(String cpf){
    this.cpf = cpf;
  }
  public void setRg(String rg){
    this.rg = rg;
  }
  public void setNome(String nome){
    this.nome = nome;
  }
  public void setData(String dt){
    this.dataNascimento = dt;
  }
  public void setCidade(String cidade){
    this.cidade = cidade;
  }

  public String toString(){
    return (
        "\nCPF: " + this.cpf + 
        "\nRG: " + this.rg +
        "\nNome: " + this.nome +
        "\nData: " + this.dataNascimento +
        "\nCidade: " + this.cidade
    );
  }
}