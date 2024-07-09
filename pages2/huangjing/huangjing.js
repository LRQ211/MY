// huangjing.js

Page({
  data: {
    mqttData: [],
    weatherLocation: '重庆巴南区', // 固定位置
    weatherTemp: '', // 温度
    weatherIcon: '',  // 天气图标
    weatherCondition: '', // 天气状况
    weatherRange: '', // 温度范围 (这部分将只显示当前温度)
    currentTime: '', // 当前时间
    currentDate: ''  // 当前日期
  },

  onLoad(options) {
    // 保持原有的加载逻辑
    this.getWeather();
    this.updateTime();

    // 添加从huanjing.js合并的onLoad逻辑
    const movieId = options.movieId;
    wx.request({
      url: 'http://localhost:8081/selectMqtt', // 替换为实际的后端接口地址
      data: {
        movieid: movieId
      },
      method: 'GET',
      success: (res) => {
        const latestData = res.data[0]; // 获取最新的一条数据
        this.setData({
          mqttData: [latestData] // 只显示最新的一条数据
        });
      },
      fail: (error) => {
        console.log(error);
      }
    });
  },

  // 获取天气信息的方法
  getWeather() {
    const that = this;
    const apiKey = '803a70135ee648abb15132647241406'; // 替换为你的API密钥
    const location = 'Chongqing Banan'; // 替换为你需要获取天气的地点
    wx.request({
      url: `https://api.weatherapi.com/v1/current.json?key=${apiKey}&q=${location}`,
      method: 'GET',
      success(res) {
        if (res.statusCode === 200) {
          const weatherIconUrl = res.data.current.condition.icon;
          that.setData({
            weatherTemp: res.data.current.temp_c,
            weatherIcon: `https:${weatherIconUrl}`, // 天气图标的完整URL
            weatherCondition: res.data.current.condition.text,
            weatherRange: `${res.data.current.temp_c}°` // 当前温度
          });
        } else {
          console.error('Failed to load weather data: ', res);
        }
      },
      fail(err) {
        console.error('Failed to load weather data: ', err);
      }
    });
  },

  // 更新当前时间和日期的方法
  updateTime() {
    const now = new Date();
    const time = this.formatTime(now);
    const date = this.formatDate(now);
    this.setData({
      currentTime: time,
      currentDate: date
    });
  },

  // 格式化时间
  formatTime(date) {
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    return `${hours}:${minutes}`;
  },

  // 格式化日期
  formatDate(date) {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}.${month}.${day}`;
  }
});
