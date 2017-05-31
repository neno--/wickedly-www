create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO users VALUES ('admin', 'admin', true);
INSERT INTO users VALUES ('user', 'user', true);
INSERT INTO users VALUES ('locked', 'locked', false);

INSERT INTO authorities VALUES ('admin', 'ADMIN');
INSERT INTO authorities VALUES ('user', 'USER');
INSERT INTO authorities VALUES ('locked', 'USER');