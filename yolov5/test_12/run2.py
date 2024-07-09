from datetime import datetime
from flask import Flask, render_template, Response
from mydb import db, Temp, Illumination, Humidity, Alarm, Sr04, Avg

import time
app = Flask(__name__)

temperature = 0.0
humidity=0.0
illumination=0.0
alarm=0.0
sr04=0.0
avg=0.0
# tempAVG=0.0
# humiAVG=0.0
# ilAVG=0.0
#前端数据库配置
app = Flask(__name__, template_folder='templates', static_folder='static')

app.secret_key = 'secret!'

app.debug = False

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:Root@localhost/test'

app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db.init_app(app)

    #数据库与前端数据的交互
@app.route('/')
def index():
    Temp1 = [tempe.to_dict() for tempe in Temp.query.all()]   #循环语句便利数据库中的所有数据
    Humidity1 = [humidity.to_dict() for humidity in Humidity.query.all()]
    Illumination1 = [illumination.to_dict() for illumination in Illumination.query.all()]
    Alarm1 = [alarm.to_dict() for alarm in Alarm.query.all()]
    Sr041 = [sr04.to_dict() for sr04 in Sr04.query.all()]
    Avg1 = [avg.to_dict() for avg in Avg.query.all()]

    # 建立数据库连接
    return render_template('index.html', Temptoweb=Temp1,Humiditytoweb=Humidity1,Illuminationtoweb=Illumination1,Alarmtoweb=Alarm1,Sr04toweb=Sr041,Avgtoweb=Avg1)

@app.route('/jiankong.html/')
def jiankong():
    return render_template('jiankong.html')

@app.route('/login.html/')
def data():
    return render_template('login.html')


#添加温度数据
def addTemperature():
    global temperature
    date = datetime.now().date()
    time = date.strftime("")
    temperature1 = temperature
    db.session.add(date=date, time=time, temperature=temperature1)
    db.session.commit()

#添加湿度数据
def addHumidity():
    global humidity
    date = datetime.now().date()
    time = date.strftime("")
    humidity1 = humidity
    db.session.add(date=date, time=time,  humidity = humidity1)
    db.session.commit()

#添加光照数据
def addIllumination():
    global illumination
    date = datetime.now().date()
    time = date.strftime("")
    illumination1 = illumination
    db.session.add(date=date, time=time,  illumination = illumination1)
    db.session.commit()
# 添加报警次数数据
def addAlarm(times):
    global alarm
    times=times
    alarm1=alarm
    db.session.add(times=times, alarm1=alarm)
    db.session.commit()

#添加超声波测距数据
def addSr04(distance):
    global sr04
    distance=distance
    sr041=sr04
    db.session.add(distance=distance, sr041=sr04)
    db.session.commit()

    # 添加平均值
def addAvg(tempAVG,humiAVG,ilAVG):
        global avg
        tempAVG = tempAVG
        humiAVG = humiAVG
        ilAVG = ilAVG
        avg1=avg
        db.session.add(tempAVG=tempAVG,humiAVG=humiAVG,ilAVG=ilAVG,avg1=avg)
        db.session.commit()

if __name__ == '__main__':
    app.run(debug=False)




