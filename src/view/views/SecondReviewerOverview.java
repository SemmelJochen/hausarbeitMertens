package view.views;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.PeerReviewer;
import model.Student;
import model.TableData;
import view.components.ContentPane;
import view.components.Table;

public class SecondReviewerOverview extends ContentPane {

	public SecondReviewerOverview(){
		super();
		
		JTabbedPane tPane = new JTabbedPane();
		JPanel panel3 = new JPanel();
		tPane.addTab("Zweitgutachter", panel3);

		this.setHeader(new JLabel("Zweitgutachter�bersicht"));
		this.setContent(buildComponents());
	}
	
	public Component buildComponents() {
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("Kalle", "Heino", "kalle@heino.de", "WI62/19", "zeb", "Netzwerke"));
		students.add(new Student("Peter", "G�nther", "peter@guenther.de", "WI62/19", "Microsoft", "Datenstrutkuren"));
		students.add(new Student("Schimmer", "Ralf", "schimmer@ralf.de", "WI62/19", "euronics", "Infrastrukturen"));
		
		List<PeerReviewer> reviewer = new ArrayList<PeerReviewer>();
		reviewer.add(new PeerReviewer("Prof.", "Schmitz", "Karl", "karl@schmitz.de", 20));
		
		
		TableData secondreviewer = TableData.builder()
				.withColumn("Titel", reviewer.stream().map(e -> e.getTitle()).collect(Collectors.toList()))//
				.withColumn("Vorname", reviewer.stream().map(e -> e.getFirstName()).collect(Collectors.toList()))//
				.withColumn("Nachname", reviewer.stream().map(e -> e.getName()).collect(Collectors.toList()))//
				.withColumn("E-Mail", reviewer.stream().map(e -> e.getEmail()).collect(Collectors.toList()))//
				.withColumn("Kapazit�t", reviewer.stream().map(e -> e.getCapacity()).collect(Collectors.toList()))//
				.withColumn("Erstgutachter", reviewer.stream().map(e -> e.getFirstName()+" "+e.getName()).collect(Collectors.toList()))//
				.build();
		
		Table table = new Table(secondreviewer);
		
		JTabbedPane tPane = new JTabbedPane();
		tPane.addTab("Zweitgutachter", table.getContent());
		return tPane;
	}
}