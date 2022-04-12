CREATE TABLE `companyInfo` (
    `idx` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
    `companyName` VARCHAR(40) NOT NULL DEFAULT '(주)율도국' COLLATE 'utf8mb3_general_ci',
    `companyLeader` VARCHAR(10) NOT NULL DEFAULT '홍길동' COLLATE 'utf8mb3_general_ci',
    `companyAddress` VARCHAR(500) NOT NULL DEFAULT '율도국' COLLATE 'utf8mb3_general_ci',
    `companyRegNum` VARCHAR(12) NOT NULL DEFAULT '12-345-67890' COLLATE 'utf8mb3_general_ci',
    `companyMainPhone` VARCHAR(16) NOT NULL DEFAULT '02-1234-5678' COLLATE 'utf8mb3_general_ci',
    `companyEmail` VARCHAR(80) NOT NULL DEFAULT 'gogildong@uldokuk.co.ul' COLLATE 'utf8mb3_general_ci',
    `companyFax` VARCHAR(16) NOT NULL DEFAULT '02-1234-5679' COLLATE 'utf8mb3_general_ci',
    `companyRegDate` DATETIME NOT NULL DEFAULT current_timestamp(),
    `updateDate` DATETIME NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`idx`) USING BTREE
)
COMMENT='회사정보'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

CREATE TABLE `companyHistory` (
    `idx` INT(11) NOT NULL AUTO_INCREMENT,
    `year` VARCHAR(4) NOT NULL COLLATE 'utf8mb3_general_ci',
    `month` VARCHAR(2) NOT NULL COLLATE 'utf8mb3_general_ci',
    `history` VARCHAR(2000) NOT NULL COLLATE 'utf8mb3_general_ci',
    `history_en` VARCHAR(2000) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    PRIMARY KEY (`idx`) USING BTREE
)
COMMENT='회사연혁'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

CREATE TABLE `userInfo` (
    `id` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
    `password` VARCHAR(256) NOT NULL COLLATE 'utf8mb3_general_ci',
    `auth` ENUM('A','U','N') NOT NULL DEFAULT 'U' COLLATE 'utf8mb3_general_ci',
    `regDate` DATETIME NOT NULL DEFAULT current_timestamp(),
    `loginIP` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    `loginTime` DATETIME NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`) USING BTREE
)
COMMENT='사용자 관리테이블'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

CREATE TABLE `menuList` (
    `idx` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `menuName` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
    `menuTitle` varchar(500) null COLLATE 'utf8mb3_general_ci',
    `menuComment` VARCHAR(500) NULL DEFAULT '' COLLATE 'utf8mb3_general_ci',
    `menuOrder` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
    PRIMARY KEY (`idx`) USING BTREE
)
COMMENT='메뉴목록'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

CREATE TABLE `tabList` (
    `idx` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `menuId` INT(10) UNSIGNED NOT NULL,
    `tabName` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
    `tabComment` VARCHAR(500) NULL DEFAULT '' COLLATE 'utf8mb3_general_ci',
    `tabOrder` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
    PRIMARY KEY (`idx`) USING BTREE
)
COMMENT='메뉴별 탭 목록'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;


CREATE TABLE `boardList` (
    `idx` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `menuId` INT(10) UNSIGNED NOT NULL,
    `tabId` INT(10) UNSIGNED NOT NULL,
    `title` VARCHAR(200) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    `content` TEXT NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    `writer` VARCHAR(12) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    `writeDate` DATETIME NOT NULL DEFAULT current_timestamp(),
    `password` VARCHAR(256) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    PRIMARY KEY (`idx`) USING BTREE
)
COMMENT='게시판'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

CREATE TABLE `imageList` (
    `idx` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `menuId` INT(10) UNSIGNED NOT NULL DEFAULT '0',
    `tabId` INT(10) UNSIGNED NOT NULL DEFAULT '0',
    `mimeType` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    `name` VARCHAR(100) NOT NULL COLLATE 'utf8mb3_general_ci',
    `path` VARCHAR(256) NOT NULL COLLATE 'utf8mb3_general_ci',
    `size` BIGINT(20) UNSIGNED NULL DEFAULT NULL,
    `writeDate` DATETIME NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (`idx`) USING BTREE
)
COMMENT='이미지목록'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;

create table detailimagelist(
    `idx` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `imageId` INT UNSIGNED NULL DEFAULT 0 NOT NULL,
    `mimeType` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    `name` VARCHAR(100) NOT NULL COLLATE 'utf8mb3_general_ci',
    `path` VARCHAR(256) NOT NULL COLLATE 'utf8mb3_general_ci',
    `size` BIGINT(20) UNSIGNED NULL DEFAULT NULL,
    `writeDate` DATETIME NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (`idx`) USING BTREE
)
COMMENT '이미지상세목록'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB;