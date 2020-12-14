package view.components;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import controller.ObservableList;
import model.ModelContainer;
import model.Student;
import model.TableData;

public class Table {

	private CustomTableModel model;
	private JTable table;

	public Table(TableData tableData) {
		super();
		// insert data into the tablemodel and create a table
		this.model = new CustomTableModel(tableData);
		this.table = new JTable(this.model);
		this.createMouseListener();
		// style the table
		this.table.setRowHeight(30);

		TableColumnModel column = this.table.getColumnModel();
		column.getColumns().asIterator().forEachRemaining(c -> c.setPreferredWidth(100));

	}

	public void createMouseListener() {
		this.table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// Do nothing
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				// TODO: ermitteln, ob ein Student oder Gutachter gesuscht wird

				String firstName = (String) Table.this.model.getValueAt(Table.this.table.getSelectedRow(), 1);
				String name = (String) Table.this.model.getValueAt(Table.this.table.getSelectedRow(), 2);
				Table.this.openEditStudentDialog(Table.this.findStudent(name, firstName));

			}
		});
	}

	private void openEditStudentDialog(Student student) {
		JFrame editDialog = new JFrame();
		editDialog.setSize(new Dimension(450, 450));
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		content.add(this.createElement("Vorname: ", student.getFirstName()));
		content.add(this.createElement("Nachname: ", student.getName()));
		content.add(this.createElement("Studiengruppe: ", student.getStudentGroup()));
		content.add(this.createElement("Praxispartner: ", student.getPracticePartner()));
		content.add(this.createElement("Thema: ", student.getSubject()));
		content.add(this.createElement("E-mail: ", student.getEmail()));
		

		content.setAlignmentX(JPanel.LEFT_ALIGNMENT);

		editDialog.add(content);
		editDialog.setResizable(false);
		editDialog.setVisible(true);
	}

	private JPanel createElement(String attributeName, String attribute) {
		JPanel element = new JPanel();
		element.setLayout(new BoxLayout(element, BoxLayout.X_AXIS));
		
		JLabel firstName = new JLabel(attributeName);
		firstName.setPreferredSize(new Dimension(200, 20));
		element.add(firstName);

		JTextField firstNameAttribute = new JTextField(attribute);

		firstNameAttribute.setPreferredSize(new Dimension(250, 20));
		element.add(firstNameAttribute);

		return element;
	}

	/*
	 * finds the student checking the name and firstName(s)
	 */
	private Student findStudent(String name, String firstName) {

		boolean found = false;

		ObservableList<Student> students = ModelContainer.getInstance().getStudents();
		for (int i = 0; i < students.size() && !found; i++) {
			String firstnameToCheck = students.get(i).getFirstName();
			String nameToCheck = students.get(i).getName();

			if (firstName.equals(firstnameToCheck) && name.equals(nameToCheck)) {
				return students.get(i);
			}
		}
		return new Student("", "", "", "", "", "", "");
	}

	public void refreshData(TableData tableData) {
		this.model.updateData(tableData);
	}

	public JScrollPane getContent() {
		JScrollPane pane = new JScrollPane(this.table);
		pane.setPreferredSize(new Dimension(800, 600));
		return pane;
	}

	class CustomTableModel extends AbstractTableModel {

		private String[] columnNames;
		private List<Object>[] data;

		public CustomTableModel(TableData tableData) {
			this.columnNames = tableData.getColumnNames();
			this.data = tableData.getContent();
		}

		public int getColumnCount() {
			return this.data.length;
		}

		public int getRowCount() {
			return this.data[0].size();
		}

		public String getColumnName(int col) {
			return this.columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return this.data[col].get(row);
		}

		// data change event
		public void setValueAt(String value, int row, int col) {
			this.data[col].set(row, value);
			fireTableCellUpdated(row, col);
			//TODO: change model, too
		}

		public void updateData(TableData tableData) {
			this.data = tableData.getContent();
			this.fireTableDataChanged();
		}
	}
}
