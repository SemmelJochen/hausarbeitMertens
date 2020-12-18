package model;

import java.io.Serializable;

public class Pair<K, V> implements Serializable {
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
