fun Recipe.build() {
    buildType {
        id("PathPrefixFail")
        name = "Exec Mode Fails With TEAMCITY_PATH_PREFIX"

        params {
            param("env.TEAMCITY_PATH_PREFIX", "/opt/custom-bin")
        }

        steps {
            exec {
                name = "Run mytool using exec (should fail)"
                path = "mytool"
                arguments = ""
            }
        }
    }
}
