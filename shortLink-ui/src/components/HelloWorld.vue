<template>
  <div class="post-input">
    <h2>输入并提交数据</h2>
    <div class="form-group">
      <input
        type="text"
        v-model="inputText"
        placeholder="请输入内容"
        @keydown.enter="handleSubmit"
      />
      <button @click="handleSubmit">提交</button>
    </div>

    <div v-if="responseData" class="response-data">
      <p>返回数据：</p>
      <textarea v-model="responseData" readonly></textarea>
      <button @click="copyToClipboard">复制结果</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      inputText: "",          // 输入框中的内容
      responseData: "",       // 后端返回的数据
    };
  },
  methods: {
    async handleSubmit() {
      // 清空之前的返回数据
      this.responseData = "";

      if (!this.inputText.trim()) {
        alert("请输入内容！");
        return;
      }
      
      try {
        // 发送 POST 请求
        const response = await fetch("https://me.me:8080/shortchain", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ data: this.inputText }),
        });
        const result = await response.json();

        // 将后端返回的数据存入 responseData 中
        this.responseData = result.data;
      } catch (error) {
        console.error("请求失败：", error);
        alert("请求失败，请稍后重试！");
      }
    },
    async copyToClipboard() {
      try {
        await navigator.clipboard.writeText(this.responseData);
        alert("复制成功！");
      } catch (error) {
        console.error("复制失败：", error);
        alert("复制失败，请手动复制！");
      }
    },
  },
};
</script>

<style scoped>
.post-input {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.form-group {
  display: flex;
  gap: 10px;
}

input[type="text"] {
  flex: 1;
  padding: 8px;
  font-size: 1em;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 8px 12px;
  font-size: 1em;
  color: white;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.response-data {
  margin-top: 16px;
}

textarea {
  width: 100%;
  height: 80px;
  padding: 8px;
  font-size: 1em;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: none;
}
</style>
