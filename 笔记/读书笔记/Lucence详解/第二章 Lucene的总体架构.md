# Lucene的总体架构

​	Lucene是一个高效的，可以扩展的、全文速索库。使用java语言实现，无需配置。仅仅支持纯文本的速索，不负责由其他格式抽取纯文本，或者是网络文件。基本分为三个部分，创建索引，索引，查询。

	## 创建索引

​	被索引的文档用document对象表表示，IndexWriter通过函数的addDocument将文档加入到索引表中，实现创建索引的过程，Lucene的索引是一个反向索引的过程，当用户有请求，Query代表用户查询语句代表用户的查询语句，IndexSearcher通过函数search索引索引，IndexSearcher计算权重和排序并将信息返回给用户，返回给用户的是一个排序之后的结果。

​	创建一个IndexWriter将document放入iw中去，IndexWriter有几个参数：文件位置、分析器。Document是代表创建索引的字段。

​	速索过程如下：IndexReader将磁盘上的文件读入内存，IndexSearch对文件进行速索，分析器对用户的速索进行分析。然后形成查索树，然后对树进行查询，将结果在进行排序。



## 索引各部分介绍



​		