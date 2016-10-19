-- sequence

drop SEQUENCE user_seq;

create SEQUENCE user_seq
start with 1
increment by 1
maxvalue 9999999999;

drop SEQUENCE guestbook_seq;

create SEQUENCE guestbook_seq
start with 1
increment by 1
maxvalue 9999999999;

drop SEQUENCE board_seq;

create SEQUENCE board_seq
start with 1
increment by 1
maxvalue 9999999999;