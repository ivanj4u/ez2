-- ez_param
INSERT INTO ez_param (key_, create_by, create_date, versi, description_, value_) VALUES
	('MAX.LOGIN.FAIL', 'SYSTEM', CURRENT_TIMESTAMP, 0, 'Maksimal Gagal Login', '3');

-- ez_menu
INSERT INTO ez_menu (menu_id, create_by, create_date, update_by, update_date, versi, have_child, menu_class, menu_name, parent_id, no_urut) VALUES
	('900', 'SYSTEM', CURRENT_TIMESTAMP, 0, '1', NULL, 'Parameter', '0', 900),
	('910', 'SYSTEM', CURRENT_TIMESTAMP, 0, '1', NULL, 'User', '900', 5),
	('911', 'SYSTEM', CURRENT_TIMESTAMP, 0, '0', 'com.ez2.acc.view.user.ListUserView', 'Daftar User', '910', 5),
	('912', 'SYSTEM', CURRENT_TIMESTAMP, 0, '0', 'com.ez2.acc.view.user.ListUserGroupView', 'Daftar Group User', '910', 10),
	('913', 'SYSTEM', CURRENT_TIMESTAMP, 0, '0', 'com.ez2.acc.view.user.ListPriviledgeView', 'Daftar Group', '910', 15),
	('920', 'SYSTEM', CURRENT_TIMESTAMP, 0, '1', NULL, 'Menu', '900', 10),
	('921', 'SYSTEM', CURRENT_TIMESTAMP, 0, '0', 'com.ez2.acc.view.menu.ListMenuView', 'Daftar Menu', '920', 5),
	('930', 'SYSTEM', CURRENT_TIMESTAMP, 0, '1', NULL, 'Sys Param', '900', 15),
	('931', 'SYSTEM', CURRENT_TIMESTAMP, 0, '0', 'com.ez2.acc.view.param.ListParamView', 'Daftar Sys Param', '930', 5);

-- ez_group
INSERT INTO ez_group (group_id, create_by, create_date, versi, group_name) VALUES
	(1, 'SYSTEM', CURRENT_TIMESTAMP , 0, 'Administrator');

-- ez_company


-- ez_user
INSERT INTO ez_user (user_id, create_by, create_date, versi, company_code, end_time, login_fail_count, user_name, kodeProvinsi, phone, start_time, status) VALUES
	('admin', 'SYSTEM', CURRENT_TIMESTAMP , 0, '00000', '2020-01-01', 0, 'Admin', 'qwd', '0000', '2017-01-01', '1');

-- ez_user_group
INSERT INTO ez_user_group (group_id, user_id) VALUES
	(1, 'admin');

-- ez_priviledge
INSERT INTO ez_priviledge (group_id, menu_id, is_add, is_delete, is_update, is_view) VALUES
	(1, '900', '1', '1', '1', '1'),
	(1, '910', '1', '1', '1', '1'),
	(1, '911', '1', '1', '1', '1'),
	(1, '912', '1', '1', '1', '1'),
	(1, '913', '1', '1', '1', '1'),
	(1, '920', '1', '1', '1', '1'),
	(1, '921', '1', '1', '1', '1'),
	(1, '930', '1', '1', '1', '1'),
	(1, '931', '1', '1', '1', '1');
