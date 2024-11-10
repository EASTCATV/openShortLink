

export function copyValue(value) {
  return new  Promise ((resolve, reject)=>{
  // 创建输入框元素
  const oInput = document.createElement('input')
  // 将想要复制的值
  oInput.value = value
  // 页面底部追加输入框
  document.body.appendChild(oInput)
  // 选中输入框
  oInput.select()
  // 执行浏览器复制命令
  document.execCommand('Copy')
  // 复制后移除输入框
  oInput.remove()
  resolve(true);
  })


}


// module.exports = {
//   copyValue
// }
