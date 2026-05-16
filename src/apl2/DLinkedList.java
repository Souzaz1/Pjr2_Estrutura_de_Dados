// arquivo: src/apl2/DLinkedList.java

// TODO: Colocar a identificação dos(as) integrantes aqui.
// Nome: Gabriel Pereira de Souza RA: 10440766
// Nome: Joaquim Lange Lima Amaral RA: 10738376
// Nome: Lucas dos Santos Bartolomeu RA: 10747984 

package apl2;

// -- A classe DLinkedList (que pertence ao pacote apl2) deve implementar uma
// lista duplamente encadeada. Os nós dessa lista são do tipo [da classe] Node.
// -- A classe deve possuir dois nós especiais, head e tail, que são
// referências para o primeiro e último nó da lista, respectivamente.
// -- A classe deve possuir um contador de quantos nós existem na lista.
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com o conteúdo da lista.
// -- A classe deve implementar as operações a seguir, respeitando o
// comportamento descrito em cada operação.

public class DLinkedList {
	private Node head, tail;
	private int size;

	
	// TODO: Implementar a classe conforme o enunciado da atividade Apl2.


// OPERAÇÃO:		Método construtor
// COMPORTAMENTO:	Cria uma lista vazia.
	public DLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}


// OPERAÇÃO:		insert(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no início da lista.
	public void insert(String id, String nome, float nota) {
		Node aux = new Node(id, nome, nota, null, this.head);
		if (this.head != null) {
			this.head.setLeft(aux);
		} else {
			this.tail = aux;
		}
		this.head = aux;
		this.size++;
	}


// OPERAÇÃO:		append(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no final da lista.
	public void append(String id, String nome, float nota) {
		Node aux = new Node (id, nome, nota, this.tail, null);
		if (this.tail != null) {
			this.tail.setRight(aux);
		} else {
			this.head = aux;
		}
		this.tail = aux;
		this.size++;
	}


// OPERAÇÃO: 		removeHead()
// COMPORTAMENTO:	Remove o nó do início da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeHead() {
		if(isEmpty()) {
			return null;
		}
		Node aux = this.head;
		this.head = this.head.getRight();
		if (this.head != null) {
			this.head.setLeft(null);
		}else {
			this.tail = null;
		}
		this.size--;
		return aux;
	}


// OPERAÇÃO:		removeTail()
// COMPORTAMENTO:	Remove o nó do final da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeTail() {
		if(isEmpty()) {
			return null;
		}
		Node aux = this.tail;
		this.tail = this.tail.getLeft();
		if (this.tail != null) {
			this.tail.setRight(null);
		}else {
			this.head = null;
		}
		this.size--;
		return aux;
	}


// OPERAÇÃO:		removeNode(<ID da pessoa>)
// COMPORTAMENTO:	Remove o nó que contém o <ID da pessoa> da lista e retorna
//					a referência do nó removido.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node removeNode(String id) {
		// TODO: Implementar o método e remover o lançamento de exceção abaixo.
		if (isEmpty()) {
			return null;
		}
		Node aux = this.head;
		while (aux != null) { 
			if (aux.getId().equals(id)) {
				if (aux == this.head) {
					return removeHead();
				} 

				if (aux == this.tail) {
					return removeTail();
				}

				aux.getLeft().setRight(aux.getRight());
				aux.getRight().setLeft(aux.getLeft());

				aux.setLeft(null);
				aux.setRight(null);

				this.size--;
				return aux;
			}
			aux = aux.getRight();
		}
		return null;
	}


// OPERAÇÃO:		getHead()
// COMPORTAMENTO:	Retorna uma referência para o nó do início da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getHead() {
		// TODO: Implementar o método e remover o lançamento de exceção abaixo.
		return this.head;
	}


// OPERAÇÃO:		getTail()
// COMPORTAMENTO:	Retorna uma referência para o nó do final da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getTail() {
		// TODO: Implementar o método e remover o lançamento de exceção abaixo.
		return this.tail;
	}


// OPERAÇÃO:		getNode(<ID da pessoa>)
// COMPORTAMENTO:	Retorna uma referência para o nó que contém o <ID da pessoa>
//					da lista.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node getNode(String id) {
		// TODO: Implementar o método e remover o lançamento de exceção abaixo.
		Node aux = this.head;
		while (aux != null) {
			if (aux.getId().equals(id)) {
				return aux;
			}
			aux = aux.getRight();
		}
		return null;
	}


// OPERAÇÃO:		count()
// COMPORTAMENTO:	Retorna a quantidade de nós da lista.
	public int count() {
		// TODO: Implementar o método e remover o lançamento de exceção abaixo.
		return this.size;
	}


// OPERAÇÃO:		isEmpty()
// COMPORTAMENTO:	Retorna true se a lista estiver vazia ou false, caso contrário.
	public boolean isEmpty() {
		// TODO: Implementar o método e remover o lançamento de exceção abaixo.
		return this.size == 0;
	}


// OPERAÇÃO:		clear()
// COMPORTAMENTO:	Esvazia a lista, liberando a memória de todos os nós da lista.
	public void clear() {
		// TODO: Implementar o método e remover o lançamento de exceção abaixo.
		this.head = null;
		this.tail = null;
		this.size = 0;
	}


// OPERAÇÃO:		toString()
// COMPORTAMENTO:	Retorna uma string com o conteúdo da lista (caso queira, use o
//					exemplo do método toString() da classe LinkedListOriginal).
	@Override
	public String toString() {
		// TODO: Implementar o método e remover o lançamento de exceção abaixo.
		if (isEmpty()) 
			return "(" + this.size + ") \nnull.";
		
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(this.size).append(")" + "\n");
		Node aux = this.head;
		while (aux != null) {
			String leftId; 
			String rightId;
			if (aux.getLeft() != null) {
				leftId = aux.getLeft().getId();
			} else {
				leftId = "null";
			}
			if (aux.getRight() != null) {
				rightId = aux.getRight().getId();
			} else {
				rightId = "null";
			}
			sb.append(leftId).append(" <- ").append(aux.toString()).append(" -> ").append(rightId).append("\n");
			aux = aux.getRight();
		}
		return sb.toString();
	}
}
