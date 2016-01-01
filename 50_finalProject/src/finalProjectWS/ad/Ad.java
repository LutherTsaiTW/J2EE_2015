package finalProjectWS.ad;

import model.AdModel;

public interface Ad {
	void create(AdModel adModel) throws Exception;
	AdModel getAdByToken(String token) throws Exception;
}
