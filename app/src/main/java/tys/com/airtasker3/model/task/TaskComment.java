package tys.com.airtasker3.model.task;

import java.io.Serializable;
import java.util.Date;


public class TaskComment implements Serializable {

	long id;

	String comment;

	private String createdDateDesc;

	private Date createdDate;

	Task task;

	Client client;

	public String getCreatedDateDesc() {
		return createdDateDesc;
	}

	public void setCreatedDateDesc(String createdDateDesc) {
		this.createdDateDesc = createdDateDesc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
