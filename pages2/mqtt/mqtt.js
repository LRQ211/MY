Page({
  data: {
    movies: [],
    movieId: '',
    groupedData: [] // 新增一个用于存储分组后数据的数组
  },
  onLoad() {
    wx.request({
      url: 'http://localhost:8081/selectDetail', // 替换为实际的后端接口地址
      method: 'GET',
      success: (res) => {
        console.log('Fetched movies:', res.data); // 调试用，输出获取的数据
        this.setData({ movies: res.data }); // 将从后端获取的电影数据存储到data中
        this.groupData(); // 调用分组函数
      },
      fail: (error) => {
        console.error(error);
      }
    });
  },
  inputChange(e) {
    console.log('Input change:', e.detail.value); // 调试用，输出输入的值
    this.setData({
      movieId: e.detail.value
    });
  },
  searchMqtt() {
    const { movieId, movies } = this.data;
    console.log('Searching for movieId:', movieId); // 调试用，输出要查找的movieId
    const movie = movies.find(item => item.movieId.toString() === movieId); // 确保类型一致
    if (movie) {
      wx.navigateTo({
        url: '/pages/huangjing/huangjing?movieId=' + movieId
      });
    } else {
      wx.showToast({
        title: '未找到对应ID的数据',
        icon: 'none',
        duration: 2000
      });
    }
  },
  groupData() {
    const groupedData = this.data.movies.reduce((acc, curr) => {
      const found = acc.find(item => item.id === curr.movieId);
      if (found) {
        found.data.push(curr);
      } else {
        acc.push({ id: curr.movieId, data: [curr] });
      }
      return acc;
    }, []);
    console.log('Grouped data:', groupedData); // 调试用，输出分组后的数据
    this.setData({
      groupedData: groupedData,
    });
  }
});
