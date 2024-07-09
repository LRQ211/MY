import time
import paho.mqtt.client as mqtt

# 设置 MQTT 代理的连接信息
MQTT_BROKER = "118.24.230.64"  # MQTT 代理地址
MQTT_PORT = 1883  # MQTT 代理端口
TOPIC_PUBLISH = "esp32_js"  # 发布的主题

# 创建 MQTT 客户端
client = mqtt.Client()
# 连接到 MQTT 代理
client.connect(MQTT_BROKER, MQTT_PORT)
# 定义发送消息函数
def send_message():
    message = "cmd:beep"
    client.publish(TOPIC_PUBLISH, message)
    print("Sent message: ", message)

# 每3秒发送一次消息

send_message()
