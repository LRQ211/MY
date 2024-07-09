from machine import Pin, ADC
import dht
import network
from umqtt.simple import MQTTClient
import time


# Wi-Fi设置
wifi_ssid = 'ZTE_B34066'
wifi_password = '84564062'

# MQTT设置
mqtt_server = '118.24.230.64'
mqtt_port = 1883  # 默认MQTT端口
mqtt_user = 'your_mqtt_username'
mqtt_password = 'your_mqtt_password'
topic = 'esp32_js'

# 连接Wi-Fi
wlan = network.WLAN(network.STA_IF)
wlan.active(True)
wlan.connect(wifi_ssid, wifi_password)

# 等待连接到Wi-Fi
while not wlan.isconnected():
    pass

print('Connected to Wi-Fi')

# 初始化DHT传感器和ADC
dht_sensor = dht.DHT11(Pin(13))  # 更改引脚号以匹配你的设置
adc = ADC(Pin(3))  # ESP32 ADC引脚
adc.atten(ADC.ATTN_11DB)  # 设置衰减，适用于较高的电压范围

# 初始化超声波传感器引脚
trig_pin = Pin(4, Pin.OUT)  # Trig引脚
echo_pin = Pin(5, Pin.IN)   # Echo引脚

# 连接MQTT服务器
client = MQTTClient('esp32_js', mqtt_server, port=mqtt_port, user=mqtt_user, password=mqtt_password)
client.connect()

print('Connected to MQTT Broker')

def read_ultrasonic():
    trig_pin.value(0)
    time.sleep_us(2)
    trig_pin.value(1)
    time.sleep_us(10)
    trig_pin.value(0)
    
    while echo_pin.value() == 0:
        signal_off = time.ticks_us()
    while echo_pin.value() == 1:
        signal_on = time.ticks_us()
    
    time_passed = signal_on - signal_off
    distance = (time_passed * 0.0343) / 2
    return distance

while True:
    try:
        # 读取温湿度
        dht_sensor.measure()
        temp = dht_sensor.temperature()
        hum = dht_sensor.humidity()
        
        # 读取光照强度
        illumination = adc.read()
        
        # 读取距离
        distance = read_ultrasonic()
        
        # 创建消息
        msg = b'{ "temperature": %f, "humidity": %f, "illumination": %d, "distance": %f }' % (temp, hum, illumination, distance)
        
        # 发送消息
        client.publish(topic, msg)
        
        print('Message sent:', msg)
        
        # 等待5秒
        time.sleep(5)
        
    except OSError as e:
        print('Failed to read or send data:', e)
