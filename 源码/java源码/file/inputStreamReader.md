## inputStreamReader

它是字节流向字符流之间的桥梁，读取字节流并使用一个特殊的字符集解码为字符，他可以自己指定，如果不指定，那么就会使用默认的。保证效率可以是有BufferedReader。

### field

```java

//用于decode，大部分操作都是靠他实现的，装饰器模式
private final StreamDecoder sd;

```
### constructor

可以指定字符集，解码器

```java

//默认charset
public InputStreamReader(InputStream in);

//指定charset名
public InputStreamReader(InputStream in, String charsetName)
    throws UnsupportedEncodingException;
    
//指定Charset
public InputStreamReader(InputStream in, Charset cs);

//指定CharsetDecoder
public InputStreamReader(InputStream in, CharsetDecoder dec);


```
### getEncode

```java

//获取编码方式
public String getEncoding() {
    return sd.getEncoding();
}
```
### 读取字符

```
//读到cbuf中，offset为开始存储的位置，length为最大读取长度
public int read(char cbuf[], int offset, int length) throws IOException {
    return sd.read(cbuf, offset, length);
}
```

读取字符，将字符从开始读取到最后一个字符

### ready

```java

//是否可读
public boolean ready() throws IOException {
    return sd.ready();
}


```

### close

```java
//关闭流
public void close() throws IOException {
    sd.close();
}

```