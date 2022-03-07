package test;

import java.util.Iterator;
import java.util.List;

import dao.NoticeDAO;
import domain.NoticeVO;

public class Noticetest {
 
 
 public static void main(String[] args) {
	 
	 NoticeDAO dao = new NoticeDAO();
	 NoticeVO vo = new NoticeVO();
	 
//	 vo.setContent("가나다");
//	 vo.setTitle("라마바사");
//	 dao.create(vo);
	 
	 List<NoticeVO> list = dao.read();
		Iterator<NoticeVO> it =  list.iterator();
				
		while(it.hasNext()) {
			NoticeVO svo = it.next();
			System.out.print(svo.getNo()+"\t");
			System.out.print(svo.getTitle()+"\t");
			System.out.print(svo.getContent()+"\t");
			System.out.println(svo.getInputDate()+"\t");
		}
	 
}
 
 
}
