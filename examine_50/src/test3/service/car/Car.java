package test1.service.car;

import java.util.List;

import test1.model.CarModel;

public interface Car {
	void create(CarModel feeModel) throws Exception;
	void update(CarModel feeModel) throws Exception;
	void delete(CarModel feeModel) throws Exception;
	CarModel find(CarModel feeModel) throws Exception;
	List<CarModel> list() throws Exception;
}
