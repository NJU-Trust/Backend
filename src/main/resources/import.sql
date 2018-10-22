insert into user (username, password, credit_score, credit_rating, user_level) values ('test', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', 85, 'A', 'PRIMARY');
INSERT INTO user (username, credit_rating, credit_score, institution, password, student_id) VALUES ('cross1', 'A', '85', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', '161250001');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross2', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS',  '161250002');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross3', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS',  '161250003');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross4', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', '161250004');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross5', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', '161250005');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross6', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', '161250006');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross7', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', '161250007');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross8', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', '161250008');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross9', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', '161250009');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross10', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', '161250010');
INSERT INTO user (username, institution, password, student_id) VALUES ('cross11', '软院', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', '161250011');

insert into user_month_statistics(id, income, disc, surplus, username, debt) values (1, 1000, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (2, 1050, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (3, 1010, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (4, 900, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (5, 890, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (6, 1001, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (7, 1023, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (8, 1045, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (9, 1567, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (10, 1234, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (11, 1111, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (12, 1243, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (13, 1456, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (14, 1325, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (15, 1456, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (16, 1346, 500, 500, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (17, 908, 500, 400, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (18, 1432, 500, 300, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (19, 1200, 500, 400, 'test', 100);
insert into user_month_statistics(id, income, disc, surplus, username, debt) values (60, 1000, 500, 501, 'test', 100);
insert into user (username, password, credit_score, roles) values ('admin', '$2a$10$3lj/cEZUJiepqAwekmiaKe9p6X7gudLsc9jiZixB.53PStsrdS7pS', 0, 'ADMIN');
insert into base_target(id, username, target_type, collected_money, money, project_description, start_time, target_state, target_rating, completion_rate, identity_option, use_of_funds, proof, target_rating_score) values(1, 'test', 'SMALL', 1440, 1800, 'target 1 description', '2018-06-10', 'ON_GOING', 'A', 80, 0, '鞋帽服饰', 'proof path', 0.95);
insert into small_target(id, classification) VALUES (1, 0);
INSERT INTO user_info_check_record(id, check_item, check_state, description, message, time, username) VALUES ('1', 'VOLUNTEERTIME', 'UPDATE', 'description', 'message', '2018-09-13', 'test');
INSERT INTO base_user_evidence(data_type, id, evidence, state, time, item, username) VALUES ('VOLUNTEERTIME', '1', 'evidence1', 'UPDATE', '2018-09-13', '1', 'test');
INSERT INTO base_user_evidence(data_type, id, evidence, state, time, item, username) VALUES ('VOLUNTEERTIME', '2', 'evidence2', 'ONGING', '2018-09-13', '1', 'test');
INSERT INTO volunteer_evidence(length, id) VALUES ('1', '1');
INSERT INTO volunteer_evidence(length, id) VALUES ('2', '2');
INSERT INTO unstructured_data(id, data_type, score, username) VALUES(1, 0, 100, 'test');
INSERT INTO repayment(id, difficulty, duration, interest_rate, remaining_amount, start_date, total_interest, type, target_id, username) VALUES(1, 80.0, 3, 5.55, 1800, '2018-10-08', 24.98, 'PRE_INTEREST', 1, 'test');
INSERT INTO repayment(id, difficulty, duration, interest_rate, remaining_amount, start_date, total_interest, type, target_id, username) VALUES(2, 50.0, 24, 5.85, 8000, '2018-10-25', 936, 'PRE_INTEREST', 2, 'test');
INSERT INTO repayment_record(id, time, actual_repay_date, interest, period, principal, remaining_principal, return_date, sum, username, target_id) VALUES (1, '2018-9-13', NULL, 90, 100, 100, 200, '2018-10-08', 200, 'test', 1) ;
insert into base_target(id, username, target_type, collected_money, money, project_description, start_time, target_state, target_rating, completion_rate, identity_option, use_of_funds, proof, target_rating_score) values (2, 'test', 'LARGE', 6400, 8000, 'target 2 description', '2018-06-10', 'ON_GOING', 'A', 80, 0, '托福培训', 'proof path', 0.95);
insert into large_target(id, classification) VALUES (2, 0);
insert into trade(id, contact, create_date, from_username, goods_desc, goods_name, goods_pic, goods_price, goods_type, is_rating, is_selling, rating, to_username) VALUES (1, '13323389923', '2018-09-23', 'test', '名创优品39.9入 带过一次 可小刀', '名创优品粉红顽皮帽子', 'http://localhost:8000/images/upload/awdxvzvwada.png', 23, '化妆洗漱', FALSE , TRUE, 0, NULL )
insert into trade(id, contact, create_date, from_username, goods_desc, goods_name, goods_pic, goods_price, goods_type, is_rating, is_selling, rating, to_username) VALUES (2, '123', '2018-09-24', 'test', '123', '123', 'http://localhost:8000/images/upload/awdxvzvwada.png', 23, '影音家电', FALSE , TRUE, 0, NULL )
insert into trade(id, contact, create_date, from_username, goods_desc, goods_name, goods_pic, goods_price, goods_type, is_rating, is_selling, rating, to_username) VALUES (3, '123', '2018-09-25', 'test', '123', '123', 'http://localhost:8000/images/upload/awdxvzvwada.png', 23, '化妆洗漱', FALSE , TRUE, 0, NULL )
insert into trade(id, contact, create_date, from_username, goods_desc, goods_name, goods_pic, goods_price, goods_type, is_rating, is_selling, rating, to_username) VALUES (4, '123', '2018-09-25', 'test', '123', '123', 'http://localhost:8000/images/upload/awdxvzvwada.png', 23, '化妆洗漱', FALSE , TRUE, 0, NULL )
insert into trade(id, contact, create_date, from_username, goods_desc, goods_name, goods_pic, goods_price, goods_type, is_rating, is_selling, rating, to_username) VALUES (5, '123', '2018-09-25', 'test', '123', '123', 'http://localhost:8000/images/upload/awdxvzvwada.png', 23, '化妆洗漱', FALSE , TRUE, 0, NULL )
insert into trade(id, contact, create_date, from_username, goods_desc, goods_name, goods_pic, goods_price, goods_type, is_rating, is_selling, rating, to_username) VALUES (6, '123', '2018-09-25', 'test', '123', '123', 'http://localhost:8000/images/upload/awdxvzvwada.png', 23, '化妆洗漱', FALSE , TRUE, 0, NULL )
insert into trade(id, contact, create_date, from_username, goods_desc, goods_name, goods_pic, goods_price, goods_type, is_rating, is_selling, rating, to_username) VALUES (7, '123', '2018-09-25', 'test', '123', '123', 'http://localhost:8000/images/upload/awdxvzvwada.png', 23, '化妆洗漱', FALSE , TRUE, 0, NULL )

