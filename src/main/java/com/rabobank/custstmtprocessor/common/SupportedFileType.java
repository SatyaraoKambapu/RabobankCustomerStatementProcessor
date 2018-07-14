package com.rabobank.custstmtprocessor.common;

public enum SupportedFileType {
	CSV(".csv"), XML(".xml");

	String fileType;

	SupportedFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileType() {
		return fileType;
	}
}
