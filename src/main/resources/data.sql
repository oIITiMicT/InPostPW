insert into permissions(id, name) values (1, 'get user info');
insert into roles(id, name) values (1, 'admin');
insert into roles(id, name) values (2, 'user');
insert into roles_permissions(role_id, permission_id) VALUES (1, 1);
insert into users(id, email, password_hash, salt, username, role_id) values ('1000', 'test@gmail.com', '$2a$12$V7b/Sa.6u7N5qM0/iwBEC.l32sWeNOW5tnIj/XaW4LHCRY7radPfS', '[�7�', 'test','1');
insert into users(id, email, password_hash, salt, username, role_id) values ('1001', 'admin@gmail.com', '$2a$12$YZnKWQYjlw/guH6WaqRYk.DOOk9EWGajx1DYaupY4Igc/UlMWhh/q', 'Vɻ�', 'admin','1');
insert into packages(id, tracker, recipient_id, sender_id, destination_address, shipping_address) values (1000, 'QWERTY', '1000', '1001', 'Warsaw', 'Krakow');
insert into packages(id, tracker, recipient_id, sender_id, destination_address, shipping_address) values (1001, 'ZXCVBB', '1001', '1000', 'Krakow', 'Warszaw');
insert into stages(id, description, time, package_id) values ('1000', 'new stage', '2022-11-11', '1000');