package view.views;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.PeerReviewer;
import model.Student;
import model.TableData;

public class StudentOverview {

	public TableData studentsOverview(){
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("Kalle", "Heino", "kalle@heino.de", "WI62/19", "zeb", "Netzwerke"));
		students.add(new Student("Peter", "G�nther", "peter@guenther.de", "WI62/19", "Microsoft", "Datenstrutkuren"));
		students.add(new Student("Schimmer", "Ralf", "schimmer@ralf.de", "WI62/19", "euronics", "Infrastrukturen"));
		
		List<PeerReviewer> reviewer = new ArrayList<PeerReviewer>();
		reviewer.add(new PeerReviewer("Prof.", "Schmitz", "Karl", "karl@schmitz.de", 20));
		
		
		TableData student = TableData.builder()
				.withColumn("Studiengruppe", students.stream().map(e -> e.getStudentGroup()).collect(Collectors.toList()))//
				.withColumn("Vorname", students.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn("Nachname", students.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn("E-Mail", students.stream().map(e -> e.getEmail()).collect(Collectors.toList()))//
				.withColumn("Thema", students.stream().map(e -> e.getSubject()).collect(Collectors.toList()))//
				.withColumn("Praxispartner", students.stream().map(e -> e.getPracticePartner()).collect(Collectors.toList()))//
				.withColumn("Erstgutachter", reviewer.stream().map(e -> e.getFirstName()+" "+ e.getName()).collect(Collectors.toList()))//
				.withColumn("Zweitgutachter", reviewer.stream().map(e -> e.getFirstName()+" "+e.getName()).collect(Collectors.toList()))//
				.build();
		
		return student;
	}
	
}
