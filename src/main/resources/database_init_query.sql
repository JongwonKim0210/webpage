# companyInfo
INSERT INTO companyInfo (companyName, companyLeader, companyAddress, companyRegNum, companyMainPhone, companyEmail, companyFax) VALUES ('(주)율도국', '홍길동', '율도국', '12-345-67890', '02-1234-5678', 'gogildong@uldokuk.co.ul', '02-1234-5678');
# companyInfo - Certificate Image
# INSERT INTO imageList (menuId, tabId, name, path) VALUES (1, 4, '', '');
# companyInfo - Organization Image
# INSERT INTO imageList (menuId, tabId, name, path) VALUES (1, 4, '', '');
# companyInfo - Company History
# INSERT INTO companyHistory (year, month, history, history_en) VALUES ('', '', '', '');

# default Menu - tab Info(menu - tab group)
INSERT INTO menuList (idx, menuName, menuTitle, menuOrder) VALUES
                    (1, '회사소개', '정직한 회사 율도국 입니다.', 8),
                    (8, 'testMenu', 'this is test image board', 1),
                    (9, 'testboard', '', 99);
INSERT INTO tabList (idx, menuId, tabName, tabOrder, templateType) VALUES
                    (1, 1, '회사개요', 1, 0),
                    (2, 1, '연혁', 2, 0),
                    (3, 1, '조직도', 3, 0),
                    (4, 1, '인증현황', 4, 0),
                    (5, 1, '오시는길', 5, 0),
                    (6, 8, 'testtab1', 1, 1),
                    (7, 8, 'testtab2', 2, 1),
                    (8, 8, 'testtab3', 3, 1),
                    (9, 9, 'testNotice', 99, 2);

INSERT INTO userInfo (id, password, auth) VALUES
                    ('admin@admin.com', '2334808E6BF1BABAA064AC8FB1D769835E8CE2378FA10A6C06C50939E559C154407BAAF48C3E560C667E756125163F09E9834BDBC12E08CFC88C6098BD814E68', 'A'),
                    ('tester', '2334808E6BF1BABAA064AC8FB1D769835E8CE2378FA10A6C06C50939E559C154407BAAF48C3E560C667E756125163F09E9834BDBC12E08CFC88C6098BD814E68', 'A');