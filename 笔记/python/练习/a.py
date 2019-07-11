#coding:'utf-8'
import requests
import json
import xlwt
import time


provinceList = ['北京市', '天津市', '河北省', '山西省',
                '内蒙古自治区', '辽宁省', '吉林省','黑龙江省',
                '上海市', '江苏省', '浙江省', '安徽省', '福建省',
                '江西省', '山东省','河南省', '湖北省', '湖南省',
                '广东省', '广西壮族自治区','海南省', '重庆市',
                '四川省', '贵州省', '云南省', '西藏自治区', '陕西省',
                '甘肃省', '青海省', '宁夏回族自治区', '新疆维吾尔自治区',
                '台灣', '香港特别行>政区', '澳门特别行政区'];
info = ['省','市','区','健身房场馆','联系电话','地址','经度','维度']
i=0
j=0
count=0
#url ='https://restapi.amap.com/v3/place/text?types=08010'+str(num)+'&city='+s+'&key=37b3781dfd1926ed67c18320fc387ead'
for p in provinceList:
    
    i=0
    j=0
    sportInfo = xlwt.Workbook()
    sheet = sportInfo.add_sheet('p')
    s=p
    print(s)
    urls=[]
    for num in range(0,19):
        url ='https://restapi.amap.com/v3/place/text?types=08010'+str(num)+'&city='+s+'&key=37b3781dfd1926ed67c18320fc387ead'
        urls.append(url)
    for url in urls:
        res = requests.get(url)
        json_data = json.loads(res.text)
        pois = json_data['pois']
        count=count+len(pois)
        time.sleep(0.01)
        sportInfo.save(p+'.xls')
    print(count)
print(count)
    
