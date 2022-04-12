# companyInfo
INSERT INTO companyInfo (companyName, companyLeader, companyAddress, companyRegNum, companyMainPhone, companyEmail, companyFax) VALUES ('(주)율도국', '홍길동', '율도국', '12-345-67890', '02-1234-5678', 'gogildong@uldokuk.co.ul', '02-1234-5678');
# companyInfo - Certificate Image
INSERT INTO imageList (menuId, tabId, name, path) VALUES (1, 4, '', '');
# companyInfo - Organization Image
INSERT INTO imageList (menuId, tabId, name, path) VALUES (1, 4, '', '');
# companyInfo - Company History
INSERT INTO companyHistory (year, month, history, history_en) VALUES ('', '', '', '');

# default Menu - tab Info(menu - tab group)
INSERT INTO menuList (menuName, menuTitle, menuOrder) VALUES ('회사소개', '정직한 회사 율도국 입니다.', 1);
INSERT INTO tabList (menuId, tabName, tabOrder, templateType) VALUES (1, '회사개요', 1, 0);
INSERT INTO tabList (menuId, tabName, tabOrder, templateType) VALUES (2, '연혁', 2, 0);
INSERT INTO tabList (menuId, tabName, tabOrder, templateType) VALUES (3, '조직도', 3, 0);
INSERT INTO tabList (menuId, tabName, tabOrder, templateType) VALUES (4, '인증현황', 4, 0);
INSERT INTO tabList (menuId, tabName, tabOrder, templateType) VALUES (5, '오시는길', 5, 0);
