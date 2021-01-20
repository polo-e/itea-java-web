<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Table generator</title>
</head>
<body>

<%
    String form = "<form action='handler.jsp'><center><table border='1'><caption>Enter the dimension of the table</caption><tr><td>width:</td><td><input type='number' name='width' min = '1'></td></tr><tr><td>height:</td><td><input type='number' name='height' min = '1'></td></tr><tr><td align='center'><input type='submit' value='Generate!'></td></tr></center></table></form>";	
    boolean isShownForm = true; 	

    String w = request.getParameter("width");
    String h = request.getParameter("height");	

    if (w != null && !w.isEmpty() && h != null && !w.isEmpty()){
   
       int width = Integer.parseInt(request.getParameter("width"));
       int height = Integer.parseInt(request.getParameter("height"));

    if (width >= 1 && height >= 1){	
        String table = "<center><table border='1'><caption>Generated table</caption>";

        for (int i = 0; i < height; i++){
       	table = table + "<tr>";
              for (int j = 0; j < width; j++){
                  String color = j%2 == 0 ? "red" : "green";
                  table = table + String.format("<td bgcolor=%s>", color) + (int)(Math.random()*100) + "</td>";
              }
        table = table + "</tr>";
        }

    isShownForm = false;   
    out.write(table + "</table></br><button onclick=\"location.href='handler.jsp'\">Generate another table</center>");
    }
    } 	
    if (isShownForm) {
      out.write(form);
    }
%>
</body>
</html>