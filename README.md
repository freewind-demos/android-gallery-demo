# Android Gallery 图片画廊演示

## 简介

本 Demo 演示 Android Gallery 组件的基本用法，展示如何创建水平滚动的图片列表。

## 基本原理

Gallery 是 Android 早期的水平列表组件，继承自 AbsSpinner，用于在水平滚动的列表中显示图片。它支持快速滑动，并且可以设置 item 之间的间距和对齐方式。

Gallery 的特点：
- 水平滚动显示内容
- 继承自 AdapterView，需要通过 Adapter 提供数据
- 默认选中第一个 item
- 支持 setOnItemClickListener 监听点击事件

**注意**：Gallery 组件已在 API Level 16（Android 4.1）被标记为过时（Deprecated），官方推荐使用 RecyclerView 的横向布局管理器来替代。

## 启动和使用

### 环境要求
- Android Studio
- JDK 17
- Gradle 8.x

### 安装和运行

1. 用 Android Studio 打开项目
2. 连接 Android 设备或模拟器
3. 点击 Run 运行

### 使用方法
- 左右滑动Gallery可以浏览多个图片
- 点击图片会弹出Toast显示选中项

## 教程

### 什么是 Gallery？

Gallery 是 Android 早期提供的水平滚动列表组件，专门用于展示图片集合。它允许用户通过手指左右滑动来浏览一系列图片，类似于相册的浏览方式。

Gallery 的工作原理：
- 继承自 AdapterView，需要绑定 Adapter 来显示数据
- 使用 Adapter 来提供每个 item 的视图和数据
- 支持自动居中选中项
- 可以设置选中动画

### 为什么使用 Gallery？

在早期 Android 版本中，Gallery 是实现图片画廊的首选方案。它的优点包括：
- 使用简单，只需设置 Adapter 即可
- 自带选中和滑动动画
- 适合展示图片集合

但是它有一些局限性：
- 功能相对有限定制性不高
- 已被官方标记为过时

### 创建 Gallery 布局

在 XML 布局中使用 Gallery 组件：

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Gallery 图片画廊演示"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center"
        android:paddingBottom="16dp" />

    <!-- Gallery 组件 -->
    <Gallery
        android:id="@+id/gallery"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
</LinearLayout>
```

### 设置 Adapter

Gallery 需要通过 Adapter 来提供数据，这里使用简单的 ArrayAdapter：

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 获取 Gallery 组件
        val gallery = findViewById<Gallery>(R.id.gallery)

        // 准备数据
        val items = arrayOf("图片1", "图片2", "图片3", "图片4", "图片5")

        // 设置 Adapter
        // 使用系统提供的 simple_gallery_item 布局
        gallery.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_gallery_item,
            items
        )

        // 设置点击监听器
        gallery.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this,
                "选择了: ${items[position]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
```

### 自定义 Adapter（可选）

如果需要更丰富的图片展示，可以自定义 BaseAdapter：

```kotlin
class ImageAdapter(context: Context) : BaseAdapter() {
    private val context = context
    private val images = intArrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    override fun getCount(): Int = images.size

    override fun getItem(position: Int): Any = images[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView = ImageView(context)
        imageView.setImageResource(images[position])
        imageView.layoutParams = Gallery.LayoutParams(200, 200)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        return imageView
    }
}
```

### 设置 Gallery 属性

可以通过代码或 XML 设置 Gallery 的样式：

```kotlin
// 设置选中项动画
gallery.animationDuration = 2000

// 设置垂直间距
gallery.verticalSpacing = 20

// 设置水平间距
gallery.setSpacing(30)

// 设置选中项基准
gallery.setSelection(2, true)  // 默认选中第三个
```

### 注意事项

1. **过时组件**：Gallery 已在 Android 4.1 被标记过时，新项目应使用 RecyclerView
2. **性能问题**：Gallery 不支持 ViewHolder 模式，大量数据时性能较差
3. **适配器选择**：简单场景用 ArrayAdapter，复杂场景用自定义 BaseAdapter
4. **图片加载**：实际项目中应使用 Glide 或 Picasso 等图片加载库

## 关键代码详解

### MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 获取布局中的 Gallery 组件
        val gallery = findViewById<Gallery>(R.id.gallery)

        // 2. 准备数据 - 这里使用字符串数组，实际项目可替换为图片资源
        val items = arrayOf("图片1", "图片2", "图片3", "图片4", "图片5")

        // 3. 创建 Adapter 并绑定到 Gallery
        // android.R.layout.simple_gallery_item 是系统提供的默认布局
        gallery.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_gallery_item,
            items
        )

        // 4. 设置 item 点击监听器
        // 参数说明：parent-父视图，view-当前项视图，position-位置，id-行id
        gallery.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this,
                "选择了: ${items[position]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
```

### activity_main.xml

```xml
<!-- 根布局：垂直线性布局 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- 标题文本 -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Gallery 图片画廊演示"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center"
        android:paddingBottom="16dp" />

    <!-- Gallery 组件 -->
    <Gallery
        android:id="@+id/gallery"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</LinearLayout>
```
