package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 create table reviews (
 	review_id INT AUTO_INCREMENT PRIMARY KEY, 
 	data_id INT,
 	student_name VARCHAR(100), 
 	time VARCHAR(20), 
 	review VARCHAR(500), 
 	FOREIGN KEY (data_id) REFERENCES data(data_id));
 */
@Entity
@Table(name = "reviews")
public class Reviews {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "review_id")
   private Integer review_id;
   
   @Column(name = "data_id")
   private Integer data_id;
   
   @Column(name = "student_name")
   private String student_name;
   
   @Column(name = "date")
   private String date;

   @Column(name = "review")
   private String review;
   
   @Column(name = "rating")
   private Integer rating;

   public Reviews() {
   }

   public Reviews(Integer data_id, String student_name, String date, String review, Integer rating) {
      this.data_id = data_id;
      this.student_name = student_name;
      this.date = date;
      this.review = review;
      this.rating = rating;
   }

   public Integer getReviewid() {
      return review_id;
   }

   public void setReviewid(Integer review_id) {
      this.review_id = review_id;
   }

   public Integer getDataid() {
      return data_id;
   }

   public void setDataid(Integer data_id) {
      this.data_id = data_id;
   }
   
   public String getStudentname() {
      return student_name;
   }

   public void setStudentname(String student_name) {
      this.student_name = student_name;
   }
   
   public String getDate() {
	   return date;
   }
   
   public void setDate(String date) {
	   this.date = date;
   }

   public String getReview() {
      return review;
   }

   public void setReview(String review) {
      this.review = review;
   }
   
   public Integer getRating() {
	   return rating;
   }
   
   public void setRating(Integer rating) {
	   this.rating = rating;
   }

   @Override
   public String toString() {
      return "Review: " + this.review_id + ", " + this.data_id + ", " + this.student_name + ", " + this.date + ", " + this.review + ", " + this.rating;
   }
}