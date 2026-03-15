# Android Gallery 图片画廊演示

## 简介

本 Demo 演示 Android Gallery 组件的基本用法。

## 基本原理

Gallery 是 Android 早期的水平列表组件，用于显示可滚动的图片列表。

## 教程

```kotlin
val gallery = findViewById<Gallery>(R.id.gallery)
gallery.adapter = ArrayAdapter(this, android.R.layout.simple_gallery_item, items)
gallery.setOnItemClickListener { _, _, position, _ ->
    // 处理点击
}
```

## 注意

Gallery 已过时，推荐使用 RecyclerView 横向列表。
