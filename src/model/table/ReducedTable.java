package model.table;

import javax.swing.JPanel;

import controller.CommandController;
import model.PeerReviewer;
import view.components.Table;

public abstract class ReducedTable extends JPanel {

	/*
	 * The ReducedTable is a table, which don´t show all attributes 
	 * of a Student or PeerReviewer.
	 * 
	 * Which attributes are presented, is definded in the 
	 * refreshTableData() method in the specific child.
	 */
	
	
	protected TableData<?> tableData;
	protected Table table;
	protected PeerReviewer selectedPeerReviewer;

	public ReducedTable(CommandController commandController) {
		super();
		this.add(buildTable(commandController));
	}

	public JPanel buildTable(CommandController commandController) {
		refreshTableData();
		table = new Table(tableData, commandController, false);
		return table;
	}

	public abstract void refreshTableData();

	public void update() {
		refreshTableData();
		this.table.refreshData(tableData);
	}
	
	public void updateSelectedPeerReviewer(PeerReviewer p) {
		this.selectedPeerReviewer = p;
	}
}
