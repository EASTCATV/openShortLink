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
    <div v-if="isVisible" class="bubble-tip" @transitionend="handleTransitionEnd">
      复制成功！
    </div>
  </div>
</template>

<script>
import axios from 'axios'; // 确保这行存在并在最上面
import { copyValue } from '@/utils/utils.js'
export default {
  data() {
    return {
      inputText: "",          // 输入框中的内容
      responseData: "",       // 后端返回的数据
      isVisible: false,
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
        //http://127.0.0.1:8080/short/getLinks?s_url=12312

        const res = await axios.get('http://short-api.y20y.com:8080/api/short/getLinks?s_url='+this.inputText, {
          headers: {
            'Content-Type': 'application/json',
          },
        });
        this.responseData  = res.data;
        console.log("==========res",res)
        this.copySuccess = false; // Reset copy success message

      } catch (error) {
        console.error("请求失败：", error);
        alert("请求失败，请稍后重试！"+this.inputText);
      }
    },
    // async  copyToClipboard(text) {
    //   if (!navigator.clipboard) {
    //     console.error('Clipboard API is not available');
    //     return;
    //   }
    //   try {
    //     await navigator.clipboard.writeText(text);
    //     console.log('Text copied to clipboard');
    //   } catch (err) {
    //     console.error('Failed to copy text', err);
    //   }
    // },
    async copyText  ()  {
      if (navigator && navigator.clipboard) {
        try {
          await navigator.clipboard.writeText(textToCopy.value);
          alert('Text copied to clipboard!');
        } catch (err) {
          console.error('Failed to copy text: ', err);
          alert('Failed to copy text to clipboard.');
        }
      } else {
        console.error('Clipboard API is not available.');
        alert('Clipboard API is not available.');
      }
    },
    // async copyToClipboard(text) {
    //   if (typeof navigator.clipboard === 'undefined') {
    //     console.error('Clipboard API is not available');
    //     return;
    //   }
    //   try {
    //     await navigator.clipboard.writeText(text);
    //     console.log('Text copied to clipboard');
    //   } catch (err) {
    //     console.error('Failed to copy text', err);
    //   }
    // },

    async copyToClipboard() {
      var text = this.responseData
      const res = await copyValue(text)
      if (res === true) {
        this.showBubbleTip();
      } else {
        alert("复制失败，请手动复制！"+err);
      }
       // navigator.clipboard
       //  .writeText(text)
       //  .then(() => {
       //     Promise.resolve()
       //    this.showBubbleTip();
       //  })
       //  .catch((err) => {
       //      console.error("复制失败：", error);
       //      alert("复制失败，请手动复制！"+err);
       //  })
    },
    showBubbleTip() {
      this.isVisible = true; // 显示气泡提示
      setTimeout(() => {
        this.isVisible = false; // 2秒后隐藏
      }, 2000);
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
.bubble-tip {
  position: fixed;
  top: 20px; /* 距离顶部20px */
  left: 50%; /* 水平居中 */
  transform: translateX(-50%); /* 使其水平居中 */
  background-color: rgba(0, 0, 0, 0.8); /* 黑色半透明背景 */
  color: white; /* 白色文字 */
  padding: 10px 15px; /* 内边距 */
  border-radius: 5px; /* 圆角 */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.5); /* 阴影效果 */
  opacity: 1; /* 初始透明度 */
  transition: opacity 0.5s ease; /* 渐变效果 */
  z-index: 1000; /* 确保显示在其他元素之上 */
}

</style>
