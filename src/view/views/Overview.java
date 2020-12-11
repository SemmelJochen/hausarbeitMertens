package view.views;

import javax.swing.JTabbedPane;

import view.components.ContentPane;

public class Overview extends ContentPane {

	//final tab ids for references
	public static final int STUDENT_TAB_ID = 0;
	public static final int FIRSTREVIEWER_TAB_ID = 1;
	public static final int SECONDREVIEWER_TAB_ID = 2;
	
	private JTabbedPane tPane = new JTabbedPane();
	
	
	
	public Overview() {
		super();
		StudentOverview student = new StudentOverview();
		FirstReviewerOverview first = new FirstReviewerOverview();
		SecondReviewerOverview second = new SecondReviewerOverview();
		
		tPane.add(student.buildComponents(), STUDENT_TAB_ID);
		tPane.setTitleAt(STUDENT_TAB_ID, "Student");
		
		tPane.add(first.buildComponents(), FIRSTREVIEWER_TAB_ID);
		tPane.setTitleAt(FIRSTREVIEWER_TAB_ID, "Erstgutachter");
		
		tPane.add(second.buildComponents(), SECONDREVIEWER_TAB_ID);
		tPane.setTitleAt(SECONDREVIEWER_TAB_ID, "Zweitgutachter");
		
		this.setContent(tPane);
	}
	
	public void setActiveTab(int tabId) {
		tPane.setSelectedIndex(tabId);
	}
	
	


}
