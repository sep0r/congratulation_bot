DELETE
FROM gmtable1;
DELETE
FROM gmtable2_1;
DELETE
FROM gmtable2_2;
DELETE
FROM gmtable2_3_1;
DELETE
FROM gmtable2_3_2;
DELETE
FROM gmtable2_3_3;
DELETE
FROM gmtable2_3_4;
DELETE
FROM gmtable2_4_1;
DELETE
FROM gmtable2_4_2;
DELETE
FROM gmtable2_4_3;
DELETE
FROM gmtable2_4_4;
DELETE
FROM gmtable3_1;
DELETE
FROM gmtable3_2_1;
DELETE
FROM gmtable3_2_2;
DELETE
FROM gmtable3_3_1;
DELETE
FROM gmtable3_3_2;
DELETE
FROM gmtable3_3_3;
DELETE
FROM gmtable3_4_1;
DELETE
FROM gmtable3_4_2;
DELETE
FROM gmtable3_5;
-- ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO gmtable1 (text)
VALUES ('С прекрасным новым утром!'),
       ('Доброе утро!'),
       ('С добрым утром.'),
       ('Просыпайся! С добрым утром!'),
       ('Доброе утро, моё солнышко!'),
       ('С добрым утром, с началом нового дня!'),
       ('Доброго утречка!'),
       ('Спешу пожелать тебе доброго утра.'),
       ('С началом нового дня, с добрым утром!'),
       ('Доброго утречка, радостного настроения!'),
       ('Доброе утро, мой дорогой человечек!'),
       ('Самого добрейшего утра тебе!'),
       ('Доброго утра и приятного пробуждения.'),
       ('Улыбнись, уже утро.'),
       ('Красивого, вдохновляющего тебе утра!'),
       ('С прекрасным добрым и солнечным утром!'),
       ('Доброе-предоброе утро!'),
       ('Хочу пожелать самого добренького утречка!'),
       ('Восхитительного, доброго утра!'),
       ('С чудным утром!'),
       ('Открывай свои глазки и наслаждайся прекрасным утром!'),
       ('С добрым и волшебным утром, да — именно волшебным.'),
       ('Приятного пробуждения и доброго утра!'),
       ('Утра доброго тебе!'),
       ('Доброго утра тебе я желаю!'),
       ('Вставай скорее! С добрым утром!'),
       ('Просыпайся, соня, солнце уже встало! С добрым утром!'),
       ('Утро доброе, утро светлое, утро несравненное!'),
       ('Открывай скорей глаза, с добрым утром!');

INSERT INTO gmtable2_1 (text)
VALUES ('Пусть'),
       ('Пускай');

INSERT INTO gmtable2_2 (text)
VALUES ('этот день'),
       ('день'),
       ('сегодня день'),
       ('это утро'),
       ('этот денёчек');

INSERT INTO gmtable2_3_1 (text)
VALUES ('будет'),
       ('станет');

INSERT INTO gmtable2_3_2 (text)
VALUES ('будет'),
       ('станет');

INSERT INTO gmtable2_3_3 (text)
VALUES ('сложится'),
       ('пройдёт');

INSERT INTO gmtable2_3_4 (text)
VALUES ('будет наполнен'),
       ('будет заполнен'),
       ('будет насыщен'),
       ('будет полон');

INSERT INTO gmtable2_4_1 (text)
VALUES ('ярким'),
       ('сказочным'),
       ('успешным'),
       ('прекрасным'),
       ('пылающим'),
       ('дружеским'),
       ('порхающим'),
       ('бодрящим');

INSERT INTO gmtable2_4_2 (text)
VALUES ('удачу'),
       ('радость'),
       ('хорошее настроение'),
       ('бодрость духа');

INSERT INTO gmtable2_4_3 (text)
VALUES ('удачно'),
       ('сказачно'),
       ('успешно'),
       ('жизнерадостно');

INSERT INTO gmtable2_4_4 (text)
VALUES ('яркими эмоциями'),
       ('хорошим настроением'),
       ('положительными эмоциями'),
       ('душевным равновесием');

INSERT INTO gmtable3_1 (text)
VALUES ('Желаю');

INSERT INTO gmtable3_2_1 (text)
VALUES ('чтобы');

INSERT INTO gmtable3_2_2 (text)
VALUES ('тебе');

INSERT INTO gmtable3_3_1 (text)
VALUES ('с самого утра'),
       ('сегодня');

INSERT INTO gmtable3_3_2 (text)
VALUES ('предстоящий день');

INSERT INTO gmtable3_3_3 (text)
VALUES ('потрясающего настроения'),
       ('самого доброго'),
       ('прекрасного утра'),
       ('сопутствующей удачи'),
       ('удачного начала дня'),
       ('позитивных встреч'),
       ('лёгкого подъема'),
       ('хорошего настроения'),
       ('вкусного завтрака'),
       ('отличных утренних новостей');

INSERT INTO gmtable3_4_1 (text)
VALUES ('у тебя было'),
       ('ты ощущала');

INSERT INTO gmtable3_4_2 (text)
VALUES ('принес тебе'),
       ('подарил тебе');

INSERT INTO gmtable3_5 (text)
VALUES ('прекрасное настроение'),
       ('отличное настроение'),
       ('солнечное настроение'),
       ('теплый денечек');