-- 테스트 계정
insert into user_account (user_id, user_password, role_types, nickname, email, memo, provide_id
                         , provider, created_at, created_by, modified_at, modified_by) values
    ('test', '1234', 'ADMIN', 'Test', 'test@mail.com', 'I am Test.', null, null, now(), 'test', now(), 'test')
    , ('test2', '1234', 'USER', 'Test2', 'test2@mail.com', 'I am Test2.', null, null, now(), 'test2', now(), 'test2')
    , ('test3', '1234', 'MANAGER', 'Test3', 'test3@mail.com', 'I am Test3.', null, null, now(), 'test3', now(), 'test3')
    , ('test4', '1234', 'MANAGER,DEVELOPER', 'Test4', 'test4@mail.com', 'I am Test4.', null, null, now(), 'test4', now(), 'test4')
;

-- 1 게시글
insert into article (user_id, title, content, created_by, modified_by, created_at, modified_at) values
    ('test2', 'Quisque ut erat.', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.
Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.
Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.
#pink', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50')
;

insert into article_comment (article_id, user_id, parent_comment_id, content, created_at, modified_at, created_by, modified_by) values
(49, 'test', null, 'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', '2021-03-02 22:40:04', '2021-04-27 15:38:09', 'Lind', 'Orv');

insert into article_comment (article_id, user_id, parent_comment_id, content, created_at, modified_at, created_by, modified_by) values
(49, 'test', 1, '퍼가요~', '2021-03-02 22:40:04', '2021-04-27 15:38:09', 'Test', 'Test');

insert into article_hashtag (article_id, hashtag_id) values
(1, 11);
