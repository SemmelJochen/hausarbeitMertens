package model;

import java.util.List;
import java.util.ArrayList;

public class TableData<T> {
	private List<Object>[] data;
	private List<T> metaData;
	private String[] columnNames;

	private TableData(Builder<T> builder) {
		this.data = new ArrayList[builder.columns.size()];
		this.data = builder.columns.toArray(data);
		this.columnNames = new String[builder.columnNames.size()];
		this.columnNames = builder.columnNames.toArray(columnNames);
		this.metaData = builder.metaData;
	}

	public String[] getColumnNames() {
		return this.columnNames;
	}

	public List<Object>[] getContent() {
		return this.data;
	}
	
	public List<T> getMetaData(){
		return this.metaData;
	}

	/**
	 * Creates builder to build {@link TableData}.
	 * 
	 * @return created builder
	 */

	public static Builder newTableData() {
		return new Builder();
	}

	/**
	 * Builder to build {@link TableData}.
	 */

	public static final class Builder<T> {
		private List<List<Object>> columns = new ArrayList<List<Object>>();
		private List<String> columnNames = new ArrayList<String>();
		private List<T> metaData = new ArrayList<T>();

		private Builder() {
		}

		public Builder<T> withColumn(Column columnName, List<Object> data) {
			this.columnNames.add(columnName.getValue());
			this.columns.add(data);
			return this;
		}
		
		public Builder<T> withMetaData(List<T> metaData){
			this.metaData = metaData;
			return this;
		}

		public TableData<T> build() {
			validateData();
			return new TableData<T>(this);
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
