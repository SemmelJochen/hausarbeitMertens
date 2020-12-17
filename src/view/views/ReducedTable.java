package view.views;

import java.util.List;
import java.util.Observable;

import javax.swing.JPanel;

import controller.CommandController;
import model.Student;
import model.TableData;
import view.components.Table;

public abstract class ReducedTable extends JPanel {

	@SuppressWarnings("rawtypes")
	protected TableData tableData;
	protected Table table;
	protected List<Student> students;

	public ReducedTable(CommandController commandController, List<Student> students) {
		super();
		this.students = students;
		this.add(buildTable(commandController));
	}

	public JPanel buildTable(CommandController commandController) {
		refreshTableData();

		table = new Table(tableData, commandController);
		return table;
	}

	public abstract void refreshTableData();

	public void update() {
		refreshTableData();
		this.table.refreshData(tableData);
	}
	
	public void setList(List<Student> students) {
		this.students = students;
	}
}
