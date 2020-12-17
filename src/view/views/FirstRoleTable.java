package view.views;

import static model.TableData.newTableData;

import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import controller.CommandController;
import model.ModelContainer;
import model.PeerReviewer;
import model.ReviewerColumn;
import model.Student;
import model.StudentColumn;
import model.TableData;
import view.components.Table;

public class FirstRoleTable extends ReducedTable {

	public FirstRoleTable(CommandController commandController) {
		super(commandController);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refreshTableData() {
		this.tableData = newTableData()
				.withColumn(StudentColumn.STUDENT_GROUP,
						students.stream().map(e -> e.getStudentGroup()).collect(Collectors.toList()))//
				.withColumn(StudentColumn.FIRSTNAME,
						students.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn(StudentColumn.LASTNAME,
						students.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn(StudentColumn.E_MAIL, students.stream().map(e -> e.getEmail()).collect(Collectors.toList()))//
				.withColumn(StudentColumn.SUBJECT,
						students.stream().map(e -> e.getSubject()).collect(Collectors.toList()))//
				.withColumn(StudentColumn.PRACTICE_PARTNER,
						students.stream().map(e -> e.getPracticePartner()).collect(Collectors.toList()))//
				.withColumn(StudentColumn.REMARK,
						students.stream().map(e -> e.getRemark()).collect(Collectors.toList()))//
				.withColumn(StudentColumn.SECOND_REVIEWER, students.stream()
						.map(e -> e.getSecondPeerReviewer())
						.collect(Collectors.toList()))//
				.withMetaData(students)//
				.build();
	}
}
