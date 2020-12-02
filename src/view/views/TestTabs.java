package view.views;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class TestTabs {

	public static void main(String[] args) {
		JFrame st = new JFrame();
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel1 = makeTextPanel("Panel #1");
		tabbedPane.addTab("Studenten", panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		JComponent panel2 = makeTextPanel("Panel #2");
		tabbedPane.addTab("Erstgutachter", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		JComponent panel3 = makeTextPanel("Panel #3");
		tabbedPane.addTab("Zweitgutachter", panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		st.add(tabbedPane);
		st.setVisible(true);
		
		//Table table1 = new Table(studentOverview.studentsOverview());
		//ContentPane contentPane = new ContentPane(new JLabel("Studentenuebersicht") ,table1.getContent());
			
		//Table table2 = new Table(firstReviewer.firstReviewerOverview());
		//ContentPane contentPane = new ContentPane(new JLabel("Erstgutachteruebersicht") ,table2.getContent());
				
		//Table table3 = new Table(secondReviewer.secondReviewerOverview());
		//ContentPane contentPane = new ContentPane(new JLabel("Zweitgutachteruebersicht") ,table3.getContent());			
	}

	private static JComponent makeTextPanel(String string) {
		// TODO Auto-generated method stub
		JLabel tab = new JLabel();
		
		return tab;
	}

}
