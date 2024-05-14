package net.ezra.ui.donate


import android.location.Location
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import net.ezra.R
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


@Composable
fun DonateFood(navController: NavHostController) {

    Scaffold(
        bottomBar = {BottomBar() }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xffE6EFF0))

        ){
            LazyColumn {
                item {
                    Box(modifier = Modifier.fillMaxSize()) {
//
                        Column(

                            modifier = Modifier.padding(15.dp),

                            horizontalAlignment = Alignment.CenterHorizontally
                        ){

                            Text(text = "Donate Food")

                            var photoUri: Uri? by remember { mutableStateOf(null) }
                            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                                photoUri = uri
                            }

                            var FoodType by rememberSaveable {
                                mutableStateOf("")
                            }

                            var Quantity by rememberSaveable {
                                mutableStateOf("")
                            }

                            var Email by rememberSaveable {
                                mutableStateOf("")
                            }

                            var Location by rememberSaveable {
                                mutableStateOf("")
                            }




                            OutlinedTextField(
                                value = FoodType,
                                onValueChange = { FoodType = it },
                                label = { Text(text = "Food Type") },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            )

                            OutlinedTextField(
                                value = Quantity,
                                onValueChange = { Quantity= it },
                                label = { Text(text = "Quantity") },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            )

                            OutlinedTextField(
                                value = Email,
                                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
                                onValueChange = { Email= it },
                                label = { Text(text = "Email") },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            )

                            OutlinedTextField(
                                value = Location,
                                leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "emailIcon") },
                                onValueChange = { Location= it },
                                label = { Text(text = "Location") },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            )






                            OutlinedButton(
                                onClick = {
                                    launcher.launch(
                                        PickVisualMediaRequest(
                                            //Here we request only photos. Change this to .ImageAndVideo if you want videos too.
                                            //Or use .VideoOnly if you only want videos.
                                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                }
                            ) {
                                Text("Select Image")
                            }


                            if (photoUri != null) {
                                //Use Coil to display the selected image
                                val painter = rememberAsyncImagePainter(
                                    ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(data = photoUri)
                                        .build()
                                )

                                Image(
                                    painter = painter,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(150.dp)
                                        .fillMaxWidth()
                                        .border(1.dp, Color.Gray),
                                    contentScale = ContentScale.Crop,

                                    )
                            }


                            OutlinedButton(onClick = {
                                photoUri?.let { uploadImageToFirebaseStorage(it, FoodType, Email, Location) }

                            }) {

                                Text(text = "Submit Donation")


                            }



                        }


                    }



                }
            }

        }





    }



}



fun uploadImageToFirebaseStorage(imageUri: Uri, FoodType: String, Email: String, Location: String) {
    val storageRef = FirebaseStorage.getInstance().reference
    val imageRef = storageRef.child("images/${UUID.randomUUID()}")

    val uploadTask = imageRef.putFile(imageUri)
    uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        imageRef.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val downloadUri = task.result
            saveToFirestore(downloadUri.toString(), FoodType, Email, Location)
        } else {


        }
    }
}

fun saveToFirestore(imageUrl: String, FoodType: String, Email: String, Location: String) {
    val db = Firebase.firestore
    val imageInfo = hashMapOf(
        "imageUrl" to imageUrl,
        "FoodType" to FoodType,
        "Email" to Email,
        "Location" to Location,



        )




    db.collection("Students")
        .add(imageInfo)
        .addOnSuccessListener {



        }
        .addOnFailureListener {
            // Handle error
        }
}


@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            }
        )

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"")
        },
            label = { Text(text = "Donate") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Log in") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
    }

}







@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    DonateFood(rememberNavController())
}