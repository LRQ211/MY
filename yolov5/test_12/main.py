import asyncio
import io
import socket
import cv2
import numpy as np
import torch
import base64
from PIL import Image
from models.experimental import attempt_load
from ultralytics.utils.ops import scale_coords
from utils.general import non_max_suppression
from utils.torch_utils import select_device
from utils.plots import plot_one_box
import websockets
import paho.mqtt.client as mqtt
# 模型和设备配置
model_path = r"D:\exp5\weights\best.pt"  # 修改为你的模型路径
device = select_device('')  # CPU使用''，GPU使用'0', '1', etc.

# 加载YOLOv5模型
model = attempt_load(model_path)  # 直接加载模型，不指定map_location
model.to(device)  # 将模型转移到设定的设备
names = model.module.names if hasattr(model, 'module') else model.names  # 获取类别名称

ws_list = set()
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
class WebSocketConnection:
    def __init__(self, websocket, path):
        self.websocket = websocket
        self.path = path

async def handle_socket_client(reader, writer, connected_clients):
    while True:
        try:
            # 从客户端读取图像大小信息
            img_size_bytes = await reader.readexactly(4)
            img_size = int.from_bytes(img_size_bytes, 'big')
            # 从客户端读取完整的图像数据
            img_data = await reader.readexactly(img_size)
            nparr = np.frombuffer(img_data, np.uint8)
            img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
            # 对图像进行模型推理
            img_tensor = torch.from_numpy(img).to(device).float() / 255.0
            img_tensor = img_tensor.permute(2, 0, 1).unsqueeze(0)
            pred = model(img_tensor, augment=False, visualize=False)[0]

            # 应用非极大值抑制(NMS)获取最终检测结果
            pred = non_max_suppression(pred, 0.25, 0.45, classes=None, agnostic=False)

            # 对检测到的每个对象绘制边界框和标签
            for i, det in enumerate(pred):
                if len(det):
                    # 调整检测框的坐标
                    det[:, :4] = scale_coords(img_tensor.shape[2:], det[:, :4], img.shape).round()
                    for *xyxy, conf, cls in det:
                        label = f'{names[int(cls)]} {conf:.2f}'
                        plot_one_box(xyxy, img, label=label, color=(255, 0, 0), line_thickness=3)
            # send_message()
            # 将处理后的图像转换为JPEG并发送给所有WebSocket客户端
            pil_img = Image.fromarray(cv2.cvtColor(img, cv2.COLOR_BGR2RGB))
            jpeg_img = io.BytesIO()
            pil_img.save(jpeg_img, format='JPEG')
            jpeg_img_bytes = jpeg_img.getvalue()
            base64_img = base64.b64encode(jpeg_img_bytes).decode('utf-8')
            for connect in connected_clients:
                if not connect.websocket.closed:
                    print('Websocket sent')
                    await connect.websocket.send("frame" + base64_img)
        except Exception as e:
            print(f"发生错误: {e}")

async def start_socket_server(socket_port, connected_clients):
    async def handle_connection(reader, writer):
        await handle_socket_client(reader, writer, connected_clients)

    server = await asyncio.start_server(handle_connection, '0.0.0.0', socket_port)
    print(f"Socket服务正在运行, 监听端口 {socket_port}")
    async with server:
        await server.serve_forever()

async def start_websocket_server():
    async with websockets.serve(handle_websocket, "localhost", 8765):
        print("WebSocket server started and listening on ws://localhost:8765")
        await asyncio.Future()  # Keep running

async def handle_websocket(websocket, path):
    connection = WebSocketConnection(websocket, path)
    ws_list.add(connection)
    try:
        async for message in websocket:
            # 处理WebSocket消息
            print(message)
            pass  # 实现WebSocket消息处理逻辑
    except websockets.exceptions.ConnectionClosedError:
        pass
    finally:
        print(websocket)
        for conn in ws_list:
            if conn.websocket == websocket:
                ws_list.remove(conn)
                break

async def main():
    websocket_port = 8765
    socket_port = 8081
    websocket_server_task = start_websocket_server()
    socket_server_task = asyncio.create_task(start_socket_server(socket_port, ws_list))
    await asyncio.gather(websocket_server_task, socket_server_task)

if __name__ == "__main__":
    asyncio.run(main())
