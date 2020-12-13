package view.views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

import view.components.ContentPane;

public class Overview extends ContentPane implements Observer {

	//final tab ids for references
	public static final int STUDENT_TAB_ID = 0;
	public static final int FIRSTREVIEWER_TAB_ID = 1;
	public static final int SECONDREVIEWER_TAB_ID = 2;
	
	private JTabbedPane tPane = new JTabbedPane();
	private StudentOverview studentOverview;
	private FirstReviewerOverview firstReviewerOverview;
	private SecondReviewerOverview secondReviewerOverview;
	
	
	public Overview() {
		super();
		this.studentOverview = new StudentOverview();
		this.firstReviewerOverview = new FirstReviewerOverview();
		this.secondReviewerOverview = new SecondReviewerOverview();
		
		tPane.add(this.studentOverview, STUDENT_TAB_ID);
		tPane.setTitleAt(STUDENT_TAB_ID, "Student");
		
		tPane.add(this.firstReviewerOverview, FIRSTREVIEWER_TAB_ID);
		tPane.setTitleAt(FIRSTREVIEWER_TAB_ID, "Erstgutachter");
		
		tPane.add(this.secondReviewerOverview, SECONDREVIEWER_TAB_ID);
		tPane.setTitleAt(SECONDREVIEWER_TAB_ID, "Zweitgutachter");
		
		this.setContent(tPane);
	}
	
	public void setActiveTab(int tabId) {
		tPane.setSelectedIndex(tabId);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.firstReviewerOverview.update(o, arg);
		this.secondReviewerOverview.update(o, arg);
		this.studentOverview.update(o, arg);
		
	}
	
	


}
