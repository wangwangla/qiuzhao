## File类

FIle类并不是真正的文件，或者目录，它仅仅是实现了Serializable,Comparable<File>接口的，主要提供了对文件或者文件夹的操作，文件权限等，FIle对象代表的是磁盘中的文件和目录。

### 关于路径

绝对路径和相对路径，路径转换为平台无关的，即就是抽象路径

抽象路径转化为绝对路径的时候，相邻之间加入一个系统默认的分隔符，抽象路径名转换为路径名，使用的是可以支持的分隔符进行分割，分割符会根据平台进行转换，实现平台无关。

#### 常量

```java
/**
     * The FileSystem object representing the platform's local file system.
     */
    private static final FileSystem fs = DefaultFileSystem.getFileSystem();
```

FileSystem仅仅是一个接口，它的实现是由平台进行实现的

```java
//抽象路径名标准化了的路径名，使用默认的分隔符，不含多余的分隔符
private final String path;
```

使用默认的分隔符，在不同的操作系统上，根据本地分隔符，将路径转化为系统的分隔符

```java
//从系统配置中获取的平台文件分隔符
public static final char separatorChar = fs.getSeparator();
```

#### 构造函数

```java
//给定路径名，转换为抽象路径名
public File(String pathname) {
    if (pathname == null) {
        throw new NullPointerException();
    }
    //将路径转化为抽象的
    this.path = fs.normalize(pathname);
    this.prefixLength = fs.prefixLength(this.path);
}

   /**
     * Internal constructor for already-normalized pathname strings.
     */
    private File(String pathname, int prefixLength) {
        this.path = pathname;
        this.prefixLength = prefixLength;
    }

    /**
     * Internal constructor for already-normalized pathname strings.
     * The parameter order is used to disambiguate this method from the
     * public(File, String) constructor.
     */
    private File(String child, File parent) {
        assert parent.path != null;
        assert (!parent.path.equals(""));
        this.path = fs.resolve(parent.path, child);
        this.prefixLength = parent.prefixLength;
    }

    public File(String pathname) {
        if (pathname == null) {
            throw new NullPointerException();
        }
        this.path = fs.normalize(pathname);
        this.prefixLength = fs.prefixLength(this.path);
    }

   
    public File(String parent, String child) {
        if (child == null) {
            throw new NullPointerException();
        }
        if (parent != null) {
            //如果父亲是空串
            if (parent.equals("")) {
                //那么就使用默认的进行解析子类
                this.path = fs.resolve(fs.getDefaultParent(),
                                       fs.normalize(child));
            } else {
                //如果不是空串，那么就从父类开始解析
                this.path = fs.resolve(fs.normalize(parent),
                                       fs.normalize(child));
            }
        } else {
            //如果是null,,那就只看子路径
            this.path = fs.normalize(child);
        }
        this.prefixLength = fs.prefixLength(this.path);
    }

    public File(File parent, String child) {
        if (child == null) {
            throw new NullPointerException();
        }
        if (parent != null) {
            if (parent.path.equals("")) {
                this.path = fs.resolve(fs.getDefaultParent(),
                                       fs.normalize(child));
            } else {
                this.path = fs.resolve(parent.path,
                                       fs.normalize(child));
            }
        } else {
            this.path = fs.normalize(child);
        }
        this.prefixLength = fs.prefixLength(this.path);
    }

   
    public File(URI uri) {

        // Check our many preconditions
        if (!uri.isAbsolute())
            throw new IllegalArgumentException("URI is not absolute");
        if (uri.isOpaque())
            throw new IllegalArgumentException("URI is not hierarchical");
        String scheme = uri.getScheme();
        if ((scheme == null) || !scheme.equalsIgnoreCase("file"))
            throw new IllegalArgumentException("URI scheme is not \"file\"");
        if (uri.getAuthority() != null)
            throw new IllegalArgumentException("URI has an authority component");
        if (uri.getFragment() != null)
            throw new IllegalArgumentException("URI has a fragment component");
        if (uri.getQuery() != null)
            throw new IllegalArgumentException("URI has a query component");
        String p = uri.getPath();
        if (p.equals(""))
            throw new IllegalArgumentException("URI path component is empty");

        // Okay, now initialize
        p = fs.fromURIPath(p);
        //根据输入的路径，将其转换为本地支持的分隔符
        if (File.separatorChar != '/')
            p = p.replace('/', File.separatorChar);
        this.path = fs.normalize(p);
        this.prefixLength = fs.prefixLength(this.path);
    }
```

