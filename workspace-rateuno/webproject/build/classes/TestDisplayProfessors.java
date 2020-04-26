import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.List;
import datamodel.Data;

class TestDisplayProfessors {

	@Test
	void testGetData() {
		DisplayProfessors dr = new DisplayProfessors();
		String prof = "Bo Guo";
		String expectedCourse1 = "CSCI 2030";
		String expectedCourse2 = "CSCI 4830";
		List<Data> result = dr.getData(prof);
		
		Assert.assertEquals(expectedCourse1, result.get(0).getCourse());
		Assert.assertEquals(expectedCourse2, result.get(1).getCourse());
		
	}
	
	@Test
	void testGetData2() {
		DisplayProfessors dr = new DisplayProfessors();
		String prof = "Siy";
		
		String expectedCourse1 = "CSCI 3320";
		String expectedCourse2 = "CSCI 4000";
		String expectedCourse3 = "CSCI 4950";
		String expectedCourse4 = "CSCI 4970";
		String expectedCourse5 = "CSCI 4990";
		List<Data> result = dr.getData(prof);
		
		Assert.assertEquals(expectedCourse1, result.get(0).getCourse());
		Assert.assertEquals(expectedCourse2, result.get(1).getCourse());
		Assert.assertEquals(expectedCourse3, result.get(2).getCourse());
		Assert.assertEquals(expectedCourse4, result.get(3).getCourse());
		Assert.assertEquals(expectedCourse5, result.get(4).getCourse());
		
	}

}
