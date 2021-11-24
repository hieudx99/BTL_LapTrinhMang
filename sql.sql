
use laptrinhmang;

CREATE TABLE tblCard (id int(10) NOT NULL AUTO_INCREMENT, image varchar(255) NOT NULL, number int(10) NOT NULL, type varchar(255) NOT NULL, PRIMARY KEY (id));
CREATE TABLE tblDealtCard (id int(10) NOT NULL AUTO_INCREMENT, totalValue int(10) NOT NULL, Cardid int(10) NOT NULL, Playerid int(10) NOT NULL, Matchid int(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE tblMatch (id int(10) NOT NULL AUTO_INCREMENT, totalSlot int(10) NOT NULL, fisrtRank varchar(255), secondRank varchar(255), thirdRank varchar(255), lastRank varchar(255), Tableid int(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE tblPlayer (id int(10) NOT NULL AUTO_INCREMENT, username varchar(255) NOT NULL UNIQUE, password varchar(255) NOT NULL, point int(10) NOT NULL, status varchar(255) NOT NULL, firstRankTime int(10) NOT NULL, secondRankTime int(10) NOT NULL, thirdRankTime int(10) NOT NULL, lastRankTime int(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE tblTable (id int(10) NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, status varchar(255) NOT NULL, PRIMARY KEY (id));
ALTER TABLE tblDealtCard ADD CONSTRAINT FKDealtCard596187 FOREIGN KEY (Cardid) REFERENCES tblCard (id);
ALTER TABLE tblMatch ADD CONSTRAINT FKMatch430657 FOREIGN KEY (Tableid) REFERENCES tblTable (id);
ALTER TABLE tblDealtCard ADD CONSTRAINT FKDealtCard83162 FOREIGN KEY (Playerid) REFERENCES tblPlayer (id);
ALTER TABLE tblDealtCard ADD CONSTRAINT FKDealtCard287867 FOREIGN KEY (Matchid) REFERENCES tblMatch (id);

insert into tblPlayer(username, password, point, status, firstRankTime, secondRankTime, thirdRankTime, lastRankTime) 
values ('hieudx', '123456', 0, 'O', 0, 0, 0 ,0);
insert into tblPlayer(username, password, point, status, firstRankTime, secondRankTime, thirdRankTime, lastRankTime) 
values ('duyvd', '123456', 0, 'O', 0, 0, 0 ,0);
insert into tblPlayer(username, password, point, status, firstRankTime, secondRankTime, thirdRankTime, lastRankTime) 
values ('lochd', '123456', 0, 'O', 0, 0, 0 ,0);
insert into tblPlayer(username, password, point, status, firstRankTime, secondRankTime, thirdRankTime, lastRankTime) 
values ('binhnt', '123456', 0, 'O', 0, 0, 0 ,0);