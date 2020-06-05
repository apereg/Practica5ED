package ule.edi.tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.swing.tree.TreeNode;

/**
 * Ã�rbol binario de bÃºsqueda (binary search tree, BST).
 * 
 * El cÃ³digo fuente estÃ¡ en UTF-8, y la constante EMPTY_TREE_MARK definida en
 * AbstractTreeADT del proyecto API deberÃ­a ser el sÃ­mbolo de conjunto vacÃ­o:
 * âˆ…
 * 
 * Si aparecen caracteres "raros", es porque el proyecto no estÃ¡ bien
 * configurado en Eclipse para usar esa codificaciÃ³n de caracteres.
 *
 * En el toString() que estÃ¡ ya implementado en AbstractTreeADT se usa el
 * formato:
 * 
 * Un Ã¡rbol vacÃ­o se representa como "âˆ…". Un Ã¡rbol no vacÃ­o como
 * "{(informaciÃ³n raÃ­z), sub-Ã¡rbol 1, sub-Ã¡rbol 2, ...}".
 * 
 * Por ejemplo, {A, {B, âˆ…, âˆ…}, âˆ…} es un Ã¡rbol binario con raÃ­z "A" y un
 * Ãºnico sub-Ã¡rbol, a su izquierda, con raÃ­z "B".
 * 
 * El mÃ©todo render() tambiÃ©n representa un Ã¡rbol, pero con otro formato; por
 * ejemplo, un Ã¡rbol {M, {E, âˆ…, âˆ…}, {S, âˆ…, âˆ…}} se muestra como:
 * 
 * M | E | | âˆ… | | âˆ… | S | | âˆ… | | âˆ…
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para adjuntar
 * informaciÃ³n extra. Si es el caso, tanto toString() como render() mostrarÃ¡n
 * los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor) y con
 * {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en los
 * elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> serÃ­a muy restrictivo; en su lugar se
 * permiten tipos que sean comparables no sÃ³lo con exactamente T sino tambiÃ©n
 * con tipos por encima de T en la herencia.
 * 
 * @param <T> tipo de la informaciÃ³n en cada nodo, comparable.
 */
public class BinarySearchTreeImpl<T extends Comparable<? super T>> extends AbstractBinaryTreeADT<T> {

	BinarySearchTreeImpl<T> father; // referencia a su nodo padre)

	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda izquierdo.
	 */
	protected BinarySearchTreeImpl<T> getLeftBST() {
		// El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		// aquÃ­ se sabe que es ademÃ¡s de bÃºsqueda binario
		//
		return (BinarySearchTreeImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeImpl<T> left) {
		this.leftSubtree = left;
	}

	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda derecho.
	 */
	protected BinarySearchTreeImpl<T> getRightBST() {
		return (BinarySearchTreeImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeImpl<T> right) {
		this.rightSubtree = right;
	}

	/**
	 * Ã�rbol BST vacÃ­o
	 */
	public BinarySearchTreeImpl() {
		this.father = this.emptyBST(null);
	}

	public BinarySearchTreeImpl(BinarySearchTreeImpl<T> father) {
		this.father = father;
	}

	private BinarySearchTreeImpl<T> emptyBST(BinarySearchTreeImpl<T> father) {
		return new BinarySearchTreeImpl<T>(father);

	}

	/**
	 * Inserta los elementos de una colecciÃ³n en el Ã¡rbol. si alguno es 'null', NO
	 * INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements valores a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya estÃ¡n no los
	 *         inserta)
	 */
	public int insert(Collection<T> elements) {
		/*
		 * Si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
		 */
		for (T t : elements)
			if (t == null)
				throw new IllegalArgumentException();

		int count = 0;
		for (T t : elements) {
			if (!this.contains(t)) {
				this.insert(t);
				count++;
			}
		}

		return count;
	}

	/**
	 * Inserta los elementos de un array en el Ã¡rbol. si alguno es 'null', NO
	 * INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya estÃ¡n no los
	 *         inserta)
	 */
	public int insert(T... elements) {
		// si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
		for (T t : elements)
			if (t == null)
				throw new IllegalArgumentException();

		int count = 0;
		for (T t : elements) {
			if (!this.contains(t)) {
				this.insert(t);
				count++;
			}
		}
		return count;
	}

	/**
	 * Inserta (como hoja) un nuevo elemento en el Ã¡rbol de bÃºsqueda.
	 * 
	 * Debe asignarse valor a su atributo father (referencia a su nodo padre o null
	 * si es la raÃ­z)
	 * 
	 * No se permiten elementos null. Si element es null dispara excepciÃ³n:
	 * IllegalArgumentException Si el elemento ya existe en el Ã¡rbol NO lo inserta.
	 * 
	 * @param element valor a insertar.
	 * @return true si se pudo insertar (no existia ese elemento en el arbol, false
	 *         en caso contrario
	 * @throws IllegalArgumentException si element es null
	 */
	public boolean insert(T element) {
		/* Si el elemento es nulo se lanza la excepcion marcada en la documentacion */
		if (element == null)
			throw new IllegalArgumentException();

		if (this.isEmpty()) {
			/* Si la lista esta vacia se añade como padre */
			this.content = element;
			this.leftSubtree = this.emptyBST(this);
			this.rightSubtree = this.emptyBST(this);
		} else if (!this.contains(element)) {
			/* Si no se compara y añade al lado pertinente */
			if (element.compareTo(this.getContent()) > 0) {
				/* Si no hay nada en el nodo se crea */
				if (this.getRightBST().isEmpty()) {
					this.getRightBST().setContent(element);
					this.getRightBST().setRightBST(this.emptyBST(this.getRightBST()));
					this.getRightBST().setLeftBST(this.emptyBST(this.getRightBST()));
				} else {
					this.getRightBST().insert(element);
				}
			} else {
				/* Si no hay nada en el nodo se crea */
				if (this.getLeftBST().isEmpty()) {
					this.getLeftBST().setContent(element);
					this.getLeftBST().setRightBST(this.emptyBST(this.getRightBST()));
					this.getLeftBST().setLeftBST(this.emptyBST(this.getRightBST()));
				} else {
					this.getLeftBST().insert(element);
				}
			}
		} else {
			/* Si el nodo ya esta no se inserta */
			return false;
		}

		return true;
	}

	/**
	 * Busca el elemento en el Ã¡rbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param element valor a buscar.
	 * @return true si el elemento estÃ¡ en el Ã¡rbol, false en caso contrario
	 */
	public boolean contains(T element) {
		/* Si el elemento es nulo se lanza la excepcion marcada en la documentacion */
		if (element == null)
			throw new IllegalArgumentException();

		if (!this.isEmpty()) {
			/* Se comprueba si es el actual (true) */
			if (this.getContent().equals(element))
				return true;

			/* Se comprueba en que hijo se deberia buscar */
			if (element.compareTo(this.getContent()) < 0) {
				/* Si el hijo esta vacio el elemento ya no puede estar */
				if (this.getLeftBST().isEmpty())
					return false;
				/* Se llama al metodo desde el hijo */
				return this.getLeftBST().contains(element);
			} else {
				if (this.getRightBST().isEmpty())
					return false;
				return this.getRightBST().contains(element);
			}
		}
		return false;
	}

	/**
	 * Elimina los valores en un array del Ã¡rbol. O todos o ninguno; si alguno es
	 * 'null'o no lo contiene el Ã¡rbol, no se eliminarÃ¡ ningÃºn elemento
	 * 
	 * @throws NoSuchElementException si alguno de los elementos a eliminar no estÃ¡
	 *                                en el Ã¡rbol
	 */
	public void remove(T... elements) {
		/*
		 * Se comprueba en todos los elementos por ver si hay algun nulo o no contenido
		 * lanzar las excepciones y no borrar nada
		 */
		for (T t : elements) {
			if (t == null)
				throw new IllegalArgumentException();
			if (!this.contains(t))
				throw new NoSuchElementException();
		}

		/* Se recorre todo el array (Ya pulido) */
		for (T t : elements) {
			/* Se llama al metodo que elimina */
			this.remove(t);
		}
	}

	/**
	 * Elimina un elemento del Ã¡rbol.
	 * 
	 * Si el elemento tiene dos hijos, se tomarÃ¡ el criterio de sustituir el
	 * elemento por el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no estÃ¡ en el
	 *                                Ã¡rbol
	 */
	public void remove(T element) {
		/*
		 * Si el elemento es nulo o no esta se comprueba lanzando las correspondientes
		 * excepciones
		 */
		if (element == null)
			throw new IllegalArgumentException();
		if (!this.contains(element))
			throw new NoSuchElementException();

		/* Se situa el elemento buscando en profundidad */
		BinarySearchTreeImpl<T> aux = this;
		while (!aux.getContent().equals(element)) {
			if (element.compareTo(aux.getContent()) > 0)
				aux = aux.getRightBST();
			else
				aux = aux.getLeftBST();
		}

		/* Aux es el elemento a buscar */
		if (aux.isLeaf()) {
			/* Caso 1: Es una hoja */
			aux.setLeftBST(null);
			aux.setRightBST(null);
			aux.setContent(null);
		} else if (aux.getRightBST().getContent() != null) {
			/* Si tiene hijo derecho */
			if (aux.getLeftBST().getContent() != null) {
				/* Caso 3: Tiene ambos hijos */
				BinarySearchTreeImpl<T> menor = aux.getRightBST().getMenor();
				aux.setContent(menor.getContent());
				menor.setContent(null);
			} else {
				/* Caso 2: Solo tiene el hijo derecho */
				aux.setContent(aux.getRightBST().getContent()); // Le asigno el contenido
				/* Si este tiene algun hijo se sube, si no se le asigna uno vacio */
				if (aux.getRightBST().getLeftBST().getContent() != null) {
					aux.setLeftBST(aux.getRightBST().getLeftBST());
					aux.getLeftBST().father = aux;
				} else {
					aux.setLeftBST(emptyBST(aux));
				}

				if (aux.getRightBST().getRightBST().getContent() != null) {
					aux.setRightBST(aux.getRightBST().getRightBST());
					aux.getRightBST().father = aux;
				} else {
					aux.setRightBST(emptyBST(aux));
				}
			}
		} else {
			/* Caso 2: Solo tiene el hijo izquierdo */
			aux.setContent(aux.getLeftBST().getContent());
			if (aux.getLeftBST().getRightBST().getContent() != null) {
				aux.setRightBST(aux.getLeftBST().getRightBST());
				aux.getRightBST().father = aux;
			} else {
				aux.setRightBST(emptyBST(aux));
			}

			if (aux.getLeftBST().getLeftBST().getContent() != null) {
				aux.setLeftBST(aux.getLeftBST().getLeftBST());
				aux.getLeftBST().father = aux;
			} else {
				aux.setLeftBST(emptyBST(aux));
			}

		}
	}

	private BinarySearchTreeImpl<T> getMenor() {
		if (this.isLeaf()) {
			this.setLeftBST(null);
			this.setRightBST(null);
			return this;
		} else if (this.getLeftBST().isEmpty()) {
			BinarySearchTreeImpl<T> n = this;
			this.getRightBST().father = this.father;
			this.father.setLeftBST(this.getRightBST());
			return n;
		} else {
			return this.getLeftBST().getMenor();
		}
	}

	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Etiqueta cada nodo con la etiqueta "height" y el valor correspondiente a la
	 * altura del nodo.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A":
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
	 * 10 | 5 | | 2 | | | âˆ… | | | âˆ… | | âˆ… | 20 | | 15 | | | âˆ… | | | âˆ… | |
	 * 30 | | | âˆ… | | | âˆ…
	 * 
	 * 
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 * {10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], âˆ…, âˆ…}, âˆ…}, {20
	 * [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], âˆ…, âˆ…}, âˆ…}, âˆ…}}
	 * 
	 */
	public void tagHeight() {
		/* Llamada al metodo recursivo con la altura de la raiz (1) */
		this.tagHeightRec(1);
	}

	private void tagHeightRec(int actualHeight) {
		if (this.getContent() != null) {
			/* Si ese nodo no esta vacio se taggea a la altura actual */
			this.setTag("height", actualHeight);

			/*
			 * Se llama al metodo desde los hijos para que si existen se taggen a una altura
			 * mas
			 */
			this.getLeftBST().tagHeightRec(++actualHeight);
			this.getRightBST().tagHeightRec(actualHeight);
		}
	}

	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Etiqueta cada nodo con el valor correspondiente al nÃºmero de descendientes
	 * que tiene en este Ã¡rbol.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A":
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
	 * 10 | 5 | | 2 | | | âˆ… | | | âˆ… | | âˆ… | 20 | | 15 | | | âˆ… | | | âˆ… | |
	 * 30 | | | âˆ… | | | âˆ…
	 * 
	 * 
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 * {10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], âˆ…, âˆ…},
	 * âˆ…}, {20 [(decendents, 2)], {15 [(decendents, 0)], âˆ…, âˆ…}, {30
	 * [(decendents, 0)], âˆ…, âˆ…}}}
	 * 
	 * 
	 */
	public void tagDecendents() {
		/*
		 * Llamada al metodo recursivo (necesario para que devuelva int con sus
		 * descendientes hacia arriba)
		 */
		this.tagDecendentsRec();
	}

	private int tagDecendentsRec() {
		int actualDescendents = 0;

		/* Si el hijo izquierdo no esta vacio se buscan sus descendientes */
		if (this.getLeftBST().getContent() != null) {
			actualDescendents += this.getLeftBST().tagDecendentsRec();
		}
		/* Igual pero en el derecho */
		if (this.getRightBST().getContent() != null) {
			actualDescendents += this.getRightBST().tagDecendentsRec();
		}

		/* Se taguea el numero de descendientes del nodo en cuestion */
		this.setTag("decendents", actualDescendents);

		/* Se devuelve ese numero + 1 (el mismo) al de arriba */
		return ++actualDescendents;
	}

	/**
	 * Devuelve un iterador que recorre los elementos del arbol por niveles segÃºn
	 * el recorrido en anchura
	 * 
	 * Por ejemplo, con el Ã¡rbol
	 * 
	 * {50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * y devolverÃ­a el iterador que recorrerÃ­a los nodos en el orden: 50, 30, 80,
	 * 10, 40, 60
	 * 
	 * 
	 * 
	 * @return iterador para el recorrido en anchura
	 */

	public Iterator<T> iteratorWidth() {
		/* La lista en la que se van a añadir los elementos */
		LinkedList<T> listaAnchura = new LinkedList<>();

		/* La cola de elementos que ir insertando */
		Queue<BinarySearchTreeImpl<T>> colaDeNodos = new LinkedList<>();
		colaDeNodos.add(this);

		BinarySearchTreeImpl<T> aux;
		/* Mientras haya algun nodo en cola que añadir */
		while (!colaDeNodos.isEmpty()) {
			/* Se coge el nodo en cuestion */
			aux = colaDeNodos.poll();
			if (aux.getContent() != null) {
				/* Si este no esta vacio se añade a la lista y se meten sus hijos a la cola */
				listaAnchura.add(aux.getContent());
				colaDeNodos.add(aux.getLeftBST());
				colaDeNodos.add(aux.getRightBST());
			}
		}

		/* Se devuelve el iterador de la lista creada */
		return listaAnchura.iterator();
	}

	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Calcula y devuelve el nÃºmero de nodos que son hijos Ãºnicos y etiqueta cada
	 * nodo que sea hijo Ãºnico (no tenga hermano hijo del mismo padre) con la
	 * etiqueta "onlySon" y el valor correspondiente a su posiciÃ³n segÃºn el
	 * recorrido inorden en este Ã¡rbol.
	 * 
	 * La raÃ­z no se considera hijo Ãºnico.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A", que tiene 3 hijos Ãºnicos, los va etiquetando
	 * segÃºn su recorrido en inorden.
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
	 *
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 * {10, {5, {2 [(onlySon, 1)], âˆ…, âˆ…}, âˆ…}, {20, {15 [(onlySon, 3)], {12
	 * [(onlySon, 2)], âˆ…, âˆ…}, âˆ…}, âˆ…}}
	 * 
	 */
	public int tagOnlySonInorder() {
		/* Si la lista esta vacia no tendra hijos unicos */
		if (this.isEmpty())
			return 0;

		/* Llamada recursiva desde la raiz */
		return this.tagOnlySonInorderRec(0);
	}

	private int tagOnlySonInorderRec(int actualPos) {
		if (this.isLeaf()) {
			/* Si se llega a una hoja no habra mas hijos unicos debajo */
			return actualPos;
		} else if (this.getLeftBST().isEmpty()) {
			/* Si un nodo esta vacio se busca en el opuesto */
			actualPos = this.getRightBST().tagOnlySonInorderRec(actualPos);
			this.getRightBST().setTag("onlySon", ++actualPos);
		} else if (this.getRightBST().isEmpty()) {
			actualPos = getLeftBST().tagOnlySonInorderRec(actualPos);
			this.getLeftBST().setTag("onlySon", ++actualPos);
		} else {
			/* Si ningun nodo esta vacio se busca en sus hijos de ambos lados */
			actualPos = this.getLeftBST().tagOnlySonInorderRec(actualPos);
			actualPos = this.getRightBST().tagOnlySonInorderRec(actualPos);
		}
		return actualPos;
	}
}
