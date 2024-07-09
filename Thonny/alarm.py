import network
from umqtt.simple import MQTTClient
import time
from machine import Pin


# Wi-Fi设置
wifi_ssid = 'ZTE_B34066'
wifi_password = '84564062'

# MQTT设置
mqtt_server = '118.24.230.64'
mqtt_port = 1883  # 默认MQTT端口
topic = 'esp32_js'

# 连接Wi-Fi
wlan = network.WLAN(network.STA_IF)
wlan.active(True)
wlan.connect(wifi_ssid, wifi_password)

# 等待连接到Wi-Fi
while not wlan.isconnected():
    pass

print('Connected to Wi-Fi')
def sub_cb(topic, msg):
    if msg.decode().startswith("cmd") and msg.decode() == 'cmd:beep':
        beep = Pin(10, Pin.OUT)  # 将引脚号更改为您选择的GPIO引脚编号
        beep.value(1)  # 蜂鸣器响
        time.sleep(3)
        beep.value(0)  # 蜂鸣器不响
        time.sleep(3)
        # 这里写蜂鸣器触发代码
        print("beep")

# 创建一个MQTT客户端对象
client = MQTTClient("clientId", mqtt_server, mqtt_port)

try:
    # 连接到MQTT服务器
    client.connect()
    print("Connected to MQTT server")

    # 订阅主题
    client.set_callback(sub_cb)
    client.subscribe(topic)
    print("Subscribed to topic:", topic)

    # 循环等待消息
    while True:
        client.wait_msg()
finally:
    # 断开连接
    client.disconnect()
    print("Disconnected from MQTT server")