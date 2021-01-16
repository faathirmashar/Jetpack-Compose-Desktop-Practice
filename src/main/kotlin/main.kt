import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DesktopDialogProperties
import androidx.compose.ui.window.Dialog
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.text.TableView

private val Yellow200 = Color(0xffffeb46)
private val Blue200 = Color(0xff91a4fc)

private val LightColors = lightColors(
    primary = Yellow200,
    secondary = Blue200,
)

fun getWindowIcon(): BufferedImage {
    var image: BufferedImage? = null
    try {
        image = ImageIO.read(File("logo.png"))

    } catch (e: Exception) {
        println(e.message)
    }

    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
}

fun main() {
    mainWindow()
}

fun mainWindow() {
    Window(title = "Latihan", icon = getWindowIcon()) {
        val dialogState = remember { mutableStateOf(false) }
        var lat: String? by remember { mutableStateOf(null) }

        if (dialogState.value) {
            Dialog(
                onDismissRequest = { dialogState.value = false },
                properties = DesktopDialogProperties(undecorated = true)
            ) {
                Box(modifier = Modifier.fillMaxSize().background(Color.Blue), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = { dialogState.value = false }
                    ) {
                        Text("Close")
                    }
                }
            }
        }

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        MaterialTheme(colors = LightColors) {
            Scaffold(
                topBar = {TopAppBar(title = { Text("Latihan")}, navigationIcon = {Icon(Icons.Default.Menu, modifier = Modifier.clickable(onClick = {
                    scaffoldState.drawerState.open()
                }))})},
                drawerContent = {},
                scaffoldState = scaffoldState
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Login",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.preferredHeight(height = 16.dp))
                    OutlinedTextField(
                        value = lat ?: "",
                        onValueChange = { lat = it },
                        label = { Text(text = "Username") },
                        modifier = Modifier.background(Color.White)
                    )
                    Spacer(Modifier.preferredHeight(height = 16.dp))
                    OutlinedTextField(
                        value = lat ?: "",
                        onValueChange = { lat = it },
                        label = { Text(text = "Password") },
                    )
                    Spacer(Modifier.preferredHeight(height = 16.dp))
                    Row {
                        Button(
                            onClick = {},
                        ) {
                            Text("Register")
                        }
                        Spacer(Modifier.preferredWidth(width = 32.dp))
                        Button(
                            onClick = { dialogState.value = true }
                        ) {
                            Text("Login")
                        }
                    }
                }
            }
        }
    }
}