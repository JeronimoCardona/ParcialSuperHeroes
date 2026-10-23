package com.ucaldas.aplicacion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucaldas.aplicacion1.ui.theme.Aplicacion1Theme
import com.ucaldas.aplicacion1.ui.theme.HeroCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Aplicacion1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Utils.Rutas.LISTA
    ) {
        composable(Utils.Rutas.LISTA) {
            // Pasamos la lista de villanos y la acción de navegar
            ListaVillanos(
                villanos = listaVillanos,
                onVerDetalle = { id ->
                    navController.navigate(Utils.Rutas.detalle(id))
                }
            )
        }

        composable(
            route = Utils.Rutas.DETALLE,
            // Error común: no definir el tipo de argumento. Aquí forzamos IntType.
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: return@composable
            val villano = listaVillanos.firstOrNull { it.id == id } ?: return@composable

            DetalleVillano(
                villano = villano,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaVillanos(
    villanos: List<Villano>,
    onVerDetalle: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Archivo de Villanos", style = MaterialTheme.typography.headlineMedium) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    titleContentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(villanos, key = { it.id }) { villano ->
                HeroCard(
                    villano = villano,
                    onClick = { onVerDetalle(villano.id) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleVillano(
    villano: Villano,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expediente: ${villano.nombre}") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    titleContentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Reutilizamos la Card con la opción 1 para ver el detalle enriquecido
                HeroCard(villano = villano, option = 1)
            }
            item {
                Button(
                    onClick = onVolver,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Cerrar Expediente (Volver)")
                }
            }
        }
    }
}
