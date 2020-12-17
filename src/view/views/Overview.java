package view.views;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

import controller.CommandController;
import model.ModelContainer;
import model.Student;
import view.components.ContentPane;

public class Overview extends ContentPane implements Observer {

	//final tab ids for references
	public static final int STUDENT_TAB_ID = 0;
	public static final int REVIEWER_TAB_ID = 1;
	public static final int FREESTUDENTS_TAB_ID = 2;
	
	private JTabbedPane tPane = new JTabbedPane();
	private StudentTable studentTab;
	private ReviewerTable reviewerTab;
	private ReducedTable freeStudents;
	
	
	public Overview(CommandController commandController) {
		super();
		this.studentTab = new StudentTable(commandController);
		this.reviewerTab = new ReviewerTable(commandController);
		this.freeStudents = new FreeStudents(commandController);
		
		tPane.add(this.studentTab, STUDENT_TAB_ID);
		tPane.setTitleAt(STUDENT_TAB_ID, "Student");
		
		tPane.add(this.reviewerTab, REVIEWER_TAB_ID);
		tPane.setTitleAt(REVIEWER_TAB_ID, "Gutachter");
		
		tPane.add(this.freeStudents, FREESTUDENTS_TAB_ID);
		tPane.setTitleAt(FREESTUDENTS_TAB_ID, "freie Studenten");
		
		this.setHeader("Gesamtuebersicht");
		this.setContent(tPane);
	}
	
	public void setActiveTab(int tabId) {
		tPane.setSelectedIndex(tabId);
	}

	@Override
	public void update(Observable o, Object arg) {

		this.freeStudents.setList(this.getFreeStudents());
		this.freeStudents.update();
		this.reviewerTab.update();
		this.studentTab.update();
		
	}
	
	private ArrayList<Student> getFreeStudents(){
		ArrayList<Student> freeStudents = new ArrayList<>();
		for (Student student : ModelContainer.getInstance().getStudents()) {
			if (student.getFirstPeerReviewer().isDummy()) {
				freeStudents.add(student);
			}
		}
		return freeStudents;
	}
	
	


}
