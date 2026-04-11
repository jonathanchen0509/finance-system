# 📊 Finance System（投資商品管理系統）

## 📌 專案介紹

本專案為一個簡易的投資商品管理系統，提供使用者查詢、新增、修改與刪除喜好商品，並自動計算商品金額、手續費與扣款總額。

系統採用 **前後端分離架構**：

* 前端：Vue.js
* 後端：Spring Boot
* 資料庫：MySQL

---

## 🚀 功能說明

### 👤 使用者功能

* 查詢使用者喜好商品清單
* 顯示：

    * 商品名稱
    * 數量
    * 商品金額（baseAmount）
    * 手續費（totalFee）
    * 扣款總額（totalAmount）
    * 扣款帳戶（account）

### ➕ 新增功能

* 新增喜好商品
* 自動計算金額（透過 Stored Procedure）

### ✏️ 修改功能

* 修改商品
* 修改數量
* 修改扣款帳戶

### ❌ 刪除功能

* 刪除指定商品

---

## 🧱 系統架構

```
Vue (Frontend)
   ↓ REST API
Spring Boot (Backend)
   ↓ JDBC / JPA
MySQL (Database)
```

---

## 🗄️ 資料庫設計

### users

| 欄位        | 型別      |
| --------- | ------- |
| user_id   | VARCHAR |
| user_name | VARCHAR |
| email     | VARCHAR |

### product

| 欄位           | 型別      |
| ------------ | ------- |
| no           | INT     |
| product_name | VARCHAR |
| price        | DECIMAL |
| fee_rate     | DECIMAL |

### like_list

| 欄位                | 型別      |
| ----------------- | ------- |
| sn                | INT     |
| user_id           | VARCHAR |
| product_id        | INT     |
| purchase_quantity | INT     |
| account           | VARCHAR |
| base_amount       | DECIMAL |
| total_fee         | DECIMAL |
| total_amount      | DECIMAL |

---

## ⚙️ 環境需求

* Java 17
* MySQL 8+
* Node.js (Vue)
* Maven

---

## 🛠️ 安裝與執行

### 1️⃣ 建立資料庫

進入DB資料夾

```\finance-system\src\main\resources\DB```

請依序執行：
```text
schema.sql
procedure.sql
data.sql
```
---

### 2️⃣ 啟動後端

進入專案資料夾

```\finance-system\```

執行
```bash
set DB_PASSWORD="你的密碼"
mvn spring-boot:run
```

預設 port：

```
http://localhost:8080
```



---

### 3️⃣ 啟動前端

```bash
cd  /finance-fronted/
npm install
npm run dev
```





---

## 🔌 功能測試


### 🔍 查詢

### ✏️ 更新

### ❌ 刪除

### ➕ 新增



---

## 💡 設計說明

* 使用 Stored Procedure 計算金額，確保資料一致性
* 採用 DTO 分離資料結構
* 前後端分離設計，提高可維護性
* 使用 RESTful API

---

## 📣 作者

* 開發者：陳鈞翔
* Mail：jonathanchen0509@gmail.com



