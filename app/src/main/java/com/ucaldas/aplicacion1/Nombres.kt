package com.ucaldas.aplicacion1

// Corregimos el error semántico: La clase representa un Villano, no solo "Nombres"
data class Villano(
    val id: Int,
    val nombre: String,
    val imagenUrl: String,
    val descripcion: String,
    val superPoderes: List<String>,
    val nivelMaldad: Int // Agregamos un campo extra para el detalle
)

val listaVillanos = listOf(
    Villano(
        1,
        "Joker",
        "https://static.wikia.nocookie.net/batman/images/b/b5/Joker_Heath_Ledger.jpg",
        "El archienemigo de Batman, un genio criminal con un sentido del humor retorcido.",
        listOf("Inteligencia superior", "Química", "Combate", "Manipulación"),
        100
    ),
    Villano(
        2,
        "Lex Luthor",
        "https://static.wikia.nocookie.net/superman/images/d/d8/Lex_Luthor_DC_Comics.jpg",
        "Uno de los hombres más inteligentes del mundo, obsesionado con derrotar a Superman.",
        listOf("Inteligencia", "Tecnología", "Riqueza"),
        95
    ),
    Villano(
        3,
        "Thanos",
        "https://static.wikia.nocookie.net/marveldatabase/images/5/52/Thanos_%28Earth-616%29_from_Thanos_Vol_2_1.jpg",
        "El Titán Loco que busca el equilibrio universal a través de las Gemas del Infinito.",
        listOf("Super fuerza", "Resistencia", "Energía", "Estratega"),
        110
    ),
    Villano(
        4,
        "Darkseid",
        "https://static.wikia.nocookie.net/dc-microheroes/images/4/4b/Darkseid.jpg",
        "El tirano de Apokolips que busca la Ecuación de la Anti-Vida.",
        listOf("Efecto Omega", "Super fuerza", "Inmortalidad"),
        120
    ),
    Villano(
        5,
        "Duende Verde",
        "https://static.wikia.nocookie.net/villains/images/1/1d/Green_Goblin_Early_2000s.jpg",
        "Norman Osborn, un industrial transformado en maníaco por el Suero del Duende.",
        listOf("Super fuerza", "Inteligencia", "Tecnología", "Combate"),
        85
    )
)
