package test3.service.car;

import java.util.List;

import test3.model.CarModel;

public interface Car {
	void create(CarModel carModel) throws Exception;
	void update(CarModel carModel) throws Exception;
	void delete(CarModel carModel) throws Exception;
	CarModel find(CarModel carModel) throws Exception;
	List<CarModel> list() throws Exception;
	List<CarModel> personalList(CarModel carModel) throws Exception;
}
