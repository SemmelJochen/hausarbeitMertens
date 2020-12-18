package view.components;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;

import model.PeerReviewer;

public class ReviewerComboBox extends JComboBox<PeerReviewer> implements PropertyChangeListener {

	private ReviewerComboBoxModel comboBoxModel;
	private ReviewerListCellRenderer comboBoxRenderer;
	private PeerReviewer selectedPeerReviewer;
	private PropertyChangeSupport propertyChangeSupport;

	public ReviewerComboBox() {
		this(new ArrayList<PeerReviewer>());
	}

	public ReviewerComboBox(ArrayList<PeerReviewer> reviewerData) {
		this.comboBoxModel = new ReviewerComboBoxModel();
		this.comboBoxModel.addPropertyChangeListener(this);
		this.setUI(new BasicComboBoxUI());

		this.comboBoxRenderer = new ReviewerListCellRenderer();
		this.selectedPeerReviewer = PeerReviewer.createDummy();
		this.propertyChangeSupport = new PropertyChangeSupport(this.selectedPeerReviewer);
		this.setModel(this.comboBoxModel);
		this.setRenderer(this.comboBoxRenderer);
		this.updateComboBoxModel(reviewerData);
	}

	public void updateComboBoxModel(ArrayList<PeerReviewer> reviewerData) {
		this.comboBoxModel.updateData(reviewerData);
		this.updateUI();
	}

	public PeerReviewer getSelectedPeerReviewer() {
		return this.selectedPeerReviewer;
	}

	public void setSelectedPeerReviewer(PeerReviewer p) {
		PeerReviewer oldValue = this.selectedPeerReviewer;
		this.selectedPeerReviewer = p;
		this.propertyChangeSupport.firePropertyChange("peerReviewer", oldValue, p);
	}

	public void addCustomPropertyChangeListener(PropertyChangeListener l) {
		this.propertyChangeSupport.addPropertyChangeListener(l);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.setSelectedPeerReviewer((PeerReviewer) this.comboBoxModel.getSelectedItem());

	}

	class ReviewerListCellRenderer extends BasicComboBoxRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				String name = (((PeerReviewer) value).getFirstName() + " " + ((PeerReviewer) value).getName());
				int freeReviews = (((PeerReviewer) value).getFreeReviews());
				if (name.length() > 1) {
					setText(name + " - " + freeReviews);
				} else {
					setText(" - ");
				}

			}
			return this;
		}
	}

	class ReviewerComboBoxModel implements MutableComboBoxModel<PeerReviewer> {

		private List<PeerReviewer> entries;
		private int index = -1;
		private PropertyChangeSupport propertyChangeSupport;

		public ReviewerComboBoxModel() {
			this(new ArrayList<PeerReviewer>());
			this.propertyChangeSupport = new PropertyChangeSupport(this.index);
		}

		public ReviewerComboBoxModel(ArrayList<PeerReviewer> entries) {
			super();
			this.entries = new ArrayList<PeerReviewer>();
			this.entries.add(PeerReviewer.createDummy());
			this.entries.addAll(entries);
			if (entries.size() > 0) {
				this.setIndex(0);
			}
		}

		@Override
		public void setSelectedItem(Object obj) {
			for (PeerReviewer r : this.entries) {
				if (r.equals(obj)) {
					this.setIndex(this.entries.indexOf(r));
					break;
				}
			}
		}

		@Override
		public Object getSelectedItem() {
			if (index >= 0) {
				return this.entries.get(this.getIndex());
			} else {
				return null;
			}
		}

		@Override
		public int getSize() {
			return this.entries.size();
		}

		@Override
		public PeerReviewer getElementAt(int index) {
			if (index >= 0) {
				return this.entries.get(index);
			} else {
				return null;
			}
		}

		@Override
		public void addListDataListener(ListDataListener l) {
		}

		@Override
		public void removeListDataListener(ListDataListener l) {

		}

		@Override
		public void addElement(PeerReviewer item) {
			this.entries.add(item);
		}

		@Override
		public void removeElement(Object obj) {
			this.entries.remove(obj);
		}

		@Override
		public void insertElementAt(PeerReviewer item, int index) {
			this.entries.add(index, item);

		}

		@Override
		public void removeElementAt(int index) {
			this.entries.remove(index);

		}

		public void updateData(List<PeerReviewer> data) {
			this.entries.clear();
			this.entries.add(PeerReviewer.createDummy());
			this.entries.addAll(data);
			if (this.index > this.entries.size()) {
				this.setIndex(0);
			}
		}

		private int getIndex() {
			return index;
		}

		private void setIndex(int newIndex) {
			int oldIndex = this.index;
			if (oldIndex != newIndex) {
				this.index = newIndex;
				this.propertyChangeSupport.firePropertyChange("index", oldIndex, newIndex);
			}
		}

		public void addPropertyChangeListener(PropertyChangeListener listener) {
			this.propertyChangeSupport.addPropertyChangeListener(listener);
		}
	}
}
