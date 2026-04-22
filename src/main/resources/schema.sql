-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Petitioner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Petitioner` (
  `PetitionerID` INT NOT NULL AUTO_INCREMENT,
  `PetitionerName` VARCHAR(255) NOT NULL,
  `PetitionerType` ENUM('Individual', 'Organization') NOT NULL,
  PRIMARY KEY (`PetitionerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CourtCase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CourtCase` (
  `CaseNumber` INT NOT NULL,
  `FilingDate` DATE NOT NULL,
  `Status` VARCHAR(50) NULL,
  `CaseTitle` VARCHAR(255) NULL,
  `PetitionerID` INT NOT NULL,
  PRIMARY KEY (`CaseNumber`),
  INDEX `PetitionerID_idx` (`PetitionerID` ASC) VISIBLE,
  CONSTRAINT `PetitionerID`
    FOREIGN KEY (`PetitionerID`)
    REFERENCES `mydb`.`Petitioner` (`PetitionerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Law`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Law` (
  `LawID` INT NOT NULL AUTO_INCREMENT,
  `LawTitle` VARCHAR(255) NOT NULL,
  `ArticleNumber` VARCHAR(50) NULL,
  PRIMARY KEY (`LawID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`OrganizationPetitioner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`OrganizationPetitioner` (
  `PetitionerID` INT NOT NULL,
  `RegistrationNumber` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`PetitionerID`),
  UNIQUE INDEX `RegistrationNumber_UNIQUE` (`RegistrationNumber` ASC) VISIBLE,
  CONSTRAINT `PetitionerID_op`
    FOREIGN KEY (`PetitionerID`)
    REFERENCES `mydb`.`Petitioner` (`PetitionerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ruling`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ruling` (
  `RulingID` INT NOT NULL AUTO_INCREMENT,
  `VerdictDate` DATE NULL,
  `VerdictText` VARCHAR(45) NULL,
  `CaseNumber` INT NOT NULL,
  PRIMARY KEY (`RulingID`),
  INDEX `fk_Ruling_CourtCase1_idx` (`CaseNumber` ASC) VISIBLE,
  CONSTRAINT `fk_Ruling_CourtCase1`
    FOREIGN KEY (`CaseNumber`)
    REFERENCES `mydb`.`CourtCase` (`CaseNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Hearing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Hearing` (
  `HearingID` INT NOT NULL AUTO_INCREMENT,
  `HearingDate` DATE NOT NULL,
  `Location` VARCHAR(255) NULL,
  `CaseNumber` INT NOT NULL,
  PRIMARY KEY (`HearingID`),
  INDEX `fk_Hearing_CourtCase1_idx` (`CaseNumber` ASC) VISIBLE,
  CONSTRAINT `fk_Hearing_CourtCase1`
    FOREIGN KEY (`CaseNumber`)
    REFERENCES `mydb`.`CourtCase` (`CaseNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Judge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Judge` (
  `JudgeID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(100) NOT NULL,
  `LastName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`JudgeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reviews` (
  `CourtCase_CaseNumber` INT NOT NULL,
  `Law_LawID` INT NOT NULL,
  PRIMARY KEY (`CourtCase_CaseNumber`, `Law_LawID`),
  INDEX `fk_CourtCase_has_Law_Law1_idx` (`Law_LawID` ASC) VISIBLE,
  INDEX `fk_CourtCase_has_Law_CourtCase1_idx` (`CourtCase_CaseNumber` ASC) VISIBLE,
  CONSTRAINT `fk_CourtCase_has_Law_CourtCase1`
    FOREIGN KEY (`CourtCase_CaseNumber`)
    REFERENCES `mydb`.`CourtCase` (`CaseNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CourtCase_has_Law_Law1`
    FOREIGN KEY (`Law_LawID`)
    REFERENCES `mydb`.`Law` (`LawID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Attends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Attends` (
  `Judge_JudgeID` INT NOT NULL,
  `Hearing_HearingID` INT NOT NULL,
  `PetitionerID` INT NOT NULL,
  INDEX `fk_Judge_has_Hearing_Hearing1_idx` (`Hearing_HearingID` ASC) VISIBLE,
  INDEX `fk_Judge_has_Hearing_Judge1_idx` (`Judge_JudgeID` ASC) VISIBLE,
  INDEX `PetitionerID_idx` (`PetitionerID` ASC) VISIBLE,
  CONSTRAINT `fk_Judge_has_Hearing_Judge1`
    FOREIGN KEY (`Judge_JudgeID`)
    REFERENCES `mydb`.`Judge` (`JudgeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Judge_has_Hearing_Hearing1`
    FOREIGN KEY (`Hearing_HearingID`)
    REFERENCES `mydb`.`Hearing` (`HearingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `PetitionerID_attends`
    FOREIGN KEY (`PetitionerID`)
    REFERENCES `mydb`.`Petitioner` (`PetitionerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`IndividualPetitioner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`IndividualPetitioner` (
  `PassportID` VARCHAR(50) NOT NULL,
  `PetitionerID` INT NOT NULL,
  PRIMARY KEY (`PetitionerID`),
  UNIQUE INDEX `PassportID_UNIQUE` (`PassportID` ASC) VISIBLE,
  INDEX `fk_IndividualPetitioner_Petitioner1_idx` (`PetitionerID` ASC) VISIBLE,
  CONSTRAINT `fk_IndividualPetitioner_Petitioner1`
    FOREIGN KEY (`PetitionerID`)
    REFERENCES `mydb`.`Petitioner` (`PetitionerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
