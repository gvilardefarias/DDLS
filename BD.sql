CREATE TABLE user_tb(
nameUser VARCHAR(50) PRIMARY KEY,
registryUser VARCHAR(12) PRIMARY KEY,
passUser VARCHAR(40) NOT NULL,
emailUser VARCHAR(50) NOT NULL,
courseUser VARCHAR(50) NOT NULL,
classUser VARCHAR(6) NOT NULL);

CREATE TABLE admin_tb(
nameAdmin VARCHAR(50) NOT NULL,
registryAdmin VARCHAR(12) PRIMARY KEY,
passAdmin VARCHAR(40) NOT NULL,
emailAdmin VARCHAR(50) NOT NULL);

CREATE TABLE group_tb(
numberGp INT(1) PRIMARY KEY,
nameUser VARCHAR(50),
registryUser VARCHAR(12),
CONSTRAINT fk_group_user FOREIGN KEY (nameUser, registryUser) REFERENCES user_tb (nameUser, registryUser));

CREATE TABLE testbench_tb(
testbId INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
testbPath VARCHAR(255) NOT NULL,
testbDateUp TIMESTAMP,
registryAdmin VARCHAR(12),
CONSTRAINT fk_testb_admin FOREIGN KEY (registryAdmin) REFERENCES admin_tb (registryAdmin));

CREATE TABLE project_tb(
projectId INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(600) NOT NULL,
referenceFiles VARCHAR(255) NOT NULL,
testbId INT UNSIGNED,
registryAdmin VARCHAR(12),
CONSTRAINT fk_project_admin FOREIGN KEY (registryAdmin) REFERENCES admin_tb (registryAdmin),
CONSTRAINT fk_project_testb FOREIGN KEY (testbId) REFERENCES testbench_tb (testbId));

CREATE TABLE dut_tb(
dutId INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
dutPath VARCHAR(255) NOT NULL,
dutDateUp TIMESTAMP,
dutCheckTest BOOLEAN NOT NULL,
dutResultPath VARCHAR(255),
registryUser VARCHAR(12),
testbId INT UNSIGNED,
CONSTRAINT fk_dut_testb FOREIGN KEY (testbId) REFERENCES testbench_tb (testbId),
CONSTRAINT fk_dut_user FOREIGN KEY (registryUser) REFERENCES user_tb (registryUser));

CREATE TABLE user_project_tb(
registryUser VARCHAR(12) PRIMARY KEY,
projectId INT UNSIGNED PRIMARY KEY,
CONSTRAINT fk_project_user FOREIGN KEY (registryUser) REFERENCES user_tb (registryUser),
CONSTRAINT fk_user_project FOREIGN KEY (projectId) REFERENCES project_tb (projectId)); 

CREATE TABLE admin_user_tb(
registryAdmin VARCHAR(12) PRIMARY KEY,
registryUser VARCHAR(12) PRIMARY KEY,
CONSTRAINT fk_admin_user FOREIGN KEY (registryUser) REFERENCES user_tb (registryUser),
CONSTRAINT fk_user_admin FOREIGN KEY (registryAdmin) REFERENCES admin_tb (registryAdmin));
