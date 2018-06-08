DROP SCHEMA IF EXISTS gameDB;
CREATE SCHEMA gameDB;

-- ----------------------------
-- Table structure for tbl_announce
-- ----------------------------    1.公告表
DROP TABLE IF EXISTS `gameDB`.`tbl_announce` ;
CREATE TABLE `gameDB`.`tbl_announce` (
  `announceID`  BIGINT NOT NULL primary key AUTO_INCREMENT, -- 公告ID
  `announceTitle`   NVARCHAR(300) NOT NULL,                 -- 公告标题
  `announceContent` LONGTEXT      NOT NULL,                 -- 公告内容
  `announceDate`    DATETIME                                -- 公告时间
);

-- ----------------------------
-- Records of "tbl_announce"
-- ----------------------------
INSERT INTO `gameDB`.`tbl_announce` VALUES (NULL,'娱乐类题库已上传', '娱乐类题库已上传，欢迎答题~', STR_TO_DATE('2018-04-10 11:07:34', '%Y-%m-%d %H:%i:%s'));
INSERT INTO `gameDB`.`tbl_announce` VALUES (NULL,'文学类题库已上传', '文学类题库已上传，欢迎答题~', STR_TO_DATE('2018-04-10 13:12:53', '%Y-%m-%d %H:%i:%s'));
INSERT INTO `gameDB`.`tbl_announce` VALUES (NULL,'天文地理类题库已上传', '天文地理类题库已上传，欢迎答题~', STR_TO_DATE('2018-04-10 13:13:31', '%Y-%m-%d %H:%i:%s'));
INSERT INTO `gameDB`.`tbl_announce` VALUES (NULL,'体育文化类题库已上传', '体育文化类题库已上传，欢迎答题~', STR_TO_DATE('2018-04-10 13:14:03', '%Y-%m-%d %H:%i:%s'));
INSERT INTO `gameDB`.`tbl_announce` VALUES (NULL,'生活常识类题库已上传', '生活常识类题库已上传，欢迎答题~', STR_TO_DATE('2018-04-10 13:13:31', '%Y-%m-%d %H:%i:%s'));


-- ----------------------------
-- Table structure for tbl_answer  2. 答案表
-- ----------------------------
DROP TABLE IF EXISTS `gameDB`.`tbl_answer`;
CREATE TABLE `gameDB`.`tbl_answer` (
  `answerID` BIGINT NOT NULL primary key AUTO_INCREMENT , -- 答案ID, 自增
  `testID` BIGINT NOT NULL ,                              -- 答案对应的测试ID
  `questionID` BIGINT NOT NULL ,                          -- 答案对应的问题ID
  `gamerAnswer` BIGINT NOT NULL ,                         -- 玩家答案
  `answerScore` BIGINT NOT NULL ,                         -- 答案所得分数
  `answerTime` DATETIME ,                                 -- 答案生成时间
  `isChecked` SMALLINT NOT NULL                           -- 是否核对
);

-- ----------------------------
-- Table structure for tbl_question  3. 问题表
-- ----------------------------
DROP TABLE IF EXISTS `gameDB`.`tbl_question`;
CREATE TABLE `gameDB`.`tbl_question` (
  `questionID` BIGINT NOT NULL primary key  AUTO_INCREMENT , -- 问题ID, 自增
  `questionContent` LONGTEXT NOT NULL ,                      -- 问题内容
  `answer1` NVARCHAR(300) ,                                  -- 选项1
  `answer2` NVARCHAR(300) ,                                  -- 选项2
  `answer3` NVARCHAR(300) ,                                  -- 选项3
  `answer4` NVARCHAR(300) ,                                  -- 选项4
  `trueAnswer` SMALLINT NOT NULL ,                           -- 正确答案
  `score` BIGINT NOT NULL,                                   -- 分数
  `QLibraryID` BIGINT NOT NULL                             -- 问题所属的题库ID
);

-- ----------------------------
-- Table structure for tbl_test  4. 测试表
-- ----------------------------
DROP TABLE IF EXISTS `gameDB`.`tbl_test`;
CREATE TABLE `gameDB`.`tbl_test` (
  `testID` BIGINT NOT NULL primary key AUTO_INCREMENT,      -- 测试ID
  `testTime` DATETIME ,                                     -- 测试创建时间
  `gamerID`  DOUBLE ,                                       -- 答题的玩家ID
  `QLibraryID` DOUBLE,                                      -- 测试所属题库
  `testScore` DOUBLE                                      -- 测试总分数
);


-- ----------------------------
-- Table structure for tbl_QLibrary  5. 题库表
-- ----------------------------
DROP TABLE IF EXISTS `gameDB`.`tbl_QLibrary`;
CREATE TABLE `gameDB`.`tbl_QLibrary` (
  `id` BIGINT NOT NULL primary key AUTO_INCREMENT,
  `type` VARCHAR(254) ,
  `name` VARCHAR(254) ,
  `startTime` DATETIME ,
  `endTime` DATETIME ,
  `adminID` DOUBLE ,
  `amountPerTest` DOUBLE
);
-- ----------------------------
-- Table structure for tbl_gamer   6. 玩家表
-- ----------------------------
DROP TABLE IF EXISTS `gameDB`.`tbl_gamer`;
CREATE TABLE `gameDB`.`tbl_gamer` (
  `gamerID` BIGINT NOT NULL primary key  AUTO_INCREMENT ,    -- 玩家ID, 自增
  `gamerEmail`    VARCHAR(254) ,                             -- 玩家邮箱
  `gamerPassword` VARCHAR(254) ,                             -- 玩家密码
  `gamerName` VARCHAR(254) ,                                 -- 玩家名称
  `gamerGender` DOUBLE                                       -- 玩家性别
);

-- ----------------------------
-- Records of "tbl_gamer"
-- ----------------------------
INSERT INTO `gameDB`.`tbl_gamer` VALUES (NULL, 'g0@OnlineQuiz.com', '123456', 'Gamer0', '0');
INSERT INTO `gameDB`.`tbl_gamer` VALUES (NULL, 'g1@OnlineQuiz.com', '123456', 'Gamer1', '0');
INSERT INTO `gameDB`.`tbl_gamer` VALUES (NULL, 'g2@OnlineQuiz.com', '123456', 'Gamer2', '0');
INSERT INTO `gameDB`.`tbl_gamer` VALUES (NULL, 'g3@OnlineQuiz.com', '123456', 'Gamer3', '0');
INSERT INTO `gameDB`.`tbl_gamer` VALUES (NULL, 'g4@OnlineQuiz.com', '123456', 'Gamer4', '0');


-- ----------------------------
-- Table structure for tbl_administrator  7. 管理员表
-- ----------------------------
DROP TABLE IF EXISTS `gameDB`.`tbl_administrator`;
CREATE TABLE `gameDB`.`tbl_administrator` (
  `adminID` BIGINT NOT NULL primary key AUTO_INCREMENT,     -- 管理员ID
  `adminEmail` VARCHAR(254) ,                               -- 管理员邮箱
  `adminPassword` VARCHAR(254) ,                            -- 管理员密码
  `adminName` VARCHAR(254) ,                                -- 管理员名称
  `adminGender` DOUBLE ,                                    -- 管理员性别
  `adminPhone` VARCHAR(254)                                 -- 管理员联系方式
)
;

-- ----------------------------
-- Records of "tbl_administrator"
-- ----------------------------
INSERT INTO `gameDB`.`tbl_administrator` VALUES (NULL, 'a0@OnlineQuiz.com', '123456', 'admin0', '0', '18200001111');
INSERT INTO `gameDB`.`tbl_administrator` VALUES (NULL, 'a1@OnlineQuiz.com', '123456', 'admin1', '0', '18200001111');
INSERT INTO `gameDB`.`tbl_administrator` VALUES (NULL, 'a2@OnlineQuiz.com', '123456', 'admin2', '0', '18240008888');
INSERT INTO `gameDB`.`tbl_administrator` VALUES (NULL, 'a3@OnlineQuiz.com', '123456', 'admin3', '0', '18212202222');




ALTER TABLE `gameDB`.`tbl_announce` ADD CONSTRAINT `SYS_C0011134` CHECK (`announceTitle` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_announce` ADD CONSTRAINT `SYS_C0011135` CHECK (`announceContent` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_announce` ADD CONSTRAINT `SYS_C0011137` CHECK (`announceID` IS NOT NULL) ;


-- ----------------------------
-- Checks structure for table tbl_answer
-- ----------------------------
ALTER TABLE `gameDB`.`tbl_answer` ADD CONSTRAINT `SYS_C0011144` CHECK (`answerID` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_answer` ADD CONSTRAINT `SYS_C0011145` CHECK (`testID` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_answer` ADD CONSTRAINT `SYS_C0011146` CHECK (`questionID` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_answer` ADD CONSTRAINT `SYS_C0011147` CHECK (`gamerAnswer` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_answer` ADD CONSTRAINT `SYS_C0011148` CHECK (`answerScore` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_answer` ADD CONSTRAINT `SYS_C0011149` CHECK (`isChecked` IS NOT NULL) ;

-- ----------------------------
-- Checks structure for table tbl_question
-- ----------------------------
ALTER TABLE `gameDB`.`tbl_question` ADD CONSTRAINT `SYS_C0011150` CHECK (`questionID` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_question` ADD CONSTRAINT `SYS_C0011151` CHECK (`questionContent` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_question` ADD CONSTRAINT `SYS_C0011152` CHECK (`trueAnswer` IS NOT NULL) ;
ALTER TABLE `gameDB`.`tbl_question` ADD CONSTRAINT `SYS_C0011153` CHECK (`score` IS NOT NULL) ;

select 'DATABASE INITIALIZATION COMPLETE!' AS '';