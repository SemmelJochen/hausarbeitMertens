package view.views;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static model.TableData.newTableData;

import controller.CommandController;
import model.ModelContainer;
import model.Student;
import model.StudentColumn;

public class AllRolesTable extends ReducedTable {

	public AllRolesTable(CommandController commandController) {
		super(commandController);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refreshTableData() {
		List<Student> students = new ArrayList<Student>();
		if(this.selectedPeerReviewer != null) {
			String key = this.selectedPeerReviewer.getFirstName()+ this.selectedPeerReviewer.getName();
			if(ModelContainer.getInstance().getPeerReviewer(key) != null) {
//				this.selectedPeerReviewer.getFirstPeerReviewerRoles()
				ModelContainer.getInstance().getPeerReviewer(key).printStudents();
				students.addAll(ModelContainer.getInstance().getPeerReviewer(key).getFirstPeerReviewerRoles());
				students.addAll(ModelContainer.getInstance().getPeerReviewer(key).getSecondPeerReviewerRoles());							
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
				.withMetaData(students)//
				.withType(Student.class)//
				.build();
	}
}
