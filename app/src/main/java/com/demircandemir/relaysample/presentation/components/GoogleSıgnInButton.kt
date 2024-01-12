package com.demircandemir.relaysample.presentation.components
// import androidx.compose.foundation.layout.PaddingValues
// import androidx.compose.foundation.layout.fillMaxHeight
// import androidx.compose.foundation.layout.fillMaxWidth
// import androidx.compose.foundation.layout.padding
// import androidx.compose.foundation.layout.requiredHeight
// import androidx.compose.foundation.layout.requiredWidth
// import androidx.compose.foundation.layout.wrapContentHeight
// import androidx.compose.runtime.Composable
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.graphics.Color
// import androidx.compose.ui.res.painterResource
// import androidx.compose.ui.text.font.FontWeight
// import androidx.compose.ui.text.style.TextAlign
// import androidx.compose.ui.unit.dp
// import androidx.compose.ui.unit.em
// import androidx.compose.ui.unit.sp
// import com.demircandemir.relaysample.R
//
//
// @Composable
// fun SGnWithGoogle(
// modifier: Modifier = Modifier,
// onClick: () -> Unit
// ) {
// TopLevel(
// modifier = modifier
// ) {
// androidx.compose.material3.Button(
// onClick = onClick
// ) {
// StateLayer(modifier = Modifier.weight(1.0f)) {
// Icon {
// Vector(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
// Vector1(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
// Vector2(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
// Vector3(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
// Vector4(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
// }
// LabelText()
// }
// }
// }
// }
//
// /*@Preview(widthDp = 296, heightDp = 40)
// @Composable
// private fun SGnWithGooglePreview() {
// MaterialTheme {
// SGnWithGoogle()
// }
// }*/
//
// @Composable
// fun Vector(modifier: Modifier = Modifier) {
// RelayVector(
// vector = painterResource(R.drawable.s_gn_with_google_vector),
// modifier = modifier.padding(
// paddingValues = PaddingValues(
// start = 9.0.dp,
// top = 7.5.dp,
// end = 1.0799999237060547.dp,
// bottom = 2.748750686645508.dp
// )
// ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
// )
// }
//
// @Composable
// fun Vector1(modifier: Modifier = Modifier) {
// RelayVector(
// vector = painterResource(R.drawable.s_gn_with_google_vector1),
// modifier = modifier.padding(
// paddingValues = PaddingValues(
// start = 1.62744140625.dp,
// top = 10.5675048828125.dp,
// end = 3.540058135986328.dp,
// bottom = 0.749995231628418.dp
// )
// ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
// )
// }
//
// @Composable
// fun Vector2(modifier: Modifier = Modifier) {
// RelayVector(
// vector = painterResource(R.drawable.s_gn_with_google_vector2),
// modifier = modifier.padding(
// paddingValues = PaddingValues(
// start = 0.75.dp,
// top = 5.29498291015625.dp,
// end = 13.616249561309814.dp,
// bottom = 5.295016288757324.dp
// )
// ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
// )
// }
//
// @Composable
// fun Vector3(modifier: Modifier = Modifier) {
// RelayVector(
// vector = painterResource(R.drawable.s_gn_with_google_vector3),
// modifier = modifier.padding(
// paddingValues = PaddingValues(
// start = 1.62744140625.dp,
// top = 0.75.dp,
// end = 3.4800586700439453.dp,
// bottom = 10.567500114440918.dp
// )
// ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
// )
// }
//
// @Composable
// fun Vector4(modifier: Modifier = Modifier) {
// RelayVector(modifier = modifier.padding(paddingValues = PaddingValues(all = 0.75.dp)).fillMaxWidth(1.0f).fillMaxHeight(1.0f))
// }
//
// @Composable
// fun Icon(
// modifier: Modifier = Modifier,
// content: @Composable RelayContainerScope.() -> Unit
// ) {
// RelayContainer(
// isStructured = false,
// content = content,
// modifier = modifier.requiredWidth(18.0.dp).requiredHeight(18.0.dp)
// )
// }
//
// @Composable
// fun LabelText(modifier: Modifier = Modifier) {
// RelayText(
// content = "Continue with Google",
// color = Color(
// alpha = 255,
// red = 255,
// green = 255,
// blue = 255
// ),
// height = 1.4285714721679688.em,
// letterSpacing = 0.10000000149011612.sp,
// textAlign = TextAlign.Left,
// fontWeight = FontWeight(500.0.toInt()),
// modifier = modifier.wrapContentHeight(
// align = Alignment.CenterVertically,
// unbounded = true
// )
// )
// }
//
// @Composable
// fun StateLayer(
// modifier: Modifier = Modifier,
// content: @Composable RelayContainerScope.() -> Unit
// ) {
// RelayContainer(
// arrangement = RelayContainerArrangement.Row,
// padding = PaddingValues(
// start = 16.0.dp,
// top = 10.0.dp,
// end = 0.0.dp,
// bottom = 10.0.dp
// ),
// itemSpacing = 8.0,
// clipToParent = false,
// content = content,
// modifier = modifier.fillMaxWidth(1.0f).requiredHeight(40.0.dp)
// )
// }
//
// @Composable
// fun Button(
// modifier: Modifier = Modifier,
// content: @Composable RelayContainerScope.() -> Unit
// ) {
// RelayContainer(
// backgroundColor = Color(
// alpha = 255,
// red = 19,
// green = 56,
// blue = 0
// ),
// mainAxisAlignment = MainAxisAlignment.SpaceBetween,
// clipToParent = false,
// radius = 100.0,
// borderAlignment = BorderAlignment.Center,
// content = content,
// modifier = modifier.requiredWidth(296.0.dp).requiredHeight(40.0.dp)
// )
// }
//
// @Composable
// fun TopLevel(
// modifier: Modifier = Modifier,
// content: @Composable RelayContainerScope.() -> Unit
// ) {
// RelayContainer(
// mainAxisAlignment = MainAxisAlignment.Start,
// crossAxisAlignment = CrossAxisAlignment.Start,
// clipToParent = false,
// content = content,
// modifier = modifier
// )
// }