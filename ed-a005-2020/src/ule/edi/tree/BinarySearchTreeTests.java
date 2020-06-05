package ule.edi.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTests {

	/*
	 * 10 | 5 | | 2 | | | ∅ | | | ∅ | | ∅ | 20 | | 15 | | | ∅ | | | ∅ | | 30 | | | ∅
	 * | | | ∅
	 */
	private BinarySearchTreeImpl<Integer> ejemplo = null;

	/*
	 * 10 | 5 | | 2 | | | ∅ | | | ∅ | | ∅ | 20 | | 15 | | | 12 | | | | ∅ | | | | ∅ |
	 * | ∅
	 */
	private BinarySearchTreeImpl<Integer> other = null;

	@Before
	public void setupBSTs() {

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 20, 5, 2, 15, 30);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");

		other = new BinarySearchTreeImpl<Integer>();
		other.insert(10, 20, 5, 2, 15, 12);
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");

	}

	@Test
	public void testRemoveHoja() {
		ejemplo.remove(30);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}", ejemplo.toString());
	}

	@Test
	public void testRemove1Hijo() {
		ejemplo.remove(5);
		Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}", ejemplo.toString());
	}

	@Test
	public void testRemove2Hijos() {
		ejemplo.remove(10);
		Assert.assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}", ejemplo.toString());
	}

	@Test
	public void testTagDecendentsEjemplo() {
		ejemplo.tagDecendents();
		ejemplo.filterTags("decendents");
		Assert.assertEquals(
				"{10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, {20 [(decendents, 2)], {15 [(decendents, 0)], ∅, ∅}, {30 [(decendents, 0)], ∅, ∅}}}",
				ejemplo.toString());
	}

	@Test
	public void testTagHeightEjemplo() {
		other.tagHeight();
		other.filterTags("height");
		Assert.assertEquals(
				"{10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅}, {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], ∅, ∅}, ∅}, ∅}}",
				other.toString());
	}

	@Test
	public void testTagOnlySonEjemplo() {

		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		Assert.assertEquals(3, other.tagOnlySonInorder());
		other.filterTags("onlySon");
		Assert.assertEquals(
				"{10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], ∅, ∅}, ∅}, ∅}}",
				other.toString());

	}
	
	
	/* TESTS DEL ALUMNO */
	
	
	@Test
	public void testOnlySonInOrderEmpty() {
		BinarySearchTreeImpl<Integer> empty = new BinarySearchTreeImpl<>();
		assertEquals(0, empty.tagOnlySonInorder());
	}
	
	@Test
	public void testOnlySonInOrderLeftBSTEmpty() {
		other.insert(13);
		assertEquals(4, other.tagOnlySonInorder());
	}
	
	@Test 
	public void testIteratorWidth() {
		Iterator<Integer> itr = other.iteratorWidth();
		assertTrue(itr.hasNext());
		assertEquals((Integer)10, itr.next());
		assertTrue(itr.hasNext());
		assertEquals((Integer)5, itr.next());
		assertTrue(itr.hasNext());
		assertEquals((Integer)20, itr.next());
		assertTrue(itr.hasNext());
		assertEquals((Integer)2, itr.next());
		assertTrue(itr.hasNext());
		assertEquals((Integer)15, itr.next());
		assertTrue(itr.hasNext());
		assertEquals((Integer)12, itr.next());
		assertFalse(itr.hasNext());
	}
	
	@Test
	public void testRemoveSeveral() {
		ejemplo.remove(10, 30);
		assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, ∅}}", ejemplo.toString());
	}
	
	@Test
	public void testRemoveOneSonAtRight() {
		other.insert(13);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, {13, ∅, ∅}}, ∅}, ∅}}", other.toString());
		other.remove(12);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {13, ∅, ∅}, ∅}, ∅}}", other.toString());
	}
	
	@Test
	public void testRemoveOneSonAtRightWithSons() {
		other.insert(100);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, {100, ∅, ∅}}}", other.toString());
		other.insert(110);
		other.insert(105);
		other.insert(115);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, {100, ∅, {110, {105, ∅, ∅}, {115, ∅, ∅}}}}}", other.toString());
		other.remove(100);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, {110, {105, ∅, ∅}, {115, ∅, ∅}}}}", other.toString());	
	}
	
	@Test
	public void testRemoveOneSonAtLeftWithSons() {
		other.insert(100);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, {100, ∅, ∅}}}", other.toString());
		other.insert(90);
		other.insert(95);
		other.insert(85);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, {100, {90, {85, ∅, ∅}, {95, ∅, ∅}}, ∅}}}", other.toString());
		other.remove(100);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, {90, {85, ∅, ∅}, {95, ∅, ∅}}}}", other.toString());	
	}
	
	@Test
	public void testGetMenor() {
		other.insert(100);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, {100, ∅, ∅}}}", other.toString());
		other.insert(90);
		other.insert(200);
		other.insert(190);
		other.insert(210);
		other.insert(195);
		other.insert(205);
		other.insert(215);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, {100, {90, ∅, ∅}, {200, {190, ∅, {195, ∅, ∅}}, {210, {205, ∅, ∅}, {215, ∅, ∅}}}}}}", other.toString());
		other.remove(100);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, {190, {90, ∅, ∅}, {200, {195, ∅, ∅}, {210, {205, ∅, ∅}, {215, ∅, ∅}}}}}}", other.toString());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveNull() {
		Integer aux = null;
		aux = null;
		other.remove(aux);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveNonContained() {
		other.remove(9999);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveSeveralNull() {
		Integer[] aux = {5, 4};
		aux[1] = null;
		other.remove(aux);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveSeveralNonContained() {
		Integer[] aux = {5, 4000};
		other.remove(aux);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testContainsNull() {
		other.contains(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInsertNull() {
		Integer aux = null;
		aux = null;
		other.insert(aux);
	}
	
	@Test
	public void testInsertAlreadyContained() {
		assertFalse(other.insert(10));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInsertSeveralNull() {
		Integer[] aux = {5, 4};
		aux[1] = null;
		other.insert(aux);
	}
	
	@Test
	public void testInsertSeveralAlreadyContained() {
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}" , other.toString());
		Integer[] aux = {10, 4};
		other.insert(aux);
		assertEquals("{10, {5, {2, ∅, {4, ∅, ∅}}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}" , other.toString());
	}
	
	@Test
	public void testInsertOtherList() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(16);
		list.add(25);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}" , other.toString());
		other.insert(list);
		assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, {16, ∅, ∅}}, {25, ∅, ∅}}}" , other.toString());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInsertOtherListNull() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(4);
		list.add(null);
		other.insert(list);
	}
	
	@Test
	public void testInsertOtherListAlreadyContained() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(4);
		list.add(10);
		other.insert(list);
		assertEquals("{10, {5, {2, ∅, {4, ∅, ∅}}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}" , other.toString());
	}

}
