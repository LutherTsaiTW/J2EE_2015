package examine.viewmodel;

import java.util.List;

import examine.model.CarModel;

public class GarageModel {
	private int ownerId;
	private int count;
	private List<CarModel> carList;
	
	public int getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public List<CarModel> getCarList() {
		return carList;
	}
	
	public void setCarList(List<CarModel> list) {
		this.carList = list;
	}
}
