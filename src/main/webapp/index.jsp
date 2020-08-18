<html>
<body>

<style> 
 table { 
                border-collapse: collapse; 
            }
            
           td { 
                 
                text-align:center; 
                border = 0; 
                padding:15px
               
              
            } 

            h1 { 
                color:red; 
            } 
            
             h4 { 
                color:purple; 
            } 
        </style> 
<center>

<br><br> 
<h1>Pdf Uploader</h1>

	<form action="upload" method="post" enctype="multipart/form-data">
	
	<table border=1 >
			 
			<td>Select Pdf File :</td>
			<td> <input type="file" name="pdf" /><br></td>
			<%--<td><input id="file_bal" name="Browse" type="file" value="Browse" enctype="multipart/form-data"/></td> --%>
		</tr>
				
	</table>

			<br> <input type="submit" value="UPLOAD" /></br>
			
			</br></br></br>		
			<h4>(Reload before uploading )</h4>
		
	</form>
	
	</center>


</body>
</html>
