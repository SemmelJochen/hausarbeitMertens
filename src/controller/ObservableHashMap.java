package controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

public class ObservableHashMap<K, V> extends Observable implements Map<K, V> {
	private HashMap<K, V> map;

	public ObservableHashMap() {
		this.map = new HashMap<K, V>();
	}

	public ObservableHashMap(Map<? extends K, ? extends V> m) {
		this.map = new HashMap<K, V>(m);
	}

	@Override
	public int size() {
		return this.map.size();
	}

	@Override
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.map.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return this.map.get(key);
	}

	@Override
	public V remove(Object key) {
		V res = this.map.remove(key);
		this.setChanged();
		this.notifyObservers();
		return res;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		this.map.putAll(m);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void clear() {
		this.map.clear();
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public Set<K> keySet() {
		return this.map.keySet();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return this.map.entrySet();
	}

	@Override
	public V put(K key, V value) {
		V res = this.map.put(key, value);
		this.setChanged();
		this.notifyObservers();
		return res;
	}

	@Override
	public Collection<V> values() {
		return this.map.values();
	}

	public HashMap<K, V> convertToHashMap() {
		return this.map;
	}
}