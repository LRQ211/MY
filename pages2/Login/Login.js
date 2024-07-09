const app = getApp();
const { request } = require('../../utils/request');

Page({
  data: {
    username: '',
    password: '',
  },
 // 输入框内容变化处理函数
  handleInputChange: function(e) {
    const { value } = e.detail;
    const id = e.currentTarget.id;
    this.setData({
      [id]: value
    });
    console.log(`Input change detected: ${id} = ${value}`);
  },
 // 登录按钮点击处理函数
  handleLogin: function() {
    const { username, password } = this.data;
    console.log(`Attempting to log in with username: ${username}`);
    
    const url = '/login';
    request(url, 'POST', { username, password })
      .then(res => {
        console.log('Login successful:', res);
        if (res.statusCode === 200) {
          const userInfo = {
            userName: res.data.username,
            userAvatar: res.data.pictureurl,
          };
          wx.setStorageSync('userInfo', userInfo);
          wx.showToast({
            title: '登录成功',
            icon: 'success'
          });

          // 使用 wx.switchTab 进行 tab 页跳转
          wx.switchTab({
            url: '/pages/wode/wode', // 登录成功后跳转到个人中心页面
          });
        } else {
          wx.showToast({
            title: '登录失败: ' + res.data,
            icon: 'none'
          });
        }
      })
      .catch(err => {
        console.error('Login failed:', err);
        wx.showToast({
          title: '登录失败',
          icon: 'none'
        });
      });
  },
});
