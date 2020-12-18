package view.views;

import static model.table.TableData.newTableData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.CommandController;
import model.ModelContainer;
import model.Student;
import model.table.CellEditorType;
import model.table.StudentColumn;

public class SecondRolesTable extends ReducedTable {


	public SecondRolesTable(CommandController commandController) {
		super(commandController);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refreshTableData() {
		List<Student> students = new ArrayList<Student>();
		if(this.selectedPeerReviewer != null) {
			String key = this.selectedPeerReviewer.getFirstName()+ this.selectedPeerReviewer.getName();
			if(ModelContainer.getInstance().getPeerReviewer(key) != null) {
				students = ModelContainer.getInstance().getPeerReviewer(key).getSecondPeerReviewerRoles();							
			}
		}
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
				.withColumn(StudentColumn.FIRST_REVIEWER, students.stream()
						.map(e -> e.getFirstPeerReviewerKey())
						.collect(Collectors.toList()), CellEditorType.PEER_REVIEWER_COMBO_BOX_EDITOR)//
				.withMetaData(students)//
				
				.build();
	}
}
