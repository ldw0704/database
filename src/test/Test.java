package test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import Domain.ExamVO;
import dao.ExamDAO;

public class Test {

	public static void main(String[] args) {
		ExamDAO dao = new ExamDAO();

		//num컬럼이 4인 데이터 삭제
		ExamVO deleteVO = new ExamVO();
		
		deleteVO.setNum(4);
		dao.delete(deleteVO);
		
		ExamVO createVo = new ExamVO(0, "가변폭문자열", "고정폭문자열", 0.123, new Date(),
				new Timestamp(System.currentTimeMillis()));
//		System.out.println(createVo);
//		dao.create(createVo);
		//num이 4인 열만 가져오기
		ExamVO readVo = new ExamVO();
		readVo.setNum(4);
		
		ExamVO readVoRes = dao.read(readVo);
		
		if(readVoRes != null) {
//		System.out.print(readVoRes.getNum()+"\t");
		System.out.print(readVoRes.getVarcharTest()+"\t");
		System.out.print(readVoRes.getCharTest()+"\t");
		System.out.print(readVoRes.getDoubleTest()+"\t");
		System.out.print(readVoRes.getDateTest()+"\t");
		System.out.print(readVoRes.getDateTimeTest()+"\t");
		System.out.println();
		}
		//num컬럼이 4번인 데이터 수정
		ExamVO updateVO = new ExamVO();
		
		updateVO.setNum(4);
		updateVO.setVarcharTest("바꿀값");
		updateVO.setDoubleTest(123.12);
		dao.update(updateVO);
		
		
//		System.out.print(updateVO.getVarcharTest()+"\t");
//		System.out.print(updateVO.getCharTest()+"\t");
//		System.out.print(updateVO.getDoubleTest()+"\t");
//		System.out.print(updateVO.getDateTest()+"\t");
//		System.out.print(updateVO.getDateTimeTest()+"\t");
//		System.out.println();
		
//		List<ExamVO> list = dao.read();
//		Iterator<ExamVO> it = list.iterator();
//		while (it.hasNext()) {
//			ExamVO vo = it.next();
//			System.out.print(vo.getNum()+"\t");
//			System.out.print(vo.getVarcharTest()+"\t");
//			System.out.print(vo.getCharTest()+"\t");
//			System.out.print(vo.getDoubleTest()+"\t");
//			System.out.print(vo.getDateTest()+"\t");
//			System.out.print(vo.getDateTimeTest()+"\t");
//			System.out.println();
//						
//		}
	}

}
