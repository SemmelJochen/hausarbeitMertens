package view.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.CommandController;
import model.ModelContainer;
import model.Student;
import view.components.ContentPane;
import view.components.ReviewerComboBox;

public class DetailedPeerReviewerOverview extends ContentPane implements Observer, PropertyChangeListener {

	public static final int ALLREVIEWS_TAB_ID = 0;
	public static final int FIRSTREVIEWRROLE_TAB_ID = 1;
	public static final int SECONDREVIEWERROLE_TAB_ID = 2;
	public static final int ASKINGSTUDENTS_TAB_ID = 3;
	
	private ReviewerComboBox comboBox;
	private JLabel subjects;

	private JTabbedPane tPane = new JTabbedPane();
	private ReducedTable allReviews;
	private ReducedTable firstReviews;
	private ReducedTable secondReviews;
	private ReducedTable askingStudents;

	public DetailedPeerReviewerOverview(CommandController commandController) {
		super();
		this.comboBox = new ReviewerComboBox(ModelContainer.getInstance().getPeerReviewers());
		this.comboBox.addCustomPropertyChangeListener(this);
		this.creatingTables(commandController);
		this.subjects = new JLabel("");

		this.fillingTPane();

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

	@Override
	public void update(Observable o, Object arg) {
		this.comboBox.updateComboBoxModel(ModelContainer.getInstance().getPeerReviewers());
		this.update();
		this.subjects.revalidate();
		this.subjects.repaint();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.update();
		this.subjects.setText(this.getLabelText());
		this.subjects.revalidate();
		this.subjects.repaint();
	}

	private String getLabelText() {
		return "Auslastung: " + (int) (this.comboBox.getSelectedPeerReviewer().getLoad() * 100) + "% - "
				+ this.comboBox.getSelectedPeerReviewer().getSubjects();
	}

	private void update() {

		ArrayList<Student> allStudents = this.getAllStudents();
		ArrayList<Student> askingStudents = this.getAskingStudents();

		this.allReviews.setList(allStudents);
		this.firstReviews.setList(this.comboBox.getSelectedPeerReviewer().getFirstPeerReviewerRoles());
		this.secondReviews.setList(this.comboBox.getSelectedPeerReviewer().getSecondPeerReviewerRoles());
		this.askingStudents.setList(askingStudents);

		this.allReviews.update();
		this.firstReviews.update();
		this.secondReviews.update();
		this.askingStudents.update();
	}

	private ArrayList<Student> getAskingStudents() {
		ArrayList<Student> askingStudents = new ArrayList<Student>();
		for (Student student : this.comboBox.getSelectedPeerReviewer().getRequested()) {
			askingStudents.add(student);
		}
		return askingStudents;
	}

	private ArrayList<Student> getAllStudents() {
		ArrayList<Student> allStudents = new ArrayList<Student>();
		allStudents.addAll(this.comboBox.getSelectedPeerReviewer().getFirstPeerReviewerRoles());
		allStudents.addAll(this.comboBox.getSelectedPeerReviewer().getSecondPeerReviewerRoles());
		return allStudents;
	}

	private void creatingTables(CommandController commandController) {
		this.firstReviews = new FirstRoleTable(commandController);
		this.secondReviews = new SecondRolesTable(commandController);
		this.askingStudents = new FreeStudents(commandController);
		this.allReviews = new AllRolesTable(commandController);
	}

	private void fillingTPane() {
		this.tPane.add(this.allReviews, ALLREVIEWS_TAB_ID);
		this.tPane.add(this.firstReviews, FIRSTREVIEWRROLE_TAB_ID);
		this.tPane.add(this.secondReviews, SECONDREVIEWERROLE_TAB_ID);
		this.tPane.add(this.askingStudents, ASKINGSTUDENTS_TAB_ID);

		this.tPane.setTitleAt(ALLREVIEWS_TAB_ID, "Alle Gutachten");
		this.tPane.setTitleAt(FIRSTREVIEWRROLE_TAB_ID, "Erstgutachten");
		this.tPane.setTitleAt(SECONDREVIEWERROLE_TAB_ID, "Zweitgutachten");
		this.tPane.setTitleAt(ASKINGSTUDENTS_TAB_ID, "Anfragende Studenten");
	}
}