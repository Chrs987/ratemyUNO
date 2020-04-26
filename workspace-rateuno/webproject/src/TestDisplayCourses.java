import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.List;
import datamodel.Data;

class TestDisplayCourses {

	@Test
	void testGetData() {
		DisplayCourses dc = new DisplayCourses();
		String course = "CSCI 2030";
		String expectedProf1 = "Gregory Gelfond";
		String expectedProf2 = "Mark E Mordeson";
		String expectedProf3 = "Bo Guo";
		List<Data> result = dc.getData(course);
		
		Assert.assertEquals(expectedProf1, result.get(0).getProfessor());
		Assert.assertEquals(expectedProf2, result.get(1).getProfessor());
		Assert.assertEquals(expectedProf3, result.get(2).getProfessor());
		
	}
	
	@Test
	void testGetData2() {
		DisplayCourses dc = new DisplayCourses();
		String course = "CSCI 3320";
		String expectedProf1 = "Xin Zhong";
		String expectedProf2 = "Zhengxin Chen";
		String expectedProf3 = "Harvey Pe Siy";
		List<Data> result = dc.getData(course);
		
		Assert.assertEquals(expectedProf1, result.get(0).getProfessor());
		Assert.assertEquals(expectedProf2, result.get(1).getProfessor());
		Assert.assertEquals(expectedProf3, result.get(2).getProfessor());
		
	}

}
