CREATE TABLE Brieven (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
destinationLastName VARCHAR(30),
destinationFirstName VARCHAR(30),
destinationStreet VARCHAR(30),
destinationStreetNumber VARCHAR(5),
destinationZipCode VARCHAR(10),
destinationCity VARCHAR(30),
destinationEmail VARCHAR(30),
senderLastName VARCHAR(30),
senderFirstName VARCHAR(30),
senderEmail VARCHAR(30),
reg_date TIMESTAMP
)