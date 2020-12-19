package model;

import java.io.Serializable;

/**
 * 
 * @author josua
 *
 * @param <K> can be anything
 * @param <V> can be anything
 * 
 *        A pair is used for places, where u need to store two objects related
 *        to each other.
 */
public class Pair<K, V> implements Serializable {

	private static final long serialVersionUID = 1L;
	private K k;
	private V v;

	public Pair(K k, V v) {
		this.k = k;
		this.v = v;
	}

	public Pair() {
		this.k = null;
		this.v = null;
	}

	public K getKey() {
		return this.k;
	}

	public V getValue() {
		return this.v;
	}

	public void setValue(V v) {
		this.v = v;
	}

	public void setKey(K k) {
		this.k = k;
	}
}
