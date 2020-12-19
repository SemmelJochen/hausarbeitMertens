package model.table;

public enum ReviewerColumn implements Column {

	LASTNAME("Nachname"), FIRSTNAME("Vorname"), TITLE("Titel"), CAPACITY("Kapazitaet"), CONCATED_NAME("Name"),
	E_MAIL("E-Mail"), FIRST_REVIEWER("Erstgutachter"), SECOND_REVIEWER("Zweitgutachter"), SUBJECTS("Themen");

	private String value;

	private ReviewerColumn(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static ReviewerColumn getEnumForValue(String value) {
		for (ReviewerColumn r : values()) {
			if (r.getValue().equals(value)) {
				return r;
			}
		}
		return null;
	}
}
