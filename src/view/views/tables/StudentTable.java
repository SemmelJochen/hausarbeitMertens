package view.views.tables;

import static model.table.TableData.newTableData;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import controller.command.CommandController;
import model.ModelContainer;
import model.Student;
import model.table.CellEditorType;
import model.table.StudentColumn;
import model.table.TableData;
import view.components.table.Table;

public class StudentTable extends JPanel {

	private TableData<Student> tableData;
	private Table table;

	public StudentTable(CommandController commandController) {
		super();
		this.add(buildTable(commandController));
	}

	public JPanel buildTable(CommandController commandController) {
		refreshTableData();
		table = new Table(tableData, commandController, true);
		return table;
	}

	@SuppressWarnings("unchecked")
	public void refreshTableData() {
		List<Student> students = ModelContainer.getInstance().getStudents();
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

				.withColumn(StudentColumn.FIRST_REVIEWER_KEY,
						students.stream().map(e -> e.getFirstPeerReviewerKey()).collect(Collectors.toList()),
						CellEditorType.PEER_REVIEWER_COMBO_BOX_EDITOR)//

				.withColumn(StudentColumn.SECOND_REVIEWER_KEY,
						students.stream().map(e -> e.getSecondPeerReviewerKey()).collect(Collectors.toList()),
						CellEditorType.PEER_REVIEWER_COMBO_BOX_EDITOR)//

				.withMetaData(students)//

				.withType(Student.class)//

				.build();
	}

	public void update() {
		refreshTableData();
		this.table.refreshData(tableData);
	}

}
