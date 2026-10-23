package com.ucaldas.aplicacion1.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ucaldas.aplicacion1.Villano

@Composable
fun HeroImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun HeroInfo(
    villano: Villano,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "ID: ${villano.id}",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = villano.nombre,
            style = MaterialTheme.typography.headlineSmall, // Ajustado para mejor jerarquía
            modifier = Modifier.padding(top = 4.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = villano.descripcion,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 2 // Compacto para la lista
        )
        Text(
            text = "Poderes: ${villano.superPoderes.joinToString()}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

private fun villainProfile(superPoderes: List<String>): String = when {
    superPoderes.any { it.contains("Inteligencia", ignoreCase = true) } &&
            superPoderes.any { it.contains("Manipulación", ignoreCase = true) } -> "Mente Maestra"
    superPoderes.any { it.contains("Energía", ignoreCase = true) } -> "Amenaza Cósmica"
    superPoderes.any { it.contains("Fuerza", ignoreCase = true) } -> "Destructor"
    else -> "Antagonista"
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroInfoDetail(
    villano: Villano,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Bloque principal destacado
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.errorContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = villano.nombre.firstOrNull()?.toString() ?: "?",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
            Column {
                Text(text = "Amenaza #${villano.id}", style = MaterialTheme.typography.labelLarge)
                Text(text = villano.nombre, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            }
        }

        // Descripción amplia
        Surface(
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Expediente Criminal", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.error)
                Text(text = villano.descripcion, style = MaterialTheme.typography.bodyLarge)
            }
        }

        // Métricas
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Card(modifier = Modifier.weight(1f), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
                Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Maldad", style = MaterialTheme.typography.labelSmall)
                    Text("${villano.nivelMaldad}", style = MaterialTheme.typography.titleLarge)
                }
            }
            Card(modifier = Modifier.weight(1f), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Perfil", style = MaterialTheme.typography.labelSmall)
                    Text(villainProfile(villano.superPoderes), style = MaterialTheme.typography.titleSmall)
                }
            }
        }

        // FlowRow para superpoderes (con Opt-in)
        Text(text = "Habilidades Peligrosas", style = MaterialTheme.typography.titleMedium)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            villano.superPoderes.forEach { poder ->
                AssistChip(
                    onClick = { },
                    label = { Text(poder) }
                )
            }
        }
    }
}

@Composable
fun HeroCard(
    villano: Villano,
    onClick: (() -> Unit)? = null,
    option: Int = 0,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            )
    ) {
        // ERROR CORREGIDO: Usamos Column para que la imagen y el texto no se encimen
        Column {
            HeroImage(
                imageUrl = villano.imagenUrl,
                contentDescription = villano.nombre
            )
            if (option == 0) {
                HeroInfo(villano = villano)
            } else {
                HeroInfoDetail(villano = villano)
            }
        }
    }
}
