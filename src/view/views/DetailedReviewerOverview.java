package view.views;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.ModelContainer;
import view.components.ContentPane;
import view.components.ReviewerComboBox;

public class DetailedReviewerOverview extends ContentPane implements Observer {

	private ReviewerComboBox comboBox;
	private JTabbedPane tabbedPane;
	private JPanel contentPane;

	public DetailedReviewerOverview() {
		super();
		this.contentPane = new JPanel();
		this.comboBox = new ReviewerComboBox(ModelContainer.getInstance().getPeerReviewers());
		
		this.setHeader(new JLabel("Gutachtereinsicht"));
		this.setContent(comboBox);
	}

	public DetailedReviewerOverview(JLabel header, Component content) {
		super(header, content);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.comboBox.updateComboBoxModel(ModelContainer.getInstance().getPeerReviewers());
	}
}