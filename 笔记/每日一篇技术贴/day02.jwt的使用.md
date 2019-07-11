# JWT
```
    http是无状态的，也就是每次不会自己维护自己的访问，每一次请求都是新的，遇到的问题就是，登录一个网站之后，就会一直显示未登录，虽然已经登录过了，所以有一个东西可以保存住已登录的这个状态，这个东西就是session，这个session保存在服务端，web项目通过session的认证，就会有一个jsessionid存放在cookie中，以便与服务器保持着会话。

    现在常用vue或者安卓它只需要一些json数据的时候，就需要通过token来认证，每次请求都会将token存放在header中，服务端解析token，并确定用户身份和用户权限，数据通过jsoon交互。

    但是token是一般只是拿取一段uuid，但是有多个服务器这就比较麻烦，就需要第三个东西来存放它，存放在哪里不至于每次查询好久，又能使得每一个服务器都可以访问到，所以就需要第三方的一个存储，比如redis，或者是使用mongodb。

    所以有一个新方法可以解决这个问题，这个东西就是jwt，他是一个特殊的token

    session的执行步骤   
        客户端第一次发送请求到达客户端，客户端获取session但是未获取到，那就给cookie中存放一个sessionid,然后在服务器端存放一个sessionid，下一次访问的时候，就会从cookie中获取这个sessionid，如果有就可以直接访问，如果么有就是第一次访问，给一个session，如果说过期了，那就和没有一样处理。

    总结一下：
        记录状态：
        （1）session和cookie组合  这个就是将数据sessionid存放在cookie中，每次带着cookie就可以了，如果说使用cookie加入禁用了，也可以使用url重写的方式传递这个sessionid。分布式的解决方法，就是做成分布式的，比如spring+redis。
        （2）token  token中存储一个uuid，这个是服务器给的，到达服务器然后解析这个token，将token中的数据解析到，在与服务器端做比较。
        （3）jwt【下文说】

jwt是json web token,他也是token，开头是session这个东西是基于json的开放标准
一般用于在身份提供者和服务提供者间传递被认证的用户身份信息，它可以被用于认证，也可以被用于加密。

jwt的特点： 
    简洁：可以通过URL、post参数或者Http header发生，数据量小，传递速度快。
    自包含：负载中包含了所有用户所需要的信息，避免了多次查询数据库或者缓存。【划重点：不用查询了】

它的结构：
    jwt不用查询，那就自己带着呗，那么怎样带呢
    它的结构分别是：
        头部   载荷   签证
    它既是将一个jwt分为了三个部分，这三个部分使用.隔开

jwt头部承载着两个部分的信息
    ~声明类型，这里是jwt
    ~声明加密的算法，通过是HMACSHA256
    然后使用base64加密，base64是什么鬼，他是表示任意一种二进制数据的方法，常用于在URL、Cookie、网页中传递少量二进制数据，他是使用64位字符串来表示任意一种二进制数据的方法。

    第一部分说完了，他主要存储的是类型和解密算法 。


    jwt的第二部分：载荷
        就时承载消息具体内容的地方
        内容有分为三个部分：
            （1）标准中注册的声明
            （2）公共的声明
            （3）私有的声明
    payload-标准中注册的声明 (建议但不强制使用) ：
        iss: jwt签发者
        sub: jwt所面向的用户
        aud: 接收jwt的一方
        exp: jwt的过期时间，这个过期时间必须要大于签发时间
        nbf: 定义在什么时间之前，该jwt都是不可用的.
        iat: jwt的签发时间
        jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
    payload-公共的声明 ：
        公共的声明可以添加任何的信息。一般这里我们会存放一下用户的基本信息（非敏感信息）。
    payload-私有的声明 ：
        私有声明是提供者和消费者所共同定义的声明。

签证
    用来干啥的，其实就是一个签名信息，使用了自定义的一个密钥然后加密后的结果，目的就是为了保证签名的信息没有被别人改过！（也就是保证jwt安全可用）
    也就是说，签证部分的信息有3个组成部分：
    头部-header (base64后的)
    载荷-payload (base64后的)
    密钥-secret


==========================使用，我没有用过，代码从一个地方复制来的，有空了用的试一下====================================
使用步骤如下：

第一步，导入jar包：

<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
第二步：稍微封装一下，方便集成到项目中：

/**
 * jwt工具类
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017/9/21 22:21
 */
@ConfigurationProperties(prefix = "renren.jwt")
@Component
public class JwtUtils {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String secret;    
    private long expire;    
    private String header;    
    /**
     * 生成jwt token
     */
    public String generateToken(long userId) {
        Date nowDate = new Date();        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);        
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }    
    public Claims getClaimByToken(String token) {        
    try {            
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            logger.debug("validate is token error ", e);            
            return null;
        }
    }    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {        
            return expiration.before(new Date());
    }    
    //getter、setter  

}
JWT适合一次性的命令认证，颁发一个有效期极短的JWT，即使暴露了危险也很小，由于每次操作都会生成新的JWT，因此也没必要保存JWT，真正实现无状态。