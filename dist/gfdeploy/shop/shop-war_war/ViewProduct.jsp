<%-- 
    Document   : ViewProduct
    Created on : Apr 15, 2021, 8:52:02 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%
    if(request.getParameter("submit")!=null){
        String productName  = request.getParameter("ProductName");
        String description = request.getParameter("Description");
        String unitPrice = request.getParameter("UnitPrice");
        String quantity = request.getParameter("Quantity");
        String categoryID = request.getParameter("CategoryID");
        String thumbnail = request.getParameter("Thumbnail");
        
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
       
   
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=a18059;user=sa;password=sa;");
           pst = con.prepareStatement("insert into Products(ProductName, Description, UnitPrice, Quantity, CategoryID, Thumbnail) values (?,?,?,?,?,?)");
           pst.setString(1, productName);
           pst.setString(2, description);
           pst.setString(3, unitPrice);
           pst.setString(4, quantity);
           pst.setString(5, categoryID);
           pst.setString(6, thumbnail);
          
           pst.executeUpdate();
        %>
           
        <script>
            alert("Record ADD");
            </script>
           
           
    <%       
       
    }
    

%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <title>Samuel_A18059</title>
        
    </head>
    <body>
      <jsp:include page="Menu.jsp"></jsp:include>
        <div class="row">
            <div class="col-sm-4">
                 <h2>Product add </h2>
                <form class="card" method="POST" action="#">                   
                    <div align="left">
                        <label class="form-label"> Product Name</label>
                        <input type="text" class="form-control" placeholder="Product Name" name="productName" id="productName" required/>
                    </div>
                    <div align="left">
                        <label class="form-label"> Description</label>
                        <input type="text" class="form-control" placeholder="Description" name="description" id="description" required/>
                    </div>
                    <div align="left">
                        <label class="form-label">Unit Price</label>
                        <input type="text" class="form-control" placeholder="UnitPrice" name="unitPrice" id="unitPrice" required/>
                    </div>
                    <div align="left">
                        <label class="form-label">Quantity</label>
                        <input type="text" class="form-control" placeholder="Quantity" name="quantity" id="quantity" required/>
                    </div>
                     <div align="left">
                        <label class="form-label">CategoryID</label>
                        <input type="text" class="form-control" placeholder="Category ID" name="CategoryID" id="Category" required/>
                    </div>
                     <div align="left">
                        <label class="form-label">Thumbnails</label>
                        <input type="text" class="form-control" placeholder="Thumbnail" name="thumbnail" id="thumbnail" required/>
                    </div>

                    
                    
                     <div align="right">
                         <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info"/>
                         <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning"/>
                       </div>
                </form>
            
            </div>
            <div class="col-sm-8">
                <div class="panel-body">
                    <table id="tbl-student" class="table table-responsive table-bordered" cellpadding="0" width="100%">
                        <h2><CENTER>PRODUCT LIST</CENTER></h2>
                        <thread>
                            <tr>
                                <th>PRODUCT NAME</th>
                                <th>DESCRIPTION</th>
                                <th>UNIT PRICE</th>
                                
                                <th>QUANTITY</th>
                                <th>CATEGORY ID</th>
                                <th>THUMBNAIL</th>

                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thread>
                        
                            <%
                            Connection conn;
                            PreparedStatement pst;
                            ResultSet rs;
       
   
                            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=A18059;user=sa;password=sa;");
                            
                            
                            String query = "select * from Products";
                            Statement st = conn.createStatement();
                            rs = st.executeQuery(query);
                            while(rs.next()){
                                String id = rs.getString("id");
                            
                            %>
                            <tr>
                            <td><%=rs.getString("ProductName")%></td>
                            <td><%=rs.getString("Description")%></td>
                            <td><%=rs.getString("UnitPrice")%></td>
                            <td><%=rs.getString("Quantity")%></td>
                            <td><%=rs.getString("CategoryID")%></td>
                             <td><%=rs.getString("Thumbnail")%></td>
                            
                            <td><a href="UpdateProduct.jsp?id=<%=id%>">Edit</a></td>
                            <td><a href="DeleteProduct.jsp?id=<%=id%>">Delete</a></td>
                            </tr>
                            <%
                            }
                            %>
                       
                    </table>
                    
                </div>
                
                
                
            </div>
            
        </div>
 <jsp:include page="footer.xhtml"></jsp:include>
</body>
</html>
