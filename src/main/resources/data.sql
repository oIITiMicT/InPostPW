insert into permissions(id, name) values (1, 'get user info');
insert into roles(id, name) values (1, 'admin');
insert into roles(id, name) values (2, 'user');
insert into roles_permissions(role_id, permission_id) VALUES (1, 1);