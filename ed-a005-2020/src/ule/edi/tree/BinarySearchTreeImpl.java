package ule.edi.tree;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;


/**
 * Árbol binario de búsqueda (binary search tree, BST).
 * 
 * El código fuente está en UTF-8, y la constante 
 * EMPTY_TREE_MARK definida en AbstractTreeADT del
 * proyecto API debería ser el símbolo de conjunto vacío: ∅
 * 
 * Si aparecen caracteres "raros", es porque
 * el proyecto no está bien configurado en Eclipse para
 * usar esa codificación de caracteres.
 *
 * En el toString() que está ya implementado en AbstractTreeADT
 * se usa el formato:
 * 
 * 		Un árbol vacío se representa como "∅". Un árbol no vacío
 * 		como "{(información raíz), sub-árbol 1, sub-árbol 2, ...}".
 * 
 * 		Por ejemplo, {A, {B, ∅, ∅}, ∅} es un árbol binario con 
 * 		raíz "A" y un único sub-árbol, a su izquierda, con raíz "B".
 * 
 * El método render() también representa un árbol, pero con otro
 * formato; por ejemplo, un árbol {M, {E, ∅, ∅}, {S, ∅, ∅}} se
 * muestra como:
 * 
 * M
 * |  E
 * |  |  ∅
 * |  |  ∅
 * |  S
 * |  |  ∅
 * |  |  ∅
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para
 * adjuntar información extra. Si es el caso, tanto toString() como
 * render() mostrarán los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor)
 * y con {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en
 * los elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> sería muy restrictivo; en
 * su lugar se permiten tipos que sean comparables no sólo con exactamente
 * T sino también con tipos por encima de T en la herencia.
 * 
 * @param <T>
 *            tipo de la información en cada nodo, comparable.
 */
public class BinarySearchTreeImpl<T extends Comparable<? super T>> extends
		AbstractBinaryTreeADT<T> {

   BinarySearchTreeImpl<T> father;  //referencia a su nodo padre)

	/**
	 * Devuelve el árbol binario de búsqueda izquierdo.
	 */
	protected BinarySearchTreeImpl<T> getLeftBST() {
		//	El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		//	aquí se sabe que es además de búsqueda binario
		//
		return (BinarySearchTreeImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeImpl<T> left) {
		this.leftSubtree = left;
	}
	
	/**
	 * Devuelve el árbol binario de búsqueda derecho.
	 */
	protected BinarySearchTreeImpl<T> getRightBST() {
		return (BinarySearchTreeImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeImpl<T> right) {
		this.rightSubtree = right;
	}
	
	/**
	 * Árbol BST vacío
	 */
	public BinarySearchTreeImpl() {
		// TODO HACER QUE THIS SEA EL NODO VACÍO
			
	}
	
	public BinarySearchTreeImpl(BinarySearchTreeImpl<T> father) {
		// TODO HACER QUE THIS SEA EL NODO VACÍO, asignando como padre el parámetro recibido
		
	}


	private BinarySearchTreeImpl<T> emptyBST(BinarySearchTreeImpl<T> father) {
		return new BinarySearchTreeImpl<T>(father);
	}
	
	/**
	 * Inserta los elementos de una colección en el árbol.
	 *  si alguno es 'null', NO INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements
	 *            valores a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya están no los inserta)
	 */
	public int insert(Collection<T> elements) {
		//	 si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
		//TODO Implementar el método
		return 0;
	}

	/**
	 * Inserta los elementos de un array en el árbol.
	 *  si alguno es 'null', NO INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya están no los inserta)
	 */
	public int insert(T ... elements) {
		//	 si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
	    // TODO Implementar el método
		return 0;
	}
	
	/**
	 * Inserta (como hoja) un nuevo elemento en el árbol de búsqueda.
	 * 
	 * Debe asignarse valor a su atributo father (referencia a su nodo padre o null si es la raíz)
	 * 
	 * No se permiten elementos null. Si element es null dispara excepción: IllegalArgumentException 
	 *  Si el elemento ya existe en el árbol NO lo inserta.
	 * 
	 * @param element
	 *            valor a insertar.
	 * @return true si se pudo insertar (no existia ese elemento en el arbol, false en caso contrario
	 * @throws IllegalArgumentException si element es null           
	 */
	public boolean insert(T element) {
    //	TODO Implementar el método
	
		return false;
	
	}
	

	/**
	 * Busca el elemento en el árbol.
	 * 
	 * No se permiten elementos null. 
	 * 
	 * @param element   valor a buscar.
	 * @return true si el elemento está en el árbol, false en caso contrario          
	 */
	public boolean contains(T element) {
		// TODO Implementar el método
		
		return false;
	}
	
	/**
	 * Elimina los valores en un array del árbol.
	 * O todos o ninguno; si alguno es 'null'o no lo contiene el árbol, no se eliminará ningún elemento
	 * 
	 * @throws NoSuchElementException si alguno de los elementos a eliminar no está en el árbol           
	 */
	public void remove(T ... elements) {
	    // TODO Implementar el método
	}
	
	/**
	 * Elimina un elemento del árbol.
	 * 
	 * Si el elemento tiene dos hijos, se tomará el criterio de sustituir el elemento por
	 *  el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no está en el árbol           
	 */
	public void remove(T element) {
		// TODO Implementar el método
			
	}
	
	/**
	 * Importante: Solamente se puede recorrer el árbol una vez
	 * 
	 * Etiqueta cada nodo con la etiqueta "height" y el valor correspondiente a la altura del nodo.
	 * 
	 * Por ejemplo, sea un árbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
	 * 
     * 10
     * |  5
     * |  |  2
     * |  |  |  ∅
     * |  |  |  ∅
     * |  |  ∅
     * |  20
     * |  |  15
     * |  |  |  ∅
     * |  |  |  ∅ 
     * |  |  30
     * |  |  |  ∅
     * |  |  |  ∅
     * 
	 * 
	 * el árbol quedaría etiquetado:
	 * 
	 *   {10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅},
	 *               {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], ∅, ∅}, ∅}, ∅}}
	 * 
	 */
	public void tagHeight() {
	// TODO implementar el método
	}
	
	
	/**
	 * Importante: Solamente se puede recorrer el árbol una vez
	 * 
	 * Etiqueta cada nodo con el valor correspondiente al número de descendientes que tiene en este árbol.
	 * 
	 * Por ejemplo, sea un árbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
	 * 
     * 10
     * |  5
     * |  |  2
     * |  |  |  ∅
     * |  |  |  ∅
     * |  |  ∅
     * |  20
     * |  |  15
     * |  |  |  ∅
     * |  |  |  ∅ 
     * |  |  30
     * |  |  |  ∅
     * |  |  |  ∅
     * 
	 * 
	 * el árbol quedaría etiquetado:
	 * 
	 *  {10 [(decendents, 5)], 
	 *       {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, 
	 *       {20 [(decendents, 2)], {15 [(decendents, 0)], ∅, ∅}, {30 [(decendents, 0)], ∅, ∅}}}
	 * 
	 * 
	 */
	public void tagDecendents() {
	   // TODO Implementar el método
		
	}
		
	
	
	/**	
	 * Devuelve un iterador que recorre los elementos del arbol por niveles según 
         * el recorrido en anchura
	 * 
	 * Por ejemplo, con el árbol
	 * 
	 * 		{50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * y devolvería el iterador que recorrería los nodos en el orden: 50, 30, 80, 10, 40, 60
	 * 
	 * 		
	 * 
	 * @return iterador para el recorrido en anchura
	 */

	public Iterator<T> iteratorWidth() {
		//	TODO Implementar método
		// puede implementarse creando una lista con el recorrido en anchura de los elementos del árbol y devolver el iterador de dicha lista
		return null;
	}	
	
	

	/**
	 * Importante: Solamente se puede recorrer el árbol una vez
	 * 
	 * Calcula y devuelve el número de nodos que son hijos únicos 
	 *  y etiqueta cada nodo que sea hijo único (no tenga hermano hijo del mismo padre) 
	 *   con la etiqueta "onlySon" y el valor correspondiente a su posición según el 
	 *   recorrido inorden en este árbol. 
	 *   
	 *   La raíz no se considera hijo único.
	 * 
	 * Por ejemplo, sea un árbol "A", que tiene 3 hijos únicos, los va etiquetando según 
	 * su recorrido en inorden. 
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}
	 * 
     *
	 * el árbol quedaría etiquetado:
	 * 
	 * {10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, 
	 *      {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], ∅, ∅}, ∅}, ∅}}
	 * 
	 */
	public int tagOnlySonInorder() {
		// TODO Implementar el método
		return 0;
	}
	
	
	
}

