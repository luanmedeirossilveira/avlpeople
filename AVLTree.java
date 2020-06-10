/* Comentários da classe AVLTree
 * Classe projetada para construção da Árvore Binário de Busca
 * ***********************************************************
 * Atributos privados:
 * 
 * Pessoa pessoa => Elemento pessoa do nó 
 * AVLTree right => Nó direito
 * AVLTree left => Nó esquerdo
 * bal => número representa balanceamento do Árvore
 * ***********************************************************
 * Constrututores:
 * AVLTree() => inicializa todos null/0
 * AVLTree(Pessoa pessoa) => @param pessoa aloca elemento pessoa
 * ************************************************************
 * Métodos Públicos:
 *
 * int calcHeight() => Calcula a altura da árvore e retorna
 * void calcBal() => Calcula o balanceamento de cada nó
 * AVLTree checkBal() => Faz a verificação de balanceamento para rotações*
 * AVLTree simpleRotRight() => Rotação simples a direita
 * AVLTree simpleRotLeft() => Rotação simples a esquerda
 * AVLTree doubleRotRight() => Rotação dupla a direita
 * AVLTree doubleRotLeft() => Rotação dupla a esquerda
 * boolean isEmpty() => Verifica se está vazio
 * AVLTree insert(Pessoa newPessoa) => @param newPessoa - Insere um novo elemento pessoa na árvore 
 * AVLTree delete(int pessoaChave) => @param pessoaChave - deleta um elemento através de sua chave
 * boolean search(long chave) => @param chave pesquisa uma nova pessoa através da chave 
 * Métodos get/set dos Atributos
 * 
 * * Rotações simples a Direita/Esquerda ; Rotações duplas Direita/Esquerda
 * *************************************************************
 * 
 * 
 *
*/

class AVLTree {
    private Pessoa pessoa;
    private AVLTree right;
    private AVLTree left;
    private int bal;

    public AVLTree(){
        this.pessoa = null; //inicializa elemento pessoa com null
        this.right = null; //inicializa nó direito com null
        this.left = null; //inicializa nó esquerdo com null
        this.bal = 0; //inicializa balanceamento com 0
    }

    public AVLTree(Pessoa pessoa){
        this.pessoa = pessoa; //inicializa com elemento pessoa passado por parâmetro
        this.right = null; //inicializa nó direito com null
        this.left = null; //inicializa nó esquerdo com null
        this.bal = 0; //inicializa balanceamento com 0
    }

    public int calcHeight(){
        if(this.left == null){
            if(this.right == null){
                return 1;
            }
            else{
                return 1 + this.right.calcHeight();
            }
        }else{
            if(this.right == null){
                return 1 + this.left.calcHeight();
            }else{
                return 1 + Math.max(this.left.calcHeight(), this.right.calcHeight());      
            }
        }
    }

    public void calcBal(){
        if(this.right == null){
            if(this.left == null){
                this.bal = 0;
            }else{
                this.bal = 0 - this.left.calcHeight();
            }
        }else{
            if(this.left == null){
               this.bal = this.right.calcHeight() - 0; 
            }else{
                this.bal = this.right.calcHeight() - this.left.calcHeight();
            }
        }
    
        if(this.right != null){
            this.right.calcBal();
        }
    
        if(this.left != null){
            this.left.calcBal();
        }
    }

    public AVLTree checkBal(){
        if(this.bal >= 2 || this.bal <= -2){
            if(this.bal >= 2){
                if(this.bal * this.right.getBal() > 0){
                    System.out.println("Rotação Simples a Direita");
                    return simpleRotRight();
                }else{
                    System.out.println("Rotação Dupla a Direita");
                    return doubleRotRight();
                }
            }else{ // bal <= -2
                if(this.bal * this.left.getBal() > 0){
                    System.out.println("Rotação Simples a Esquerda");
                    return simpleRotLeft();
                }else{
                    System.out.println("Rotação Dupla a Esquerda");
                    return doubleRotLeft();
                }
            }
        }
        this.calcBal();

        if(this.left != null) {
            this.left = this.left.checkBal();
        }
        if(this.right != null) {
            this.right = this.right.checkBal();
        }

        return this;

    }

    public AVLTree simpleRotRight(){
        AVLTree rightChild;
        AVLTree nextChild = null;

        rightChild = this.getRight();
        if (this.right != null){
            if (this.right.getLeft()!= null){
                nextChild = rightChild.getLeft();
            }
        }
        rightChild.setLeft(this);
        this.setRight(nextChild);

        return rightChild;
    }

    public AVLTree simpleRotLeft(){
        AVLTree leftChild;
        AVLTree nextChild = null;

        leftChild = this.getLeft();
        if (this.left != null){
            if (this.left.getRight()!= null){
                nextChild = leftChild.getRight();
            }
        }
        leftChild.setRight(this);
        this.setLeft(nextChild);

        return leftChild;
    }

    public AVLTree doubleRotRight(){
        AVLTree tree = this;
        AVLTree rightChild = this.getRight();
        AVLTree nextChild = rightChild.getLeft();
        AVLTree node = nextChild.getRight();

        // parte 1: alinhar os caras
        rightChild.setLeft(node);
        nextChild.setRight(rightChild);
        this.setRight(nextChild);

        // parte 2: tornar o filho à direita a nova raiz
        AVLTree newRightChild = this.getRight();
        tree.setRight(null);
        newRightChild.setLeft(tree);
        return newRightChild;
    }

    public AVLTree doubleRotLeft(){
        AVLTree tree = this;
        AVLTree leftChild = this.getLeft();
        AVLTree nextChild = leftChild.getRight();
        AVLTree node = nextChild.getLeft();

        // parte 1: alinhar os caras
        leftChild.setRight(node);
        nextChild.setLeft(leftChild);
        this.setLeft(nextChild);

        // parte 2: tornar o filho à esquerda a nova raiz
        AVLTree newLeftChild = this.getLeft();
        tree.setLeft(null);
        newLeftChild.setRight(tree);
        return newLeftChild;
    }


    public boolean isEmpty(){
        return (this.pessoa == null);
    }

    public AVLTree insert(Pessoa newPessoa){
        if(isEmpty()){
            this.pessoa = newPessoa;
        }
        else{
            AVLTree newTree = new AVLTree(newPessoa);
            if(newPessoa.getChave() < this.pessoa.getChave()){
                if(this.left == null){
                    this.left = newTree;
                }
                else{
                    this.left = this.left.insert(newPessoa);
                }
            }
            else if(newPessoa.getChave() > this.pessoa.getChave()){
                if(this.right == null){
                    this.right = newTree;
                }
                else{
                    this.right = this.right.insert(newPessoa);
                }
            }
        }
        return this;
    }

    public AVLTree delete(int pessoaChave){
        if(this.pessoa.getChave() == pessoaChave){
            if(this.left == null){
                if(this.right == null){
                    return null;
                }
            }
            else{
                if(this.left != null && this.right == null){
                    return this.left;
                }
                else if(this.right != null && this.left == null){
                    return this.right;
                }
                else{
                    AVLTree aux = this.right;
                    while(aux.left != null){
                        aux = aux.left;
                    }
                    this.setPessoa(aux.getPessoa());
                    aux.getPessoa().setChave(pessoaChave);
                    this.right = right.delete(pessoaChave);
                }
            }
        }else{
            if(pessoaChave < this.pessoa.getChave()){
                if(this.left != null){
                    this.left = this.left.delete(pessoaChave);
                }
            }
            else if (pessoaChave > this.pessoa.getChave()){
                if(this.right != null){
                    this.right = this.right.delete(pessoaChave);
                }
            }
        }
        return this;
    }
    
    public boolean search(int chave){
        if(isEmpty()){
            return false;
        }
        if(this.pessoa.getChave() == chave){
            return true;
        }
        else{
            if (chave < this.pessoa.getChave()){
                if(this.left == null){
                    return false;
                }
                else{
                    return this.left.search(chave);
                }
            }
            else if(chave > this.pessoa.getChave()){
                if(this.right == null){
                    return false;
                }
                else{
                    return this.right.search(chave);
                }
            }
            return false;
        }
    }

    //gets e sets
    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }
    public void setRight(AVLTree right){
        this.right = right;
    }
    public void setLeft(AVLTree left){
        this.left = left;
    }
    public void setBal(int bal){
        this.bal = bal;
    }

    public int getBal(){
        return this.bal;
    }
    public AVLTree getRight(){
        return this.right;
    }
    public AVLTree getLeft(){
        return this.left;
    }
    public Pessoa getPessoa(){
        return this.pessoa;
    }


    //Método de depuração

    public String printArvore(int level){
        String str = this.toString() + "\n";
        for(int i=0; i<level; i++){
            str += "\t";
        }
        if(this.left != null){
            str += "+=Esquerda: " + this.left.printArvore(level + 1);
        }else{
            str += "+=Esquerda: null";
        }
        str += "\n";

        for(int i=0; i<level; i++){
            str += "\t";
        }
        if(this.right != null){
            str += "+=Direita: " + this.right.printArvore(level + 1);
        }else{
            str += "+=Direita: null";
        }
        str += "\n";

        return str;
    }

    public String toString(){
        return "[" + this.pessoa.getChave() + "] (" + this.bal +")";
    }
}