package finalProjectWS.member;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import model.MemberModel;

public interface Member {

	void create(MemberModel memberModel) throws Exception;
	void update(int id, MemberModel memberModel) throws Exception;
	void active(String applicationToken) throws Exception;
	MemberModel findByName(String name) throws Exception;
	MemberModel findByEmail(String email) throws Exception;
	MemberModel findByAccount(String account) throws Exception;
	MemberModel findBySession(String session) throws Exception;
	MemberModel findByToken(String token) throws Exception;
	boolean authencate(@RequestBody MemberModel input) throws Exception;
}
