package finalProjectWS.member;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import model.MemberModel;

public interface Member {

	void create(MemberModel memberModel) throws Exception;
	void update(int id, MemberModel memberModel) throws Exception;
	void active(String applicationToken) throws Exception;
	MemberModel find(int id) throws Exception;
	MemberModel findByName(String name) throws Exception;
	MemberModel findByEmail(String email) throws Exception;
	MemberModel findByAccount(String account) throws Exception;
	public boolean authencate(@RequestBody MemberModel input) throws Exception;
}
