# NettyIM
netty实现客户端、服务端交互，主要功能有：登录、退出、单聊、群聊，创建群组，退出群组等；

* master分支为原始的代码，实现了整套系统的核心功能；
* instance_handler分支为对handler使用单例模式，合并等进行了优化的分支；  
这里只需要对server端的handler进行改进，以优化每次channel的时候，都会new一些相关的handler；  
而client端不需要，因为client端大多数时候是单连接的。
