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
          商品：{{ p.productName }} <br>
          數量：{{ p.quantity }} <br>
          商品金額：{{ p.baseAmount }} <br>
          手續費：{{ p.totalFee }} <br>
          單筆扣款總額：{{ p.totalAmount }} <br>

          <button @click="deleteItem(p.sn)">刪除</button>
        </li>
      </ul>
    </div>

    <hr />

    <h2>新增商品</h2>

    <input v-model="newData.userId" placeholder="userId" />
    <input v-model="newData.productId" placeholder="productId" />
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
      newData: {
        userId: "",
        productId: "",
        quantity: "",
        account: ""
      }
    };
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
            productId: Number(this.newData.productId),
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
    }
  }
};
</script>