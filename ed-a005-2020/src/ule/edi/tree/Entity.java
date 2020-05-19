package ule.edi.tree;

/**
 * Entidades en los mundos binarios.
 * 
 * Se conocen entidades de tipo dragón, princesa, guerrero, castillo y bosque.
 * 
 * Cada entidad lleva asociada una cardinalidad, que indica de cuántas instancias
 * de ese tipo se está hablando.
 * 
 * @author profesor
 *
 */
public class Entity implements Comparable<Entity> {

	public static final int UNKNOWN = 0;
	
	public static final int DRAGON = 1;
	
	public static final int PRINCESS = 3;
	
	public static final int WARRIOR = 5;
	
	public static final int CASTLE = 7;
	
	public static final int FOREST = 9;
	
	private int type = Entity.UNKNOWN;
	
	private long count;
	
	public static Entity princesses(long n) {
		
		return new Entity(Entity.PRINCESS, n);
	}

	public static Entity dragons(long n) {
		
		return new Entity(Entity.DRAGON, n);
	}
	
	public static Entity castles(long n) {
		
		return new Entity(Entity.CASTLE, n);
	}
	
	public static Entity warriors(long n) {
		
		return new Entity(Entity.WARRIOR, n);
	}
	
	public static Entity forests(long n) {
		
		return new Entity(Entity.FOREST, n);
	}
	

	public Entity(int type) {
		
		this.type = type;
		
		this.count = 1;
	}

	public Entity(int type, long count) {
		
		this.type = type;
		
		this.count = count;
	}

	public Entity(Entity other) {
		this.type = other.type;
		this.count = other.count;
	}
	
	public int getType() {
		
		return type;
	}

	public long getCount() {
		
		return count;
	}
	
	public void setCount(long value) {
		
		this.count = value;
	}
	
	public boolean is(int type) {
		
		return (this.type == type);
	}
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		
		if (obj instanceof Entity) {
			
			return (type == ((Entity) obj).type);
		}
		
		return false;
	}

	@Override
	public String toString() {
		
		switch (type) {
		
		case DRAGON: return "D(" + count + ")";

		case PRINCESS: return "P(" + count + ")";
		
		case WARRIOR: return "W(" + count + ")";
		
		case CASTLE: return "C(" + count + ")";
		
		case FOREST: return "F(" + count + ")";
		
		case UNKNOWN: return "U("+ count + ")";
		
		default:
			return "?";
		}
	}

	@Override
	public int compareTo(Entity o) {
		return (toString().compareTo(o.toString()));
	}
	
}
