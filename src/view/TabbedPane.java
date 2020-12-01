package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPane extends JTabbedPane {

	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private Studentenuebersicht studentenuebersicht;
	
	public void setStudentenUebersicht(Studentenuebersicht studentenuebersicht) {
		this.studentenuebersicht = studentenuebersicht;
	}
	
	public void setTabbedPane() {
		JFrame st = new JFrame();
	
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(100, 100, 500, 500);
		tabbedPane.add("Student", p1);	
		tabbedPane.add("Erstgutachter",p2);
		tabbedPane.add("Zweitgutachter",p3);
		
		st.add(tabbedPane);
		st.setVisible(true);
	}
	
	public void setStudentTable() {
		Studentenuebersicht studentenuebersicht = new Studentenuebersicht();
		
		Table table1 = new Table(studentenuebersicht.studentenUebersicht());
		ContentPane contentPane = new ContentPane(new JLabel("Studentenübersicht") ,table1.getContent());
		
		p1.add(contentPane);
	}
	
	
}
