package ule.edi.tree;


import java.util.Map;

/**
 * Interfaz de operaciones del TAD árbol.
 * 
 * Permite consultar propiedades básicas del árbol, como
 * su grado, si es árbol vacío, si es hoja, etc.
 * 
 * También da acceso a los sub-árboles que pueda tener, e 
 * implementa diversos recorridos.
 * 
 * Por último, maneja un dato de tipo T en el nodo raíz. No se
 * permiten valores <tt>null</tt> para los datos.
 * 
 * @author profesor
 *
 * @param <T> tipo de dato almacenado en cada nodo del árbol.
 */
public interface TreeADT<T> {

	/**
	 * Cierto si el árbol es "árbol vacío"
	 * 
	 * @return cierto para árboles vacíos
	 */
	boolean isEmpty();
	
	/**
	 * Indica si el árbol es hoja (todos sus hijos vacíos)
	 * 
	 * @return cierto si todos los hijos son vacíos
	 * @throws EmptyCollectionException 
	 */
	boolean isLeaf();
	
	/**
	 * Devuelve el grado de la raíz
	 * 
	 * @return número de hijos no vacíos
	 */
	int getDegree();
	
	/**
	 * Devuelve el máximo grado de la raíz
	 * 
	 * @return número máximo posible de hijos
	 */
	int getMaxDegree();

	/**
	 * Devuelve el sub-árbol n-ésimo, puede ser vacío
	 * 
	 * @param n índice del sub-árbol, empiezan en "0"
	 * 
	 * @return árbol n-ésimo
	 */
	TreeADT<T> getSubtree(int n);
	
	/**
	 * Devuelve la información en el nodo raíz.
	 * 
	 * @return información en el nodo raíz.
	 */
	T getContent();
		
	/**
	 * Cambia la información en el nodo raíz.
	 * 
	 * @param content información para el nodo raíz.
	 */
	void setContent(T content);
	
	
	/**
	 * Devuelve el mapa de etiquetas para este nodo.
	 * 
	 * @return
	 */
	public Map<String, Object> getTags();
	
	/**
	 * Asigna un par (clave,valor) como etiqueta a este nodo.
	 * 
	 * @param key
	 * @param value
	 */
	public void setTag(String key, Object value);
	
	/**
	 * Consulta un valor para la clave dada en este nodo, null si no existe.
	 * 
	 * @param key
	 * @return
	 */
	public Object getTag(String key);

	/**
	 * Elimina todas las etiquetas cuya clave no se corresponda con las dadas en keep, 
	 * en el árbol completo.
	 * 
	 * @param keep
	 */
	public void filterTags(String ... keep);
}
