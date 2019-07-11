SNIFFER问题集锦
SNIFFER相关教程下载： Sniffer使用教程.pdf|Sniffer用法.ppt

具体问题解决：
1.SNIFFER4.75无法使用，打开后提示

No adapter is binding to Sniffer driver
如下图



4.7.5 With SP5以上支持千兆以太网,一般我们从网络上下载的4.75不支持千M网卡

4.7.5 With SP5下载：

微盘：   SNIFFER POR V4.7.5 SP5(中英文切换版).rar

CSDN：SNIFFER POR V4.7.5 SP5汉化版


2.如何导入导出snifferPro定义的过滤器的过滤文件?

方法一、强行覆盖法
C:\Program Files\NAI\SnifferNT\Program\
下面有很多Local打头的目录，对应select settings里各个profile，
进入相应的local目录，用sniffer scan.csf 去覆盖Sniffer.csf(捕获过滤） 或者snifferdisplay.csf（显示过滤）

方法二、声东击西法
C:\Program Files\NAI\SnifferNT\Program\
下面有个文件Nxsample.csf，备份。
用Sniffer scan.csf替换nxsample.csf
启动Sniffer
新建过滤器
在新建过滤器对话框的sample选择里选择相应的过滤器，
建立新过滤。

反复新建，直到全部加入


3.sniffer的警报日志是些做什么用的??

sniffer警报日志也是sniffer专家系统的一部分,通常不正常的TCP/IP通讯会产生警报和日志.
比如: ping 的反映时间太长, 某一个会话中丢包或者重传数过多,单位时间产生的广播/多播数过多等等. 

4.利用ARP检测混杂模式的节点

文章很长,PDF查看：使用ARP分组检测处于混杂模式的网络节点.pdf


5.sniffer 中，utilization是按什么计算的？
Octect就是字节的意思。
Sniffer表盘的利用率是根据你的网卡的带宽来计算的，如果要监测广域网带宽的话，需要使用Sniffer提供的相应的广域网接口设备：比如 Snifferbook用来接入E1、T1、RS/V等链路，这时它所显示出的带宽就是正确的广域网带宽（如非信道化E1它会显示2.048Mbs）。

6.octets/s单位怎么计算的?

octet=byte
7.expert分析系统分析的数据是定义capture所截取的数据呢还是分析所有流经镜像端口的数据？
sniffer的专家系统只对所捕获的数据进行分析

 

8.sniffer pro4.75 不现实仪表盘和alarm log 怎么回事?

请检查您的操作系统是否安装java~

9.sniffer能识别出notes协议么

需在Tools--Options--Protocol中将lotus所用端口定义进去。1352 是notes的端口
用什么软件是次要的，关键是要理解如何根据自身的实际情况进行定制

10.在sniffer中如何定义捕捉端口范围?我想捕捉端口为8000-9000的数据包,请问在sniffer中如何定义过滤器?

建议使用SNIFFER PRO 4.8及以上版本

11.[sniffer decode面板]为什么都是乱码啊？

是这样的，这次你抓的都是小包，包头的ASCII没有实际含义，如果抓一些http/ftp之类，你可能就可以看到一些明文了

12.为何sniffer dashboard不会动的
Sniffer 4.7.5 sp5以前的版本需要Java Vitual Machine和JRE1.3.02，Java VM组件必须在安装Sniffer前检查IE里是否已存在，如果没有，就要重新安装IE组件。装完Sniffer后，第一次打开Sniffer时，会提示 安装JRE1.3.02，如果你的机器之前安装过更高版本的JRE，需要卸载然后再装JRE1.3.02，如果没有安装过，请安装JRE1.3.02。满 足了这两个条件，Dashboard就没有问题了。如果因为Java的问题无法运行Dashboard的话，不会影响Sniffer其它功能的运行，大不 了不要打开Dashboard就可以了。


13.对sniffer的疑问？？？

Switch不是把数据包进行端口广播，它将通过自己的ARP缓存来决定数据包传输到那个端口上。因此，在交换网络上，如果把上面例子中的HUB换为Switch，B就不会接收到A发送给C的数据包，即便设置网卡为混杂模式，也不能进行嗅探?

answer:Ｓｎｉｆｆｅｒ什么都不是。。
这个自己再去想办法。。
Ｓｎｉｆｆｅｒ只负责抓包分析的事。。。其他的自己搞定

14:我这的交换机不支持端口镜像，请问我如何配置才能用sniffer pro分析网络？

交换环境下的ARP欺骗和Sniffer,就这么简单

1、准备
win2000/xp ＋Etherpeek 。将自己的MAC修改为一个不存在的，具体步骤不再叙述。干坏事嘛，总得伪装一下。
2、将本机的网关设为非本网段的任一IP，比如设成其他网段的网关。制作一个批处理包含以下命令：
arp -s 网关IP 本网段网关的MAC
这样做的目的是防止ARP欺骗时自己上网也困难
3、在注册表的HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\Tcpip\Parameters下增加：

Name:IPEnableRouter

Type:REG_DWORD

Value:0x01

重启系统即可。
目的是启用路由转发功能，以便在随后的ARP欺骗中本机充当MAN-IN-THE-MIDDLE(中间人）
4、启动Etherpeek,构造如图所示的包（图1），可以将这个包存盘，以备后用。
5、以每秒5个包的速度向外发送这个ARP欺骗包（图2）。
6、启动捕获，就可以抓到整个网段内的包。
7、注意：不要用此方法干坏事。被网管抓住别找我！


15:通过Packet Generator如何定向发包？

Q:的意思是往指定的主机发包吗？自己造包比较麻烦，你可以先截取一段数据包，然后编辑包，修改目的地址

15:在监控端口和sniffer之间，可以级联交换机吗?

可以，前提是要支持ＲＳＰＡＮ，并且正确配置！

16：Sniffer的非正常退出

我在使用Sniffer的过程中，会发生异常错误。Sniffer进程无法杀掉。
请问该如何解决？

ntsd -c q -p PID
强制杀掉进程，pid为在任务管理器里看到的进程号

17:在sniffer 的 source address这一栏下，为什么有的显示ip地址，有的却显示mac地址？是不是因为显示mac地址的数据包没有包括ip层及以上的层的数据？

有些协议用于链路协商，分配地址等等，是没有带ip地址的包头的，比如rapr,arp协议等。

18:[原创]在Sniffer Pro中创建基于子网掩码的过滤器?

初为做的一个说明，解释如何在Sniffer Pro中实现基于子网掩码的过滤，
（默认功能只能提供基于主机IP地址的过滤）
以后再介绍介绍在OmniPeek里实现的快捷方式，对比一下就知道Sniffer Pro这只大笨象有多笨拙了

19: 我单位使用的都是3com产品,不知能否通过Sniffer的Switch功能来能交换机进行管理呢.
我自己试了试,不行,请高手指点. ?

Sniffer只支持Cisco大部分交换机和北电的450，3Com的不支持。Sniffer Switch Expert可以收集交换机的RMON信息，可以设置交换机警告并自动触发抓包，可以设置镜像端口等。
如果要用Sniffer Switch Expert功能，最好是有两块网卡，一块用来与交换机通讯，一块用来捕捉包。 



19:Sniffer的历史采样如何打开？在sniffer里做了占用率等历史采集，保存成了.hst文件，现在想调出来看，如何做？
Q:打开History，将.hst文件拖进去

20: 用sniffer构建数据包时，建好了不能保存啊？
用sniffer构建数据包时，建好了不能保存啊？每次都得重新构建数据包，这样很麻烦呀，可不可以保存下来，每次调用一下就OK了?

Q:构造好之后发送出去，同时捕捉一次不就保存下来了，我以前常这么干，省得我自己计算ip_sum什么的。

21:如何在８１３９网卡上设置，使其能在ＳＮＩＦＦＥＲ下看到小于６４大于１５１８的包？

如果使用Sniffer提供的专用网卡（Sniffer Cardbus网卡），可以捕捉到许多数链层的错误：
1、CRC校验错误。
2、Collison（小于64字节，一般是中继器发Jam信号导致）。
3、Runt（小于64字节，但有正确的CRC）。
4、Fragment（小于64字节，但CRC错误）。
5、Aligment（有两种，一种是小于64字节，另一种大于64字节）。
6、Jabber（大于1514字节，一般可能是网卡或中继器问题引起）。
7、Oversize（大于1514字节，如果是VLAN的Trunk协议，如802.1Q协议（1518字节）和Cisco ISL协议（1540字节），它们都大于1514字节，Sniffer也会认为它们是Oversize。）

那么上面说了这么多种错误，实际上你在现在的网络环境当中，就算是使用了Sniffer专用的Cardbus网卡，也不见得会捕捉到这些错误，为什么呢？ 原因有二：一是因为我们现在的网络大多是交换网络，需要进行交换机镜像才能让Sniffer进行捕捉数据包，而交换机会把这些错误包给丢弃了，根本到达不 了Sniffer镜像的端口。二是因为这些错误基本上是因为中继设备和网卡设备的硬件故障导致的，而现在无论是交换机、集线器还是网卡，硬件制造的工艺都 很成熟和稳定，以上这些错误，基本上，很难再产生了。
如果你没有Sniffer专用网卡，可以有两种办法：一，按V大说的自开发驱动。二，到市场上买Xircom CBE2-100型号网卡，Sniffer Cardbus网卡是它OEM的。

22:sniffer pro是不是只能监控同一交换机下的流量？这个和Sniffer没有关系！

关键在于交换机
比如有些可以支持RSPAN，跨交换机的流量也可以看到啊

 

23: 使用Sniffer Portable 抓包的时候,在解码后发现"Frame too short"提示 的可能原因是什么?

硬件（网线、网卡、端口等）有故障的症兆或存在双工、速率匹配问题。

24:请教一个sniffer的协议分析使用的问题

对比了一下，发现自己的sniffer在查看protocols分布情况时，未知的协议类型都为“others”，而别人的却能显示出具体的协议和端口 号，如下图，这是怎么回事？是不是什么选项没选上？加入具体的协议端口,下次你的也就不会是Other了.Tools ->Options->Protocols添加

25:sniffer pro ART 功能？
1、ART是应用响应时间，属于Sniffer的 Monitor功能，跟Capture和Packet Gen没有关系。
2、缺省情况下ART只监控HTTP应用，如果要监控其它协议，需要添加协议。这里要注意一点，添加协议完要重启ART应用才会生效，有时候需要重启Sniffer才行（个人经验）。
3、如果按第2步做了还是没有数据，你就要考虑是不是你添加的协议在你所能够监视到的流量里根本就不存在。（因为你说并没有做端口镜像）。
4、没有让你选择网卡，你可以手动来选，打开File-select setting－选择你需要的网卡。


26：怎么利用sniffer做一个流量的baseline？

小弟我想用sniffer做一个流量的baseline，方便以后流量出现异常用来对比，可是我对baseline具体是什么内容还不清楚，也不知道到底 该怎么做，请各位能不能指点sniffer历史采样(history sample)其实就是软件默认定义的baseline
但根据网络的实际情况不同,需要制订符合实地情况的基准线

建立baseline的方法,个人认为:
捕获正常网络状态数据包,得出packet/s,utilization/s,broadcast/s等等的数据
然后修改其阈值及采样频率

以后学习到标准制订baseline方法,再改正!

27：用snifferpro如何抓printer（打印）的包呢？

就是想用snifferpro监控打印机的使用，抓打印文件的路径和打印者的mac地址。
snifferpro的包过滤里面有tcp－printer选择，但是我怎么都抓不到东西。Q：用的是过滤中的netbios包。
又有新问题，是安装了sniffer之后，发现服务器工作不正常，我觉得可能是sniffer让服务器网卡处于混杂模式的缘故。删掉之后就好了。

我其实只是需要过滤 到通过服务器的包而已，请问如何设置sniffer关闭网卡的混杂模式？

28：在线求助(sniffer的截图)。警报:WINS No Response?? WINS no reponse是指网络中WINS服务器对WINS请求没有响应，WINS是微软的Windows Name Service，你用网上邻居时看到机器名实际上就是WINS服务起的作用。

你Sniffer系统的专家告警并非误报，是由于其没有抓获WINS Reponse数据包所造成的，可能是由于你在交换环境下抓包没有做镜像，只能抓到广播包，所以能够捕获到WINS请求，但无法捕获到回应包，所以会产生告警。


29:Sniffer Pro新版本4.8已经发布了，可以说有不少的好消息??Sniffer Pro新版本4.8已经发布了，可以说有不少的好消息：

1、Dashboard不再使用Java了，呵呵，原先Sniffer使用Java是很多人深恶痛绝的（包括本人），一打开Sniffer不再是CPU和内存蹭蹭的往上串了。

2、地址过滤可以使用子网过滤了（不再需要用Data Pattern来做了）。

3、可以做端口范围的过滤（如TCP 1050-1052）。

4、Data Pattern的Offset里多了一个叫”Variable offset“的功能，也就是说你可以做不用管位偏移在哪里的模式匹配了，举个例子：你要捕捉所有访问Sina网页的数据，你可以在Data Pattern里填入”sina“的ASCII字符，然后把”Variable offset“选项打勾，这样不管"sina"这个字符出现在数据的哪一部分，Sniffer都可以捕捉。

5、支持802.11g无线协议。

6、支持通用10/100/1000M Ethernet网卡。

7、支持实时解码－－这点也是Sniffer以前常被人诟病的地方，俺喜欢这功能。

8、支持Sniffer Mobile 1.0和Sniffer Voice 2.5。

呵呵，前面举了这么多好的方面，当然也有坏的消息：不再是使用序列号注册的方式，而是采用跟收集硬件信息和Grand No的方式来授权使用，必须在安装Sniffer的机器上生成一个请求授权文件，然后把该文件电邮给NG公司，由它们授权返回一个文件再导入才能正常使 用，要不，只能试用15天。（谁要是有办法解决授权问题，不妨共享给俺俺会十分感谢的）

30: 请问:sniffer的dashboard中的"利用率"到底是什么意思?

我的计算机级联的是10M以太网集线器,我也没有把网口插入交换机的镜像端口,就是直接运行SNIFFER,所以SNIFFER监测的流量及数据全是我的机器的,SNIFFER显示的是10M以太网卡.
这时我做了一个FTP测试,下载电影.每秒900多K,利用率怎么会显示百分之八十多呢?我就不明白啦,我连的这个网段是10M,而现在才使用啦每秒900多K,利用率怎么会显示百分之八十多呢?
不懂,请高手指教!!!
谢谢!!!下载时候的速度显示的是KB/S,而你的带宽单位是Kb/s,你下载的速度乘以8才是你对带宽的占用率,900多乘以8,可不是差不多接近10M的80%

31:Sniffer抓包时，为什么总是自己机器在流量最大？

网络环境：电信2M ADSL+宽带路由器+四口交换机，四个人通过交换机共享一个2M的ADSL，在的电脑接在交换机的端口之，然后在我的电脑安装Sniffer，然后看主 机列表中的数据包，为什么老是我的机器的数据流最大？为什么比网关（路由器IP）的收发的数据包还多??

Q: 他们说的对,主要是与你接入的位置有关.
取流量的三种方式:交换机镜象口\HUB\TAP32:我想的找一个SNIFFER4.8 有哪个大侠给一个连接??http://www.networkgeneral.com/Eval.aspx
试用15days

 

33:sniffer reporter怎么用啊？？？？

我下了一个sniffer reporter想专门用来做流量分析报告，可惜不会用啊，俺英文也不好，帮助也看不大懂，哪位用过的兄弟说说啊~Q: 倒，手册这么好的东西不看。
在装有Sniffer的机器上安装Reporter，然后启动Reporter Agent，再启动Console，添加你用Sniffer打开的那块网卡做为Agent。
打开Sniffer，在Database--Options里将所有你需要生成的数据项目都打上叉，最好调整默认的时间60分钟为5分钟，这样就可以比较快的生成报告。
一小时（比较好的时间，这样可以有更多点的数据）后就可以在Console上生成你需要的项目的报告了。

34:【网络分析入门】[原创]如何使用Sniffer的过滤器文件?
坛里经常看到许多人对Sniffer的过滤器文件不知道该如何使用，闲来无事，就截了几副图演示一番。
首先，我们可以去NG公司的网站去下载Sniffer过滤器（主要是用来检测Vulnerabilites和Virus的），漏洞的过滤器下载网址：http://www.networkgeneral.com/SnifferFilters_Details.aspx?Type=2
病毒的过滤器下载网址：http://www.networkgeneral.com/SnifferFilters_Details.aspx?Type=1 ，需要说明的是Sniffer的病毒过滤器的名称定义是来自McAfee的定义，与其它防病毒厂商尤其是国内的防病毒厂商的病毒名称定义是有一些差异的。
下载到过滤器，我们就可以把该过滤器导入到Sniffer里去了。
解压开下载到的过滤器文件，你会看到许多文件，我们以Mydoom病毒过滤器文件举例说明：Importing Filter.rtf（导入过滤器说明文件），Sniffer Filter Creation Specification for （说明如何定义Mydoom病毒过滤器），NetAsyst - （NetAsyst软件使用－－NG公司针对中小型企业定制的软件，功能与Sniffer Pro基本相当，只限10/100M Ethernet和Wireless使用），SnifferDistributed4.* - (分布式Sniffer使用，有多个版本：4.1，4.2，4.3，4.5等），还有就是我们需要使用的SnifferPortable4.* - （有4.7,4.7.5,4.8等版本，针对你所使用的Sniffer版本号来选择你需要的）。
接着找到Sniffer的安装目录，默认情况下是在：C:\Program Files\NAI\SnifferNT\Program，找到该目录下的“Nxsample.csf”文件，将它改名成 Nxsample.csf.bak（主要是为了备份，否则可以删除），然后将我们所需要的过滤器文件SnifferPortable4.7.5 - 文件拷贝到该目录，并将它改名为“Nxsample.csf”。
然后，我们再打开Sniffer Pro软件，定义过滤器（Capture--Define Filter），选择Profile－－New－－在New Profile Name里填入相应的标识，如W32/Mydoom－－选择Copy Sample Profile－－选择W32/Mydoom@MM，确定后，我们就算做好了Mydoom这个病毒的过滤器。
现在，我们就可以在过滤器选择里选择Mydoom过滤器对Mydoom病毒进行检测了。


35:怎样用SNIFFER查找ARP欺骗？

各位，现在的ARP欺骗问题是越来越多了，造成的影响也是越来越大，利用SNIFFER怎样才能发现ARP欺骗呢？
Q:从最近常见的欺骗来看，最明显的是ARP REQUEST包的目的的如果不是广播而是网关，那就基本可以确定有问题.

36:Sniffer打开trace file提示“unsupported file format”问题

很多.cap文件，使用sniffer打开提示“unsupported file format”错误

使用omnipeek打开,提示

有人研究过是怎么回事吗？
Q:不支持这种格式。

CAP是一种通用的格式，而不仅仅是Sniffer的CAP，不同CAP文件的内部结构是不一样的。
举个例，在Ethereal中，如果你将保存类型设为libpcap，然后保存为*.cap，这样Sniffer就打不开这个cap文件。

37:Sniffer抓包时，为什么总是自己机器在流量最大？
Q:没开镜像呀,抓的都是你自己的包.

38:sniffer pro提示有Local Routing是什么原因？

网络很简单
Harbor58423F是本地的三层交换
NortelF4CA0D是上级三层

1.在抓包后有很多的Local Routing是什么原因？
2.为什么会出现Local Routing？
Qocal Routing"属于TCP层的告警，产生原因是检测到Packet本应在DLC层进行转发但却通过ROUTER转发，原因有以下三点：
1、路由器的路由表设置问题，使在同一段内能直接通信的设备不致通过路由器进行通信；
2、路由器出于安全考虑，防止访问网络的特定部分；
3、路由器实现网关类功能，在应用层执行协议转换功能；
4、新设备联入网络未识别到与其他设备有直接路由、或设备配置成使用指定路由器。


39:用sniffer模拟发包的疑问？

今天应客户要求做一个ACL过滤功能的测试，我想使用sniffer的模拟发包功能（packet generator）来模拟发包！下面是我的方法：
1、先访问一个网站并抓包（收集TCP、UDP数据包）
2、选择一个TCP包，并使用send current frame编辑包
3、修改目的IP地址、端口号并发送。
4、把两个VLAN 上的ACL全部去掉，并在对端抓包，发现没有抓到一个包。
5、在自己电脑上抓包，发现IP、端口都已经修改。但提示包missing。
6、同样方法使用UDP包发送，也不成功
7、直接ping 对方IP，并抓包。然后直接模拟发包，对方可以收到。


不知道问题的原因，请大家指点！！谢谢！
Q:修改IP的同时要修改checksum，其他协议也同样道理。

40.能否用Sniffer发包功能伪造数据包?
最近看了不少伪造数据包的文章。但是，都不是很详细。所以想亲自实战一下。发现Sniffer具有发包功能。于是就想到在Sniffer里改变一下源IP地址是否能达到IP欺骗呢！

环境：
找了两台电脑，每台电脑上运行Sniffer Pro 4.75
每台电脑各配一个真实的互联网IP （A、B），机器A、机器B不在一个网段里。 
两台机器的网关做在一个路由器上，并能访问互联网。

操作：
1）首先把两台机器的Sniffer开启混杂模式抓包。
2）然后在机器A上访问任意一个互联网地址
3）停止机器A抓包，然后查看Decode，查找三次握手中的第一次握手“SYN SEQ=”
4）找到后进入发包模式，更改原IP地址为任意一个互联网IP地址，更改目的IP地址为机器B的IP地址，更改原、目的端口。发送此包n次。
5）稍后，在机器B上停止抓包，并产看Decode是否有伪造地址的“SYN SEQ=”

结果：
可惜的很呀！试验没有成功，如果机器A用伪造的地址进行SYN，在机器B上看不到。但是，用机器A自身真实的地址机器就能看到机器A的“SYN SEQ=“

问题：
为什么机器A伪造IP包在机器B上看不到，还有，互联网上说得伪造IP包是怎样通过本地DefaultRoute（网关）。路由器是否更改机器A发送来的伪造的数据包？ 
请各位高手详细解说一下伪造的IP包是怎样在互联网路由器上传送的?

Q:很简单，改了IP包头，需要修改checksum，否则包发布出去

41:sniffer专用网卡的问题

据说专用网卡可以抓一些错包，到底哪一些是呢？是否要安装专用驱动程序？

那位兄弟能帮忙解答一下，多谢!Q: 找到答案了，专用网卡上面有SNIFFER的标记。安装一下SNIFFER的驱动即可。
鄙视一下sniffer，真的是衰落了，网站上的中国区电话根本没人接，想找一个专用网卡的列表，根本找不到（当然也有可能我的搜索技术不过关）。
: 用DEC211xx芯片的网卡即为专用网卡

42:请教关于sniffer请问NG与NAI是什么关系

两家公司都有自己的sniffer产品?Q:NAI是97年McAfee和Network General合并后的公司名称。
2004年NAI将NG卖给两家投资公司（Silver Lake Partners与Texas Pacific Group），NAI又分成两家公司：McAfee和Network General，从此，NAI不再是NAI，而是McAfee。

43:sniffer如何跨网段管理?

局域网的拓扑结构是，外网光纤接光纤交换机，光交接防火墙，防火墙连两台核心交换机，两台核心交换机互为热备，再分连各楼层交换机，网段按楼层划分，一共 20个网段。我想用sniffer可以监测各个网段的流量，但不知应该将监测电脑安在哪个位置。急迫请教高手赐教。Q: 好像不可以一下监控所有的VLAN ，只能一个一个做RSPAN，想看那个就配制那个VLAN！
监控外网的就到出口的地方放个HUB，呵呵方便。

44:ICMP Redirect for Host的告警信息，重定向主机?

请教
ICMP Redirect for Host的告警信息，重定向主机。
这个重定向主机是什么意思，有什么用？
我今天抓包，发现有ICMP Redirect for Host和 ICMP Redirect for NETWORK，这意味着什么呢。。。？Q: 路由/默认网关设置有问题

在网络分析版查找,有人讨论过这个话题..你抓外包上来看看。正常情况下不太可能出现这种报文。

在开启了IP路由功能的ARP欺骗中，中转机会发送这种报文。

在直接进行的ICMP重定向攻击中，也会出现这种报文。

提供一个CAP文件，再描述一下你的拓扑。

45:我想实现的一个协议分析功能？大家看看能不能帮我实现。?

我想要实现一个这样的功能：
通过流量分析建立一个出口路由器或着想要监视的网络设备的流量基线，比如：TCP、UDP、ARP、ICMP。。。等等包文占总体报文的百分之多少，然后 形成各自的曲线图。。。周统计，年统计等方式，一旦有网络堵塞或网络故障，就知道是那种包文导致的，然后在查具体是什么包文的什么端口，或什么类型，或者 是什么IP导致的网络流量异常。。。。

因为我认为抓包只对流量较少的企业有效，但对于一个小的ISP，或者大型企业，一旦有网络拥塞的故障，做镜像然后抓包，很短的时间都可能导致你的本本死机，然后是大量的报文（甚至100M），分析完都要类死了。。
Q: 你的设想都有现成的产品，Sniffer、Netscout都有相应的硬件产品，另外通过Netflow也可以做到。Sniffer针对大流量的抓包有 S6040或者Infinistream硬件探针设备，做流量趋势分析有Sniffer enterprise visualizer报表服务器。


46:sniffer BROADCAST及UTILIZATION 监测在清晨会自动停止工作?安装的SNIFFER 4.75/4.9 .

运行monitor -> history sample -> broadcast /s 或者 UTilization 在清晨 时间轴停止在2:00 .程序没有死机.

请问这是什么原因?Q:Sniffer的History Sample历史采样的缓冲只有3600个采样点，你用默认的每隔15秒进行一次采样，这样只能采样15个小时其缓冲区就满了，所以你的采样就停止了。如 果你想采样时间长些，那只能加大采样间隔时间或者修改缓冲区满了覆盖数据（Wrap buffer when full)。

47:原创]交换环境下进行sniffer的方法（WIN平台）?交换环境下进行sniffer(基于WINDOWS平台） 

题 目：交换环境下进行sniffer，Windows平台。
试验环境：公司内部，通过一个ADSL路由器上网，内部采用8口小交换机连接
目 标：现在要监听机器B
采用软件：Ethereal 0.99 ，安装在WINXP平台上， 机器为A，arp欺骗软件:arpsender

监听原理：为了监听B,需要用到ARP欺骗：即不停的向B发ARP响应包，告诉它网关的MAC是MAC_A(而不是正常的网关的MAC),这样当B向网关 发包时，所有的包都到了机器A；再不停的向网关发ARP响应包，告诉网关，机器B的MAC地址是MAC_A（而不是正常的MAC_B)，这样，当网关要向 B发数据时，就也发送到了A。

遇到的问题：但是，实验中发现，机器A收到网关至B或者B到网关的数据包后，没有再进行转发，直接丢弃了，这样就造成了一种现象：机器B无法上网了。（包倒是监听到了一些）

解决：这肯定是机器A的IP路由没有打开。经过努力，发现WINXP的系统服务里有一项 routing and remote acess service，将其启动就可以了。

48：使用SNIFFER监测QQ号码及获取IP地址的方法？

昨天上网的时候，发现了这个论坛。我是在研究如何使用MRTG来监测华为路由器和交换机的CPU使用率时，无意间找到这个论坛的。这个问题目前还没有找到 解决方法。今天睡的早了点，半夜就起来了，一口气看了论坛的好多文章，的的确确是“网络分析专家”，有好多概念模糊的问题都可以找到答案。很幸运没有错 过！希望有了解MRTG的朋友指点一下我所遇到的问题。

刚才看了本站劳模1259的一篇文章《使用SNIFFER PRO来进行监控BT协议的流量信息》，连接地址是：http://www.netexpert.cn/viewthread.php?tid=354&fpage=3， 想到了去年公安局来我们这里查QQ号码的事，当时说正在搜捕一个持枪逃犯，现在知道他的QQ号，并且发现最近一天他的QQ号在本地上过网，而且IP地址就 是我们的（通过显示IP的QQ版本，已经取得了该QQ的IP地址和端口号）。刚开始领导以为又是来找什么麻烦，但是人家来了不能不配合。当时要求我们尽快 查找上网记录，找到究竟哪个用户在用这个QQ号码，不巧的是我们的计费管理系统正在升级，近一个月的都没有记录，这可怎么办呢？心想先装装样子再说吧。顺 便说明一下，我们这里上网是NAT转换出去的，并不是每个用户一个公网IP。所以现在就是要找到这个QQ用的内部私有地址是什么，再进一步找到上网帐号。

开始的时候想，如果这个QQ号码在某一个时刻通过我们的宽带接入上网的话，那么只要刑警查看一下对方的IP地址和端口号，这边立刻登陆到路由器上，通过查 看NAT地址转换的SESSION，就可以找到对方的内部私有地址了。于是一边假装配合着查找上网记录（其实都是以前的，最近一个月的记录根本没有），一 面说只要这个号码上网了，这边立刻就能找到（通过查看路由器的NAT session)。但是大家等了大半夜，也没等到。这下可苦了我了。那也没办法，只能一直目不转睛地等着。接下来我就琢磨，怎样才能做到，当这个QQ号码 上网之后，立刻就能用软件截取到信息，自然就想到了SNIFFER这个软件。

开始用自己的QQ号码作试验。思路很简单，先把自己的QQ号码换算成16进制的，就用WINDOWS自带的科学计算器就可以了。原本以为QQ的通讯过程会 把所有信息都加密，但是抱着瞎猫碰死耗子的想法试验一下。在SNIFFER里面设定至只抓取UDP协议的报文，因为一般情况下QQ使用UDP协议，再就是 把后台运行的其他网络软件关掉，避免抓到一些没用的干扰数据。呵呵！没想到这么顺利，一下子就在一个UDP包里面找到了自己的QQ号码的16进制，再一 看，好多包里都有，到处都是，而且固定在偏移量31H处开始的4个字节，就是你的QQ号码。接下来就很简单了，在几台网管设备上分别运行SNIFFER （已经在适当的节点配置了端口镜像），设定条件是第31H处是需要查找的QQ号码的16进制，只抓取UDP，并且目的端口等于8000。接下来就可以睡觉 了，只要有结果，就会被筛选出来。具体设定过滤条件时候需要设置Data Pattern请大家参考一下前面1259写的那篇文章，方法是一样的。

等被电话叫醒的时候，已经中午了。这时公安那边已经发现对方上QQ了，但是确看不到对方的IP地址和端口号（公安那边就是在用珊瑚虫QQ想看到对方的IP 和端口号，我还以为有什么先进的软件或者设备呢），但是我们知道由于各种原因，类似的这种显示IP的QQ并不是总能看到对方的IP和端口号的。赶紧去查看 了一下几台正在运行SNIFFER的服务器，有一台服务器已经有数据筛选出来了，赶紧查看了一下，呵呵！要找的东西出来了，幸好提前想到了 SNIFFER，要不然那边公安的没看到对方的IP，这里就得抓瞎。进一步查了一下上网帐号……

后来想进一步查看是否能查到本地QQ正在和哪些号码聊天，不过这一点没有想象的那么简单了。不过还是略有一点其他的发现。就是当你使用类似于珊瑚虫版QQ 时，有时也看不到对方的IP地址，相信大家也经常遇到这种情况。网上有文章介绍说，这时如果你试图浏览一下对方的共享文件夹，再重新打开聊天窗口后，就可 以看到对方的IP了。试了一下，的确有时候好用，但也不是总好使。受这一点启发，粗略地分析了一下，一般聊天的时候，文字信息都是通过QQ服务器中转的， 也就是说，你和对方的IP并没有直接建立联系。那么只要想办法让自己和对方直接建立联系，那就可以用SNIFFER捕捉到对方的IP了，浏览对方的共享文 件夹大概就是这个意思。接下来就想到视频和语音聊天，发送文件等等这些QQ附加功能，一般情况都应该是试图直接建立直连的。但是这些都需要对方许可才行， 但是发送图片不需要对方确认，对，随便发一张对方没有的图片试一试。效果不错，前面通过各种方法都无法看到IP的QQ号码，通过这种方法都查找到了IP。 不过不敢保证这种方法总是有效的。另外QQ的版本升级比较快，不同的版本的通讯原理可能也有变化，而且对QQ的通讯过程掌握的不是很清楚。说到这里大家可 能觉得上网简直是一点安全感都没有，有象我这样的“坏蛋”在研究怎样监视大家的秘密。不过我这是工作需要逼着你去做的，要不然我才不会干这样的“坏事”。 不过通过研究的过程确实能学习到很多知识，欢迎大家一起讨论！

49：交换网络中的sniffer讨论？

大家都知道在HUB的网络里面用sniffer很容易嗅探到 数据包的。
因为是 在一个冲突domain。学过CCNA都知道。hub只是一个电信号的转发。
当你的nic在 混合模式下。是可以听到所有的数据包的。

那么在Switch 的网络里面怎么样呢？
Switch是根据 MAC address 具体转发到某个端口的。
这样。用一般的sniffer软件是不能听到其他的端口的数据的。在物理上就被隔离了：）

怎么办？
在switch 里面是根据mac-address-table来确定转发的。
如果我们把这个mac-address-table clear 不是可以了吗？：）
但是设备我们不一定可以接触啊。还是郁闷。

switch 的 mac-address-table是存在cache 中的。是有一定的空间的。
如果我们把这个表用一些垃圾的数据把这个表给刷了。这样的那些直接接在switch 上的mac地址没有地方来放了。
因为mac表中没有这些数据。switch 只有象所有的端口去转发这些数据包。
这样我们就可以在其他的端口 听到这些数据包了 ：）
就实现了sniffer的功能。

当然还有一种正常的作法是在交换网络下面是用ＳＰＡＮ来进行ＳＮＩＦＦＥＲ的。

基于上面的论述，提出一个问题，在交换网络里用ＳＮＩＦＦＥＲ好，还是在共享式的ＨＵＢ下面用ＳＮＩＦＦＥＲ好呢？　先让网友们拍拍砖。
转一篇贴子：基于交换网络的ARP spoofing sniffer

[注]在阅读这篇文章之前，我假设你已经知道TCP/IP协议，ARP协议，知道什么是sniffer等基本网络知识。
在一般的局域网里面，经常会有两种接入方式，一种是HUB接入（这里的HUB是指普通HUB），一种是交换机直接接入（这里的交换机是比较高级的交换机， 老式交换机不在此列）。采用HUB方式接入的网络，数据传送的时候，是采用广播的方式发送，这个时候，只要一台主机将自己的网卡设置成混杂模式 （promiscuous mode），就可以嗅探到整个网络的数据。 此篇文章不打算讨论这种网络环境的嗅探(sniffer)和反嗅探(anti sniffer)方法，主要是想就交换机方式直接接入的网络环境如何sniffer以及如何anti sniffer做一个比较粗浅的分析。
我们知道，一台计算机想要接入到网络中，必须要有两个地址。一个是网卡的地址，我们称之为MAC地址，它是固化在网卡中的。在以太网中，我们通过MAC地 址来进行数据传送和数据交换。在以太网环境中，数据会分帧传送，每一个数据帧都会包含自己的MAC和目的MAC地址信息； 另外一个地址是平时所说的IP地址，定义在网络层，每一台网络计算机都会有一个或者多个IP地址，这是一个虚拟的数据，并且可以随时更改。
IP地址和MAC地址是同时使用的，在数据传送过程中，一个完整的TCP/IP包需要由以太网进行数据封装，数据分帧，最后再通过物理层传输到目标计算 机。在以太网封装上层的TCP/IP包的时候，它需要知道源MAC地址和目的MAC地址，但是我们只能给出一个对方的IP地址，这个时候就需要一个协议来 支持IP到MAC的转换，这就是ARP,Address Resolution Protocol. 
在局域网中，ARP是通过广播的方式来发送的，比如，我的机器IP是192.168.7.110(A),需要知道192.168.7.119(B)机器的 MAC地址，那从A机器就会广播一个ARP包，这个包里带有B机器的IP，如果B机器收到了此ARP包，那么他就会同样返回一个ARP包，里面带有响应的 MAC地址。A收到这个ARP包后，得到B的MAC地址，这个时候以太网就可以开始封装TCP/IP包了，可以开始正常的数据传送了。比如：
d:\>arp -a

Interface: 192.168.7.110 on Interface 0x1000003
Internet Address Physical Address Type
192.168.7.1 00-90-0b-01-a0-61 dynamic

d:\>ping 192.168.7.119

Pinging 192.168.7.119 with 32 bytes of data:

Reply from 192.168.7.119: bytes=32 time<10ms TTL=128

Ping statistics for 192.168.7.119:
Packets: Sent = 1, Received = 1, Lost = 0 (0% loss),
Approximate round trip times in milli-seconds:
Minimum = 0ms, Maximum = 0ms, Average = 0ms
Control-C
^C
d:\>arp -a

Interface: 192.168.7.110 on Interface 0x1000003
Internet Address Physical Address Type
192.168.7.1 00-90-0b-01-a0-61 dynamic
192.168.7.119 00-d0-59-26-df-1a dynamic

可以清楚的看到，在未和192.168.7.119通讯之前，本机是没有该IP对应MAC地址的，但一旦通讯后，我们就知道了对方的MAC地址，windows会将对方MAC地址存在自己的ARP cache里面。
为了节省网络资源以及通讯时间，多数操作系统会保留一张ARP缓存表，里面记录曾经访问的IP和MAC地址影射记录，一旦局域网中有一个新的ARP广播， 对应一个IP到MAC的记录，这个ARP缓存表就会被刷新，MAC地址会更换成新的广播包里定义的MAC地址，这个时候就存在一个问题，在更新的时候，系 统并没有去检查是否真的是由该机器广播出来的，局域网中的恶意用户就会利用欺骗的方式来更改网络途径，将真正的MAC地址替换成自己的MAC地址，这种方 法称之为：ARP spoofing。
交换机在处理数据的时候，它会根据自己机器内部的一个MAC到端口的数据表来查询符合要求的MAC地址数据包该发往哪个端口。这张表从交换机开机的时候就 存在，在每个端口第一次数据传送的时候就会记录对应的端口的MAC地址. 通过发送我们伪造的MAC地址数据包到交换机，就可以欺骗交换机刷新自己的MAC地址到端口的数据表，假设A主机连接在2口，假设我在4口，要 sniffer A主机的数据，那么我就需要伪造一个ARP数据包，告诉交换机A主机MAC地址是在4口，那么交换机就会将本来发送到A主机的数据会转送到4口上，这个时 候我就可以监听到了A主机的数据传送了，这个就是基于交换网络的arp欺骗sniffer过程。
通过arp 欺骗，一般sniffer有几个方法：
1. 就是上面介绍的欺骗MAC进行数据窃听，但由于A收不到数据，这样它会重新发布ARP包，这样导致sniffer很容易暴露，而且效果不好，A会丢包，同样你的sniffer 一样不会抓到全部的数据。
2. 发起"中间人"窃听。攻击者可以在两台通讯主机之间插入一个中转回路，这样，攻击者既可以sniffer到两机的数据，同样还可以不影响两机的通讯。我们假设X是攻击者的机器，A和B是目标机器。
X如果想发起攻击，首先在向A主机发送一个ARP包，让A认为B机器IP对应的MAC地址是X主机的，同时再向B机器发送一个ARP包，让B机器认为A机器IP对应的MAC地址是X主机的，如下图：

3． MAC flood攻击
通过快速(比如超过1000线程) 发送大量伪造MAC地址数据包，会造成交换机的MAC-端口表塞满，但为了正常数据不被丢弃，大多数交换机会采取类似HUB一样方式：广播的方式发送数 据。这个时候，再在网络中任何一台机器设置网卡为混杂模式，就可以sniffer到任何想要监听的数据了。
*注: 以上方法我并没有正式测试过，理论上可行，实际上还有待验证。
上面介绍了几种常见的基于switch网络的arp spoofing sniffer方法，那么对于一个管理员来说，如何防范这种方式的数据嗅探呢？
严格来说，没有一种通用的方法来解决arp欺骗造成的问题，最大的可能就是采用静态的ARP缓存表，由于静态的ARP表不可以刷新，那么伪造的ARP包将 会被直接丢弃。但这样造成的问题就是，整个网络中的所有机器，都必须要建立一个静态的MAC表，在大型网络中，会增加交换机的负担，造成效率下降。如果机 器更换，那么就要手工去更改MAC地址表，很显然，在大型网络中这种方式是不适用的。
在这里需要注意的是，windows下即使你建立了静态的MAC到IP的影射表，但是在收到了强制更新的ARP包后，依然会刷新机器的影射表，一样会被sniffer到。
高级交换机还提供了MAC绑定功能，指定交换机某个端口和某个MAC绑定，这种方法可以很有效的防止MAC克隆（clone）方式的窃听，但是对于上述的arp 欺骗攻击效果不大。
最可靠的方法就是采用第三方软件来解决，Arpwatch是一个运行在Unix平台下的免费工具，他可以检测到网络中所有MAC地址的变化，一旦网络中的MAC地址有变化，它就会发送一封email到指定地点。

后记：这篇小文写的很简短，其实在交换网络中还有其他几种攻击方法，比如MAC clone等，而且交换网络中的sniffer方法还不止这一种，我这里只是介绍最常见，容易发生和编程实现的sniffer方法，希望对大家有所帮助。本人水平有限，如有错误，请不吝指责.

参考文摘：
1． An Introduction to ARP Spoofing(Sean Whalen)
2． Sniffing (network wiretap, sniffer) FAQ
50：如何利用Sniffer过滤非BT类下载软件的流量？


现在的下载软件例如影音传送带、FlashGet、迅雷等全部支持断点续传和多线程下载的功能。而且下载软件安装后默认的下载线程数均〉1。由此我们可以做出对该类软件的下载监控。
首先要了解断点续传的原理。
所谓断点续传，就是要从文件已经下载的地方开始继续下载。所以在客户端浏览器传给Web服务器的时候要多加一条信息——从哪里开始。这个信息在HTTP协议中由请求头Range来实现。例如：
GET /non-cgi/usr/1/1_3483_10.mp3 HTTP/1.1
Host: new.schoolmusic.net
Accept: */*
User-Agent: Mozilla/4.0 (compatible; MSIE 5.00; Windows 98)
Range: bytes=975782-4878912
Connection: Keep-Alive
“Range”行告诉服务器1_3483_10.mp3这个文件从975782字节开始下载，前面的字节可以不用再传了。
服务器收到请求后，返回如下代码
HTTP/1.1 206 Partial Content
Content-Length: 3903131
Content-Type: audio/mpeg
Content-Range: bytes 975782-4878912/4878913
Last-Modified: Wed, 19 Jan 2005 04:05:33 GMT
Accept-Ranges: bytes
ETag: "90c27e1fdcfdc41:d6a5"
Server: Microsoft-IIS/6.0
X-Powered-By: ASP.NET
Date: Sun, 26 Jun 2005 13:42:19 GMT
接着，客户端就开始续传了。
多线程的下载正是根据这个原理，先将文件标识成若干个块，然后每一个线程下载一个文件块。这个标识正是通过HTTP请求头中的Range来实现。
以下是RFC2616的一段说明：
The semantics of the GET method change to a "partial GET" if the request message includes a Range header field. A partial GET requests that only part of the entity be transferred, as described in section 14.35. The partial GET method is intended to reduce unnecessary network usage by allowing partially-retrieved entities to be completed without transferring data already held by the client。
因此我们初步得到的结论是根据HTTP请求头Range来标识出是否用下载软件下载。可实际上，作过滤器需要找出偏移量，而Range的偏移量并不是一个固定值。这样就无法达成了。
换个方向，看看HTTP的响应帧。
HTTP/1.1 206 Partial Content
返回的代码是206，说明是Partial Content。
根据RFC2616对206相应码的说明，
The server has fulfilled the partial GET request for the resource.
The request MUST have included a Range header field indicating the desired range, and MAY have included an If-Range header field to make the request conditional。
也就是说，当HTTP请求中包含了Range的请求头的时候，HTTP响应码总是206。因此就根据这个就可以设置过滤器了