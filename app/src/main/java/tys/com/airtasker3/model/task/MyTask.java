package tys.com.airtasker3.model.task;

/**
 * Created by chokechaic on 5/6/2016.
 */
public class MyTask {

    private long taskId;
    private String title;
    private boolean online;
    private String locationDetail;
    private float totalBudget;
    private int status;
    private int numComment;
    private int numOffer;
    private byte[] clientPicture;
    private String clientImageUrl;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public float getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(float totalBudget) {
        this.totalBudget = totalBudget;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumComment() {
        return numComment;
    }

    public void setNumComment(int numComment) {
        this.numComment = numComment;
    }

    public int getNumOffer() {
        return numOffer;
    }

    public void setNumOffer(int numOffer) {
        this.numOffer = numOffer;
    }

    public byte[] getClientPicture() {
        return clientPicture;
    }

    public void setClientPicture(byte[] clientPicture) {
        this.clientPicture = clientPicture;
    }

    public String getClientImageUrl() {
        return clientImageUrl;
    }

    public void setClientImageUrl(String clientImageUrl) {
        this.clientImageUrl = clientImageUrl;
    }
}
