-- users

-- insert
insert into users
values(user_seq.nextval, '안대혁', 'kickscar@gmail.com', '1234', 'male');

-- delete
delete from users;

commit;

select * from users;

<<<<<<< HEAD
select * from board;

delete from board where no=4;

-- select (login)
select no, name from users where email='kickscar@gmail.com'
and password='1234';



-- board

--view
select no, title, content, hit from board where no = 2;
update board set hit = hit + 1 where no = 2; -- 조회수 늘리기
-- list
select * from board;

select *
from(select rownum as rn, no, title, hit, reg_date, name, users_no, depth
		from (select a.no, a.title, a.hit, 
						to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date, 
						b.name, a.USERS_NO, a.DEPTH
				from board a, users b
				where a.users_no = b.no
				-- and title like '%kwd%' or content like '%kwd%'
				order by group_no desc, order_no asc))
where (1-1)*5+1 <= rn	-- current page:2, page_size:5
and rn <= 1*5; 

-- 새로운글
insert into board 
values(board_seq.nextval, '문제', '내용있다', sysdate, 0, 
			nvl((select max(group_no) from board), 0) + 1, 
																	1, 0, 9); 

select nvl(max(group_no), 0) from board;

commit;

update board set users_no=3 where no=3;

-- 답글
update board
set order_no = order_no + 1
where group_no = 2	-- 부모글 그룹
and order_no > 1; -- 부모글 순서

insert into board 
values(board_seq.nextval, '짬뽕', '냉무', sysdate, 0, 
			2, -- 부모글 그룹 
			2, -- 부모글 순서 + 1 
			1, -- 부모글 깊이 + 1
			2); 
=======
-- select (login)
select no, name from users where email='kickscar@gmail.com'
and password='1234';
>>>>>>> branch 'master' of https://github.com/motherfather/mysite3.git
