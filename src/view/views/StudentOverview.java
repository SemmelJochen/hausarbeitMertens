package view.views;

import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ModelContainer;
import model.Student;
import model.TableData;
import view.components.Table;

public class StudentOverview extends JPanel {

	private TableData tableData;
	private Table table;

	public StudentOverview() {
		super();
		this.add(buildTable());
	}

	public JScrollPane buildTable() {
		refreshTableData();
		table = new Table(tableData);
		return table.getContent();
	}

	public void refreshTableData() {
		List<Student> students = ModelContainer.getInstance().getStudents();
		this.tableData = TableData.builder()
				.withColumn("Studiengruppe",
						students.stream().map(e -> e.getStudentGroup()).collect(Collectors.toList()))//
				.withColumn("Vorname", students.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn("Nachname", students.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn("E-Mail", students.stream().map(e -> e.getEmail()).collect(Collectors.toList()))//
				.withColumn("Thema", students.stream().map(e -> e.getSubject()).collect(Collectors.toList()))//
				.withColumn("Praxispartner",
						students.stream().map(e -> e.getPracticePartner()).collect(Collectors.toList()))//
				.withColumn("Erstgutachter", students.stream()
						.map(e -> e.getFirstPeerReviewer().getFirstName() + " " + e.getFirstPeerReviewer().getName())
						.collect(Collectors.toList()))//
				.withColumn("Zweitgutachter", students.stream()
							.map(e -> e.getSecondPeerReviewer().getFirstName() + " " + e.getSecondPeerReviewer().getName())
						.collect(Collectors.toList()))//
				.build();
	}
	
	public void update(Observable o, Object arg) {
		refreshTableData();
		this.table.refreshData(tableData);
	}

}
