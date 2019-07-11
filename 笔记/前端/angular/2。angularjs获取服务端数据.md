一般方式
    <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <title>天气</title>
    <script src="../lib/angular.min.js"></script>
    <script>
        var u1="http://zhouxunwang.cn/data/?id=34&key=BrvOrdNmT9r+h5mB8I0zT2/BOQTgsJeZ/pxx6Q&sort=%E7%AC%91%E8%AF%9D&time=1418816972";
            
        var my=angular.module("my",[]);
        my.controller("mys",function ($scope,$http) {
        $scope.city="西安";
        $scope.show=false;
            $scope.show=true;
            $http({
                url:u1
            }).then(function (data) {
            })
        })
    </script>
    </head>
    <body ng-app="my" ng-controller="mys">

    <ul ng-show="show">
        <li>{{cityName}}</li>
        <li>{{date}}</li>
        <li>{{mTemp}}</li>
        <li>{{xTemp}}</li>
    </ul>
    </body>
    </html>
    完整案例
    <!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>天气</title>
  <script src="../lib/angular.min.js"></script>
  <script>
    var u1="https://free-api.heweather.com/v5/weather?city=";
    var u2;
    var u3="&key=545d63e185fc48169a43cbabba6e74d2";
    var my=angular.module("my",[]);
    my.controller("mys",function ($scope,$http) {
      $scope.city="西安";
      $scope.show=false;
      
          u2=$scope.city;
          $scope.show=true;
          $http({
            url:u1+u2+u3
          }).then(function (data) {
            $scope.cityName=data.data.HeWeather5[0].basic.city;
            $scope.date=data.data.HeWeather5[0].daily_forecast[0].date;
            $scope.mTemp=data.data.HeWeather5[0].daily_forecast[0].tmp.max;
            $scope.xTemp=data.data.HeWeather5[0].daily_forecast[0].tmp.min;
          	
          })
        $scope.city="";
      
    })
  </script>
</head>
<body ng-app="my" ng-controller="mys">

  <ul ng-show="show">
    <li>{{cityName}}</li>
    <li>{{date}}</li>
    <li>{{mTemp}}</li>
    <li>{{xTemp}}</li>
  </ul>
</body>
</html>


jsonp的方式
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title></title>

    </head>
    <body>
        <script type="text/javascript">
       
        		function jsonp(url, data, fn) {
        			function getData(data) {
        				fn(data);
        			}
        			window['getData'] = getData;
        			// 处理data数据
        			var searchData = '';
        			if(data.length) {
        				searchData = "?" + JSON.stringify(data);
        			}else {
        				searchData = "&";
        			}
        			// 1. 创建script标签
        			var script = document.createElement("script");
        			// 2. 加入callback = 
        			script.src = url + searchData + "callback=getData";
        			// 3. 插入标签
        			console.log(document.body.appendChild(script));
        		}
        		var url1="http://zhouxunwang.cn/data/?id=34&key=BrvOrdNmT9r+h5mB8I0zT2/BOQTgsJeZ/pxx6Q&sort=%E7%AC%91%E8%AF%9D&time=1418816972";
        		var url="http://api.douban.com/v2/movie/in_theaters";
        		
        		
        		jsonp(url1, {}, function(data) {
        			console.log(data);
        		
        		});
        </script>
    </body>
</html>



jsonp
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body>

<div ng-app="myApp" ng-controller="customersCtrl">
    <ul>
        <li ng-repeat="x in names">
            {{ x.Name + ', ' + x.Country }}
        </li>
    </ul>
</div>

<script>
    var app = angular.module('myApp', []);
    app.controller('customersCtrl', function ($scope, $http) {
        myUrl = "http://zhouxunwang.cn/data/?id=34&key=BrvOrdNmT9r+h5mB8I0zT2/BOQTgsJeZ/pxx6Q&sort=%E7%AC%91%E8%AF%9D&time=1418816972?callback=JSON_CALLBACK";
        $http.jsonp(myUrl).success(
                function (response) {
                    console.log(response);
                    alert("sssssssss");
                  
                }).error(function(info){
                	alert("d");
                	console.log(status);
                });
    });
</script>
</body>
</html>









jsonp



<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
    <meta charset="UTF-8">
    <title></title>
     <script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
    <script type="text/javascript">
        var app = angular.module('app',[]);
        app.controller('appCtrl', ['$scope','$http', function ($scope,$http) {
	     myUrl = "http://ctb.qingguo.com/mobile/msetuserinfo?uid=11444915&orgcode=100015&callback=JSON_CALLBACK";
        $http.jsonp(myUrl).success(
                function (response) {
                    console.log(response);
                    alert(response.result);
                    alert(response.data.length);
                    alert(response.desc);
//                    alert(response[0].list);
                });
    }]);
    </script>
</head>
<body>
	<div ng-app="app" ng-controller="appCtrl"></div>
</body>
</html>





跨域问题，测试的时候在本地安装插件
因为跨域有两种办法，一种是jsonp，但是如果服务端发送的是jsonp数据，这样就没啥问题，但是服务器传输的是不是jsonp，而是json数据，这就你叫麻烦了，不过在页面打包到同一个服务器下的话，就会有影响了，并且在服务器端也有比较好的处理方法。火狐可以下载一个插件，但是谷歌是不知道的。












