create database laptrinhmang;
use laptrinhmang;

create table tblPlayer (
	id integer auto_increment,
    username varchar(255) not null unique,
    password varchar(255) not null,
    point integer not null,
    winTotal integer not null,
    loseTotal integer not null,
    primary key(id)
);

create table tblMatch (
	id integer auto_increment,
    date datetime not null,
    primary key(id)
);

create table tblDealtCard(
	id integer auto_increment,
    position integer not null,
    totalValue integer not null,
    tblPlayerid integer not null,
    tblMatchid integer not null,
    primary key(id),
    foreign key(tblPlayerid) references tblPlayer(id),
    foreign key(tblMatchid) references tblMatch(id)
);

create table tblCard(
	id integer auto_increment,
    number integer not null,
    type varchar(255) not null,
    primary key(id)
);

create table tblPlayerCard (
	id integer auto_increment,
    tblCardid integer,
    tblDealtCardid integer,
    primary key(id),
    foreign key(tblDealtCardid) references tblDealtCard(id),
    foreign key(tblCardid) references tblCard(id)
);

insert into tblCard(number, type) values
(1, 'heart'), (1, 'diamond'), (1, 'club'), (1, 'spade'), 
(2, 'heart'), (2, 'diamond'), (2, 'club'), (2, 'spade'), 
(3, 'heart'), (3, 'diamond'), (3, 'club'), (3, 'spade'), 
(4, 'heart'), (4, 'diamond'), (4, 'club'), (4, 'spade'), 
(5, 'heart'), (5, 'diamond'), (5, 'club'), (5, 'spade'), 
(6, 'heart'), (6, 'diamond'), (6, 'club'), (6, 'spade'), 
(7, 'heart'), (7, 'diamond'), (7, 'club'), (7, 'spade'), 
(8, 'heart'), (8, 'diamond'), (8, 'club'), (8, 'spade'), 
(9, 'heart'), (9, 'diamond'), (9, 'club'), (9, 'spade');

insert into tblPlayer(username, password, point, winTotal, loseTotal) 
values ('hieudx', '123456', 0, 0 ,0),
 ('duyvd', '123456', 0, 0 ,0),
('lochd', '123456', 0, 0 ,0),
 ('binhnt', '123456', 0, 0 ,0);
 




 
 