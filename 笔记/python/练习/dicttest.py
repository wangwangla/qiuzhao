#查询出所有的城市列表
import requests
import json

provinceList = ['北京市', '天津市', '河北省', '山西省',
                '内蒙古自治区', '辽宁省', '吉林省','黑龙江省',
                '上海市', '江苏省', '浙江省', '安徽省', '福建省',
                '江西省', '山东省','河南省', '湖北省', '湖南省',
                '广东省', '广西壮族自治区','海南省', '重庆市',
                '四川省', '贵州省', '云南省', '西藏自治区', '陕西省',
                '甘肃省', '青海省', '宁夏回族自治区', '新疆维吾尔自治区',
                '台灣', '香港特别行>政区', '澳门特别行政区'];
#urls ='https://restapi.amap.com/v3/config/district?keywords=190102&key=37b3781dfd1926ed67c18320fc387ead'
url = "http://restapi.amap.com/v3/config/district?key=37b3781dfd1926ed67c18320fc387ead&types=190102"
res = requests.get(url)
json_data = json.loads(res.text)


#print(json_data['districts'][0]['name'])
list_info=json_data['districts'];
infos=list_info[0]['districts']
for info in infos:
    name=info['name']
    print(name)




































