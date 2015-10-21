CREATE TABLE Brieven (
id INT NOT NULL AUTO_INCREMENT,
PRIMARY KEY (id),
member_id INT(6),
destinationLastName VARCHAR(30),
destinationFirstName VARCHAR(30),
destinationStreetName VARCHAR(30),
destinationStreetNumber VARCHAR(5),
destinationZipCode VARCHAR(10),
destinationCity VARCHAR(30),
destinationEmail VARCHAR(30),
senderLastName VARCHAR(30),
senderFirstName VARCHAR(30),
senderStreetName VARCHAR(30),
senderStreetNumber VARCHAR(5),
senderZipCode VARCHAR(10),
senderCity VARCHAR(30),
senderEmail VARCHAR(30),
status VARCHAR(10),
reg_date TIMESTAMP,
sent_date TIMESTAMP,
INDEX member_idx(member_id),
FOREIGN KEY (member_id) REFERENCES Members(id) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE Members (
id INT NOT NULL AUTO_INCREMENT,
PRIMARY KEY (id),
first_name VARCHAR(30),
last_name VARCHAR(30),
email VARCHAR(30),
pass VARCHAR(60),
reg_date TIMESTAMP
) ENGINE=INNODB;

CREATE TABLE CreditLog (
id INT NOT NULL AUTO_INCREMENT,
PRIMARY KEY (id),
member_id INT(6),
amount INT(6),
reg_date TIMESTAMP,
brief_id INT(6),
INDEX member_idx(member_id),
INDEX brief_idx(brief_id),
FOREIGN KEY (member_id) REFERENCES Members(id) ON DELETE CASCADE,
FOREIGN KEY(brief_id) REFERENCES Brieven(id) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE Emails (
id INT NOT NULL AUTO_INCREMENT,
PRIMARY KEY (id),
email VARCHAR(30),
reg_date TIMESTAMP
) ENGINE=INNODB;