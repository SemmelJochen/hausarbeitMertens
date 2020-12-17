package view.components;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.CommandController;
import controller.PeerReviewerAddCommand;
import controller.StudentAddCommand;
import controller.TableController;
import model.CustomTableModel;
import model.PeerReviewer;
import model.Student;
import model.TableData;

public class Table extends JPanel implements TableModelListener {

	private CustomTableModel model;
	private TableController controller;
	private CustomCellEditor cellEditor;
	private JTable table;
	private JButton addButton, removeButton;
	private JDialog addDialog;

	public Table(TableData<?> tableData, CommandController commandController) {
		super();
		// insert data into the tablemodel and create a table
		this.model = new CustomTableModel(tableData);
		this.controller = new TableController(this, commandController);
		this.cellEditor = new CustomCellEditor(this);
		
		this.table = new JTable(this.model) {
			// make columns fit to its content
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth((int) Math.max(rendererWidth + getIntercellSpacing().getWidth(),
						tableColumn.getPreferredWidth()));
				return component;
			}
		};
		
		// prevent unauthorised actions
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.setCellSelectionEnabled(true);
		this.table.setCellEditor(this.cellEditor);
		this.table.getModel().addTableModelListener(this);
		
		// style the table
		this.table.setAutoCreateRowSorter(false);
		this.table.setRowHeight(30);
		TableColumnModel column = this.table.getColumnModel();
		column.getColumns().asIterator().forEachRemaining(c -> c.setCellEditor(this.cellEditor));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getTableContent());
		this.add(getButtonPanel());

	}

	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();
		this.addButton = new JButton("Hinzufügen");
		this.removeButton = new JButton("Löschen");
		this.removeButton.setEnabled(this.model.hasData());
		this.addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Table.this.openDialog();
			}
		});
		this.removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Table.this.getTableRef().getSelectedRow() >= 0) {

				}
			}
		});
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(this.addButton);
		buttonPanel.add(this.removeButton);
		return buttonPanel;
	}

	public void refreshData(TableData<?> tableData) {
		this.model.updateData(tableData);
	}

	private JScrollPane getTableContent() {
		JScrollPane pane = new JScrollPane(this.table);
		pane.setPreferredSize(new Dimension(1000, 580));
		return pane;
	}

	public JTable getTableRef() {
		return this.table;
	}

	private void openDialog() {
		this.addDialog = buildDialog();
		this.addDialog.setVisible(true);
	}

	private JDialog buildDialog() {
		this.addDialog = new JDialog();
		this.addDialog.setTitle("Datensatz hinzufügen");
		this.addDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		this.addDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		String[] columnNames = this.model.getColumnnNames();
		JPanel dialogPane = new JPanel();
		dialogPane.setLayout(new BoxLayout(dialogPane, BoxLayout.Y_AXIS));
		JLabel label;
		JPanel rowPanel;
		JTextField inputField[] = new JTextField[columnNames.length];
		for (int i = 0; i < columnNames.length; i++) {
			rowPanel = new JPanel();
			rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));

			label = new JLabel(columnNames[i] + ":", SwingConstants.RIGHT);
//			if(columnNames[i].equals(StudentColumn.FIRST_REVIEWER.getValue()) || columnNames[i].equals(StudentColumn.SECOND_REVIEWER.getValue())) {
//				ReviewerComboBox comboBox = new ReviewerComboBox();
////				comboBox.getSelectedPeerReviewer();
//				rowPanel.add(label);
//				rowPanel.add(comboBox);
//			}else {
				inputField[i] = new JTextField("");
				inputField[i].setPreferredSize(new Dimension(100, 25));				
				rowPanel.add(label);
				rowPanel.add(inputField[i]);
//			}

			dialogPane.add(rowPanel);
		}
		JButton submitBtt, cancelBtt;
		submitBtt = new JButton("Bestaetigen");
		cancelBtt = new JButton("Abbrechen");
		cancelBtt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Table.this.addDialog.dispose();
			}
		});
		submitBtt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] entries = new String[columnNames.length];
				for (int i = 0; i < columnNames.length; i++) {
					entries[i] = inputField[i].getText();
				}
				Table.this.submitNewEntry(entries);
				Table.this.addDialog.dispose();
			}
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(cancelBtt);
		buttonPanel.add(submitBtt);
		dialogPane.add(buttonPanel);
		dialogPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.addDialog.add(dialogPane);
		this.addDialog.setResizable(false);
		this.addDialog.setLocationRelativeTo(this);
		this.addDialog.pack();
		return this.addDialog;
	}

	private void submitNewEntry(Object[] entries) {
		
		if(this.model.getType() == Student.class) {
			Student student = new Student("","","","","","","");
			student.setStudentGroup((String) entries[0]);
			student.setFirstName((String)entries[1]);
			student.setName((String) entries[2]);
			student.setEmail((String) entries[3]);
			student.setSubject((String) entries[4]);
			student.setPracticePartner((String) entries[5]);
			student.setRemark((String) entries[6]);
			student.setFirstPeerReviewer("");
			student.setSecondPeerReviewer("");
			Table.this.getController().executeDataUpdate(new StudentAddCommand(this, student));
		}
		if(this.model.getType() == PeerReviewer.class) {
			PeerReviewer reviewer = PeerReviewer.createDummy();
			reviewer.setTitle((String) entries[0]);
			reviewer.setFirstName((String)entries[1]);
			reviewer.setName((String) entries[2]);
			reviewer.setEmail((String) entries[3]);
			int capacity = (((String)entries[4]).isBlank() ? 0 : Integer.parseInt((String) entries[4]));
			reviewer.setCapacity(capacity);
			Table.this.getController().executeDataUpdate(new PeerReviewerAddCommand(this, reviewer));
		}

	}
	
	public TableController getController() {
		return this.controller;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
	}
}
