package test;

import dao.MemberDAO;
import domain.MemberVO;

public class MemberTest {

	public static void main(String[] args) {
		
		MemberVO mvo = new MemberVO();
		
		mvo.setId("web001");
		mvo.setPw("****");
		mvo.setUname("이동욱");
//		System.out.println(mvo);
		new MemberDAO().create(mvo);
	}

}
