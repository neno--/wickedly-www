create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	enabled boolean not null
);

create table roles(
	role varchar_ignorecase(50) not null primary key
);

create table user_roles (
	username varchar_ignorecase(50) not null,
	role varchar_ignorecase(50) not null,
	constraint fk_user_roles_users foreign key(username) references users(username),
	constraint fk_user_roles_roles foreign key(role) references roles(role)
);

INSERT INTO roles VALUES ('ROLE_ADMIN');
INSERT INTO roles VALUES ('ROLE_USER');
INSERT INTO roles VALUES ('ROLE_REST');

INSERT INTO users VALUES ('admin', 'admin', true);
INSERT INTO users VALUES ('user', 'user', true);
INSERT INTO users VALUES ('locked', 'locked', false);
INSERT INTO users VALUES ('rest', 'rest', true);

INSERT INTO user_roles VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles VALUES ('user', 'ROLE_USER');
INSERT INTO user_roles VALUES ('locked', 'ROLE_USER');
INSERT INTO user_roles VALUES ('rest', 'ROLE_REST');