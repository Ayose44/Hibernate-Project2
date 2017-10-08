# Hibernate-Project2
This is a Hibernate Java application that simulates a warehouse of a shop, categorizing the products..etc.
The code is in Spanish and the final result is in English
A part from Hibernate configuration files and database files, there is one important file that runs all:
- "InsertarFicheros.java": the class first asks you for two routes where are your category and products files, if they don't exist, 
they will be created automatically. Then is divided by some methods depending of his function:
  - "Menu": is the method with the main menu of the application, is created by JOptionPane.
  - "InsertarFich": is the method for insert products or categories in the files.
  - "Traspasar": is the method for move the data of the two files to the database.
  - "LimpiarFichero": it resets the two files.
  - "Modificar": it allows you modify the products and categories that are on the database.
  - "Borrar": deletes a product or category that you choose from the database.
  
