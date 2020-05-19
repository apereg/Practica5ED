package ule.edi.tree;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class WorldTests {

	private World w = null;
	private World complete1= null;

	private String rutaComplete1="{[U(1)], {[U(1)], ∅, {[D(3)], {[P(4)], ∅, ∅}, ∅}}, {[U(1)], {[P(7)], ∅, ∅}, {[C(1), D(1)], ∅, ∅}}}";
	@Before
	public void setupWorlds() {
		
		w = World.createEmptyWorld();		
	}
	
	@Test
	public void testInsertCompleteTree() {
	
	rellenaArbolCompleto();

	Assert.assertEquals(rutaComplete1, complete1.toString());	
	}

	private void rellenaArbolCompleto() {
		complete1 = World.createEmptyWorld();
		complete1.insert("11", new Entity(Entity.DRAGON));
		complete1.insert("11", new Entity(Entity.CASTLE));
		complete1.insert("10", new Entity(Entity.PRINCESS,3));
		complete1.insert("10", new Entity(Entity.PRINCESS,4));
		complete1.insert("01", new Entity(Entity.DRAGON,3));
		complete1.insert("010", new Entity(Entity.PRINCESS,4));
		
		}

	@Test
	public void testInsertRootEmpty() {
		w.insert("", new Entity(Entity.DRAGON));
		Assert.assertEquals("{[D(1)], ∅, ∅}", w.toString());
	}
	
	@Test
	public void testInsertRootEmptyCardinality() {
		w.insert("", new Entity(Entity.DRAGON));
		w.insert("", new Entity(Entity.DRAGON));
		Assert.assertEquals("{[D(2)], ∅, ∅}", w.toString());
	}
	
	@Test
	public void testInsertRootEmptyEntitiesWithCardinality() {
		w.insert("", new Entity(Entity.DRAGON));
		w.insert("", new Entity(Entity.DRAGON));
		w.insert("", new Entity(Entity.CASTLE));
		Assert.assertEquals("{[C(1), D(2)], ∅, ∅}", w.toString());
	}

	
	@Test
	public void testInsertCreatesForest() {
		w.insert("0", new Entity(Entity.DRAGON));
		Assert.assertEquals("{[U(1)], {[D(1)], ∅, ∅}, ∅}", w.toString());
	}
	
	@Test
    public void testInsertCreatesForests() {
		w.insert("11", new Entity(Entity.DRAGON));
		Assert.assertEquals("{[U(1)], ∅, {[U(1)], ∅, {[D(1)], ∅, ∅}}}", w.toString());		
	}

	

	//	Contar entidades
	//
	
	@Test
	public void testCountOnEmpty() {
		Assert.assertTrue(w.isEmpty());
		Assert.assertEquals(0, w.countEntity(Entity.PRINCESS));
	}
	
	@Test
	public void testCountingEntitiesBasics() {
		w.insert("111", Entity.dragons(1));
		w.insert("1111", Entity.dragons(1));
		w.insert("111", Entity.castles(1));
		w.insert("100", Entity.dragons(1));
	
		Assert.assertEquals(1, w.countEntity(Entity.CASTLE));
		Assert.assertEquals(3, w.countEntity(Entity.DRAGON));
		Assert.assertEquals(4, w.countEntity(Entity.UNKNOWN));
		Assert.assertEquals(0, w.countEntity(Entity.PRINCESS));
	}
	
	
	
	@Test
	public void testcountEntityInComplete() {
		this.rellenaArbolCompleto();		
		Assert.assertEquals(3, complete1.countEntity(Entity.UNKNOWN));
		Assert.assertEquals(1, complete1.countEntity(Entity.CASTLE));
		Assert.assertEquals(4, complete1.countEntity(Entity.DRAGON));
		Assert.assertEquals(11, complete1.countEntity(Entity.PRINCESS));
	}
	

	 // tests de countAccesiblePrincess	
	
	
	@Test
	public void testAccesiblePrincessWithoutDragon() {
		w.insert("11", Entity.princesses(10));
		w.insert("10", Entity.princesses(10));
		w.insert("01", Entity.princesses(10));
		w.insert("00", Entity.princesses(10));
		LinkedList<String> lista = new LinkedList<String>();
		Assert.assertEquals(w.toString(),"{[U(1)], {[U(1)], {[P(10)], ∅, ∅}, {[P(10)], ∅, ∅}}, {[U(1)], {[P(10)], ∅, ∅}, {[P(10)], ∅, ∅}}}");
		Assert.assertEquals(w.countAccesiblePrincess(lista),40);
		Assert.assertEquals(lista.size(),4);
	}
	
	@Test
	public void testAccesiblePrincessWithDragonAndCastle() {
		w.insert("11", Entity.princesses(10));
		w.insert("10", Entity.princesses(10));
		w.insert("01", Entity.princesses(10));
		w.insert("00", Entity.princesses(10));
		w.insert("1", Entity.dragons(1));
		w.insert("10", Entity.castles(1));
		
		
		LinkedList<String> lista = new LinkedList<String>();
		Assert.assertEquals(w.toString(),"{[U(1)], {[U(1)], {[P(10)], ∅, ∅}, {[P(10)], ∅, ∅}}, {[D(1), U(1)], {[C(1), P(10)], ∅, ∅}, {[P(10)], ∅, ∅}}}");
		Assert.assertEquals(w.countAccesiblePrincess(lista),30);
		Assert.assertEquals(lista.size(),3);
		Assert.assertEquals(lista.toString(), "[00, 01, 10]");
	}

	@Test
	public void testAccesiblePrincessWithDragonInRoot() {
		w.insert("11", Entity.princesses(10));
		w.insert("10", Entity.princesses(10));
		w.insert("01", Entity.princesses(10));
		w.insert("00", Entity.princesses(10));
		w.insert("", Entity.dragons(1));
		LinkedList<String> lista =  new LinkedList<String>();
		Assert.assertEquals(w.countAccesiblePrincess(lista),0);
	}
	

	
}
