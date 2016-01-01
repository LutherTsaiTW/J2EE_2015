package model;

public class AdModel {
	private int AdID;
	private String adOwnerToken;
	private String adTitle;
	private String adDes;
	private String adImageLink;
	private String adRef;
	private int adStartDate;
	private int adEndDate;
	private int adImageHeight;
	private int adImageWidth;
	private int adClick;
	
	public int getAdID() {
		return AdID;
	}

	public void setAdID(int AdID) {
		this.AdID = AdID;
	}
	
	public String getAdOwnerToken() {
		return adOwnerToken;
	}

	public void setAdOwnerToken(String adOwnerToken) {
		this.adOwnerToken = adOwnerToken;
	}
	
	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	
	public String getAdDes() {
		return adDes;
	}

	public void setAdDes(String adDes) {
		this.adDes = adDes;
	}
	
	public String getAdImageLink() {
		return adImageLink;
	}

	public void setAdImageLink(String adImageLink) {
		this.adImageLink = adImageLink;
	}
	
	public String getAdRef() {
		return adRef;
	}

	public void setAdRef(String adRef) {
		this.adRef = adRef;
	}
	
	public int getAdStartDate() {
		return adStartDate;
	}

	public void setAdStartDate(int adStartDate) {
		this.adStartDate = adStartDate;
	}
	
	public int getAdEndDate() {
		return adEndDate;
	}

	public void setAdEndDate(int adEndDate) {
		this.adEndDate = adEndDate;
	}
	
	public int getAdImageHeight() {
		return adImageHeight;
	}

	public void setAdImageHeight(int adImageHeight) {
		this.adImageHeight = adImageHeight;
	}
	
	public int getAdImageWidth() {
		return adImageWidth;
	}

	public void setAdImageWidth(int adImageWidth) {
		this.adImageWidth = adImageWidth;
	}
	
	public int getAdClick() {
		return adClick;
	}

	public void setAdClick(int adClick) {
		this.adClick = adClick;
	}
}
