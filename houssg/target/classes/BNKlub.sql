-- 회원 테이블
CREATE TABLE `USER` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `password` varchar(100) NOT NULL,
  `email` char(50) NOT NULL,
  `nickname` varchar(8) NOT NULL,
  `department` varchar(10) NOT NULL,
  `point` int NOT NULL DEFAULT 0,
  `lastLoginDate` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
);

-- 게시판 테이블
CREATE TABLE `BOARD` (
  `boardId` int NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` longtext,
  `participantCnt` int NOT NULL DEFAULT 1 COMMENT '참여 인원 - Participant_Board 테이블에서 1(참가) 상태의 수',
  `recruitCnt` int NOT NULL COMMENT '모집할 인원',
  `onOffStatus` int NOT NULL DEFAULT 4 COMMENT '1 - Online / 2 - Offline / 3 - Both / 4 - TBD',
  `activeFlag` int NOT NULL DEFAULT 1 COMMENT '1 - 모집중 / 2 - 모집완료 / 3 - 진행중 / 4 - 종료',
  `chattingURL` varchar(60) NOT NULL COMMENT '참여를 위한 오픈 채팅 경로',
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL COMMENT '종료일',
  `userId` char(7) NOT NULL COMMENT 'User 테이블의 ID / 작성시 자동 기입',
  `createDate` datetime NOT NULL COMMENT '생성 날짜 (자동)',
  `dueDate` datetime NOT NULL COMMENT '인원 모집 마감일',
  `viewCnt` int NOT NULL DEFAULT 0 COMMENT '조회수',
  `location` varchar(60) DEFAULT NULL COMMENT '지도 - 위치정보',
  PRIMARY KEY (`boardId`),
  KEY `FK_user_TO_board` (`userId`),
  CONSTRAINT `FK_user_TO_board` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 언어 종류 테이블
CREATE TABLE `LANGUAGECATEGORY` (
  `languageId` int NOT NULL AUTO_INCREMENT,
  `languageName` varchar(20) NOT NULL COMMENT '언어이름',
  PRIMARY KEY (`languageId`)
);

-- 분야 종류 테이블
CREATE TABLE `POSITIONCATEGORY` (
  `positionId` int NOT NULL AUTO_INCREMENT,
  `positionName` varchar(10) NOT NULL COMMENT '포지션에 대한 명칭(ex. 백엔드, 프론트엔드 등)',
  PRIMARY KEY (`positionId`)
);

-- 참여 테이블
CREATE TABLE `PARTICIPANTBOARD` (
  `boardId` int NOT NULL,
  `userId` char(7) NOT NULL COMMENT 'ID',
  `captainFlag` tinyint NOT NULL COMMENT '1 - 모임장 / 2 - 팀원',
  `joinFlag` int NOT NULL DEFAULT 2 COMMENT '1 - 참가 / 2 - 승인대기 / 3 - 거절',
  KEY `FK_board_TO_participantBorad_1` (`boardId`),
  KEY `FK_user_TO_participantBorad_1` (`userId`),
  CONSTRAINT `FK_board_TO_participantBorad_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_user_TO_participantBorad_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 필요 분야 테이블
CREATE TABLE `NEEDPOSITION` (
  `boardId` int DEFAULT NULL,
  `positionId` int NOT NULL,
  KEY `FK_board_TO_needPosition_1` (`boardId`),
  KEY `FK_positionCategory_TO_needPosition_1` (`positionId`),
  CONSTRAINT `FK_board_TO_needPosition_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_positionCategory_TO_needPosition_1` FOREIGN KEY (`positionId`) REFERENCES `positioncategory` (`positionId`)
);

-- 관심 목록 테이블
CREATE TABLE `INTERESTEDPAGE` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `boardId` int NOT NULL,
  KEY `FK_user_TO_interestedPage_1` (`userId`),
  KEY `FK_board_TO_interestedPage_1` (`boardId`),
  CONSTRAINT `FK_board_TO_interestedPage_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_user_TO_interestedPage_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 관심 언어 테이블
CREATE TABLE `INTERESTEDLABUAGES` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `languageId` int NOT NULL COMMENT 'Language 테이블의 ID',
  KEY `FK_user_TO_interestedLaguages_1` (`userId`),
  KEY `FK_languageCategory_TO_interestedLaguages_1` (`languageId`),
  CONSTRAINT `FK_languageCategory_TO_interestedLaguages_1` FOREIGN KEY (`languageId`) REFERENCES `languagecategory` (`languageId`),
  CONSTRAINT `FK_user_TO_interestedLaguages_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 댓글 테이블
CREATE TABLE `COMMENT` (
  `boardId` int NOT NULL,
  `userId` char(7) NOT NULL,
  `cmtId` int NOT NULL AUTO_INCREMENT,
  `cmt` longtext NOT NULL,
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`cmtId`),
  KEY `FK_board_TO_comment_1` (`boardId`),
  KEY `FK_user_TO_comment_1` (`userId`),
  CONSTRAINT `FK_board_TO_comment_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_user_TO_comment_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 이미지 URL 테이블
CREATE TABLE `IMAGEURL` (
  `boardId` int NOT NULL,
  `url` varchar(200) NOT NULL COMMENT '이미지 경로',
  KEY `FK_board_TO_imageUrl_1` (`boardId`),
  CONSTRAINT `FK_board_TO_imageUrl_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`)
);

-- 작성 목록 테이블
CREATE TABLE `WROTEPAGE` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `boardId` int NOT NULL,
  KEY `FK_board_TO_wrotePage_1` (`boardId`),
  KEY `FK_user_TO_wrotePage_1` (`userId`),
  CONSTRAINT `FK_board_TO_wrotePage_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_user_TO_wrotePage_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 기술 스택 테이블
CREATE TABLE `TECHSTACK` (
  `boardId` int NOT NULL,
  `languageId` int NOT NULL COMMENT 'Language 테이블의 ID',
  KEY `FK_board_TO_techStack_1` (`boardId`),
  KEY `FK_languageCategory_TO_techStack_1` (`languageId`),
  CONSTRAINT `FK_board_TO_techStack_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_languageCategory_TO_techStack_1` FOREIGN KEY (`languageId`) REFERENCES `languagecategory` (`languageId`)
);

-- 활성 그룹 테이블
CREATE TABLE `ACTIVEGROUP` (
  `userId` char(7) NOT NULL COMMENT 'ID',
  `boardId` int NOT NULL,
  KEY `FK_board_TO_activeGroup_1` (`boardId`),
  KEY `FK_user_TO_activeGroup_1` (`userId`),
  CONSTRAINT `FK_board_TO_activeGroup_1` FOREIGN KEY (`boardId`) REFERENCES `board` (`boardId`),
  CONSTRAINT `FK_user_TO_activeGroup_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- 후기 테이블
CREATE TABLE `REVIEW` (
  `reviewId` int NOT NULL AUTO_INCREMENT,
  `reviewContent` longtext NOT NULL,
  `boardId` int NOT NULL,
  `userId` char(7) NOT NULL,
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`reviewId`)
);