INSERT INTO WN_USER values (1, 'Grondal', 'matus.habala@gmail.com', '$2a$10$RFybpFYrNRD/8KasplMfduse2VFr6YVjvAeAVvVJK4vUag84Bw//K', 'Y');

INSERT INTO WN_ROLE values (1, 'USER');

INSERT INTO WN_USER_ROLE values (1, 1);

INSERT INTO WN_TASK_THEME VALUES (1, 'Work-Notes', 'Vlastný projekt s cieľom vytvoriť niečo vlastné od nápadu cez realizáciu až po deployment',
'VALID', convert(DATETIME, '2019-01-22 08:00:00', 120), null, 1);

INSERT INTO WN_TASK values (1, null, convert(DATETIME, '2019-01-20 11:34:24', 120), null, 'Implementovať Work-notes aplikáciu', 'TOP', 'IN_PROGRESS', 1);
INSERT INTO WN_TASK values (2, 1, convert(DATETIME, '2019-02-07 08:12:59', 120), null, 'Naprogramovať preväzbovanie medzi UJ s pseudoDIČ a DIČ', 'TOP', 'IN_PROGRESS', 1);
INSERT INTO WN_TASK values (3, null, convert(DATETIME, '2019-02-13 18:59:00', 120), null, 'Pridať vnorene tasky (aby som mohol byt nejaky task rozdeleny do viacerých čiastkových)', 'LOW', 'OPENED', 1);
INSERT INTO WN_TASK values (4, null, convert(DATETIME, '2019-02-10 16:48:59', 120), null, 'Pridať zadávanie/zobrazovanie času vytvorenia/zatvorenia tasku', 'LOW', 'IN_PROGRESS', 1);
INSERT INTO WN_TASK values (5, null, convert(DATETIME, '2019-01-25 15:54:19', 120), convert(DATETIME, '2019-03-01 17:16:08', 120), 'Dorobiť ukladanie zobrazovanie nových taskov cez GUI', 'HIGH', 'CLOSED', 1);

