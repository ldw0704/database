package Domain;

import java.sql.Timestamp;
import java.util.Date;

public class ExamVO {
	private int num;
	private String varcharTest;
	private String charTest;
	private double doubleTest;
	private Date dateTest;
	private Timestamp dateTimeTest;
	
	@Override
	public String toString() { //데이터확인위해
		return "ExamVO [num=" + num + ", varcharTest=" + varcharTest + ", charTest=" + charTest + ", doubleTest="
				+ doubleTest + ", dateTest=" + dateTest + ", dateTimeTest=" + dateTimeTest + "]";
	}
		
	public ExamVO() {
		
	}
	
	public ExamVO(int num, String varcharTest, String charTest, double doubleTest, Date dateTest,
			Timestamp dateTimeTest) {
		super();
		this.num = num;
		this.varcharTest = varcharTest;
		this.charTest = charTest;
		this.doubleTest = doubleTest;
		this.dateTest = dateTest;
		this.dateTimeTest = dateTimeTest;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getVarcharTest() {
		return varcharTest;
	}
	public void setVarcharTest(String varcharTest) {
		this.varcharTest = varcharTest;
	}
	public String getCharTest() {
		return charTest;
	}
	public void setCharTest(String charTest) {
		this.charTest = charTest;
	}
	public double getDoubleTest() {
		return doubleTest;
	}
	public void setDoubleTest(double doubleTest) {
		this.doubleTest = doubleTest;
	}
	public Date getDateTest() {
		return dateTest;
	}
	public void setDateTest(Date dateTest) {
		this.dateTest = dateTest;
	}
	public Timestamp getDateTimeTest() {
		return dateTimeTest;
	}
	public void setDateTimeTest(Timestamp dateTimeTest) {
		this.dateTimeTest = dateTimeTest;
	}
	
	
	
	

	
}
