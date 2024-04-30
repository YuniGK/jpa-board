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
