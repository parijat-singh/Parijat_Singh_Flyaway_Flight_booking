<%@ page import="com.simplilearn.workshop.utils.*" %>
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>

<HTML>
<HEAD>
    <TITLE>Select element drop down box</TITLE>
</HEAD>

<BODY BGCOLOR=##f89ggh>

<%
    try{
//Class.forName("com.mysql.jdbc.Driver").newInstance();
/* Connection connection = 
         DriverManager.getConnection
            ("jdbc:mysql://localhost/pgfsd_flyaway?user=root&password=p01amiss");
 */
 	   Connection connection = MySQLDatabaseUtils.getConnection();
 	   Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("select city_name from places") ;
%>

    <h1> Drop down box or select element</h1>
        <select name="city" id="city">
        <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
        <% } %>
        </select>

<%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>

</BODY>
</HTML>