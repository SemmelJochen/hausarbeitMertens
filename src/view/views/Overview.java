package view.views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

import controller.CommandController;
import view.components.ContentPane;

public class Overview extends ContentPane implements Observer {

	//final tab ids for references
	public static final int STUDENT_TAB_ID = 0;
	public static final int REVIEWER_TAB_ID = 1;
	
	private JTabbedPane tPane = new JTabbedPane();
	private StudentTable studentTab;
	private ReviewerTable reviewerTab;
	
	
	public Overview(CommandController commandController) {
		super();
		this.studentTab = new StudentTable(commandController);
		this.reviewerTab = new ReviewerTable(commandController);
		
		tPane.add(this.studentTab, STUDENT_TAB_ID);
		tPane.setTitleAt(STUDENT_TAB_ID, "Student");
		
		tPane.add(this.reviewerTab, REVIEWER_TAB_ID);
		tPane.setTitleAt(REVIEWER_TAB_ID, "Gutachter");
		
		
		this.setContent(tPane);
	}
	
	public void setActiveTab(int tabId) {
		tPane.setSelectedIndex(tabId);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.reviewerTab.update(o, arg);
		this.studentTab.update(o, arg);
		
	}
	
	


}
