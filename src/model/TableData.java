package model;

import java.util.List;
import java.util.ArrayList;

public class TableData {
	private List<Object>[] data;
	private String[] columnNames;

	private TableData(Builder builder) {
		this.data = new ArrayList[builder.columns.size()];
		this.data = builder.columns.toArray(data);
		this.columnNames = new String[builder.columnNames.size()];
		this.columnNames = builder.columnNames.toArray(columnNames);
	}

	public String[] getColumnNames() {
		return this.columnNames;
	}

	public List<Object>[] getContent() {
		return this.data;
	}

	/**
	 * Creates builder to build {@link TableData}.
	 * 
	 * @return created builder
	 */

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link TableData}.
	 */

	public static final class Builder {
		private List<List<Object>> columns = new ArrayList<List<Object>>();
		private List<String> columnNames = new ArrayList<String>();

		private Builder() {
		}

		public Builder withColumn(String columnName, List<Object> data) {
			this.columnNames.add(columnName);
			this.columns.add(data);
			return this;
		}

		public TableData build() {
			validateData();
			return new TableData(this);
		}

		private void validateData() {
			// check if all columns have the same length
			// if not, fill with empty content
			int max = 0;
			for (List<Object> l : columns) {
				if (l.size() > max) {
					max = l.size();
				}
			}
			for (List<Object> l : columns) {
				while (l.size() < max) {
					l.add(null);
				}

			}

		}
	}

}
