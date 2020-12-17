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

	private ReviewerComboBox comboBox;
	private JLabel subjects;

	private JTabbedPane tPane = new JTabbedPane();
	private ReducedTable reviews;
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
		return "Auslastung: " + (int) (this.comboBox.getSelectedPeerReviewer().getLoad() * 100) + "% - " +
				this.comboBox.getSelectedPeerReviewer().getSubjects();
	}

	private void update() {

		ArrayList<Student> allStudents = new ArrayList<>();
		allStudents.addAll(this.comboBox.getSelectedPeerReviewer().getFirstPeerReviewerRoles());
		allStudents.addAll(this.comboBox.getSelectedPeerReviewer().getSecondPeerReviewerRoles());

		this.reviews.setList(allStudents);
		this.firstReviews.setList(this.comboBox.getSelectedPeerReviewer().getFirstPeerReviewerRoles());
		this.secondReviews.setList(this.comboBox.getSelectedPeerReviewer().getSecondPeerReviewerRoles());

		ArrayList<Student> freeStudents = new ArrayList<>();
		for (Student student : ModelContainer.getInstance().getStudents()) {
			if (student.getFirstPeerReviewer().isDummy()) {
				freeStudents.add(student);
			}
		}
		this.askingStudents.setList(freeStudents);

		this.reviews.update();
		this.firstReviews.update();
		this.secondReviews.update();
		this.askingStudents.update();
	}

	private void creatingTables(CommandController commandController) {
		this.firstReviews = new FirstRoleTable(commandController, new ArrayList<Student>());
		this.secondReviews = new SecondRolesTable(commandController, new ArrayList<Student>());
		this.askingStudents = new FreeStudents(commandController, new ArrayList<Student>());
		this.reviews = new AllRolesTable(commandController, new ArrayList<Student>());
	}

	private void fillingTPane() {
		this.tPane.add(this.reviews, 0);
		this.tPane.add(this.firstReviews, 1);
		this.tPane.add(this.secondReviews, 2);
		this.tPane.add(this.askingStudents, 3);

		this.tPane.setTitleAt(0, "Alle Gutachten");
		this.tPane.setTitleAt(1, "Erstgutachten");
		this.tPane.setTitleAt(2, "Zweitgutachten");
		this.tPane.setTitleAt(3, "Freie Gutachten");
	}
}