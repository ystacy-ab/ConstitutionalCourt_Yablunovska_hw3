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
(1, 'Уповноважений ВРУ з прав людини', 'Organization'),
(2, 'Валерія Лутковська', 'Individual'),
(3, 'Громадська організація "Право на захист"', 'Organization');

INSERT INTO OrganizationPetitioner (PetitionerID, RegistrationNumber) VALUES 
(1, '00012345'),
(3, '99988877');

INSERT INTO IndividualPetitioner (PetitionerID, PassportID) VALUES 
(2, 'ММ123456');

INSERT INTO Law (LawID, LawTitle, ArticleNumber) VALUES 
(1, 'Кодекс адміністративного судочинства України', 'стаття 171-2'),
(2, 'Конституція України', 'стаття 150');

INSERT INTO CourtCase (CaseNumber, FilingDate, Status, CaseTitle, PetitionerID) VALUES 
(201506, '2015-01-15', 'CLOSED', 'Справа щодо конституційності оскарження рішень про адмінвідповідальність', 1);

INSERT INTO reviews (CourtCase_CaseNumber, Law_LawID) VALUES 
(201506, 1);

INSERT INTO Judge (JudgeID, FirstName, LastName) VALUES 
(1, 'Юрій', 'Баулін'),
(2, 'Сергій', 'Вдовіченко'),
(3, 'Михайло', 'Гультай'),
(4, 'Михайло', 'Запорожець'),
(5, 'Микола', 'Мельник'),
(6, 'Станіслав', 'Шевчук'),
(7, 'Ігор', 'Сліденко');

INSERT INTO Hearing (HearingID, HearingDate, Location, CaseNumber) VALUES 
(1, '2015-04-08', 'Зал пленарних засідань КСУ', 201506);

INSERT INTO Attends (Judge_JudgeID, Hearing_HearingID, PetitionerID) VALUES 
(1, 1, 1),
(4, 1, 1),
(6, 1, 1);

INSERT INTO Ruling (RulingID, VerdictDate, VerdictText, CaseNumber) VALUES 
(1, '2015-04-08', 'Визнати положення неконституційними (№ 3-рп/2015)', 201506);
