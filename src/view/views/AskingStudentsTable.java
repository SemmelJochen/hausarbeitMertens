package view.views;

import static model.TableData.newTableData;

import java.util.List;
import java.util.stream.Collectors;

import controller.CommandController;
import model.Student;
import model.StudentColumn;

public class AskingStudentsTable extends ReducedTable{

	public AskingStudentsTable(CommandController commandController) {
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
				.withMetaData(students)//
				.build();
	}

}
