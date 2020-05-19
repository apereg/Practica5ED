package ule.edi.tree;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Implementación parcial de árboles.
 * 
 * Algunas operaciones del TAD árbol pueden implementarse ya aquí,
 * con independencia del tipo específico de árbol, binario,
 * de expresión, n-ario, etc. Se usan únicamente operaciones
 * comunes a todos los árboles, i.e. las de {@link TreeADT}
 * 
 * También hay datos que hay que almacenar para cualquier
 * tipo o implementación, como la información en cada nodo.
 * 
 * @author profesor
 *
 * @param <T> tipo de la información almacenada en cada nodo.
 */
public abstract class AbstractTreeADT<T> implements TreeADT<T> {

	//	Información en el nodo raíz
	//
	protected T content;
	
	protected Map<String, Object> tags = new HashMap<>();
	
	@Override
	public Map<String, Object> getTags() {
		return tags;
	}
	
	@Override
	public void setTag(String k, Object v) {
		tags.put(k, v);
	}
	
	@Override
	public Object getTag(String k) {
		return tags.get(k);
	}
	
	@Override
	public void filterTags(String ... keep) {
		
		if (! tags.isEmpty()) {
			HashMap<String, Object> rx = new HashMap<>();
			for (int i = 0; i < keep.length; i++) {
				if (tags.containsKey(keep[i])) {
					rx.put(keep[i], tags.get(keep[i]));
				}
			}
			this.tags = rx;
		}
		
		if (! isEmpty()) {
			for (int i = 0; i < getMaxDegree(); i++) {
				getSubtree(i).filterTags(keep);
			}
		}
	}

	
	@Override
	public void setContent(T content) {
		
		this.content = content;
	}
	
	@Override
	public T getContent() {
		return content;
	}	

	@Override
	public boolean isLeaf() {
		
		if (isEmpty()) {
			return false;
		}
		
		//	Para la implementación con árboles vacíos, será
		//	hoja si todos sus sub-árboles son vacíos.
		for (int i = 0; i < getMaxDegree(); i++) {
			//	Al menos uno no es vacío, entonces éste no es hoja
			if (! getSubtree(i).isEmpty()) { return false; }
		}
		//	Todos son vacíos, éste es hoja
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		//	Si contenido es 'null', es vacío y sus sub-árboles deben ser
		//	referencias 'null'
		if (this.content == null) {
			for (int i = 0; i < getMaxDegree(); i++) {
				assert (getSubtree(i) == null);
			}
		}
		
		//	El contenido 'null' marca el árbol como vacío
		return (this.content == null);
	}

	@Override
	public int getDegree() {
		//	Hay que contar el número de sub-árboles no vacíos
		if (! isEmpty()) {
			int result = 0;
			for (int i = 0; i < getMaxDegree(); i++) {
				//	Un sub-árbol más no vacío
				if (! getSubtree(i).isEmpty()) { result++; }
			}
			return result;
			
		} else {
			//	Un árbol vacío tiene grado "0"
			return 0;
		}
	}
		
	//	El código fuente está en UTF-8, debería ser el símbolo de
	//	conjunto vacío. Si aparecen caracteres "raros", es porque
	//	el proyecto no está bien configurado en Eclipse para
	//	usar esa codificación de caracteres.
	//
	public static final String EMPTY_TREE_MARK = "∅";

	/* 
	 * Representa un árbol como string.
	 * 
	 * Un árbol vacío se representa como "∅". Un árbol no vacío
	 * como "{(información raíz), sub-árbol 1, sub-árbol 2, ...}".
	 * 
	 * Por ejemplo, {A, {B, ∅, ∅}, ∅} es un árbol binarios con raíz "A" y
	 * un único sub-árbol, a su izquierda, con raíz "B".
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (! isEmpty()) {
			//	Construye el resultado de forma eficiente
			StringBuffer result = new StringBuffer();
				
			//	Raíz
			result.append("{" + content.toString());
			
			if (! tags.isEmpty()) {
				result.append(" [");
				
				List<String> sk = new LinkedList<String>(tags.keySet());
				
				Collections.sort(sk);
				for (String k : sk) {
					result.append("(" + k + ", " + tags.get(k) + "), ");
				}
				result.delete(result.length() - 2, result.length());
				result.append("]");
			}
			
			//	Y cada sub-árbol
			for (int i = 0; i < getMaxDegree(); i++) {
				result.append(", " + getSubtree(i).toString());
			}
			//	Cierra la "}" de este árbol
			result.append("}");
			
			return result.toString();
		} else {
			return AbstractTreeADT.EMPTY_TREE_MARK;
		}
	}
	
	/**
	 * Implementación para mostrar un árbol con niveles.
	 * 
	 * @param t (sub-)árbol a mostrar.
	 * @param bx buffer donde acumular el resultado.
	 * @param depth profundidad de t en el árbol total.
	 */
	private void render(TreeADT<T> t, StringBuffer bx, int depth) {
		
		//	Muestra el nodo raíz; tantos espacios como profundo sea
		for (int i = 0; i < depth; i++) { bx.append("|  "); }
		
		//	Y después de los espacios, la información
		if (! t.isEmpty()) {
			bx.append(t.getContent());
			
			Map<String, Object> tags = t.getTags();
			
			if (! tags.isEmpty()) {
				bx.append(" [");
				
				List<String> sk = new LinkedList<String>(tags.keySet());
				
				Collections.sort(sk);
				for (String k : sk) {
					bx.append("(" + k + ", " + tags.get(k) + "), ");
				}
				bx.delete(bx.length() - 2, bx.length());
				bx.append("]");
			}
			
			bx.append("\n");
			
			//	Muestra sus sub-árboles (recursividad)
			for (int i = 0; i < t.getMaxDegree(); i++) {
				//	Que están a mayor profundidad que éste
				render(t.getSubtree(i), bx, depth + 1);
			}			
		} else {
			bx.append(AbstractTreeADT.EMPTY_TREE_MARK);
			bx.append("\n");
		}
	}
	
	/**
	 * Devuelve una representación en niveles del árbol.
	 * 
	 * @return cada nodo se muestra según su profundidad.
	 */
	public String render() {
		//	Acumula el resultado
		StringBuffer rx = new StringBuffer();
		//	Genera usando recursividad
		render(this, rx, 0);
		//	
		return rx.toString();
	}
	
	
//	@Override
//	public int height() {
//		//	Si es vacío, altura "0"; si no, 
//		if (! isEmpty()) {
//			//	La altura será "1" más la altura máxima de sus sub-árboles
//			int maxh = getSubtree(0).height();
//			
//			for (int i = 1; i < getMaxDegree(); i++) {
//				//	Acumula el máximo de las alturas
//				maxh = Math.max(maxh, getSubtree(i).height());
//			}
//			
//			return 1 + maxh;
//		} else {
//			//	Vacío
//			return 0;
//		}
//	}
}
