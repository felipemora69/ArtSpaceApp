package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()
        }
    }
}

@Composable
fun ArtSpaceApp() {
    // List of artworks
    val artworks = listOf(
        Artwork("Mona Lisa", "Leonardo da Vinci", 1503, R.drawable.mona_lisa),
        Artwork("Starry Night", "Vincent Van Gogh", 1889, R.drawable.starry_night),
        Artwork("The Kiss", "Gustav Klimt", 1907, R.drawable.kiss),
        Artwork("Girl with the Pearl", "Johannes Vermeer", 1665, R.drawable.girl_pearl)
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    val currentArtwork = artworks[currentIndex]

    // Create a column with some responsive layout
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Image Box with shadow and responsive padding
        Box(
            modifier = Modifier
                .shadow(8.dp, shape = MaterialTheme.shapes.medium)
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Display the artwork image with responsive scaling
            Image(
                painter = painterResource(id = currentArtwork.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // Image fills width of container
                    .aspectRatio(1f) // Optional: Keep aspect ratio, can be changed based on need
                    .padding(16.dp), // Padding around image for spacing
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Description Box with responsive background color and padding
        Box(
            modifier = Modifier
                .background(Color(0xFFECEBF4)) // Light background for description
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally) // Center alignment
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = currentArtwork.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 32.sp // Larger font size for tablet view
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "${currentArtwork.artist} (${currentArtwork.year})",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp // Slightly larger font size for better readability
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Buttons for navigation, adapted for wider screens
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { currentIndex = if (currentIndex == 0) artworks.size - 1 else currentIndex - 1 },
                modifier = Modifier.weight(1f) // Button takes equal width
            ) {
                Text("Previous")
            }

            Spacer(modifier = Modifier.width(18.dp))

            Button(
                onClick = { currentIndex = if (currentIndex == artworks.size - 1) 0 else currentIndex + 1 },
                modifier = Modifier.weight(1f)
            ) {
                Text("Next")
            }
        }
    }
}

data class Artwork(val title: String, val artist: String, val year: Int, val imageRes: Int)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceApp()
}