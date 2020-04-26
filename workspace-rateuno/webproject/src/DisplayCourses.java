import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Data;
import util.Info;
import util.UtilDB;

@WebServlet("/DisplayCourses")
public class DisplayCourses extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public DisplayCourses() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String course = request.getParameter("course").trim();

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
              + "}\n" + 
              "</style>"
              + "</head>\n" + //
              "<body>"
              + "<div class=\"header\">\n" + 
              "	<p><img style=\"border-radius:15px; width: 65%;\" src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSuV09DoGvtw4VPJ2fwejRf0B9FTpop3zfDDvxllb--U2U9MSZl&usqp=CAU\" /></p>\n" + 
              "	<h1> Rate My UNO</h1>\n" + 
              "</div>\n" + 
              "\n" + 
              "<div class=\"sidenav\">\n" + 
              "	<a href=\"/webproject/search_by_course.html\">Search by Course</a>\n" + 
              "	<a href=\"/webproject/search_by_prof.html\">Search by Professor</a>\n" + 
              "</div>"
              + "<div class=\"center\">");

      List<Data> listData = getData(course);
      display(listData, out);
      out.println("</div><footer>\n" + 
        		"	<h2>UNO College of IS&T @2020</h2>\n" + 
        		"</footer>\n" + 
        		"</body>\n" + 
        		"</html>");
   }
   
   List<Data> getData(String course) {
	   List<Data> listData = UtilDB.listData();
	   if (course != null && !course.isEmpty()) {
	      listData = UtilDB.listData(course);
	   } else {
	      listData = UtilDB.listData();
	   }
	   return listData;
   }

   void display(List<Data> listData, PrintWriter out) {
      for (Data data : listData) {
         System.out.println("[DBG] " + data.getCourse() + ", " + data.getProfessor());

         out.println("<div class=\"entry\">"
        		 + "<form action=\"DisplayReviews\" method=\"POST\">\n" + 
  				"			<input style=\"float:right;\" type=\"submit\" value=\"Select\" />\n" + 
         		"			<input type=\"hidden\" id=\"dataId\" name=\"dataId\" value=\"" + data.getData_id() + "\" />\n"
         					+ "<h3>Course: " + data.getCourse() + "</h3>"
         					+ "<h3>Professor: " + data.getProfessor() + "</h3>" + 
         		"		</form></div>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
