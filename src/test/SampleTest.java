package test;

import java.util.Iterator;
import java.util.List;

import dao.SampleDAO;
import domain.SampleVO;

public class SampleTest {

	public static void main(String[] args) {
		SampleDAO dao = new SampleDAO();
		SampleVO vo = new SampleVO();
//		vo.setStrData("변경한다");
		//등록
//		dao.create(vo);
		//전체글보기
//		dao.read();
//		List<SampleVO> list = dao.read();
//		Iterator<SampleVO> it =  list.iterator();
//		while(it.hasNext()) {
//			SampleVO svo = it.next();
//			System.out.print(svo.getNum()+"\t");
//			System.out.print(svo.getStrData()+"\t");
//			System.out.println(svo.getSampleDate()+"\t");//					
//		}
		//해당글보기
//		
//		SampleVO vo2 = new SampleVO();
//		vo2.setNum(4);
//		vo2.setStrData("변경한다");
//		
//		SampleVO sampleVO = dao.read(vo2);
//		System.out.print(sampleVO.getNum()+"\t");
//		System.out.print(sampleVO.getStrData()+"\t");
//		System.out.println(sampleVO.getSampleDate()+"\t");
		
//		SampleVO vo2 = new SampleVO();
//		vo2.setNum(0);
//		vo2.setStrData("변경한다");
//		dao.update(vo2);
//		
//		dao.delete(vo2);
	}

}
