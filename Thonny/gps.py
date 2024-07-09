import machine
import time
from umqtt.simple import MQTTClient
# MQTT设置
mqtt_server = '118.24.230.64'
mqtt_port = 1883  # 默认MQTT端口
mqtt_user = 'your_mqtt_username'
mqtt_password = 'your_mqtt_password'
topic = 'gps_data'


# 连接MQTT服务器
client = MQTTClient('gps_data', mqtt_server, port=mqtt_port, user=mqtt_user, password=mqtt_password)
client.connect()
print('Connected to MQTT Broker')

uart1 = machine.UART(1, baudrate=115200, tx=0, rx=1)  # 根据实际连接调整引脚

def read_gps_and_publish_mqtt():
    while True:
        if uart1.any():
            gps_data = uart1.readline()
            if gps_data:
                decoded_data = gps_data.decode('utf-8').strip()
                print(decoded_data)

                # Publish GPS data to MQTT topic
                client.publish(topic, decoded_data)
                print('Published to MQTT:', decoded_data)

try:
    print("Waiting for GPS data...")
    read_gps_and_publish_mqtt()

except KeyboardInterrupt:
    print("Stopped.")

