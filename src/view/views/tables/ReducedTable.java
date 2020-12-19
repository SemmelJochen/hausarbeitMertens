package view.views.tables;

import javax.swing.JPanel;

import controller.commandController.CommandController;
import model.PeerReviewer;
import model.table.TableData;
import view.components.Table;

/**
 * The ReducedTable is a table, which packs the update method and basic styling
 * into one place. It also provides a "smaller" version of the normal table. The
 * opportunity to add or remove entries is cutted. On the other hand it also
 * provides functionality. It provides a ComboBox in which you can select
 * different peerReviewers.
 * 
 * Which attributes are presented, is defined in the refreshTableData() method
 * in the specific child.
 * 
 * @author felix
 *
 */
public abstract class ReducedTable extends JPanel {

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

	/**
	 * abstract method to update the table.
	 */
	public abstract void refreshTableData();

	public void update() {
		refreshTableData();
		this.table.refreshData(tableData);
	}

	/**
	 * 
	 * @param peerReviewer
	 */
	public void updateSelectedPeerReviewer(PeerReviewer peerReviewer) {
		this.selectedPeerReviewer = peerReviewer;
	}
}
