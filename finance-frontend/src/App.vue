<template>
  <div>
    <h2>喜好商品查詢</h2>

    <input v-model="userId" placeholder="輸入 userId" />
    <button @click="getLikes">查詢</button>

    <div v-if="result">
      <h3>{{ result.userName }}</h3>
      <p>Email: {{ result.email }}</p>
      <p>總商品金額：{{ result.totalBaseAmount }}</p>
      <p>總手續費：{{ result.totalFee }}</p>
      <p>總扣款金額: {{ result.totalAmount }}</p>

      <ul>
        <li v-for="p in result.products" :key="p.productName">


          商品：
          <select v-model="p.productNo">
            <option v-for="prod in products" :key="prod.no" :value="prod.no">
              {{ prod.productName }}
            </option>
          </select>

          <br>

          數量：
          <input v-model="p.quantity" style="width:60px" />

          <br>

          扣款帳戶：
          <input v-model="p.account" />

          <br>
          商品金額：{{ p.baseAmount }} <br>
          手續費：{{ p.totalFee }} <br>
          單筆扣款總額：{{ p.totalAmount }} <br>


          <button @click="updateItem(p)">更新</button>
          <button @click="deleteItem(p.sn)">刪除</button>
        </li>
      </ul>
    </div>

    <hr />

    <h2>新增商品</h2>

    <input v-model="newData.userId" placeholder="userId" />
    <select v-model="newData.productNo">
      <option disabled value="">請選擇商品</option>
      <option v-for="p in products" :key="p.no" :value="p.no">
        {{ p.productName }} (價格: {{ p.price }})
      </option>
    </select>
    <input v-model="newData.quantity" placeholder="quantity" />
    <input v-model="newData.account" placeholder="account" />

    <button @click="addLike">新增</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userId: "",
      result: null,
      products: [],
      newData: {
        userId: "",
        productNo: "",
        quantity: "",
        account: ""
      }
    };
  },
  mounted() {
    console.log("mounted 有跑");
    fetch("http://localhost:8080/products")
        .then(res => res.json())
        .then(data => {
          console.log("products:", data);
          this.products = data;
        });
  },
  methods: {
    async getLikes() {
      const id = this.userId.trim();

      if (!id) {
        alert("請輸入 userId");
        return;
      }

      try {
        const res = await fetch(`http://localhost:8080/likes/${id}`);

        if (!res.ok) {
          alert("查詢失敗：" + res.status);
          return;
        }

        this.result = await res.json();
      } catch (e) {
        alert("連線失敗");
      }
    },
    async addLike() {
      try {
        console.log("送出資料:", this.newData);

        const res = await fetch("http://localhost:8080/likes", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            userId: this.newData.userId.trim(),
            productNo: Number(this.newData.productNo),
            quantity: Number(this.newData.quantity),
            account: this.newData.account.trim()
          })
        });

        if (!res.ok) {
          alert("新增失敗：" + res.status);
          return;
        }

        alert("新增成功");

      } catch (e) {
        console.error(e);
        alert("連線錯誤");
      }
    },
    async deleteItem(sn) {
      await fetch(`http://localhost:8080/likes/${sn}`, {
        method: "DELETE"
      });
      alert("刪除成功");
    },
    async updateItem(p) {
      try {
        const res = await fetch(`http://localhost:8080/likes/${p.sn}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            userId: this.userId,
            productNo: p.productNo,
            quantity: Number(p.quantity),
            account: p.account
          })
        });

        if (!res.ok) {
          alert("更新失敗");
          return;
        }

        alert("更新成功");

        await this.getLikes();

      } catch (e) {
        alert("更新錯誤");
      }
    }
  }
};
</script>