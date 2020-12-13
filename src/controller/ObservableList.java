package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class ObservableList<E> extends Observable implements List<E> {

	private List<E> list;

	public ObservableList() {
		this.list = new ArrayList<E>();
	}
	
	public ObservableList(Collection<? extends E> c) {
		this.list = new ArrayList<E>(c);
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.list.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.list.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		boolean ret = this.list.remove(o);
		this.setChanged();
		this.notifyObservers();
		return ret;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean ret = this.list.addAll(c);
		this.setChanged();
		this.notifyObservers();
		return ret;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean ret = this.list.addAll(index, c);
		this.setChanged();
		this.notifyObservers();
		return ret;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean ret = this.list.removeAll(c);
		this.setChanged();
		this.notifyObservers();
		return ret;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.list.retainAll(c);
	}

	@Override
	public E get(int index) {
		return this.list.get(index);
	}

	@Override
	public E set(int index, E element) {
		E ret = this.list.set(index, element);
		this.setChanged();
		this.notifyObservers();
		return ret;
	}

	@Override
	public void add(int index, E element) {
		this.list.add(index, element);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public E remove(int index) {
		E ret = this.list.remove(index);
		this.setChanged();
		this.notifyObservers();
		return ret;
	}

	@Override
	public int indexOf(Object o) {
		return this.list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.list.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return this.list.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return this.list.listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return this.list.subList(fromIndex, toIndex);
	}

	@Override
	public boolean add(E e) {
		boolean ret = this.list.add(e);
		this.setChanged();
		this.notifyObservers();
		return ret;
	}

	@Override
	public void clear() {
		this.list.clear();
		this.setChanged();
		this.notifyObservers();

	}
}
