import requests
import json
import xlwt


sportInfo = xlwt.Workbook()
sheet = sportInfo.add_sheet('sportInfo')
info = ['省','市','区','健身房场馆','练习电话','地址','经度','维度']
i=0
j=0
for num in info:
    sheet.write(0,i,num)
    i=i+1
    
urls=[]
for num in range(0,19):
    url ='https://restapi.amap.com/v3/place/text?types=08010'+str(num)+'&city=陕西&key=37b3781dfd1926ed67c18320fc387ead'
    urls.append(url)
  
for num in range(0,2):
    url ='https://restapi.amap.com/v3/place/text?types=08020'+str(num)+'&city=西安&key=37b3781dfd1926ed67c18320fc387ead'
    urls.append(url)
    url ='https://restapi.amap.com/v3/place/text?types=08040'+str(num)+'&city=西安&key=37b3781dfd1926ed67c18320fc387ead'
    urls.append(url)

for num in range(0,8):
    url ='https://restapi.amap.com/v3/place/text?types=08030'+str(num)+'&city=西安&key=37b3781dfd1926ed67c18320fc387ead'
    urls.append(url)

for num in range(0,5):
    url ='https://restapi.amap.com/v3/place/text?types=08050'+str(num)+'&city=西安&key=37b3781dfd1926ed67c18320fc387ead'
    urls.append(url)
     
for num in range(0,3):
    url ='https://restapi.amap.com/v3/place/text?types=08060'+str(num)+'&city=西安&key=37b3781dfd1926ed67c18320fc387ead'
    urls.append(url)

#urls = ['https://restapi.amap.com/v3/place/text?types=080108&city=西安&key=37b3781dfd1926ed67c18320fc387ead']
for url in urls:
    res = requests.get(url)
    json_data = json.loads(res.text)
    count = json_data['count']
    # print(count,json_data)
    if int(count) == 0:
        break
    pois = json_data['pois']
    for poi in pois:
        j=j+1;
        name = poi['name']
        pname = poi['pname']
        cityname = poi['cityname']
        adname = poi['adname']
        tel = poi['tel']
        address = poi['address']
        location = poi['location']
        lon = location.split(',')[0]
        lat = location.split(',')[1]
        if len(name) == 0:
            print('dddddd')
            break
        sheet.write(j,0,pname)
        sheet.write(j,1,cityname)
        sheet.write(j,2,adname)
        sheet.write(j,3,name)
        sheet.write(j,4,tel)
        sheet.write(j,5,address)
        sheet.write(j,6,lon)
        sheet.write(j,7,lat)
        print(name)
sportInfo.save('sportInfo.xls')
        
        
        
        
        


























        
       


