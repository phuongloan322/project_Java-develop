package ait.team.java.dto.output;

import org.apache.tomcat.jni.FileInfo;

public class ResponseFile {
	
	private String message;
	private FileInfo file;
	private Error error = null;
	private String errStatus = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FileInfo getFile() {
		return file;
	}

	public void setFile(FileInfo file) {
		this.file = file;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public String getErrStatus() {
		return errStatus;
	}

	public void setErrStatus(String errStatus) {
		this.errStatus = errStatus;
	}

}