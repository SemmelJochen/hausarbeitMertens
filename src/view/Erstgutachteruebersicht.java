package view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.PeerReviewer;
import model.Student;
import model.TableData;

public class Erstgutachteruebersicht {

	public TableData erstgutachterUebersicht(){
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("Kalle", "Heino", "kalle@heino.de", "WI62/19", "zeb", "Netzwerke"));
		students.add(new Student("Peter", "G�nther", "peter@guenther.de", "WI62/19", "Microsoft", "Datenstrutkuren"));
		students.add(new Student("Schimmer", "Ralf", "schimmer@ralf.de", "WI62/19", "euronics", "Infrastrukturen"));
		
		List<PeerReviewer> reviewer = new ArrayList<PeerReviewer>();
		reviewer.add(new PeerReviewer("Prof.", "Schmitz", "Karl", "karl@schmitz.de", 20));
		
		
		TableData firstreviewer = TableData.builder()
				.withColumn("Titel", reviewer.stream().map(e -> e.getTitle()).collect(Collectors.toList()))//
				.withColumn("Vorname", reviewer.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn("Nachname", reviewer.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn("E-Mail", reviewer.stream().map(e -> e.getEmail()).collect(Collectors.toList()))//
				.withColumn("Kapazit�t", reviewer.stream().map(e -> e.getCapacity()).collect(Collectors.toList()))//
				.withColumn("Zweitgutachter", reviewer.stream().map(e -> e.getFirstName()+" "+e.getName()).collect(Collectors.toList()))//
				.build();
		
		return firstreviewer;
	}
	
	
}

