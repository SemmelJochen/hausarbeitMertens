package view.views;

import static model.TableData.newTableData;

import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ModelContainer;
import model.PeerReviewer;
import model.ReviewerColumn;
import model.TableData;
import view.components.Table;

public class FirstReviewerOverview extends JPanel {

	private TableData tableData;
	private Table table;

	public FirstReviewerOverview() {
		super();
		this.add(buildTable());
	}

	public JScrollPane buildTable() {
		refreshTableData();

		table = new Table(tableData);
		return table.getContent();
	}

	public void refreshTableData() {
		List<PeerReviewer> firstReviewer = ModelContainer.getInstance().getPeerReviewers();
		this.tableData = newTableData()
				.withColumn(ReviewerColumn.TITLE,
						firstReviewer.stream().map(e -> e.getTitle()).collect(Collectors.toList()))//
				.withColumn(ReviewerColumn.FIRSTNAME,
						firstReviewer.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn(ReviewerColumn.LASTNAME,
						firstReviewer.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn(ReviewerColumn.E_MAIL,
						firstReviewer.stream().map(e -> e.getEmail()).collect(Collectors.toList()))//
				.withColumn(ReviewerColumn.CAPACITY,
						firstReviewer.stream().map(e -> e.getCapacity()).collect(Collectors.toList()))//
				.withMetaData(firstReviewer)//
				.build();
	}

	public void update(Observable o, Object arg) {
		refreshTableData();
		this.table.refreshData(tableData);
	}

}
