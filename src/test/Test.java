package test;

import java.sql.Timestamp;
import java.util.Date;

import Domain.ExamVO;
import dao.ExamDAO;

public class Test {

	public static void main(String[] args) {
		ExamDAO dao = new ExamDAO();
		ExamVO createVo = new ExamVO(0, "가변폭문자열", "고정폭문자열", 0.123, new Date(),
				new Timestamp(System.currentTimeMillis()));
//		System.out.println(createVo);
		dao.create(createVo);
	}

}
