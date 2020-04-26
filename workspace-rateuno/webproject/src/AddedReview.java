import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDB;

@WebServlet("/AddedReview")
public class AddedReview extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public AddedReview() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Integer data_id = Integer.parseInt(request.getParameter("dataId").trim());
      String date = request.getParameter("date").trim();
      String student_name = request.getParameter("studentName").trim();
      String review = request.getParameter("review").trim();
      Integer rating = Integer.parseInt(request.getParameter("rating").trim());
      
      UtilDB.createReview(data_id, student_name, date, review, rating);

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
      
      display(data_id, out);
      out.println("</div><footer>\n" + 
        		"	<h2>UNO College of IS&T @2020</h2>\n" + 
        		"</footer>\n" + 
        		"</body>\n" + 
        		"</html>");
   }

   void display(Integer data_id, PrintWriter out) {
	  System.out.println("[DBG] Review Added");
	  out.println("<div class=\"center-text\"><h2>Review Added</h2>"
	  		+ "<form action=\"DisplayReviews\" method=\"Post\">"
	  			+ "<input type=\"submit\" value=\"Back\" />"
	  			+ "<input type=\"hidden\" id=\"dataId\" name=\"dataId\" value=\"" + data_id + "\" />"
	  		+ "</form></div>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
