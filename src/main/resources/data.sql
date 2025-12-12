insert into status (id, state) values (1, 'EN_ATTENTE');

insert into period (id, start_date, end_date) values (1, '2025-11-20 00:00:00','2025-11-25 00:00:00');

insert into participants(id, first_name, last_name, email, skill) values (100L, 'Jon', 'Doe', 'jdoe@mail.com', 'Développeur');
insert into participants(id, first_name, last_name, email, skill) values (101L, 'James', 'Rocket', 'jamrodket@mail.com', 'Développeur');
insert into participants(id, first_name, last_name, email, skill) values (102L, 'Jessy', 'Rocket', 'jessrocket@mail.com', 'Développeur');
insert into participants(id, first_name, last_name, email, skill) values (103L, 'Jo', 'Jo', 'jojo@mail.com', 'Développeur');

insert into jury_member(id, first_name, last_name, email, title, description, problem, innovation) values (1, 'Pre', 'Pos','mail', 'title', 'description','problem', 'innov' );