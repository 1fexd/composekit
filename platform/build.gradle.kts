plugins {
    `java-platform`
}

val group = "com.github.1fexd.composekit"

dependencies {
    constraints {
        rootProject.allprojects.filter { it != rootProject && it != project }.forEach { project ->
            api("$group:${project.name}:${project.version}")
        }
    }
}
