INSERT INTO WN_USER values (1, 'Grondal', 'matus.habala@gmail.com', '$2a$10$1sMmWbr6UVNO2VDl.B/Enu1/QtalFqaXBJHk57eL1mIdeo9iDkNAe', 'Y'); /*password: HC92001*/
INSERT INTO WN_USER values (2, 'Torgal', 'dhabala@gmail.com', '$2a$10$K18h9MYPfg7DPxhS88xx4unrtT/nRAeAEctw5huufO1QnKjKQIidW', 'Y'); /*password: hovno*/
INSERT INTO WN_USER values (3, 'BASE_USER', 'base.user@gmail.com', '$2a$10$kzR/3tZJKx2kUqD4mYBQLu9qLXwzjodxocU4qw3PxQ6YUCtVUEXKC', 'Y'); /*password: baseuserpass*/

INSERT INTO WN_ROLE values (1, 'USER');
INSERT INTO WN_ROLE values (2, 'ADMIN');

INSERT INTO WN_USER_ROLE values (1, 1);
INSERT INTO WN_USER_ROLE values (1, 2);
INSERT INTO WN_USER_ROLE values (2, 1);
INSERT INTO WN_USER_ROLE values (2, 2);

INSERT INTO WN_TASK_THEME VALUES (1, 'Work-Notes', 'Vlastný projekt s cieľom vytvoriť niečo vlastné od nápadu cez realizáciu až po deployment',
'VALID', PARSEDATETIME('22-01-2019 8:00:00', 'dd-MM-yyyy hh:mm:ss'), null, 1);

INSERT INTO WN_TASK values (1, null, PARSEDATETIME('20-01-19 11:34:24','dd-MM-yy hh:mm:ss'), null, 'Implementovať Work-notes aplikáciu', 'TOP', 'IN_PROGRESS', 1);
INSERT INTO WN_TASK values (2, 1, PARSEDATETIME('07-02-19 8:12:59','dd-MM-yy hh:mm:ss'), null, 'Naprogramovať preväzbovanie medzi UJ s pseudoDIČ a DIČ', 'TOP', 'IN_PROGRESS', 1);
INSERT INTO WN_TASK values (3, null, PARSEDATETIME('13-02-19 18:59:00','dd-MM-yy hh:mm:ss'), null, 'Pridať vnorene tasky (aby som mohol byt nejaky task rozdeleny do viacerých čiastkových)', 'LOW', 'OPENED', 1);
INSERT INTO WN_TASK values (4, null, PARSEDATETIME('10-02-19 16:48:59','dd-MM-yy hh:mm:ss'), null, 'Pridať zadávanie/zobrazovanie času vytvorenia/zatvorenia tasku', 'LOW', 'IN_PROGRESS', 1);
INSERT INTO WN_TASK values (5, null, PARSEDATETIME('25-01-19 15:54:19','dd-MM-yy hh:mm:ss'), PARSEDATETIME('01-03-19 17:16:08','dd-MM-yy hh:mm:ss'), 'Dorobiť ukladanie zobrazovanie nových taskov cez GUI', 'HIGH', 'CLOSED', 1);

INSERT INTO WN_TASK values (6, null, PARSEDATETIME('07-04-19 22:10:34', 'dd-MM-yy hh:mm:ss'), null, 'Optimalizovať excelovskú tabuľku tak, aby nezabíjala CPU', 'HIGH', 'CLOSED', 2);

