import network
import usocket as socket  # 使用usocket而不是socket，避免冲突
import sensor
import image
import time


# 设置WiFi连接信息
ssid = 'ZTE_B34066'     # 替换为你的WiFi SSID
key = '84564062'           # 替换为你的WiFi密码
#ssid = 'ZTE_B3408D'     # 替换为你的WiFi SSID
#key = '64651728'

# 连接到WiFi
wlan = network.WINC()
wlan.connect(ssid, key=key, security=wlan.WPA_PSK)

while not wlan.isconnected():
    pass  # 等待直到连接成功
print("WiFi连接成功！ IP信息:", wlan.ifconfig())

# 设置服务器的IP和端口
server_address = ('192.168.0.188', 8081)

# 创建TCP客户端socket，并连接到服务器
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect(server_address)
client_socket.settimeout(10)  # 设置socket超时时间

# 初始化传感器
sensor.reset()
sensor.set_framesize(sensor.VGA)  # 设置摄像头分辨率为VGA
sensor.set_pixformat(sensor.RGB565)  # 设置图像格式为RGB565

# 初始化计数器和时钟
img_count = 0
frame_count = 0
clock = time.clock()  # 创建一个时钟对象来跟踪FPS

# 主循环：捕获图像并发送到服务器
try:
    while True:
        clock.tick()  # 开始新的帧计时

        img = sensor.snapshot()  # 捕获图像
        img.compress(quality=90)  # 压缩图像
        img_bytes = img.bytearray()

        # 发送图像大小和图像数据
        img_size_bytes = len(img_bytes).to_bytes(4, 'big')
        client_socket.sendall(img_size_bytes)
        client_socket.sendall(img_bytes)

        # 尝试接收服务器的响应
#        try:
#            confirmation = client_socket.recv(1024)
#            print("服务器确认:", confirmation.decode())
#        except socket.timeout:
#            print("等待服务器确认超时，继续发送下一帧图像。")

        # 打印已发送的图像数量
        img_count += 1
        print("已发送图像数量:", img_count)

        # 每20帧更新FPS显示
        frame_count += 1
        if frame_count >= 20:
            print("Processed 20 frames. FPS: {:.2f}".format(clock.fps()))
            frame_count = 0  # 重置帧计数

finally:
    client_socket.close()  # 退出前确保关闭socket连接
    print("连接已关闭。")
