package com.example.jetnews.ui.bookmarks

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Opacity
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.Padding
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import androidx.ui.material.themeTextStyle
import com.example.jetnews.R
import com.example.jetnews.data.posts
import com.example.jetnews.ui.VectorImageButton
import com.example.jetnews.ui.home.PostCardSimple
import com.example.jetnews.ui.home.isFavorite

@Composable
fun BookmarksScreen(openDrawer: () -> Unit) {

    val bookmarks = posts.filter {
        isFavorite(it.id)
    }

    FlexColumn {
        inflexible {
            TopAppBar(
                title = { Text("Favorites") },
                navigationIcon = {
                    VectorImageButton(R.drawable.ic_jetnews_logo) {
                        openDrawer()
                    }
                }
            )
        }
        if (bookmarks.isNotEmpty())
            flexible(flex = 1f) {
                VerticalScroller {
                    Column {
                        bookmarks.forEach {
                            PostCardSimple(post = it)
                            BookmarksDivider()
                        }
                    }
                }
            }
        else
            expanded(flex = 1f) {
                NoBookmarks()
            }
    }
}

@Composable
private fun NoBookmarks() {
    Container(expanded = true) {
        Text(text = "No Bookmarks", style = +themeTextStyle { h6 })
    }
}

@Composable
private fun BookmarksDivider() {
    Padding(left = 14.dp, right = 14.dp) {
        Opacity(0.08f) {
            Divider()
        }
    }
}
