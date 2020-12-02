package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPane extends JTabbedPane {

	private JPanel tab = new JPanel();

	private StudentOverview studentoverview;
	
	public void setStudentOverview(StudentOverview studentOverview) {
		this.studentoverview = studentOverview;
	}
	
	public void setTabbedPane1() {
		JFrame st = new JFrame();
	
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(100, 100, 500, 500);
		tabbedPane.add("Student", tab);	
		//tabbedPane.add("Erstgutachter",tab2);
		//tabbedPane.add("Zweitgutachter",tab3);
		
		st.add(tabbedPane);
		st.setVisible(true);
		
		StudentOverview studentOverview = new StudentOverview();
		
		Table table1 = new Table(studentOverview.studentsOverview());
		ContentPane contentPane = new ContentPane(new JLabel("Studentenübersicht") ,table1.getContent());
		
		tab.add(contentPane);
	}
	
	public TabbedPane() {
		
	}
	
	public void setTabbedPane(Component c) {
		this.tab.removeAll();
		this.tab.add(c);
	}
	
	
}
