package view.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.commandController.CommandController;
import model.ModelContainer;
import view.components.ContentPane;
import view.components.ReviewerComboBox;
import view.views.tables.AllRolesTable;
import view.views.tables.FirstRolesTable;
import view.views.tables.RequestedStudentsTable;
import view.views.tables.SecondRolesTable;

public class DetailedPeerReviewerOverview extends ContentPane implements Observer, PropertyChangeListener {

	public static final int ALLREVIEWS_TAB_ID = 0;
	public static final int FIRSTREVIEWRROLE_TAB_ID = 1;
	public static final int SECONDREVIEWERROLE_TAB_ID = 2;
	public static final int ASKINGSTUDENTS_TAB_ID = 3;
	
	private ReviewerComboBox comboBox;
	private JLabel subjects;

	private JTabbedPane tPane = new JTabbedPane();
	private AllRolesTable allReviews;
	private FirstRolesTable firstReviews;
	private SecondRolesTable secondReviews;
	private RequestedStudentsTable requestedStudents;

	public DetailedPeerReviewerOverview(CommandController commandController) {
		super();
		this.comboBox = new ReviewerComboBox(ModelContainer.getInstance().getPeerReviewers());
		this.comboBox.addCustomPropertyChangeListener(this);
		this.createTables(commandController);
		this.subjects = new JLabel("");

		this.initializeTabbedPane();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel overlay = new JPanel();
		overlay.add(this.comboBox);
		overlay.add(this.subjects);

		panel.add(overlay);
		panel.add(this.tPane);

		this.setHeader("Detailansicht");
		this.setContent(panel);
	}

	/**
	 * Observer for the data Model. Should get triggered if values at the Student
	 * or the PeerReviewer changes
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.comboBox.updateComboBoxModel(ModelContainer.getInstance().getPeerReviewers());

		this.update();
	}

	/**
	 * PropertyChangeListener for the ComboBox. 
	 * Gets triggered if the selectedValue in the comboBox changed
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.allReviews.updateSelectedPeerReviewer(this.comboBox.getSelectedPeerReviewer());
		this.firstReviews.updateSelectedPeerReviewer(this.comboBox.getSelectedPeerReviewer());
		this.secondReviews.updateSelectedPeerReviewer(this.comboBox.getSelectedPeerReviewer());
		this.requestedStudents.updateSelectedPeerReviewer(this.comboBox.getSelectedPeerReviewer());

		this.update();
		this.subjects.setText(this.getLabelText());
		this.subjects.revalidate();
		this.subjects.repaint();
	}

	private String getLabelText() {
		return "Auslastung: " +  (this.comboBox.getSelectedPeerReviewer().getLoad()) + "% - "
				+ this.comboBox.getSelectedPeerReviewer().getSubjects();
	}

	/**
	 * initial update method used for every kind of update.
	 * It updates the selected PeerReviewer at all tables and
	 * refreshes their table
	 */
	private void update() {
		this.allReviews.update();
		this.firstReviews.update();
		this.secondReviews.update();
		this.requestedStudents.update();
	}

	private void createTables(CommandController commandController) {
		this.firstReviews = new FirstRolesTable(commandController);
		this.secondReviews = new SecondRolesTable(commandController);
		this.requestedStudents = new RequestedStudentsTable(commandController);
		this.allReviews = new AllRolesTable(commandController);
	}

	private void initializeTabbedPane() {
		this.tPane.add(this.allReviews, ALLREVIEWS_TAB_ID);
		this.tPane.add(this.firstReviews, FIRSTREVIEWRROLE_TAB_ID);
		this.tPane.add(this.secondReviews, SECONDREVIEWERROLE_TAB_ID);
		this.tPane.add(this.requestedStudents, ASKINGSTUDENTS_TAB_ID);

		this.tPane.setTitleAt(ALLREVIEWS_TAB_ID, "Alle Gutachten");
		this.tPane.setTitleAt(FIRSTREVIEWRROLE_TAB_ID, "Erstgutachten");
		this.tPane.setTitleAt(SECONDREVIEWERROLE_TAB_ID, "Zweitgutachten");
		this.tPane.setTitleAt(ASKINGSTUDENTS_TAB_ID, "Anfragende");
	}
}