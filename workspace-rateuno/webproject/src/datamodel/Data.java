package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 create table data (
 data_id INT AUTO_INCREMENT PRIMARY KEY, 
 professor VARCHAR(100), 
 course VARCHAR(100), 
 description VARCHAR(500));
 */
@Entity
@Table(name = "data")
public class Data {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "data_id")
   private Integer data_id;

   @Column(name = "professor")
   private String professor;

   @Column(name = "course")
   private String course;
   
   @Column(name = "description")
   private String description;

   public Data() {
   }

   public Data(Integer data_id, String professor, String course, String description) {
      this.data_id = data_id;
      this.professor = professor;
      this.course = course;
      this.description = description;
   }

   public Integer getData_id() {
      return data_id;
   }

   public void setData_id(Integer data_id) {
      this.data_id = data_id;
   }

   public String getProfessor() {
      return professor;
   }

   public void setProfessor(String professor) {
      this.professor = professor;
   }

   public String getCourse() {
      return course;
   }

   public void setCourse(String course) {
      this.course = course;
   }
   
   public String getDescription() {
	   return description;
   }
   
   public void setDescription(String description) {
	   this.description = description;
   }

   @Override
   public String toString() {
      return "Data: " + this.data_id + ", " + this.professor + ", " + this.course + ", " + this.description;
   }
}