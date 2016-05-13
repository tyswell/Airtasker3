package tys.com.airtasker3.model.task;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Task implements Serializable {

	long id;

	String title;

	String detail;

	boolean online;

	private String placeId;

	String location;

	double latitude;

	double longitude;

	String locationDetail;

	int dueDateType;

	Date dueDate;

	boolean moreTaskers;//team worker

	int noOfTaskers;// number of worker

	private float totalBudget;

	int budgetType;

	int budget;

	float hour;

	float expense;

	private String createDateDesc;

	private Date createdDate;

	private Date modifiedDate;

	int status;

	int reasonCode;

	String otherReason;

	Client client;

	TaskCategory taskCategory;

	List<TaskComment> comments;
	
	List<TaskOffer> offers;

	public float getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(float totalBudget) {
		this.totalBudget = totalBudget;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public String getCreateDateDesc() {
		return createDateDesc;
	}

	public void setCreateDateDesc(String createDateDesc) {
		this.createDateDesc = createDateDesc;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}

	public int getDueDateType() {
		return dueDateType;
	}

	public void setDueDateType(int dueDateType) {
		this.dueDateType = dueDateType;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isMoreTaskers() {
		return moreTaskers;
	}

	public void setMoreTaskers(boolean moreTaskers) {
		this.moreTaskers = moreTaskers;
	}

	public int getNoOfTaskers() {
		return noOfTaskers;
	}

	public void setNoOfTaskers(int noOfTaskers) {
		this.noOfTaskers = noOfTaskers;
	}

	public int getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(int budgetType) {
		this.budgetType = budgetType;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public float getHour() {
		return hour;
	}

	public void setHour(float hour) {
		this.hour = hour;
	}

	public float getExpense() {
		return expense;
	}

	public void setExpense(float expense) {
		this.expense = expense;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public TaskCategory getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}

	public List<TaskComment> getComments() {
		return comments;
	}

	public void setComments(List<TaskComment> comments) {
		this.comments = comments;
	}

	public List<TaskOffer> getOffers() {
		return offers;
	}

	public void setOffers(List<TaskOffer> offers) {
		this.offers = offers;
	}
}
