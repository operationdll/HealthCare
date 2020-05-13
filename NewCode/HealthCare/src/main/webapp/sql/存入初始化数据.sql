--
-- 转存表中的数据
--

INSERT INTO `healthcenters` ( `name`, `createDate`, `updateDate`) VALUES
('开江县普安镇卫生院', NOW(), NOW());

INSERT INTO `users` ( `userName`, `userPassword`, `createDate`, `updateDate`) VALUES
('admin', '0192023a7bbd73250516f069df18b500', NOW(), NOW());
INSERT INTO `users` ( `userName`, `userPassword`, `hcid`, `createDate`, `updateDate`) VALUES
('test', 'cc03e747a6afbbcbf8be7668acfebee5', 1, NOW(), NOW());

INSERT INTO `patients` ( `idimg`, `code`, `idcard`, `name`, `sex`, `birthdate`, `filedate`, `updateDate`, `createDate`, `fileunit`, `hcid`) VALUES
('http://47.95.230.56:8080/UploadFiles/upload/29bb58573fef41da92be42d13155c294.png','pppppp','5821828383888','张三',1,NOW(),NOW(),NOW(),NOW(),'开江县普安镇卫生院',1);

INSERT INTO `healthcheckup` ( `field1`, `updateDate`, `createDate`,`pid`,`field210`,`field246`) VALUES
(NOW(),NOW(),NOW(),1,'http://47.95.230.56:8080/UploadFiles/upload/29bb58573fef41da92be42d13155c294.png','http://47.95.230.56:8080/UploadFiles/upload/29bb58573fef41da92be42d13155c294.png');

INSERT INTO `TCM` (`updateDate`, `createDate`,`pid`) VALUES
(NOW(),NOW(),1); 

INSERT INTO `selfcare` (`updateDate`, `createDate`,`pid`) VALUES
(NOW(),NOW(),1); 
