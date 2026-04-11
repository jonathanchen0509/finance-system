CREATE TABLE users (
                       user_id VARCHAR(20) PRIMARY KEY,
                       user_name VARCHAR(50),
                       email VARCHAR(100),
                       account VARCHAR(20)
);

CREATE TABLE product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         product_name VARCHAR(100),
                         price DECIMAL(10,2),
                         fee_rate DECIMAL(5,4)
);

CREATE TABLE like_list (
                           sn INT AUTO_INCREMENT PRIMARY KEY,
                           user_id VARCHAR(20),
                           product_id INT,
                           purchase_quantity INT,
                           account VARCHAR(20),
                           total_fee DECIMAL(10,2),
                           total_amount DECIMAL(10,2)
);