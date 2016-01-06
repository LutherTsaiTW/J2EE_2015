package finalProjectWS.ad;

import java.util.List;

import model.AdModel;

public interface Ad {
	void create(AdModel adModel) throws Exception;
	String getAdByToken(String token) throws Exception;
	List<AdModel> getSelfAdByToken(String token) throws Exception;
}
