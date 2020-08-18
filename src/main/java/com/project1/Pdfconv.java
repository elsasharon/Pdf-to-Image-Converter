package com.project1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;





public class Pdfconv extends HttpServlet {
	
	
	public String Filename;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
			
			List<FileItem> multifiles = sf.parseRequest(request);
			//FileItem  file=sf.parseRequest(request);
			
			
			
			 String destFolder = "C:\\Users\\sharonmt\\Desktop\\pdfConverter\\Uploadedpdf\\"; 
			 
			 File destFile = new File(destFolder);
		        if (!destFile.exists()) {
		        	destFile.mkdir();
		        System.out.println("Folder Created -> "+ destFile.getAbsolutePath());
		       }
		        
		   
			 
		 
			for (FileItem item : multifiles) {
				item.write(new File(destFile +"\\" + item.getName()));
				Filename=item.getName();
			}
			
		       
			request.setAttribute("Message","Pdf File Uploaded Successfully !!");
			getServletContext().getRequestDispatcher("/Success.jsp").forward(request,response);
			
			System.out.println("File Uploaded");
			
			
	//*******************************************************************************
			
			
			 
		   			
			
			String pdfFileName = "C:\\Users\\sharonmt\\Desktop\\pdfConverter\\Uploadedpdf\\"+Filename;
			//System.out.println(pdfFileName);
		     String destinationDir = "C:\\Users\\sharonmt\\Desktop\\pdfConverter\\Pdf_to_Image";
		     
		     File sourceFile = new File(pdfFileName);
		        File destinationFile = new File(destinationDir);
		        if (!destinationFile.exists()) {
		          destinationFile.mkdir();
		        System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
		       }
		        
		        PDDocument document = PDDocument.load(pdfFileName);   
		        List<PDPage> pdPages = document.getDocumentCatalog().getAllPages();
		        
		        String fileName = sourceFile.getName().replace(".pdf", ""); 
		        int page = 0;
		            

		            for (PDPage pdPage : pdPages) {
		            	++page;
		            	BufferedImage image = pdPage.convertToImage();
		            	File output = new File(destinationDir +"\\"+ fileName + page +".png");
		            	System.out.println("Image Created -> "+ output.getName());				             
		               ImageIO.write(image, "png", output);

		            }
		            
		            document.close();

		          
		            Path p = Paths.get("C:\\Users\\sharonmt\\Desktop\\pdfConverter\\Uploadedpdf\\"+Filename);
		            
		            String bDir = "C:\\Users\\sharonmt\\Desktop\\pdfConverter\\ByteArray\\";
				            File bFile = new File(bDir);
					        if (!bFile.exists()) {
					          bFile.mkdir();
					        System.out.println("Folder Created -> "+ bFile.getAbsolutePath());
					       }
		            
		            byte[] bArray = Files.readAllBytes(p);
		            System.out.println("Byte array of Image");
		            
		            
		            
		          PrintWriter pr = new PrintWriter(bFile+"\\" +fileName);

		            
		            for (int i = 0; i < bArray.length; i++){
		            	
		                   System.out.print(bArray[i]);
		                    pr.println(bArray[i]);
		            	}
		            
		           
		            pr.close();

		          
		} catch (Exception e) {
			System.out.println(e);
		}

		
		
		
	}

}


