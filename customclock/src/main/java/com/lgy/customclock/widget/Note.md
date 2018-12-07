### 几点心得

#### 1. 熟悉基本的 API

#### 2. 在画图过程中要对坐标有着清晰的认识，这是可以画图的基础

#### 3. 根据实际的效果不断的调试坐标，此处的坐标只针对本次画图，不具适配性


#### 4. 同步自己有道云笔记内容

* save() 和 restore() restoreTocount()
> 关于 save() 的使用：
当在对 cavans 的坐标进行平移、缩放、旋转等一系列操作后执行 save() ,那么之后的所有的对 cavans 坐标的再次操作均是以上一次操作 save() 后坐标的情况为标准的。在执行restore() 或 restoreTocount() 后，那么此时的坐标情况为最后一个 save() 或 指定的save() 之前的坐标状态。


* translate(90,x,y)
> 以 （x,y） 为**中心**旋转 90 度 cavans 画布，注意这里明确表明的为 以 （x,y）为`中心`，再一次是`中心`，不是原点


* drawText()
> drawText() 基准点为文字的 **左下角**，在操作此 API 时一定需要注意该点


* drawLine()
> 在使用 drawLine() 画直线时，需要明白的是：paint 设置的 strokeWidth 的宽最终的呈现效果是沿线的坐标评价分配的。例子：paint.setStrokeWidth(2f) 时，在线的规定坐标下两侧宽度各为 1 ，需要明白这一点。

