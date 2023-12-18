-- 회원 테이블
CREATE TABLE `user` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `password` varchar(100) NOT NULL,
  `email` char(50) NOT NULL,
  `nickname` varchar(8) NOT NULL,
  `department` varchar(10) NOT NULL,
  `point` int NOT NULL,
  `lastLoginDate` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
);

-- 게시판 테이블
CREATE TABLE `board` (
  `boardId` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` longtext,
  `participantCnt` int NOT NULL DEFAULT '0' COMMENT '참여 인원 - Participant_Board 테이블에서 1(참가) 상태의 수',
  `recruitCnt` int DEFAULT NULL COMMENT '모집할 인원',
  `onOffFlag` int NOT NULL COMMENT '1 - Online / 2 - Offline / 3 - Both / 4 - TBD',
  `activeFlag` int NOT NULL COMMENT '1 - 모집중 / 2 - 모집완료 / 3 - 진행중 / 4 - 종료',
  `chattingURL` varchar(60) NOT NULL COMMENT '참여를 위한 오픈 채팅 경로',
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL COMMENT '종료일',
  `userId` char(7) NOT NULL COMMENT 'User 테이블의 ID / 작성시 자동 기입',
  `createDate` datetime NOT NULL COMMENT '생성 날짜 (자동)',
  `dueDate` datetime NOT NULL COMMENT '인원 모집 마감일',
  `viewCnt` int NOT NULL DEFAULT '0' COMMENT '조회수',
  `location` varchar(60) DEFAULT NULL COMMENT '지도 - 위치정보',
  PRIMARY KEY (`boardId`)
);

-- 언어 종류 테이블
CREATE TABLE `languagecategory` (
  `languageId` int NOT NULL,
  `languageName` varchar(10) NOT NULL COMMENT '언어이름',
  PRIMARY KEY (`languageId`)
);

-- 분야 종류 테이블
CREATE TABLE `positioncategory` (
  `positionId` int NOT NULL,
  `positionName` varchar(10) NOT NULL COMMENT '포지션에 대한 명칭(ex. 백엔드, 프론트엔드 등)',
  PRIMARY KEY (`positionId`)
);

-- 참여 테이블
CREATE TABLE `participantborad` (
  `boardId` int NOT NULL,
  `userId` char(7) NOT NULL COMMENT 'ID',
  `captainFlag` tinyint NOT NULL,
  `joinFlag` int NOT NULL COMMENT '1 - 참가 / 2 - 승인대기 / 3 - 거절',
  KEY `FK_board_TO_participantBorad_1` (`boardId`),
  KEY `FK_user_TO_participantBorad_1` (`userId`),
  CONSTRAINT `FK_board_TO_participantBorad_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_user_TO_participantBorad_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 필요 분야 테이블
CREATE TABLE `needposition` (
  `boardId` int DEFAULT NULL,
  `positionId` int NOT NULL,
  KEY `FK_board_TO_needPosition_1` (`boardId`),
  KEY `FK_positionCategory_TO_needPosition_1` (`positionId`),
  CONSTRAINT `FK_board_TO_needPosition_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_positionCategory_TO_needPosition_1` FOREIGN KEY (`positionId`) REFERENCES `positioncategory` (`positionId`)
);

-- 관심 목록 테이블
CREATE TABLE `interestedpage` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `boardId` int NOT NULL,
  KEY `FK_user_TO_interestedPage_1` (`userId`),
  KEY `FK_board_TO_interestedPage_1` (`boardId`),
  CONSTRAINT `FK_board_TO_interestedPage_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_user_TO_interestedPage_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 관심 언어 테이블
CREATE TABLE `interestedlaguages` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `languageId` int NOT NULL COMMENT 'Language 테이블의 ID',
  KEY `FK_user_TO_interestedLaguages_1` (`userId`),
  KEY `FK_languageCategory_TO_interestedLaguages_1` (`languageId`),
  CONSTRAINT `FK_languageCategory_TO_interestedLaguages_1` FOREIGN KEY (`languageId`) REFERENCES `languagecategory` (`languageId`),
  CONSTRAINT `FK_user_TO_interestedLaguages_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 댓글 테이블
CREATE TABLE `comment` (
  `boardId` int NOT NULL,
  `cmtId` int NOT NULL,
  `cmt` longtext NOT NULL,
  KEY `FK_board_TO_comment_1` (`boardId`),
  CONSTRAINT `FK_board_TO_comment_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`)
);

-- 이미지 URL 테이블
CREATE TABLE `imageurl` (
  `boardId` int DEFAULT NULL,
  `url` varchar(100) NOT NULL COMMENT '이미지 경로',
  KEY `FK_board_TO_imageUrl_1` (`boardId`),
  CONSTRAINT `FK_board_TO_imageUrl_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`)
);

-- 작성 목록 테이블
CREATE TABLE `wrotepage` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `boardId` int NOT NULL,
  KEY `FK_board_TO_wrotePage_1` (`boardId`),
  KEY `FK_user_TO_wrotePage_1` (`userId`),
  CONSTRAINT `FK_board_TO_wrotePage_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_user_TO_wrotePage_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 기술 스택 테이블
CREATE TABLE `techstack` (
  `boardId` int NOT NULL,
  `languageId` int NOT NULL COMMENT 'Language 테이블의 ID',
  KEY `FK_board_TO_techStack_1` (`boardId`),
  KEY `FK_languageCategory_TO_techStack_1` (`languageId`),
  CONSTRAINT `FK_board_TO_techStack_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_languageCategory_TO_techStack_1` FOREIGN KEY (`languageId`) REFERENCES `languagecategory` (`languageId`)
);

-- 활성 그룹 테이블
CREATE TABLE `activegroup` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `boardId` int NOT NULL,
  KEY `FK_board_TO_activeGroup_1` (`boardId`),
  KEY `FK_user_TO_activeGroup_1` (`userId`),
  CONSTRAINT `FK_board_TO_activeGroup_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_user_TO_activeGroup_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);