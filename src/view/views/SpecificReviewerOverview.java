package view.views;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import model.ModelContainer;
import model.PeerReviewer;
import view.components.ContentPane;

public class SpecificReviewerOverview extends ContentPane implements Observer {

	private JComboBox<PeerReviewer> reviewerComboBox;
	private ReviewerComboBoxModel comboBoxModel;
	private JTabbedPane tabbedPane;
	private JPanel contentPane;

	public SpecificReviewerOverview() {
		super();
		this.contentPane = new JPanel();
		this.comboBoxModel = new ReviewerComboBoxModel();
		this.reviewerComboBox = new JComboBox<PeerReviewer>(this.comboBoxModel);
		this.reviewerComboBox.setRenderer(new ReviewerListCellRenderer());
		
		this.updateComboBoxModel();
		
		this.setHeader(new JLabel("Gutachtereinsicht"));
		this.setContent(reviewerComboBox);

	}

	public void updateComboBoxModel() {
		ArrayList<PeerReviewer> reviewer = ModelContainer.getInstance().getPeerReviewers();
		this.comboBoxModel.updateData(reviewer);
		this.reviewerComboBox.updateUI();
	}

	public SpecificReviewerOverview(Component header, Component content) {
		super(header, content);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.updateComboBoxModel();
	}

	class ReviewerListCellRenderer extends BasicComboBoxRenderer {

		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			if(value != null) {
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
			if(this.entries.size() > 0) {
				this.index = 1;				
			}else {
				this.index = -1;
			}
			
		}

	}

}
