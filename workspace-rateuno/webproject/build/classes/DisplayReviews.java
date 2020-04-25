import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Data;
import datamodel.Reviews;
import util.Info;
import util.UtilDB;

@WebServlet("/DisplayReviews")
public class DisplayReviews extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public DisplayReviews() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Integer data_id = Integer.parseInt(request.getParameter("dataId").trim());
      SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");  
      Date d = new Date(); 
      String date = f.format(d);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
              "<html>\n" + //
              "<head>"
              + "<style>\n" + 
              "body {\n" + 
              "	font-family: Comic Sans MS, Comic Sans, cursive;\n" + 
              "}\n" + 
              ".sidenav {\n" + 
              "	overflow: hidden;\n" + 
              "	background-color: #333;\n" + 
              "	border-radius: 15px;\n" + 
              "}\n" + 
              ".sidenav a {\n" + 
              "	float: left;\n" + 
              "	display: block;\n" + 
              "	color: #f2f2f2;\n" + 
              "	padding: 20px 20px;\n" + 
              "	text-decoration: none;\n" + 
              "}\n" + 
              ".sidenav a:hover {\n" + 
              "	background-color: #ddd;\n" + 
              "	color:black;\n" + 
              "}\n" + 
              "input:hover[type=submit] {\n" + 
              "	background-color: #ddd;\n" + 
              "	color: black;\n" + 
              "}\n" + 
              "input[type=submit] {\n" + 
              "	background-color: #333;\n" + 
              "	border: none;\n" + 
              "	color: white;\n" + 
              "	padding: 20px 20px;\n" + 
              "	text-decoration: none;\n" + 
              "	margin: 4px 2px;\n" + 
              "	cursor: pointer;\n" + 
              "	border-radius: 10px;\n" + 
              "}\n" + 
              ".header {\n" + 
              "    text-align: center;\n" + 
              "    padding: 30px;	 \n" + 
              "}\n" + 
              ".header h1 {\n" + 
              "	font-size: 50px;\n" + 
              "}\n" + 
              ".center {\n" + 
              "	margin: auto;\n" + 
              "	width: 70%;\n" + 
              "}\n" + 
              ".center-text {\n" + 
              "	text-align: center;\n" + 
              "}\n" + 
              "footer {\n" + 
              "    border-radius: 15px;\n" + 
              "    padding: 8px;\n" + 
              "    text-align: center;\n" + 
              "    background: #333;\n" + 
              "    margin-top: 50px; 	 \n" + 
              "}\n" + 
              "footer h2 {\n" + 
              "	color:#f2f2f2;\n" + 
              "}\n"
              + ".entry {\n"
              + "		background-color: #ddd;"
              + "		padding: 20px;"
              + "		margin-top: 20px;"
              + "		border-radius: 15px;"
              + "		border: 1px solid #ccc;"
              + "		box-shadow: 1px 1px 1px #999;"
              + "}\n"
              + ".box {"
              + "	padding: 10px;"
              + "	line-height: 1.5;"
              + "	border-radius: 5px;"
              + "	border: 1px solid #ccc;"
              + "	box-shadow: 1px 1px 1px #999;"             
              + "}\n"
              + "textarea {"
              + "	resize: both;" 
              + "	height: 200px;"
              + "	max-width: 800px;"
              + "	min-width: 400px"
              + "}\n" + 
              "</style>"
              + "</head>\n" + //
              "<body>"
              + "<div class=\"header\">\n" + 
              "	<p><img style=\"border-radius:15px; width: 65%;\" src=\"https://lh3.googleusercontent.com/proxy/y27djYxJaHewasHzsjMeM4srzeWov0lODsJ5Z0xplAckeE-vi3aty4_P6e0BStSEt0z2ZmFUdn-cHHfDRHDNA1CX5q9jyn0MxjXDTzWiunUjlo3HnGWgcVU\" /></p>\n" + 
              "	<h1> Rate My UNO</h1>\n" + 
              "</div>\n" + 
              "\n" + 
              "<div class=\"sidenav\">\n" + 
              "	<a href=\"/webproject/search_by_course.html\">Search by Course</a>\n" + 
              "	<a href=\"/webproject/search_by_prof.html\">Search by Professor</a>\n" + 
              "</div>"
              + "<div class=\"center\">");

      List<Reviews> reviews = UtilDB.listReviews();
      if (data_id != null && data_id != 0) {
    	  reviews = UtilDB.listReviews(data_id);
	  } else {
		   reviews = UtilDB.listReviews();
	  }
      
      Data course = new Data();
      if (data_id != null && data_id != 0) {
    	  course = UtilDB.getCourse(data_id);
      }
      
      courseDesc(course, reviews, out, date);
      out.println("</div><footer>\n" + 
      		"	<h2>UNO College of IS&T @2020</h2>\n" + 
      		"</footer>\n" + 
      		"</body>\n" + 
      		"</html>");
   }

   void courseDesc(Data course, List<Reviews> reviews, PrintWriter out, String date) {
	   
	   double overall = 0;
	   int counter = 0;
	   for (Reviews review : reviews) {
		   counter++;
		   overall += review.getRating();
	   }
	   
	   if (counter != 0 && overall != 0)
	   {
		   overall /= counter;
		   DecimalFormat df = new DecimalFormat("#.##");
		   overall = Double.valueOf(df.format(overall));
	   }
	   
	  System.out.println("[DBG] " + course.getProfessor() + ", " + course.getCourse() + ", " + course.getData_id());
	  out.println("<h2>Course: " + course.getCourse() + "</h2>"
	  				+ "<h2>Professor: " + course.getProfessor() + "</h2>"
	  				+ "<h2>Rating: " + overall + "</h2>"
	  				+ "<h2>Course Description</h2><p>" + course.getDescription() + "<p>");
	   
	   out.println("<form action=\"AddedReview\" method=\"Post\">"
			   	+ "<p style=\"float:right;\">" + date + "</p>"
			     + "<input type=\"hidden\" id=\"date\" name=\"date\" value=\"" + date + "\" />"
		   		+ "<h2>Leave a Review</h2>\n"
		   		+ "<label for=\"studentName\">Student Name: </label>"
		   		+ "<input class=\"box\" type=\"text\" id=\"studentName\" name=\"studentName\" value=\"Anonymous\"><br><br>"
		   		+ "<textarea class=\"box\" name=\"review\"></textarea>"
		   		+ "<br><br>\n"
		   		+ "<p>Rate from 1 - 5</p>"
		   		+ "<input type=\"radio\" id=\"1\" name=\"rating\" value=\"1\">\n" + 
		   		"  <label for=\"1\">1</label><br>"
		   		+ "<input type=\"radio\" id=\"2\" name=\"rating\" value=\"2\">\n" + 
		   		"  <label for=\"2\">2</label><br>"
		   		+ "<input type=\"radio\" id=\"3\" name=\"rating\" value=\"3\">\n" + 
		   		"  <label for=\"3\">3</label><br>"
		   		+ "<input type=\"radio\" id=\"4\" name=\"rating\" value=\"4\">\n" + 
		   		"  <label for=\"4\">4</label><br>"
		   		+ "<input type=\"radio\" id=\"5\" name=\"rating\" value=\"5\">\n" + 
		   		"  <label for=\"5\">5</label>"
		   		+ "<input style=\"float:right;\" type=\"submit\" value=\"Submit Review\" />"
	     		+ "<input type=\"hidden\" id=\"dataId\" name=\"dataId\" value=\"" + course.getData_id() + "\" /><br><br>"
		   		+ "</form>");
	   
	   out.println("<h2>Student Reviews</h2>");
	   if (reviews.size() < 1) {
		   out.println("There are no reviews for this course and professor.");
	   }
	   for (Reviews review : reviews) {
		   out.println("<div class=\"entry\">"
				+ "<p style=\"float:right;\">" + review.getDate() + "</p>"
		   		+ "<p>Student: " + review.getStudentname() + "</p>"
		   		+ "<p>Rating: " + review.getRating() + "</p>"
		   		+ "<p>" + review.getReview() + "</p></div>");
	   }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
