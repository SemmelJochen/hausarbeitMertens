package view.views;

import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ModelContainer;
import model.PeerReviewer;
import model.TableData;
import view.components.Table;

public class SecondReviewerOverview extends JPanel {

	private TableData tableData;
	private Table table;

	public SecondReviewerOverview() {
		super();
		this.add(buildTable());
	}

	public JScrollPane buildTable() {
		refreshTableData();

		table = new Table(tableData);
		return table.getContent();
	}

	public void refreshTableData() {
		List<PeerReviewer> secondReviewer = ModelContainer.getInstance().getPeerReviewers();
		this.tableData = TableData.builder()
				.withColumn("Titel", secondReviewer.stream().map(e -> e.getTitle()).collect(Collectors.toList()))//
				.withColumn("Vorname", secondReviewer.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn("Nachname", secondReviewer.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn("E-Mail", secondReviewer.stream().map(e -> e.getEmail()).collect(Collectors.toList()))//
				.withColumn("Kapazitaet",
						secondReviewer.stream().map(e -> e.getCapacity()).collect(Collectors.toList()))//
				.withColumn("Erstgutachter",
						secondReviewer.stream().map(e -> e.getFirstName() + " " + e.getName())
								.collect(Collectors.toList()))//
				.build();

	}

	public void update(Observable o, Object arg) {
		refreshTableData();
		this.table.refreshData(tableData);
	}
}