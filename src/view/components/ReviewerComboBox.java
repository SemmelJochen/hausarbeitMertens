package view.components;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import model.PeerReviewer;

public class ReviewerComboBox extends JComboBox<PeerReviewer> {

	private ReviewerComboBoxModel comboBoxModel;
	private ReviewerListCellRenderer comboBoxRenderer;

	public ReviewerComboBox() {
		this(new ArrayList<PeerReviewer>());
	}
	
	public ReviewerComboBox(ArrayList<PeerReviewer> reviewerData) {
		this.comboBoxModel = new ReviewerComboBoxModel();
		this.comboBoxRenderer = new ReviewerListCellRenderer();
		this.setModel(this.comboBoxModel);
		this.setRenderer(this.comboBoxRenderer);

		this.updateComboBoxModel(reviewerData);
	}

	public void updateComboBoxModel(ArrayList<PeerReviewer> reviewerData) {
		this.comboBoxModel.updateData(reviewerData);
		this.updateUI();
	}

	class ReviewerListCellRenderer extends BasicComboBoxRenderer {

		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				int cap = ((PeerReviewer) value).getCapacity();
				String name = ((PeerReviewer) value).getFirstName() + " " + ((PeerReviewer) value).getName();
				String capacity = Integer.toString(cap);
				if (cap < 0) {
					capacity = "( " + capacity + ")";
				}
				setText(name + " - " + capacity);

			}

			return this;
		}

	}

	class ReviewerComboBoxModel implements MutableComboBoxModel<PeerReviewer> {

		private List<PeerReviewer> entries;
		int index = -1;

		public ReviewerComboBoxModel() {
			this(new ArrayList<PeerReviewer>());
		}

		public ReviewerComboBoxModel(ArrayList<PeerReviewer> entries) {
			super();
			this.entries = entries;
			if (entries.size() > 0) {
				this.index = 1;
			}
		}

		@Override
		public void setSelectedItem(Object obj) {
			for (PeerReviewer r : this.entries) {
				if (r.equals(obj)) {
					this.index = this.entries.indexOf(r);
					break;
				}
			}
		}

		@Override
		public Object getSelectedItem() {
			if (index >= 0) {
				return this.entries.get(this.index);
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
			this.entries.addAll(data);
			if (this.entries.size() > 0) {
				this.index = 1;
			} else {
				this.index = -1;
			}

		}

	}
}
