SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE reviews;
TRUNCATE TABLE Attends;
TRUNCATE TABLE IndividualPetitioner;
TRUNCATE TABLE OrganizationPetitioner;
TRUNCATE TABLE Ruling;
TRUNCATE TABLE Hearing;
TRUNCATE TABLE CourtCase;
TRUNCATE TABLE Law;
TRUNCATE TABLE Judge;
TRUNCATE TABLE Petitioner;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO Petitioner (PetitionerID, PetitionerName, PetitionerType) VALUES
(1, 'Уповноважений ВРУ з прав людини',          'Organization'),
(2, 'Валерія Лутковська',                        'Individual'),
(3, 'Громадська організація "Право на захист"',  'Organization'),
(4, 'Олексій Петренко',                          'Individual'),
(5, 'Асоціація правників України',               'Organization'),
(6, 'Марія Коваленко',                           'Individual');


INSERT INTO OrganizationPetitioner (PetitionerID, RegistrationNumber) VALUES
(1, '00012345'),
(3, '99988877'),
(5, '12345678');


INSERT INTO IndividualPetitioner (PetitionerID, PassportID) VALUES
(2, 'ММ123456'),
(4, 'КА567890'),
(6, 'АВ654321');


INSERT INTO Law (LawID, LawTitle, ArticleNumber) VALUES
(1, 'Кодекс адміністративного судочинства України', 'стаття 171-2'),
(2, 'Конституція України',                           'стаття 150'),
(3, 'Цивільний кодекс України',                     'стаття 203'),
(4, 'Кримінальний процесуальний кодекс України',    'стаття 237'),
(5, 'Закон України "Про вибори народних депутатів"','стаття 54');


INSERT INTO Judge (JudgeID, FirstName, LastName) VALUES
(1, 'Юрій',      'Баулін'),
(2, 'Сергій',    'Вдовіченко'),
(3, 'Михайло',   'Гультай'),
(4, 'Михайло',   'Запорожець'),
(5, 'Микола',    'Мельник'),
(6, 'Станіслав', 'Шевчук'),
(7, 'Ігор',      'Сліденко');


INSERT INTO CourtCase (CaseNumber, FilingDate, Status, CaseTitle, PetitionerID) VALUES
(201506, '2015-01-15', 'CLOSED',
'Справа щодо конституційності оскарження рішень про адмінвідповідальність', 1),
(201507, '2015-03-20', 'CLOSED',
'Справа щодо конституційності окремих положень виборчого законодавства', 2),
(201508, '2015-06-10', 'OPEN',
'Справа щодо відповідності Конституції положень Цивільного кодексу', 3),
(201601, '2016-02-05', 'OPEN',
'Справа щодо конституційності положень КПК стосовно обшуків', 4),
(201602, '2016-04-18', 'OPEN',
'Справа щодо виборчих прав громадян при формуванні парламенту', 5);


INSERT INTO reviews (CourtCase_CaseNumber, Law_LawID) VALUES
(201506, 1),
(201507, 2),
(201507, 5),
(201508, 3),
(201601, 4),
(201602, 5);

INSERT INTO Hearing (HearingID, HearingDate, Location, CaseNumber) VALUES
(1, '2015-04-08', 'Зал пленарних засідань КСУ', 201506),
(2, '2015-05-12', 'Зал пленарних засідань КСУ', 201507),
(3, '2015-07-22', 'Зал пленарних засідань КСУ', 201508),
(4, '2016-03-10', 'Зал пленарних засідань КСУ', 201601),
(5, '2016-05-25', 'Зал пленарних засідань КСУ', 201602);

INSERT INTO Attends (Judge_JudgeID, Hearing_HearingID, PetitionerID) VALUES
(1, 1, 1),
(4, 1, 1),
(6, 1, 1),
(2, 2, 2),
(3, 2, 2),
(5, 3, 3),
(7, 3, 3),
(1, 4, 4);

INSERT INTO Ruling (RulingID, VerdictDate, VerdictText, CaseNumber) VALUES
(1, '2015-04-08',
'Визнати положення неконституційними (№ 3-рп/2015)', 201506),
(2, '2015-06-01',
'Визнати положення конституційними (№ 4-рп/2015)', 201507),
(3, '2015-09-15',
'Провадження закрито у зв''язку з відкликанням подання', 201507);