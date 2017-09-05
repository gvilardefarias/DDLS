CREATE TABLE user_tb(
nameUser VARCHAR(50),
registryUser VARCHAR(12),
passUser VARCHAR(40) NOT NULL,
emailUser VARCHAR(50) NOT NULL,
courseUser VARCHAR(50) NOT NULL,
classUser VARCHAR(6) NOT NULL,
CONSTRAINT pk_user PRIMARY KEY(nameUser, registryUser));

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
testbFile MEDIUMBLOB NOT NULL,
testbDateServiceUp TIMESTAMP,
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
dutFile MEDIUMBLOB NOT NULL,
dutDateUp TIMESTAMP,
dutCeckTest BOOLEAN NOT NULL,
dutResult MEDIUMBLOB,
registryUser VARCHAR(12),
testbId INT UNSIGNED,
CONSTRAINT fk_dut_testb FOREIGN KEY (testbId) REFERENCES testbench_tb (testbId),
CONSTRAINT fk_dut_user FOREIGN KEY (registryUser) REFERENCES user_tb (registryUser));

CREATE TABLE user_project_tb(
registryUser VARCHAR(12),
projectId INT UNSIGNED,
CONSTRAINT pk_user_project PRIMARY KEY(registryUser, projectId),
CONSTRAINT fk_project_user FOREIGN KEY (registryUser) REFERENCES user_tb (registryUser),
CONSTRAINT fk_user_project FOREIGN KEY (projectId) REFERENCES project_tb (projectId)); 

CREATE TABLE admin_user_tb(
registryAdmin VARCHAR(12),
registryUser VARCHAR(12),
CONSTRAINT pk_user PRIMARY KEY(registryAdmin, registryUser),
CONSTRAINT fk_admin_user FOREIGN KEY (registryUser) REFERENCES user_tb (registryUser),
CONSTRAINT fk_user_admin FOREIGN KEY (registryAdmin) REFERENCES admin_tb (registryAdmin));
