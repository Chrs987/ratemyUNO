import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.List;
import datamodel.Data;
import datamodel.Reviews;
import util.UtilDB;

class TestDisplayReviews {

	@Test
	void testGetReviews() {
		DisplayReviews dr = new DisplayReviews();
		Integer data_id = 7;
		String expected = "TestReview";
		List<Reviews> result = dr.getReviews(data_id);
		Assert.assertEquals(expected, result.get(0).getStudentname());
	}
	
	@Test
	void testGetReviews2() {
		DisplayReviews dr = new DisplayReviews();
		Integer data_id = 40;
		Integer expected = 4;
		List<Reviews> result = dr.getReviews(data_id);
		Assert.assertEquals(expected, result.get(0).getRating());
	}
	
	@Test
	void testGetCourse() {
		DisplayReviews dr = new DisplayReviews();
		Integer data_id = 20;
		String expected = "CSCI 3550";
		Data result = dr.getCourse(data_id);
		Assert.assertEquals(expected, result.getCourse());
	}
	
	@Test
	void testGetCourse2() {
		DisplayReviews dr = new DisplayReviews();
		Integer data_id = 30;
		String expected = "Fabio Torres Vitor";
		Data result = dr.getCourse(data_id);
		Assert.assertEquals(expected, result.getProfessor());
	}
	
	@Test
	void testGetOverallRating() {
		DisplayReviews dr = new DisplayReviews();
		List<Reviews> data = UtilDB.listReviews();
		Reviews data0 = new Reviews(2, "test0", "01/01/2020", "testreview0", 3);
		data.add(data0);
		Reviews data1 = new Reviews(2, "test1", "01/01/2020", "testreview1", 3);
		data.add(data1);
		Reviews data2 = new Reviews(2, "test2", "01/01/2020", "testreview2", 5);
		data.add(data2);
		double expected = 3.66;
		double result = dr.getOverallRating(data);
		Assert.assertEquals(expected, result, .01);
	}
	
	@Test
	void testGetOverallRating2() {
		DisplayReviews dr = new DisplayReviews();
		List<Reviews> data = UtilDB.listReviews();
		Reviews data0 = new Reviews(2, "test0", "01/01/2020", "testreview0", 4);
		data.add(data0);
		Reviews data1 = new Reviews(2, "test1", "01/01/2020", "testreview1", 2);
		data.add(data1);
		Reviews data2 = new Reviews(2, "test2", "01/01/2020", "testreview2", 3);
		data.add(data2);
		Reviews data3 = new Reviews(2, "test3", "01/01/2020", "testreview3", 5);
		data.add(data3);
		Reviews data4 = new Reviews(2, "test4", "01/01/2020", "testreview4", 2);
		data.add(data4);
		Reviews data5 = new Reviews(2, "test5", "01/01/2020", "testreview5", 1);
		data.add(data5);
		double expected = 2.83;
		double result = dr.getOverallRating(data);
		Assert.assertEquals(expected, result, .01);
	}

}
