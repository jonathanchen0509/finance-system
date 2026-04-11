DELIMITER $$

CREATE PROCEDURE add_like (
    IN p_user_id VARCHAR(20),
    IN p_product_id INT,
    IN p_quantity INT,
    IN p_account VARCHAR(50)
)
BEGIN

    DECLARE v_price DECIMAL(10,2);
    DECLARE v_fee_rate DECIMAL(5,4);
    DECLARE v_base_amount DECIMAL(15,2);
    DECLARE v_total_fee DECIMAL(15,2);
    DECLARE v_total_amount DECIMAL(15,2);

    -- 取商品資料
SELECT price, fee_rate
INTO v_price, v_fee_rate
FROM product
WHERE no = p_product_id;

-- 計算
    SET v_base_amount = v_price * p_quantity;
    SET v_total_fee = v_base_amount * v_fee_rate;
    SET v_total_amount = v_base_amount + v_total_fee;

    -- insert
INSERT INTO like_list (
    user_id,
    product_id,
    purchase_quantity,
    account,
    base_amount,
    total_fee,
    total_amount
)
VALUES (
           p_user_id,
           p_product_id,
           p_quantity,
           p_account,
           v_base_amount,
           v_total_fee,
           v_total_amount
       );

END $$

DELIMITER ;