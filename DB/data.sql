DELETE FROM LIKE_LIST;
DELETE FROM PRODUCT;
DELETE FROM USERS;

-- USERS
INSERT INTO USERS VALUES ('A123456789', '王小明', 'test1@test.com', '111111');
INSERT INTO USERS VALUES ('B223456789', '李小華', 'test2@test.com', '222222');

-- PRODUCT
INSERT INTO PRODUCT (product_name, price, fee_rate)
VALUES ('基金A', 1000, 0.01);

INSERT INTO PRODUCT (product_name, price, fee_rate)
VALUES ('基金B', 2000, 0.015);

-- LIKE_LIST
INSERT INTO LIKE_LIST (purchase_quantity, account, total_fee, total_amount)
VALUES (2, '111111', 20, 2000);

INSERT INTO LIKE_LIST (purchase_quantity, account, total_fee, total_amount)
VALUES (1, '222222', 30, 2000);