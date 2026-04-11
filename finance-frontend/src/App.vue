<template>
  <div>
    <h1>金融商品喜好系統</h1>

```
<h2>新增喜好商品</h2>
<input v-model="userId" placeholder="User ID" />
<input v-model="productId" placeholder="Product ID" />
<input v-model="quantity" placeholder="Quantity" />
<input v-model="account" placeholder="Account" />
<button @click="createLike">新增</button>

<h2>喜好清單</h2>
<button @click="getLikes">查詢</button>

<ul>
  <li v-for="like in likes" :key="like.sn">
    SN: {{ like.sn }} |
    產品名稱: {{ like.productName }} |
    數量: {{ like.purchaseQuantity }} |
    帳號: {{ like.account }} |
    Email :{{ like.email }}
    金額: {{ like.totalAmount }} |
    手續費: {{ like.totalFee }}
    <button @click="deleteLike(like.sn)">刪除</button>
  </li>
</ul>
```

  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      userId: "",
      productId: "",
      quantity: "",
      account: "",
      likes: []
    };
  },
  methods: {
    async createLike() {
      await axios.post("http://localhost:8080/likes", {
        userId: this.userId,
        productNo: this.productId,
        quantity: this.quantity  
      });

      alert("新增成功");
      this.getLikes();
    },
    
    async getLikes() {
      const res = await axios.get("http://localhost:8080/likes");
      this.likes = res.data;
    },

    async deleteLike(sn) {
      await axios.delete(`http://localhost:8080/likes/${sn}`);
      this.getLikes();
    },

    async updateLike(sn) {
      await axios.put(`http://localhost:8080/likes/${sn}`, {
        userId: this.userId,
        productNo: this.productId,
        quantity: this.quantity
      });

      alert("更新成功");
      this.getLikes();
    }
  }
};
</script>
