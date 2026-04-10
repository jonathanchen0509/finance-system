CREATE TABLE USERS (
                       user_id VARCHAR(20) PRIMARY KEY,
                       user_name VARCHAR(50),
                       email VARCHAR(100),
                       account VARCHAR(50)
);

CREATE TABLE PRODUCT (
                         no BIGINT AUTO_INCREMENT PRIMARY KEY,
                         product_name VARCHAR(100),
                         price DOUBLE,
                         fee_rate DOUBLE
);

CREATE TABLE LIKE_LIST (
                           sn BIGINT AUTO_INCREMENT PRIMARY KEY,
                           purchase_quantity INT,
                           account VARCHAR(50),
                           total_fee DOUBLE,
                           total_amount DOUBLE
);