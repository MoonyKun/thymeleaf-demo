# HTML

## 标签

### div

在网页制作过程过中，可以把一些独立的逻辑部分划分出来，放在一个`<div>`标签中，这个<div>标签的作用就相当于一个容器。

**可以这么理解在一个页面（大楼）中分解出了很多小页面（房间）他的作用是存储内容。**

### header

header标签代表头部，但作用等同于div，只是具备语义化。

### footer

定义底部区域

### section

定义正文区域

### aside

定义侧边栏区域

### br

br标签实现换行

```HTML
<br />
<br>
```

### &nbsp

在html代码中输入空格是不起作用的,使用&nbsp实现换行

### hr

hr 标签实现水平分界线

```
<hr> <hr />
```

### ul,li

使用ul,li标签实现无序列表

### ol,li

ol,li标签实现有序列表

### img

img标签为网页添加图片

- src：**标识图像的位置；

- alt：**指定图像的描述性文本，当图像不可见时（下载不成功时），可看到该属性指定的文本；

- title：**提供在图像可见时对图像的描述(鼠标滑过图片时显示的文本)；

- 图像可以是GIF，PNG，JPEG格式的图像文件。

### a

a标签为网页添加超链接

```HTML
<a  href="目标网址"  title="鼠标滑过显示的文本" target = "_self or _blank">链接显示的文本</a>
```

a标签有的target属性，代表打开网页的方式。可选值为”_self和_blank”，默认值为_self，代表在当前页面打开链接，_blank代表在新窗口打开链接。

## 列表标签

### table、tr、th、td

1、<table>…</table>：整个表格以<table>标记开始、</table>标记结束。

2、<tr>…</tr>：表格的一行，所以有几对tr 表格就有几行。tr table row

3、<td>…</td>：表格的一个单元格，一行中包含几对<td>...</td>，说明一行中就有几列。   table danuyan

4、<th>…</th>：表格的头部的一个单元格，表格表头。 table header 表格头使用th有加粗的效果

5、表格中列的个数，取决于一行中数据单元格的个数。

6、border属性可以为表格添加边框，属性值为数字。

###  thead,tbody,tfoot

`<tbody>…</tbody>`：如果不加<thead><tbody><tfooter> , table表格加载完后才显示。加上这些表格结构， tbody包含行的内容下载完优先显示，不必等待表格结束后在显示，同时如果表格很长，用tbody分段，可以一部分一部分地显示。（通俗理解table 可以按结构一块块的显示，不在等整个表格加载完后显示。）

thead、tfoot 以及 tbody 元素使您有能力对表格中的行进行分组。当您创建某个表格时，您也许希望拥有一个标题行，一些带有数据的行，以及位于底部的一个总计行。这种划分使浏览器有能力支持独立于表格标题和页脚的表格正文滚动。当长的表格被打印时，表格的表头和页脚可被打印在包含表格数据的每张页面上。

## 表单标签

### form

```HTML
<form   method="传送方式"   action="服务器文件">
```

1.**<form> ：**<form>标签是成对出现的，以<form>开始，以</form>结束。

2.**action** **：**浏览者输入的数据被传送到的地方,比如一个PHP页面(save.php)。

3.**method** **：** 数据传送的方式（get/post）。

**注意:**

**1、所有表单控件（文本框、文本域、按钮、单选框、复选框等）都必须放在 <form></form> 标签之间（否则用户输入的信息可提交不到服务器上哦！）。**

### input

```HTML
<form>
   <input type="text/password" name="名称" value="文本" />
</form>
```

placeholder属性：input标签中占位符placeholder,属性，有时候需要提示用户输入框需要输入框的内容。可以理解为在输入框内的内容。

input的type属性设置为number,则表示该输入框的类型为数字

input的type属性设置为url,则表示该输入框的类型为网址。

Input的type属性设置为email,则表示该输入框的类型为邮箱

#### 单选框与复选框

1、**type:**

   当 **type="radio"** 时，控件为**单选框**

   当 **type="checkbox"** 时，控件为**复选框**

2、**value：**提交数据到服务器的值（后台程序PHP使用）

3、**name：**为控件命名，以备后台程序 ASP、PHP 使用

4、**checked：**当设置 checked="checked" 时，该选项被默认选中

```HTML
<form action="save.php" method="post">
        <label>性别:</label>
        <label>男</label>
        <input type="radio" value="1" name="gender" />
        <label>女</label>
        <input type="radio" value="2" name="gender" />
    </form>
```

注意**:同一组**的单选按钮，name 取值一定要一致，比如上面例子为同一个名称“radioLove”，这样同一组的单选按钮才可以起到单选的作用。

### 提交按钮

```HTML
<input   type="submit"   value="提交" />
```

type：只有当type值设置为submit时，按钮才有提交作用

value按钮上显示的文字`

### 重置按钮

```HTML
<input type="reset" value="重置" />
```

type：只有当type值设置为reset时，按钮才有重置作用
value：按钮上显示的文字

### textarea

当用户需要在表单中输入大段文字时，需要用到文本输入域。

```HTML
<form  method="post" action="save.php">
        <label>联系我们</label>
        <textarea cols="50" rows="10" >在这里输入内容...</textarea>
</form>
```

### label

如果你在 label 标签内点击文本，就会触发此控件。

```HTML
<form
  <label for="email">输入你的邮箱地址</label>
  <input type="email" id="email" placeholder="Enter email">
</form>
```

### select

```HTML
<form>
        <select>
            <option value="看书">看书</option>
            <option value="旅游" selected = "selected">旅游</option>
            <option value="运动">运动</option>
            <option value="购物">购物</option>
        </select>
</form>
```

1、select和option标签都是双标签，它总是成对出现的，需要首标签和尾标签。

2、select标签里面只能放option标签，表示下拉列表的选项。

3、option标签放选项内容，不放置其他标签。

4、value：属性为提交给服务器的值，标签内为需要显示的值

5，selected="selected"：

设置selected="selected"属性，则该选项就被默认选中。