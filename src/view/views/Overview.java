package view.views;

import java.awt.Component;

import javax.swing.JTabbedPane;

import view.components.ContentPane;

public class Overview extends ContentPane{

	private StudentOverview studentOverview;
	private FirstReviewerOverview firstReviewer;
	private SecondReviewerOverview secondReviewer;
	private MainWindow mainWindow;
	
	public void setMainWindow (MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void setStudentOverview (StudentOverview studentOverview) {
		this.studentOverview = studentOverview;
	}
	
	public void setFirstReviewer (FirstReviewerOverview firstReviewer) {
		this.firstReviewer = firstReviewer;
	}
	
	public void setSecondReviewer (SecondReviewerOverview secondReviewer) {
		this.secondReviewer = secondReviewer;
	}
	
	public Overview () {
		super();
		
		this.setContent(buildTab());
	}
	
	public Component buildTab() {
		
		StudentOverview student = new StudentOverview();
		FirstReviewerOverview first = new FirstReviewerOverview();
		SecondReviewerOverview second = new SecondReviewerOverview();
		
		JTabbedPane tPane = new JTabbedPane();
		
		tPane.add("Student", student.buildComponents());
		tPane.add("Erstgutachter", first.buildComponents());
		tPane.add("Zweitgutachter", second.buildComponents());
		return tPane;		
	}

}
