package ule.edi.tree;

/**
 * Árbol binario.
 * 
 * @author profesor
 *
 * @param <T>
 */
public abstract class AbstractBinaryTreeADT<T> extends AbstractTreeADT<T> {
	
	//	Como árbol binario, tiene dos sub-árboles binarios
	//	"izquierdo" y "derecho"
	//
	//	Podrían ser vacíos
	protected AbstractBinaryTreeADT<T> leftSubtree;
	protected AbstractBinaryTreeADT<T> rightSubtree;
	
	@Override
	public int getMaxDegree() {
		return 2;
	}

	@Override
	public TreeADT<T> getSubtree(int n) {
		//	El sub-árbol izquierdo es el "0"
		switch (n) {
		case 0:
			return leftSubtree;
		case 1:
			return rightSubtree;
		}
		
		throw new IllegalStateException("getSubtree(n) on a binary tree needs n in {0,1}");
	}

//
	
}
