rootProject.name = "kotlin"
pluginManagement {
    val factlinVersion = "0.1.1"
    repositories {
        maven("https://jcenter.bintray.com/")
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "factlin" ->
                    useModule("com.maeharin:factlin:$factlinVersion")
            }
        }
    }
}

