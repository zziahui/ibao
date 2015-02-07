
insert user (username, password, email, role) values('yanghui', '76a05db55a6e180b6ce0f76d1937ebd5', 'yanghui_java@163.com', 'customer');

/*
 * 平台类型 clazz1
 */
insert code(type, name, code) values (1, '优酷', 1);
insert code(type, name, code) values (1, '土豆', 2);
/**
 * 业务类型clazz2
 */
insert code(type, name, code) values (2, '刷量', 1);
insert code(type, name, code) values (2, '刷赞', 2);
insert code(type, name, code) values (2, '刷踩', 3);
insert code(type, name, code) values (2, '刷收藏', 4);
insert code(type, name, code) values (2, '刷订阅', 5);
insert code(type, name, code) values (2, '刷评论', 6);
/*
 * 所属组 group
 */
insert code(type, name, code) values(3, '视频类', 1);
/*
 * 用户角色类型
 */
insert code(type, name, code, keyword) values(4, '超级管理员', 1, 'superadmin');
insert code(type, name, code, keyword) values(4, '管理员', 2, 'admin');
insert code(type, name, code, keyword) values(4, '代理商', 3, 'agent');
insert code(type, name, code, keyword) values(4, '顾客', 4, 'user');
