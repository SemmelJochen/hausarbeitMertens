package model.table;

public enum StudentColumn implements Column {

	LASTNAME("Nachname"), FIRSTNAME("Vorname"), STUDENT_GROUP("Studentengruppe"), PRACTICE_PARTNER("Praxispartner"),
	SUBJECT("Thema"), FIRST_REVIEWER_KEY("Erstgutachter"), SECOND_REVIEWER_KEY("Zweitgutachter"), E_MAIL("E-Mail"),
	REMARK("Bemerkung"), SECOND_REVIEWER_STATE("Zustand");

	private String value;

	private StudentColumn(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static StudentColumn getEnumForValue(String value) {
		for (StudentColumn s : values()) {
			if (s.getValue().equals(value)) {
				return s;
			}
		}
		return null;
	}
}
